package net.setrion.koratio.world.level.block;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TorchBlock extends net.minecraft.world.level.block.TorchBlock {
	
	Supplier<? extends ParticleOptions> flameParticle;

	public TorchBlock(Properties properties, Supplier<? extends ParticleOptions> particle) {
		super(properties, null);
		this.flameParticle = particle;
	}
	
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		level.addParticle(this.flameParticle.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}