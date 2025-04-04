package net.iceageempire.vampirearise.item.custom;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent RUBY_POTATO = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3f)
            .alwaysEdible().build();
}
