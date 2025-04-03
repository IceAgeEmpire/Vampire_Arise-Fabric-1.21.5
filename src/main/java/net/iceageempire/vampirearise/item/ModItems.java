package net.iceageempire.vampirearise.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.iceageempire.vampirearise.VampireArise;
import net.iceageempire.vampirearise.item.custom.WandOfDecayItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID,"ruby")))));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID,"raw_ruby")))));
    public static final Item WandOfDecay = registerItem("decay_wand", new WandOfDecayItem(new Item.Settings().maxDamage(128)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID,"decay_wand")))));



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
            entries.add(WandOfDecay);
        });

    }
}