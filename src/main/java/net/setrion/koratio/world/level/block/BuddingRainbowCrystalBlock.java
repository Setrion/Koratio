package net.setrion.koratio.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.setrion.koratio.registry.KoratioBlocks;

public class BuddingRainbowCrystalBlock extends AmethystBlock {
    public static final MapCodec<BuddingRainbowCrystalBlock> CODEC = simpleCodec(BuddingRainbowCrystalBlock::new);
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public MapCodec<BuddingRainbowCrystalBlock> codec() {
        return CODEC;
    }

    public BuddingRainbowCrystalBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.get();
            } else if (blockstate.is(KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD.get();
            } else if (blockstate.is(KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD.get();
            } else if (blockstate.is(KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = KoratioBlocks.RAINBOW_CRYSTAL_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState()
                        .setValue(AmethystClusterBlock.FACING, direction)
                        .setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
    }

    public static boolean canClusterGrowAtState(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }
}