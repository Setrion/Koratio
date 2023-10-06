package net.setrion.koratio.world.level.levelgen.vanilla;

import java.util.Optional;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.world.level.levelgen.vanilla.area.AreaFactory;
import net.setrion.koratio.world.level.levelgen.vanilla.area.LazyArea;

public class Layer {
	public final LazyArea area;

	public Layer(AreaFactory<LazyArea> area) {
		this.area = area.make();
	}

	public Holder<Biome> get(HolderGetter<Biome> registry, int x, int z) {
		int i = this.area.get(x, z);
		Optional<Holder.Reference<Biome>> biome = ServerLifecycleHooks.getCurrentServer().registryAccess().registryOrThrow(Registries.BIOME).getHolder(i);
		if (biome.isEmpty()) {
			Util.logAndPauseIfInIde("Unknown biome id: " + i);
			return registry.getOrThrow(KoratioBiomes.FANTASIA_FIELDLANDS);
		} else {
			return biome.get();
		}

	}
}