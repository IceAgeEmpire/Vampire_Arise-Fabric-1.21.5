package net.iceageempire.vampirearise.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.iceageempire.vampirearise.VampireArise;
import net.iceageempire.vampirearise.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup RUBY_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(VampireArise.MOD_ID, "ruby_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.RUBY))
                    .displayName(Text.translatable("itemgroup.vampirearise.ruby_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModItems.DECAY_WAND);
                        entries.add(ModItems.RUBY_SWORD);
                        entries.add(ModItems.RUBY_POTATO);
                        entries.add(ModItems.RUBY_AXE);
                        entries.add(ModItems.RUBY_HOE);
                        entries.add(ModItems.RUBY_PICKAXE);
                        entries.add(ModItems.RUBY_SHOVEL);
                        entries.add(ModItems.RUBY_HAMMER);
                        entries.add(ModItems.RUBY_HELMET);
                        entries.add(ModItems.RUBY_CHESTPLATE);
                        entries.add(ModItems.RUBY_LEGGINGS);
                        entries.add(ModItems.RUBY_BOOTS);
                    }).build());

    public static final ItemGroup RUBY_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(VampireArise.MOD_ID, "ruby_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.RUBY_BLOCK))
                    .displayName(Text.translatable("itemgroup.vampirearise.ruby_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.RUBY_BLOCK);
                        entries.add(ModBlocks.RAW_RUBY_BLOCK);
                        entries.add(ModBlocks.RUBY_ORE);
                        entries.add(ModBlocks.DEEPSLATE_RUBY_ORE);
                        entries.add(ModBlocks.NETHER_RUBY_ORE);
                        entries.add(ModBlocks.TURRET_BLOCK);
                        entries.add(ModBlocks.WACTHER_BLOCK);
                    }).build());


    public static void registerItemGroups() {
        VampireArise.LOGGER.info("Registering Item Groups for " + VampireArise.MOD_ID);
    }
}