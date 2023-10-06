package net.setrion.koratio.registry;

import java.util.List;
import java.util.OptionalLong;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.data.BiomeGenerator;
import net.setrion.koratio.world.level.biome.DemonicioBiomeProvider;
import net.setrion.koratio.world.level.biome.DemonicioSurfaceRules;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;
import net.setrion.koratio.world.level.biome.FantasiaSurfaceRules;
import net.setrion.koratio.world.level.chunk.chunkgenerators.DemonicioChunkGenerator;
import net.setrion.koratio.world.level.chunk.chunkgenerators.FantasiaChunkGenerator;

public class KoratioDimensions {
	
	public static long seed;

	public static final ResourceKey<NoiseGeneratorSettings> FANTASIA_NOISE_GEN = ResourceKey.create(Registries.NOISE_SETTINGS, Koratio.prefix("fantasia"));
	public static final ResourceKey<NoiseGeneratorSettings> DEMONICIO_NOISE_GEN = ResourceKey.create(Registries.NOISE_SETTINGS, Koratio.prefix("demonicio"));
	
	public static final ResourceKey<DimensionType> FANTASIA_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Koratio.prefix("fantasia"));
	public static final ResourceKey<DimensionType> DEMONICIO_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Koratio.prefix("demonicio"));

	public static final ResourceKey<LevelStem> FANTASIA_LEVEL_STEM =  ResourceKey.create(Registries.LEVEL_STEM, Koratio.prefix("fantasia"));
	public static final ResourceKey<LevelStem> DEMONICIO_LEVEL_STEM =  ResourceKey.create(Registries.LEVEL_STEM, Koratio.prefix("demonicio"));
	
	public static final ResourceKey<Level> FANTASIA_DIMENSION_KEY = ResourceKey.create(Registries.DIMENSION, Koratio.prefix("fantasia"));
	public static final ResourceKey<Level> DEMONICIO_DIMENSION_KEY = ResourceKey.create(Registries.DIMENSION, Koratio.prefix("demonicio"));
	
	private static DimensionType fantasiaDimType() {
		return new DimensionType(
				OptionalLong.empty(), //Time
				true, //skylight
				false, //ceiling
				false, //ultrawarm
				true, //natural
				1, //coordinate scale
				true, //bed works
				true, //respawn anchor works
				-64, // Minimum Y Level
				384, // Height + Min Y = Max Y
				384, // Logical Height
				BlockTags.INFINIBURN_OVERWORLD, //infiburn
				BuiltinDimensionTypes.OVERWORLD_EFFECTS, // DimensionRenderInfo
				0f, //basic light level
				new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 7)
		);
	}
	
	private static DimensionType demonicioDimType() {
		return new DimensionType(
				OptionalLong.of(15000),
				false, //skylight
				false, //ceiling
				false, //ultrawarm
				true, //natural
				1, //coordinate scale
				false, //bed works
				true, //respawn anchor works
				-64, // Minimum Y Level
				384, // Height + Min Y = Max Y
				384, // Logical Height
				BlockTags.INFINIBURN_OVERWORLD, //infiburn
				BuiltinDimensionTypes.OVERWORLD_EFFECTS, // DimensionRenderInfo
				10f, //basic light level
				new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 7)
		);
	}


	public static NoiseGeneratorSettings fantasiaDefault() {

		return new NoiseGeneratorSettings(
				NoiseSettings.create(-64, 384, 1, 2),
				Blocks.STONE.defaultBlockState(),
				Blocks.WATER.defaultBlockState(),
				new NoiseRouter(
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero()
				),
				FantasiaSurfaceRules.surface(),
				List.of(),
				63,
				false,
				false,
				false,
				false
		);
	}
	
	public static NoiseGeneratorSettings demonicioDefault() {

		return new NoiseGeneratorSettings(
				NoiseSettings.create(-64, 384, 1, 2),
				Blocks.STONE.defaultBlockState(),
				KoratioBlocks.BLOOD.get().defaultBlockState(),
				new NoiseRouter(
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero(),
						DensityFunctions.zero()
				),
				DemonicioSurfaceRules.surface(),
				List.of(),
				63,
				false,
				true,
				true,
				false
		);
	}
	   
	public static void bootstrapNoise(BootstapContext<NoiseGeneratorSettings> context) {
		context.register(FANTASIA_NOISE_GEN, fantasiaDefault());
		context.register(DEMONICIO_NOISE_GEN, demonicioDefault());
	}

	public static void bootstrapType(BootstapContext<DimensionType> context) {
		context.register(FANTASIA_DIM_TYPE, fantasiaDimType());
		context.register(DEMONICIO_DIM_TYPE, demonicioDimType());
	}

	public static void bootstrapStem(BootstapContext<LevelStem> context) {
		HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
		HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
		HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

		NoiseBasedChunkGenerator fantasiaChunkGenerator = new NoiseBasedChunkGenerator(
				new FantasiaBiomeProvider(
						biomeRegistry,
						BiomeGenerator.makeFantasiaBiomeList(biomeRegistry, biomeRegistry.getOrThrow(KoratioBiomes.DEPTHS_OF_FANTASIA), biomeRegistry.getOrThrow(KoratioBiomes.SKYLANDS)),
						-1.25F,
						2.5F),
				noiseGenSettings.getOrThrow(FANTASIA_NOISE_GEN));

		LevelStem fantasiaStem = new LevelStem(
				dimTypes.getOrThrow(KoratioDimensions.FANTASIA_DIM_TYPE),
				new FantasiaChunkGenerator(
						fantasiaChunkGenerator,
						noiseGenSettings.getOrThrow(FANTASIA_NOISE_GEN))
					);

		NoiseBasedChunkGenerator demonicioChunkGenerator = new NoiseBasedChunkGenerator(
				new DemonicioBiomeProvider(
						biomeRegistry,
						BiomeGenerator.makeDemonicioBiomeList(biomeRegistry, biomeRegistry.getOrThrow(KoratioBiomes.DEPTHS_OF_FANTASIA), biomeRegistry.getOrThrow(KoratioBiomes.SKYLANDS)),
						-1.25F,
						2.5F),
				noiseGenSettings.getOrThrow(DEMONICIO_NOISE_GEN));

		LevelStem demonicioStem = new LevelStem(
				dimTypes.getOrThrow(KoratioDimensions.DEMONICIO_DIM_TYPE),
				new DemonicioChunkGenerator(
						demonicioChunkGenerator,
						noiseGenSettings.getOrThrow(DEMONICIO_NOISE_GEN))
					);
		
		context.register(FANTASIA_LEVEL_STEM, fantasiaStem);
		context.register(DEMONICIO_LEVEL_STEM, demonicioStem);
	}
}