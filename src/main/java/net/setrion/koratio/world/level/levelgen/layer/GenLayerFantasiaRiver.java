package net.setrion.koratio.world.level.levelgen.layer;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;
import net.setrion.koratio.world.level.levelgen.vanilla.context.Context;
import net.setrion.koratio.world.level.levelgen.vanilla.traits.CastleTransformer;

public enum GenLayerFantasiaRiver implements CastleTransformer {

	INSTANCE;

	private HolderGetter<Biome> registry;

	GenLayerFantasiaRiver() { }

	public GenLayerFantasiaRiver setup(HolderGetter<Biome> registry) {
		this.registry = registry;
		return this;
	}
	
	@Override
	public int apply(Context iNoiseRandom, int up, int left, int down, int right, int mid) {
		if (shouldStream(mid, left) || shouldStream(mid, right) || shouldStream(mid, down) || shouldStream(mid, up)) {
			return FantasiaBiomeProvider.getBiomeId(KoratioBiomes.RIVER, registry);
		} else {
			return mid;
		}
	}

	//vanilla: desert=2.0, jungle=0.95, plains=0.8, snowy_plains=0.0
	
	boolean shouldStream(int biome1, int biome2) {
		Registry<Biome> registry = ServerLifecycleHooks.getCurrentServer().registryAccess().registryOrThrow(Registries.BIOME);
		float difference = registry.getHolder(biome1).get().value().getBaseTemperature() - registry.getHolder(biome2).get().value().getBaseTemperature();

		if (difference < -0.1 || difference > 0.1) {
			return true;
		}
		
		return false;
	}
}
