package net.setrion.koratio.data;

import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;

import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioTags;

public class RecipeGenerator extends RecipeProvider {
	
	public static final ImmutableList<ItemLike> ARSOY_SMELTABLES = ImmutableList.of(KoratioItems.ARSOY_ORE.get(), KoratioItems.DEEPSLATE_ARSOY_ORE.get(), KoratioItems.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get(), KoratioItems.RAW_ARSOY.get());

	public RecipeGenerator(PackOutput output) {
		super(output);
	}
	
	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		//ShapelessRecipes
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KoratioItems.RAINBOW_GEM.get()).requires(Items.LAPIS_LAZULI).requires(Items.AMETHYST_SHARD).requires(Items.QUARTZ).requires(Items.GLOWSTONE_DUST).requires(Items.NETHER_STAR).requires(Items.REDSTONE).requires(Items.EMERALD).requires(Items.NETHERITE_INGOT).requires(Items.DIAMOND).unlockedBy("has_netherite", has(Items.NETHERITE_INGOT)).save(consumer);
		decompress(KoratioItems.RAINBOW_GEM.get(), KoratioBlocks.RAINBOW_GEM_BLOCK.get()).save(consumer, Koratio.prefix("rainbow_gem_from_block"));
		decompress(KoratioItems.RAW_ARSOY.get(), KoratioBlocks.RAW_ARSOY_BLOCK.get()).save(consumer, Koratio.prefix("raw_arsoy_from_block"));
		decompress(KoratioItems.ARSOY_INGOT.get(), KoratioBlocks.ARSOY_BLOCK.get()).save(consumer, Koratio.prefix("arsoy_ingot_from_block"));
		decompress(KoratioItems.ARSOY_NUGGET.get(), KoratioItems.ARSOY_INGOT.get()).save(consumer, Koratio.prefix("arsoy_nugget_from_ingot"));
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, KoratioItems.RED_SUGAR.get()).requires(Items.SUGAR, 8).requires(Tags.Items.DYES_RED).group("red_sugar").unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, KoratioItems.BLUE_SUGAR.get()).requires(Items.SUGAR, 8).requires(Tags.Items.DYES_BLUE).group("blue_sugar").unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, KoratioItems.YELLOW_SUGAR.get()).requires(Items.SUGAR, 8).requires(Tags.Items.DYES_YELLOW).group("yellow_sugar").unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, KoratioItems.GREEN_SUGAR.get()).requires(Items.SUGAR, 8).requires(Tags.Items.DYES_GREEN).group("green_sugar").unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);
		
		oneToOne(Items.COOKIE, KoratioItems.COOKIE_FLOWER.get()).save(consumer, Koratio.prefix("cookie_from_flower"));
		oneToOne(Items.SUGAR, KoratioItems.WHITE_SUGARGLASS_FLOWER.get()).save(consumer, Koratio.prefix("sugar_from_flower"));
		oneToOne(KoratioItems.BLUE_SUGAR.get(), KoratioItems.BLUE_SUGARGLASS_FLOWER.get()).save(consumer, Koratio.prefix("blue_sugar_from_flower"));
		oneToOne(KoratioItems.GREEN_SUGAR.get(), KoratioItems.GREEN_SUGARGLASS_FLOWER.get()).save(consumer, Koratio.prefix("green_sugar_from_flower"));
		oneToOne(KoratioItems.YELLOW_SUGAR.get(), KoratioItems.YELLOW_SUGARGLASS_FLOWER.get()).save(consumer, Koratio.prefix("yellow_sugar_from_flower"));
		oneToOne(KoratioItems.RED_SUGAR.get(), KoratioItems.RED_SUGARGLASS_FLOWER.get()).save(consumer, Koratio.prefix("red_sugar_from_flower"));
		
		decompress(Items.SUGAR, KoratioBlocks.SUGAR_BLOCK.get()).save(consumer, Koratio.prefix("sugar_from_block"));
		decompress(KoratioItems.RED_SUGAR.get(), KoratioBlocks.RED_SUGAR_BLOCK.get()).save(consumer, Koratio.prefix("red_sugar_from_block"));
		decompress(KoratioItems.BLUE_SUGAR.get(), KoratioBlocks.BLUE_SUGAR_BLOCK.get()).save(consumer, Koratio.prefix("blue_sugar_from_block"));
		decompress(KoratioItems.YELLOW_SUGAR.get(), KoratioBlocks.YELLOW_SUGAR_BLOCK.get()).save(consumer, Koratio.prefix("yellow_sugar_from_block"));
		decompress(KoratioItems.GREEN_SUGAR.get(), KoratioBlocks.GREEN_SUGAR_BLOCK.get()).save(consumer, Koratio.prefix("green_sugar_from_block"));
		
		button(KoratioBlocks.PANGO_BUTTON.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		planksFromLogs(consumer, KoratioBlocks.PANGO_PLANKS.get(), KoratioTags.Items.PANGO_LOGS, 4);
		chestBoat(consumer, KoratioItems.PANGO_CHEST_BOAT.get(), KoratioItems.PANGO_BOAT.get());
		
		button(KoratioBlocks.RUGONA_BUTTON.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		planksFromLogs(consumer, KoratioBlocks.RUGONA_PLANKS.get(), KoratioTags.Items.RUGONA_LOGS, 4);
		chestBoat(consumer, KoratioItems.RUGONA_CHEST_BOAT.get(), KoratioItems.RUGONA_BOAT.get());
		
		button(KoratioBlocks.VARESO_BUTTON.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		planksFromLogs(consumer, KoratioBlocks.VARESO_PLANKS.get(), KoratioTags.Items.VARESO_LOGS, 4);
		chestBoat(consumer, KoratioItems.VARESO_CHEST_BOAT.get(), KoratioItems.VARESO_BOAT.get());
		
		//ShapedRecipes
		compress(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), KoratioItems.RAINBOW_GEM.get()).save(consumer);
		compress(KoratioBlocks.RAW_ARSOY_BLOCK.get(), KoratioItems.RAW_ARSOY.get()).save(consumer);
		compress(KoratioBlocks.ARSOY_BLOCK.get(), KoratioItems.ARSOY_INGOT.get()).save(consumer);
		compress(KoratioItems.ARSOY_INGOT.get(), KoratioItems.ARSOY_NUGGET.get()).save(consumer);
		compress(KoratioBlocks.SUGAR_BLOCK.get(), Items.SUGAR).save(consumer);
		compress(KoratioBlocks.RED_SUGAR_BLOCK.get(), KoratioItems.RED_SUGAR.get()).save(consumer);
		compress(KoratioBlocks.BLUE_SUGAR_BLOCK.get(), KoratioItems.BLUE_SUGAR.get()).save(consumer);
		compress(KoratioBlocks.YELLOW_SUGAR_BLOCK.get(), KoratioItems.YELLOW_SUGAR.get()).save(consumer);
		compress(KoratioBlocks.GREEN_SUGAR_BLOCK.get(), KoratioItems.GREEN_SUGAR.get()).save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, KoratioItems.GOLDEN_BREAD.get()).pattern("###").define('#', KoratioItems.GOLDEN_WHEAT.get()).unlockedBy("has_golden_wheat", has(KoratioItems.GOLDEN_WHEAT.get())).save(consumer);
		
		stairs(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE).save(consumer);
		slab(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE).save(consumer);
		wall(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CHISELED_BLOOD_STAINED_DEEPSLATE.get()).pattern("#").pattern("#").define('#', KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get()).unlockedBy("has_cobbled_deepslate_slab", has(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get())).save(consumer);
		twoByTwo(consumer, KoratioItems.POLISHED_BLOOD_STAINED_DEEPSLATE.get(), KoratioItems.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stairs(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE).save(consumer);
		slab(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE).save(consumer);
		wall(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE).save(consumer);
		twoByTwo(consumer, KoratioItems.BLOOD_STAINED_DEEPSLATE_BRICKS.get(), KoratioItems.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stairs(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS).save(consumer);
		slab(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS).save(consumer);
		wall(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS).save(consumer);
		twoByTwo(consumer, KoratioItems.BLOOD_STAINED_DEEPSLATE_TILES.get(), KoratioItems.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		stairs(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES).save(consumer);
		slab(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES).save(consumer);
		wall(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES).save(consumer);
		
		woodFromLogs(consumer, KoratioBlocks.PANGO_WOOD.get(), KoratioBlocks.PANGO_LOG.get());
		woodFromLogs(consumer, KoratioBlocks.STRIPPED_PANGO_WOOD.get(), KoratioBlocks.STRIPPED_PANGO_LOG.get());
		door(KoratioBlocks.PANGO_DOOR.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		fence(KoratioBlocks.PANGO_FENCE.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		fenceGate(KoratioBlocks.PANGO_FENCE_GATE.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		pressurePlate(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		woodenSlab(KoratioBlocks.PANGO_SLAB.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		woodenStairs(KoratioBlocks.PANGO_STAIRS.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		trapdoor(KoratioBlocks.PANGO_TRAPDOOR.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		sign(KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		hangingSign(KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_PANGO_LOG).save(consumer);
		woodenBoat(consumer, KoratioItems.PANGO_BOAT.get(), KoratioBlocks.PANGO_PLANKS.get());
		chest(KoratioItems.PANGO_CHEST.get(), KoratioBlocks.PANGO_PLANKS).save(consumer);
		
		woodFromLogs(consumer, KoratioBlocks.RUGONA_WOOD.get(), KoratioBlocks.RUGONA_LOG.get());
		woodFromLogs(consumer, KoratioBlocks.STRIPPED_RUGONA_WOOD.get(), KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		door(KoratioBlocks.RUGONA_DOOR.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		fence(KoratioBlocks.RUGONA_FENCE.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		fenceGate(KoratioBlocks.RUGONA_FENCE_GATE.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		pressurePlate(KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		woodenSlab(KoratioBlocks.RUGONA_SLAB.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		woodenStairs(KoratioBlocks.RUGONA_STAIRS.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		trapdoor(KoratioBlocks.RUGONA_TRAPDOOR.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		sign(KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		hangingSign(KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_RUGONA_LOG).save(consumer);
		woodenBoat(consumer, KoratioItems.RUGONA_BOAT.get(), KoratioBlocks.RUGONA_PLANKS.get());
		chest(KoratioItems.RUGONA_CHEST.get(), KoratioBlocks.RUGONA_PLANKS).save(consumer);
		
		woodFromLogs(consumer, KoratioBlocks.VARESO_WOOD.get(), KoratioBlocks.VARESO_LOG.get());
		woodFromLogs(consumer, KoratioBlocks.STRIPPED_VARESO_WOOD.get(), KoratioBlocks.STRIPPED_VARESO_LOG.get());
		door(KoratioBlocks.VARESO_DOOR.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		fence(KoratioBlocks.VARESO_FENCE.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		fenceGate(KoratioBlocks.VARESO_FENCE_GATE.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		pressurePlate(KoratioBlocks.VARESO_PRESSURE_PLATE.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		woodenSlab(KoratioBlocks.VARESO_SLAB.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		woodenStairs(KoratioBlocks.VARESO_STAIRS.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		trapdoor(KoratioBlocks.VARESO_TRAPDOOR.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		sign(KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		hangingSign(KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.STRIPPED_VARESO_LOG).save(consumer);
		woodenBoat(consumer, KoratioItems.VARESO_BOAT.get(), KoratioBlocks.VARESO_PLANKS.get());
		chest(KoratioItems.VARESO_CHEST.get(), KoratioBlocks.VARESO_PLANKS).save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.CAKE).pattern("mmm").pattern("ses").pattern("www").define('m', Items.MILK_BUCKET).define('s', KoratioTags.Items.SUGAR).define('e', Tags.Items.EGGS).define('w', Items.WHEAT).unlockedBy("has_egg", has(Tags.Items.EGGS)).save(consumer);
		
		helmet(consumer, KoratioItems.ARSOY_HELMET.get(), KoratioItems.ARSOY_INGOT.get());
		chestplate(consumer, KoratioItems.ARSOY_CHESTPLATE.get(), KoratioItems.ARSOY_INGOT.get());
		leggings(consumer, KoratioItems.ARSOY_LEGGINGS.get(), KoratioItems.ARSOY_INGOT.get());
		boots(consumer, KoratioItems.ARSOY_BOOTS.get(), KoratioItems.ARSOY_INGOT.get());
		sword(consumer, KoratioItems.ARSOY_SWORD.get(), KoratioItems.ARSOY_INGOT.get());
		pickaxe(consumer, KoratioItems.ARSOY_PICKAXE.get(), KoratioItems.ARSOY_INGOT.get());
		axe(consumer, KoratioItems.ARSOY_AXE.get(), KoratioItems.ARSOY_INGOT.get());
		shovel(consumer, KoratioItems.ARSOY_SHOVEL.get(), KoratioItems.ARSOY_INGOT.get());
		hoe(consumer, KoratioItems.ARSOY_HOE.get(), KoratioItems.ARSOY_INGOT.get());
		
		//BrewingRecipes
		
		//FurnaceRecipes
		oreSmelting(consumer, ARSOY_SMELTABLES, RecipeCategory.MISC, KoratioItems.ARSOY_INGOT.get(), 0.7F, 200, "arsoy_ingot");
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.BLOOD_STAINED_COBBLED_DEEPSLATE.get()), RecipeCategory.BUILDING_BLOCKS, KoratioItems.BLOOD_STAINED_DEEPSLATE.get(), 0.1F, 200).unlockedBy("has_blood_stained_cobbled_deepslate", has(KoratioItems.BLOOD_STAINED_COBBLED_DEEPSLATE.get())).save(consumer);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.BLOOD_STAINED_DEEPSLATE_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, KoratioItems.CRACKED_BLOOD_STAINED_DEEPSLATE_BRICKS.get(), 0.1F, 200).unlockedBy("has_blood_stained_deepslate_bricks", has(KoratioItems.BLOOD_STAINED_DEEPSLATE_BRICKS.get())).save(consumer);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.BLOOD_STAINED_DEEPSLATE_TILES.get()), RecipeCategory.BUILDING_BLOCKS, KoratioItems.CRACKED_BLOOD_STAINED_DEEPSLATE_TILES.get(), 0.1F, 200).unlockedBy("has_blood_stained_deepslate_tiles", has(KoratioItems.BLOOD_STAINED_DEEPSLATE_TILES.get())).save(consumer);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.ARSOY_PICKAXE.get(), KoratioItems.ARSOY_SHOVEL.get(), KoratioItems.ARSOY_AXE.get(), KoratioItems.ARSOY_HOE.get(), KoratioItems.ARSOY_SWORD.get(), KoratioItems.ARSOY_HELMET.get(), KoratioItems.ARSOY_CHESTPLATE.get(), KoratioItems.ARSOY_LEGGINGS.get(), KoratioItems.ARSOY_BOOTS.get()), RecipeCategory.MISC, KoratioItems.ARSOY_NUGGET.get(), 0.1F, 200).unlockedBy("has_arsoy_pickaxe", has(KoratioItems.ARSOY_PICKAXE.get())).unlockedBy("has_arsoy_shovel", has(KoratioItems.ARSOY_SHOVEL.get())).unlockedBy("has_arsoy_axe", has(KoratioItems.ARSOY_AXE.get())).unlockedBy("has_arsoy_hoe", has(KoratioItems.ARSOY_HOE.get())).unlockedBy("has_arsoy_sword", has(KoratioItems.ARSOY_SWORD.get())).unlockedBy("has_arsoy_helmet", has(KoratioItems.ARSOY_HELMET.get())).unlockedBy("has_arsoy_chestplate", has(KoratioItems.ARSOY_CHESTPLATE.get())).unlockedBy("has_arsoy_leggings", has(KoratioItems.ARSOY_LEGGINGS.get())).unlockedBy("has_arsoy_boots", has(KoratioItems.ARSOY_BOOTS.get())).save(consumer, Koratio.prefix("arsoy_nugget_from_smelting"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.RAW_PANGO.get()), RecipeCategory.FOOD, KoratioItems.CRACKED_PANGO.get(), 0.35F, 200).unlockedBy("has_raw_pango", has(KoratioItems.RAW_PANGO.get())).save(consumer);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(KoratioItems.SPIKED_PORKCHOP.get()), RecipeCategory.FOOD, KoratioItems.COOKED_SPIKED_PORKCHOP.get(), 0.35F, 200).unlockedBy("has_spiked_porkchop", has(KoratioItems.SPIKED_PORKCHOP.get())).save(consumer);
		
		//SmokingRecipes
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(KoratioItems.SPIKED_PORKCHOP.get()), RecipeCategory.FOOD, KoratioItems.COOKED_SPIKED_PORKCHOP.get(), 0.35F, 100).unlockedBy("has_spiked_porkchop", has(KoratioItems.SPIKED_PORKCHOP.get())).save(consumer, Koratio.prefix("cooked_spiked_porkchop_from_smoking"));
		
		//BlastingRecipes
		oreBlasting(consumer, ARSOY_SMELTABLES, RecipeCategory.MISC, KoratioItems.ARSOY_INGOT.get(), 0.7F, 100, "arsoy_ingot");
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(KoratioItems.ARSOY_PICKAXE.get(), KoratioItems.ARSOY_SHOVEL.get(), KoratioItems.ARSOY_AXE.get(), KoratioItems.ARSOY_HOE.get(), KoratioItems.ARSOY_SWORD.get(), KoratioItems.ARSOY_HELMET.get(), KoratioItems.ARSOY_CHESTPLATE.get(), KoratioItems.ARSOY_LEGGINGS.get(), KoratioItems.ARSOY_BOOTS.get()), RecipeCategory.MISC, KoratioItems.ARSOY_NUGGET.get(), 0.1F, 100).unlockedBy("has_arsoy_pickaxe", has(KoratioItems.ARSOY_PICKAXE.get())).unlockedBy("has_arsoy_shovel", has(KoratioItems.ARSOY_SHOVEL.get())).unlockedBy("has_arsoy_axe", has(KoratioItems.ARSOY_AXE.get())).unlockedBy("has_arsoy_hoe", has(KoratioItems.ARSOY_HOE.get())).unlockedBy("has_arsoy_sword", has(KoratioItems.ARSOY_SWORD.get())).unlockedBy("has_arsoy_helmet", has(KoratioItems.ARSOY_HELMET.get())).unlockedBy("has_arsoy_chestplate", has(KoratioItems.ARSOY_CHESTPLATE.get())).unlockedBy("has_arsoy_leggings", has(KoratioItems.ARSOY_LEGGINGS.get())).unlockedBy("has_arsoy_boots", has(KoratioItems.ARSOY_BOOTS.get())).save(consumer, Koratio.prefix("arsoy_nugget_from_blasting"));
		
		//CampfireRecipes
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(KoratioItems.RAW_PANGO.get()), RecipeCategory.FOOD, KoratioItems.CRACKED_PANGO.get(), 0.35F, 600).unlockedBy("has_raw_pango", has(KoratioItems.RAW_PANGO.get())).save(consumer, Koratio.prefix("cracked_pango_from_campfire_cooking"));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(KoratioItems.SPIKED_PORKCHOP.get()), RecipeCategory.FOOD, KoratioItems.COOKED_SPIKED_PORKCHOP.get(), 0.35F, 600).unlockedBy("has_spiked_porkchop", has(KoratioItems.SPIKED_PORKCHOP.get())).save(consumer, Koratio.prefix("cooked_spiked_porkchop_from_campfire_cooking"));
		
		//SmithingRecipes
		rainbowGemSmithing(consumer, Items.NETHERITE_SWORD, RecipeCategory.COMBAT, KoratioItems.RAINBOW_GEM_SWORD.get());
		rainbowGemSmithing(consumer, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, KoratioItems.RAINBOW_GEM_SHOVEL.get());
		rainbowGemSmithing(consumer, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, KoratioItems.RAINBOW_GEM_PICKAXE.get());
		rainbowGemSmithing(consumer, Items.NETHERITE_AXE, RecipeCategory.TOOLS, KoratioItems.RAINBOW_GEM_AXE.get());
		rainbowGemSmithing(consumer, Items.NETHERITE_HOE, RecipeCategory.TOOLS, KoratioItems.RAINBOW_GEM_HOE.get());
		rainbowGemSmithing(consumer, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, KoratioItems.RAINBOW_GEM_HELMET.get());
		rainbowGemSmithing(consumer, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, KoratioItems.RAINBOW_GEM_CHESTPLATE.get());
		rainbowGemSmithing(consumer, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, KoratioItems.RAINBOW_GEM_LEGGINGS.get());
		rainbowGemSmithing(consumer, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, KoratioItems.RAINBOW_GEM_BOOTS.get());
		
		//StonecuttingRecipes
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.CHISELED_BLOOD_STAINED_DEEPSLATE.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get());
	}
	
	protected static ShapedRecipeBuilder door(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, output, 3).pattern("xx").pattern("xx").pattern("xx").define('x', input.get()).group("wooden_door").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder fence(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 3).pattern("x#x").pattern("x#x").define('x', input.get()).define('#', Items.STICK).group("wooden_fence").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder fenceGate(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output).pattern("x#x").pattern("x#x").define('#', input.get()).define('x', Items.STICK).group("wooden_fence_gate").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder pressurePlate(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, output).pattern("xx").define('x', input.get()).group("wooden_pressure_plate").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapelessRecipeBuilder button(Block output, RegistryObject<? extends Block> input) {
		return ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, output).requires(input.get()).unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder slab(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6).pattern("xxx").define('x', input.get()).group("wooden_slab").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder woodenSlab(Block output, RegistryObject<? extends Block> input) {
		return slab(output, input).group("wooden_slab");
	}
	
	protected static ShapedRecipeBuilder stairs(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4).pattern("x  ").pattern("xx ").pattern("xxx").define('x', input.get()).unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder woodenStairs(Block output, RegistryObject<? extends Block> input) {
		return stairs(output, input).group("wooden_stairs");
	}
	
	protected static ShapedRecipeBuilder trapdoor(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, output, 2).pattern("xxx").pattern("xxx").define('x', input.get()).group("wooden_trapdoor").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder wall(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2).pattern("xxx").pattern("xxx").define('x', input.get()).unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder sign(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 3).pattern("xxx").pattern("xxx").pattern(" # ").define('x', input.get()).define('#', Items.STICK).group("wooden_sign").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder hangingSign(Block output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 6).pattern("# #").pattern("xxx").pattern("xxx").define('x', input.get()).define('#', Items.CHAIN).group("wooden_hanging_sign").unlockedBy("has_"+input.getId().getPath(), has(input.get()));
	}
	
	protected static ShapedRecipeBuilder chest(BlockItem output, RegistryObject<? extends Block> input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 2).pattern("###").pattern("#x#").pattern("###").define('#', input.get()).define('x', Tags.Items.CHESTS_WOODEN).group("chests").unlockedBy("has_planks", has(input.get()));
	}
	
	protected static void chestBoat(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, output).requires(Tags.Items.CHESTS_WOODEN).requires(input).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}
	
	protected static void twoByTwo(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4).pattern("##").pattern("##").define('#', input).unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(input.asItem()).getPath(), has(MinMaxBounds.Ints.atLeast(4), input)).save(consumer);
	}
	
	protected static ShapelessRecipeBuilder oneToOne(Item output, Item input) {
		return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output).requires(input).unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(input).getPath(), has(input));
	}
	
	protected static ShapedRecipeBuilder compress(ItemLike output, ItemLike input) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output).pattern("xxx").pattern("xxx").pattern("xxx").define('x', input).unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(input.asItem()).getPath(), has(MinMaxBounds.Ints.atLeast(9), input));
	}
	
	protected static ShapelessRecipeBuilder decompress(Item output, ItemLike input) {
		return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, 9).requires(input).unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(input.asItem()).getPath(), has(input));
	}
	
	protected static void rainbowGemSmithing(Consumer<FinishedRecipe> consumer, Item ingredient, RecipeCategory category, Item result) {
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(KoratioItems.RAINBOW_GEM_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(ingredient), Ingredient.of(KoratioItems.RAINBOW_GEM.get()), category, result).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(consumer, Koratio.prefix(getItemName(result) + "_smithing"));
	}
	
	protected static void sword(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('#', Items.STICK).define('X', ingredient).pattern("X").pattern("X").pattern("#").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void pickaxe(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void axe(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void shovel(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern("X").pattern("#").pattern("#").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void hoe(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('#', Items.STICK).define('X', ingredient).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void helmet(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('X', ingredient).pattern("XXX").pattern("X X").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void chestplate(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('X', ingredient).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void leggings(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('X', ingredient).pattern("XXX").pattern("X X").pattern("X X").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void boots(Consumer<FinishedRecipe> consumer, Item result, Item ingredient) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('X', ingredient).pattern("X X").pattern("X X").unlockedBy("has_"+ForgeRegistries.ITEMS.getKey(ingredient).getPath(), has(ingredient)).save(consumer);
	}
	
	protected static void oreSmelting(Consumer<FinishedRecipe> consumer, List<ItemLike> input, RecipeCategory category, ItemLike output, float xp, int time, String group) {
		oreCooking(consumer, RecipeSerializer.SMELTING_RECIPE, input, category, output, xp, time, group, "_from_smelting");
	}

	protected static void oreBlasting(Consumer<FinishedRecipe> consumer, List<ItemLike> input, RecipeCategory category, ItemLike output, float xp, int time, String group) {
		oreCooking(consumer, RecipeSerializer.BLASTING_RECIPE, input, category, output, xp, time, group, "_from_blasting");
	}

	protected static void oreCooking(Consumer<FinishedRecipe> consumer, RecipeSerializer<? extends AbstractCookingRecipe> serializer, List<ItemLike> input, RecipeCategory category, ItemLike output, float xp, int time, String group, String extra) {
		for(ItemLike itemlike : input) {
			SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, output, xp, time, serializer).group(group).unlockedBy(getHasName(itemlike), has(itemlike)).save(consumer, Koratio.prefix(getItemName(output) + extra + "_" + getItemName(itemlike)));
		}
	}
	
	protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike ingredient, int amount) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), category, output, amount).unlockedBy("has_"+getItemName(ingredient), has(ingredient)).save(consumer, Koratio.prefix(getItemName(output)+"_from_"+getItemName(ingredient) + "_stonecutting"));
	}
	
	protected static String getItemName(ItemLike item) {
		return ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
	}
}