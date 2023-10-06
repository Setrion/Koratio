package net.setrion.koratio.world.level.levelgen.vanilla;

import net.setrion.koratio.world.level.levelgen.vanilla.area.Area;
import net.setrion.koratio.world.level.levelgen.vanilla.context.BigContext;
import net.setrion.koratio.world.level.levelgen.vanilla.traits.AreaTransformer1;

public enum ZoomLayer implements AreaTransformer1 {
	NORMAL,
	FUZZY {
		protected int modeOrRandom(BigContext<?> context, int p_76980_, int p_76981_, int p_76982_, int p_76983_) {
			return context.random(p_76980_, p_76981_, p_76982_, p_76983_);
		}
	};

	public int getParentX(int x) {
		return x >> 1;
	}

	public int getParentY(int y) {
		return y >> 1;
	}

	public int applyPixel(BigContext<?> context, Area area, int x, int y) {
		int i = area.get(this.getParentX(x), this.getParentY(y));
		context.initRandom(x >> 1 << 1, y >> 1 << 1);
		int j = x & 1;
		int k = y & 1;
		if (j == 0 && k == 0) {
			return i;
		} else {
			int l = area.get(this.getParentX(x), this.getParentY(y + 1));
			int i1 = context.random(i, l);
			if (j == 0 && k == 1) {
				return i1;
			} else {
				int j1 = area.get(this.getParentX(x + 1), this.getParentY(y));
				int k1 = context.random(i, j1);
				if (j == 1 && k == 0) {
					return k1;
				} else {
					int l1 = area.get(this.getParentX(x + 1), this.getParentY(y + 1));
					return this.modeOrRandom(context, i, j1, l, l1);
				}
			}
		}
	}

	protected int modeOrRandom(BigContext<?> p_76960_, int p_76961_, int p_76962_, int p_76963_, int p_76964_) {
		if (p_76962_ == p_76963_ && p_76963_ == p_76964_) {
			return p_76962_;
		} else if (p_76961_ == p_76962_ && p_76961_ == p_76963_) {
			return p_76961_;
		} else if (p_76961_ == p_76962_ && p_76961_ == p_76964_) {
			return p_76961_;
		} else if (p_76961_ == p_76963_ && p_76961_ == p_76964_) {
			return p_76961_;
		} else if (p_76961_ == p_76962_ && p_76963_ != p_76964_) {
			return p_76961_;
		} else if (p_76961_ == p_76963_ && p_76962_ != p_76964_) {
			return p_76961_;
		} else if (p_76961_ == p_76964_ && p_76962_ != p_76963_) {
			return p_76961_;
		} else if (p_76962_ == p_76963_ && p_76961_ != p_76964_) {
			return p_76962_;
		} else if (p_76962_ == p_76964_ && p_76961_ != p_76963_) {
			return p_76962_;
		} else {
			return p_76963_ == p_76964_ && p_76961_ != p_76962_ ? p_76963_ : p_76960_.random(p_76961_, p_76962_, p_76963_, p_76964_);
		}
	}
}