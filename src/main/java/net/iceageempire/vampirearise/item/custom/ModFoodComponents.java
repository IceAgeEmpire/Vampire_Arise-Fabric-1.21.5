package net.iceageempire.vampirearise.item.custom;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModFoodComponents {
    public static final FoodComponent RUBY_POTATO = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3f)
            .alwaysEdible().build();

    public ModFoodComponents(Item.Settings component, Identifier rubyPotato) {
    }
}
