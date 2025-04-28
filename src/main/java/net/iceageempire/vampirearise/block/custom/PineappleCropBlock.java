package net.iceageempire.vampirearise.block.custom;

import net.iceageempire.vampirearise.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class PineappleCropBlock extends CropBlock {
    public static final int MAX_AGE = 6;
    public static final IntProperty AGE = IntProperty.of("age",0,6);

    public PineappleCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Check light level before growing
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int age = this.getAge(state);
            if (age < this.getMaxAge()) {
                float moisture = getAvailableMoisture(this, world, pos);

                // Adjust the growth chance to make it slower
                // Vanilla has 1/26 chance, so 1/52 makes it grow twice as slow
                if (random.nextInt((int)(40.0F / moisture) + 1) == 0) {
                    world.setBlockState(pos, this.withAge(age + 1), Block.NOTIFY_LISTENERS);
                }
            }
        }
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.PINEAPPLE_SEEDS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
