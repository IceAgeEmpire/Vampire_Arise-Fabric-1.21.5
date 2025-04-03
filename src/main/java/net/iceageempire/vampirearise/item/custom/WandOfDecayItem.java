package net.iceageempire.vampirearise.item.custom;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.AbstractMap;
import java.util.Map;
public class WandOfDecayItem extends Item {

    private static final Map<Block, Block> CHISEL_MAP =
            Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(Blocks.GRASS_BLOCK, Blocks.DIRT),
                    new AbstractMap.SimpleEntry<>(Blocks.DIRT, Blocks.COARSE_DIRT),
                    new AbstractMap.SimpleEntry<>(Blocks.OAK_SAPLING , Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.ACACIA_SAPLING , Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.BAMBOO_SAPLING, Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.BIRCH_SAPLING , Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.CHERRY_SAPLING, Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.JUNGLE_SAPLING, Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.DARK_OAK_SAPLING, Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.PALE_OAK_SAPLING, Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.SPRUCE_SAPLING, Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.CRIMSON_FUNGUS, Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.WARPED_FUNGUS, Blocks.DEAD_BUSH),
                    new AbstractMap.SimpleEntry<>(Blocks.MANGROVE_PROPAGULE, Blocks.DEAD_BUSH)
            );

    public WandOfDecayItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!world.isClient()) {
                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_ROOTED_DIRT_PLACE, SoundCategory.BLOCKS);
            }
        }

        return ActionResult.SUCCESS;
    }
}