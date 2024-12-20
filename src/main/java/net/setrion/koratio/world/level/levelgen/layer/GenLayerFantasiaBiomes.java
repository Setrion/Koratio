package net.setrion.koratio.world.level.levelgen.layer;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;
import net.setrion.koratio.world.level.levelgen.vanilla.context.Context;
import net.setrion.koratio.world.level.levelgen.vanilla.traits.AreaTransformer0;

import java.util.List;

public enum GenLayerFantasiaBiomes implements AreaTransformer0 {
	INSTANCE;
	private static final int RARE_BIOME_CHANCE = 15;
	private static final int VERY_RARE_BIOME_CHANCE = 45;

	private static final List<ResourceKey<Biome>> commonBiomes = ImmutableList.of(
			KoratioBiomes.FANTASIA_FIELDLANDS
	);
	private static final List<ResourceKey<Biome>> rareBiomes = ImmutableList.of(
			KoratioBiomes.MUSHROOM_FOREST,
			KoratioBiomes.CANDY_CANE_VALLEY,
			KoratioBiomes.CHOCOLATE_HILLS
	);
	private static final List<ResourceKey<Biome>> veryRareBiomes = ImmutableList.of(
			KoratioBiomes.GILDED_FOREST,
			KoratioBiomes.ELVEN_FOREST
	);

	private HolderGetter<Biome> registry;

	public GenLayerFantasiaBiomes setup(HolderGetter<Biome> registry) {
		this.registry = registry;
		return this;
	}

	GenLayerFantasiaBiomes() {
	}

	@Override
	public int applyPixel(Context context, int x, int z) {
		if (context.nextRandom(VERY_RARE_BIOME_CHANCE) == 0) {
			return getRandomBiome(context, veryRareBiomes);
		} else if (context.nextRandom(RARE_BIOME_CHANCE) == 0) {
			return getRandomBiome(context, rareBiomes);
		} else {
			return getRandomBiome(context, commonBiomes);
		}
	}

	private int getRandomBiome(Context random, List<ResourceKey<Biome>> biomes) {
		return FantasiaBiomeProvider.getBiomeId(biomes.get(random.nextRandom(biomes.size())), registry);
	}
}