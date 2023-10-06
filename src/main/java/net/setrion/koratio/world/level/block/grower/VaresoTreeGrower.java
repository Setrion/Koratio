package net.setrion.koratio.world.level.block.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.setrion.koratio.registry.KoratioConfiguredFeatures;

public class VaresoTreeGrower extends AbstractMegaTreeGrower {
	
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
		return null;
	}

	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
		return KoratioConfiguredFeatures.VARESO;
	}
}