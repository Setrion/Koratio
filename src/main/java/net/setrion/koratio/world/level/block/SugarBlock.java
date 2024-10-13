package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class SugarBlock extends ConcretePowderBlock {

    private final ColorRGBA dustColor;
    private final Block concrete;

    public SugarBlock(ColorRGBA dustColor, Block concrete, Properties properties) {
        super(concrete, properties);
        this.dustColor = dustColor;
        this.concrete = concrete;
    }

    protected static boolean shouldHandlePrecipitation(Level level, Biome.Precipitation precipitation) {
        return (precipitation == Biome.Precipitation.RAIN && level.getRandom().nextFloat() < 0.05F);
    }

    public void handlePrecipitation(BlockState state, Level level, BlockPos pos, Biome.Precipitation precipitation) {
        if (shouldHandlePrecipitation(level, precipitation)) {
            level.setBlockAndUpdate(pos, concrete.defaultBlockState());
            level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
        }
    }

    @Override
    public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
        return dustColor.rgba();
    }
}