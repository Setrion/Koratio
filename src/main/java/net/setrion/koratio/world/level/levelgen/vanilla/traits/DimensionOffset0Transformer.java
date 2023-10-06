package net.setrion.koratio.world.level.levelgen.vanilla.traits;

public interface DimensionOffset0Transformer extends DimensionTransformer {
	default int getParentX(int x) {
		return x;
	}

	default int getParentY(int y) {
		return y;
	}
}