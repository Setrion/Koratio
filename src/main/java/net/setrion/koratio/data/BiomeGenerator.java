package net.setrion.koratio.data;

import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.floats.Float2ObjectSortedMap;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.world.level.levelgen.carver.TerrainColumn;

import java.util.List;
import java.util.function.Consumer;

public final class BiomeGenerator extends BiomeHelper {

	public static List<TerrainColumn> makeFantasiaBiomeList(HolderGetter<Biome> biomeRegistry, Holder<Biome> undergroundBiome, Holder<Biome> skyBiome) {
		return List.of(
				biomeColumnWithUndergroundAndSkyBiome(9.025F, 0.05F, biomeRegistry, KoratioBiomes.FANTASIA_FIELDLANDS, undergroundBiome, skyBiome),
				biomeColumnWithUndergroundAndSkyBiome(9.125F, 0.05F, biomeRegistry, KoratioBiomes.GILDED_FOREST, undergroundBiome, skyBiome),
				biomeColumnWithUnderground(9.1F, 0.2F, biomeRegistry, KoratioBiomes.MUSHROOM_FOREST, undergroundBiome),
				biomeColumnWithUnderground(9F, 0.0125F, biomeRegistry, KoratioBiomes.ELVEN_FOREST, undergroundBiome),
				biomeColumnWithUndergroundAndSkyBiome(9.1F, 0.2F, biomeRegistry, KoratioBiomes.CANDY_CANE_VALLEY, undergroundBiome, skyBiome),
				biomeColumnWithUndergroundAndSkyBiome(12.31F, 0.2F, biomeRegistry, KoratioBiomes.CHOCOLATE_HILLS, undergroundBiome, skyBiome),
				biomeColumnWithUndergroundAndSkyBiome(7.35F, 0.35F, biomeRegistry, KoratioBiomes.RIVER, undergroundBiome, skyBiome)
		);
	}

	private static TerrainColumn biomeColumnWithUnderground(float noiseDepth, float noiseScale, HolderGetter<Biome> biomeRegistry, ResourceKey<Biome> key, Holder<Biome> undergroundBiome) {
		Holder.Reference<Biome> biomeHolder = biomeRegistry.getOrThrow(key);

		biomeHolder.bindKey(key);

		return makeColumn(noiseDepth, noiseScale, biomeHolder, treeMap -> {
			// This will put the transition boundary around Y-8
			treeMap.put(Math.min(noiseDepth - 1, -1), biomeHolder);
			treeMap.put(Math.min(noiseDepth - 3, -3), undergroundBiome);
		});
	}
	private static TerrainColumn biomeColumnWithUndergroundAndSkyBiome(float noiseDepth, float noiseScale, HolderGetter<Biome> biomeRegistry, ResourceKey<Biome> key, Holder<Biome> undergroundBiome, Holder<Biome> skyBiome) {
		Holder.Reference<Biome> biomeHolder = biomeRegistry.getOrThrow(key);

		biomeHolder.bindKey(key);

		return makeColumn(noiseDepth, noiseScale, biomeHolder, treeMap -> {
			treeMap.put(Math.min(noiseDepth - 1, -1), biomeHolder);
			treeMap.put(Math.min(noiseDepth - 3, -3), undergroundBiome);
			treeMap.put(Math.min(noiseDepth + 100, 100), skyBiome);
		});
	}

	@SuppressWarnings("unused")
	private static TerrainColumn biomeColumnToBedrock(float noiseDepth, float noiseScale, HolderGetter<Biome> biomeRegistry, ResourceKey<Biome> key) {
		Holder.Reference<Biome> biomeHolder = biomeRegistry.getOrThrow(key);

		biomeHolder.bindKey(key);

		return makeColumn(noiseDepth, noiseScale, biomeHolder, treeMap -> treeMap.put(0, biomeHolder));
	}

	private static TerrainColumn makeColumn(float noiseDepth, float noiseScale, Holder<Biome> biomeHolder, Consumer<Float2ObjectSortedMap<Holder<Biome>>> layerBuilder) {
		return new TerrainColumn(biomeHolder, Util.make(new Float2ObjectAVLTreeMap<>(), layerBuilder), noiseDepth, noiseScale);
	}
}