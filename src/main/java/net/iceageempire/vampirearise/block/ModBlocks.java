package net.iceageempire.vampirearise.block;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.iceageempire.vampirearise.VampireArise;
import net.iceageempire.vampirearise.block.custom.TurretBlock;
import net.iceageempire.vampirearise.block.custom.WatcherBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK));

    public static final Block RAW_RUBY_BLOCK = registerBlock("raw_ruby_block",
            AbstractBlock.Settings.create().strength(3f)
                    .requiresTool());
    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            ExperienceDroppingBlock.Settings.create().strength(3f).sounds(BlockSoundGroup.STONE)
                    .requiresTool());
    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            ExperienceDroppingBlock.Settings.create().strength(4.5f).sounds(BlockSoundGroup.DEEPSLATE)
                    .requiresTool());
    public static final Block NETHER_RUBY_ORE = registerBlock("nether_ruby_ore",
            ExperienceDroppingBlock.Settings.create().strength(3f).sounds(BlockSoundGroup.NETHER_ORE)
                    .requiresTool());

//    public static final Block RUBY_ORE = registerXPBlock("ruby_ore", () ->
//            new ExperienceDroppingBlock(
//                    UniformIntProvider.create(3, 5),
//                    ExperienceDroppingBlock.Settings.create().strength(3f).requiresTool()
//            )
//    );
//    public static final Block DEEPSLATE_RUBY_ORE = registerXPBlock("deepslate_ruby_ore", () ->
//            new ExperienceDroppingBlock(
//                    UniformIntProvider.create(4, 6),
//                    ExperienceDroppingBlock.Settings.create().strength(4.5f).requiresTool().
//                            sounds(BlockSoundGroup.DEEPSLATE)
//            )
//    );
//
//    public static final Block NETHER_RUBY_ORE = registerXPBlock("nether_ruby_ore", () ->
//            new ExperienceDroppingBlock(
//                    UniformIntProvider.create(3, 6),
//                    ExperienceDroppingBlock.Settings.create().strength(3f).requiresTool().
//                            sounds(BlockSoundGroup.NETHER_ORE)
//            )
//    );

    public static final Block TURRET_BLOCK = registerBlock("turret_block",
            TurretBlock.Settings.create().strength(1f));

    public static final Block WACTHER_BLOCK = registerBlockV2("watcher_block",
            WatcherBlock::new,Block.Settings.create().strength(1f).luminance(state-> state.get(WatcherBlock.CLICKED) ? 15 : 0));


    private static Block registerBlockV2(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of("vampirearise", path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }
    private static Block registerBlock(String name, AbstractBlock.Settings blockSettings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(VampireArise.MOD_ID, name));
        Block block = new Block(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }
    private static Block registerXPBlock(String name, Supplier<Block> blockSupplier) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(VampireArise.MOD_ID, name));
        Block block = blockSupplier.get();
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }


    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModBlocks() {
        VampireArise.LOGGER.info("Registering Mod Blocks for " + VampireArise.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.RUBY_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.RAW_RUBY_BLOCK);
            entries.add(ModBlocks.RUBY_ORE);
            entries.add(ModBlocks.DEEPSLATE_RUBY_ORE);
            entries.add(ModBlocks.NETHER_RUBY_ORE);
        });
    }
}