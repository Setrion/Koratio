package net.setrion.koratio.registry;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.CandyFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.ElvenFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.RugonaFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.VaresoFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.treedecorators.LeaveGildedVineDecorator;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.ElvenTrunkPlacer;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.RugonaTrunkPlacer;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.VaresoTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class KoratioConfiguredFeatures {
	
	//Fantasia
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FANTASIA_GRASS = registerKey("patch_fantasia_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_COTTON_CANDY_GRASS = registerKey("patch_cotton_candy_grass");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> PANGO_BUSH = registerKey("pango_bush");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RUGONA = registerKey("rugona");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CANDY_TREE = registerKey("candy_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> VARESO = registerKey("vareso");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ELVEN = registerKey("elven");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CHOCOLATE_MILK_LAKE = registerKey("chocolate_milk_lake");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_CANDY_CANE = registerKey("huge_candy_cane");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_FANTASIA_FIELDLANDS = registerKey("trees_fantasia_fieldlands");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_MUSHROOM = registerKey("huge_purple_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_GREEN_MUSHROOM = registerKey("huge_green_mushroom");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_RAINBOW_FIELDS = registerKey("flower_rainbow_fields");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_CANDY_CANE_VALLEY = registerKey("flower_candy_cane_valley");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PURPLE_MUSHROOM = registerKey("patch_purple_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_GREEN_MUSHROOM = registerKey("patch_green_mushroom");
	
	//Ores
	
	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, Koratio.prefix(name));
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
		context.register(PATCH_FANTASIA_GRASS, new ConfiguredFeature<>(Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(KoratioBlocks.FANTASIA_GRASS.get()), 32)));
		context.register(PATCH_COTTON_CANDY_GRASS, new ConfiguredFeature<>(Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(KoratioBlocks.COTTON_CANDY_GRASS.get()), 32)));
		context.register(PANGO_BUSH, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.PANGO_LOG.get().defaultBlockState()), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(KoratioBlocks.PANGO_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 1), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));
		context.register(RUGONA, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.RUGONA_LOG.get().defaultBlockState()), new RugonaTrunkPlacer(10, 2, 2), BlockStateProvider.simple(KoratioBlocks.RUGONA_LEAVES.get().defaultBlockState()), new RugonaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1)), new ThreeLayersFeatureSize(1, 2, 3, 1, 2, OptionalInt.empty())).ignoreVines().build()));
		context.register(CANDY_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.CANDY_LOG.get().defaultBlockState()), new StraightTrunkPlacer(8, 3, 0), BlockStateProvider.simple(KoratioBlocks.COTTON_CANDY_LEAVES.get().defaultBlockState()), new CandyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 2, 3, 1, 2, OptionalInt.empty())).ignoreVines().build()));
		context.register(VARESO, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.VARESO_LOG.get().defaultBlockState()), new VaresoTrunkPlacer(20, 4, 4), BlockStateProvider.simple(KoratioBlocks.VARESO_LEAVES.get().defaultBlockState()), new VaresoFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 2, 3, 1, 2, OptionalInt.empty())).decorators(ImmutableList.of(new LeaveGildedVineDecorator(0.25F))).ignoreVines().build()));
		context.register(ELVEN, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.ELVEN_LOG.get().defaultBlockState()), new ElvenTrunkPlacer(20, 4, 4), BlockStateProvider.simple(KoratioBlocks.ELVEN_LEAVES.get().defaultBlockState()), new ElvenFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 2, 3, 1, 2, OptionalInt.empty())).ignoreVines().build()));
		context.register(CHOCOLATE_MILK_LAKE, new ConfiguredFeature<>(Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(KoratioBlocks.CHOCOLATE_MILK.get().defaultBlockState()), BlockStateProvider.simple(Blocks.GRASS_BLOCK.defaultBlockState()))));
		
		context.register(TREES_FANTASIA_FIELDLANDS, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(PANGO_BUSH)), 0.9F)), PlacementUtils.inlinePlaced(features.getOrThrow(RUGONA)))));
		context.register(HUGE_PURPLE_MUSHROOM, new ConfiguredFeature<>(KoratioFeatures.HUGE_PURPLE_MUSHROOM.get(), new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get().defaultBlockState()), BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 2)));
		context.register(HUGE_GREEN_MUSHROOM, new ConfiguredFeature<>(KoratioFeatures.HUGE_GREEN_MUSHROOM.get(), new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get().defaultBlockState()), BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 2)));
		context.register(HUGE_CANDY_CANE, new ConfiguredFeature<>(KoratioFeatures.HUGE_CANDY_CANE.get(), new NoneFeatureConfiguration()));
		
		context.register(FLOWER_RAINBOW_FIELDS, new ConfiguredFeature<>(Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(KoratioBlocks.RAINBOW_ROSE.get().defaultBlockState(), 3).add(KoratioBlocks.RAINBOW_ALLIUM.get().defaultBlockState(), 2).add(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get().defaultBlockState(), 1)), 64)));
		context.register(FLOWER_CANDY_CANE_VALLEY, new ConfiguredFeature<>(Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(KoratioBlocks.COOKIE_FLOWER.get().defaultBlockState(), 3).add(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get().defaultBlockState(), 1).add(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get().defaultBlockState(), 1).add(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get().defaultBlockState(), 1).add(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get().defaultBlockState(), 1).add(KoratioBlocks.RED_SUGARGLASS_FLOWER.get().defaultBlockState(), 1)), 64)));
		context.register(PATCH_PURPLE_MUSHROOM, new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(KoratioBlocks.PURPLE_MUSHROOM.get())))));
		context.register(PATCH_GREEN_MUSHROOM, new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(KoratioBlocks.GREEN_MUSHROOM.get())))));
	}
	
	private static RandomPatchConfiguration grassPatch(BlockStateProvider state, int i) {
		return FeatureUtils.simpleRandomPatchConfiguration(i, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(state)));
	}
}