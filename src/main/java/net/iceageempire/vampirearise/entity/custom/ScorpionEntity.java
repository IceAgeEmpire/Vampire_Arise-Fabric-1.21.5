package net.iceageempire.vampirearise.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationState;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;


public class ScorpionEntity extends PathAwareEntity implements GeoEntity {
    private static final RawAnimation ANIMATION_IDLE = RawAnimation.begin().thenLoop("animation.scorpion.idle");
    private static final RawAnimation ANIMATION_WALK = RawAnimation.begin().thenLoop("animation.scorpion.walk");
    private static final RawAnimation ANIMATION_ATTACK = RawAnimation.begin().then("animation.scorpion.attack", Animation.LoopType.PLAY_ONCE);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean isAttacking = false;
    private AttackGoal attackGoal;

    public ScorpionEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 40)
                .add(EntityAttributes.ENTITY_INTERACTION_RANGE, 10)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.ATTACK_DAMAGE, 4)
                .add(EntityAttributes.FOLLOW_RANGE, 20);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.attackGoal = new AttackGoal(this);
        this.goalSelector.add(4, this.attackGoal);
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.add(3, new TargetGoal<>(this, IronGolemEntity.class));
    }

    public static class AttackGoal extends MeleeAttackGoal {
        private final ScorpionEntity scorpion;
        private boolean isAttacking = false; // Local flag

        public AttackGoal(ScorpionEntity scorpion) {
            super(scorpion, 1.0, true);
            this.scorpion = scorpion;
        }

        @Override
        public void start() {
            super.start();
            this.isAttacking = true;
            this.scorpion.setAttacking(true); //redundant
        }

        @Override
        public void stop() {
            super.stop();
            this.isAttacking = false;
            this.scorpion.setAttacking(false);
        }

        @Override
        protected void attack(LivingEntity target) {
            if (this.canAttack(target)) {
                this.resetCooldown();
                this.scorpion.swingHand(Hand.MAIN_HAND);
                this.scorpion.tryAttack(getServerWorld(this.scorpion), target);
                this.isAttacking = true; // Set local flag *during* attack
                this.scorpion.setAttacking(true);
            }
        }

        @Override
        public void tick() {
            super.tick();
            if (this.isAttacking && this.getCooldown() <= 0) { //check if attacking and cooldown
                this.isAttacking = false;
                this.scorpion.setAttacking(false);
            }
        }

        @Override
        public boolean shouldContinue() {
            return super.shouldContinue();
        }
    }

    static class TargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        public TargetGoal(ScorpionEntity scorpion, Class<T> targetEntityClass) {
            super(scorpion, targetEntityClass, true);
        }

        @Override
        public boolean canStart() {
            float f = this.mob.getBrightnessAtEyes();
            return !(f >= 0.5F) && super.canStart();
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SPIDER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SPIDER_DEATH;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("main_controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationTest<GeoAnimatable> state) {
        if (this.isAttacking) {
            state.setAnimation(ANIMATION_ATTACK);
            return PlayState.CONTINUE;
        }
        if (state.isMoving()) {
            state.setAnimation(ANIMATION_WALK);
            return PlayState.CONTINUE;
        } else {
            state.setAnimation(ANIMATION_IDLE);
            return PlayState.CONTINUE;
        }
    }

    public boolean isAttacking() {
        return this.isAttacking;
    }
}