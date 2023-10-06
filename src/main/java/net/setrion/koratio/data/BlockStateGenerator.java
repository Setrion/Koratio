package net.setrion.koratio.data;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.block.FantasiaPortalBlock;
import net.setrion.koratio.world.level.block.StandingSignBlock;
import net.setrion.koratio.world.level.block.WallSignBlock;

public class BlockStateGenerator extends BlockStateProvider {

	public BlockStateGenerator(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, Koratio.MOD_ID, fileHelper);
	}

	@Override
	protected void registerStatesAndModels() {		
		ModelFile portal_ns = models().getExistingFile(Koratio.prefix("block/fantasia_portal_ns"));
		ModelFile portal_ew = models().getExistingFile(Koratio.prefix("block/fantasia_portal_ew"));
		
		getVariantBuilder(KoratioBlocks.FANTASIA_PORTAL.get())
        .partialState().with(FantasiaPortalBlock.AXIS, Axis.X)
            .modelForState().modelFile(portal_ns).addModel()
        .partialState().with(FantasiaPortalBlock.AXIS, Axis.Z)
            .modelForState().modelFile(portal_ew).addModel();
		
		simpleBlock(KoratioBlocks.MINIATURE_FANTASIA_PORTAL.get(), models().getExistingFile(Koratio.prefix("block/miniature/fantasia_portal")));
		
		simpleBlock(KoratioBlocks.DECRYPTING_TABLE.get(), models().cube(KoratioBlocks.DECRYPTING_TABLE.getKey().location().getPath(), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "block", "_bottom"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "block", "_top"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "block", "_front"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "block", "_side"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "block", "_front"), getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "block", "_side")).texture("particle", getBlockPathInFolder(KoratioBlocks.DECRYPTING_TABLE.get(), "block", "_front")));		
		
		tintedCrossBlock(KoratioBlocks.FANTASIA_GRASS.get(), "");
		tintedCrossBlock(KoratioBlocks.COTTON_CANDY_GRASS.get(), "_top");
		crossBlock(KoratioBlocks.AMETHYST_GRASS.get());
		crossBlock(KoratioBlocks.EMERALD_GRASS.get());
		tintedCrossBlock(KoratioBlocks.RAINBOW_ROSE.get(), "_stem");
		tintedCrossBlock(KoratioBlocks.RAINBOW_ALLIUM.get(), "_stem");
		tintedCrossBlock(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), "_stem");
		crossBlock(KoratioBlocks.COOKIE_FLOWER.get());
		simpleBlock(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/white_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/white_sugarglass_flower")).renderType("tripwire"));
		simpleBlock(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/blue_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/blue_sugarglass_flower")).renderType("tripwire"));
		simpleBlock(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/green_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/green_sugarglass_flower")).renderType("tripwire"));
		simpleBlock(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/yellow_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/yellow_sugarglass_flower")).renderType("tripwire"));
		simpleBlock(KoratioBlocks.RED_SUGARGLASS_FLOWER.get(), models().withExistingParent(Koratio.prefix("block/red_sugarglass_flower").getPath(), "minecraft:block/coral_fan").texture("fan", Koratio.prefix("block/red_sugarglass_flower")).renderType("tripwire"));
		
		crossBlock(KoratioBlocks.AMETHYST_COBWEB.get());
		crossBlock(KoratioBlocks.EMERALD_COBWEB.get());
		simpleBlock(KoratioBlocks.RAINBOW_GEM_BLOCK.get());
		simpleBlock(KoratioBlocks.ARSOY_ORE.get());
		simpleBlock(KoratioBlocks.DEEPSLATE_ARSOY_ORE.get());
		simpleBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get());
		simpleBlock(KoratioBlocks.COOKIE_ORE.get());
		simpleBlock(KoratioBlocks.DEEPSLATE_COOKIE_ORE.get());
		simpleBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_COOKIE_ORE.get());
		simpleBlock(KoratioBlocks.RAW_ARSOY_BLOCK.get());
		simpleBlock(KoratioBlocks.ARSOY_BLOCK.get());
		simpleBlock(KoratioBlocks.SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.RED_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.BLUE_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.YELLOW_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.GREEN_SUGAR_BLOCK.get());
		simpleBlock(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get());
		stairsBlock(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get(), Koratio.prefix("block/raw_gingerbread_block"));
		slabBlock(KoratioBlocks.RAW_GINGERBREAD_SLAB.get(), KoratioBlocks.RAW_GINGERBREAD_BLOCK.getId(), Koratio.prefix("block/raw_gingerbread_block"));
		simpleBlock(KoratioBlocks.GINGERBREAD_BLOCK.get());
		stairsBlock(KoratioBlocks.GINGERBREAD_STAIRS.get(), Koratio.prefix("block/gingerbread_block"));
		slabBlock(KoratioBlocks.GINGERBREAD_SLAB.get(), KoratioBlocks.GINGERBREAD_BLOCK.getId(), Koratio.prefix("block/gingerbread_block"));
		simpleBlock(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get());
		stairsBlock(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get(), Koratio.prefix("block/raw_gingerbread_bricks"));
		slabBlock(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS.getId(), Koratio.prefix("block/raw_gingerbread_bricks"));
		simpleBlock(KoratioBlocks.GINGERBREAD_BRICKS.get());
		stairsBlock(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get(), Koratio.prefix("block/gingerbread_bricks"));
		slabBlock(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.GINGERBREAD_BRICKS.getId(), Koratio.prefix("block/gingerbread_bricks"));

		getVariantBuilder(KoratioBlocks.GOLDEN_WHEAT.get())
		.forAllStates(state -> {
			int age = state.getValue(CropBlock.AGE);
			return ConfiguredModel.builder()
					.uvLock(true)
					.modelFile(models().withExistingParent(KoratioBlocks.GOLDEN_WHEAT.getId().getPath()+"_stage"+age, "block/crop").renderType("cutout").texture("crop", Koratio.prefix("block/golden_wheat_stage"+age)))
					.build();
		});
		getVariantBuilder(KoratioBlocks.GOLDEN_BABY_CARROTS.get())
		.forAllStates(state -> {
			int age = state.getValue(CarrotBlock.AGE);
			if (age == 7) age = 3;
			else if (age <= 1) age = 0;
			else if (age <= 3) age = 1;
			else if (age <= 6) age = 2;
			return ConfiguredModel.builder()
					.uvLock(true)
					.modelFile(models().withExistingParent(KoratioBlocks.GOLDEN_BABY_CARROTS.getId().getPath()+"_stage"+age, "block/crop").renderType("cutout").texture("crop", Koratio.prefix("block/golden_baby_carrots_stage"+age)))
					.build();
		});
		axisBlock(KoratioBlocks.GOLDEN_HAY_BLOCK.get(), Koratio.prefix("block/golden_hay_block_side"), Koratio.prefix("block/golden_hay_block_top"));
		
		simpleBlock(KoratioBlocks.AMETHYST_BRICKS.get());
		stairsBlock(KoratioBlocks.AMETHYST_BRICK_STAIRS.get(), Koratio.prefix("block/amethyst_bricks"));
		slabBlock(KoratioBlocks.AMETHYST_BRICK_SLAB.get(), KoratioBlocks.AMETHYST_BRICKS.getId(), Koratio.prefix("block/amethyst_bricks"));
		logBlock(KoratioBlocks.AMETHYST_PILLAR.get());
		simpleBlock(KoratioBlocks.CHISELED_AMETHYST_BLOCK.get());
		
		simpleBlock(KoratioBlocks.EMERALD_BRICKS.get());
		stairsBlock(KoratioBlocks.EMERALD_BRICK_STAIRS.get(), Koratio.prefix("block/emerald_bricks"));
		slabBlock(KoratioBlocks.EMERALD_BRICK_SLAB.get(), KoratioBlocks.EMERALD_BRICKS.getId(), Koratio.prefix("block/emerald_bricks"));
		logBlock(KoratioBlocks.EMERALD_PILLAR.get());
		simpleBlock(KoratioBlocks.CHISELED_EMERALD_BLOCK.get());
				
		simpleBlock(KoratioBlocks.SOUL_STONE.get());
		stairsBlock(KoratioBlocks.SOUL_STONE_STAIRS.get(), Koratio.prefix("block/soul_stone"));
		slabBlock(KoratioBlocks.SOUL_STONE_SLAB.get(), KoratioBlocks.SOUL_STONE.getId(), Koratio.prefix("block/soul_stone"));
		pressurePlateBlock(KoratioBlocks.SOUL_STONE_PRESSURE_PLATE.get(), Koratio.prefix("block/soul_stone"));
		buttonBlock(KoratioBlocks.SOUL_STONE_BUTTON.get(), Koratio.prefix("block/soul_stone"));
		
		simpleBlock(KoratioBlocks.INFESTED_SOUL_STONE.get());
		stairsBlock(KoratioBlocks.INFESTED_SOUL_STONE_STAIRS.get(), Koratio.prefix("block/infested_soul_stone"));
		slabBlock(KoratioBlocks.INFESTED_SOUL_STONE_SLAB.get(), KoratioBlocks.SOUL_STONE.getId(), Koratio.prefix("block/infested_soul_stone"));
		pressurePlateBlock(KoratioBlocks.INFESTED_SOUL_STONE_PRESSURE_PLATE.get(), Koratio.prefix("block/infested_soul_stone"));
		buttonBlock(KoratioBlocks.INFESTED_SOUL_STONE_BUTTON.get(), Koratio.prefix("block/infested_soul_stone"));
		
		simpleBlock(KoratioBlocks.SOUL_COBBLESTONE.get());
		stairsBlock(KoratioBlocks.SOUL_COBBLESTONE_STAIRS.get(), Koratio.prefix("block/soul_cobblestone"));
		slabBlock(KoratioBlocks.SOUL_COBBLESTONE_SLAB.get(), KoratioBlocks.SOUL_COBBLESTONE.getId(), Koratio.prefix("block/soul_cobblestone"));
		wallBlock(KoratioBlocks.SOUL_COBBLESTONE_WALL.get(), Koratio.prefix("block/soul_cobblestone"));
		wallInventoryBlock(KoratioBlocks.SOUL_COBBLESTONE_WALL.get(), Koratio.prefix("block/soul_cobblestone"));

		simpleBlock(KoratioBlocks.INFESTED_SOUL_COBBLESTONE.get());
		stairsBlock(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_STAIRS.get(), Koratio.prefix("block/infested_soul_cobblestone"));
		slabBlock(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_SLAB.get(), KoratioBlocks.SOUL_COBBLESTONE.getId(), Koratio.prefix("block/infested_soul_cobblestone"));
		wallBlock(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_WALL.get(), Koratio.prefix("block/infested_soul_cobblestone"));
		wallInventoryBlock(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_WALL.get(), Koratio.prefix("block/infested_soul_cobblestone"));

		simpleBlock(KoratioBlocks.SMOOTH_SOUL_STONE.get());
		slabBlock(KoratioBlocks.SMOOTH_SOUL_STONE_SLAB.get(), KoratioBlocks.SMOOTH_SOUL_STONE.getId(), Koratio.prefix("block/smooth_soul_stone"));
		
		simpleBlock(KoratioBlocks.SOUL_STONE_BRICKS.get());
		simpleBlock(KoratioBlocks.CRACKED_SOUL_STONE_BRICKS.get());
		stairsBlock(KoratioBlocks.SOUL_STONE_BRICK_STAIRS.get(), Koratio.prefix("block/soul_stone_bricks"));
		slabBlock(KoratioBlocks.SOUL_STONE_BRICK_SLAB.get(), KoratioBlocks.SOUL_COBBLESTONE.getId(), Koratio.prefix("block/soul_stone_bricks"));
		wallBlock(KoratioBlocks.SOUL_STONE_BRICK_WALL.get(), Koratio.prefix("block/soul_stone_bricks"));
		wallInventoryBlock(KoratioBlocks.SOUL_STONE_BRICK_WALL.get(), Koratio.prefix("block/soul_stone_bricks"));
		simpleBlock(KoratioBlocks.CHISELED_SOUL_STONE_BRICKS.get());
		
		simpleBlock(KoratioBlocks.INFESTED_SOUL_STONE_BRICKS.get());
		simpleBlock(KoratioBlocks.CRACKED_INFESTED_SOUL_STONE_BRICKS.get());
		stairsBlock(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_STAIRS.get(), Koratio.prefix("block/infested_soul_stone_bricks"));
		slabBlock(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_SLAB.get(), KoratioBlocks.SOUL_COBBLESTONE.getId(), Koratio.prefix("block/infested_soul_stone_bricks"));
		wallBlock(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_WALL.get(), Koratio.prefix("block/infested_soul_stone_bricks"));
		wallInventoryBlock(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_WALL.get(), Koratio.prefix("block/infested_soul_stone_bricks"));
		simpleBlock(KoratioBlocks.CHISELED_INFESTED_SOUL_STONE_BRICKS.get());
		
		logBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get());
		simpleBlock(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stairsBlock(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS.get(), Koratio.prefix("block/blood_stained_cobbled_deepslate"));
		slabBlock(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.getId(), Koratio.prefix("block/blood_stained_cobbled_deepslate"));
		wallBlock(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get(), Koratio.prefix("block/blood_stained_cobbled_deepslate"));
		wallInventoryBlock(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get(), Koratio.prefix("block/blood_stained_cobbled_deepslate"));

		simpleBlock(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stairsBlock(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS.get(), Koratio.prefix("block/polished_blood_stained_deepslate"));
		slabBlock(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.getId(), Koratio.prefix("block/polished_blood_stained_deepslate"));
		wallBlock(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get(), Koratio.prefix("block/polished_blood_stained_deepslate"));
		wallInventoryBlock(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get(), Koratio.prefix("block/polished_blood_stained_deepslate"));
		
		simpleBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get());
		stairsBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get(), Koratio.prefix("block/blood_stained_deepslate_tiles"));
		slabBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.getId(), Koratio.prefix("block/blood_stained_deepslate_tiles"));
		wallBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), Koratio.prefix("block/blood_stained_deepslate_tiles"));
		wallInventoryBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), Koratio.prefix("block/blood_stained_deepslate_tiles"));
		
		simpleBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		stairsBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get(), Koratio.prefix("block/blood_stained_deepslate_bricks"));
		slabBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.getId(), Koratio.prefix("block/blood_stained_deepslate_bricks"));
		wallBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), Koratio.prefix("block/blood_stained_deepslate_bricks"));
		wallInventoryBlock(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), Koratio.prefix("block/blood_stained_deepslate_bricks"));
		
		simpleBlock(KoratioBlocks.CHISELED_BLOOD_STAINED_DEEPSLATE.get());
		simpleBlock(KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		simpleBlock(KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_TILES.get());
		
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
		doorBlockWithRenderType(KoratioBlocks.PANGO_DOOR.get(), Koratio.prefix("block/pango_door_bottom"), Koratio.prefix("block/pango_door_top"), "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.PANGO_TRAPDOOR.get(), Koratio.prefix("block/pango_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.PANGO_BUTTON.get(), Koratio.prefix("block/pango_planks"));
		pressurePlateBlock(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), Koratio.prefix("block/pango_planks"));
		signBlock(KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_WALL_SIGN.get(), KoratioBlocks.PANGO_PLANKS.get());
		builtinEntity(KoratioBlocks.PANGO_CHEST.get(), "koratio:block/pango_planks");

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
		doorBlockWithRenderType(KoratioBlocks.RUGONA_DOOR.get(), Koratio.prefix("block/rugona_door_bottom"), Koratio.prefix("block/rugona_door_top"), "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.RUGONA_TRAPDOOR.get(), Koratio.prefix("block/rugona_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.RUGONA_BUTTON.get(), Koratio.prefix("block/rugona_planks"));
		pressurePlateBlock(KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), Koratio.prefix("block/rugona_planks"));
		signBlock(KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get(), KoratioBlocks.RUGONA_PLANKS.get());
		builtinEntity(KoratioBlocks.RUGONA_CHEST.get(), "koratio:block/rugona_planks");

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
		doorBlockWithRenderType(KoratioBlocks.VARESO_DOOR.get(), Koratio.prefix("block/vareso_door_bottom"), Koratio.prefix("block/vareso_door_top"), "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.VARESO_TRAPDOOR.get(), Koratio.prefix("block/vareso_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.VARESO_BUTTON.get(), Koratio.prefix("block/vareso_planks"));
		pressurePlateBlock(KoratioBlocks.VARESO_PRESSURE_PLATE.get(), Koratio.prefix("block/vareso_planks"));
		signBlock(KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get(), KoratioBlocks.VARESO_PLANKS.get());
		builtinEntity(KoratioBlocks.VARESO_CHEST.get(), "koratio:block/vareso_planks");
		
		crossBlock(KoratioBlocks.NIGHY_SAPLING.get());
		logBlock(KoratioBlocks.NIGHY_LOG.get());
		woodBlock(KoratioBlocks.NIGHY_WOOD.get(), KoratioBlocks.NIGHY_LOG.get());
		logBlock(KoratioBlocks.STRIPPED_NIGHY_LOG.get());
		woodBlock(KoratioBlocks.STRIPPED_NIGHY_WOOD.get(), KoratioBlocks.STRIPPED_NIGHY_LOG.get());
		simpleBlock(KoratioBlocks.NIGHY_PLANKS.get());
		slabBlock(KoratioBlocks.NIGHY_SLAB.get(), KoratioBlocks.NIGHY_PLANKS.getId(), Koratio.prefix("block/nighy_planks"));
		stairsBlock(KoratioBlocks.NIGHY_STAIRS.get(), Koratio.prefix("block/nighy_planks"));
		leavesBlock(KoratioBlocks.NIGHY_LEAVES.get());
		fenceBlock(KoratioBlocks.NIGHY_FENCE.get(), Koratio.prefix("block/nighy_planks"));
		fenceGateBlock(KoratioBlocks.NIGHY_FENCE_GATE.get(), Koratio.prefix("block/nighy_planks"));
		doorBlockWithRenderType(KoratioBlocks.NIGHY_DOOR.get(), Koratio.prefix("block/nighy_door_bottom"), Koratio.prefix("block/nighy_door_top"), "cutout");
		trapdoorBlockWithRenderType(KoratioBlocks.NIGHY_TRAPDOOR.get(), Koratio.prefix("block/nighy_trapdoor"), true, "cutout");
		buttonBlock(KoratioBlocks.NIGHY_BUTTON.get(), Koratio.prefix("block/nighy_planks"));
		pressurePlateBlock(KoratioBlocks.NIGHY_PRESSURE_PLATE.get(), Koratio.prefix("block/nighy_planks"));
		signBlock(KoratioBlocks.NIGHY_SIGN.get(), KoratioBlocks.NIGHY_WALL_SIGN.get(), KoratioBlocks.NIGHY_PLANKS.get());
		builtinEntity(KoratioBlocks.NIGHY_CHEST.get(), "koratio:block/nighy_planks");

		crossBlock(KoratioBlocks.PURPLE_MUSHROOM.get());
		crossBlock(KoratioBlocks.GREEN_MUSHROOM.get());
		
		multifaceBlock(KoratioBlocks.GILDED_VINES.get());
		
		mushroomBlock(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get());
		mushroomBlock(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get());
		
		tintedPottedFlower(KoratioBlocks.POTTED_RAINBOW_ROSE.get());
		tintedPottedFlower(KoratioBlocks.POTTED_RAINBOW_ALLIUM.get());
		tintedPottedFlower(KoratioBlocks.POTTED_RAINBOW_LILY_OF_THE_VALLEY.get());
		
		pottedPlant(KoratioBlocks.POTTED_COOKIE_FLOWER.get());
		pottedPlant(KoratioBlocks.POTTED_PANGO_SAPLING.get());
		pottedPlant(KoratioBlocks.POTTED_RUGONA_SAPLING.get());
		pottedPlant(KoratioBlocks.POTTED_VARESO_SAPLING.get());
		pottedPlant(KoratioBlocks.POTTED_NIGHY_SAPLING.get());
		pottedPlant(KoratioBlocks.POTTED_PURPLE_MUSHROOM.get());
		pottedPlant(KoratioBlocks.POTTED_GREEN_MUSHROOM.get());
	}
	
	protected void wallInventoryBlock(Block block, ResourceLocation texture) {
		simpleBlockItem(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath()+"_inventory", "minecraft:block/wall_inventory").texture("wall", texture));
	}
	
	protected void woodBlock(RotatedPillarBlock block, Block log) {
		axisBlock(block, models().cubeColumn(ForgeRegistries.BLOCKS.getKey(block).getPath(), getBlockPathInFolder(log, "block"), getBlockPathInFolder(log, "block")), models().cubeColumn(ForgeRegistries.BLOCKS.getKey(block).getPath(), getBlockPathInFolder(log, "block"), getBlockPathInFolder(log, "block")));
	}
	
	protected void leavesBlock(Block block) {
		simpleBlock(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "block/leaves").texture("all", getBlockPathInFolder(block, "block")));
	}
	
	protected void multifaceBlock(Block block) {
		MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
		ModelFile model = models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "minecraft:block/block").ao(false).renderType("cutout").texture("vine", getBlockPathInFolder(block, "block")).texture("particle", "#vine")
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
		signBlock(sign, wallSign, getBlockPathInFolder(particle, "block"));
	}
	
	protected void crossBlock(Block block) {
		simpleBlock(block, models().cross(ForgeRegistries.BLOCKS.getKey(block).getPath(), getBlockPathInFolder(block, "block")).renderType("cutout"));
	}
	
	protected void tintedCrossBlock(Block block, String extra) {
		simpleBlock(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), Koratio.prefix("block/tinted_cross")).texture("cross", getBlockPathInFolder(block, "block", extra)).texture("tinted_cross", getBlockPathInFolder(block, "block")).renderType("cutout"));
	}
	
	protected void pottedPlant(FlowerPotBlock block) {
		simpleBlock(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "minecraft:block/flower_pot_cross").texture("plant", getBlockPathInFolder(block.getContent(), "block")).renderType("cutout"));
	}
	
	protected void tintedPottedFlower(FlowerPotBlock block) {
		simpleBlock(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), Koratio.prefix("block/tinted_flower_pot_cross")).texture("plant", getBlockPathInFolder(block.getContent(), "block", "_stem")).texture("plant_tinted", getBlockPathInFolder(block.getContent(), "block")).renderType("cutout"));
	}
	
	protected void mushroomBlock(Block block) {
		MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
		builder.part().modelFile(models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block, "block"))).rotationX(270).uvLock(true).addModel()
		.condition(HugeMushroomBlock.UP, true);
		builder.part().modelFile(models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block, "block"))).rotationX(90).uvLock(true).addModel()
		.condition(HugeMushroomBlock.DOWN, true);
		builder.part().modelFile(models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block, "block"))).addModel()
		.condition(HugeMushroomBlock.NORTH, true);
		builder.part().modelFile(models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block, "block"))).rotationY(90).uvLock(true).addModel()
		.condition(HugeMushroomBlock.EAST, true);
		builder.part().modelFile(models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block, "block"))).rotationY(180).uvLock(true).addModel()
		.condition(HugeMushroomBlock.SOUTH, true);
		builder.part().modelFile(models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), "minecraft:block/template_single_face").texture("texture", getBlockPathInFolder(block, "block"))).rotationY(270).uvLock(true).addModel()
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
		simpleBlock(block, models().getBuilder(ForgeRegistries.BLOCKS.getKey(block).getPath())
				.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
				.texture("particle", particle));
	}
	
	private ResourceLocation getBlockPathInFolder(Block block, String folder, String extra) {
		if (ForgeRegistries.BLOCKS.getKey(block).getNamespace() == "minecraft") {
			return new ResourceLocation(folder+"/"+ForgeRegistries.BLOCKS.getKey(block).getPath()+extra);
		} else {
			return Koratio.prefix(folder+"/"+ForgeRegistries.BLOCKS.getKey(block).getPath()+extra);
		}
	}
	
	private ResourceLocation getBlockPathInFolder(Block block, String folder) {
		return getBlockPathInFolder(block, folder, "");
	}
}