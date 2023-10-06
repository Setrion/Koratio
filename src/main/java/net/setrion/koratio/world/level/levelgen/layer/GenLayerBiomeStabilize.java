package net.setrion.koratio.world.level.levelgen.layer;

import net.setrion.koratio.world.level.levelgen.vanilla.area.Area;
import net.setrion.koratio.world.level.levelgen.vanilla.context.BigContext;
import net.setrion.koratio.world.level.levelgen.vanilla.traits.AreaTransformer1;

public enum GenLayerBiomeStabilize implements AreaTransformer1 {

	INSTANCE;

	GenLayerBiomeStabilize() { }

	@Override
	public int getParentX(int x) {
		return x & 3;
	}

	@Override
	public int getParentY(int y) {
		return y & 3;
	}

	@Override
	public int applyPixel(BigContext<?> iExtendedNoiseRandom, Area iArea, int x, int y) {
		int offX = getParentX(x << 4);
		int offZ = getParentY(y << 4);
		int centerX = ((x + offX + 1) & -4) - offX;
		int centerZ = ((y + offZ + 1) & -4) - offZ;
		if (x <= centerX + 1 && x >= centerX - 1 && y <= centerZ + 1 && y >= centerZ - 1) {
			return iArea.get(centerX, centerZ);
		} else {
			return iArea.get(x, y);
		}
	}
}