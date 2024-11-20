package net.setrion.koratio.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.EndFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.setrion.koratio.Koratio;

import java.util.List;

public class KoratioPlacedFeatures {

	public static final ResourceKey<PlacedFeature> PLACED_PATCH_FANTASIA_GRASS = registerKey("patch_fantasia_grass");
	public static final ResourceKey<PlacedFeature> PLACED_PATCH_COTTON_CANDY_GRASS = registerKey("patch_cotton_candy_grass");
	public static final ResourceKey<PlacedFeature> PLACED_PANGO_BUSH = registerKey("tree/pango_bush");
	public static final ResourceKey<PlacedFeature> PLACED_RUGONA = registerKey("tree/rugona");
	public static final ResourceKey<PlacedFeature> PLACED_CANDY = registerKey("tree/candy");
	public static final ResourceKey<PlacedFeature> PLACED_CHOCOLATE_OAK = registerKey("tree/chocolate_oak");
	public static final ResourceKey<PlacedFeature> PLACED_VARESO = registerKey("tree/vareso");
	public static final ResourceKey<PlacedFeature> PLACED_ELVEN = registerKey("tree/elven");
	public static final ResourceKey<PlacedFeature> PLACED_CHOCOLATE_MILK_LAKE = registerKey("chocolate_milk_lake");

	public static final ResourceKey<PlacedFeature> PLACED_HUGE_CANDY_CANE = registerKey("huge_candy_cane");
	public static final ResourceKey<PlacedFeature> PLACED_HUGE_LOLLIPOP = registerKey("huge_lollipop");
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
	
	private static List<PlacementModifier> treeCheckArea(BlockState sapling) {
		return List.of(InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(sapling.getBlock()), BiomeFilter.biome());
	}
	
	private static List<PlacementModifier> mushroomCheckArea() {
		return List.of(InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.isEmpty(), BiomeFilter.biome());
	}
	
	public static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Koratio.prefix(name));
	}
	
	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

		context.register(PLACED_PATCH_FANTASIA_GRASS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PATCH_FANTASIA_GRASS), List.of(NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		context.register(PLACED_PATCH_COTTON_CANDY_GRASS, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PATCH_COTTON_CANDY_GRASS), List.of(NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		context.register(PLACED_PANGO_BUSH, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.PANGO_BUSH), treeCheckArea(KoratioBlocks.PANGO_SAPLING.get().defaultBlockState())));
		context.register(PLACED_RUGONA, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.RUGONA), treeCheckArea(KoratioBlocks.RUGONA_SAPLING.get().defaultBlockState())));
		context.register(PLACED_CANDY, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.CANDY), VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.1F, 1), KoratioBlocks.CANDY_SAPLING.get())));
		context.register(PLACED_CHOCOLATE_OAK, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.CHOCOLATE_OAK), VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25F, 1), KoratioBlocks.CHOCOLATE_OAK_SAPLING.get())));
		context.register(PLACED_VARESO, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.VARESO), treeCheckArea(KoratioBlocks.VARESO_SAPLING.get().defaultBlockState())));
		context.register(PLACED_ELVEN, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.ELVEN), treeCheckArea(KoratioBlocks.ELVEN_SAPLING.get().defaultBlockState())));
		context.register(PLACED_CHOCOLATE_MILK_LAKE, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.CHOCOLATE_MILK_LAKE), List.of(RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));

		context.register(PLACED_HUGE_CANDY_CANE, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.HUGE_CANDY_CANE), List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
		context.register(PLACED_HUGE_LOLLIPOP, new PlacedFeature(features.getOrThrow(KoratioConfiguredFeatures.HUGE_LOLLIPOP), List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
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
	}
}