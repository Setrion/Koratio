package net.setrion.koratio.registry;

import java.util.List;
import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.RugonaFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.VaresoFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.treedecorators.LeaveGildedVineDecorator;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.NighyTrunkPlacer;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.RugonaTrunkPlacer;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.VaresoTrunkPlacer;

public class KoratioConfiguredFeatures {
	
	//Fantasia
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FANTASIA_GRASS = registerKey("patch_fantasia_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_COTTON_CANDY_GRASS = registerKey("patch_cotton_candy_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_AMETHYST_GRASS = registerKey("patch_amethyst_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_EMERALD_GRASS = registerKey("patch_emerald_grass");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> PANGO_BUSH = registerKey("pango_bush");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RUGONA = registerKey("rugona");
	public static final ResourceKey<ConfiguredFeature<?, ?>> VARESO = registerKey("vareso");
	public static final ResourceKey<ConfiguredFeature<?, ?>> NIGHY = registerKey("nighy");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_SPIKE = registerKey("amethyst_spike");
	public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_GEODE = registerKey("amethyst_geode");
	
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
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ARSOY = registerKey("ore_arsoy");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ARSOY_SMALL = registerKey("ore_arsoy_small");
	
	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, Koratio.prefix(name));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
		context.register(PATCH_FANTASIA_GRASS, new ConfiguredFeature<>(Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(KoratioBlocks.FANTASIA_GRASS.get()), 32)));
		context.register(PATCH_COTTON_CANDY_GRASS, new ConfiguredFeature<>(Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(KoratioBlocks.COTTON_CANDY_GRASS.get()), 32)));
		context.register(PATCH_AMETHYST_GRASS, new ConfiguredFeature<>(Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(KoratioBlocks.AMETHYST_GRASS.get()), 32)));
		context.register(PATCH_EMERALD_GRASS, new ConfiguredFeature<>(Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(KoratioBlocks.EMERALD_GRASS.get()), 32)));
		context.register(PANGO_BUSH, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.PANGO_LOG.get().defaultBlockState()), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(KoratioBlocks.PANGO_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 1), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));
		context.register(RUGONA, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.RUGONA_LOG.get().defaultBlockState()), new RugonaTrunkPlacer(10, 2, 2), BlockStateProvider.simple(KoratioBlocks.RUGONA_LEAVES.get().defaultBlockState()), new RugonaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1)), new ThreeLayersFeatureSize(1, 2, 3, 1, 2, OptionalInt.empty())).ignoreVines().build()));
		context.register(VARESO, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.VARESO_LOG.get().defaultBlockState()), new VaresoTrunkPlacer(20, 4, 4), BlockStateProvider.simple(KoratioBlocks.VARESO_LEAVES.get().defaultBlockState()), new VaresoFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 2, 3, 1, 2, OptionalInt.empty())).decorators(ImmutableList.of(new LeaveGildedVineDecorator(0.25F))).ignoreVines().build()));
		context.register(NIGHY, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(KoratioBlocks.NIGHY_LOG.get().defaultBlockState()), new NighyTrunkPlacer(10, 2, 4), BlockStateProvider.simple(KoratioBlocks.NIGHY_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new ThreeLayersFeatureSize(1, 2, 3, 1, 2, OptionalInt.empty())).ignoreVines().build()));
		context.register(CHOCOLATE_MILK_LAKE, new ConfiguredFeature<>(Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(KoratioBlocks.CHOCOLATE_MILK.get().defaultBlockState()), BlockStateProvider.simple(Blocks.GRASS_BLOCK.defaultBlockState()))));
		
		context.register(AMETHYST_SPIKE, new ConfiguredFeature<>(KoratioFeatures.AMETHYST_SPIKES.get(), new NoneFeatureConfiguration()));
		context.register(AMETHYST_GEODE, new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR), BlockStateProvider.simple(Blocks.AMETHYST_BLOCK), BlockStateProvider.simple(Blocks.BUDDING_AMETHYST), BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT), List.of(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(), Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), Blocks.LARGE_AMETHYST_BUD.defaultBlockState(), Blocks.AMETHYST_CLUSTER.defaultBlockState()), BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D), new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1)));
		
		context.register(TREES_FANTASIA_FIELDLANDS, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(PANGO_BUSH)), 0.9F)), PlacementUtils.inlinePlaced(features.getOrThrow(RUGONA)))));
		context.register(HUGE_PURPLE_MUSHROOM, new ConfiguredFeature<>(KoratioFeatures.HUGE_PURPLE_MUSHROOM.get(), new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get().defaultBlockState()), BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 2)));
		context.register(HUGE_GREEN_MUSHROOM, new ConfiguredFeature<>(KoratioFeatures.HUGE_GREEN_MUSHROOM.get(), new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get().defaultBlockState()), BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 2)));
		context.register(HUGE_CANDY_CANE, new ConfiguredFeature<>(KoratioFeatures.HUGE_CANDY_CANE.get(), new NoneFeatureConfiguration()));
		
		context.register(FLOWER_RAINBOW_FIELDS, new ConfiguredFeature<>(Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(KoratioBlocks.RAINBOW_ROSE.get().defaultBlockState(), 3).add(KoratioBlocks.RAINBOW_ALLIUM.get().defaultBlockState(), 2).add(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get().defaultBlockState(), 1)), 64)));
		context.register(FLOWER_CANDY_CANE_VALLEY, new ConfiguredFeature<>(Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(KoratioBlocks.COOKIE_FLOWER.get().defaultBlockState(), 3).add(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get().defaultBlockState(), 1).add(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get().defaultBlockState(), 1).add(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get().defaultBlockState(), 1).add(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get().defaultBlockState(), 1).add(KoratioBlocks.RED_SUGARGLASS_FLOWER.get().defaultBlockState(), 1)), 64)));
		context.register(PATCH_PURPLE_MUSHROOM, new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(KoratioBlocks.PURPLE_MUSHROOM.get())))));
		context.register(PATCH_GREEN_MUSHROOM, new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(KoratioBlocks.GREEN_MUSHROOM.get())))));
		RuleTest ruletest1 = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest ruletest2 = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
		RuleTest ruletest3 = new TagMatchTest(KoratioTags.Blocks.BLOOD_STAINED_DEEPSLATE_ORE_REPLACEABLES);
		
		List<OreConfiguration.TargetBlockState> list = List.of(OreConfiguration.target(ruletest1, KoratioBlocks.ARSOY_ORE.get().defaultBlockState()), OreConfiguration.target(ruletest2, KoratioBlocks.DEEPSLATE_ARSOY_ORE.get().defaultBlockState()), OreConfiguration.target(ruletest3, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get().defaultBlockState()));
		context.register(ORE_ARSOY, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(list, 9)));
	    context.register(ORE_ARSOY_SMALL, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(list, 4)));
	}
	
	private static RandomPatchConfiguration grassPatch(BlockStateProvider state, int i) {
		return FeatureUtils.simpleRandomPatchConfiguration(i, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(state)));
	}
}