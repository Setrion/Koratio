package net.setrion.koratio.world.level.levelgen.vanilla.traits;

import net.setrion.koratio.world.level.levelgen.vanilla.area.Area;
import net.setrion.koratio.world.level.levelgen.vanilla.area.AreaFactory;
import net.setrion.koratio.world.level.levelgen.vanilla.context.BigContext;

public interface AreaTransformer1  extends DimensionTransformer {
	
	default <R extends Area> AreaFactory<R> run(BigContext<R> context, AreaFactory<R> factory) {
		return () -> {
			R r = factory.make();
			return context.createResult((x, y) -> {
				context.initRandom(x, y);
				return this.applyPixel(context, r, x, y);
			}, r);
		};
	}

	int applyPixel(BigContext<?> context, Area area, int x, int y);
}