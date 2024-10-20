package net.setrion.koratio.data;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.block.GlazedModelLoader;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.block.*;
import net.setrion.koratio.world.level.block.state.properties.TripleBlockPart;

public class BlockStateGenerator extends BlockStateProvider {

	public BlockStateGenerator(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, Koratio.MOD_ID, fileHelper);
	}

	@Override
	protected void registerStatesAndModels() {		
		ModelFile portal = models().getExistingFile(Koratio.prefix("block/fantasia_portal"));
		
		getVariantBuilder(KoratioBlocks.FANTASIA_PORTAL.get())
        	.partialState().with(FantasiaPortalBlock.FACING, Direction.NORTH)
            	.modelForState().modelFile(portal).addModel()
			.partialState().with(FantasiaPortalBlock.FACING, Direction.SOUTH)
				.modelForState().modelFile(portal).rotationY(90).addModel()
			.partialState().with(FantasiaPortalBlock.FACING, Direction.EAST)
				.modelForState().modelFile(portal).rotationY(180).addModel()
			.partialState().with(FantasiaPortalBlock.FACING, Direction.WEST)
				.modelForState().modelFile(portal).rotationY(270).addModel();
		
		simpleBlock(KoratioBlocks.MINIATURE_FANTASIA_PORTAL.get(), models().getExistingFile(Koratio.prefix("block/miniature/fantasia_portal")));

		simpleBlock(KoratioBlocks.DECRYPTING_TABLE.get(), models().cube(KoratioBlocks.DECRYPTING_TABLE.getKey().location().getPath(), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "_bottom"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "_top"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "_front"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "_side"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "_front"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "_side")).texture("particle", getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "_front")));
		simpleBlock(KoratioBlocks.CANDY_SHAPER.get(), models().cube(KoratioBlocks.CANDY_SHAPER.getKey().location().getPath(), getBlockPathInFolder(KoratioBlocks.CANDY_PLANKS.get()), getBlockPathInFolder(KoratioBlocks.CANDY_SHAPER.get(), "_top"), getBlockPathInFolder(KoratioBlocks.CANDY_SHAPER.get(), "_front"), getBlockPathInFolder(KoratioBlocks.CANDY_SHAPER.get(), "_side"), getBlockPathInFolder(KoratioBlocks.CANDY_SHAPER.get(), "_back"), getBlockPathInFolder(KoratioBlocks.CANDY_SHAPER.get(), "_side")).texture("particle", getBlockPathInFolder(KoratioBlocks.CANDY_SHAPER.get(), "_front")));

		flippedFarmlandBlock(KoratioBlocks.FLIPPED_FARMLAND.get());
		tintedCrossBlock(KoratioBlocks.FANTASIA_GRASS.get(), "");
		tintedCrossBlock(KoratioBlocks.COTTON_CANDY_GRASS.get(), "_top");
		tintedCrossBlock(KoratioBlocks.RAINBOW_ROSE.get(), "_stem");
		tintedCrossBlock(KoratioBlocks.RAINBOW_ALLIUM.get(), "_stem");
		tintedCrossBlock(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), "_stem");
		crossBlock(KoratioBlocks.GOLDEN_TULIP.get());
		getVariantBuilder(KoratioBlocks.GOLD_ROSE_BUSH.get())
			.partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
				.modelForState().modelFile(models().withExistingParent(Koratio.prefix("block/gold_rose_bush_bottom").getPath(), "minecraft:block/cross").texture("cross", Koratio.prefix("block/gold_rose_bush_bottom")).renderType("cutout_mipped")).addModel()
			.partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
				.modelForState().modelFile(models().withExistingParent(Koratio.prefix("block/gold_rose_bush_top").getPath(), "minecraft:block/cross").texture("cross", Koratio.prefix("block/gold_rose_bush_top")).renderType("cutout_mipped")).addModel();
		crossBlock(KoratioBlocks.COOKIE_FLOWER.get());
		simpleBlock(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/white_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/white_sugarglass_flower")).renderType("tripwire"));
		simpleBlock(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/blue_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/blue_sugarglass_flower")).renderType("tripwire"));
		simpleBlock(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/green_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/green_sugarglass_flower")).renderType("tripwire"));
		simpleBlock(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/yellow_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/yellow_sugarglass_flower")).renderType("tripwire"));
		simpleBlock(KoratioBlocks.RED_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/red_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/red_sugarglass_flower")).renderType("tripwire"));

		cauldronBlock(KoratioBlocks.MOLTEN_WHITE_SUGAR_CAULDRON.get(), "koratio:block/molten_white_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_LIGHT_GRAY_SUGAR_CAULDRON.get(), "koratio:block/molten_light_gray_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_GRAY_SUGAR_CAULDRON.get(), "koratio:block/molten_gray_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_BLACK_SUGAR_CAULDRON.get(), "koratio:block/molten_black_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_BROWN_SUGAR_CAULDRON.get(), "koratio:block/molten_brown_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_RED_SUGAR_CAULDRON.get(), "koratio:block/molten_red_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_ORANGE_SUGAR_CAULDRON.get(), "koratio:block/molten_orange_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_YELLOW_SUGAR_CAULDRON.get(), "koratio:block/molten_yellow_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_LIME_SUGAR_CAULDRON.get(), "koratio:block/molten_lime_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_GREEN_SUGAR_CAULDRON.get(), "koratio:block/molten_green_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_CYAN_SUGAR_CAULDRON.get(), "koratio:block/molten_cyan_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_LIGHT_BLUE_SUGAR_CAULDRON.get(), "koratio:block/molten_light_blue_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_BLUE_SUGAR_CAULDRON.get(), "koratio:block/molten_blue_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_PURPLE_SUGAR_CAULDRON.get(), "koratio:block/molten_purple_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_MAGENTA_SUGAR_CAULDRON.get(), "koratio:block/molten_magenta_sugar_still");
		cauldronBlock(KoratioBlocks.MOLTEN_PINK_SUGAR_CAULDRON.get(), "koratio:block/molten_pink_sugar_still");

		simpleBlock(KoratioBlocks.RAINBOW_GEM_BLOCK.get());
		simpleBlock(KoratioBlocks.RUBY_BLOCK.get());
		simpleBlock(KoratioBlocks.SAPPHIRE_BLOCK.get());
		simpleBlock(KoratioBlocks.TOPAZ_BLOCK.get());
		simpleBlock(KoratioBlocks.ONYX_BLOCK.get());
		simpleBlock(KoratioBlocks.RUBY_ORE.get());
		simpleBlock(KoratioBlocks.DEEPSLATE_RUBY_ORE.get());
		simpleBlock(KoratioBlocks.SAPPHIRE_ORE.get());
		simpleBlock(KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
		simpleBlock(KoratioBlocks.TOPAZ_ORE.get());
		simpleBlock(KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get());
		simpleBlock(KoratioBlocks.ONYX_ORE.get());
		simpleBlock(KoratioBlocks.DEEPSLATE_ONYX_ORE.get());
		simpleBlock(KoratioBlocks.COOKIE_ORE.get());
		simpleBlock(KoratioBlocks.DEEPSLATE_COOKIE_ORE.get());

		simpleBlock(KoratioBlocks.WHITE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.LIGHT_GRAY_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.GRAY_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.BLACK_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.BROWN_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.RED_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.ORANGE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.YELLOW_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.LIME_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.GREEN_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.CYAN_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.LIGHT_BLUE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.BLUE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.PURPLE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.MAGENTA_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.PINK_SUGAR_BLOCK.get());

		simpleBlock(KoratioBlocks.STICKY_WHITE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_LIGHT_GRAY_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_GRAY_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_BLACK_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_BROWN_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_RED_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_ORANGE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_YELLOW_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_LIME_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_GREEN_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_CYAN_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_LIGHT_BLUE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_BLUE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_PURPLE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_MAGENTA_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.STICKY_PINK_SUGAR_BLOCK.get());

		simpleBlock(KoratioBlocks.WHITE_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.LIGHT_GRAY_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.GRAY_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.BLACK_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.BROWN_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.RED_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.ORANGE_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.YELLOW_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.LIME_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.GREEN_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.CYAN_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.LIGHT_BLUE_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.BLUE_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.PURPLE_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.MAGENTA_FROSTING_BLOCK.get());
		simpleBlock(KoratioBlocks.PINK_FROSTING_BLOCK.get());

		simpleBlock(KoratioBlocks.WHITE_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.LIGHT_GRAY_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.GRAY_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.BLACK_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.BROWN_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.RED_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.ORANGE_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.YELLOW_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.LIME_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.GREEN_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.CYAN_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.LIGHT_BLUE_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.BLUE_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.PURPLE_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.MAGENTA_CANDY_BLOCK.get());
		simpleBlock(KoratioBlocks.PINK_CANDY_BLOCK.get());

		simpleBlock(KoratioBlocks.WHITE_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.WHITE_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.WHITE_WOOL)));
		simpleBlock(KoratioBlocks.ORANGE_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.ORANGE_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.ORANGE_WOOL)));
		simpleBlock(KoratioBlocks.MAGENTA_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.MAGENTA_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.MAGENTA_WOOL)));
		simpleBlock(KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.LIGHT_BLUE_WOOL)));
		simpleBlock(KoratioBlocks.YELLOW_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.YELLOW_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.YELLOW_WOOL)));
		simpleBlock(KoratioBlocks.LIME_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.LIME_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.LIME_WOOL)));
		simpleBlock(KoratioBlocks.PINK_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.PINK_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.PINK_WOOL)));
		simpleBlock(KoratioBlocks.GRAY_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.GRAY_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.GRAY_WOOL)));
		simpleBlock(KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.LIGHT_GRAY_WOOL)));
		simpleBlock(KoratioBlocks.CYAN_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.CYAN_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.CYAN_WOOL)));
		simpleBlock(KoratioBlocks.PURPLE_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.PURPLE_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.PURPLE_WOOL)));
		simpleBlock(KoratioBlocks.BLUE_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.BLUE_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.BLUE_WOOL)));
		simpleBlock(KoratioBlocks.BROWN_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.BROWN_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.BROWN_WOOL)));
		simpleBlock(KoratioBlocks.GREEN_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.GREEN_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.GREEN_WOOL)));
		simpleBlock(KoratioBlocks.RED_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.RED_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.RED_WOOL)));
		simpleBlock(KoratioBlocks.BLACK_LEVITATING_WOOL.get(), models().cubeAll(KoratioBlocks.BLACK_LEVITATING_WOOL.getId().getPath(), blockTexture(Blocks.BLACK_WOOL)));

		simpleBlock(KoratioBlocks.COOKIE_BLOCK.get());
		stairsBlock(KoratioBlocks.COOKIE_BLOCK_STAIRS.get(), Koratio.prefix("block/cookie_block"));
		slabBlock(KoratioBlocks.COOKIE_BLOCK_SLAB.get(), KoratioBlocks.COOKIE_BLOCK.getId(), Koratio.prefix("block/cookie_block"));

		glazedBlock(KoratioBlocks.RAW_GINGERBREAD_BLOCK);
		stairsBlock(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get(), Koratio.prefix("block/raw_gingerbread_block"));
		slabBlock(KoratioBlocks.RAW_GINGERBREAD_SLAB.get(), KoratioBlocks.RAW_GINGERBREAD_BLOCK.getId(), Koratio.prefix("block/raw_gingerbread_block"));
		glazedBlock(KoratioBlocks.GINGERBREAD_BLOCK);
		stairsBlock(KoratioBlocks.GINGERBREAD_STAIRS.get(), Koratio.prefix("block/gingerbread_block"));
		slabBlock(KoratioBlocks.GINGERBREAD_SLAB.get(), KoratioBlocks.GINGERBREAD_BLOCK.getId(), Koratio.prefix("block/gingerbread_block"));
		glazedBlock(KoratioBlocks.RAW_GINGERBREAD_BRICKS);
		stairsBlock(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get(), Koratio.prefix("block/raw_gingerbread_bricks"));
		slabBlock(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS.getId(), Koratio.prefix("block/raw_gingerbread_bricks"));
		glazedBlock(KoratioBlocks.GINGERBREAD_BRICKS);
		stairsBlock(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get(), Koratio.prefix("block/gingerbread_bricks"));
		slabBlock(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.GINGERBREAD_BRICKS.getId(), Koratio.prefix("block/gingerbread_bricks"));
		glazedBlock(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS);
		stairsBlock(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.get(), Koratio.prefix("block/raw_large_gingerbread_bricks"));
		slabBlock(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.getId(), Koratio.prefix("block/raw_large_gingerbread_bricks"));
		glazedBlock(KoratioBlocks.LARGE_GINGERBREAD_BRICKS);
		stairsBlock(KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.get(), Koratio.prefix("block/large_gingerbread_bricks"));
		slabBlock(KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.LARGE_GINGERBREAD_BRICKS.getId(), Koratio.prefix("block/large_gingerbread_bricks"));
		wallInventoryBlock(KoratioBlocks.RAW_GINGERBREAD_WALL.get(), Koratio.prefix("block/raw_gingerbread_block"));
		wallInventoryBlock(KoratioBlocks.GINGERBREAD_WALL.get(), Koratio.prefix("block/gingerbread_block"));
		wallInventoryBlock(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.get(), Koratio.prefix("block/raw_gingerbread_bricks"));
		wallInventoryBlock(KoratioBlocks.GINGERBREAD_BRICK_WALL.get(), Koratio.prefix("block/gingerbread_bricks"));
		wallInventoryBlock(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.get(), Koratio.prefix("block/raw_large_gingerbread_bricks"));
		wallInventoryBlock(KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.get(), Koratio.prefix("block/large_gingerbread_bricks"));

		eatableBlock(KoratioBlocks.MARSHMALLOW_BLOCK.get(), Koratio.prefix("block/marshmallow_block_top"), Koratio.prefix("block/marshmallow_block_side"), Koratio.prefix("block/marshmallow_block_bottom"));

		flippedCropBlock(KoratioBlocks.CEINANAS.get());
		flippedCropBlock(KoratioBlocks.UPNIPS.get());

		simpleBlock(KoratioBlocks.ANCIENT_COBBLESTONE.get());
		stairsBlock(KoratioBlocks.ANCIENT_COBBLESTONE_STAIRS.get(), Koratio.prefix("block/ancient_cobblestone"));
		slabBlock(KoratioBlocks.ANCIENT_COBBLESTONE_SLAB.get(), KoratioBlocks.ANCIENT_COBBLESTONE.getId(), Koratio.prefix("block/ancient_cobblestone"));
		wallInventoryBlock(KoratioBlocks.ANCIENT_COBBLESTONE_WALL.get(), Koratio.prefix("block/ancient_cobblestone"));
		simpleBlock(KoratioBlocks.ANCIENT_STONE.get());
		stairsBlock(KoratioBlocks.ANCIENT_STONE_STAIRS.get(), Koratio.prefix("block/ancient_stone"));
		slabBlock(KoratioBlocks.ANCIENT_STONE_SLAB.get(), KoratioBlocks.ANCIENT_STONE.getId(), Koratio.prefix("block/ancient_stone"));
		simpleBlock(KoratioBlocks.ANCIENT_STONE_BRICKS.get());
		simpleBlock(KoratioBlocks.CRACKED_ANCIENT_STONE_BRICKS.get());
		simpleBlock(KoratioBlocks.CHISELED_ANCIENT_STONE_BRICKS.get());
		stairsBlock(KoratioBlocks.ANCIENT_STONE_BRICK_STAIRS.get(), Koratio.prefix("block/ancient_stone_bricks"));
		slabBlock(KoratioBlocks.ANCIENT_STONE_BRICK_SLAB.get(), KoratioBlocks.ANCIENT_STONE_BRICKS.getId(), Koratio.prefix("block/ancient_stone_bricks"));
		simpleBlock(KoratioBlocks.POLISHED_ANCIENT_STONE.get());
		stairsBlock(KoratioBlocks.POLISHED_ANCIENT_STONE_STAIRS.get(), Koratio.prefix("block/polished_ancient_stone"));
		slabBlock(KoratioBlocks.POLISHED_ANCIENT_STONE_SLAB.get(), KoratioBlocks.POLISHED_ANCIENT_STONE.getId(), Koratio.prefix("block/polished_ancient_stone"));
		simpleBlock(KoratioBlocks.ANCIENT_STONE_TILES.get());
		stairsBlock(KoratioBlocks.ANCIENT_STONE_TILE_STAIRS.get(), Koratio.prefix("block/ancient_stone_tiles"));
		slabBlock(KoratioBlocks.ANCIENT_STONE_TILE_SLAB.get(), KoratioBlocks.ANCIENT_STONE_TILES.getId(), Koratio.prefix("block/ancient_stone_tiles"));
		wallInventoryBlock(KoratioBlocks.ANCIENT_STONE_BRICK_WALL.get(), Koratio.prefix("block/ancient_stone_bricks"));
		wallInventoryBlock(KoratioBlocks.POLISHED_ANCIENT_STONE_WALL.get(), Koratio.prefix("block/polished_ancient_stone"));
		wallInventoryBlock(KoratioBlocks.ANCIENT_STONE_TILE_WALL.get(), Koratio.prefix("block/ancient_stone_tiles"));
		
		crossBlock(KoratioBlocks.PANGO_SAPLING.get());
		logBlock(KoratioBlocks.PANGO_LOG.get());
		woodBlock(KoratioBlocks.PANGO_WOOD.get(), KoratioBlocks.PANGO_LOG.get());
		logBlock(KoratioBlocks.STRIPPED_PANGO_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_PANGO_WOOD.get(), KoratioBlocks.STRIPPED_PANGO_LOG.get());
		simpleBlock(KoratioBlocks.PANGO_PLANKS.get());
		slabBlock(KoratioBlocks.PANGO_SLAB.get(), KoratioBlocks.PANGO_PLANKS.getId(), Koratio.prefix("block/pango_planks"));
		stairsBlock(KoratioBlocks.PANGO_STAIRS.get(), Koratio.prefix("block/pango_planks"));
		leavesBlock(KoratioBlocks.PANGO_LEAVES.get());
		fenceBlock(KoratioBlocks.PANGO_FENCE.get(), Koratio.prefix("block/pango_planks"));
		fenceGateBlock(KoratioBlocks.PANGO_FENCE_GATE.get(), Koratio.prefix("block/pango_planks"));
		doorBlockInternalWithRenderType(KoratioBlocks.PANGO_DOOR.get(), Koratio.prefix("block/pango_door_bottom"), Koratio.prefix("block/pango_door_top"), Koratio.prefix("block/pango_door_sides"), Koratio.prefix("block/pango_door_top_bottom"), "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.PANGO_TRAPDOOR.get(), Koratio.prefix("block/pango_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.PANGO_BUTTON.get(), Koratio.prefix("block/pango_planks"));
		pressurePlateBlock(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), Koratio.prefix("block/pango_planks"));
		signBlock(KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_WALL_SIGN.get(), KoratioBlocks.PANGO_PLANKS.get());
		builtinEntity(KoratioBlocks.PANGO_HANGING_SIGN.get(), "koratio:block/stripped_pango_log");
		builtinEntity(KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(), "koratio:block/stripped_pango_log");
		bookshelfBlock(KoratioBlocks.PANGO_BOOKSHELF.get(), "pango");
		builtinEntity(KoratioBlocks.PANGO_CHEST.get(), "koratio:block/pango_planks");
		builtinEntity(KoratioBlocks.TRAPPED_PANGO_CHEST.get(), "koratio:block/pango_planks");

		crossBlock(KoratioBlocks.RUGONA_SAPLING.get());
		logBlock(KoratioBlocks.RUGONA_LOG.get());
		woodBlock(KoratioBlocks.RUGONA_WOOD.get(), KoratioBlocks.RUGONA_LOG.get());
		logBlock(KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_RUGONA_WOOD.get(), KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		simpleBlock(KoratioBlocks.RUGONA_PLANKS.get());
		slabBlock(KoratioBlocks.RUGONA_SLAB.get(), KoratioBlocks.RUGONA_PLANKS.getId(), Koratio.prefix("block/rugona_planks"));
		stairsBlock(KoratioBlocks.RUGONA_STAIRS.get(), Koratio.prefix("block/rugona_planks"));
		leavesBlock(KoratioBlocks.RUGONA_LEAVES.get());
		fenceBlock(KoratioBlocks.RUGONA_FENCE.get(), Koratio.prefix("block/rugona_planks"));
		fenceGateBlock(KoratioBlocks.RUGONA_FENCE_GATE.get(), Koratio.prefix("block/rugona_planks"));
		doorBlockInternalWithRenderType(KoratioBlocks.RUGONA_DOOR.get(), Koratio.prefix("block/rugona_door_bottom"), Koratio.prefix("block/rugona_door_top"), Koratio.prefix("block/rugona_door_sides"), Koratio.prefix("block/rugona_door_top_bottom"), "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.RUGONA_TRAPDOOR.get(), Koratio.prefix("block/rugona_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.RUGONA_BUTTON.get(), Koratio.prefix("block/rugona_planks"));
		pressurePlateBlock(KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), Koratio.prefix("block/rugona_planks"));
		signBlock(KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get(), KoratioBlocks.RUGONA_PLANKS.get());
		builtinEntity(KoratioBlocks.RUGONA_HANGING_SIGN.get(), "koratio:block/stripped_rugona_log");
		builtinEntity(KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(), "koratio:block/stripped_rugona_log");
		bookshelfBlock(KoratioBlocks.RUGONA_BOOKSHELF.get(), "rugona");
		builtinEntity(KoratioBlocks.RUGONA_CHEST.get(), "koratio:block/rugona_planks");
		builtinEntity(KoratioBlocks.TRAPPED_RUGONA_CHEST.get(), "koratio:block/rugona_planks");

		crossBlock(KoratioBlocks.VARESO_SAPLING.get());
		logBlock(KoratioBlocks.VARESO_LOG.get());
		woodBlock(KoratioBlocks.VARESO_WOOD.get(), KoratioBlocks.VARESO_LOG.get());
		logBlock(KoratioBlocks.STRIPPED_VARESO_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_VARESO_WOOD.get(), KoratioBlocks.STRIPPED_VARESO_LOG.get());
		simpleBlock(KoratioBlocks.VARESO_PLANKS.get());
		slabBlock(KoratioBlocks.VARESO_SLAB.get(), KoratioBlocks.VARESO_PLANKS.getId(), Koratio.prefix("block/vareso_planks"));
		stairsBlock(KoratioBlocks.VARESO_STAIRS.get(), Koratio.prefix("block/vareso_planks"));
		leavesBlock(KoratioBlocks.VARESO_LEAVES.get());
		fenceBlock(KoratioBlocks.VARESO_FENCE.get(), Koratio.prefix("block/vareso_planks"));
		fenceGateBlock(KoratioBlocks.VARESO_FENCE_GATE.get(), Koratio.prefix("block/vareso_planks"));
		doorBlockInternalWithRenderType(KoratioBlocks.VARESO_DOOR.get(), Koratio.prefix("block/vareso_door_bottom"), Koratio.prefix("block/vareso_door_top"), Koratio.prefix("block/vareso_door_sides"), Koratio.prefix("block/vareso_door_top_bottom"), "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.VARESO_TRAPDOOR.get(), Koratio.prefix("block/vareso_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.VARESO_BUTTON.get(), Koratio.prefix("block/vareso_planks"));
		pressurePlateBlock(KoratioBlocks.VARESO_PRESSURE_PLATE.get(), Koratio.prefix("block/vareso_planks"));
		signBlock(KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get(), KoratioBlocks.VARESO_PLANKS.get());
		builtinEntity(KoratioBlocks.VARESO_HANGING_SIGN.get(), "koratio:block/stripped_vareso_log");
		builtinEntity(KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(), "koratio:block/stripped_vareso_log");
		bookshelfBlock(KoratioBlocks.VARESO_BOOKSHELF.get(), "vareso");
		builtinEntity(KoratioBlocks.VARESO_CHEST.get(), "koratio:block/vareso_planks");
		builtinEntity(KoratioBlocks.TRAPPED_VARESO_CHEST.get(), "koratio:block/vareso_planks");

		crossBlock(KoratioBlocks.CANDY_SAPLING.get());
		logBlock(KoratioBlocks.CANDY_LOG.get());
		woodBlock(KoratioBlocks.CANDY_WOOD.get(), KoratioBlocks.CANDY_LOG.get());
		logBlock(KoratioBlocks.STRIPPED_CANDY_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_CANDY_WOOD.get(), KoratioBlocks.STRIPPED_CANDY_LOG.get());
		simpleBlock(KoratioBlocks.CANDY_PLANKS.get());
		slabBlock(KoratioBlocks.CANDY_SLAB.get(), KoratioBlocks.CANDY_PLANKS.getId(), Koratio.prefix("block/candy_planks"));
		stairsBlock(KoratioBlocks.CANDY_STAIRS.get(), Koratio.prefix("block/candy_planks"));
		leavesBlock(KoratioBlocks.COTTON_CANDY_LEAVES.get());
		fenceBlock(KoratioBlocks.CANDY_FENCE.get(), Koratio.prefix("block/candy_planks"));
		fenceGateBlock(KoratioBlocks.CANDY_FENCE_GATE.get(), Koratio.prefix("block/candy_planks"));
		doorBlockInternalWithRenderType(KoratioBlocks.CANDY_DOOR.get(), Koratio.prefix("block/candy_door_bottom"), Koratio.prefix("block/candy_door_top"), Koratio.prefix("block/candy_door_sides"), Koratio.prefix("block/candy_door_top_bottom"), "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.CANDY_TRAPDOOR.get(), Koratio.prefix("block/candy_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.CANDY_BUTTON.get(), Koratio.prefix("block/candy_planks"));
		pressurePlateBlock(KoratioBlocks.CANDY_PRESSURE_PLATE.get(), Koratio.prefix("block/candy_planks"));
		signBlock(KoratioBlocks.CANDY_SIGN.get(), KoratioBlocks.CANDY_WALL_SIGN.get(), KoratioBlocks.CANDY_PLANKS.get());
		builtinEntity(KoratioBlocks.CANDY_HANGING_SIGN.get(), "koratio:block/stripped_candy_log");
		builtinEntity(KoratioBlocks.CANDY_WALL_HANGING_SIGN.get(), "koratio:block/stripped_candy_log");
		bookshelfBlock(KoratioBlocks.CANDY_BOOKSHELF.get(), "candy");
		builtinEntity(KoratioBlocks.CANDY_CHEST.get(), "koratio:block/candy_planks");
		builtinEntity(KoratioBlocks.TRAPPED_CANDY_CHEST.get(), "koratio:block/candy_planks");
		
		crossBlock(KoratioBlocks.ELVEN_SAPLING.get());
		logBlock(KoratioBlocks.ELVEN_LOG.get());
		woodBlock(KoratioBlocks.ELVEN_WOOD.get(), KoratioBlocks.ELVEN_LOG.get());
		//logBlock(KoratioBlocks.STRIPPED_ELVEN_LOG.get());
		logBlock(KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get());
		logBlock(KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get());
		logBlock(KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_ELVEN_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get());
		simpleBlock(KoratioBlocks.ELVEN_PLANKS.get());
		simpleBlock(KoratioBlocks.BLUE_ELVEN_PLANKS.get());
		simpleBlock(KoratioBlocks.CYAN_ELVEN_PLANKS.get());
		simpleBlock(KoratioBlocks.GREEN_ELVEN_PLANKS.get());
		slabBlock(KoratioBlocks.ELVEN_SLAB.get(), KoratioBlocks.ELVEN_PLANKS.getId(), Koratio.prefix("block/elven_planks"));
		slabBlock(KoratioBlocks.BLUE_ELVEN_SLAB.get(), KoratioBlocks.BLUE_ELVEN_PLANKS.getId(), Koratio.prefix("block/blue_elven_planks"));
		slabBlock(KoratioBlocks.CYAN_ELVEN_SLAB.get(), KoratioBlocks.CYAN_ELVEN_PLANKS.getId(), Koratio.prefix("block/cyan_elven_planks"));
		slabBlock(KoratioBlocks.GREEN_ELVEN_SLAB.get(), KoratioBlocks.GREEN_ELVEN_PLANKS.getId(), Koratio.prefix("block/green_elven_planks"));
		//stairsBlock(KoratioBlocks.ELVEN_STAIRS.get(), Koratio.prefix("block/elven_planks"));
		stairsBlock(KoratioBlocks.BLUE_ELVEN_STAIRS.get(), Koratio.prefix("block/blue_elven_planks"));
		stairsBlock(KoratioBlocks.CYAN_ELVEN_STAIRS.get(), Koratio.prefix("block/cyan_elven_planks"));
		stairsBlock(KoratioBlocks.GREEN_ELVEN_STAIRS.get(), Koratio.prefix("block/green_elven_planks"));
		leavesBlock(KoratioBlocks.ELVEN_LEAVES.get());
		//fenceBlock(KoratioBlocks.ELVEN_FENCE.get(), Koratio.prefix("block/elven_planks"));
		fenceBlock(KoratioBlocks.BLUE_ELVEN_FENCE.get(), Koratio.prefix("block/blue_elven_planks"));
		fenceBlock(KoratioBlocks.CYAN_ELVEN_FENCE.get(), Koratio.prefix("block/cyan_elven_planks"));
		fenceBlock(KoratioBlocks.GREEN_ELVEN_FENCE.get(), Koratio.prefix("block/green_elven_planks"));
		//fenceGateBlock(KoratioBlocks.ELVEN_FENCE_GATE.get(), Koratio.prefix("block/elven_planks"));
		fenceGateBlock(KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get(), Koratio.prefix("block/blue_elven_planks"));
		fenceGateBlock(KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get(), Koratio.prefix("block/cyan_elven_planks"));
		fenceGateBlock(KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get(), Koratio.prefix("block/green_elven_planks"));
		doorBlockInternalWithRenderType(KoratioBlocks.ELVEN_DOOR.get(), Koratio.prefix("block/elven_door_bottom"), Koratio.prefix("block/elven_door_top"), Koratio.prefix("block/elven_door_sides"), Koratio.prefix("block/elven_door_top_bottom"), "cutout");
		doorBlockInternalWithRenderType(KoratioBlocks.BLUE_ELVEN_DOOR.get(), Koratio.prefix("block/blue_elven_door_bottom"), Koratio.prefix("block/blue_elven_door_top"), Koratio.prefix("block/blue_elven_door_sides"), Koratio.prefix("block/blue_elven_door_top_bottom"), "cutout");
		doorBlockInternalWithRenderType(KoratioBlocks.CYAN_ELVEN_DOOR.get(), Koratio.prefix("block/cyan_elven_door_bottom"), Koratio.prefix("block/cyan_elven_door_top"), Koratio.prefix("block/cyan_elven_door_sides"), Koratio.prefix("block/cyan_elven_door_top_bottom"), "cutout");
		doorBlockInternalWithRenderType(KoratioBlocks.GREEN_ELVEN_DOOR.get(), Koratio.prefix("block/green_elven_door_bottom"), Koratio.prefix("block/green_elven_door_top"), Koratio.prefix("block/green_elven_door_sides"), Koratio.prefix("block/green_elven_door_top_bottom"), "cutout");
		//trapdoorBlockWithRenderType(KoratioBlocks.ELVEN_TRAPDOOR.get(), Koratio.prefix("block/elven_trapdoor"), true, "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.BLUE_ELVEN_TRAPDOOR.get(), Koratio.prefix("block/blue_elven_trapdoor"), true, "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.CYAN_ELVEN_TRAPDOOR.get(), Koratio.prefix("block/cyan_elven_trapdoor"), true, "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.GREEN_ELVEN_TRAPDOOR.get(), Koratio.prefix("block/green_elven_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.ELVEN_BUTTON.get(), Koratio.prefix("block/elven_planks"));
		buttonBlock(KoratioBlocks.BLUE_ELVEN_BUTTON.get(), Koratio.prefix("block/blue_elven_planks"));
		buttonBlock(KoratioBlocks.CYAN_ELVEN_BUTTON.get(), Koratio.prefix("block/cyan_elven_planks"));
		buttonBlock(KoratioBlocks.GREEN_ELVEN_BUTTON.get(), Koratio.prefix("block/green_elven_planks"));
		//pressurePlateBlock(KoratioBlocks.ELVEN_PRESSURE_PLATE.get(), Koratio.prefix("block/elven_planks"));
		pressurePlateBlock(KoratioBlocks.BLUE_ELVEN_PRESSURE_PLATE.get(), Koratio.prefix("block/blue_elven_planks"));
		pressurePlateBlock(KoratioBlocks.CYAN_ELVEN_PRESSURE_PLATE.get(), Koratio.prefix("block/cyan_elven_planks"));
		pressurePlateBlock(KoratioBlocks.GREEN_ELVEN_PRESSURE_PLATE.get(), Koratio.prefix("block/green_elven_planks"));
		signBlock(KoratioBlocks.ELVEN_SIGN.get(), KoratioBlocks.ELVEN_WALL_SIGN.get(), KoratioBlocks.ELVEN_PLANKS.get());
		signBlock(KoratioBlocks.BLUE_ELVEN_SIGN.get(), KoratioBlocks.BLUE_ELVEN_WALL_SIGN.get(), KoratioBlocks.BLUE_ELVEN_PLANKS.get());
		signBlock(KoratioBlocks.CYAN_ELVEN_SIGN.get(), KoratioBlocks.CYAN_ELVEN_WALL_SIGN.get(), KoratioBlocks.CYAN_ELVEN_PLANKS.get());
		signBlock(KoratioBlocks.GREEN_ELVEN_SIGN.get(), KoratioBlocks.GREEN_ELVEN_WALL_SIGN.get(), KoratioBlocks.GREEN_ELVEN_PLANKS.get());
		builtinEntity(KoratioBlocks.ELVEN_HANGING_SIGN.get(), "koratio:block/stripped_elven_log");
		builtinEntity(KoratioBlocks.ELVEN_WALL_HANGING_SIGN.get(), "koratio:block/stripped_elven_log");
		builtinEntity(KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get(), "koratio:block/stripped_blue_elven_log");
		builtinEntity(KoratioBlocks.BLUE_ELVEN_WALL_HANGING_SIGN.get(), "koratio:block/stripped_blue_elven_log");
		builtinEntity(KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get(), "koratio:block/stripped_cyan_elven_log");
		builtinEntity(KoratioBlocks.CYAN_ELVEN_WALL_HANGING_SIGN.get(), "koratio:block/stripped_cyan_elven_log");
		builtinEntity(KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get(), "koratio:block/stripped_green_elven_log");
		builtinEntity(KoratioBlocks.GREEN_ELVEN_WALL_HANGING_SIGN.get(), "koratio:block/stripped_green_elven_log");
		//bookshelfBlock(KoratioBlocks.ELVEN_BOOKSHELF.get(), "elven");
		simpleBlock(KoratioBlocks.ELVEN_BOOKSHELF.get(), models().cubeBottomTop(BuiltInRegistries.BLOCK.getKey(KoratioBlocks.ELVEN_BOOKSHELF.get()).getPath(), Koratio.prefix("block/elven_bookshelf"), Koratio.prefix("block/blue_elven_planks"), Koratio.prefix("block/green_elven_planks")));
		bookshelfBlock(KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get(), "blue_elven");
		bookshelfBlock(KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get(), "cyan_elven");
		bookshelfBlock(KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get(), "green_elven");
		builtinEntity(KoratioBlocks.ELVEN_CHEST.get(), "koratio:block/elven_planks");
		builtinEntity(KoratioBlocks.TRAPPED_ELVEN_CHEST.get(), "koratio:block/elven_planks");
		builtinEntity(KoratioBlocks.BLUE_ELVEN_CHEST.get(), "koratio:block/blue_elven_planks");
		builtinEntity(KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get(), "koratio:block/blue_elven_planks");
		builtinEntity(KoratioBlocks.CYAN_ELVEN_CHEST.get(), "koratio:block/cyan_elven_planks");
		builtinEntity(KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get(), "koratio:block/cyan_elven_planks");
		builtinEntity(KoratioBlocks.GREEN_ELVEN_CHEST.get(), "koratio:block/green_elven_planks");
		builtinEntity(KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get(), "koratio:block/green_elven_planks");

		crossBlock(KoratioBlocks.PURPLE_MUSHROOM.get());
		crossBlock(KoratioBlocks.GREEN_MUSHROOM.get());
		
		multifaceBlock(KoratioBlocks.GILDED_VINES.get());
		
		mushroomBlock(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get());
		mushroomBlock(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get());
		
		tintedPottedFlower(KoratioBlocks.POTTED_RAINBOW_ROSE.get());
		tintedPottedFlower(KoratioBlocks.POTTED_RAINBOW_ALLIUM.get());
		tintedPottedFlower(KoratioBlocks.POTTED_RAINBOW_LILY_OF_THE_VALLEY.get());

		leafPaneBlock(KoratioBlocks.OAK_LEAF_PANE.get(), Blocks.OAK_LEAVES);
		leafPaneBlock(KoratioBlocks.SPRUCE_LEAF_PANE.get(), Blocks.SPRUCE_LEAVES);
		leafPaneBlock(KoratioBlocks.BIRCH_LEAF_PANE.get(), Blocks.BIRCH_LEAVES);
		leafPaneBlock(KoratioBlocks.JUNGLE_LEAF_PANE.get(), Blocks.JUNGLE_LEAVES);
		leafPaneBlock(KoratioBlocks.ACACIA_LEAF_PANE.get(), Blocks.ACACIA_LEAVES);
		leafPaneBlock(KoratioBlocks.DARK_OAK_LEAF_PANE.get(), Blocks.DARK_OAK_LEAVES);
		leafPaneBlock(KoratioBlocks.MANGROVE_LEAF_PANE.get(), Blocks.MANGROVE_LEAVES);
		leafPaneBlock(KoratioBlocks.AZALEA_LEAF_PANE.get(), Blocks.AZALEA_LEAVES);
		leafPaneBlock(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.get(), Blocks.FLOWERING_AZALEA_LEAVES);
		leafPaneBlock(KoratioBlocks.CHERRY_LEAF_PANE.get(), Blocks.CHERRY_LEAVES);

		leafPaneBlock(KoratioBlocks.PANGO_LEAF_PANE.get(), KoratioBlocks.PANGO_LEAVES.get());
		leafPaneBlock(KoratioBlocks.RUGONA_LEAF_PANE.get(), KoratioBlocks.RUGONA_LEAVES.get());
		leafPaneBlock(KoratioBlocks.VARESO_LEAF_PANE.get(), KoratioBlocks.VARESO_LEAVES.get());
		leafPaneBlock(KoratioBlocks.COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.COTTON_CANDY_LEAVES.get());
		//leafPaneBlock(KoratioBlocks.ELVEN_LEAF_PANE.get(), KoratioBlocks.ELVEN_LEAVES.get());

		pottedPlant(KoratioBlocks.POTTED_COOKIE_FLOWER.get());
		pottedPlant(KoratioBlocks.POTTED_PANGO_SAPLING.get());
		pottedPlant(KoratioBlocks.POTTED_RUGONA_SAPLING.get());
		pottedPlant(KoratioBlocks.POTTED_VARESO_SAPLING.get());
		pottedPlant(KoratioBlocks.POTTED_ELVEN_SAPLING.get());
		pottedPlant(KoratioBlocks.POTTED_PURPLE_MUSHROOM.get());
		pottedPlant(KoratioBlocks.POTTED_GREEN_MUSHROOM.get());

		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_OAK_DOOR.get(), Koratio.prefix("block/tall_oak_door_bottom"), Koratio.prefix("block/tall_oak_door_middle"), Koratio.prefix("block/tall_oak_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_SPRUCE_DOOR.get(), Koratio.prefix("block/tall_spruce_door_bottom"), Koratio.prefix("block/tall_spruce_door_middle"), Koratio.prefix("block/tall_spruce_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_BIRCH_DOOR.get(), Koratio.prefix("block/tall_birch_door_bottom"), Koratio.prefix("block/tall_birch_door_middle"), Koratio.prefix("block/tall_birch_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_JUNGLE_DOOR.get(), Koratio.prefix("block/tall_jungle_door_bottom"), Koratio.prefix("block/tall_jungle_door_middle"), Koratio.prefix("block/tall_jungle_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_ACACIA_DOOR.get(), Koratio.prefix("block/tall_acacia_door_bottom"), Koratio.prefix("block/tall_acacia_door_middle"), Koratio.prefix("block/tall_acacia_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_DARK_OAK_DOOR.get(), Koratio.prefix("block/tall_dark_oak_door_bottom"), Koratio.prefix("block/tall_dark_oak_door_middle"), Koratio.prefix("block/tall_dark_oak_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_MANGROVE_DOOR.get(), Koratio.prefix("block/tall_mangrove_door_bottom"), Koratio.prefix("block/tall_mangrove_door_middle"), Koratio.prefix("block/tall_mangrove_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_CHERRY_DOOR.get(), Koratio.prefix("block/tall_cherry_door_bottom"), Koratio.prefix("block/tall_cherry_door_middle"), Koratio.prefix("block/tall_cherry_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_BAMBOO_DOOR.get(), Koratio.prefix("block/tall_bamboo_door_bottom"), Koratio.prefix("block/tall_bamboo_door_middle"), Koratio.prefix("block/tall_bamboo_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_CRIMSON_DOOR.get(), Koratio.prefix("block/tall_crimson_door_bottom"), Koratio.prefix("block/tall_crimson_door_middle"), Koratio.prefix("block/tall_crimson_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_WARPED_DOOR.get(), Koratio.prefix("block/tall_warped_door_bottom"), Koratio.prefix("block/tall_warped_door_middle"), Koratio.prefix("block/tall_warped_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_IRON_DOOR.get(), Koratio.prefix("block/tall_iron_door_bottom"), Koratio.prefix("block/tall_iron_door_middle"), Koratio.prefix("block/tall_iron_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_COPPER_DOOR.get(), Koratio.prefix("block/tall_copper_door_bottom"), Koratio.prefix("block/tall_copper_door_middle"), Koratio.prefix("block/tall_copper_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get(), Koratio.prefix("block/tall_exposed_copper_door_bottom"), Koratio.prefix("block/tall_exposed_copper_door_middle"), Koratio.prefix("block/tall_exposed_copper_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get(), Koratio.prefix("block/tall_oxidized_copper_door_bottom"), Koratio.prefix("block/tall_oxidized_copper_door_middle"), Koratio.prefix("block/tall_oxidized_copper_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get(), Koratio.prefix("block/tall_weathered_copper_door_bottom"), Koratio.prefix("block/tall_weathered_copper_door_middle"), Koratio.prefix("block/tall_weathered_copper_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_WAXED_COPPER_DOOR.get(), Koratio.prefix("block/tall_copper_door_bottom"), Koratio.prefix("block/tall_copper_door_middle"), Koratio.prefix("block/tall_copper_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get(), Koratio.prefix("block/tall_exposed_copper_door_bottom"), Koratio.prefix("block/tall_exposed_copper_door_middle"), Koratio.prefix("block/tall_exposed_copper_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get(), Koratio.prefix("block/tall_oxidized_copper_door_bottom"), Koratio.prefix("block/tall_oxidized_copper_door_middle"), Koratio.prefix("block/tall_oxidized_copper_door_top"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get(), Koratio.prefix("block/tall_weathered_copper_door_bottom"), Koratio.prefix("block/tall_weathered_copper_door_middle"), Koratio.prefix("block/tall_weathered_copper_door_top"), "cutout");

		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_PANGO_DOOR.get(), Koratio.prefix("block/tall_pango_door_bottom"), Koratio.prefix("block/tall_pango_door_middle"), Koratio.prefix("block/tall_pango_door_top"), Koratio.prefix("block/pango_door_sides"), Koratio.prefix("block/pango_door_top_bottom"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_RUGONA_DOOR.get(), Koratio.prefix("block/tall_rugona_door_bottom"), Koratio.prefix("block/tall_rugona_door_middle"), Koratio.prefix("block/tall_rugona_door_top"), Koratio.prefix("block/rugona_door_sides"), Koratio.prefix("block/rugona_door_top_bottom"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_VARESO_DOOR.get(), Koratio.prefix("block/tall_vareso_door_bottom"), Koratio.prefix("block/tall_vareso_door_middle"), Koratio.prefix("block/tall_vareso_door_top"), Koratio.prefix("block/vareso_door_sides"), Koratio.prefix("block/vareso_door_top_bottom"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_ELVEN_DOOR.get(), Koratio.prefix("block/tall_elven_door_bottom"), Koratio.prefix("block/tall_elven_door_middle"), Koratio.prefix("block/tall_elven_door_top"), Koratio.prefix("block/elven_door_sides"), Koratio.prefix("block/elven_door_top_bottom"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_BLUE_ELVEN_DOOR.get(), Koratio.prefix("block/tall_blue_elven_door_bottom"), Koratio.prefix("block/tall_blue_elven_door_middle"), Koratio.prefix("block/tall_blue_elven_door_top"), Koratio.prefix("block/blue_elven_door_sides"), Koratio.prefix("block/blue_elven_door_top_bottom"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_CYAN_ELVEN_DOOR.get(), Koratio.prefix("block/tall_cyan_elven_door_bottom"), Koratio.prefix("block/tall_cyan_elven_door_middle"), Koratio.prefix("block/tall_cyan_elven_door_top"), Koratio.prefix("block/cyan_elven_door_sides"), Koratio.prefix("block/cyan_elven_door_top_bottom"), "cutout");
		tallDoorBlockInternalWithRenderType(KoratioBlocks.TALL_GREEN_ELVEN_DOOR.get(), Koratio.prefix("block/tall_green_elven_door_bottom"), Koratio.prefix("block/tall_green_elven_door_middle"), Koratio.prefix("block/tall_green_elven_door_top"), Koratio.prefix("block/green_elven_door_sides"), Koratio.prefix("block/green_elven_door_top_bottom"), "cutout");
	}
	
	protected void wallInventoryBlock(WallBlock block, ResourceLocation texture) {
		wallBlock(block, texture);
		simpleBlockItem(block, models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_inventory", "minecraft:block/wall_inventory").texture("wall", texture));
	}

	protected void bookshelfBlock(Block block, String name) {
		simpleBlock(block, models().cubeColumn(BuiltInRegistries.BLOCK.getKey(block).getPath(), Koratio.prefix("block/"+name+"_bookshelf"), Koratio.prefix("block/"+name+"_planks")));
	}
	
	protected void woodBlock(RotatedPillarBlock block, Block log) {
		axisBlock(block, models().cubeColumn(BuiltInRegistries.BLOCK.getKey(block).getPath(), getBlockPathInFolder(log), getBlockPathInFolder(log)), models().cubeColumn(BuiltInRegistries.BLOCK.getKey(block).getPath(), getBlockPathInFolder(log), getBlockPathInFolder(log)));
	}

	private void doorBlockInternalWithRenderType(DoorBlock block, ResourceLocation bottom, ResourceLocation top, ResourceLocation sides, ResourceLocation top_bottom, String renderType) {
		ModelFile bottomLeft = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_left", Koratio.prefix("block/template/door_bottom_left")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile bottomLeftOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_left_open", Koratio.prefix("block/template/door_bottom_left_open")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile bottomRight = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_right", Koratio.prefix("block/template/door_bottom_right")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile bottomRightOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_right_open", Koratio.prefix("block/template/door_bottom_right_open")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile topLeft = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_top_left", Koratio.prefix("block/template/door_top_left")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile topLeftOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_top_left_open", Koratio.prefix("block/template/door_top_left_open")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile topRight = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_top_right", Koratio.prefix("block/template/door_top_right")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile topRightOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_top_right_open", Koratio.prefix("block/template/door_top_right_open")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		doorBlock(block, bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
	}

	private void tallDoorBlockInternalWithRenderType(TallDoorBlock block, ResourceLocation bottom, ResourceLocation middle, ResourceLocation top, String renderType) {
		ModelFile bottomLeft = models().doorBottomLeft(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_left", bottom, top).renderType(renderType);
		ModelFile bottomLeftOpen = models().doorBottomLeftOpen(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_left_open", bottom, top).renderType(renderType);
		ModelFile bottomRight = models().doorBottomRight(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_right", bottom, top).renderType(renderType);
		ModelFile bottomRightOpen = models().doorBottomRightOpen(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_right_open", bottom, top).renderType(renderType);
		ModelFile middleLeft = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_middle_left", Koratio.prefix("block/template/old_door_middle_left")).texture("middle", middle).renderType(renderType);
		ModelFile middleLeftOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_middle_left_open", Koratio.prefix("block/template/old_door_middle_left_open")).texture("middle", middle).renderType(renderType);
		ModelFile middleRight = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_middle_right", Koratio.prefix("block/template/old_door_middle_right")).texture("middle", middle).renderType(renderType);
		ModelFile middleRightOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_middle_right_open", Koratio.prefix("block/template/old_door_middle_right_open")).texture("middle", middle).renderType(renderType);
		ModelFile topLeft = models().doorTopLeft(BuiltInRegistries.BLOCK.getKey(block) + "_top_left", bottom, top).renderType(renderType);
		ModelFile topLeftOpen = models().doorTopLeftOpen(BuiltInRegistries.BLOCK.getKey(block) + "_top_left_open", bottom, top).renderType(renderType);
		ModelFile topRight = models().doorTopRight(BuiltInRegistries.BLOCK.getKey(block) + "_top_right", bottom, top).renderType(renderType);
		ModelFile topRightOpen = models().doorTopRightOpen(BuiltInRegistries.BLOCK.getKey(block) + "_top_right_open", bottom, top).renderType(renderType);
		tallDoorBlock(block, bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, middleLeft, middleLeftOpen, middleRight, middleRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
	}

	private void tallDoorBlockInternalWithRenderType(TallDoorBlock block, ResourceLocation bottom, ResourceLocation middle, ResourceLocation top, ResourceLocation sides, ResourceLocation top_bottom, String renderType) {
		ModelFile bottomLeft = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_left", Koratio.prefix("block/template/door_bottom_left")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile bottomLeftOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_left_open", Koratio.prefix("block/template/door_bottom_left_open")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile bottomRight = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_right", Koratio.prefix("block/template/door_bottom_right")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile bottomRightOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_bottom_right_open", Koratio.prefix("block/template/door_bottom_right_open")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile middleLeft = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_middle_left", Koratio.prefix("block/template/door_middle_left")).texture("side", sides).texture("front_m", middle).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile middleLeftOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_middle_left_open", Koratio.prefix("block/template/door_middle_left_open")).texture("side", sides).texture("front_m", middle).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile middleRight = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_middle_right", Koratio.prefix("block/template/door_middle_right")).texture("side", sides).texture("front_m", middle).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile middleRightOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_middle_right_open", Koratio.prefix("block/template/door_middle_right_open")).texture("side", sides).texture("front_m", middle).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile topLeft = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_top_left", Koratio.prefix("block/template/door_top_left")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile topLeftOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_top_left_open", Koratio.prefix("block/template/door_top_left_open")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile topRight = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_top_right", Koratio.prefix("block/template/door_top_right")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		ModelFile topRightOpen = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) + "_top_right_open", Koratio.prefix("block/template/door_top_right_open")).texture("side", sides).texture("front_b", bottom).texture("front_t", top).texture("top", top_bottom).renderType(renderType);
		tallDoorBlock(block, bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, middleLeft, middleLeftOpen, middleRight, middleRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
	}

	public void eatableBlock(EatableBlock block, ResourceLocation top, ResourceLocation side, ResourceLocation bottom) {
		getVariantBuilder(block).forAllStates(state -> {
			ModelFile full = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block)+"", Koratio.prefix("block/template/eatable_full")).texture("top", top).texture("side", side).texture("bottom", bottom);
			ModelFile[] slices = new ModelFile[7];
			slices[0] = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block)+"_slice_1", Koratio.prefix("block/template/eatable_slice_1")).texture("top", top).texture("side", side).texture("bottom", bottom);
			slices[1] = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block)+"_slice_2", Koratio.prefix("block/template/eatable_slice_2")).texture("top", top).texture("side", side).texture("bottom", bottom);
			slices[2] = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block)+"_slice_3", Koratio.prefix("block/template/eatable_slice_3")).texture("top", top).texture("side", side).texture("bottom", bottom);
			slices[3] = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block)+"_slice_4", Koratio.prefix("block/template/eatable_slice_4")).texture("top", top).texture("side", side).texture("bottom", bottom);
			slices[4] = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block)+"_slice_5", Koratio.prefix("block/template/eatable_slice_5")).texture("top", top).texture("side", side).texture("bottom", bottom);
			slices[5] = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block)+"_slice_6", Koratio.prefix("block/template/eatable_slice_6")).texture("top", top).texture("side", side).texture("bottom", bottom);
			slices[6] = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block)+"_slice_7", Koratio.prefix("block/template/eatable_slice_7")).texture("top", top).texture("side", side).texture("bottom", bottom);
			ModelFile model;
			if(state.getValue(EatableBlock.BITES) == 0) {
				model = full;
			} else {
				model = slices[state.getValue(EatableBlock.BITES)-1];
			}
			return ConfiguredModel.builder().modelFile(model)
					.build();
		});
    }

	public void tallDoorBlock(TallDoorBlock block, ModelFile bottomLeft, ModelFile bottomLeftOpen, ModelFile bottomRight, ModelFile bottomRightOpen, ModelFile middleLeft, ModelFile middleLeftOpen, ModelFile middleRight, ModelFile middleRightOpen, ModelFile topLeft, ModelFile topLeftOpen, ModelFile topRight, ModelFile topRightOpen) {
		getVariantBuilder(block).forAllStatesExcept(state -> {
			int yRot = ((int) state.getValue(TallDoorBlock.FACING).toYRot()) + 90;
			boolean right = state.getValue(TallDoorBlock.HINGE) == DoorHingeSide.RIGHT;
			boolean open = state.getValue(TallDoorBlock.OPEN);
			boolean lower = state.getValue(TallDoorBlock.PART) == TripleBlockPart.LOWER;
			boolean middle = state.getValue(TallDoorBlock.PART) == TripleBlockPart.MIDDLE;
			if (open) {
				yRot += 90;
			}
			if (right && open) {
				yRot += 180;
			}
			yRot %= 360;

			ModelFile model = null;
			if (lower && right && open) {
				model = bottomRightOpen;
			} else if (lower && !right && open) {
				model = bottomLeftOpen;
			}
			if (lower && right && !open) {
				model = bottomRight;
			} else if (lower && !right && !open) {
				model = bottomLeft;
			}
			if (middle && right && open) {
				model = middleRightOpen;
			} else if (middle && !right && open) {
				model = middleLeftOpen;
			}
			if (middle && right && !open) {
				model = middleRight;
			} else if (middle && !right && !open) {
				model = middleLeft;
			}
			if (!lower && !middle && right && open) {
				model = topRightOpen;
			} else if (!lower && !middle && !right && open) {
				model = topLeftOpen;
			}
			if (!lower && !middle && right && !open) {
				model = topRight;
			} else if (!lower && !middle && !right && !open) {
				model = topLeft;
			}

			return ConfiguredModel.builder().modelFile(model)
					.rotationY(yRot)
					.build();
		}, DoorBlock.POWERED);
	}

    protected void cropBlock(Block block) {
		getVariantBuilder(block)
				.forAllStates(state -> {
					int age = state.getValue(CropBlock.AGE);
					if (age == 7) age = 3;
					else if (age <= 1) age = 0;
					else if (age <= 3) age = 1;
					else if (age <= 6) age = 2;
					return ConfiguredModel.builder()
							.uvLock(true)
							.modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_stage"+age, "block/crop").renderType("cutout").texture("crop", getBlockPathInFolder(block, "_stage"+age)))
							.build();
				});
	}

	protected void flippedCropBlock(Block block) {
		getVariantBuilder(block)
				.forAllStates(state -> {
					int age = state.getValue(FlippedCropBlock.AGE);
					if (age == 7) age = 3;
					else if (age <= 1) age = 0;
					else if (age <= 3) age = 1;
					else if (age <= 6) age = 2;
					return ConfiguredModel.builder()
							.uvLock(true)
							.modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_stage"+age, "koratio:block/flipped_crop").renderType("cutout").texture("crop", getBlockPathInFolder(block, "_stage"+age)))
							.build();
				});
	}

	protected void leafPaneBlock(IronBarsBlock block, Block texture) {
		paneBlockWithRenderType(block, getBlockPathInFolder(texture), getBlockPathInFolder(texture), "cutout_mipped");
		ModelFile post = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) +"_post", "koratio:block/template_glass_pane_post")
				.texture("pane", getBlockPathInFolder(texture))
				.texture("edge", getBlockPathInFolder(texture)).renderType("cutout_mipped");
		ModelFile side = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) +"_side", "koratio:block/template_glass_pane_side")
				.texture("pane", getBlockPathInFolder(texture))
				.texture("edge", getBlockPathInFolder(texture)).renderType("cutout_mipped");
		ModelFile sideAlt = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) +"_side_alt", "koratio:block/template_glass_pane_side_alt")
				.texture("pane", getBlockPathInFolder(texture))
				.texture("edge", getBlockPathInFolder(texture)).renderType("cutout_mipped");
		ModelFile noSide = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) +"_noside", "koratio:block/template_glass_pane_noside")
				.texture("pane", getBlockPathInFolder(texture)).renderType("cutout_mipped");
		ModelFile noSideAlt = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block) +"_noside_alt", "koratio:block/template_glass_pane_noside_alt")
				.texture("pane", getBlockPathInFolder(texture)).renderType("cutout_mipped");
		paneBlock(block, post, side, sideAlt, noSide, noSideAlt);
	}
	
	protected void leavesBlock(Block block) {
		simpleBlock(block, models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "block/leaves").texture("all", getBlockPathInFolder(block)));
	}

	protected void flippedFarmlandBlock(Block block) {
		ModelFile dry = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/block").texture("dirt", getBlockPathInFolder(Blocks.DIRT)).texture("particle", "#dirt").texture("farmland", getBlockPathInFolder(Blocks.FARMLAND))
				.element().from(0, 1, 0).to(16, 16, 16)
				.face(Direction.NORTH).texture("#dirt").end()
				.face(Direction.SOUTH).texture("#dirt").end()
				.face(Direction.EAST).texture("#dirt").end()
				.face(Direction.WEST).texture("#dirt").end()
				.face(Direction.UP).texture("#dirt").end()
				.face(Direction.DOWN).texture("#farmland").end().end();

		ModelFile moist = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_moist", "minecraft:block/block").texture("dirt", getBlockPathInFolder(Blocks.DIRT)).texture("particle", "#dirt").texture("farmland", "minecraft:block/farmland_moist")
				.element().from(0, 1, 0).to(16, 16, 16)
				.face(Direction.NORTH).texture("#dirt").end()
				.face(Direction.SOUTH).texture("#dirt").end()
				.face(Direction.EAST).texture("#dirt").end()
				.face(Direction.WEST).texture("#dirt").end()
				.face(Direction.UP).texture("#dirt").end()
				.face(Direction.DOWN).texture("#farmland").end().end();

		getVariantBuilder(block).forAllStates(state -> {
			ModelFile model = dry;
			if (state.getValue(FlippedFarmBlock.MOISTURE) >= 7) {
				model = moist;
			}
			return ConfiguredModel.builder()
					.modelFile(model)
					.uvLock(true)
					.build();
		});
	}
	
	protected void multifaceBlock(Block block) {
		MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
		ModelFile model = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/block").ao(false).renderType("cutout").texture("vine", getBlockPathInFolder(block)).texture("particle", "#vine")
				.element().from(0, 0, 0.8F).to(16, 16, 0.8F).shade(false)
				.face(Direction.NORTH).texture("#vine").uvs(16, 0, 0, 16).end()
				.face(Direction.SOUTH).texture("#vine").uvs(0, 0, 16, 16).end().end();
		builder.part().modelFile(model).addModel().condition(VineBlock.NORTH, true);
		builder.part().modelFile(model).addModel().condition(VineBlock.SOUTH, false).condition(VineBlock.EAST, false).condition(VineBlock.WEST, false).condition(VineBlock.UP, false).condition(VineBlock.NORTH, false);
		builder.part().modelFile(model).rotationY(90).addModel().condition(VineBlock.EAST, true);
		builder.part().modelFile(model).rotationY(90).addModel().condition(VineBlock.SOUTH, false).condition(VineBlock.EAST, false).condition(VineBlock.WEST, false).condition(VineBlock.UP, false).condition(VineBlock.NORTH, false);
		builder.part().modelFile(model).rotationY(180).addModel().condition(VineBlock.SOUTH, true);
		builder.part().modelFile(model).rotationY(180).addModel().condition(VineBlock.SOUTH, false).condition(VineBlock.EAST, false).condition(VineBlock.WEST, false).condition(VineBlock.UP, false).condition(VineBlock.NORTH, false);
		builder.part().modelFile(model).rotationY(270).addModel().condition(VineBlock.WEST, true);
		builder.part().modelFile(model).rotationY(270).addModel().condition(VineBlock.SOUTH, false).condition(VineBlock.EAST, false).condition(VineBlock.WEST, false).condition(VineBlock.UP, false).condition(VineBlock.NORTH, false);
		builder.part().modelFile(model).rotationX(270).addModel().condition(VineBlock.UP, true);
		builder.part().modelFile(model).rotationX(270).addModel().condition(VineBlock.SOUTH, false).condition(VineBlock.EAST, false).condition(VineBlock.WEST, false).condition(VineBlock.UP, false).condition(VineBlock.NORTH, false);
	}
	
	protected void signBlock(StandingSignBlock sign, WallSignBlock wallSign, Block particle) {
		signBlock(sign, wallSign, getBlockPathInFolder(particle));
	}

	protected void glazedBlock(DeferredBlock<?> block) {
		GlazedModelLoader.GingerbreadBlockModelLoaderBuilder loaderBuilder = models().getBuilder(block.getId().toString()).customLoader(GlazedModelLoader.GingerbreadBlockModelLoaderBuilder::new);
		loaderBuilder.setBaseModel(ResourceLocation.parse(getBlockPathInFolder(block.get())+"_base"));
		models().withExistingParent(block.getId().getPath()+"_base", "minecraft:block/cube_all").texture("all", getBlockPathInFolder(block.get()));
		simpleBlock(block.get(), loaderBuilder.end());
	}

	protected void cauldronBlock(Block block, String fluid) {
		simpleBlock(block, models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/template_cauldron_full").texture("bottom", "minecraft:block/cauldron_bottom").texture("content", fluid).texture("inside", "minecraft:block/cauldron_inner").texture("particle", "minecraft:block/cauldron_side").texture("side", "minecraft:block/cauldron_side").texture("top", "minecraft:block/cauldron_top"));
	}
	
	protected void crossBlock(Block block) {
		simpleBlock(block, models().cross(BuiltInRegistries.BLOCK.getKey(block).getPath(), getBlockPathInFolder(block)).renderType("cutout"));
	}
	
	protected void tintedCrossBlock(Block block, String extra) {
		simpleBlock(block, models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), Koratio.prefix("block/tinted_cross")).texture("cross", getBlockPathInFolder(block, extra)).texture("tinted_cross", getBlockPathInFolder(block)).renderType("cutout"));
	}
	
	protected void pottedPlant(FlowerPotBlock block) {
		simpleBlock(block, models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/flower_pot_cross").texture("plant", getBlockPathInFolder(block.getPotted())).renderType("cutout"));
	}
	
	protected void tintedPottedFlower(FlowerPotBlock block) {
		simpleBlock(block, models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), Koratio.prefix("block/tinted_flower_pot_cross")).texture("plant", getBlockPathInFolder(block.getPotted(), "_stem")).texture("plant_tinted", getBlockPathInFolder(block.getPotted())).renderType("cutout"));
	}
	
	protected void mushroomBlock(Block block) {
		MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
		builder.part().modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block))).rotationX(270).uvLock(true).addModel()
		.condition(HugeMushroomBlock.UP, true);
		builder.part().modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block))).rotationX(90).uvLock(true).addModel()
		.condition(HugeMushroomBlock.DOWN, true);
		builder.part().modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block))).addModel()
		.condition(HugeMushroomBlock.NORTH, true);
		builder.part().modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block))).rotationY(90).uvLock(true).addModel()
		.condition(HugeMushroomBlock.EAST, true);
		builder.part().modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block))).rotationY(180).uvLock(true).addModel()
		.condition(HugeMushroomBlock.SOUTH, true);
		builder.part().modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block))).rotationY(270).uvLock(true).addModel()
		.condition(HugeMushroomBlock.WEST, true);
		builder.part().modelFile(models().withExistingParent("minecraft:block/mushroom_block_inside", "minecraft:block/template_single_face").texture("texture", "minecraft:block/mushroom_block_inside")).rotationX(270).uvLock(true).addModel()
		.condition(HugeMushroomBlock.UP, false);
		builder.part().modelFile(models().withExistingParent("minecraft:block/mushroom_block_inside", "minecraft:block/template_single_face").texture("texture", "minecraft:block/mushroom_block_inside")).rotationX(90).uvLock(true).addModel()
		.condition(HugeMushroomBlock.DOWN, false);
		builder.part().modelFile(models().withExistingParent("minecraft:block/mushroom_block_inside", "minecraft:block/template_single_face").texture("texture", "minecraft:block/mushroom_block_inside")).addModel()
		.condition(HugeMushroomBlock.NORTH, false);
		builder.part().modelFile(models().withExistingParent("minecraft:block/mushroom_block_inside", "minecraft:block/template_single_face").texture("texture", "minecraft:block/mushroom_block_inside")).rotationY(90).uvLock(true).addModel()
		.condition(HugeMushroomBlock.EAST, false);
		builder.part().modelFile(models().withExistingParent("minecraft:block/mushroom_block_inside", "minecraft:block/template_single_face").texture("texture", "minecraft:block/mushroom_block_inside")).rotationY(180).uvLock(true).addModel()
		.condition(HugeMushroomBlock.SOUTH, false);
		builder.part().modelFile(models().withExistingParent("minecraft:block/mushroom_block_inside", "minecraft:block/template_single_face").texture("texture", "minecraft:block/mushroom_block_inside")).rotationY(270).uvLock(true).addModel()
		.condition(HugeMushroomBlock.WEST, false);
	}
	
	protected void builtinEntity(Block block, String particle) {
		simpleBlock(block, models().getBuilder(BuiltInRegistries.BLOCK.getKey(block).getPath())
				.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
				.texture("particle", particle));
	}
	
	private ResourceLocation getBlockPathInFolder(Block block, String extra) {
		if (BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals("minecraft")) {
			return ResourceLocation.withDefaultNamespace("block" +"/"+BuiltInRegistries.BLOCK.getKey(block).getPath()+extra);
		} else {
			return Koratio.prefix("block" +"/"+BuiltInRegistries.BLOCK.getKey(block).getPath()+extra);
		}
	}
	
	private ResourceLocation getBlockPathInFolder(Block block) {
		return getBlockPathInFolder(block, "");
	}
}