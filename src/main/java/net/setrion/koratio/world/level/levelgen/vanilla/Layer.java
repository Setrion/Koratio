package net.setrion.koratio.world.level.levelgen.vanilla;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.world.level.levelgen.vanilla.area.AreaFactory;
import net.setrion.koratio.world.level.levelgen.vanilla.area.LazyArea;

import java.util.Optional;

public class Layer {
	public final LazyArea area;

	public Layer(AreaFactory<LazyArea> area) {
		this.area = area.make();
	}

	public Holder<Biome> get(HolderGetter<Biome> registry, int x, int z) {
		int i = this.area.get(x, z);
		Optional<Holder.Reference<Biome>> biome = ServerLifecycleHooks.getCurrentServer().registryAccess().lookupOrThrow(Registries.BIOME).get(i);
		if (biome.isEmpty()) {
			Util.logAndPauseIfInIde("Unknown biome id: " + i);
			return registry.getOrThrow(KoratioBiomes.FANTASIA_FIELDLANDS);
		} else {
			return biome.get();
		}

	}
}