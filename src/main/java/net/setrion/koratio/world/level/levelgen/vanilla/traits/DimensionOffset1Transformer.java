package net.setrion.koratio.world.level.levelgen.vanilla.traits;

public interface DimensionOffset1Transformer extends DimensionTransformer {
	default int getParentX(int x) {
		return x - 1;
	}

	default int getParentY(int y) {
		return y - 1;
	}
}