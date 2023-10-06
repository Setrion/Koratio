package net.setrion.koratio.data;

import java.util.Collection;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;

public class ItemModelGenerator extends ItemModelProvider {

	public ItemModelGenerator(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, Koratio.MOD_ID, fileHelper);
	}
	
	@Override
	protected void registerModels() {
		withExistingParent(ForgeRegistries.BLOCKS.getKey(KoratioBlocks.MINIATURE_FANTASIA_PORTAL.get()).getPath(), Koratio.prefix("block/miniature/fantasia_portal"));
		
		basicItem(KoratioItems.RAW_PANGO.get());
		basicItem(KoratioItems.CRACKED_PANGO.get());
		basicItem(KoratioItems.SPIKED_PORKCHOP.get());
		basicItem(KoratioItems.COOKED_SPIKED_PORKCHOP.get());
		basicItem(KoratioItems.GOLDEN_CHICKEN.get());
		basicItem(KoratioItems.COOKED_GOLDEN_CHICKEN.get());
		basicItem(KoratioItems.GOLDEN_BREAD.get());
		basicItem(KoratioItems.GOLDEN_EGG.get());
		basicItem(KoratioItems.GOLDEN_WHEAT.get());
		basicItem(KoratioItems.GOLDEN_BABY_CARROTS.get());
		
		basicItem(KoratioItems.GOLDEN_WHEAT_SEEDS.get());
		blockModel(KoratioBlocks.GOLDEN_HAY_BLOCK.get());
		
		blockModel(KoratioBlocks.DECRYPTING_TABLE.get());
		basic2LayerItem(KoratioItems.SCROLL.getKey().location());
		basicItem(KoratioItems.DECRYPTING_BOOK.get());
		basicItem(KoratioItems.BETTER_DECRYPTING_BOOK.get());
		basicItem(KoratioItems.FANTASTIC_DECRYPTING_BOOK.get());
		
		basicItem(KoratioItems.RAINBOW_GEM.get());
		blockModel(KoratioBlocks.RAINBOW_GEM_BLOCK.get());
		basicItem(KoratioItems.RAW_ARSOY.get());
		basicItem(KoratioItems.ARSOY_INGOT.get());
		basicItem(KoratioItems.ARSOY_NUGGET.get());
		blockModel(KoratioBlocks.ARSOY_ORE.get());
		blockModel(KoratioBlocks.DEEPSLATE_ARSOY_ORE.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get());
		blockModel(KoratioBlocks.RAW_ARSOY_BLOCK.get());
		blockModel(KoratioBlocks.ARSOY_BLOCK.get());
		blockModel(KoratioBlocks.COOKIE_ORE.get());
		blockModel(KoratioBlocks.DEEPSLATE_COOKIE_ORE.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_COOKIE_ORE.get());
		basicItem(KoratioItems.WITHER_BONE.get());
		basicItem(KoratioItems.RED_SUGAR.get());
		basicItem(KoratioItems.BLUE_SUGAR.get());
		basicItem(KoratioItems.YELLOW_SUGAR.get());
		basicItem(KoratioItems.GREEN_SUGAR.get());
		blockModel(KoratioBlocks.SUGAR_BLOCK.get());
		blockModel(KoratioBlocks.RED_SUGAR_BLOCK.get());
		blockModel(KoratioBlocks.BLUE_SUGAR_BLOCK.get());
		blockModel(KoratioBlocks.YELLOW_SUGAR_BLOCK.get());
		blockModel(KoratioBlocks.GREEN_SUGAR_BLOCK.get());
		
		blockModel(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get());
		blockModel(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get());
		blockModel(KoratioBlocks.RAW_GINGERBREAD_SLAB.get());
		blockModel(KoratioBlocks.GINGERBREAD_BLOCK.get());
		blockModel(KoratioBlocks.GINGERBREAD_STAIRS.get());
		blockModel(KoratioBlocks.GINGERBREAD_SLAB.get());
		blockModel(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get());
		blockModel(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get());
		blockModel(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get());
		blockModel(KoratioBlocks.GINGERBREAD_BRICKS.get());
		blockModel(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get());
		blockModel(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get());
		blockModel(KoratioBlocks.MARSHMALLOW_BLOCK.get());
		blockModel(KoratioBlocks.MARSHMALLOW_STAIRS.get());
		blockModel(KoratioBlocks.MARSHMALLOW_SLAB.get());
		
		basicTool(KoratioItems.RAINBOW_GEM_SWORD);
		basicTool(KoratioItems.RAINBOW_GEM_AXE);
		basicTool(KoratioItems.RAINBOW_GEM_SHOVEL);
		basicTool(KoratioItems.RAINBOW_GEM_PICKAXE);
		basicTool(KoratioItems.RAINBOW_GEM_HOE);
		basicTool(KoratioItems.ARSOY_SWORD);
		basicTool(KoratioItems.ARSOY_AXE);
		basicTool(KoratioItems.ARSOY_SHOVEL);
		basicTool(KoratioItems.ARSOY_PICKAXE);
		basicTool(KoratioItems.ARSOY_HOE);
		basicTool(KoratioItems.WITHER_BONE_SWORD);
		basicTool(KoratioItems.WITHER_BONE_AXE);
		basicTool(KoratioItems.WITHER_BONE_SHOVEL);
		basicTool(KoratioItems.WITHER_BONE_PICKAXE);
		basicTool(KoratioItems.WITHER_BONE_HOE);
		basicTool(KoratioItems.BONE_SWORD);
		basicTool(KoratioItems.BONE_AXE);
		basicTool(KoratioItems.BONE_SHOVEL);
		basicTool(KoratioItems.BONE_PICKAXE);
		basicTool(KoratioItems.BONE_HOE);
		
		basicItem(KoratioItems.RAINBOW_GEM_HELMET.get());
		basicItem(KoratioItems.RAINBOW_GEM_CHESTPLATE.get());
		basicItem(KoratioItems.RAINBOW_GEM_LEGGINGS.get());
		basicItem(KoratioItems.RAINBOW_GEM_BOOTS.get());
		basicItem(KoratioItems.ARSOY_HELMET.get());
		basicItem(KoratioItems.ARSOY_CHESTPLATE.get());
		basicItem(KoratioItems.ARSOY_LEGGINGS.get());
		basicItem(KoratioItems.ARSOY_BOOTS.get());
		basicItem(KoratioItems.WITHER_BONE_HELMET.get());
		basicItem(KoratioItems.WITHER_BONE_CHESTPLATE.get());
		basicItem(KoratioItems.WITHER_BONE_LEGGINGS.get());
		basicItem(KoratioItems.WITHER_BONE_BOOTS.get());
		basicItem(KoratioItems.BONE_HELMET.get());
		basicItem(KoratioItems.BONE_CHESTPLATE.get());
		basicItem(KoratioItems.BONE_LEGGINGS.get());
		basicItem(KoratioItems.BONE_BOOTS.get());
		
		plantItem(KoratioItems.AMETHYST_GRASS.get());
		plantItem(KoratioItems.AMETHYST_COBWEB.get());
		plantItem(KoratioItems.EMERALD_GRASS.get());
		plantItem(KoratioItems.EMERALD_COBWEB.get());
		basicItem(KoratioItems.RAINBOW_ROSE.get());
		basicItem(KoratioItems.RAINBOW_ALLIUM.get());
		basicItem(KoratioItems.RAINBOW_LILY_OF_THE_VALLEY.get());
		plantItem(KoratioItems.COOKIE_FLOWER.get());
		plantItem(KoratioItems.WHITE_SUGARGLASS_FLOWER.get(), "_item");
		plantItem(KoratioItems.BLUE_SUGARGLASS_FLOWER.get(), "_item");
		plantItem(KoratioItems.GREEN_SUGARGLASS_FLOWER.get(), "_item");
		plantItem(KoratioItems.YELLOW_SUGARGLASS_FLOWER.get(), "_item");
		plantItem(KoratioItems.RED_SUGARGLASS_FLOWER.get(), "_item");
		
		spawnEggItem(KoratioItems.FIRE_BAT_SPAWN_EGG.get());
		spawnEggItem(KoratioItems.ICE_BAT_SPAWN_EGG.get());
		spawnEggItem(KoratioItems.THUNDER_BAT_SPAWN_EGG.get());
		spawnEggItem(KoratioItems.UNICORN_CAT_SPAWN_EGG.get());
		spawnEggItem(KoratioItems.SPIKY_PIG_SPAWN_EGG.get());
		spawnEggItem(KoratioItems.DEMONIC_SOLDIER_SPAWN_EGG.get());
		spawnEggItem(KoratioItems.JUMSTEM_SPAWN_EGG.get());
		
		blockModel(KoratioBlocks.AMETHYST_BRICKS.get());
		blockModel(KoratioBlocks.AMETHYST_BRICK_STAIRS.get());
		blockModel(KoratioBlocks.AMETHYST_BRICK_SLAB.get());
		blockModel(KoratioBlocks.CHISELED_AMETHYST_BLOCK.get());
		blockModel(KoratioBlocks.AMETHYST_PILLAR.get());
		blockModel(KoratioBlocks.EMERALD_BRICKS.get());
		blockModel(KoratioBlocks.EMERALD_BRICK_STAIRS.get());
		blockModel(KoratioBlocks.EMERALD_BRICK_SLAB.get());
		blockModel(KoratioBlocks.CHISELED_EMERALD_BLOCK.get());
		blockModel(KoratioBlocks.EMERALD_PILLAR.get());
		blockModel(KoratioBlocks.SOUL_STONE.get());
		blockModel(KoratioBlocks.SOUL_STONE_STAIRS.get());
		blockModel(KoratioBlocks.SOUL_STONE_SLAB.get());
		blockModel(KoratioBlocks.SOUL_STONE_PRESSURE_PLATE.get());
		blockModel(KoratioBlocks.SOUL_STONE_BUTTON.get());
		withExistingParent(KoratioBlocks.SOUL_STONE_BUTTON.getId().getPath(), "block/button_inventory").texture("texture", Koratio.prefix("block/soul_stone"));
		blockModel(KoratioBlocks.INFESTED_SOUL_STONE.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_STONE_STAIRS.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_STONE_SLAB.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_STONE_PRESSURE_PLATE.get());
		withExistingParent(KoratioBlocks.INFESTED_SOUL_STONE_BUTTON.getId().getPath(), "block/button_inventory").texture("texture", Koratio.prefix("block/infested_soul_stone"));
		blockModel(KoratioBlocks.SOUL_COBBLESTONE.get());
		blockModel(KoratioBlocks.SOUL_COBBLESTONE_STAIRS.get());
		blockModel(KoratioBlocks.SOUL_COBBLESTONE_SLAB.get());
		blockModel(KoratioBlocks.SOUL_COBBLESTONE_WALL.get(), KoratioBlocks.SOUL_COBBLESTONE_WALL.getKey().location().getPath()+"_inventory");
		blockModel(KoratioBlocks.INFESTED_SOUL_COBBLESTONE.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_STAIRS.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_SLAB.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_WALL.get(), KoratioBlocks.INFESTED_SOUL_COBBLESTONE_WALL.getKey().location().getPath()+"_inventory");
		blockModel(KoratioBlocks.SMOOTH_SOUL_STONE.get());
		blockModel(KoratioBlocks.SMOOTH_SOUL_STONE_SLAB.get());
		blockModel(KoratioBlocks.SOUL_STONE_BRICKS.get());
		blockModel(KoratioBlocks.CRACKED_SOUL_STONE_BRICKS.get());
		blockModel(KoratioBlocks.SOUL_STONE_BRICK_STAIRS.get());
		blockModel(KoratioBlocks.SOUL_STONE_BRICK_SLAB.get());
		blockModel(KoratioBlocks.SOUL_STONE_BRICK_WALL.get(), KoratioBlocks.SOUL_STONE_BRICK_WALL.getKey().location().getPath()+"_inventory");
		blockModel(KoratioBlocks.CHISELED_SOUL_STONE_BRICKS.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_STONE_BRICKS.get());
		blockModel(KoratioBlocks.CRACKED_INFESTED_SOUL_STONE_BRICKS.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_STAIRS.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_SLAB.get());
		blockModel(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_WALL.get(), KoratioBlocks.INFESTED_SOUL_STONE_BRICK_WALL.getKey().location().getPath()+"_inventory");
		blockModel(KoratioBlocks.CHISELED_INFESTED_SOUL_STONE_BRICKS.get());
		
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get(), KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.getKey().location().getPath()+"_inventory");
		blockModel(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
		blockModel(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS.get());
		blockModel(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB.get());
		blockModel(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get(), KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.getKey().location().getPath()+"_inventory");
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.getKey().location().getPath()+"_inventory");
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get());
		blockModel(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.getKey().location().getPath()+"_inventory");
		blockModel(KoratioBlocks.CHISELED_BLOOD_STAINED_DEEPSLATE.get());
		blockModel(KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_BRICKS.get());
		blockModel(KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_TILES.get());

		blockModel(KoratioBlocks.PANGO_PLANKS.get());
		blockModel(KoratioBlocks.PANGO_LOG.get());
		blockModel(KoratioBlocks.STRIPPED_PANGO_LOG.get());
		blockModel(KoratioBlocks.PANGO_WOOD.get());
		blockModel(KoratioBlocks.STRIPPED_PANGO_WOOD.get());
		blockModel(KoratioBlocks.PANGO_LEAVES.get());
		blockModel(KoratioBlocks.PANGO_SLAB.get());
		blockModel(KoratioBlocks.PANGO_STAIRS.get());
		plantItem(KoratioItems.PANGO_SAPLING.get());
		withExistingParent(KoratioBlocks.PANGO_FENCE.getId().getPath(), "block/fence_inventory").texture("texture", Koratio.prefix("block/pango_planks"));
		signItem(KoratioItems.PANGO_SIGN.get());
		signItem(KoratioItems.PANGO_HANGING_SIGN.get());
		withExistingParent(KoratioBlocks.PANGO_BUTTON.getId().getPath(), "block/button_inventory").texture("texture", Koratio.prefix("block/pango_planks"));
		blockModel(KoratioBlocks.PANGO_PRESSURE_PLATE.get());
		doorItem(KoratioItems.PANGO_DOOR.get());
		blockModel(KoratioBlocks.PANGO_TRAPDOOR.get(), "pango_trapdoor_bottom");
		blockModel(KoratioBlocks.PANGO_FENCE_GATE.get());
		withExistingParent(KoratioBlocks.PANGO_CHEST.getId().toString(), "item/chest").texture("particle", Koratio.prefix("block/pango_planks"));
		
		basicItem(KoratioItems.PANGO_BOAT.get());
		basicItem(KoratioItems.PANGO_CHEST_BOAT.get());
		
		blockModel(KoratioBlocks.RUGONA_PLANKS.get());
		blockModel(KoratioBlocks.RUGONA_LOG.get());
		blockModel(KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		blockModel(KoratioBlocks.RUGONA_WOOD.get());
		blockModel(KoratioBlocks.STRIPPED_RUGONA_WOOD.get());
		blockModel(KoratioBlocks.RUGONA_LEAVES.get());
		blockModel(KoratioBlocks.RUGONA_SLAB.get());
		blockModel(KoratioBlocks.RUGONA_STAIRS.get());
		plantItem(KoratioItems.RUGONA_SAPLING.get());
		withExistingParent(KoratioBlocks.RUGONA_FENCE.getId().getPath(), "block/fence_inventory").texture("texture", Koratio.prefix("block/rugona_planks"));
		signItem(KoratioItems.RUGONA_SIGN.get());
		signItem(KoratioItems.RUGONA_HANGING_SIGN.get());
		withExistingParent(KoratioBlocks.RUGONA_BUTTON.getId().getPath(), "block/button_inventory").texture("texture", Koratio.prefix("block/rugona_planks"));
		blockModel(KoratioBlocks.RUGONA_PRESSURE_PLATE.get());
		doorItem(KoratioItems.RUGONA_DOOR.get());
		blockModel(KoratioBlocks.RUGONA_TRAPDOOR.get(), "rugona_trapdoor_bottom");
		blockModel(KoratioBlocks.RUGONA_FENCE_GATE.get());
		withExistingParent(KoratioBlocks.RUGONA_CHEST.getId().toString(), "item/chest").texture("particle", Koratio.prefix("block/rugona_planks"));
		
		basicItem(KoratioItems.RUGONA_BOAT.get());
		basicItem(KoratioItems.RUGONA_CHEST_BOAT.get());
		
		blockModel(KoratioBlocks.VARESO_PLANKS.get());
		blockModel(KoratioBlocks.VARESO_LOG.get());
		blockModel(KoratioBlocks.STRIPPED_VARESO_LOG.get());
		blockModel(KoratioBlocks.VARESO_WOOD.get());
		blockModel(KoratioBlocks.STRIPPED_VARESO_WOOD.get());
		blockModel(KoratioBlocks.VARESO_LEAVES.get());
		blockModel(KoratioBlocks.VARESO_SLAB.get());
		blockModel(KoratioBlocks.VARESO_STAIRS.get());
		plantItem(KoratioItems.VARESO_SAPLING.get());
		withExistingParent(KoratioBlocks.VARESO_FENCE.getId().getPath(), "block/fence_inventory").texture("texture", Koratio.prefix("block/vareso_planks"));
		signItem(KoratioItems.VARESO_SIGN.get());
		signItem(KoratioItems.VARESO_HANGING_SIGN.get());
		withExistingParent(KoratioBlocks.VARESO_BUTTON.getId().getPath(), "block/button_inventory").texture("texture", Koratio.prefix("block/vareso_planks"));
		blockModel(KoratioBlocks.VARESO_PRESSURE_PLATE.get());
		doorItem(KoratioItems.VARESO_DOOR.get());
		blockModel(KoratioBlocks.VARESO_TRAPDOOR.get(), "vareso_trapdoor_bottom");
		blockModel(KoratioBlocks.VARESO_FENCE_GATE.get());
		withExistingParent(KoratioBlocks.VARESO_CHEST.getId().toString(), "item/chest").texture("particle", Koratio.prefix("block/vareso_planks"));
		
		basicItem(KoratioItems.VARESO_BOAT.get());
		basicItem(KoratioItems.VARESO_CHEST_BOAT.get());
		
		blockModel(KoratioBlocks.NIGHY_PLANKS.get());
		blockModel(KoratioBlocks.NIGHY_LOG.get());
		blockModel(KoratioBlocks.STRIPPED_NIGHY_LOG.get());
		blockModel(KoratioBlocks.NIGHY_WOOD.get());
		blockModel(KoratioBlocks.STRIPPED_NIGHY_WOOD.get());
		blockModel(KoratioBlocks.NIGHY_LEAVES.get());
		blockModel(KoratioBlocks.NIGHY_SLAB.get());
		blockModel(KoratioBlocks.NIGHY_STAIRS.get());
		plantItem(KoratioItems.NIGHY_SAPLING.get());
		withExistingParent(KoratioBlocks.NIGHY_FENCE.getId().getPath(), "block/fence_inventory").texture("texture", Koratio.prefix("block/nighy_planks"));
		signItem(KoratioItems.NIGHY_SIGN.get());
		signItem(KoratioItems.NIGHY_HANGING_SIGN.get());
		withExistingParent(KoratioBlocks.NIGHY_BUTTON.getId().getPath(), "block/button_inventory").texture("texture", Koratio.prefix("block/nighy_planks"));
		blockModel(KoratioBlocks.NIGHY_PRESSURE_PLATE.get());
		doorItem(KoratioItems.NIGHY_DOOR.get());
		blockModel(KoratioBlocks.NIGHY_TRAPDOOR.get(), "nighy_trapdoor_bottom");
		blockModel(KoratioBlocks.NIGHY_FENCE_GATE.get());
		withExistingParent(KoratioBlocks.NIGHY_CHEST.getId().toString(), "item/chest").texture("particle", Koratio.prefix("block/nighy_planks"));
		
		basicItem(KoratioItems.NIGHY_BOAT.get());
		basicItem(KoratioItems.NIGHY_CHEST_BOAT.get());
		
		plantItem(KoratioItems.PURPLE_MUSHROOM.get());
		plantItem(KoratioItems.GREEN_MUSHROOM.get());
		
		plantItem(KoratioItems.GILDED_VINES.get());
		
		withExistingParent(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.getId().getPath(), "koratio:block/purple_mushroom_block_inventory");
		withExistingParent(KoratioBlocks.GREEN_MUSHROOM_BLOCK.getId().getPath(), "koratio:block/green_mushroom_block_inventory");
		
		Collection<Item> eggs = KoratioItems.SPAWN_EGGS.getEntries().stream().map(RegistryObject::get).toList();
		eggs.forEach(this::spawnEggItem);
	}
	
	private ItemModelBuilder buildItem(String name, String parent, int emissivity, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		if (emissivity > 0) builder = builder.customLoader(ItemLayerModelBuilder::begin).emissive(emissivity, emissivity, 0).renderType("forge_entity_unsorted_translucent", 0).end();
		return builder;
	}
	
	private ItemModelBuilder basicTool(RegistryObject<Item> item) {
		return toolItem(item.getId().getPath(), Koratio.prefix("item/" + item.getId().getPath()));
	}
	
	private ItemModelBuilder toolItem(String name, ResourceLocation... layers) {
		return buildItem(name, "item/handheld", 0, layers);
	}
	
	private void doorItem(Item item) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), "item/generated").texture("layer0", getItemPathInFolder(item, "item"));
	}
	
	private void spawnEggItem(Item item) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), "minecraft:item/template_spawn_egg");
	}
	
	private void plantItem(Item item) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), "item/generated").texture("layer0", getItemPathInFolder(item, "block"));
	}
	
	private void plantItem(Item item, String name) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), "item/generated").texture("layer0", getItemPathInFolder(item, "block", name));
	}
	
	private void signItem(Item item) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), "item/generated").texture("layer0", getItemPathInFolder(item, "item"));
	}
	
	private void blockModel(Block b) {
		blockModel(b, ForgeRegistries.BLOCKS.getKey(b).getPath());
	}

	private void blockModel(Block b, String model) {
		blockModel(b, Koratio.prefix("block/" + model));
	}

	private void blockModel(Block b, ResourceLocation model) {
		withExistingParent(ForgeRegistries.BLOCKS.getKey(b).getPath(), model);
	}
	
	public ItemModelBuilder basic2LayerItem(ResourceLocation item) {
		return getBuilder(item.toString())
				.parent(new ModelFile.UncheckedModelFile("item/generated"))
				.texture("layer0", new ResourceLocation(item.getNamespace(), "item/"+item.getPath()))
				.texture("layer1", new ResourceLocation(item.getNamespace(), "item/"+item.getPath()+"_overlay"));
    }
	
	private ResourceLocation getItemPathInFolder(Item item, String folder, String extra) {
		return Koratio.prefix(folder+"/"+ForgeRegistries.ITEMS.getKey(item).getPath()+extra);
	}
	
	private ResourceLocation getItemPathInFolder(Item item, String folder) {
		return getItemPathInFolder(item, folder, "");
	}
}