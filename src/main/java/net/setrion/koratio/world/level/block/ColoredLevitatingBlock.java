package net.setrion.koratio.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ColoredLevitatingBlock extends LevitatingBlock {

    public static final MapCodec<ColoredLevitatingBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(ColorRGBA.CODEC.fieldOf("levitating_dust_color").forGetter(block -> block.dustColor), propertiesCodec())
                    .apply(instance, ColoredLevitatingBlock::new)
    );
    private final ColorRGBA dustColor;

    @Override
    public MapCodec<ColoredLevitatingBlock> codec() {
        return CODEC;
    }

    public ColoredLevitatingBlock(ColorRGBA dustColor, BlockBehaviour.Properties properties) {
        super(properties);
        this.dustColor = dustColor;
    }

    @Override
    public int getDustColor(BlockState state, BlockGetter level, BlockPos pos) {
        return dustColor.rgba();
    }
}