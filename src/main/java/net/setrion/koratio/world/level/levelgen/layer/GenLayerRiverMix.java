package net.setrion.koratio.world.level.levelgen.layer;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;
import net.setrion.koratio.world.level.levelgen.vanilla.area.Area;
import net.setrion.koratio.world.level.levelgen.vanilla.context.Context;
import net.setrion.koratio.world.level.levelgen.vanilla.traits.AreaTransformer2;
import net.setrion.koratio.world.level.levelgen.vanilla.traits.DimensionOffset0Transformer;

public enum GenLayerRiverMix implements AreaTransformer2, DimensionOffset0Transformer {

	INSTANCE;

	private HolderGetter<Biome> registry;

	GenLayerRiverMix() { }

	public GenLayerRiverMix setup(HolderGetter<Biome> registry) {
		this.registry = registry;
		return this;
	}

	@Override
	public int applyPixel(Context iNoiseRandom, Area area1, Area area2, int val1, int val2) {
		int biomeInputs = area1.get(this.getParentX(val1), this.getParentY(val2));
		int riverInputs = area2.get(this.getParentX(val1), this.getParentY(val2));

		int river = FantasiaBiomeProvider.getBiomeId(KoratioBiomes.RIVER, registry);

		if (riverInputs == river) {
			return riverInputs;
		} else {
			return biomeInputs;
		}
	}
}