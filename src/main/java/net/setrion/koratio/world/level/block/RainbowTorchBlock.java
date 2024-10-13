package net.setrion.koratio.world.level.block;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.setrion.koratio.registry.KoratioParticles;

public class RainbowTorchBlock extends BaseTorchBlock {
    public static final MapCodec<RainbowTorchBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(propertiesCodec())
                    .apply(instance, RainbowTorchBlock::new)
    );

    @Override
    public MapCodec<? extends RainbowTorchBlock> codec() {
        return CODEC;
    }

    public RainbowTorchBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double d0 = (double)pPos.getX() + 0.5;
        double d1 = (double)pPos.getY() + 0.7;
        double d2 = (double)pPos.getZ() + 0.5;
        pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
        pLevel.addParticle(KoratioParticles.RAINBOW_FIRE_FLAME.get(), d0, d1, d2, 0.0, 0.0, 0.0);
    }
}