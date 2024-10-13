package net.setrion.koratio.registry;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.setrion.koratio.Koratio;

import java.util.List;

public class KoratioProcessorLists {
	
	public static final ResourceKey<StructureProcessorList> OUTCAST_ABANDONED = createKey("outcast_abandoned");

	private static ResourceKey<StructureProcessorList> createKey(String name) {
		return ResourceKey.create(Registries.PROCESSOR_LIST, Koratio.prefix(name));
	}

	private static void register(BootstrapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> key, List<StructureProcessor> list) {
		context.register(key, new StructureProcessorList(list));
	}
	
	public static void bootstrap(BootstrapContext<StructureProcessorList> context) {
		register(context, OUTCAST_ABANDONED, ImmutableList.of(new RuleProcessor(ImmutableList.of(new ProcessorRule(new RandomBlockMatchTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.defaultBlockState()), new ProcessorRule(new RandomBlockStateMatchTest(Blocks.STONE_BRICK_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM), 0.8F), AlwaysTrueTest.INSTANCE, Blocks.MOSSY_STONE_BRICK_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM)), new ProcessorRule(new RandomBlockMatchTest(Blocks.STONE_BRICKS, 0.8F), AlwaysTrueTest.INSTANCE, Blocks.MOSSY_STONE_BRICKS.defaultBlockState()), new ProcessorRule(new TagMatchTest(BlockTags.DOORS), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()), new ProcessorRule(new BlockMatchTest(Blocks.TORCH), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()), new ProcessorRule(new BlockMatchTest(Blocks.WALL_TORCH), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.OAK_LOG, 0.05F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.OAK_PLANKS, 0.1F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.OAK_STAIRS, 0.1F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.STRIPPED_OAK_LOG, 0.02F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()), new ProcessorRule(new BlockStateMatchTest(Blocks.GLASS_PANE.defaultBlockState().setValue(IronBarsBlock.NORTH, Boolean.valueOf(true)).setValue(IronBarsBlock.SOUTH, Boolean.valueOf(true))), AlwaysTrueTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.defaultBlockState().setValue(IronBarsBlock.NORTH, Boolean.valueOf(true)).setValue(IronBarsBlock.SOUTH, Boolean.valueOf(true))), new ProcessorRule(new BlockStateMatchTest(Blocks.GLASS_PANE.defaultBlockState().setValue(IronBarsBlock.EAST, Boolean.valueOf(true)).setValue(IronBarsBlock.WEST, Boolean.valueOf(true))), AlwaysTrueTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.defaultBlockState().setValue(IronBarsBlock.EAST, Boolean.valueOf(true)).setValue(IronBarsBlock.WEST, Boolean.valueOf(true))), new ProcessorRule(new RandomBlockMatchTest(Blocks.WHEAT, 0.3F), AlwaysTrueTest.INSTANCE, Blocks.CARROTS.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.WHEAT, 0.2F), AlwaysTrueTest.INSTANCE, Blocks.POTATOES.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.WHEAT, 0.1F), AlwaysTrueTest.INSTANCE, Blocks.BEETROOTS.defaultBlockState())))));
	}
}