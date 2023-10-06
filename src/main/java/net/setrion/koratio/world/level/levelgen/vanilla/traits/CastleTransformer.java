package net.setrion.koratio.world.level.levelgen.vanilla.traits;

import net.setrion.koratio.world.level.levelgen.vanilla.area.Area;
import net.setrion.koratio.world.level.levelgen.vanilla.context.BigContext;
import net.setrion.koratio.world.level.levelgen.vanilla.context.Context;

public interface CastleTransformer extends AreaTransformer1, DimensionOffset1Transformer {
	int apply(Context context, int up, int left, int down, int right, int mid);

	default int applyPixel(BigContext<?> context, Area area, int x, int y) {
		return this.apply(context, area.get(this.getParentX(x + 1), this.getParentY(y)), area.get(this.getParentX(x + 2), this.getParentY(y + 1)), area.get(this.getParentX(x + 1), this.getParentY(y + 2)), area.get(this.getParentX(x), this.getParentY(y + 1)), area.get(this.getParentX(x + 1), this.getParentY(y + 1)));
	}
}