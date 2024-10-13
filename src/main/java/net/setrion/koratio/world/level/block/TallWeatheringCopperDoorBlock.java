package net.setrion.koratio.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.setrion.koratio.world.level.block.state.properties.TripleBlockPart;

public class TallWeatheringCopperDoorBlock extends TallDoorBlock implements WeatheringCopper {

    public static final MapCodec<TallWeatheringCopperDoorBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            BlockSetType.CODEC.fieldOf("block_set_type").forGetter(TallDoorBlock::type),
                            WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(TallWeatheringCopperDoorBlock::getAge),
                            propertiesCodec()
                    )
                    .apply(instance, TallWeatheringCopperDoorBlock::new)
    );
    private final WeatheringCopper.WeatherState weatherState;

    @Override
    public MapCodec<TallWeatheringCopperDoorBlock> codec() {
        return CODEC;
    }

    public TallWeatheringCopperDoorBlock(BlockSetType setType, WeatheringCopper.WeatherState state, BlockBehaviour.Properties properties) {
        super(setType, properties);
        this.weatherState = state;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(TallDoorBlock.PART) == TripleBlockPart.LOWER) {
            changeOverTime(state, level, pos, random);
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public WeatheringCopper.WeatherState getAge() {
        return weatherState;
    }
}