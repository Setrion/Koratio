package net.setrion.koratio.registry;

import static net.setrion.koratio.data.BiomeHelper.*;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.setrion.koratio.Koratio;

public class KoratioBiomes {
	
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Koratio.MOD_ID);
	
	//Fantasia Biomes
	public static final ResourceKey<Biome> FANTASIA_FIELDLANDS = makeKey("fantasia_fieldlands");
	public static final ResourceKey<Biome> AMETHYST_FIELDS = makeKey("amethyst_fields");
	
	public static final ResourceKey<Biome> GILDED_FOREST = makeKey("gilded_forest");
	public static final ResourceKey<Biome> MUSHROOM_FOREST = makeKey("mushroom_forest");
	
	public static final ResourceKey<Biome> CANDY_CANE_VALLEY = makeKey("candy_cane_valley");
	
	public static final ResourceKey<Biome> RIVER = makeKey("river");
	
	public static final ResourceKey<Biome> DEPTHS_OF_FANTASIA = makeKey("depths_of_fantasia");
	public static final ResourceKey<Biome> SKYLANDS = makeKey("skylands");
	
	//Demonicio Biomes
	public static final ResourceKey<Biome> SKULL_DESERT = makeKey("skull_desert");
	
	public static final ResourceKey<Biome> NIGHTMARE_FOREST = makeKey("nightmare_forest");
	
	public static final ResourceKey<Biome> BLOOD_RIVER = makeKey("blood_river");
	
	private static ResourceKey<Biome> makeKey(String name) {
		return ResourceKey.create(Registries.BIOME, Koratio.prefix(name));
	}

	public static void bootstrap(BootstapContext<Biome> context) {
		HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
		//Fantasia
		context.register(FANTASIA_FIELDLANDS, biomeWithDefaults(defaultFantasiaAmbientBuilder().foliageColorOverride(14768987).grassColorOverride(5348240), fantasiaFieldlandsSpawning(), fantasiaFieldlandsGen(featureGetter, carverGetter)).temperature(0.8F).build());
		context.register(AMETHYST_FIELDS, biomeWithDefaults(defaultFantasiaAmbientBuilder().foliageColorOverride(14768987).grassColorOverride(8018869), amethystFieldsSpawning(), amethystFieldsGen(featureGetter, carverGetter)).temperature(0.8F).build());
		context.register(GILDED_FOREST, biomeWithDefaults(defaultFantasiaAmbientBuilder().foliageColorOverride(16769359).grassColorOverride(12632256), defaultFantasiaMobSpawning(), gildedForestGen(featureGetter, carverGetter)).temperature(0.8F).build());
		context.register(MUSHROOM_FOREST, biomeWithDefaults(defaultFantasiaAmbientBuilder().ambientParticle(new AmbientParticleSettings(ParticleTypes.WITCH, 0.025F)).fogColor(16735130).foliageColorOverride(65535).grassColorOverride(16735130), mushroomForestSpawning(), mushroomForestGen(featureGetter, carverGetter)).build());
		context.register(CANDY_CANE_VALLEY, biomeWithDefaults(defaultFantasiaAmbientBuilder().foliageColorOverride(1687910).grassColorOverride(4176127), defaultFantasiaMobSpawning(), candycanevalleyGen(featureGetter, carverGetter)).temperature(0.7F).build());

		context.register(RIVER, biomeWithDefaults(defaultFantasiaAmbientBuilder(), defaultFantasiaMobSpawning(), rivers(featureGetter, carverGetter, false)).temperature(0.5F).downfall(0.1F).build());
		
		context.register(DEPTHS_OF_FANTASIA, biomeWithDefaults(defaultFantasiaAmbientBuilder(), fantasiaDepthsMobSpawning(), depthGen(featureGetter, carverGetter)).temperature(0.7F).downfall(0.0F).build());
		context.register(SKYLANDS, biomeWithDefaults(defaultFantasiaAmbientBuilder(), skylandsMobSpawning(), skylandsGen(featureGetter, carverGetter)).temperature(0.7F).downfall(0.0F).build());
	
		//Demonicio
		context.register(SKULL_DESERT, biomeWithDefaults(defaultDemonicioAmbientBuilder().foliageColorOverride(8350208).grassColorOverride(8350208), defaultDemonicioMobSpawning(), defaultDemonicioGenSettingBuilder(featureGetter, carverGetter)).temperature(-0.25F).build());
		context.register(NIGHTMARE_FOREST, biomeWithDefaults(defaultDemonicioAmbientBuilder().foliageColorOverride(3289650).grassColorOverride(3289650), defaultDemonicioMobSpawning(), nightmareForestGen(featureGetter, carverGetter)).temperature(0.4F).build());
		context.register(BLOOD_RIVER, biomeWithDefaults(defaultDemonicioAmbientBuilder().grassColorOverride(8323072).foliageColorOverride(8323072), defaultDemonicioMobSpawning(), rivers(featureGetter, carverGetter, false)).temperature(0.5F).downfall(0.1F).build());
		
	}
}