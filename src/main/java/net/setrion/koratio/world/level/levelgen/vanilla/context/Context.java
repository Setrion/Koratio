package net.setrion.koratio.world.level.levelgen.vanilla.context;

import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

public interface Context {
	int nextRandom(int i);

	ImprovedNoise getBiomeNoise();
}