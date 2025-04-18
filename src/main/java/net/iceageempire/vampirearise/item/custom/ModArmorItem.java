package net.iceageempire.vampirearise.item.custom;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.iceageempire.vampirearise.item.ModArmorMaterials;
import net.iceageempire.vampirearise.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.LightType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ModArmorItem extends Item {
    public int blindnessTimer = 200;

    public int getBlindessTimer() {
        return blindnessTimer;
    }

    public void setBlindnessTimer(int blindnessTimer){
        this.blindnessTimer = blindnessTimer;
    }

    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>())
                    .put(ModArmorMaterials.RUBY_ARMOR,
                            List.of(new StatusEffectInstance(StatusEffects.HASTE, 400, 2, false, false),
                                    new StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, 1, false, false))).build();

    public ModArmorItem(Settings settings) {
        super(settings);
    }


    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player) {
                if(hasFullSuitOfArmorOn(player)) {
                    blindnessTimer--;
                    StatusEffectInstance effect = player.getStatusEffect(StatusEffects.REGENERATION);
                    if(effect != null){
                        if(blindnessTimer <= 0){
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 0, false, false, true));
                            Random rand = new Random();
                            int nextBlindess = rand.nextInt(4800) + 12000;
                            blindnessTimer+=nextBlindess;
                        }
                        if(effect.getDuration()<20){
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0, false, false, true));

                        }
                    }
                    else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0, false, false, true));
                    }

                    if(world.isDay() && world.isSkyVisible(player.getBlockPos())){
                       player.setFireTicks(100);
                    }
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack helmet = player.getInventory().getStack(39);
        ItemStack chestplate = player.getInventory().getStack(38);
        ItemStack leggings = player.getInventory().getStack(37);
        ItemStack boots = player.getInventory().getStack(36);

        return helmet.getItem() instanceof ModArmorItem &&
                chestplate.getItem() instanceof ModArmorItem &&
                leggings.getItem() instanceof ModArmorItem &&
                boots.getItem() instanceof ModArmorItem;
    }
}