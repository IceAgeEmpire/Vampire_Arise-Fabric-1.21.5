package net.iceageempire.vampirearise.item.custom;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

import java.util.List;

import static net.minecraft.component.type.ConsumableComponents.food;

public class CustomConsumableComponents {
    public static final ConsumableComponent RUBY_POTATO = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 18000, 1)
                    )
            )
            .build();

//    public static final ConsumableComponent RUBY_POTATO = food()
//            .consumeEffect(
//                    new ApplyEffectsConsumeEffect(
//                            List.of(
//                                    new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1),
//                                    new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0),
//                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0),
//                                    new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3)
//                            )
//                    )
//            )
//            .build();
}
