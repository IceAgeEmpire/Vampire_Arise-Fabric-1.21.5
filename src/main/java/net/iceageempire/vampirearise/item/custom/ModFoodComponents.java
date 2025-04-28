package net.iceageempire.vampirearise.item.custom;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

import static net.minecraft.component.type.ConsumableComponents.food;

public class ModFoodComponents {
    public static final FoodComponent RUBY_POTATO = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3f)
            .alwaysEdible().build();

    public static final ConsumableComponent RUBY_POTATO_EFFECT = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 18000, 1)
                    )
            )
            .build();

    public static final FoodComponent PINEAPPLE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6f)
            .build();
}
