package net.setrion.koratio.world.level.levelgen.vanilla.traits;

import net.setrion.koratio.world.level.levelgen.vanilla.LayerBiomes;

public interface DimensionTransformer extends LayerBiomes {
	int getParentX(int x);

	int getParentY(int y);
}