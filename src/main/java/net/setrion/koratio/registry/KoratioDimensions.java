package net.setrion.koratio.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.data.BiomeGenerator;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;
import net.setrion.koratio.world.level.biome.FantasiaSurfaceRules;
import net.setrion.koratio.world.level.chunk.chunkgenerators.FantasiaChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.OptionalLong;

public class KoratioDimensions {

	public static final DeferredRegister<MapCodec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister.create(BuiltInRegistries.CHUNK_GENERATOR, Koratio.MOD_ID);

	public static final DeferredHolder<MapCodec<? extends ChunkGenerator>, MapCodec<? extends ChunkGenerator>> FANTASIA_CHUNK_GENERATOR = CHUNK_GENERATORS.register("fantasia_chunk_generator", () -> FantasiaChunkGenerator.CODEC);
	
	public static long seed;

	public static final ResourceKey<NoiseGeneratorSettings> FANTASIA_NOISE_GEN = ResourceKey.create(Registries.NOISE_SETTINGS, Koratio.prefix("fantasia"));

	public static final ResourceKey<DimensionType> FANTASIA_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Koratio.prefix("fantasia"));

	public static final ResourceKey<LevelStem> FANTASIA_LEVEL_STEM =  ResourceKey.create(Registries.LEVEL_STEM, Koratio.prefix("fantasia"));

	public static final ResourceKey<Level> FANTASIA_DIMENSION_KEY = ResourceKey.create(Registries.DIMENSION, Koratio.prefix("fantasia"));

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
	   
	public static void bootstrapNoise(BootstrapContext<NoiseGeneratorSettings> context) {
		context.register(FANTASIA_NOISE_GEN, fantasiaDefault());
	}

	public static void bootstrapType(BootstrapContext<DimensionType> context) {
		context.register(FANTASIA_DIM_TYPE, fantasiaDimType());
	}

	public static void bootstrapStem(BootstrapContext<LevelStem> context) {
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
		
		context.register(FANTASIA_LEVEL_STEM, fantasiaStem);
	}
}