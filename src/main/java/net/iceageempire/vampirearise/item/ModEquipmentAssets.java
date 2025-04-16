package net.iceageempire.vampirearise.item;

import net.iceageempire.vampirearise.VampireArise;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModEquipmentAssets {
    public static final RegistryKey<EquipmentAsset> RUBY_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VampireArise.MOD_ID, "ruby"));
}

