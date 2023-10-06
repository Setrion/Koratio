package net.setrion.koratio.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioTags;

public class BlockTagGenerator extends IntrinsicHolderTagsProvider<Block> {
 
    @SuppressWarnings("deprecation")
	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
		super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), Koratio.MOD_ID, helper);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(KoratioTags.Blocks.PANGO_LOGS).add(KoratioBlocks.PANGO_LOG.get(), KoratioBlocks.PANGO_WOOD.get(), KoratioBlocks.STRIPPED_PANGO_LOG.get(), KoratioBlocks.STRIPPED_PANGO_WOOD.get());
		tag(KoratioTags.Blocks.RUGONA_LOGS).add(KoratioBlocks.RUGONA_LOG.get(), KoratioBlocks.RUGONA_WOOD.get(), KoratioBlocks.STRIPPED_RUGONA_LOG.get(), KoratioBlocks.STRIPPED_RUGONA_WOOD.get());
		tag(KoratioTags.Blocks.VARESO_LOGS).add(KoratioBlocks.VARESO_LOG.get(), KoratioBlocks.VARESO_WOOD.get(), KoratioBlocks.STRIPPED_VARESO_LOG.get(), KoratioBlocks.STRIPPED_VARESO_WOOD.get());
		tag(KoratioTags.Blocks.NIGHY_LOGS).add(KoratioBlocks.NIGHY_LOG.get(), KoratioBlocks.NIGHY_WOOD.get(), KoratioBlocks.STRIPPED_NIGHY_LOG.get(), KoratioBlocks.STRIPPED_NIGHY_WOOD.get());
		tag(KoratioTags.Blocks.KORATIO_LOGS).addTags(KoratioTags.Blocks.PANGO_LOGS, KoratioTags.Blocks.RUGONA_LOGS, KoratioTags.Blocks.VARESO_LOGS, KoratioTags.Blocks.NIGHY_LOGS);
		tag(KoratioTags.Blocks.MUSHROOMS).add(Blocks.RED_MUSHROOM, Blocks.BROWN_MUSHROOM, KoratioBlocks.PURPLE_MUSHROOM.get(), KoratioBlocks.GREEN_MUSHROOM.get());
		tag(BlockTags.LOGS).addTag(KoratioTags.Blocks.KORATIO_LOGS);
		tag(BlockTags.LOGS_THAT_BURN).addTag(KoratioTags.Blocks.KORATIO_LOGS);
		tag(BlockTags.SAPLINGS).add(KoratioBlocks.PANGO_SAPLING.get(), KoratioBlocks.RUGONA_SAPLING.get(), KoratioBlocks.VARESO_SAPLING.get(), KoratioBlocks.NIGHY_SAPLING.get());
		tag(BlockTags.LEAVES).add(KoratioBlocks.PANGO_LEAVES.get(), KoratioBlocks.RUGONA_LEAVES.get(), KoratioBlocks.VARESO_LEAVES.get(), KoratioBlocks.NIGHY_LEAVES.get());
		tag(BlockTags.PLANKS).add(KoratioBlocks.PANGO_PLANKS.get(), KoratioBlocks.RUGONA_PLANKS.get(), KoratioBlocks.VARESO_PLANKS.get(), KoratioBlocks.NIGHY_PLANKS.get());
		tag(KoratioTags.Blocks.KORATIO_FENCES).add(KoratioBlocks.PANGO_FENCE.get(), KoratioBlocks.RUGONA_FENCE.get(), KoratioBlocks.VARESO_FENCE.get(), KoratioBlocks.NIGHY_FENCE.get());
		tag(KoratioTags.Blocks.KORATIO_FENCE_GATES).add(KoratioBlocks.PANGO_FENCE_GATE.get(), KoratioBlocks.RUGONA_FENCE_GATE.get(), KoratioBlocks.VARESO_FENCE_GATE.get(), KoratioBlocks.NIGHY_FENCE_GATE.get());
		tag(BlockTags.WOODEN_FENCES).add(KoratioBlocks.PANGO_FENCE.get(), KoratioBlocks.RUGONA_FENCE.get(), KoratioBlocks.VARESO_FENCE.get(), KoratioBlocks.NIGHY_FENCE.get());
		tag(BlockTags.FENCE_GATES).add(KoratioBlocks.PANGO_FENCE_GATE.get(), KoratioBlocks.RUGONA_FENCE_GATE.get(), KoratioBlocks.VARESO_FENCE_GATE.get(), KoratioBlocks.NIGHY_FENCE_GATE.get());
		tag(Tags.Blocks.FENCES_WOODEN).add(KoratioBlocks.PANGO_FENCE.get(), KoratioBlocks.RUGONA_FENCE.get(), KoratioBlocks.VARESO_FENCE.get(), KoratioBlocks.NIGHY_FENCE.get());
		tag(Tags.Blocks.FENCE_GATES_WOODEN).add(KoratioBlocks.PANGO_FENCE_GATE.get(), KoratioBlocks.RUGONA_FENCE_GATE.get(), KoratioBlocks.VARESO_FENCE_GATE.get(), KoratioBlocks.NIGHY_FENCE_GATE.get());
		tag(BlockTags.WOODEN_SLABS).add(KoratioBlocks.PANGO_SLAB.get(), KoratioBlocks.RUGONA_SLAB.get(), KoratioBlocks.VARESO_SLAB.get(), KoratioBlocks.NIGHY_SLAB.get());
		tag(BlockTags.WOODEN_STAIRS).add(KoratioBlocks.PANGO_STAIRS.get(), KoratioBlocks.RUGONA_STAIRS.get(), KoratioBlocks.VARESO_STAIRS.get(), KoratioBlocks.NIGHY_STAIRS.get());
		tag(BlockTags.WOODEN_BUTTONS).add(KoratioBlocks.PANGO_BUTTON.get(), KoratioBlocks.RUGONA_BUTTON.get(), KoratioBlocks.VARESO_BUTTON.get(), KoratioBlocks.NIGHY_BUTTON.get());
		tag(BlockTags.WOODEN_PRESSURE_PLATES).add(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), KoratioBlocks.VARESO_PRESSURE_PLATE.get(), KoratioBlocks.NIGHY_PRESSURE_PLATE.get());
		tag(BlockTags.WOODEN_TRAPDOORS).add(KoratioBlocks.PANGO_TRAPDOOR.get(), KoratioBlocks.RUGONA_TRAPDOOR.get(), KoratioBlocks.VARESO_TRAPDOOR.get(), KoratioBlocks.NIGHY_TRAPDOOR.get());
		tag(BlockTags.WOODEN_DOORS).add(KoratioBlocks.PANGO_DOOR.get(), KoratioBlocks.RUGONA_DOOR.get(), KoratioBlocks.VARESO_DOOR.get(), KoratioBlocks.NIGHY_DOOR.get());
		tag(BlockTags.STANDING_SIGNS).add(KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.NIGHY_SIGN.get());
		tag(BlockTags.WALL_SIGNS).add(KoratioBlocks.PANGO_WALL_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get(), KoratioBlocks.NIGHY_WALL_SIGN.get());
		tag(BlockTags.WALL_HANGING_SIGNS).add(KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(), KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(), KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(), KoratioBlocks.NIGHY_WALL_HANGING_SIGN.get());
		tag(BlockTags.CEILING_HANGING_SIGNS).add(KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.NIGHY_HANGING_SIGN.get());
		tag(Tags.Blocks.CHESTS_WOODEN).add(KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.NIGHY_CHEST.get());
		tag(Tags.Blocks.CHESTS).addTag(Tags.Blocks.CHESTS_WOODEN);
		tag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add(KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.NIGHY_CHEST.get());
		tag(BlockTags.FEATURES_CANNOT_REPLACE).add(KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.NIGHY_CHEST.get());
		tag(BlockTags.PORTALS).add(KoratioBlocks.FANTASIA_PORTAL.get());
		tag(BlockTags.REPLACEABLE).add(KoratioBlocks.GILDED_VINES.get(), KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get());
		tag(BlockTags.SWORD_EFFICIENT).add(KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get());
		tag(BlockTags.REPLACEABLE_BY_TREES).add(KoratioBlocks.GILDED_VINES.get(), KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get());
		tag(BlockTags.FIRE).add(KoratioBlocks.AMETHYST_FIRE.get());
		tag(BlockTags.CAMPFIRES).add(KoratioBlocks.AMETHYST_CAMPFIRE.get());
		tag(KoratioTags.Blocks.AMETHYST_FIRE_BASE_BLOCKS).addTags(BlockTags.SOUL_FIRE_BASE_BLOCKS, BlockTags.INFINIBURN_NETHER, BlockTags.INFINIBURN_END).add(Blocks.AMETHYST_BLOCK);
		tag(KoratioTags.Blocks.EMERALD_FIRE_BASE_BLOCKS).addTags(BlockTags.SOUL_FIRE_BASE_BLOCKS, BlockTags.INFINIBURN_NETHER, BlockTags.INFINIBURN_END).add(Blocks.EMERALD_BLOCK);
		
		tag(BlockTags.STAIRS).add(KoratioBlocks.SOUL_STONE_STAIRS.get(), KoratioBlocks.INFESTED_SOUL_STONE_STAIRS.get(), KoratioBlocks.SOUL_COBBLESTONE_STAIRS.get(), KoratioBlocks.INFESTED_SOUL_COBBLESTONE_STAIRS.get(), KoratioBlocks.SOUL_STONE_BRICK_STAIRS.get(), KoratioBlocks.INFESTED_SOUL_STONE_BRICK_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get());
		tag(BlockTags.SLABS).add(KoratioBlocks.SOUL_STONE_SLAB.get(), KoratioBlocks.INFESTED_SOUL_STONE_SLAB.get(), KoratioBlocks.SOUL_COBBLESTONE_SLAB.get(), KoratioBlocks.INFESTED_SOUL_COBBLESTONE_SLAB.get(), KoratioBlocks.SMOOTH_SOUL_STONE_SLAB.get(), KoratioBlocks.SOUL_STONE_BRICK_SLAB.get(), KoratioBlocks.INFESTED_SOUL_STONE_BRICK_SLAB.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get());
		tag(BlockTags.WALLS).add(KoratioBlocks.SOUL_COBBLESTONE_WALL.get(), KoratioBlocks.INFESTED_SOUL_COBBLESTONE_WALL.get(), KoratioBlocks.SOUL_STONE_BRICK_WALL.get(), KoratioBlocks.INFESTED_SOUL_STONE_BRICK_WALL.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get());
		
		tag(BlockTags.STONE_PRESSURE_PLATES).add(KoratioBlocks.SOUL_STONE_PRESSURE_PLATE.get(), KoratioBlocks.INFESTED_SOUL_STONE_PRESSURE_PLATE.get());
		tag(BlockTags.STONE_BUTTONS).add(KoratioBlocks.SOUL_STONE_BUTTON.get(), KoratioBlocks.INFESTED_SOUL_STONE_BUTTON.get());
		
		tag(BlockTags.BEACON_BASE_BLOCKS).add(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), KoratioBlocks.ARSOY_BLOCK.get());
		
		tag(BlockTags.ENDERMAN_HOLDABLE).add(KoratioBlocks.GREEN_MUSHROOM.get(), KoratioBlocks.PURPLE_MUSHROOM.get());
		
		tag(KoratioTags.Blocks.CRYSTAL_CAVE_CRYSTALS).add(Blocks.AMETHYST_BLOCK, Blocks.BUDDING_AMETHYST, Blocks.SMALL_AMETHYST_BUD, Blocks.MEDIUM_AMETHYST_BUD, Blocks.LARGE_AMETHYST_BUD, Blocks.AMETHYST_CLUSTER);
		tag(KoratioTags.Blocks.FANTASIA_CARVER_REPLACEABLES).addTag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).addTag(KoratioTags.Blocks.CRYSTAL_CAVE_CRYSTALS).add(KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get());
		tag(KoratioTags.Blocks.DEMONICIO_CARVER_REPLACEABLES).addTag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).add(KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get(), Blocks.SOUL_SAND, Blocks.SOUL_SOIL, KoratioBlocks.SOUL_STONE.get());
		
		tag(BlockTags.SMALL_FLOWERS).add(KoratioBlocks.RAINBOW_ROSE.get(), KoratioBlocks.RAINBOW_ALLIUM.get(), KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), KoratioBlocks.COOKIE_FLOWER.get());
		tag(BlockTags.FLOWER_POTS).add(KoratioBlocks.POTTED_PANGO_SAPLING.get(), KoratioBlocks.POTTED_RUGONA_SAPLING.get(), KoratioBlocks.POTTED_VARESO_SAPLING.get(), KoratioBlocks.POTTED_NIGHY_SAPLING.get(), KoratioBlocks.POTTED_RAINBOW_ROSE.get(), KoratioBlocks.POTTED_RAINBOW_ALLIUM.get(), KoratioBlocks.POTTED_RAINBOW_LILY_OF_THE_VALLEY.get(), KoratioBlocks.POTTED_COOKIE_FLOWER.get(), KoratioBlocks.POTTED_PURPLE_MUSHROOM.get(), KoratioBlocks.POTTED_GREEN_MUSHROOM.get());
		
		tag(BlockTags.CLIMBABLE).add(KoratioBlocks.GILDED_VINES.get());
		
		tag(KoratioTags.Blocks.SOUL_STONE_BRICKS).add(KoratioBlocks.SOUL_STONE_BRICKS.get(), KoratioBlocks.INFESTED_SOUL_STONE_BRICKS.get(), KoratioBlocks.CRACKED_SOUL_STONE_BRICKS.get(), KoratioBlocks.CRACKED_INFESTED_SOUL_STONE_BRICKS.get(), KoratioBlocks.CHISELED_SOUL_STONE_BRICKS.get(), KoratioBlocks.CHISELED_INFESTED_SOUL_STONE_BRICKS.get());
		
		tag(KoratioTags.Blocks.BASE_STONE_FANTASIA).add(Blocks.STONE, Blocks.DEEPSLATE);
		tag(KoratioTags.Blocks.BASE_STONE_DEMONICIO).add(KoratioBlocks.SOUL_STONE.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get());
		tag(KoratioTags.Blocks.BLOOD_STAINED_DEEPSLATE_ORE_REPLACEABLES).add(KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get());
		
		tag(KoratioTags.Blocks.ARSOY_ORES).add(KoratioBlocks.ARSOY_ORE.get(), KoratioBlocks.DEEPSLATE_ARSOY_ORE.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get());
		tag(KoratioTags.Blocks.COOKIE_ORES).add(KoratioBlocks.COOKIE_ORE.get(), KoratioBlocks.DEEPSLATE_COOKIE_ORE.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_COOKIE_ORE.get());
		
		tag(BlockTags.SWORD_EFFICIENT).add(
				KoratioBlocks.GILDED_VINES.get()
			);
		
		tag(BlockTags.MINEABLE_WITH_AXE).add(
				KoratioBlocks.GREEN_MUSHROOM.get(),
				KoratioBlocks.PURPLE_MUSHROOM.get(),
				KoratioBlocks.GREEN_MUSHROOM_BLOCK.get(),
				KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get(),
				KoratioBlocks.PANGO_CHEST.get(),
				KoratioBlocks.RUGONA_CHEST.get(),
				KoratioBlocks.VARESO_CHEST.get(),
				KoratioBlocks.NIGHY_CHEST.get(),
				KoratioBlocks.FANTASIA_GRASS.get(),
				KoratioBlocks.TALL_FANTASIA_GRASS.get(),
				KoratioBlocks.GILDED_VINES.get()
			);
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
				KoratioBlocks.ARSOY_ORE.get(),
				KoratioBlocks.DEEPSLATE_ARSOY_ORE.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get(),
				KoratioBlocks.COOKIE_ORE.get(),
				KoratioBlocks.DEEPSLATE_COOKIE_ORE.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_COOKIE_ORE.get(),
				KoratioBlocks.RAW_ARSOY_BLOCK.get(),
				KoratioBlocks.ARSOY_BLOCK.get(),
				KoratioBlocks.SOUL_STONE.get(),
				KoratioBlocks.INFESTED_SOUL_STONE.get(),
				KoratioBlocks.SOUL_STONE_STAIRS.get(),
				KoratioBlocks.INFESTED_SOUL_STONE_STAIRS.get(),
				KoratioBlocks.SOUL_STONE_SLAB.get(),
				KoratioBlocks.INFESTED_SOUL_STONE_SLAB.get(),
				KoratioBlocks.SOUL_STONE_PRESSURE_PLATE.get(),
				KoratioBlocks.INFESTED_SOUL_STONE_PRESSURE_PLATE.get(),
				KoratioBlocks.SOUL_STONE_BUTTON.get(),
				KoratioBlocks.INFESTED_SOUL_STONE_BUTTON.get(),
				KoratioBlocks.SOUL_COBBLESTONE.get(),
				KoratioBlocks.INFESTED_SOUL_COBBLESTONE.get(),
				KoratioBlocks.SOUL_COBBLESTONE_STAIRS.get(),
				KoratioBlocks.INFESTED_SOUL_COBBLESTONE_STAIRS.get(),
				KoratioBlocks.SOUL_COBBLESTONE_SLAB.get(),
				KoratioBlocks.INFESTED_SOUL_COBBLESTONE_SLAB.get(),
				KoratioBlocks.SOUL_COBBLESTONE_WALL.get(), 
				KoratioBlocks.INFESTED_SOUL_COBBLESTONE_WALL.get(),
				KoratioBlocks.SMOOTH_SOUL_STONE.get(),
				KoratioBlocks.SMOOTH_SOUL_STONE_SLAB.get(),
				KoratioBlocks.SOUL_STONE_BRICK_STAIRS.get(),
				KoratioBlocks.INFESTED_SOUL_STONE_BRICK_STAIRS.get(),
				KoratioBlocks.SOUL_STONE_BRICK_SLAB.get(),
				KoratioBlocks.INFESTED_SOUL_STONE_BRICK_SLAB.get(),
				KoratioBlocks.SOUL_STONE_BRICK_WALL.get(),
				KoratioBlocks.INFESTED_SOUL_STONE_BRICK_WALL.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(),
				KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get(),
				KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get(),
				KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS.get(),
				KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(),
				KoratioBlocks.CHISELED_BLOOD_STAINED_DEEPSLATE.get(),
				KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_BRICKS.get(),
				KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_TILES.get()
			).addTag(KoratioTags.Blocks.SOUL_STONE_BRICKS);
		/*tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
			);*/
		tag(BlockTags.MINEABLE_WITH_HOE).add(
				KoratioBlocks.PANGO_LEAVES.get(),
				KoratioBlocks.RUGONA_LEAVES.get(),
				KoratioBlocks.VARESO_LEAVES.get()
			);
		tag(KoratioTags.Blocks.NEEDS_RAINBOW_GEM_TOOL).add(
				KoratioBlocks.ARSOY_ORE.get(),
				KoratioBlocks.DEEPSLATE_ARSOY_ORE.get(),
				KoratioBlocks.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get()
			);
	}
	
	@Override
	public String getName() {
		return "Koratio Block Tags";
	}
}