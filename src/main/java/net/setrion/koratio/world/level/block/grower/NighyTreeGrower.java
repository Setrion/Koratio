package net.setrion.koratio.world.level.block.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.setrion.koratio.registry.KoratioConfiguredFeatures;

public class NighyTreeGrower extends AbstractTreeGrower {
	
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
		return KoratioConfiguredFeatures.NIGHY;
	}
}