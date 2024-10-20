package net.setrion.koratio.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.*;
import net.setrion.koratio.scroll.Scroll;
import net.setrion.koratio.world.item.CandyItem;
import net.setrion.koratio.world.item.ColoredCandyItem;

import java.util.function.Supplier;

public abstract class LanguageGenerator extends LanguageProvider {

	public LanguageGenerator(PackOutput output, String language) {
		super(output, Koratio.MOD_ID, language);
	}

	@Override
	protected void addTranslations() {
		addMisc();
		addAdvancements();
		addItems();
		addEnchantments();
		addBlocks();
		addBiomes();
		addEntities();
		addTags();
		addSounds();
		addScrolls();
	}
	
	protected abstract void addMisc();
	protected abstract void addAdvancements();
	protected abstract void addItems();
	protected abstract void addEnchantments();
	protected abstract void addBlocks();
	protected abstract void addBiomes();
	protected abstract void addEntities();
	protected abstract void addTags();
	protected abstract void addSounds();
	protected abstract void addScrolls();
	
	public void addBiome(Supplier<? extends ResourceKey<Biome>> key, String name) {
        add(key.get(), name);
    }

    public void add(ResourceKey<Biome> key, String name) {
        add(key.location().toLanguageKey("biome"), name);
    }
    
    public void addSubtitle(Supplier<? extends SoundEvent> key, String name) {
        add(key.get(), name);
    }

    public void add(SoundEvent key, String name) {
        add(key.getLocation().toLanguageKey("subtitles").replaceFirst("koratio.entity.koratio", "koratio.entity"), name);
    }
    
    public void addScroll(Supplier<? extends Scroll> key, String name, String text) {
    	add("scroll."+key.get().getName()+".title", name);
    	add("scroll."+key.get().getName()+".text", text);
    }
    
    public void add(Scroll key, String name, String text) {
    	add("scroll."+key.getName()+".title", name);
    	add("scroll."+key.getName()+".text", text);
    }
	
	public static class English extends LanguageGenerator {

		public English(PackOutput output) {
			super(output, "en_us");
		}
	
		@Override
		protected void addMisc() {
			add("itemGroup.koratio.main", "Koratio");
			add("itemGroup.koratio.fantasia", "Koratio - Fantasia");

			add("koratio.woodcutter_category", "Woodcutter");
			add("koratio.candy_shaper_category", "Candy Shaper");
			
			add("scroll.not_readable", "Not Readable");
			add("scroll.readable", "Readable");
			add("scroll.no_data", "This scroll is too damaged to decrypt. It can be destroyed");
			
			add("decrypting_book.power", "Decrypting Power: ");
			add("container.decrypting", "Decrypting");
			add("container.woodcutter", "Woodcutter");
			add("container.candy_shaper", "Candy Shaper");
			add("decrypting.chance", "Chance: ");

			add("tooltip.koratio.piping_bag.empty", "Empty");
			add("tooltip.koratio.piping_bag.filled", "Filled with %s units of Icing");
			add("container.candy_shaper.missing_template_tooltip", "Add Candy Template");
		}
		
		@Override
		protected void addAdvancements() {
			add("advancements.koratio.root.title", "Koratio");
			add("advancements.koratio.root.desc", "Discover a building far from civilization. Could it be that someone lives there?");
			add("advancements.koratio.decrypting.title", "Decrypting");
			add("advancements.koratio.decrypting.desc", "Decypher the scroll you just found");
			add("advancements.koratio.enter_fantasia.title", "Fantasia");
			add("advancements.koratio.enter_fantasia.desc", "Enter the newly discovered portal");
		}
		
		@Override
		protected void addItems() {
			add(KoratioItems.SCROLL.get(), "Scroll");
			add(KoratioItems.PIPING_BAG.get(), "Piping Bag");
			add(KoratioItems.DECRYPTING_BOOK.get(), "Decrypting Book");
			add(KoratioItems.BETTER_DECRYPTING_BOOK.get(), "Better Decrypting Book");
			add(KoratioItems.FANTASTIC_DECRYPTING_BOOK.get(), "Fantastic Decrypting Book");

			add(KoratioItems.SPIKED_PORKCHOP.get(), "Spiked Porkchop");
			add(KoratioItems.COOKED_SPIKED_PORKCHOP.get(), "Cooked Spiked Porkchop");
			add(KoratioItems.FLUFFER.get(), "Fluffer");
			add(KoratioItems.COOKED_FLUFFER.get(), "Cooked Fluffer");

			for (CandyItem candy : CandyItem.CANDY_LIST) {
				add(candy, createCandyName(candy));
			}

			add(KoratioItems.RAW_PANGO.get(), "Raw Pango");
			add(KoratioItems.CRACKED_PANGO.get(), "Cracked Pango");
			add(KoratioItems.PANGO_BOAT.get(), "Pango Boat");
			add(KoratioItems.PANGO_CHEST_BOAT.get(), "Pango Chest Boat");
			add(KoratioItems.RUGONA_BOAT.get(), "Rugona Boat");
			add(KoratioItems.RUGONA_CHEST_BOAT.get(), "Rugona Chest Boat");
			add(KoratioItems.CANDY_BOAT.get(), "Candy Boat");
			add(KoratioItems.CANDY_CHEST_BOAT.get(), "Candy Chest Boat");
			add(KoratioItems.VARESO_BOAT.get(), "Vareso Boat");
			add(KoratioItems.VARESO_CHEST_BOAT.get(), "Vareso Chest Boat");
			add(KoratioItems.ELVEN_BOAT.get(), "Elven Boat");
			add(KoratioItems.BLUE_ELVEN_BOAT.get(), "Blue Elven Boat");
			add(KoratioItems.CYAN_ELVEN_BOAT.get(), "Cyan Elven Boat");
			add(KoratioItems.GREEN_ELVEN_BOAT.get(), "Green Elven Boat");
			add(KoratioItems.ELVEN_CHEST_BOAT.get(), "Elven Chest Boat");
			add(KoratioItems.BLUE_ELVEN_CHEST_BOAT.get(), "Blue Elven Chest Boat");
			add(KoratioItems.CYAN_ELVEN_CHEST_BOAT.get(), "Cyan Elven Chest Boat");
			add(KoratioItems.GREEN_ELVEN_CHEST_BOAT.get(), "Green Elven Chest Boat");
			
			add(KoratioItems.CHOCOLATE_MILK_BUCKET.get(), "Chocolate Milk Bucket");

			add(KoratioItems.RAINBOW_GEM.get(), "Rainbow Gem");
			add(KoratioItems.RAINBOW_CRYSTAL_SHARD.get(), "Rainbow Crystal Shard");
			add(KoratioItems.RUBY.get(), "Ruby");
			add(KoratioItems.SAPPHIRE.get(), "Sapphire");
			add(KoratioItems.TOPAZ.get(), "Topaz");
			add(KoratioItems.ONYX.get(), "Onyx");
			add(KoratioItems.WITHER_BONE.get(), "Wither Bone");

			add(KoratioItems.LIGHT_GRAY_SUGAR.get(), "Light Gray Sugar");
			add(KoratioItems.GRAY_SUGAR.get(), "Gray Sugar");
			add(KoratioItems.BLACK_SUGAR.get(), "Black Sugar");
			add(KoratioItems.BROWN_SUGAR.get(), "Brown Sugar");
			add(KoratioItems.RED_SUGAR.get(), "Red Sugar");
			add(KoratioItems.ORANGE_SUGAR.get(), "Orange Sugar");
			add(KoratioItems.YELLOW_SUGAR.get(), "Yellow Sugar");
			add(KoratioItems.LIME_SUGAR.get(), "Lime Sugar");
			add(KoratioItems.GREEN_SUGAR.get(), "Green Sugar");
			add(KoratioItems.CYAN_SUGAR.get(), "Cyan Sugar");
			add(KoratioItems.LIGHT_BLUE_SUGAR.get(), "Light Blue Sugar");
			add(KoratioItems.BLUE_SUGAR.get(), "Blue Sugar");
			add(KoratioItems.PURPLE_SUGAR.get(), "Purple Sugar");
			add(KoratioItems.MAGENTA_SUGAR.get(), "Magenta Sugar");
			add(KoratioItems.PINK_SUGAR.get(), "Pink Sugar");

			add(KoratioItems.STICKY_WHITE_SUGAR_BUCKET.get(), "Sticky White Sugar Bucket");
			add(KoratioItems.STICKY_LIGHT_GRAY_SUGAR_BUCKET.get(), "Sticky Light Gray Sugar Bucket");
			add(KoratioItems.STICKY_GRAY_SUGAR_BUCKET.get(), "Sticky Gray Sugar Bucket");
			add(KoratioItems.STICKY_BLACK_SUGAR_BUCKET.get(), "Sticky Black Sugar Bucket");
			add(KoratioItems.STICKY_BROWN_SUGAR_BUCKET.get(), "Sticky Brown Sugar Bucket");
			add(KoratioItems.STICKY_RED_SUGAR_BUCKET.get(), "Sticky Red Sugar Bucket");
			add(KoratioItems.STICKY_ORANGE_SUGAR_BUCKET.get(), "Sticky Orange Sugar Bucket");
			add(KoratioItems.STICKY_YELLOW_SUGAR_BUCKET.get(), "Sticky Yellow Sugar Bucket");
			add(KoratioItems.STICKY_LIME_SUGAR_BUCKET.get(), "Sticky Lime Sugar Bucket");
			add(KoratioItems.STICKY_GREEN_SUGAR_BUCKET.get(), "Sticky Green Sugar Bucket");
			add(KoratioItems.STICKY_CYAN_SUGAR_BUCKET.get(), "Sticky Cyan Sugar Bucket");
			add(KoratioItems.STICKY_LIGHT_BLUE_SUGAR_BUCKET.get(), "Sticky Light Blue Sugar Bucket");
			add(KoratioItems.STICKY_BLUE_SUGAR_BUCKET.get(), "Sticky Blue Sugar Bucket");
			add(KoratioItems.STICKY_PURPLE_SUGAR_BUCKET.get(), "Sticky Purple Sugar Bucket");
			add(KoratioItems.STICKY_MAGENTA_SUGAR_BUCKET.get(), "Sticky Magenta Sugar Bucket");
			add(KoratioItems.STICKY_PINK_SUGAR_BUCKET.get(), "Sticky Pink Sugar Bucket");

			add(KoratioItems.MOLTEN_WHITE_SUGAR_BUCKET.get(), "Molten White Sugar Bucket");
			add(KoratioItems.MOLTEN_LIGHT_GRAY_SUGAR_BUCKET.get(), "Molten Light Gray Sugar Bucket");
			add(KoratioItems.MOLTEN_GRAY_SUGAR_BUCKET.get(), "Molten Gray Sugar Bucket");
			add(KoratioItems.MOLTEN_BLACK_SUGAR_BUCKET.get(), "Molten Black Sugar Bucket");
			add(KoratioItems.MOLTEN_BROWN_SUGAR_BUCKET.get(), "Molten Brown Sugar Bucket");
			add(KoratioItems.MOLTEN_RED_SUGAR_BUCKET.get(), "Molten Red Sugar Bucket");
			add(KoratioItems.MOLTEN_ORANGE_SUGAR_BUCKET.get(), "Molten Orange Sugar Bucket");
			add(KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET.get(), "Molten Yellow Sugar Bucket");
			add(KoratioItems.MOLTEN_LIME_SUGAR_BUCKET.get(), "Molten Lime Sugar Bucket");
			add(KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET.get(), "Molten Green Sugar Bucket");
			add(KoratioItems.MOLTEN_CYAN_SUGAR_BUCKET.get(), "Molten Cyan Sugar Bucket");
			add(KoratioItems.MOLTEN_LIGHT_BLUE_SUGAR_BUCKET.get(), "Molten Light Blue Sugar Bucket");
			add(KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET.get(), "Molten Blue Sugar Bucket");
			add(KoratioItems.MOLTEN_PURPLE_SUGAR_BUCKET.get(), "Molten Purple Sugar Bucket");
			add(KoratioItems.MOLTEN_MAGENTA_SUGAR_BUCKET.get(), "Molten Magenta Sugar Bucket");
			add(KoratioItems.MOLTEN_PINK_SUGAR_BUCKET.get(), "Molten Pink Sugar Bucket");

			add(KoratioItems.WOODEN_ICING_SPATULA.get(), "Wooden Icing Spatula");
			add(KoratioItems.STONE_ICING_SPATULA.get(), "Stone Icing Spatula");
			add(KoratioItems.GOLDEN_ICING_SPATULA.get(), "Golden Icing Spatula");
			add(KoratioItems.IRON_ICING_SPATULA.get(), "Iron Icing Spatula");
			add(KoratioItems.DIAMOND_ICING_SPATULA.get(), "Diamond Icing Spatula");
			add(KoratioItems.NETHERITE_ICING_SPATULA.get(), "Netherite Icing Spatula");

			add(KoratioItems.BONE_HELMET.get(), "Bone Helmet");
			add(KoratioItems.BONE_CHESTPLATE.get(), "Bone Chestplate");
			add(KoratioItems.BONE_LEGGINGS.get(), "Bone Leggings");
			add(KoratioItems.BONE_BOOTS.get(), "Bone Boots");
			add(KoratioItems.BONE_SWORD.get(), "Bone Sword");
			add(KoratioItems.BONE_SHOVEL.get(), "Bone Shovel");
			add(KoratioItems.BONE_AXE.get(), "Bone Axe");
			add(KoratioItems.BONE_PICKAXE.get(), "Bone Pickaxe");
			add(KoratioItems.BONE_HOE.get(), "Bone Hoe");
			add(KoratioItems.BONE_ICING_SPATULA.get(), "Bone Icing Spatula");
			
			add(KoratioItems.WITHER_BONE_HELMET.get(), "Wither Bone Helmet");
			add(KoratioItems.WITHER_BONE_CHESTPLATE.get(), "Wither Bone Chestplate");
			add(KoratioItems.WITHER_BONE_LEGGINGS.get(), "Wither Bone Leggings");
			add(KoratioItems.WITHER_BONE_BOOTS.get(), "Wither Bone Boots");
			add(KoratioItems.WITHER_BONE_SWORD.get(), "Wither Bone Sword");
			add(KoratioItems.WITHER_BONE_SHOVEL.get(), "Wither Bone Shovel");
			add(KoratioItems.WITHER_BONE_AXE.get(), "Wither Bone Axe");
			add(KoratioItems.WITHER_BONE_PICKAXE.get(), "Wither Bone Pickaxe");
			add(KoratioItems.WITHER_BONE_HOE.get(), "Wither Bone Hoe");
			add(KoratioItems.WITHER_BONE_ICING_SPATULA.get(), "Wither Bone Icing Spatula");
			
			add(KoratioItems.MAGICAL_CAT_SPAWN_EGG.get(), "Magical Cat Spawn Egg");
			add(KoratioItems.JUMSTEM_SPAWN_EGG.get(), "Jumstem Spawn Egg");
			add(KoratioItems.SPIKY_PIG_SPAWN_EGG.get(), "Spiky Pig Spawn Egg");
			add(KoratioItems.DEMONIC_SOLDIER_SPAWN_EGG.get(), "Demonic Soldier Spawn Egg");
			add(KoratioItems.DEMONIC_ZOMBIE_SPAWN_EGG.get(), "Demonic Zombie Spawn Egg");
			add(KoratioItems.DEMONIC_SKELETON_SPAWN_EGG.get(), "Demonic Skeleton Spawn Egg");
		}
		
		@Override
		protected void addEnchantments() {
		}
		
		@Override
		protected void addBlocks() {
			add(KoratioBlocks.FANTASIA_PORTAL.get(), "Fantasia Portal");
			add(KoratioBlocks.FLIPPED_FARMLAND.get(), "Flipped Farmland");
			add(KoratioBlocks.DECRYPTING_TABLE.get(), "Decrypting Table");
			add(KoratioBlocks.WOODCUTTER.get(), "Woodcutter");
			add(KoratioBlocks.CANDY_SHAPER.get(), "Candy Shaper");

			add(KoratioBlocks.BLOOD.get(), "Blood");
			add(KoratioBlocks.CHOCOLATE_MILK.get(), "Chocolate Milk");
			add(KoratioBlocks.MOLTEN_WHITE_SUGAR.get(), "Molten White Sugar");
			add(KoratioBlocks.MOLTEN_LIGHT_GRAY_SUGAR.get(), "Molten Light Gray Sugar");
			add(KoratioBlocks.MOLTEN_GRAY_SUGAR.get(), "Molten Gray Sugar");
			add(KoratioBlocks.MOLTEN_BLACK_SUGAR.get(), "Molten Black Sugar");
			add(KoratioBlocks.MOLTEN_BROWN_SUGAR.get(), "Molten Brown Sugar");
			add(KoratioBlocks.MOLTEN_RED_SUGAR.get(), "Molten Red Sugar");
			add(KoratioBlocks.MOLTEN_ORANGE_SUGAR.get(), "Molten Orange Sugar");
			add(KoratioBlocks.MOLTEN_YELLOW_SUGAR.get(), "Molten Yellow Sugar");
			add(KoratioBlocks.MOLTEN_LIME_SUGAR.get(), "Molten Lime Sugar");
			add(KoratioBlocks.MOLTEN_GREEN_SUGAR.get(), "Molten Green Sugar");
			add(KoratioBlocks.MOLTEN_CYAN_SUGAR.get(), "Molten Cyan Sugar");
			add(KoratioBlocks.MOLTEN_LIGHT_BLUE_SUGAR.get(), "Molten Light Blue Sugar");
			add(KoratioBlocks.MOLTEN_BLUE_SUGAR.get(), "Molten Blue Sugar");
			add(KoratioBlocks.MOLTEN_PURPLE_SUGAR.get(), "Molten Purple Sugar");
			add(KoratioBlocks.MOLTEN_MAGENTA_SUGAR.get(), "Molten Magenta Sugar");
			add(KoratioBlocks.MOLTEN_PINK_SUGAR.get(), "Molten Pink Sugar");

			add(KoratioBlocks.RUBY_ORE.get(), "Ruby Ore");
			add(KoratioBlocks.DEEPSLATE_RUBY_ORE.get(), "Deepslate Ruby Ore");
			add(KoratioBlocks.SAPPHIRE_ORE.get(), "Sapphire Ore");
			add(KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), "Deepslate Sapphire Ore");
			add(KoratioBlocks.TOPAZ_ORE.get(), "Topaz Ore");
			add(KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get(), "Deepslate Topaz Ore");
			add(KoratioBlocks.ONYX_ORE.get(), "Onyx Ore");
			add(KoratioBlocks.DEEPSLATE_ONYX_ORE.get(), "Deepslate Onyx Ore");

			add(KoratioBlocks.OAK_LEAF_PANE.get(), "Oak Leaf Pane");
			add(KoratioBlocks.SPRUCE_LEAF_PANE.get(), "Spruce Leaf Pane");
			add(KoratioBlocks.BIRCH_LEAF_PANE.get(), "Birch Leaf Pane");
			add(KoratioBlocks.JUNGLE_LEAF_PANE.get(), "Jungle Leaf Pane");
			add(KoratioBlocks.ACACIA_LEAF_PANE.get(), "Acacia Leaf Pane");
			add(KoratioBlocks.DARK_OAK_LEAF_PANE.get(), "Dark Oak Leaf Pane");
			add(KoratioBlocks.MANGROVE_LEAF_PANE.get(), "Mangrove Leaf Pane");
			add(KoratioBlocks.AZALEA_LEAF_PANE.get(), "Azalea Leaf Pane");
			add(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.get(), "Flowering Azalea Leaf Pane");
			add(KoratioBlocks.CHERRY_LEAF_PANE.get(), "Cherry Leaf Pane");

			add(KoratioBlocks.TALL_OAK_DOOR.get(), "Tall Oak Door");
			add(KoratioBlocks.TALL_SPRUCE_DOOR.get(), "Tall Spruce Door");
			add(KoratioBlocks.TALL_BIRCH_DOOR.get(), "Tall Birch Door");
			add(KoratioBlocks.TALL_JUNGLE_DOOR.get(), "Tall Jungle Door");
			add(KoratioBlocks.TALL_ACACIA_DOOR.get(), "Tall Acacia Door");
			add(KoratioBlocks.TALL_DARK_OAK_DOOR.get(), "Tall Dark Oak Door");
			add(KoratioBlocks.TALL_MANGROVE_DOOR.get(), "Tall Mangrove Door");
			add(KoratioBlocks.TALL_CHERRY_DOOR.get(), "Tall Cherry Door");
			add(KoratioBlocks.TALL_BAMBOO_DOOR.get(), "Tall Bamboo Door");
			add(KoratioBlocks.TALL_CRIMSON_DOOR.get(), "Tall Crimson Door");
			add(KoratioBlocks.TALL_WARPED_DOOR.get(), "Tall Warped Door");
			add(KoratioBlocks.TALL_IRON_DOOR.get(), "Tall Iron Door");
			add(KoratioBlocks.TALL_COPPER_DOOR.get(), "Tall Copper Door");
			add(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get(), "Tall Exposed Copper Door");
			add(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get(), "Tall Weathered Copper Door");
			add(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get(), "Tall Oxidized Copper Door");
			add(KoratioBlocks.TALL_WAXED_COPPER_DOOR.get(), "Tall Waxed Copper Door");
			add(KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get(), "Tall Waxed Exposed Copper Door");
			add(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get(), "Tall Waxed Weathered Copper Door");
			add(KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get(), "Tall Waxed Oxidized Copper Door");

			add(KoratioBlocks.RAINBOW_TORCH.get(), "Rainbow Torch");
			add(KoratioBlocks.RAINBOW_LANTERN.get(), "Rainbow Lantern");
			add(KoratioBlocks.RAINBOW_CAMPFIRE.get(), "Rainbow Campfire");
			add(KoratioBlocks.RAINBOW_CANDLE.get(), "Rainbow Candle");
			add(KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.get(), "Small Rainbow Crystal Bud");
			add(KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD.get(), "Medium Rainbow Crystal Bud");
			add(KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD.get(), "Large Rainbow Crystal Bud");
			add(KoratioBlocks.RAINBOW_CRYSTAL_CLUSTER.get(), "Rainbow Crystal Cluster");
			add(KoratioBlocks.RAINBOW_CRYSTAL_BLOCK.get(), "Rainbow Crystal Block");
			add(KoratioBlocks.BUDDING_RAINBOW_CRYSTAL.get(), "Budding Rainbow Crystal");
			add(KoratioBlocks.RAINBOW_CRYSTAL_GLASS.get(), "Rainbow Crystal Glass");
			add(KoratioBlocks.RAINBOW_CRYSTAL_GLASS_PANE.get(), "Rainbow Crystal Glass Pane");

			add(KoratioBlocks.RAINBOW_ROSE.get(), "Rainbow Rose");
			add(KoratioBlocks.RAINBOW_ALLIUM.get(), "Rainbow Allium");
			add(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), "Rainbow Lily of the Valley");
			add(KoratioBlocks.COOKIE_FLOWER.get(), "Cookie Flower");
			add(KoratioBlocks.ICE_ROSE.get(), "Ice Rose");
			add(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get(), "White Sugarglass Flower");
			add(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get(), "Blue Sugarglass Flower");
			add(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get(), "Green Sugarglass Flower");
			add(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get(), "Yellow Sugarglass Flower");
			add(KoratioBlocks.RED_SUGARGLASS_FLOWER.get(), "Red Sugarglass Flower");

			add(KoratioBlocks.ANCIENT_STONE.get(), "Ancient Stone");
			add(KoratioBlocks.ANCIENT_STONE_STAIRS.get(), "Ancient Stone Stairs");
			add(KoratioBlocks.ANCIENT_STONE_SLAB.get(), "Ancient Stone Slab");
			add(KoratioBlocks.ANCIENT_COBBLESTONE.get(), "Ancient Cobblestone");
			add(KoratioBlocks.ANCIENT_COBBLESTONE_STAIRS.get(), "Ancient Cobblestone Stairs");
			add(KoratioBlocks.ANCIENT_COBBLESTONE_SLAB.get(), "Ancient Cobblestone Slab");
			add(KoratioBlocks.ANCIENT_COBBLESTONE_WALL.get(), "Ancient Cobblestone Wall");
			add(KoratioBlocks.ANCIENT_STONE_BRICKS.get(), "Ancient Stone Bricks");
			add(KoratioBlocks.CRACKED_ANCIENT_STONE_BRICKS.get(), "Cracked Ancient Stone Bricks");
			add(KoratioBlocks.CHISELED_ANCIENT_STONE_BRICKS.get(), "Chiseled Ancient Stone Bricks");
			add(KoratioBlocks.ANCIENT_STONE_BRICK_STAIRS.get(), "Ancient Stone Brick Stairs");
			add(KoratioBlocks.ANCIENT_STONE_BRICK_SLAB.get(), "Ancient Stone Brick Slab");
			add(KoratioBlocks.ANCIENT_STONE_BRICK_WALL.get(), "Ancient Stone Brick Wall");
			add(KoratioBlocks.POLISHED_ANCIENT_STONE.get(), "Polished Ancient Stone");
			add(KoratioBlocks.POLISHED_ANCIENT_STONE_STAIRS.get(), "Polished Ancient Stone Stairs");
			add(KoratioBlocks.POLISHED_ANCIENT_STONE_SLAB.get(), "Polished Ancient Stone Slab");
			add(KoratioBlocks.POLISHED_ANCIENT_STONE_WALL.get(), "Polished Ancient Stone Wall");
			add(KoratioBlocks.ANCIENT_STONE_TILES.get(), "Ancient Stone Tiles");
			add(KoratioBlocks.ANCIENT_STONE_TILE_STAIRS.get(), "Ancient Stone Tile Stairs");
			add(KoratioBlocks.ANCIENT_STONE_TILE_SLAB.get(), "Ancient Stone Tile Slab");
			add(KoratioBlocks.ANCIENT_STONE_TILE_WALL.get(), "Ancient Stone Tile Wall");
			add(KoratioBlocks.ANCIENT_FURNACE.get(), "Ancient Furnace");
			add(KoratioBlocks.ANCIENT_TELEPORTER.get(), "Ancient Teleporter");

			add(KoratioBlocks.PANGO_PLANKS.get(), "Pango Planks");
			add(KoratioBlocks.PANGO_LOG.get(), "Pango Log");
			add(KoratioBlocks.STRIPPED_PANGO_LOG.get(), "Stripped Pango Log");
			add(KoratioBlocks.PANGO_WOOD.get(), "Pango Wood");
			add(KoratioBlocks.STRIPPED_PANGO_WOOD.get(), "Stripped Pango Wood");
			add(KoratioBlocks.PANGO_LEAVES.get(), "Pango Leaves");
			add(KoratioBlocks.PANGO_LEAF_PANE.get(), "Pango Leaf Pane");
			add(KoratioBlocks.PANGO_SLAB.get(), "Pango Slab");
			add(KoratioBlocks.PANGO_STAIRS.get(), "Pango Stairs");
			add(KoratioBlocks.PANGO_SAPLING.get(), "Pango Sapling");
			add(KoratioBlocks.PANGO_FENCE.get(), "Pango Fence");
			add(KoratioBlocks.PANGO_SIGN.get(), "Pango Sign");
			add(KoratioBlocks.PANGO_HANGING_SIGN.get(), "Pango Hanging Sign");
			add(KoratioBlocks.PANGO_CHEST.get(), "Pango Chest");
			add(KoratioBlocks.TRAPPED_PANGO_CHEST.get(), "Trapped Pango Chest");
			add(KoratioBlocks.PANGO_BOOKSHELF.get(), "Pango Bookshelf");
			add(KoratioBlocks.PANGO_BUTTON.get(), "Pango Button");
			add(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), "Pango Pressure Plate");
			add(KoratioBlocks.PANGO_DOOR.get(), "Pango Door");
			add(KoratioBlocks.TALL_PANGO_DOOR.get(), "Tall Pango Door");
			add(KoratioBlocks.PANGO_TRAPDOOR.get(), "Pango Trapdoor");
			add(KoratioBlocks.PANGO_FENCE_GATE.get(), "Pango Fence Gate");
			
			add(KoratioBlocks.RUGONA_PLANKS.get(), "Rugona Planks");
			add(KoratioBlocks.RUGONA_LOG.get(), "Rugona Log");
			add(KoratioBlocks.STRIPPED_RUGONA_LOG.get(), "Stripped Rugona Log");
			add(KoratioBlocks.RUGONA_WOOD.get(), "Rugona Wood");
			add(KoratioBlocks.STRIPPED_RUGONA_WOOD.get(), "Stripped Rugona Wood");
			add(KoratioBlocks.RUGONA_LEAVES.get(), "Rugona Leaves");
			add(KoratioBlocks.RUGONA_LEAF_PANE.get(), "Rugona Leaf Pane");
			add(KoratioBlocks.RUGONA_SLAB.get(), "Rugona Slab");
			add(KoratioBlocks.RUGONA_STAIRS.get(), "Rugona Stairs");
			add(KoratioBlocks.RUGONA_SAPLING.get(), "Rugona Sapling");
			add(KoratioBlocks.RUGONA_FENCE.get(), "Rugona Fence");
			add(KoratioBlocks.RUGONA_SIGN.get(), "Rugona Sign");
			add(KoratioBlocks.RUGONA_HANGING_SIGN.get(), "Rugona Hanging Sign");
			add(KoratioBlocks.RUGONA_CHEST.get(), "Rugona Chest");
			add(KoratioBlocks.TRAPPED_RUGONA_CHEST.get(), "Trapped Rugona Chest");
			add(KoratioBlocks.RUGONA_BOOKSHELF.get(), "Rugona Bookshelf");
			add(KoratioBlocks.RUGONA_BUTTON.get(), "Rugona Button");
			add(KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), "Rugona Pressure Plate");
			add(KoratioBlocks.RUGONA_DOOR.get(), "Rugona Door");
			add(KoratioBlocks.TALL_RUGONA_DOOR.get(), "Tall Rugona Door");
			add(KoratioBlocks.RUGONA_TRAPDOOR.get(), "Rugona Trapdoor");
			add(KoratioBlocks.RUGONA_FENCE_GATE.get(), "Rugona Fence Gate");

			add(KoratioBlocks.ELVEN_PLANKS.get(), "Elven Planks");
			add(KoratioBlocks.BLUE_ELVEN_PLANKS.get(), "Blue Elven Planks");
			add(KoratioBlocks.CYAN_ELVEN_PLANKS.get(), "Cyan Elven Planks");
			add(KoratioBlocks.GREEN_ELVEN_PLANKS.get(), "Green Elven Planks");
			add(KoratioBlocks.ELVEN_LOG.get(), "Elven Log");
			add(KoratioBlocks.STRIPPED_ELVEN_LOG.get(), "Stripped Elven Log");
			add(KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get(), "Stripped Blue Elven Log");
			add(KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get(), "Stripped Cyan Elven Log");
			add(KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get(), "Stripped Green Elven Log");
			add(KoratioBlocks.ELVEN_WOOD.get(), "Elven Wood");
			add(KoratioBlocks.STRIPPED_ELVEN_WOOD.get(), "Stripped Elven Wood");
			add(KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.get(), "Stripped Blue Elven Wood");
			add(KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.get(), "Stripped Cyan Elven Wood");
			add(KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.get(), "Stripped Green Elven Wood");
			add(KoratioBlocks.ELVEN_LEAVES.get(), "Elven Leaves");
			add(KoratioBlocks.ELVEN_LEAF_PANE.get(), "Elven Leaf Pane");
			add(KoratioBlocks.ELVEN_SLAB.get(), "Elven Slab");
			add(KoratioBlocks.BLUE_ELVEN_SLAB.get(), "Blue Elven Slab");
			add(KoratioBlocks.CYAN_ELVEN_SLAB.get(), "Cyan Elven Slab");
			add(KoratioBlocks.GREEN_ELVEN_SLAB.get(), "Green Elven Slab");
			add(KoratioBlocks.ELVEN_STAIRS.get(), "Elven Stairs");
			add(KoratioBlocks.BLUE_ELVEN_STAIRS.get(), "Blue Elven Stairs");
			add(KoratioBlocks.CYAN_ELVEN_STAIRS.get(), "Cyan Elven Stairs");
			add(KoratioBlocks.GREEN_ELVEN_STAIRS.get(), "Green Elven Stairs");
			add(KoratioBlocks.ELVEN_SAPLING.get(), "Elven Sapling");
			add(KoratioBlocks.ELVEN_FENCE.get(), "Elven Fence");
			add(KoratioBlocks.BLUE_ELVEN_FENCE.get(), "Blue Elven Fence");
			add(KoratioBlocks.CYAN_ELVEN_FENCE.get(), "Cyan Elven Fence");
			add(KoratioBlocks.GREEN_ELVEN_FENCE.get(), "Green Elven Fence");
			add(KoratioBlocks.ELVEN_SIGN.get(), "Elven Sign");
			add(KoratioBlocks.BLUE_ELVEN_SIGN.get(), "Blue Elven Sign");
			add(KoratioBlocks.CYAN_ELVEN_SIGN.get(), "Cyan Elven Sign");
			add(KoratioBlocks.GREEN_ELVEN_SIGN.get(), "Green Elven Sign");
			add(KoratioBlocks.ELVEN_HANGING_SIGN.get(), "Elven Hanging Sign");
			add(KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get(), "Blue Elven Hanging Sign");
			add(KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get(), "Cyan Elven Hanging Sign");
			add(KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get(), "Green Elven Hanging Sign");
			add(KoratioBlocks.ELVEN_CHEST.get(), "Elven Chest");
			add(KoratioBlocks.TRAPPED_ELVEN_CHEST.get(), "Trapped Elven Chest");
			add(KoratioBlocks.BLUE_ELVEN_CHEST.get(), "Blue Elven Chest");
			add(KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get(), "Trapped Blue Elven Chest");
			add(KoratioBlocks.CYAN_ELVEN_CHEST.get(), "Cyan Elven Chest");
			add(KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get(), "Trapped Cyan Elven Chest");
			add(KoratioBlocks.GREEN_ELVEN_CHEST.get(), "Green Elven Chest");
			add(KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get(), "Trapped Green Elven Chest");
			add(KoratioBlocks.ELVEN_BOOKSHELF.get(), "Elven Bookshelf");
			add(KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get(), "Blue Elven Bookshelf");
			add(KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get(), "Cyan Elven Bookshelf");
			add(KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get(), "Green Elven Bookshelf");
			add(KoratioBlocks.ELVEN_BUTTON.get(), "Elven Button");
			add(KoratioBlocks.BLUE_ELVEN_BUTTON.get(), "Blue Elven Button");
			add(KoratioBlocks.CYAN_ELVEN_BUTTON.get(), "Cyan Elven Button");
			add(KoratioBlocks.GREEN_ELVEN_BUTTON.get(), "Green Elven Button");
			add(KoratioBlocks.ELVEN_PRESSURE_PLATE.get(), "Elven Pressure Plate");
			add(KoratioBlocks.BLUE_ELVEN_PRESSURE_PLATE.get(), "Blue Elven Pressure Plate");
			add(KoratioBlocks.CYAN_ELVEN_PRESSURE_PLATE.get(), "Cyan Elven Pressure Plate");
			add(KoratioBlocks.GREEN_ELVEN_PRESSURE_PLATE.get(), "Green Elven Pressure Plate");
			add(KoratioBlocks.ELVEN_DOOR.get(), "Elven Door");
			add(KoratioBlocks.TALL_ELVEN_DOOR.get(), "Tall Elven Door");
			add(KoratioBlocks.BLUE_ELVEN_DOOR.get(), "Blue Elven Door");
			add(KoratioBlocks.TALL_BLUE_ELVEN_DOOR.get(), "Tall Blue Elven Door");
			add(KoratioBlocks.CYAN_ELVEN_DOOR.get(), "Cyan Elven Door");
			add(KoratioBlocks.TALL_CYAN_ELVEN_DOOR.get(), "Tall Cyan Elven Door");
			add(KoratioBlocks.GREEN_ELVEN_DOOR.get(), "Green Elven Door");
			add(KoratioBlocks.TALL_GREEN_ELVEN_DOOR.get(), "Tall Green Elven Door");
			add(KoratioBlocks.ELVEN_TRAPDOOR.get(), "Elven Trapdoor");
			add(KoratioBlocks.BLUE_ELVEN_TRAPDOOR.get(), "Blue Elven Trapdoor");
			add(KoratioBlocks.CYAN_ELVEN_TRAPDOOR.get(), "Cyan Elven Trapdoor");
			add(KoratioBlocks.GREEN_ELVEN_TRAPDOOR.get(), "Green Elven Trapdoor");
			add(KoratioBlocks.ELVEN_FENCE_GATE.get(), "Elven Fence Gate");
			add(KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get(), "Blue Elven Fence Gate");
			add(KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get(), "Cyan Elven Fence Gate");
			add(KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get(), "Green Elven Fence Gate");

			add(KoratioBlocks.CANDY_PLANKS.get(), "Candy Planks");
			add(KoratioBlocks.CANDY_LOG.get(), "Candy Log");
			add(KoratioBlocks.STRIPPED_CANDY_LOG.get(), "Stripped Candy Log");
			add(KoratioBlocks.CANDY_WOOD.get(), "Candy Wood");
			add(KoratioBlocks.STRIPPED_CANDY_WOOD.get(), "Stripped Candy Wood");
			add(KoratioBlocks.COTTON_CANDY_LEAVES.get(), "Cotton Candy Leaves");
			add(KoratioBlocks.COTTON_CANDY_LEAF_PANE.get(), "Cotton Candy Leaf Pane");
			add(KoratioBlocks.CANDY_SLAB.get(), "Candy Slab");
			add(KoratioBlocks.CANDY_STAIRS.get(), "Candy Stairs");
			add(KoratioBlocks.CANDY_SAPLING.get(), "Candy Sapling");
			add(KoratioBlocks.CANDY_FENCE.get(), "Candy Fence");
			add(KoratioBlocks.CANDY_SIGN.get(), "Candy Sign");
			add(KoratioBlocks.CANDY_HANGING_SIGN.get(), "Candy Hanging Sign");
			add(KoratioBlocks.CANDY_CHEST.get(), "Candy Chest");
			add(KoratioBlocks.TRAPPED_CANDY_CHEST.get(), "Trapped Candy Chest");
			add(KoratioBlocks.CANDY_BOOKSHELF.get(), "Candy Bookshelf");
			add(KoratioBlocks.CANDY_BUTTON.get(), "Candy Button");
			add(KoratioBlocks.CANDY_PRESSURE_PLATE.get(), "Candy Pressure Plate");
			add(KoratioBlocks.CANDY_DOOR.get(), "Candy Door");
			//add(KoratioBlocks.TALL_CANDY_DOOR.get(), "Tall Candy Door");
			add(KoratioBlocks.CANDY_TRAPDOOR.get(), "Candy Trapdoor");
			add(KoratioBlocks.CANDY_FENCE_GATE.get(), "Candy Fence Gate");
			
			add(KoratioBlocks.VARESO_PLANKS.get(), "Vareso Planks");
			add(KoratioBlocks.VARESO_LOG.get(), "Vareso Log");
			add(KoratioBlocks.STRIPPED_VARESO_LOG.get(), "Stripped Vareso Log");
			add(KoratioBlocks.VARESO_WOOD.get(), "Vareso Wood");
			add(KoratioBlocks.STRIPPED_VARESO_WOOD.get(), "Stripped Vareso Wood");
			add(KoratioBlocks.VARESO_LEAVES.get(), "Vareso Leaves");
			add(KoratioBlocks.VARESO_LEAF_PANE.get(), "Vareso Leaf Pane");
			add(KoratioBlocks.VARESO_SLAB.get(), "Vareso Slab");
			add(KoratioBlocks.VARESO_STAIRS.get(), "Vareso Stairs");
			add(KoratioBlocks.VARESO_SAPLING.get(), "Vareso Sapling");
			add(KoratioBlocks.VARESO_FENCE.get(), "Vareso Fence");
			add(KoratioBlocks.VARESO_SIGN.get(), "Vareso Sign");
			add(KoratioBlocks.VARESO_HANGING_SIGN.get(), "Vareso Hanging Sign");
			add(KoratioBlocks.VARESO_CHEST.get(), "Vareso Chest");
			add(KoratioBlocks.TRAPPED_VARESO_CHEST.get(), "Trapped Vareso Chest");
			add(KoratioBlocks.VARESO_BOOKSHELF.get(), "Vareso Bookshelf");
			add(KoratioBlocks.VARESO_BUTTON.get(), "Vareso Button");
			add(KoratioBlocks.VARESO_PRESSURE_PLATE.get(), "Vareso Pressure Plate");
			add(KoratioBlocks.VARESO_DOOR.get(), "Vareso Door");
			add(KoratioBlocks.TALL_VARESO_DOOR.get(), "Tall Vareso Door");
			add(KoratioBlocks.VARESO_TRAPDOOR.get(), "Vareso Trapdoor");
			add(KoratioBlocks.VARESO_FENCE_GATE.get(), "Vareso Fence Gate");
			
			add(KoratioBlocks.PURPLE_MUSHROOM.get(), "Purple Mushroom");
			add(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get(), "Purple Mushroom Block");
			add(KoratioBlocks.GREEN_MUSHROOM.get(), "Green Mushroom");
			add(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get(), "Green Mushroom Block");

			add(KoratioBlocks.GILDED_VINES.get(), "Gilded Vines");

			add(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), "Block of Rainbow Gem");
			add(KoratioBlocks.RUBY_BLOCK.get(), "Block of Ruby");
			add(KoratioBlocks.SAPPHIRE_BLOCK.get(), "Block of Sapphire");
			add(KoratioBlocks.TOPAZ_BLOCK.get(), "Block of Topaz");
			add(KoratioBlocks.ONYX_BLOCK.get(), "Block of Onyx");

			add(KoratioBlocks.WHITE_SUGAR_BLOCK.get(), "Block of White Sugar");
			add(KoratioBlocks.LIGHT_GRAY_SUGAR_BLOCK.get(), "Block of Light Gray Sugar");
			add(KoratioBlocks.GRAY_SUGAR_BLOCK.get(), "Block of Gray Sugar");
			add(KoratioBlocks.BLACK_SUGAR_BLOCK.get(), "Block of Black Sugar");
			add(KoratioBlocks.BROWN_SUGAR_BLOCK.get(), "Block of Brown Sugar");
			add(KoratioBlocks.RED_SUGAR_BLOCK.get(), "Block of Red Sugar");
			add(KoratioBlocks.ORANGE_SUGAR_BLOCK.get(), "Block of Orange Sugar");
			add(KoratioBlocks.YELLOW_SUGAR_BLOCK.get(), "Block of Yellow Sugar");
			add(KoratioBlocks.LIME_SUGAR_BLOCK.get(), "Block of Lime Sugar");
			add(KoratioBlocks.GREEN_SUGAR_BLOCK.get(), "Block of Green Sugar");
			add(KoratioBlocks.CYAN_SUGAR_BLOCK.get(), "Block of Cyan Sugar");
			add(KoratioBlocks.LIGHT_BLUE_SUGAR_BLOCK.get(), "Block of Light Blue Sugar");
			add(KoratioBlocks.BLUE_SUGAR_BLOCK.get(), "Block of Blue Sugar");
			add(KoratioBlocks.PURPLE_SUGAR_BLOCK.get(), "Block of Purple Sugar");
			add(KoratioBlocks.MAGENTA_SUGAR_BLOCK.get(), "Block of Magenta Sugar");
			add(KoratioBlocks.PINK_SUGAR_BLOCK.get(), "Block of Pink Sugar");

			add(KoratioBlocks.STICKY_WHITE_SUGAR_BLOCK.get(), "Block of Sticky White Sugar");
			add(KoratioBlocks.STICKY_LIGHT_GRAY_SUGAR_BLOCK.get(), "Block of Sticky Light Gray Sugar");
			add(KoratioBlocks.STICKY_GRAY_SUGAR_BLOCK.get(), "Block of Sticky Gray Sugar");
			add(KoratioBlocks.STICKY_BLACK_SUGAR_BLOCK.get(), "Block of Sticky Black Sugar");
			add(KoratioBlocks.STICKY_BROWN_SUGAR_BLOCK.get(), "Block of Sticky Brown Sugar");
			add(KoratioBlocks.STICKY_RED_SUGAR_BLOCK.get(), "Block of Sticky Red Sugar");
			add(KoratioBlocks.STICKY_ORANGE_SUGAR_BLOCK.get(), "Block of Sticky Orange Sugar");
			add(KoratioBlocks.STICKY_YELLOW_SUGAR_BLOCK.get(), "Block of Sticky Yellow Sugar");
			add(KoratioBlocks.STICKY_LIME_SUGAR_BLOCK.get(), "Block of Sticky Lime Sugar");
			add(KoratioBlocks.STICKY_GREEN_SUGAR_BLOCK.get(), "Block of Sticky Green Sugar");
			add(KoratioBlocks.STICKY_CYAN_SUGAR_BLOCK.get(), "Block of Sticky Cyan Sugar");
			add(KoratioBlocks.STICKY_LIGHT_BLUE_SUGAR_BLOCK.get(), "Block of Sticky Light Blue Sugar");
			add(KoratioBlocks.STICKY_BLUE_SUGAR_BLOCK.get(), "Block of Sticky Blue Sugar");
			add(KoratioBlocks.STICKY_PURPLE_SUGAR_BLOCK.get(), "Block of Sticky Purple Sugar");
			add(KoratioBlocks.STICKY_MAGENTA_SUGAR_BLOCK.get(), "Block of Sticky Magenta Sugar");
			add(KoratioBlocks.STICKY_PINK_SUGAR_BLOCK.get(), "Block of Sticky Pink Sugar");

			add(KoratioBlocks.WHITE_FROSTING_BLOCK.get(), "Block of White Frosting");
			add(KoratioBlocks.LIGHT_GRAY_FROSTING_BLOCK.get(), "Block of Light Gray Frosting");
			add(KoratioBlocks.GRAY_FROSTING_BLOCK.get(), "Block of Gray Frosting");
			add(KoratioBlocks.BLACK_FROSTING_BLOCK.get(), "Block of Black Frosting");
			add(KoratioBlocks.BROWN_FROSTING_BLOCK.get(), "Block of Brown Frosting");
			add(KoratioBlocks.RED_FROSTING_BLOCK.get(), "Block of Red Frosting");
			add(KoratioBlocks.ORANGE_FROSTING_BLOCK.get(), "Block of Orange Frosting");
			add(KoratioBlocks.YELLOW_FROSTING_BLOCK.get(), "Block of Yellow Frosting");
			add(KoratioBlocks.LIME_FROSTING_BLOCK.get(), "Block of Lime Frosting");
			add(KoratioBlocks.GREEN_FROSTING_BLOCK.get(), "Block of Green Frosting");
			add(KoratioBlocks.CYAN_FROSTING_BLOCK.get(), "Block of Cyan Frosting");
			add(KoratioBlocks.LIGHT_BLUE_FROSTING_BLOCK.get(), "Block of Light Blue Frosting");
			add(KoratioBlocks.BLUE_FROSTING_BLOCK.get(), "Block of Blue Frosting");
			add(KoratioBlocks.PURPLE_FROSTING_BLOCK.get(), "Block of Purple Frosting");
			add(KoratioBlocks.MAGENTA_FROSTING_BLOCK.get(), "Block of Magenta Frosting");
			add(KoratioBlocks.PINK_FROSTING_BLOCK.get(), "Block of Pink Frosting");

			add(KoratioBlocks.WHITE_CANDY_BLOCK.get(), "White Candy Block");
			add(KoratioBlocks.LIGHT_GRAY_CANDY_BLOCK.get(), "Light Gray Candy Block");
			add(KoratioBlocks.GRAY_CANDY_BLOCK.get(), "Gray Candy Block");
			add(KoratioBlocks.BLACK_CANDY_BLOCK.get(), "Black Candy Block");
			add(KoratioBlocks.BROWN_CANDY_BLOCK.get(), "Brown Candy Block");
			add(KoratioBlocks.RED_CANDY_BLOCK.get(), "Red Candy Block");
			add(KoratioBlocks.ORANGE_CANDY_BLOCK.get(), "Orange Candy Block");
			add(KoratioBlocks.YELLOW_CANDY_BLOCK.get(), "Yellow Candy Block");
			add(KoratioBlocks.LIME_CANDY_BLOCK.get(), "Lime Candy Block");
			add(KoratioBlocks.GREEN_CANDY_BLOCK.get(), "Green Candy Block");
			add(KoratioBlocks.CYAN_CANDY_BLOCK.get(), "Cyan Candy Block");
			add(KoratioBlocks.LIGHT_BLUE_CANDY_BLOCK.get(), "Light Blue Candy Block");
			add(KoratioBlocks.BLUE_CANDY_BLOCK.get(), "Blue Candy Block");
			add(KoratioBlocks.PURPLE_CANDY_BLOCK.get(), "Purple Candy Block");
			add(KoratioBlocks.MAGENTA_CANDY_BLOCK.get(), "Magenta Candy Block");
			add(KoratioBlocks.PINK_CANDY_BLOCK.get(), "Pink Candy Block");

			add(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(), "Raw Gingerbread Block");
			add(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get(), "Raw Gingerbread Stairs");
			add(KoratioBlocks.RAW_GINGERBREAD_SLAB.get(), "Raw Gingerbread Slab");
			add(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(), "Raw Gingerbread Bricks");
			add(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get(), "Raw Gingerbread Brick Stairs");
			add(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get(), "Raw Gingerbread Brick Slab");
			add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get(), "Raw Large Gingerbread Bricks");
			add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.get(), "Raw Large Gingerbread Brick Stairs");
			add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.get(), "Raw Large Gingerbread Brick Slab");
			add(KoratioBlocks.RAW_GINGERBREAD_WALL.get(), "Raw Gingerbread Wall");
			add(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.get(), "Raw Gingerbread Brick Wall");
			add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.get(), "Raw Large Gingerbread Brick Wall");
			add(KoratioBlocks.GINGERBREAD_BLOCK.get(), "Gingerbread Block");
			add(KoratioBlocks.GINGERBREAD_STAIRS.get(), "Gingerbread Stairs");
			add(KoratioBlocks.GINGERBREAD_SLAB.get(), "Gingerbread Slab");
			add(KoratioBlocks.GINGERBREAD_BRICKS.get(), "Gingerbread Bricks");
			add(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get(), "Gingerbread Brick Stairs");
			add(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(), "Gingerbread Brick Slab");
			add(KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get(), "Large Gingerbread Bricks");
			add(KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.get(), "Large Gingerbread Brick Stairs");
			add(KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.get(), "Large Gingerbread Brick Slab");
			add(KoratioBlocks.GINGERBREAD_WALL.get(), "Gingerbread Wall");
			add(KoratioBlocks.GINGERBREAD_BRICK_WALL.get(), "Gingerbread Brick Wall");
			add(KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.get(), "Large Gingerbread Brick Wall");

			add(KoratioBlocks.MARSHMALLOW_BLOCK.get(), "Marshmallow Block");

			add(KoratioBlocks.WHITE_LEVITATING_WOOL.get(), "White Levitating Wool");
			add(KoratioBlocks.ORANGE_LEVITATING_WOOL.get(), "Orange Levitating Wool");
			add(KoratioBlocks.MAGENTA_LEVITATING_WOOL.get(), "Magenta Levitating Wool");
			add(KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL.get(), "Light Blue Levitating Wool");
			add(KoratioBlocks.YELLOW_LEVITATING_WOOL.get(), "Yellow Levitating Wool");
			add(KoratioBlocks.LIME_LEVITATING_WOOL.get(), "Lime Levitating Wool");
			add(KoratioBlocks.PINK_LEVITATING_WOOL.get(), "Pink Levitating Wool");
			add(KoratioBlocks.GRAY_LEVITATING_WOOL.get(), "Gray Levitating Wool");
			add(KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL.get(), "Light Gray Levitating Wool");
			add(KoratioBlocks.CYAN_LEVITATING_WOOL.get(), "Cyan Levitating Wool");
			add(KoratioBlocks.PURPLE_LEVITATING_WOOL.get(), "Purple Levitating Wool");
			add(KoratioBlocks.BLUE_LEVITATING_WOOL.get(), "Blue Levitating Wool");
			add(KoratioBlocks.BROWN_LEVITATING_WOOL.get(), "Brown Levitating Wool");
			add(KoratioBlocks.GREEN_LEVITATING_WOOL.get(), "Green Levitating Wool");
			add(KoratioBlocks.RED_LEVITATING_WOOL.get(), "Red Levitating Wool");
			add(KoratioBlocks.BLACK_LEVITATING_WOOL.get(), "Black Levitating Wool");

		}
			
		@Override
		protected void addEntities() {
			add(KoratioEntityType.MAGICAL_CAT.get(), "Magical Cat");
			add(KoratioEntityType.FLUFFER.get(), "Fluffer");
			add(KoratioEntityType.SPIKY_PIG.get(), "Spiky Pig");

			add(KoratioEntityType.JUMSTEM.get(), "Jumstem");
			add(KoratioEntityType.MUSHROOM_SPORE.get(), "Mushroom Spore");
			add(KoratioEntityType.DEMONIC_ZOMBIE.get(), "Demonic Zombie");
			add(KoratioEntityType.DEMONIC_SKELETON.get(), "Demonic Skeleton");
			add(KoratioEntityType.DEMONIC_SOLDIER.get(), "Demonic Soldier");

			add(KoratioEntityType.BOAT.get(), "Boat");
			add(KoratioEntityType.CHEST_BOAT.get(), "Chest Boat");
			add(KoratioEntityType.LEVITATING_BLOCK.get(), "Levitating Block");
		}

		@Override
		protected void addBiomes() {
			add(KoratioBiomes.FANTASIA_FIELDLANDS, "Fantasia Fieldlands");
			add(KoratioBiomes.MUSHROOM_FOREST, "Mushroom Forest");
			add(KoratioBiomes.GILDED_FOREST, "Gilded Forest");
			add(KoratioBiomes.CANDY_CANE_VALLEY, "Candy Cane Valley");
			add(KoratioBiomes.RIVER, "River");
			add(KoratioBiomes.DEPTHS_OF_FANTASIA, "Depths of Fantasia");
			add(KoratioBiomes.SKYLANDS, "Skylands");
		}

		@Override
		protected void addTags() {
			add(KoratioTags.Items.SUGAR, "Sugar");
			add(KoratioTags.Items.FLUFFER_FOOD, "Fluffer Foods");
			add(KoratioTags.Items.MAGICAL_CAT_FOOD, "Magical Cat Foods");
			add(KoratioTags.Items.GEMS, "Gems");
			add(KoratioTags.Items.RAINBOW_GEMS, "Rainbow Gems");
			add(KoratioTags.Items.RUBY_GEMS, "Rubies");
			add(KoratioTags.Items.SAPPHIRE_GEMS, "Sapphires");
			add(KoratioTags.Items.TOPAZ_GEMS, "Topaz");
			add(KoratioTags.Items.ONYX_GEMS, "Onyxes");
			add(KoratioTags.Items.PANGO_LOGS, "Pango Logs");
			add(KoratioTags.Items.RUGONA_LOGS, "Rugona Logs");
			add(KoratioTags.Items.VARESO_LOGS, "Vareso Logs");
			add(KoratioTags.Items.ELVEN_LOGS, "Elven Logs");
			add(KoratioTags.Items.LOGS, "Logs");
			add(KoratioTags.Items.LEAF_PANES, "Leaf Panes");
			add(KoratioTags.Items.FENCES, "Fences");
			add(KoratioTags.Items.FENCE_GATES, "Fence Gates");
			add(KoratioTags.Items.TALL_WOODEN_DOORS, "Tall Wooden Doors");
			add(KoratioTags.Items.TALL_DOORS, "Tall Doors");
			add(KoratioTags.Items.RAINBOW_GEM_STORAGE_BLOCKS, "Rainbow Gem Storage Blocks");
			add(KoratioTags.Items.RUBY_STORAGE_BLOCKS, "Ruby Storage Blocks");
			add(KoratioTags.Items.SAPPHIRE_STORAGE_BLOCKS, "Sapphire Storage Blocks");
			add(KoratioTags.Items.TOPAZ_STORAGE_BLOCKS, "Topaz Storage Blocks");
			add(KoratioTags.Items.ONYX_STORAGE_BLOCKS, "Onyx Storage Blocks");
			add(KoratioTags.Items.RUBY_ORES, "Ruby Ores");
			add(KoratioTags.Items.SAPPHIRE_ORES, "Sapphire Ores");
			add(KoratioTags.Items.TOPAZ_ORES, "Topaz Ores");
			add(KoratioTags.Items.ONYX_ORES, "Onyx Ores");

			add(KoratioTags.Blocks.PANGO_LOGS, "Pango Logs");
			add(KoratioTags.Blocks.RUGONA_LOGS, "Rugona Logs");
			add(KoratioTags.Blocks.VARESO_LOGS, "Vareso Logs");
			add(KoratioTags.Blocks.ELVEN_LOGS, "Elven Logs");
			add(KoratioTags.Blocks.LOGS, "Logs");
			add(KoratioTags.Blocks.LEAF_PANES, "Leaf Panes");
			add(KoratioTags.Blocks.FENCES, "Fences");
			add(KoratioTags.Blocks.FENCE_GATES, "Fence Gates");
			add(KoratioTags.Blocks.TALL_WOODEN_DOORS, "Tall Wooden Doors");
			add(KoratioTags.Blocks.TALL_DOORS, "Tall Doors");
			add(KoratioTags.Blocks.MUSHROOMS, "Mushrooms");
			add(KoratioTags.Blocks.BASE_STONE_FANTASIA, "Fantasia Base Stone");
			add(KoratioTags.Blocks.FANTASIA_CARVER_REPLACEABLES, "Fantasia Carver Replaceables");
			add(KoratioTags.Blocks.CRYSTAL_CAVE_CRYSTALS, "Crystal Cave Crystals");
			add(KoratioTags.Blocks.COOKIE_ORES, "Cookie Ores");
			add(KoratioTags.Blocks.RAINBOW_GEM_STORAGE_BLOCKS, "Rainbow Gem Storage Blocks");
			add(KoratioTags.Blocks.RUBY_STORAGE_BLOCKS, "Ruby Storage Blocks");
			add(KoratioTags.Blocks.SAPPHIRE_STORAGE_BLOCKS, "Sapphire Storage Blocks");
			add(KoratioTags.Blocks.TOPAZ_STORAGE_BLOCKS, "Topaz Storage Blocks");
			add(KoratioTags.Blocks.ONYX_STORAGE_BLOCKS, "Onyx Storage Blocks");
			add(KoratioTags.Blocks.RUBY_ORES, "Ruby Ores");
			add(KoratioTags.Blocks.SAPPHIRE_ORES, "Sapphire Ores");
			add(KoratioTags.Blocks.TOPAZ_ORES, "Topaz Ores");
			add(KoratioTags.Blocks.ONYX_ORES, "Onyx Ores");

			add(KoratioTags.Entities.DEMONS, "Demons");
		}

		@Override
		protected void addSounds() {
			add(KoratioSoundEvents.CRYSTALLIZE_AMBIENT.get(), "Crystallize trembles");
			add(KoratioSoundEvents.CRYSTALLIZE_HURT.get(), "Crystallize hurts");
			add(KoratioSoundEvents.CRYSTALLIZE_DEATH.get(), "Crystallize dies");
			
			add(KoratioSoundEvents.SPIKY_PIG_AMBIENT.get(), "Spiky Pig oinks");
			add(KoratioSoundEvents.SPIKY_PIG_HURT.get(), "Spiky Pig hurts");
			add(KoratioSoundEvents.SPIKY_PIG_DEATH.get(), "Spiky Pig dies");
		}

		@Override
		protected void addScrolls() {
			add(KoratioScrolls.A_NEW_WORLD, "A new world", "");
			add(KoratioScrolls.DEMONS, "Demons..", "");
		}
	}
	
	public static class German extends LanguageGenerator {

		public German(PackOutput output) {
			super(output, "de_de");
		}

		@Override
		protected void addMisc() {			
		}
		
		@Override
		protected void addAdvancements() {			
		}
		
		@Override
		protected void addItems() {
		}

		@Override
		protected void addEnchantments() {
		}

		@Override
		protected void addBlocks() {
		}

		@Override
		protected void addBiomes() {
		}

		@Override
		protected void addEntities() {
		}

		@Override
		protected void addTags() {
		}

		@Override
		protected void addSounds() {			
		}

		@Override
		protected void addScrolls() {			
		}
	}

	private static String createCandyName(CandyItem candy) {
		String name = candy.getDescriptionId();
		name = name.replaceFirst("item.koratio.", "");
		name = name.replaceFirst("white", "White").replaceFirst("light_gray", "Light Gray").replaceFirst("gray", "Gray").replaceFirst("black", "Black").replaceFirst("brown", "Brown").replaceFirst("red", "Red").replaceFirst("orange", "Orange").replaceFirst("yellow", "Yellow").replaceFirst("lime", "Lime").replaceFirst("green", "Green").replaceFirst("cyan", "Cyan").replaceFirst("light_blue", "Light Blue").replaceFirst("blue", "Blue").replaceFirst("purple", "Purple").replaceFirst("magenta", "Magenta").replaceFirst("pink", "Pink").replaceFirst("candy_cane", "Candy Cane").replaceFirst("bonbon", "Bonbon").replaceFirst("lollipop", "Lollipop");
		name = name.replace("_", " ");
		return name;
	}
}