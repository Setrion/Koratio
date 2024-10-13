package net.setrion.koratio.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.setrion.koratio.Koratio;

import static net.setrion.koratio.data.BiomeHelper.*;

public class KoratioBiomes {
	
	//Fantasia Biomes
	public static final ResourceKey<Biome> FANTASIA_FIELDLANDS = makeKey("fantasia_fieldlands");
	
	public static final ResourceKey<Biome> GILDED_FOREST = makeKey("gilded_forest");
	public static final ResourceKey<Biome> ELVEN_FOREST = makeKey("elven_forest");
	public static final ResourceKey<Biome> MUSHROOM_FOREST = makeKey("mushroom_forest");
	
	public static final ResourceKey<Biome> CANDY_CANE_VALLEY = makeKey("candy_cane_valley");
	
	public static final ResourceKey<Biome> RIVER = makeKey("river");
	
	public static final ResourceKey<Biome> DEPTHS_OF_FANTASIA = makeKey("depths_of_fantasia");
	public static final ResourceKey<Biome> SKYLANDS = makeKey("skylands");
	
	private static ResourceKey<Biome> makeKey(String name) {
		return ResourceKey.create(Registries.BIOME, Koratio.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Biome> context) {
		HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
		//Fantasia
		context.register(FANTASIA_FIELDLANDS, biomeWithDefaults(defaultFantasiaAmbientBuilder().foliageColorOverride(14768987).grassColorOverride(5348240), fantasiaFieldlandsSpawning(), fantasiaFieldlandsGen(featureGetter, carverGetter)).temperature(0.8F).build());
		context.register(GILDED_FOREST, biomeWithDefaults(defaultFantasiaAmbientBuilder().ambientParticle(new AmbientParticleSettings(KoratioParticles.GOLD_GLINT.get(), 0.00125F)).foliageColorOverride(16769359).grassColorOverride(7709636), defaultFantasiaMobSpawning(), gildedForestGen(featureGetter, carverGetter)).temperature(0.8F).build());
		context.register(MUSHROOM_FOREST, biomeWithDefaults(defaultFantasiaAmbientBuilder().ambientParticle(new AmbientParticleSettings(ParticleTypes.WITCH, 0.025F)).fogColor(16735130).foliageColorOverride(65535).grassColorOverride(16735130), mushroomForestSpawning(), mushroomForestGen(featureGetter, carverGetter)).build());
		context.register(ELVEN_FOREST, biomeWithDefaults(defaultFantasiaAmbientBuilder().ambientParticle(new AmbientParticleSettings(ParticleTypes.MYCELIUM, 0.025F)).foliageColorOverride(16773070).grassColorOverride(16631551), defaultFantasiaMobSpawning(), elvenForestGen(featureGetter, carverGetter)).build());
		context.register(CANDY_CANE_VALLEY, biomeWithDefaults(defaultFantasiaAmbientBuilder().foliageColorOverride(1687910).grassColorOverride(4176127), defaultFantasiaMobSpawning(), candycanevalleyGen(featureGetter, carverGetter)).temperature(0.7F).build());

		context.register(RIVER, biomeWithDefaults(defaultFantasiaAmbientBuilder(), defaultFantasiaMobSpawning(), rivers(featureGetter, carverGetter, false)).temperature(0.5F).downfall(0.1F).build());
		
		context.register(DEPTHS_OF_FANTASIA, biomeWithDefaults(defaultFantasiaAmbientBuilder(), fantasiaDepthsMobSpawning(), depthGen(featureGetter, carverGetter)).temperature(0.7F).downfall(0.0F).build());
		context.register(SKYLANDS, biomeWithDefaults(defaultFantasiaAmbientBuilder(), skylandsMobSpawning(), skylandsGen(featureGetter, carverGetter)).temperature(0.7F).downfall(0.0F).build());
	
	}
}