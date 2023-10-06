package net.setrion.koratio.data;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.setrion.koratio.registry.KoratioCarvers;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioPlacedFeatures;

public abstract class BiomeHelper {
	
	//Fantasia

	public static BiomeGenerationSettings.Builder fantasiaFieldlandsGen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = defaultFantasiaGenSettingBuilder(featureGetter, carverGetter);

		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_PATCH_FANTASIA_GRASS);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.TREES_FANTASIA_FIELDLANDS);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_RAINBOW_FLOWERS);
		
		return biome;
	}
	
	public static BiomeGenerationSettings.Builder amethystFieldsGen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = defaultFantasiaGenSettingBuilder(featureGetter, carverGetter);

		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_PATCH_AMETHYST_GRASS);
		biome.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, KoratioPlacedFeatures.PLACED_AMETHYST_SPIKE);
		biome.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, KoratioPlacedFeatures.PLACED_AMETHYST_GEODE);
		biome.addCarver(GenerationStep.Carving.AIR, KoratioCarvers.AMETHYST_SPIDER_CAVES_CONFIGURED);
		
		return biome;
	}
	
	public static BiomeGenerationSettings.Builder candycanevalleyGen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = defaultFantasiaGenSettingBuilder(featureGetter, carverGetter);

		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_PATCH_COTTON_CANDY_GRASS);
		biome.addFeature(GenerationStep.Decoration.LAKES, KoratioPlacedFeatures.PLACED_CHOCOLATE_MILK_LAKE);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_CANDY_FLOWERS);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_HUGE_CANDY_CANE);
		
		return biome;
	}
	
	public static BiomeGenerationSettings.Builder gildedForestGen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = defaultFantasiaGenSettingBuilder(featureGetter, carverGetter);
		
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_PATCH_FANTASIA_GRASS);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_VARESO);
		
		return biome;
	}

	public static BiomeGenerationSettings.Builder mushroomForestGen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = defaultFantasiaGenSettingBuilder(featureGetter, carverGetter);
		
		addFantasiaCrystalCaves(biome);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_PATCH_FANTASIA_GRASS);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_PURPLE_MUSHROOM);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_GREEN_MUSHROOM);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PURPLE_MUSHROOM_NORMAL);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.GREEN_MUSHROOM_NORMAL);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.RED_MUSHROOM_NORMAL);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.BROWN_MUSHROOM_NORMAL);
		
		return biome;
	}

	public static BiomeGenerationSettings.Builder rivers(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean isLake) {
		BiomeGenerationSettings.Builder biome = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);

		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, isLake ? AquaticPlacements.SEAGRASS_DEEP : AquaticPlacements.SEAGRASS_NORMAL);

		BiomeDefaultFeatures.addDefaultSeagrass(biome);

		return biome;
	}

	public static BiomeGenerationSettings.Builder depthGen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);

		BiomeDefaultFeatures.addDefaultSoftDisks(biome);
		addDefaultFantasiaOres(biome);
		addFantasiaCaves(biome);
		return biome;
	}
	
	public static BiomeGenerationSettings.Builder skylandsGen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
		
		biome.addFeature(GenerationStep.Decoration.RAW_GENERATION, KoratioPlacedFeatures.END_ISLAND_DECORATED);

		return biome;
	}
	
	//Demonicio
	
	public static BiomeGenerationSettings.Builder nightmareForestGen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = defaultDemonicioGenSettingBuilder(featureGetter, carverGetter);
		
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, KoratioPlacedFeatures.PLACED_NIGHY);
		
		return biome;
	}

	public static BiomeSpecialEffects.Builder whiteAshParticles(BiomeSpecialEffects.Builder builder) {
		builder.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.05f));
		return builder;
	}

	//Caves!
	public static void addFantasiaCaves(BiomeGenerationSettings.Builder biome) {
		biome.addCarver(GenerationStep.Carving.AIR, KoratioCarvers.FANTASIA_CAVES_CONFIGURED);
	}
	
	//Caves!
	public static void addDemonicioCaves(BiomeGenerationSettings.Builder biome) {
		biome.addCarver(GenerationStep.Carving.AIR, KoratioCarvers.DEMONICIO_CAVES_CONFIGURED);
	}
	
	public static void addFantasiaCrystalCaves(BiomeGenerationSettings.Builder biome) {
		biome.addCarver(GenerationStep.Carving.AIR, KoratioCarvers.FANTASIA_CRYSTAL_CAVES_CONFIGURED);
	}

	// Defaults
	public static BiomeSpecialEffects.Builder defaultFantasiaAmbientBuilder() {
		return new BiomeSpecialEffects.Builder()
				.fogColor(0xC0FFD8)
				.waterColor(0x3F76E4)
				.waterFogColor(0x050533)
				.grassColorOverride(5348240)
				.foliageColorOverride(14768987)
				.skyColor(0x20224A)
				.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
				.backgroundMusic(Musics.GAME);

	}
	
	public static BiomeSpecialEffects.Builder defaultDemonicioAmbientBuilder() {
		return new BiomeSpecialEffects.Builder()
				.fogColor(0xC0FFD8)
				.waterColor(0x3F76E4)
				.waterFogColor(0x050533)
				.grassColorOverride(5348240)
				.foliageColorOverride(14768987)
				.skyColor(0x20224A)
				.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
				.backgroundMusic(Musics.GAME);

	}

	public static BiomeGenerationSettings.Builder defaultFantasiaGenSettingBuilder(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
		
		BiomeDefaultFeatures.addDefaultSoftDisks(biome);
		addDefaultFantasiaOres(biome);
		
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
		addFantasiaCaves(biome);
		return biome;
	}
	
	public static BiomeGenerationSettings.Builder defaultDemonicioGenSettingBuilder(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
		
		addDemonicioCaves(biome);
		return biome;
	}
	
	public static void addDefaultFantasiaOres(BiomeGenerationSettings.Builder biome) {
		biome.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, KoratioPlacedFeatures.ORE_ARSOY_UPPER);
		biome.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, KoratioPlacedFeatures.ORE_ARSOY_MIDDLE);
		biome.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, KoratioPlacedFeatures.ORE_ARSOY_SMALL);
	}
	
	//MobSpawning - Fantasia

	public static MobSpawnSettings.Builder defaultFantasiaMobSpawning() {
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();

		return spawnInfo;
	}
	
	public static MobSpawnSettings.Builder fantasiaFieldlandsSpawning() {
		MobSpawnSettings.Builder spawnInfo = defaultFantasiaMobSpawning();
		
		spawnInfo.addSpawn(MobCategory.CREATURE, new SpawnerData(KoratioEntityType.SPIKY_PIG.get(), 10, 4, 4));
		spawnInfo.addSpawn(MobCategory.CREATURE, new SpawnerData(KoratioEntityType.UNICORN_CAT.get(), 1, 1, 1));
		
		return spawnInfo;
	}
	
	public static MobSpawnSettings.Builder amethystFieldsSpawning() {
		MobSpawnSettings.Builder spawnInfo = defaultFantasiaMobSpawning();
		
		spawnInfo.addSpawn(MobCategory.MONSTER, new SpawnerData(KoratioEntityType.AMETHYST_SPIDER.get(), 10, 1, 4));
		
		return spawnInfo;
	}
	
	public static MobSpawnSettings.Builder mushroomForestSpawning() {
		MobSpawnSettings.Builder spawnInfo = defaultFantasiaMobSpawning();
		
		spawnInfo.addSpawn(MobCategory.MONSTER, new SpawnerData(KoratioEntityType.JUMSTEM.get(), 100, 4, 4));
		
		return spawnInfo;
	}

	public static MobSpawnSettings.Builder fantasiaDepthsMobSpawning() {
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();

		spawnInfo.addSpawn(MobCategory.MONSTER, new SpawnerData(EntityType.SPIDER, 10, 2, 3));
		spawnInfo.addSpawn(MobCategory.MONSTER, new SpawnerData(EntityType.ZOMBIE, 10, 1, 2));
		spawnInfo.addSpawn(MobCategory.MONSTER, new SpawnerData(EntityType.SKELETON, 10, 1, 1));
		spawnInfo.addSpawn(MobCategory.MONSTER, new SpawnerData(EntityType.CREEPER, 1, 1, 1));
		spawnInfo.addSpawn(MobCategory.MONSTER, new SpawnerData(EntityType.SLIME, 10, 2, 4));
		spawnInfo.addSpawn(MobCategory.MONSTER, new SpawnerData(EntityType.ENDERMAN, 1, 1, 2));
		spawnInfo.addSpawn(MobCategory.AMBIENT, new SpawnerData(EntityType.BAT, 10, 1, 1));

		return spawnInfo;
	}
	
	public static MobSpawnSettings.Builder skylandsMobSpawning() {
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();

		spawnInfo.creatureGenerationProbability(0.1f);

		spawnInfo.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.PHANTOM, 10, 2, 3));
		spawnInfo.addSpawn(MobCategory.MISC, new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 10, 1, 2));

		return spawnInfo;
	}

	//MobSpawning - Demonicio
	
	public static MobSpawnSettings.Builder defaultDemonicioMobSpawning() {
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();

		return spawnInfo;
	}
	
	public static Biome.BiomeBuilder biomeWithDefaults(BiomeSpecialEffects.Builder biomeAmbience, MobSpawnSettings.Builder mobSpawnInfo, BiomeGenerationSettings.Builder biomeGenerationSettings) {
		return new Biome.BiomeBuilder()
				.hasPrecipitation(true)
				.temperature(0.5F)
				.downfall(0.5F)
				.specialEffects(biomeAmbience.build())
				.mobSpawnSettings(mobSpawnInfo.build())
				.generationSettings(biomeGenerationSettings.build())
				.temperatureAdjustment(Biome.TemperatureModifier.NONE);
	}
}