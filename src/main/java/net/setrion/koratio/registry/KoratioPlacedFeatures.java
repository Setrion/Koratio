package net.setrion.koratio.registry;

import java.util.List;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.EndFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.setrion.koratio.Koratio;

public class KoratioPlacedFeatures {

	public static final ResourceKey<PlacedFeature> PLACED_PATCH_FANTASIA_GRASS = registerKey("patch_fantasia_grass");
	public static final ResourceKey<PlacedFeature> PLACED_PATCH_COTTON_CANDY_GRASS = registerKey("patch_cotton_candy_grass");
	public static final ResourceKey<PlacedFeature> PLACED_PATCH_AMETHYST_GRASS = registerKey("patch_amethyst_grass");
	public static final ResourceKey<PlacedFeature> PLACED_PATCH_EMERALD_GRASS = registerKey("patch_emeraldt_grass");
	public static final ResourceKey<PlacedFeature> PLACED_PANGO_BUSH = registerKey("tree/pango_bush");
	public static final ResourceKey<PlacedFeature> PLACED_RUGONA = registerKey("tree/rugona");
	public static final ResourceKey<PlacedFeature> PLACED_VARESO = registerKey("tree/vareso");
	public static final ResourceKey<PlacedFeature> PLACED_NIGHY = registerKey("tree/nighy");
	public static final ResourceKey<PlacedFeature> PLACED_CHOCOLATE_MILK_LAKE = registerKey("chocolate_milk_lake");
	
	public static final ResourceKey<PlacedFeature> PLACED_AMETHYST_SPIKE = registerKey("amethyst_spike");
	public static final ResourceKey<PlacedFeature> PLACED_AMETHYST_GEODE = registerKey("amethyst_geode");
	
	public static final ResourceKey<PlacedFeature> PLACED_HUGE_CANDY_CANE = registerKey("huge_candy_cane");
	public static final ResourceKey<PlacedFeature> PLACED_PURPLE_MUSHROOM = registerKey("tree/purple_mushroom");
	public static final ResourceKey<PlacedFeature> PLACED_GREEN_MUSHROOM = registerKey("tree/green_mushroom");
	public static final ResourceKey<PlacedFeature> PLACED_RAINBOW_FLOWERS = registerKey("rainbow_flowers");
	public static final ResourceKey<PlacedFeature> PLACED_CANDY_FLOWERS = registerKey("candy_flowers");
	public static final ResourceKey<PlacedFeature> PURPLE_MUSHROOM_NORMAL = registerKey("purple_mushroom_normal");
	public static final ResourceKey<PlacedFeature> GREEN_MUSHROOM_NORMAL = registerKey("green_mushroom_normal");
	public static final ResourceKey<PlacedFeature> RED_MUSHROOM_NORMAL = registerKey("red_mushroom_normal");
	public static final ResourceKey<PlacedFeature> BROWN_MUSHROOM_NORMAL = registerKey("brown_mushroom_normal");
	
	public static final ResourceKey<PlacedFeature> TREES_FANTASIA_FIELDLANDS = registerKey("trees_fantasia_fieldlands");
	
	public static final ResourceKey<PlacedFeature> END_ISLAND_DECORATED = registerKey("end_island_decorated");
	
	//Ores
	public static final ResourceKey<PlacedFeature> ORE_ARSOY_UPPER = registerKey("ore_arsoy_upper");
	public static final ResourceKey<PlacedFeature> ORE_ARSOY_MIDDLE = registerKey("ore_arsoy_middle");
	public static final ResourceKey<PlacedFeature> ORE_ARSOY_SMALL = registerKey("ore_arsoy_small");
	
	private static List<PlacementModifier> treeCheckArea(BlockState sapling) {
		return List.of(InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(sapling.getBlock()), BiomeFilter.biome());
	}
	
	private static List<PlacementModifier> mushroomCheckArea() {
		return List.of(InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.isEmpty(), BiomeFilter.biome());
	}
	
	public static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Koratio.prefix(name));
	}
	
	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
	
		context.register(PLACED_PATCH_FANTASIA_GRASS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PATCH_FANTASIA_GRASS), List.of(NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		context.register(PLACED_PATCH_COTTON_CANDY_GRASS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PATCH_COTTON_CANDY_GRASS), List.of(NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		context.register(PLACED_PATCH_AMETHYST_GRASS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PATCH_AMETHYST_GRASS), List.of(NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		context.register(PLACED_PATCH_EMERALD_GRASS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PATCH_EMERALD_GRASS), List.of(NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		context.register(PLACED_PANGO_BUSH, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PANGO_BUSH), treeCheckArea(KoratioBlocks.PANGO_SAPLING.get().defaultBlockState())));
		context.register(PLACED_RUGONA, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.RUGONA), treeCheckArea(KoratioBlocks.RUGONA_SAPLING.get().defaultBlockState())));
		context.register(PLACED_VARESO, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.VARESO), treeCheckArea(KoratioBlocks.VARESO_SAPLING.get().defaultBlockState())));
		context.register(PLACED_NIGHY, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.NIGHY), treeCheckArea(KoratioBlocks.NIGHY_SAPLING.get().defaultBlockState())));
		context.register(PLACED_CHOCOLATE_MILK_LAKE, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.CHOCOLATE_MILK_LAKE), List.of(RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		
		context.register(PLACED_AMETHYST_SPIKE, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.AMETHYST_SPIKE), List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
		context.register(PLACED_AMETHYST_GEODE, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.AMETHYST_GEODE), List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(62)), BiomeFilter.biome())));
	      
		context.register(PLACED_HUGE_CANDY_CANE, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.HUGE_CANDY_CANE), List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		context.register(PLACED_PURPLE_MUSHROOM, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.HUGE_PURPLE_MUSHROOM), mushroomCheckArea()));
		context.register(PLACED_GREEN_MUSHROOM, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.HUGE_GREEN_MUSHROOM), mushroomCheckArea()));
		context.register(PLACED_RAINBOW_FLOWERS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.FLOWER_RAINBOW_FIELDS), List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
		context.register(PLACED_CANDY_FLOWERS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.FLOWER_CANDY_CANE_VALLEY), List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
		context.register(PURPLE_MUSHROOM_NORMAL, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PATCH_PURPLE_MUSHROOM), mushroomCheckArea()));
		context.register(GREEN_MUSHROOM_NORMAL, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PATCH_GREEN_MUSHROOM), mushroomCheckArea()));
		context.register(RED_MUSHROOM_NORMAL, new PlacedFeature(features.getOrThrow(VegetationFeatures.PATCH_RED_MUSHROOM), mushroomCheckArea()));
		context.register(BROWN_MUSHROOM_NORMAL, new PlacedFeature(features.getOrThrow(VegetationFeatures.PATCH_BROWN_MUSHROOM), mushroomCheckArea()));
		context.register(TREES_FANTASIA_FIELDLANDS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.TREES_FANTASIA_FIELDLANDS), treeCheckArea(KoratioBlocks.PANGO_SAPLING.get().defaultBlockState())));
		context.register(END_ISLAND_DECORATED, new PlacedFeature(features.getOrThrow(EndFeatures.END_ISLAND), List.of(RarityFilter.onAverageOnceEvery(14), PlacementUtils.countExtra(1, 0.25F, 1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(95), VerticalAnchor.absolute(150)), BiomeFilter.biome())));
		
		context.register(ORE_ARSOY_UPPER, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.ORE_ARSOY), List.of(CountPlacement.of(90), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384)), BiomeFilter.biome())));
		context.register(ORE_ARSOY_MIDDLE, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.ORE_ARSOY), List.of(CountPlacement.of(10), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-52), VerticalAnchor.absolute(28)), BiomeFilter.biome())));
		context.register(ORE_ARSOY_SMALL, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.ORE_ARSOY_SMALL), List.of(CountPlacement.of(10), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(72)), BiomeFilter.biome())));
	}
}