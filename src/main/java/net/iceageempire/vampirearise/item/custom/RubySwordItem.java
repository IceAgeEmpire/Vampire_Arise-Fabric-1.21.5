package net.iceageempire.vampirearise.item.custom;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class RubySwordItem extends Item {


    public RubySwordItem(Settings settings) {
        super(settings.sword(ToolMaterial.DIAMOND,1,-2.4f));
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if(attacker.getHealth() < attacker.getMaxHealth()) attacker.setHealth(Math.min(attacker.getHealth() + 1f, attacker.getMaxHealth()));
        //if(target.isDead()) attacker.setAbsorptionAmount(Math.min(attacker.getAbsorptionAmount()+2, 20));
        if(target.isDead()) attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,600,0));

        super.postDamageEntity(stack, target, attacker);
    }

}
