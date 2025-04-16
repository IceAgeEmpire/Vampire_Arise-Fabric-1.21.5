package net.iceageempire.vampirearise.item;

import net.iceageempire.vampirearise.util.ModTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModArmorMaterials {
    public static final ArmorMaterial RUBY_ARMOR = new ArmorMaterial(
            24, // durability multiplier
            Map.of(
                    EquipmentType.HELMET, 2,
                    EquipmentType.CHESTPLATE, 6,
                    EquipmentType.LEGGINGS, 5,
                    EquipmentType.BOOTS, 2
            ),
            20, // Enchantability
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0, // Toughness
            0, // Knockback Resistance
            TagKey.of(RegistryKeys.ITEM, Identifier.of("vampirearise", "ruby_repair")),
            ModEquipmentAssets.RUBY_ARMOR_MATERIAL_KEY);
}