package net.setrion.koratio.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.data.recipes.CandyShaperRecipeBuilder;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioFluids;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioTags;
import net.setrion.koratio.world.item.CandyItem;
import net.setrion.koratio.world.item.ColoredCandyItem;
import net.setrion.koratio.world.item.crafting.WoodcutterRecipe;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends RecipeProvider {

	public static final ImmutableList<ItemLike> RUBY_SMELTABLES = ImmutableList.of(KoratioItems.RUBY_ORE.get(), KoratioItems.DEEPSLATE_RUBY_ORE.get());
	public static final ImmutableList<ItemLike> SAPPHIRE_SMELTABLES = ImmutableList.of(KoratioItems.SAPPHIRE_ORE.get(), KoratioItems.DEEPSLATE_SAPPHIRE_ORE.get());
	public static final ImmutableList<ItemLike> TOPAZ_SMELTABLES = ImmutableList.of(KoratioItems.TOPAZ_ORE.get(), KoratioItems.DEEPSLATE_TOPAZ_ORE.get());
	public static final ImmutableList<ItemLike> ONYX_SMELTABLES = ImmutableList.of(KoratioItems.ONYX_ORE.get(), KoratioItems.DEEPSLATE_ONYX_ORE.get());

	public RecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider);
	}
	
	@Override
	protected void buildRecipes(RecipeOutput output) {
		//ShapelessRecipes
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KoratioItems.RAINBOW_GEM.get()).requires(Items.LAPIS_LAZULI).requires(Items.AMETHYST_SHARD).requires(Items.QUARTZ).requires(KoratioItems.TOPAZ.get()).requires(KoratioItems.SAPPHIRE.get()).requires(KoratioItems.RUBY.get()).requires(Items.EMERALD).requires(KoratioItems.ONYX.get()).requires(Items.DIAMOND).unlockedBy("has_netherite", has(Items.NETHERITE_INGOT)).save(output);
		decompress(KoratioItems.RAINBOW_GEM.get(), KoratioBlocks.RAINBOW_GEM_BLOCK.get()).save(output, Koratio.prefix("rainbow_gem_from_block"));
		decompress(KoratioItems.RUBY.get(), KoratioBlocks.RUBY_BLOCK.get()).save(output, Koratio.prefix("ruby_from_block"));
		decompress(KoratioItems.SAPPHIRE.get(), KoratioBlocks.SAPPHIRE_BLOCK.get()).save(output, Koratio.prefix("sapphire_from_block"));
		decompress(KoratioItems.TOPAZ.get(), KoratioBlocks.TOPAZ_BLOCK.get()).save(output, Koratio.prefix("topaz_from_block"));
		decompress(KoratioItems.ONYX.get(), KoratioBlocks.ONYX_BLOCK.get()).save(output, Koratio.prefix("onyx_from_block"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, KoratioItems.RED_SUGAR.get(), 8).requires(Items.SUGAR, 8).requires(Tags.Items.DYES_RED).group("red_sugar").unlockedBy("has_sugar", has(Items.SUGAR)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, KoratioItems.BLUE_SUGAR.get(), 8).requires(Items.SUGAR, 8).requires(Tags.Items.DYES_BLUE).group("blue_sugar").unlockedBy("has_sugar", has(Items.SUGAR)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, KoratioItems.YELLOW_SUGAR.get(), 8).requires(Items.SUGAR, 8).requires(Tags.Items.DYES_YELLOW).group("yellow_sugar").unlockedBy("has_sugar", has(Items.SUGAR)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, KoratioItems.GREEN_SUGAR.get(), 8).requires(Items.SUGAR, 8).requires(Tags.Items.DYES_GREEN).group("green_sugar").unlockedBy("has_sugar", has(Items.SUGAR)).save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KoratioItems.STICKY_WHITE_SUGAR_BUCKET.get()).requires(Items.BUCKET).requires(KoratioBlocks.STICKY_WHITE_SUGAR_BLOCK.get()).unlockedBy("has_sugar_block", has(KoratioBlocks.STICKY_WHITE_SUGAR_BLOCK.get())).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KoratioItems.STICKY_BLUE_SUGAR_BUCKET.get()).requires(Items.BUCKET).requires(KoratioBlocks.STICKY_BLUE_SUGAR_BLOCK.get()).unlockedBy("has_sugar_block", has(KoratioBlocks.STICKY_BLUE_SUGAR_BLOCK.get())).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KoratioItems.STICKY_GREEN_SUGAR_BUCKET.get()).requires(Items.BUCKET).requires(KoratioBlocks.STICKY_GREEN_SUGAR_BLOCK.get()).unlockedBy("has_sugar_block", has(KoratioBlocks.STICKY_GREEN_SUGAR_BLOCK.get())).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KoratioItems.STICKY_YELLOW_SUGAR_BUCKET.get()).requires(Items.BUCKET).requires(KoratioBlocks.STICKY_YELLOW_SUGAR_BLOCK.get()).unlockedBy("has_sugar_block", has(KoratioBlocks.STICKY_YELLOW_SUGAR_BLOCK.get())).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KoratioItems.STICKY_RED_SUGAR_BUCKET.get()).requires(Items.BUCKET).requires(KoratioBlocks.STICKY_RED_SUGAR_BLOCK.get()).unlockedBy("has_sugar_block", has(KoratioBlocks.STICKY_RED_SUGAR_BLOCK.get())).save(output);

		oneToOne(Items.COOKIE, KoratioItems.COOKIE_FLOWER.get()).save(output, Koratio.prefix("cookie_from_flower"));
		oneToOne(Items.SUGAR, KoratioItems.WHITE_SUGARGLASS_FLOWER.get()).save(output, Koratio.prefix("sugar_from_flower"));
		oneToOne(KoratioItems.BLUE_SUGAR.get(), KoratioItems.BLUE_SUGARGLASS_FLOWER.get()).save(output, Koratio.prefix("blue_sugar_from_flower"));
		oneToOne(KoratioItems.GREEN_SUGAR.get(), KoratioItems.GREEN_SUGARGLASS_FLOWER.get()).save(output, Koratio.prefix("green_sugar_from_flower"));
		oneToOne(KoratioItems.YELLOW_SUGAR.get(), KoratioItems.YELLOW_SUGARGLASS_FLOWER.get()).save(output, Koratio.prefix("yellow_sugar_from_flower"));
		oneToOne(KoratioItems.RED_SUGAR.get(), KoratioItems.RED_SUGARGLASS_FLOWER.get()).save(output, Koratio.prefix("red_sugar_from_flower"));
		
		decompress(Items.SUGAR, KoratioBlocks.WHITE_SUGAR_BLOCK.get()).save(output, Koratio.prefix("white_sugar_from_block"));
		decompress(KoratioItems.RED_SUGAR.get(), KoratioBlocks.RED_SUGAR_BLOCK.get()).save(output, Koratio.prefix("red_sugar_from_block"));
		decompress(KoratioItems.BLUE_SUGAR.get(), KoratioBlocks.BLUE_SUGAR_BLOCK.get()).save(output, Koratio.prefix("blue_sugar_from_block"));
		decompress(KoratioItems.YELLOW_SUGAR.get(), KoratioBlocks.YELLOW_SUGAR_BLOCK.get()).save(output, Koratio.prefix("yellow_sugar_from_block"));
		decompress(KoratioItems.GREEN_SUGAR.get(), KoratioBlocks.GREEN_SUGAR_BLOCK.get()).save(output, Koratio.prefix("green_sugar_from_block"));
		
		button(KoratioBlocks.PANGO_BUTTON.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		planksFromLogs(output, KoratioBlocks.PANGO_PLANKS.get(), KoratioTags.Items.PANGO_LOGS, 4);
		chestBoat(output, KoratioItems.PANGO_CHEST_BOAT.get(), KoratioItems.PANGO_BOAT.get());
		
		button(KoratioBlocks.RUGONA_BUTTON.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		planksFromLogs(output, KoratioBlocks.RUGONA_PLANKS.get(), KoratioTags.Items.RUGONA_LOGS, 4);
		chestBoat(output, KoratioItems.RUGONA_CHEST_BOAT.get(), KoratioItems.RUGONA_BOAT.get());
		
		button(KoratioBlocks.VARESO_BUTTON.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		planksFromLogs(output, KoratioBlocks.VARESO_PLANKS.get(), KoratioTags.Items.VARESO_LOGS, 4);
		chestBoat(output, KoratioItems.VARESO_CHEST_BOAT.get(), KoratioItems.VARESO_BOAT.get());

		button(KoratioBlocks.ELVEN_BUTTON.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		button(KoratioBlocks.BLUE_ELVEN_BUTTON.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		button(KoratioBlocks.CYAN_ELVEN_BUTTON.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		button(KoratioBlocks.GREEN_ELVEN_BUTTON.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		planksFromLogs(output, KoratioBlocks.ELVEN_PLANKS.get(), KoratioTags.Items.ELVEN_LOGS, 4);
		chestBoat(output, KoratioItems.ELVEN_CHEST_BOAT.get(), KoratioItems.ELVEN_BOAT.get());
		chestBoat(output, KoratioItems.BLUE_ELVEN_CHEST_BOAT.get(), KoratioItems.BLUE_ELVEN_BOAT.get());
		chestBoat(output, KoratioItems.CYAN_ELVEN_CHEST_BOAT.get(), KoratioItems.CYAN_ELVEN_BOAT.get());
		chestBoat(output, KoratioItems.GREEN_ELVEN_CHEST_BOAT.get(), KoratioItems.GREEN_ELVEN_BOAT.get());
		
		//ShapedRecipes
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.WOODCUTTER.get()).pattern(" i ").pattern("sws").define('i', Tags.Items.INGOTS_IRON).define('s', Blocks.STONE).define('w', ItemTags.PLANKS).unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON)).save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.DECRYPTING_TABLE.get()).pattern("pb").pattern("ww").pattern("ww").define('p', Items.PAPER).define('b', Items.BOOK).define('w', ItemTags.PLANKS).unlockedBy("has_book", has(Items.BOOK)).save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CANDY_SHAPER.get()).pattern("ss").pattern("ww").pattern("ww").define('s', Blocks.STONE_SLAB).define('w', ItemTags.PLANKS).unlockedBy("has_planks", has(ItemTags.PLANKS)).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, KoratioItems.PIPING_BAG.get()).pattern(" sb").pattern("sgs").pattern("is ").define('s', Items.STRING).define('b', ItemTags.WOODEN_BUTTONS).define('g', Items.GLASS_BOTTLE).define('i', Items.BAMBOO).unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE)).save(output);

		spatula(output, KoratioItems.WOODEN_ICING_SPATULA.get(), Ingredient.of(ItemTags.PLANKS));
		spatula(output, KoratioItems.STONE_ICING_SPATULA.get(), Ingredient.of(ItemTags.STONE_TOOL_MATERIALS));
		spatula(output, KoratioItems.GOLDEN_ICING_SPATULA.get(), Ingredient.of(Tags.Items.INGOTS_GOLD));
		spatula(output, KoratioItems.IRON_ICING_SPATULA.get(), Ingredient.of(Tags.Items.INGOTS_IRON));
		spatula(output, KoratioItems.DIAMOND_ICING_SPATULA.get(), Ingredient.of(Tags.Items.GEMS_DIAMOND));

		compress(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), KoratioItems.RAINBOW_GEM.get()).save(output);
		compress(KoratioBlocks.RUBY_BLOCK.get(), KoratioItems.RUBY.get()).save(output);
		compress(KoratioBlocks.SAPPHIRE_BLOCK.get(), KoratioItems.SAPPHIRE.get()).save(output);
		compress(KoratioBlocks.TOPAZ_BLOCK.get(), KoratioItems.TOPAZ.get()).save(output);
		compress(KoratioBlocks.ONYX_BLOCK.get(), KoratioItems.ONYX.get()).save(output);
		compress(KoratioBlocks.WHITE_SUGAR_BLOCK.get(), Items.SUGAR).save(output);
		compress(KoratioBlocks.RED_SUGAR_BLOCK.get(), KoratioItems.RED_SUGAR.get()).save(output);
		compress(KoratioBlocks.BLUE_SUGAR_BLOCK.get(), KoratioItems.BLUE_SUGAR.get()).save(output);
		compress(KoratioBlocks.YELLOW_SUGAR_BLOCK.get(), KoratioItems.YELLOW_SUGAR.get()).save(output);
		compress(KoratioBlocks.GREEN_SUGAR_BLOCK.get(), KoratioItems.GREEN_SUGAR.get()).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(), 8).pattern("www").pattern("sms").pattern("chc").define('w', Items.WHEAT).define('s', KoratioTags.Items.SUGAR).define('m', Items.MILK_BUCKET).define('c', Items.COCOA_BEANS).define('h', Items.HONEY_BOTTLE).unlockedBy("has_cocoa_beans", has(Items.COCOA_BEANS)).save(output);
		twoByTwo(output, KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(), KoratioBlocks.RAW_GINGERBREAD_BLOCK.get());
		twoByTwo(output, KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS.get());
		twoByTwo(output, KoratioBlocks.GINGERBREAD_BRICKS.get(), KoratioBlocks.GINGERBREAD_BLOCK.get());
		twoByTwo(output, KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get(), KoratioBlocks.GINGERBREAD_BRICKS.get());

		stairs(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get(), KoratioBlocks.RAW_GINGERBREAD_BLOCK);
		stairs(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS);
		stairs(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.get(), KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS);
		stairs(KoratioBlocks.GINGERBREAD_STAIRS.get(), KoratioBlocks.GINGERBREAD_BLOCK);
		stairs(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get(), KoratioBlocks.GINGERBREAD_BRICKS);
		stairs(KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.get(), KoratioBlocks.LARGE_GINGERBREAD_BRICKS);

		slab(KoratioBlocks.RAW_GINGERBREAD_SLAB.get(), KoratioBlocks.RAW_GINGERBREAD_BLOCK);
		slab(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS);
		slab(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS);
		slab(KoratioBlocks.GINGERBREAD_SLAB.get(), KoratioBlocks.GINGERBREAD_BLOCK);
		slab(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.GINGERBREAD_BRICKS);
		slab(KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.get(), KoratioBlocks.LARGE_GINGERBREAD_BRICKS);

		wall(KoratioBlocks.RAW_GINGERBREAD_WALL.get(), KoratioBlocks.RAW_GINGERBREAD_BLOCK);
		wall(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS);
		wall(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.get(), KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS);
		wall(KoratioBlocks.GINGERBREAD_WALL.get(), KoratioBlocks.GINGERBREAD_BLOCK);
		wall(KoratioBlocks.GINGERBREAD_BRICK_WALL.get(), KoratioBlocks.GINGERBREAD_BRICKS);
		wall(KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.get(), KoratioBlocks.LARGE_GINGERBREAD_BRICKS);

		leafPane(KoratioBlocks.OAK_LEAF_PANE.get(), Blocks.OAK_LEAVES).save(output);
		leafPane(KoratioBlocks.SPRUCE_LEAF_PANE.get(), Blocks.SPRUCE_LEAVES).save(output);
		leafPane(KoratioBlocks.BIRCH_LEAF_PANE.get(), Blocks.BIRCH_LEAVES).save(output);
		leafPane(KoratioBlocks.JUNGLE_LEAF_PANE.get(), Blocks.JUNGLE_LEAVES).save(output);
		leafPane(KoratioBlocks.ACACIA_LEAF_PANE.get(), Blocks.ACACIA_LEAVES).save(output);
		leafPane(KoratioBlocks.DARK_OAK_LEAF_PANE.get(), Blocks.DARK_OAK_LEAVES).save(output);
		leafPane(KoratioBlocks.MANGROVE_LEAF_PANE.get(), Blocks.MANGROVE_LEAVES).save(output);
		leafPane(KoratioBlocks.AZALEA_LEAF_PANE.get(), Blocks.AZALEA_LEAVES).save(output);
		leafPane(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.get(), Blocks.FLOWERING_AZALEA_LEAVES).save(output);
		leafPane(KoratioBlocks.CHERRY_LEAF_PANE.get(), Blocks.CHERRY_LEAVES).save(output);

		tallDoor(KoratioBlocks.TALL_OAK_DOOR.get(), Blocks.OAK_PLANKS, Blocks.OAK_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_SPRUCE_DOOR.get(), Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_BIRCH_DOOR.get(), Blocks.BIRCH_PLANKS, Blocks.BIRCH_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_JUNGLE_DOOR.get(), Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_ACACIA_DOOR.get(), Blocks.ACACIA_PLANKS, Blocks.ACACIA_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_DARK_OAK_DOOR.get(), Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_MANGROVE_DOOR.get(), Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_CHERRY_DOOR.get(), Blocks.CHERRY_PLANKS, Blocks.CHERRY_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_BAMBOO_DOOR.get(), Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_CRIMSON_DOOR.get(), Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_WARPED_DOOR.get(), Blocks.WARPED_PLANKS, Blocks.WARPED_DOOR, "tall_wooden_door").save(output);
		tallDoor(KoratioBlocks.TALL_IRON_DOOR.get(), Blocks.IRON_BLOCK, Blocks.IRON_DOOR, "").save(output);
		tallDoor(KoratioBlocks.TALL_COPPER_DOOR.get(), Blocks.COPPER_BLOCK, Blocks.COPPER_DOOR, "").save(output);
		tallDoor(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get(), Blocks.EXPOSED_COPPER, Blocks.EXPOSED_COPPER_DOOR, "").save(output);
		tallDoor(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get(), Blocks.OXIDIZED_COPPER, Blocks.OXIDIZED_COPPER_DOOR, "").save(output);
		tallDoor(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get(), Blocks.WEATHERED_COPPER, Blocks.WEATHERED_COPPER_DOOR, "").save(output);
		tallDoor(KoratioBlocks.TALL_WAXED_COPPER_DOOR.get(), Blocks.WAXED_COPPER_BLOCK, Blocks.WAXED_COPPER_DOOR, "").save(output);
		tallDoor(KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get(), Blocks.WAXED_EXPOSED_COPPER, Blocks.WAXED_EXPOSED_COPPER_DOOR, "").save(output);
		tallDoor(KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get(), Blocks.WAXED_OXIDIZED_COPPER, Blocks.WAXED_OXIDIZED_COPPER_DOOR, "").save(output);
		tallDoor(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get(), Blocks.WAXED_WEATHERED_COPPER, Blocks.WAXED_WEATHERED_COPPER_DOOR, "").save(output);
		
		woodFromLogs(output, KoratioBlocks.PANGO_WOOD.get(), KoratioBlocks.PANGO_LOG.get());
		woodFromLogs(output, KoratioBlocks.STRIPPED_PANGO_WOOD.get(), KoratioBlocks.STRIPPED_PANGO_LOG.get());
		door(KoratioBlocks.PANGO_DOOR.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		tallDoor(KoratioBlocks.TALL_PANGO_DOOR.get(), KoratioBlocks.PANGO_PLANKS.get(), KoratioBlocks.PANGO_DOOR.get(), "tall_wooden_door").save(output);
		fence(KoratioBlocks.PANGO_FENCE.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		fenceGate(KoratioBlocks.PANGO_FENCE_GATE.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		pressurePlate(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		woodenSlab(KoratioBlocks.PANGO_SLAB.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		woodenStairs(KoratioBlocks.PANGO_STAIRS.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		trapdoor(KoratioBlocks.PANGO_TRAPDOOR.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		sign(KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		hangingSign(KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_PANGO_LOG).save(output);
		woodenBoat(output, KoratioItems.PANGO_BOAT.get(), KoratioBlocks.PANGO_PLANKS.get());
		chest(KoratioItems.PANGO_CHEST.get(), KoratioBlocks.PANGO_PLANKS).save(output);
		bookshelf(output, KoratioItems.PANGO_BOOKSHELF.get(), KoratioBlocks.PANGO_PLANKS);
		leafPane(KoratioBlocks.PANGO_LEAF_PANE.get(), KoratioBlocks.PANGO_LEAVES.get()).save(output);
		
		woodFromLogs(output, KoratioBlocks.RUGONA_WOOD.get(), KoratioBlocks.RUGONA_LOG.get());
		woodFromLogs(output, KoratioBlocks.STRIPPED_RUGONA_WOOD.get(), KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		door(KoratioBlocks.RUGONA_DOOR.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		tallDoor(KoratioBlocks.TALL_RUGONA_DOOR.get(), KoratioBlocks.RUGONA_PLANKS.get(), KoratioBlocks.RUGONA_DOOR.get(), "tall_wooden_door").save(output);
		fence(KoratioBlocks.RUGONA_FENCE.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		fenceGate(KoratioBlocks.RUGONA_FENCE_GATE.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		pressurePlate(KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		woodenSlab(KoratioBlocks.RUGONA_SLAB.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		woodenStairs(KoratioBlocks.RUGONA_STAIRS.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		trapdoor(KoratioBlocks.RUGONA_TRAPDOOR.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		sign(KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		hangingSign(KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_RUGONA_LOG).save(output);
		woodenBoat(output, KoratioItems.RUGONA_BOAT.get(), KoratioBlocks.RUGONA_PLANKS.get());
		chest(KoratioItems.RUGONA_CHEST.get(), KoratioBlocks.RUGONA_PLANKS).save(output);
		bookshelf(output, KoratioItems.RUGONA_BOOKSHELF.get(), KoratioBlocks.RUGONA_PLANKS);
		leafPane(KoratioBlocks.RUGONA_LEAF_PANE.get(), KoratioBlocks.RUGONA_LEAVES.get()).save(output);

		woodFromLogs(output, KoratioBlocks.CANDY_WOOD.get(), KoratioBlocks.CANDY_LOG.get());
		woodFromLogs(output, KoratioBlocks.STRIPPED_CANDY_WOOD.get(), KoratioBlocks.STRIPPED_CANDY_LOG.get());
		door(KoratioBlocks.CANDY_DOOR.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		//tallDoor(KoratioBlocks.TALL_CANDY_DOOR.get(), KoratioBlocks.CANDY_PLANKS.get(), KoratioBlocks.CANDY_DOOR.get(), "tall_wooden_door").save(output);
		fence(KoratioBlocks.CANDY_FENCE.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		fenceGate(KoratioBlocks.CANDY_FENCE_GATE.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		pressurePlate(KoratioBlocks.CANDY_PRESSURE_PLATE.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		woodenSlab(KoratioBlocks.CANDY_SLAB.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		woodenStairs(KoratioBlocks.CANDY_STAIRS.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		trapdoor(KoratioBlocks.CANDY_TRAPDOOR.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		sign(KoratioBlocks.CANDY_SIGN.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		hangingSign(KoratioBlocks.CANDY_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_CANDY_LOG).save(output);
		woodenBoat(output, KoratioItems.CANDY_BOAT.get(), KoratioBlocks.CANDY_PLANKS.get());
		chest(KoratioItems.CANDY_CHEST.get(), KoratioBlocks.CANDY_PLANKS).save(output);
		bookshelf(output, KoratioItems.CANDY_BOOKSHELF.get(), KoratioBlocks.CANDY_PLANKS);
		leafPane(KoratioBlocks.PINK_COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.PINK_COTTON_CANDY_LEAVES.get()).save(output);
		leafPane(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAVES.get()).save(output);
		leafPane(KoratioBlocks.LIME_COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.LIME_COTTON_CANDY_LEAVES.get()).save(output);
		leafPane(KoratioBlocks.YELLOW_COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.YELLOW_COTTON_CANDY_LEAVES.get()).save(output);

		woodFromLogs(output, KoratioBlocks.VARESO_WOOD.get(), KoratioBlocks.VARESO_LOG.get());
		woodFromLogs(output, KoratioBlocks.STRIPPED_VARESO_WOOD.get(), KoratioBlocks.STRIPPED_VARESO_LOG.get());
		door(KoratioBlocks.VARESO_DOOR.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		tallDoor(KoratioBlocks.TALL_VARESO_DOOR.get(), KoratioBlocks.VARESO_PLANKS.get(), KoratioBlocks.VARESO_DOOR.get(), "tall_wooden_door").save(output);
		fence(KoratioBlocks.VARESO_FENCE.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		fenceGate(KoratioBlocks.VARESO_FENCE_GATE.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		pressurePlate(KoratioBlocks.VARESO_PRESSURE_PLATE.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		woodenSlab(KoratioBlocks.VARESO_SLAB.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		woodenStairs(KoratioBlocks.VARESO_STAIRS.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		trapdoor(KoratioBlocks.VARESO_TRAPDOOR.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		sign(KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		hangingSign(KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_VARESO_LOG).save(output);
		woodenBoat(output, KoratioItems.VARESO_BOAT.get(), KoratioBlocks.VARESO_PLANKS.get());
		chest(KoratioItems.VARESO_CHEST.get(), KoratioBlocks.VARESO_PLANKS).save(output);
		bookshelf(output, KoratioItems.VARESO_BOOKSHELF.get(), KoratioBlocks.VARESO_PLANKS);
		leafPane(KoratioBlocks.VARESO_LEAF_PANE.get(), KoratioBlocks.VARESO_LEAVES.get()).save(output);

		woodFromLogs(output, KoratioBlocks.ELVEN_WOOD.get(), KoratioBlocks.ELVEN_LOG.get());
		woodFromLogs(output, KoratioBlocks.STRIPPED_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_ELVEN_LOG.get());
		woodFromLogs(output, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get());
		woodFromLogs(output, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get());
		woodFromLogs(output, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get());
		door(KoratioBlocks.ELVEN_DOOR.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		tallDoor(KoratioBlocks.TALL_ELVEN_DOOR.get(), KoratioBlocks.ELVEN_PLANKS.get(), KoratioBlocks.ELVEN_DOOR.get(), "tall_wooden_door").save(output);
		door(KoratioBlocks.BLUE_ELVEN_DOOR.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		tallDoor(KoratioBlocks.TALL_BLUE_ELVEN_DOOR.get(), KoratioBlocks.BLUE_ELVEN_PLANKS.get(), KoratioBlocks.BLUE_ELVEN_DOOR.get(), "tall_wooden_door").save(output);
		door(KoratioBlocks.CYAN_ELVEN_DOOR.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		tallDoor(KoratioBlocks.TALL_CYAN_ELVEN_DOOR.get(), KoratioBlocks.CYAN_ELVEN_PLANKS.get(), KoratioBlocks.CYAN_ELVEN_DOOR.get(), "tall_wooden_door").save(output);
		door(KoratioBlocks.GREEN_ELVEN_DOOR.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		tallDoor(KoratioBlocks.TALL_GREEN_ELVEN_DOOR.get(), KoratioBlocks.GREEN_ELVEN_PLANKS.get(), KoratioBlocks.GREEN_ELVEN_DOOR.get(), "tall_wooden_door").save(output);
		fence(KoratioBlocks.ELVEN_FENCE.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		fence(KoratioBlocks.BLUE_ELVEN_FENCE.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		fence(KoratioBlocks.CYAN_ELVEN_FENCE.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		fence(KoratioBlocks.GREEN_ELVEN_FENCE.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		fenceGate(KoratioBlocks.ELVEN_FENCE_GATE.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		fenceGate(KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		fenceGate(KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		fenceGate(KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		pressurePlate(KoratioBlocks.ELVEN_PRESSURE_PLATE.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		pressurePlate(KoratioBlocks.BLUE_ELVEN_PRESSURE_PLATE.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		pressurePlate(KoratioBlocks.CYAN_ELVEN_PRESSURE_PLATE.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		pressurePlate(KoratioBlocks.GREEN_ELVEN_PRESSURE_PLATE.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		woodenSlab(KoratioBlocks.ELVEN_SLAB.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		woodenSlab(KoratioBlocks.BLUE_ELVEN_SLAB.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		woodenSlab(KoratioBlocks.CYAN_ELVEN_SLAB.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		woodenSlab(KoratioBlocks.GREEN_ELVEN_SLAB.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		woodenStairs(KoratioBlocks.ELVEN_STAIRS.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		woodenStairs(KoratioBlocks.BLUE_ELVEN_STAIRS.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		woodenStairs(KoratioBlocks.CYAN_ELVEN_STAIRS.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		woodenStairs(KoratioBlocks.GREEN_ELVEN_STAIRS.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		trapdoor(KoratioBlocks.ELVEN_TRAPDOOR.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		trapdoor(KoratioBlocks.BLUE_ELVEN_TRAPDOOR.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		trapdoor(KoratioBlocks.CYAN_ELVEN_TRAPDOOR.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		trapdoor(KoratioBlocks.GREEN_ELVEN_TRAPDOOR.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		sign(KoratioBlocks.ELVEN_SIGN.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		sign(KoratioBlocks.BLUE_ELVEN_SIGN.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		sign(KoratioBlocks.CYAN_ELVEN_SIGN.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		sign(KoratioBlocks.GREEN_ELVEN_SIGN.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		hangingSign(KoratioBlocks.ELVEN_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_ELVEN_LOG).save(output);
		hangingSign(KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG).save(output);
		hangingSign(KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG).save(output);
		hangingSign(KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG).save(output);
		woodenBoat(output, KoratioItems.ELVEN_BOAT.get(), KoratioBlocks.ELVEN_PLANKS.get());
		woodenBoat(output, KoratioItems.BLUE_ELVEN_BOAT.get(), KoratioBlocks.BLUE_ELVEN_PLANKS.get());
		woodenBoat(output, KoratioItems.CYAN_ELVEN_BOAT.get(), KoratioBlocks.CYAN_ELVEN_PLANKS.get());
		woodenBoat(output, KoratioItems.GREEN_ELVEN_BOAT.get(), KoratioBlocks.GREEN_ELVEN_PLANKS.get());
		chest(KoratioItems.ELVEN_CHEST.get(), KoratioBlocks.ELVEN_PLANKS).save(output);
		chest(KoratioItems.BLUE_ELVEN_CHEST.get(), KoratioBlocks.BLUE_ELVEN_PLANKS).save(output);
		chest(KoratioItems.CYAN_ELVEN_CHEST.get(), KoratioBlocks.CYAN_ELVEN_PLANKS).save(output);
		chest(KoratioItems.GREEN_ELVEN_CHEST.get(), KoratioBlocks.GREEN_ELVEN_PLANKS).save(output);
		bookshelf(output, KoratioItems.ELVEN_BOOKSHELF.get(), KoratioBlocks.ELVEN_PLANKS);
		bookshelf(output, KoratioItems.BLUE_ELVEN_BOOKSHELF.get(), KoratioBlocks.BLUE_ELVEN_PLANKS);
		bookshelf(output, KoratioItems.CYAN_ELVEN_BOOKSHELF.get(), KoratioBlocks.CYAN_ELVEN_PLANKS);
		bookshelf(output, KoratioItems.GREEN_ELVEN_BOOKSHELF.get(), KoratioBlocks.GREEN_ELVEN_PLANKS);
		leafPane(KoratioBlocks.ELVEN_LEAF_PANE.get(), KoratioBlocks.ELVEN_LEAVES.get()).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.CAKE).pattern("mmm").pattern("ses").pattern("www").define('m', Items.MILK_BUCKET).define('s', KoratioTags.Items.SUGAR).define('e', Tags.Items.EGGS).define('w', Items.WHEAT).unlockedBy("has_egg", has(Tags.Items.EGGS)).save(output);

		//CandyShaperRecipes candyShaping(output, result, mainFluid, secondFluid)
		for (CandyItem candy : ColoredCandyItem.candy()) {
			if (candy instanceof ColoredCandyItem colored) {
				if (colored.getBaseColor() == colored.getSecondColor()) {
					CandyShaperRecipeBuilder.recipe(candy, colored.getType().getTemplate()).requires(FluidIngredient.of(colored.getBaseColor().getFluid().get()), colored.getType().getCost()).unlockedBy("has_bucket", has(colored.getBaseColor().getFluid().get().getBucket())).save(output);
				} else {
					CandyShaperRecipeBuilder.recipe(candy, colored.getType().getTemplate()).requires(FluidIngredient.of(colored.getBaseColor().getFluid().get()), colored.getType().getCost()/2).requires(FluidIngredient.of(colored.getSecondColor().getFluid().get()), colored.getType().getCost()/2).unlockedBy("has_bucket", has(colored.getBaseColor().getFluid().get().getBucket())).save(output);
				}
			}
		}

		//BrewingRecipes
		
		//FurnaceRecipes
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.STICKY_WHITE_SUGAR_BUCKET.get()), RecipeCategory.MISC, KoratioItems.MOLTEN_WHITE_SUGAR_BUCKET.get(), 0.5F, 200).unlockedBy("has_sugar_bucket", has(KoratioItems.STICKY_WHITE_SUGAR_BUCKET.get())).save(output);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.STICKY_BLUE_SUGAR_BUCKET.get()), RecipeCategory.MISC, KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET.get(), 0.5F, 200).unlockedBy("has_sugar_bucket", has(KoratioItems.STICKY_BLUE_SUGAR_BUCKET.get())).save(output);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.STICKY_GREEN_SUGAR_BUCKET.get()), RecipeCategory.MISC, KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET.get(), 0.5F, 200).unlockedBy("has_sugar_bucket", has(KoratioItems.STICKY_GREEN_SUGAR_BUCKET.get())).save(output);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.STICKY_YELLOW_SUGAR_BUCKET.get()), RecipeCategory.MISC, KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET.get(), 0.5F, 200).unlockedBy("has_sugar_bucket", has(KoratioItems.STICKY_YELLOW_SUGAR_BUCKET.get())).save(output);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.STICKY_RED_SUGAR_BUCKET.get()), RecipeCategory.MISC, KoratioItems.MOLTEN_RED_SUGAR_BUCKET.get(), 0.5F, 200).unlockedBy("has_sugar_bucket", has(KoratioItems.STICKY_RED_SUGAR_BUCKET.get())).save(output);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_GINGERBREAD_BLOCK), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GINGERBREAD_BLOCK.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get())).save(output, Koratio.prefix("gingerbread_block_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_GINGERBREAD_BRICKS), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GINGERBREAD_BRICKS.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get())).save(output, Koratio.prefix("gingerbread_bricks_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get())).save(output, Koratio.prefix("large_gingerbread_bricks_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_GINGERBREAD_STAIRS), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GINGERBREAD_STAIRS.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get())).save(output, Koratio.prefix("gingerbread_stairs_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get())).save(output, Koratio.prefix("gingerbread_brick_stairs_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.get())).save(output, Koratio.prefix("large_gingerbread_brick_stairs_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_GINGERBREAD_SLAB), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GINGERBREAD_SLAB.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_GINGERBREAD_SLAB.get())).save(output, Koratio.prefix("gingerbread_slab_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get())).save(output, Koratio.prefix("gingerbread_brick_slab_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.get())).save(output, Koratio.prefix("large_gingerbread_brick_slab_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_GINGERBREAD_WALL), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GINGERBREAD_WALL.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_GINGERBREAD_WALL.get())).save(output, Koratio.prefix("gingerbread_wall_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GINGERBREAD_BRICK_WALL.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.get())).save(output, Koratio.prefix("gingerbread_brick_wall_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL), RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.get(), 0.5F, 200).unlockedBy("has_raw_gingerbread", has(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.get())).save(output, Koratio.prefix("large_gingerbread_brick_wall_from_smelting"));

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.RAW_PANGO.get()), RecipeCategory.FOOD, KoratioItems.CRACKED_PANGO.get(), 0.35F, 200).unlockedBy("has_raw_pango", has(KoratioItems.RAW_PANGO.get())).save(output);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.SPIKED_PORKCHOP.get()), RecipeCategory.FOOD, KoratioItems.COOKED_SPIKED_PORKCHOP.get(), 0.35F, 200).unlockedBy("has_spiked_porkchop", has(KoratioItems.SPIKED_PORKCHOP.get())).save(output);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.FLUFFER.get()), RecipeCategory.FOOD, KoratioItems.COOKED_FLUFFER.get(), 0.35F, 200).unlockedBy("has_fluffer", has(KoratioItems.FLUFFER.get())).save(output);

		oreSmelting(output, RUBY_SMELTABLES, RecipeCategory.MISC, KoratioItems.RUBY.get(), 1.0F, 200, "ruby");
		oreSmelting(output, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, KoratioItems.SAPPHIRE.get(), 1.0F, 200, "sapphire");
		oreSmelting(output, TOPAZ_SMELTABLES, RecipeCategory.MISC, KoratioItems.TOPAZ.get(), 1.0F, 200, "topaz");
		oreSmelting(output, ONYX_SMELTABLES, RecipeCategory.MISC, KoratioItems.ONYX.get(), 1.0F, 200, "onyx");
		
		//SmokingRecipes
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(KoratioItems.SPIKED_PORKCHOP.get()), RecipeCategory.FOOD, KoratioItems.COOKED_SPIKED_PORKCHOP.get(), 0.35F, 100).unlockedBy("has_spiked_porkchop", has(KoratioItems.SPIKED_PORKCHOP.get())).save(output, Koratio.prefix("cooked_spiked_porkchop_from_smoking"));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(KoratioItems.FLUFFER.get()), RecipeCategory.FOOD, KoratioItems.COOKED_FLUFFER.get(), 0.35F, 100).unlockedBy("has_fluffer", has(KoratioItems.FLUFFER.get())).save(output, Koratio.prefix("cooked_fluffer_from_smoking"));

		//BlastingRecipes
		oreBlasting(output, RUBY_SMELTABLES, RecipeCategory.MISC, KoratioItems.RUBY.get(), 1.0F, 100, "ruby");
		oreBlasting(output, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, KoratioItems.SAPPHIRE.get(), 1.0F, 100, "sapphire");
		oreBlasting(output, TOPAZ_SMELTABLES, RecipeCategory.MISC, KoratioItems.TOPAZ.get(), 1.0F, 100, "topaz");
		oreBlasting(output, ONYX_SMELTABLES, RecipeCategory.MISC, KoratioItems.ONYX.get(), 1.0F, 100, "onyx");

		//CampfireRecipes
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(KoratioItems.RAW_PANGO.get()), RecipeCategory.FOOD, KoratioItems.CRACKED_PANGO.get(), 0.35F, 600).unlockedBy("has_raw_pango", has(KoratioItems.RAW_PANGO.get())).save(output, Koratio.prefix("cracked_pango_from_campfire_cooking"));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(KoratioItems.SPIKED_PORKCHOP.get()), RecipeCategory.FOOD, KoratioItems.COOKED_SPIKED_PORKCHOP.get(), 0.35F, 600).unlockedBy("has_spiked_porkchop", has(KoratioItems.SPIKED_PORKCHOP.get())).save(output, Koratio.prefix("cooked_spiked_porkchop_from_campfire_cooking"));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(KoratioItems.FLUFFER.get()), RecipeCategory.FOOD, KoratioItems.COOKED_FLUFFER.get(), 0.35F, 600).unlockedBy("has_fluffer", has(KoratioItems.FLUFFER.get())).save(output, Koratio.prefix("cooked_fluffer_from_campfire_cooking"));

		//SmithingRecipes
		netheriteSmithing(output, KoratioItems.DIAMOND_ICING_SPATULA.get(), RecipeCategory.TOOLS, KoratioItems.NETHERITE_ICING_SPATULA.get());
		
		//StonecuttingRecipes

		//WoodcuttingRecipes
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_PLANKS, Blocks.OAK_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_PLANKS, Blocks.OAK_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_PLANKS, Blocks.STRIPPED_OAK_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_PLANKS, Blocks.STRIPPED_OAK_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_STAIRS, Blocks.OAK_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_STAIRS, Blocks.OAK_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_STAIRS, Blocks.STRIPPED_OAK_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_STAIRS, Blocks.STRIPPED_OAK_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_SLAB, Blocks.OAK_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_SLAB, Blocks.OAK_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_SLAB, Blocks.STRIPPED_OAK_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_SLAB, Blocks.STRIPPED_OAK_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_SLAB, Blocks.OAK_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_STAIRS, Blocks.OAK_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_PLANKS, Blocks.STRIPPED_SPRUCE_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_PLANKS, Blocks.STRIPPED_SPRUCE_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_STAIRS, Blocks.STRIPPED_SPRUCE_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_STAIRS, Blocks.STRIPPED_SPRUCE_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_SLAB, Blocks.STRIPPED_SPRUCE_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_SLAB, Blocks.STRIPPED_SPRUCE_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_PLANKS, Blocks.BIRCH_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_PLANKS, Blocks.BIRCH_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_PLANKS, Blocks.STRIPPED_BIRCH_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_PLANKS, Blocks.STRIPPED_BIRCH_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_STAIRS, Blocks.BIRCH_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_STAIRS, Blocks.BIRCH_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_STAIRS, Blocks.STRIPPED_BIRCH_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_STAIRS, Blocks.STRIPPED_BIRCH_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_SLAB, Blocks.BIRCH_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_SLAB, Blocks.BIRCH_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_SLAB, Blocks.STRIPPED_BIRCH_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_SLAB, Blocks.STRIPPED_BIRCH_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_SLAB, Blocks.BIRCH_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_STAIRS, Blocks.BIRCH_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_PLANKS, Blocks.STRIPPED_JUNGLE_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_PLANKS, Blocks.STRIPPED_JUNGLE_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_STAIRS, Blocks.STRIPPED_JUNGLE_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_STAIRS, Blocks.STRIPPED_JUNGLE_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_SLAB, Blocks.STRIPPED_JUNGLE_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_SLAB, Blocks.STRIPPED_JUNGLE_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_PLANKS, Blocks.ACACIA_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_PLANKS, Blocks.ACACIA_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_PLANKS, Blocks.STRIPPED_ACACIA_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_PLANKS, Blocks.STRIPPED_ACACIA_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_STAIRS, Blocks.ACACIA_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_STAIRS, Blocks.ACACIA_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_STAIRS, Blocks.STRIPPED_ACACIA_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_STAIRS, Blocks.STRIPPED_ACACIA_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_SLAB, Blocks.ACACIA_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_SLAB, Blocks.ACACIA_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_SLAB, Blocks.STRIPPED_ACACIA_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_SLAB, Blocks.STRIPPED_ACACIA_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_STAIRS, Blocks.ACACIA_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_PLANKS, Blocks.STRIPPED_DARK_OAK_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_PLANKS, Blocks.STRIPPED_DARK_OAK_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_STAIRS, Blocks.STRIPPED_DARK_OAK_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_STAIRS, Blocks.STRIPPED_DARK_OAK_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_SLAB, Blocks.STRIPPED_DARK_OAK_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_SLAB, Blocks.STRIPPED_DARK_OAK_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_PLANKS, Blocks.STRIPPED_MANGROVE_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_PLANKS, Blocks.STRIPPED_MANGROVE_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_MANGROVE_WOOD, Blocks.MANGROVE_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_STAIRS, Blocks.STRIPPED_MANGROVE_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_STAIRS, Blocks.STRIPPED_MANGROVE_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_SLAB, Blocks.STRIPPED_MANGROVE_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_SLAB, Blocks.STRIPPED_MANGROVE_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_PLANKS, Blocks.CHERRY_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_PLANKS, Blocks.CHERRY_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_PLANKS, Blocks.STRIPPED_CHERRY_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_PLANKS, Blocks.STRIPPED_CHERRY_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_CHERRY_WOOD, Blocks.CHERRY_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_STAIRS, Blocks.CHERRY_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_STAIRS, Blocks.CHERRY_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_STAIRS, Blocks.STRIPPED_CHERRY_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_STAIRS, Blocks.STRIPPED_CHERRY_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_SLAB, Blocks.CHERRY_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_SLAB, Blocks.CHERRY_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_SLAB, Blocks.STRIPPED_CHERRY_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_SLAB, Blocks.STRIPPED_CHERRY_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_SLAB, Blocks.CHERRY_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_STAIRS, Blocks.CHERRY_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_STEM, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_HYPHAE, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_PLANKS, Blocks.STRIPPED_CRIMSON_STEM, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_PLANKS, Blocks.STRIPPED_CRIMSON_HYPHAE, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_STEM, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_HYPHAE, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_STEM, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_HYPHAE, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_STAIRS, Blocks.STRIPPED_CRIMSON_STEM, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_STAIRS, Blocks.STRIPPED_CRIMSON_HYPHAE, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_STEM, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_HYPHAE, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_SLAB, Blocks.STRIPPED_CRIMSON_STEM, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_SLAB, Blocks.STRIPPED_CRIMSON_HYPHAE, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_PLANKS, Blocks.WARPED_STEM, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_PLANKS, Blocks.WARPED_HYPHAE, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_PLANKS, Blocks.STRIPPED_WARPED_STEM, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_PLANKS, Blocks.STRIPPED_WARPED_HYPHAE, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_STEM, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_HYPHAE, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_STAIRS, Blocks.WARPED_STEM, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_STAIRS, Blocks.WARPED_HYPHAE, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_STAIRS, Blocks.STRIPPED_WARPED_STEM, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_STAIRS, Blocks.STRIPPED_WARPED_HYPHAE, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_SLAB, Blocks.WARPED_STEM, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_SLAB, Blocks.WARPED_HYPHAE, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_SLAB, Blocks.STRIPPED_WARPED_STEM, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_SLAB, Blocks.STRIPPED_WARPED_HYPHAE, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_SLAB, Blocks.WARPED_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_STAIRS, Blocks.WARPED_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_PLANKS, KoratioBlocks.PANGO_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_PLANKS, KoratioBlocks.PANGO_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_PLANKS, KoratioBlocks.STRIPPED_PANGO_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_PLANKS, KoratioBlocks.STRIPPED_PANGO_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_PANGO_LOG, KoratioBlocks.PANGO_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_PANGO_WOOD, KoratioBlocks.PANGO_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_STAIRS, KoratioBlocks.PANGO_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_STAIRS, KoratioBlocks.PANGO_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_STAIRS, KoratioBlocks.STRIPPED_PANGO_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_STAIRS, KoratioBlocks.STRIPPED_PANGO_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_SLAB, KoratioBlocks.PANGO_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_SLAB, KoratioBlocks.PANGO_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_SLAB, KoratioBlocks.STRIPPED_PANGO_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_SLAB, KoratioBlocks.STRIPPED_PANGO_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_SLAB, KoratioBlocks.PANGO_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.PANGO_STAIRS, KoratioBlocks.PANGO_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_PLANKS, KoratioBlocks.RUGONA_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_PLANKS, KoratioBlocks.RUGONA_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_PLANKS, KoratioBlocks.STRIPPED_RUGONA_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_PLANKS, KoratioBlocks.STRIPPED_RUGONA_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_RUGONA_LOG, KoratioBlocks.RUGONA_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_RUGONA_WOOD, KoratioBlocks.RUGONA_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_STAIRS, KoratioBlocks.RUGONA_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_STAIRS, KoratioBlocks.RUGONA_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_STAIRS, KoratioBlocks.STRIPPED_RUGONA_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_STAIRS, KoratioBlocks.STRIPPED_RUGONA_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_SLAB, KoratioBlocks.RUGONA_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_SLAB, KoratioBlocks.RUGONA_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_SLAB, KoratioBlocks.STRIPPED_RUGONA_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_SLAB, KoratioBlocks.STRIPPED_RUGONA_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_SLAB, KoratioBlocks.RUGONA_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.RUGONA_STAIRS, KoratioBlocks.RUGONA_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_PLANKS, KoratioBlocks.VARESO_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_PLANKS, KoratioBlocks.VARESO_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_PLANKS, KoratioBlocks.STRIPPED_VARESO_LOG, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_PLANKS, KoratioBlocks.STRIPPED_VARESO_WOOD, 6);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_VARESO_LOG, KoratioBlocks.VARESO_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_VARESO_WOOD, KoratioBlocks.VARESO_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_STAIRS, KoratioBlocks.VARESO_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_STAIRS, KoratioBlocks.VARESO_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_STAIRS, KoratioBlocks.STRIPPED_VARESO_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_STAIRS, KoratioBlocks.STRIPPED_VARESO_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_SLAB, KoratioBlocks.VARESO_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_SLAB, KoratioBlocks.VARESO_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_SLAB, KoratioBlocks.STRIPPED_VARESO_LOG, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_SLAB, KoratioBlocks.STRIPPED_VARESO_WOOD, 12);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_SLAB, KoratioBlocks.VARESO_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.VARESO_STAIRS, KoratioBlocks.VARESO_PLANKS, 1);

		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.STRIPPED_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.STRIPPED_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.STRIPPED_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.STRIPPED_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_PLANKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_PLANKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_PLANKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_ELVEN_LOG, KoratioBlocks.ELVEN_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, KoratioBlocks.ELVEN_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, KoratioBlocks.ELVEN_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, KoratioBlocks.ELVEN_LOG, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_ELVEN_WOOD, KoratioBlocks.ELVEN_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, KoratioBlocks.ELVEN_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, KoratioBlocks.ELVEN_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, KoratioBlocks.ELVEN_WOOD, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.STRIPPED_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.STRIPPED_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.STRIPPED_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.STRIPPED_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 4);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.STRIPPED_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.STRIPPED_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.STRIPPED_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.STRIPPED_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.STRIPPED_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.STRIPPED_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.STRIPPED_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.STRIPPED_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD, 8);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_SLAB, KoratioBlocks.ELVEN_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_SLAB, KoratioBlocks.BLUE_ELVEN_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_SLAB, KoratioBlocks.CYAN_ELVEN_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_SLAB, KoratioBlocks.GREEN_ELVEN_PLANKS, 2);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.ELVEN_STAIRS, KoratioBlocks.ELVEN_PLANKS, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLUE_ELVEN_STAIRS, KoratioBlocks.BLUE_ELVEN_PLANKS, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CYAN_ELVEN_STAIRS, KoratioBlocks.CYAN_ELVEN_PLANKS, 1);
		woodcutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.GREEN_ELVEN_STAIRS, KoratioBlocks.GREEN_ELVEN_PLANKS, 1);
	}
	
	protected static ShapedRecipeBuilder door(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, output, 3).pattern("xx").pattern("xx").pattern("xx").define('x', input.get()).group("wooden_door").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}

	protected static ShapedRecipeBuilder tallDoor(Block output, Block input, Block door, String group) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, output, 2).pattern("x").pattern("#").pattern("x").define('x', input).define('#', door).group(group).unlockedBy("has_door", has(door));
	}
	
	protected static ShapedRecipeBuilder fence(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 3).pattern("x#x").pattern("x#x").define('x', input.get()).define('#', Items.STICK).group("wooden_fence").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder fenceGate(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output).pattern("x#x").pattern("x#x").define('#', input.get()).define('x', Items.STICK).group("wooden_fence_gate").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder pressurePlate(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, output).pattern("xx").define('x', input.get()).group("wooden_pressure_plate").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapelessRecipeBuilder button(Block output, DeferredBlock<? extends Block> input) {
		return ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, output).requires(input.get()).unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder slab(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6).pattern("xxx").define('x', input.get()).group("wooden_slab").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder woodenSlab(Block output, DeferredBlock<? extends Block> input) {
		return slab(output, input).group("wooden_slab");
	}
	
	protected static ShapedRecipeBuilder stairs(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4).pattern("x  ").pattern("xx ").pattern("xxx").define('x', input.get()).unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder woodenStairs(Block output, DeferredBlock<? extends Block> input) {
		return stairs(output, input).group("wooden_stairs");
	}
	
	protected static ShapedRecipeBuilder trapdoor(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, output, 2).pattern("xxx").pattern("xxx").define('x', input.get()).group("wooden_trapdoor").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}

	protected static ShapedRecipeBuilder leafPane(Block output, Block input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 16).pattern("xxx").pattern("xxx").define('x', input).group("leaf_pane").unlockedBy("has_leaves", has(input));
	}
	
	protected static ShapedRecipeBuilder wall(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2).pattern("xxx").pattern("xxx").define('x', input.get()).unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder sign(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 3).pattern("xxx").pattern("xxx").pattern(" # ").define('x', input.get()).define('#', Items.STICK).group("wooden_sign").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder hangingSign(Block output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 6).pattern("# #").pattern("xxx").pattern("xxx").define('x', input.get()).define('#', Items.CHAIN).group("wooden_hanging_sign").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder chest(BlockItem output, DeferredBlock<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 2).pattern("###").pattern("#x#").pattern("###").define('#', input.get()).define('x', Tags.Items.CHESTS_WOODEN).group("chests").unlockedBy("has_planks", has(input.get()));
	}

	protected static void bookshelf(RecipeOutput output, ItemLike result, DeferredBlock<? extends Block> input) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result).pattern("###").pattern("xxx").pattern("###").define('#', input.get()).define('x', Items.BOOK).group("bookshelfs").unlockedBy("has_books", has(Items.BOOK));
	}
	
	protected static void chestBoat(RecipeOutput output, ItemLike result, ItemLike input) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, result).requires(Tags.Items.CHESTS_WOODEN).requires(input).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(output);
	}
	
	protected static void twoByTwo(RecipeOutput output, ItemLike result, ItemLike input) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4).pattern("##").pattern("##").define('#', input).unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(input.asItem()).getPath(), has(MinMaxBounds.Ints.atLeast(4), input)).save(output);
	}
	
	protected static ShapelessRecipeBuilder oneToOne(Item output, Item input) {
		return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output).requires(input).unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(input).getPath(), has(input));
	}
	
	protected static ShapedRecipeBuilder compress(ItemLike output, ItemLike input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output).pattern("xxx").pattern("xxx").pattern("xxx").define('x', input).unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(input.asItem()).getPath(), has(MinMaxBounds.Ints.atLeast(9), input));
	}
	
	protected static ShapelessRecipeBuilder decompress(Item output, ItemLike input) {
		return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, 9).requires(input).unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(input.asItem()).getPath(), has(input));
	}
	
	protected static void sword(RecipeOutput output, Item result, Ingredient ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('#', Items.STICK).define('X', ingredient).pattern("X").pattern("X").pattern("#").unlockedBy("has_stick", has(Items.STICK)).save(output);
	}
	
	protected static void pickaxe(RecipeOutput output, Item result, Ingredient ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_stick", has(Items.STICK)).save(output);
	}
	
	protected static void axe(RecipeOutput output, Item result, Ingredient ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_stick", has(Items.STICK)).save(output);
	}
	
	protected static void shovel(RecipeOutput output, Item result, Ingredient ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern("X").pattern("#").pattern("#").unlockedBy("has_stick", has(Items.STICK)).save(output);
	}
	
	protected static void hoe(RecipeOutput output, Item result, Ingredient ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_stick", has(Items.STICK)).save(output);
	}

	protected static void spatula(RecipeOutput output, Item result, Ingredient ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern(" X").pattern("X ").pattern("# ").unlockedBy("has_stick", has(Items.STICK)).save(output);
	}
	
	protected static void helmet(RecipeOutput output, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('X', ingredient).pattern("XXX").pattern("X X").unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(ingredient).getPath(), has(ingredient)).save(output);
	}
	
	protected static void chestplate(RecipeOutput output, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('X', ingredient).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(ingredient).getPath(), has(ingredient)).save(output);
	}
	
	protected static void leggings(RecipeOutput output, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('X', ingredient).pattern("XXX").pattern("X X").pattern("X X").unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(ingredient).getPath(), has(ingredient)).save(output);
	}
	
	protected static void boots(RecipeOutput output, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('X', ingredient).pattern("X X").pattern("X X").unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(ingredient).getPath(), has(ingredient)).save(output);
	}
	
	protected static void oreSmelting(RecipeOutput output, List<ItemLike> input, RecipeCategory category, ItemLike result, float xp, int time, String group) {
		oreCooking(output, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, input, category, result, xp, time, group, "_from_smelting");
	}

	protected static void oreBlasting(RecipeOutput output, List<ItemLike> input, RecipeCategory category, ItemLike result, float xp, int time, String group) {
		oreCooking(output, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, input, category, result, xp, time, group, "_from_blasting");
	}

	protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput output, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> input, RecipeCategory category, ItemLike result, float xp, int time, String group, String extra) {
		for(ItemLike itemlike : input) {
			SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, xp, time, serializer, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike)).save(output, Koratio.prefix(getItemName(result) + extra + "_" + getItemName(itemlike)));
		}
	}
	
	protected static void stonecutterResultFromBase(RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike ingredient, int amount) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), category, result, amount).unlockedBy("has_"+getItemName(ingredient), has(ingredient)).save(output, Koratio.prefix(getItemName(result)+"_from_"+getItemName(ingredient) + "_stonecutting"));
	}

	protected static void woodcutterResultFromBase(RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike ingredient, int amount) {
		woodcutting(Ingredient.of(ingredient), category, result, amount).unlockedBy("has_"+getItemName(ingredient), has(ingredient)).save(output, Koratio.prefix(getItemName(result)+"_from_"+getItemName(ingredient) + "_woodcutting"));
	}

	public static SingleItemRecipeBuilder woodcutting(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult, int pCount) {
		return new SingleItemRecipeBuilder(pCategory, WoodcutterRecipe::new, pIngredient, pResult, pCount);
	}

	protected static String getItemName(ItemLike item) {
		return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
	}
}