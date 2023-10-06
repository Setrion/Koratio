package net.setrion.koratio.world.level.levelgen.layer;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;
import net.setrion.koratio.world.level.levelgen.vanilla.context.Context;
import net.setrion.koratio.world.level.levelgen.vanilla.traits.CastleTransformer;

public enum GenLayerDemonicioRiver implements CastleTransformer {

	INSTANCE;

	private HolderGetter<Biome> registry;

	GenLayerDemonicioRiver() { }

	public GenLayerDemonicioRiver setup(HolderGetter<Biome> registry) {
		this.registry = registry;
		return this;
	}
	
	@Override
	public int apply(Context iNoiseRandom, int up, int left, int down, int right, int mid) {
		if (shouldStream(mid, left) || shouldStream(mid, right) || shouldStream(mid, down) || shouldStream(mid, up)) {
			return FantasiaBiomeProvider.getBiomeId(KoratioBiomes.BLOOD_RIVER, registry);
		} else {
			return mid;
		}
	}
	
	boolean shouldStream(int biome1, int biome2) {
		if (biome1 != biome2) {
			return true;
		}
		return false;
	}
}
