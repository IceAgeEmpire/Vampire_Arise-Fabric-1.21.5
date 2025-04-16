package net.iceageempire.vampirearise.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.iceageempire.vampirearise.VampireArise;
import net.iceageempire.vampirearise.item.custom.*;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.minecraft.item.Items.register;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID,"ruby")))));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID,"raw_ruby")))));
    public static final Item DECAY_WAND = registerItem("decay_wand", new WandOfDecayItem(new Item.Settings().maxDamage(128)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID,"decay_wand")))));
    public static final Item RUBY_SWORD = registerItem("ruby_sword", new RubySwordItem(new Item.Settings().maxDamage(635)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID,"ruby_sword")))));
    public static final Item RUBY_POTATO = registerItem("ruby_potato", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID,"ruby_potato")))
            .rarity(Rarity.UNCOMMON)
            .food(ModFoodComponents.RUBY_POTATO, ModFoodComponents.RUBY_POTATO_EFFECT)));

    public static final Item RUBY_PICKAXE = registerItem("ruby_pickaxe",
            new Item(new Item.Settings().pickaxe(ModToolMaterials.RUBY, 1, -2.8f)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_pickaxe")))));
    public static final Item RUBY_SHOVEL = registerItem("ruby_shovel",
            new ShovelItem(ModToolMaterials.RUBY, 1.5f, -3.0f, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_shovel")))));
    public static final Item RUBY_AXE = registerItem("ruby_axe",
            new AxeItem(ModToolMaterials.RUBY, 6, -3.05f, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_axe")))));
    public static final Item RUBY_HOE = registerItem("ruby_hoe",
            new HoeItem(ModToolMaterials.RUBY, -2, -1f, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_hoe")))));
    public static final Item RUBY_HAMMER = registerItem("ruby_hammer",
            new RubyHammerItem(new Item.Settings().pickaxe(ModToolMaterials.RUBY, 9, -3.5f)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_hammer")))));
    public static final Item RUBY_HELMET = registerItem("ruby_helmet",
            new Item(new Item.Settings().armor(ModArmorMaterials.RUBY_ARMOR, EquipmentType.HELMET)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_helmet")))));
    public static final Item RUBY_CHESTPLATE = registerItem("ruby_chestplate",
            new Item(new Item.Settings().armor(ModArmorMaterials.RUBY_ARMOR, EquipmentType.CHESTPLATE)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_chestplate")))));
    public static final Item RUBY_LEGGINGS = registerItem("ruby_leggings",
            new Item(new Item.Settings().armor(ModArmorMaterials.RUBY_ARMOR, EquipmentType.LEGGINGS)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_leggings")))));
    public static final Item RUBY_BOOTS = registerItem("ruby_boots",
            new Item(new Item.Settings().armor(ModArmorMaterials.RUBY_ARMOR, EquipmentType.BOOTS)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, "ruby_boots")))));
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(VampireArise.MOD_ID, name), item);
    }

    public static void registerModItems() {
        VampireArise.LOGGER.info("Registering Mod Items for " + VampireArise.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RUBY);
            entries.add(RAW_RUBY);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(DECAY_WAND);
            entries.add(RUBY_AXE);
            entries.add(RUBY_HOE);
            entries.add(RUBY_PICKAXE);
            entries.add(RUBY_SHOVEL);
            entries.add(RUBY_HAMMER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(RUBY_POTATO);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(RUBY_SWORD);
        });

    }
}