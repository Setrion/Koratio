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
        add(key.location().toLanguageKey("subtitles").replaceFirst("koratio.entity.koratio", "koratio.entity"), name);
    }
    
    public void addScroll(Supplier<? extends Scroll> key, String name, String text) {
    	add("scroll."+key.get().name()+".title", name);
    	add("scroll."+key.get().name()+".text", text);
    }
    
    public void add(Scroll key, String name, String text) {
    	add("scroll."+key.name()+".title", name);
    	add("scroll."+key.name()+".text", text);
    }
	
	public static class English extends LanguageGenerator {

		public English(PackOutput output) {
			super(output, "en_us");
		}
	
		@Override
		protected void addMisc() {
			add("itemGroup.koratio.main", "Koratio");
			add("itemGroup.koratio.fantasia", "Koratio - Fantasia");
			add("itemGroup.koratio.candy", "Koratio - Candy");

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

			add("tooltip.koratio.hold_shift", "Hold §6Shift§r for more Information");
			add("tooltip.koratio.piping_bag.empty", "Empty");
			add("tooltip.koratio.piping_bag.empty_fill", "Right-click with this in your §6Mainhand§r, while holding any color of §6Icing§r in your §6Offhand§r");
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
			add(KoratioEnchantments.TELEKINESIS.location().toLanguageKey("enchantment"), "Telekinesis");
		}
		
		@Override
		protected void addBlocks() {
			add(KoratioBlocks.FANTASIA_PORTAL.get(), "Fantasia Portal");
			add(KoratioBlocks.FLIPPED_FARMLAND.asItem(), "Flipped Farmland");
			add(KoratioBlocks.DECRYPTING_TABLE.asItem(), "Decrypting Table");
			add(KoratioBlocks.WOODCUTTER.asItem(), "Woodcutter");
			add(KoratioBlocks.CANDY_SHAPER.asItem(), "Candy Shaper");

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

			add(KoratioBlocks.RUBY_ORE.asItem(), "Ruby Ore");
			add(KoratioBlocks.DEEPSLATE_RUBY_ORE.asItem(), "Deepslate Ruby Ore");
			add(KoratioBlocks.SAPPHIRE_ORE.asItem(), "Sapphire Ore");
			add(KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.asItem(), "Deepslate Sapphire Ore");
			add(KoratioBlocks.TOPAZ_ORE.asItem(), "Topaz Ore");
			add(KoratioBlocks.DEEPSLATE_TOPAZ_ORE.asItem(), "Deepslate Topaz Ore");
			add(KoratioBlocks.ONYX_ORE.asItem(), "Onyx Ore");
			add(KoratioBlocks.DEEPSLATE_ONYX_ORE.asItem(), "Deepslate Onyx Ore");

			add(KoratioBlocks.OAK_LEAF_PANE.asItem(), "Oak Leaf Pane");
			add(KoratioBlocks.SPRUCE_LEAF_PANE.asItem(), "Spruce Leaf Pane");
			add(KoratioBlocks.BIRCH_LEAF_PANE.asItem(), "Birch Leaf Pane");
			add(KoratioBlocks.JUNGLE_LEAF_PANE.asItem(), "Jungle Leaf Pane");
			add(KoratioBlocks.ACACIA_LEAF_PANE.asItem(), "Acacia Leaf Pane");
			add(KoratioBlocks.DARK_OAK_LEAF_PANE.asItem(), "Dark Oak Leaf Pane");
			add(KoratioBlocks.MANGROVE_LEAF_PANE.asItem(), "Mangrove Leaf Pane");
			add(KoratioBlocks.AZALEA_LEAF_PANE.asItem(), "Azalea Leaf Pane");
			add(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.asItem(), "Flowering Azalea Leaf Pane");
			add(KoratioBlocks.CHERRY_LEAF_PANE.asItem(), "Cherry Leaf Pane");

			add(KoratioBlocks.TALL_OAK_DOOR.asItem(), "Tall Oak Door");
			add(KoratioBlocks.TALL_SPRUCE_DOOR.asItem(), "Tall Spruce Door");
			add(KoratioBlocks.TALL_BIRCH_DOOR.asItem(), "Tall Birch Door");
			add(KoratioBlocks.TALL_JUNGLE_DOOR.asItem(), "Tall Jungle Door");
			add(KoratioBlocks.TALL_ACACIA_DOOR.asItem(), "Tall Acacia Door");
			add(KoratioBlocks.TALL_DARK_OAK_DOOR.asItem(), "Tall Dark Oak Door");
			add(KoratioBlocks.TALL_MANGROVE_DOOR.asItem(), "Tall Mangrove Door");
			add(KoratioBlocks.TALL_CHERRY_DOOR.asItem(), "Tall Cherry Door");
			add(KoratioBlocks.TALL_BAMBOO_DOOR.asItem(), "Tall Bamboo Door");
			add(KoratioBlocks.TALL_CRIMSON_DOOR.asItem(), "Tall Crimson Door");
			add(KoratioBlocks.TALL_WARPED_DOOR.asItem(), "Tall Warped Door");
			add(KoratioBlocks.TALL_IRON_DOOR.asItem(), "Tall Iron Door");
			add(KoratioBlocks.TALL_COPPER_DOOR.asItem(), "Tall Copper Door");
			add(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.asItem(), "Tall Exposed Copper Door");
			add(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.asItem(), "Tall Weathered Copper Door");
			add(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.asItem(), "Tall Oxidized Copper Door");
			add(KoratioBlocks.TALL_WAXED_COPPER_DOOR.asItem(), "Tall Waxed Copper Door");
			add(KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.asItem(), "Tall Waxed Exposed Copper Door");
			add(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.asItem(), "Tall Waxed Weathered Copper Door");
			add(KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.asItem(), "Tall Waxed Oxidized Copper Door");

			add(KoratioBlocks.RAINBOW_TORCH.asItem(), "Rainbow Torch");
			add(KoratioBlocks.RAINBOW_LANTERN.asItem(), "Rainbow Lantern");
			add(KoratioBlocks.RAINBOW_CAMPFIRE.asItem(), "Rainbow Campfire");
			add(KoratioBlocks.RAINBOW_CANDLE.asItem(), "Rainbow Candle");
			add(KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.asItem(), "Small Rainbow Crystal Bud");
			add(KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD.asItem(), "Medium Rainbow Crystal Bud");
			add(KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD.asItem(), "Large Rainbow Crystal Bud");
			add(KoratioBlocks.RAINBOW_CRYSTAL_CLUSTER.asItem(), "Rainbow Crystal Cluster");
			add(KoratioBlocks.RAINBOW_CRYSTAL_BLOCK.asItem(), "Rainbow Crystal Block");
			add(KoratioBlocks.BUDDING_RAINBOW_CRYSTAL.asItem(), "Budding Rainbow Crystal");
			add(KoratioBlocks.RAINBOW_CRYSTAL_GLASS.asItem(), "Rainbow Crystal Glass");
			add(KoratioBlocks.RAINBOW_CRYSTAL_GLASS_PANE.asItem(), "Rainbow Crystal Glass Pane");

			add(KoratioBlocks.RAINBOW_ROSE.asItem(), "Rainbow Rose");
			add(KoratioBlocks.RAINBOW_ALLIUM.asItem(), "Rainbow Allium");
			add(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.asItem(), "Rainbow Lily of the Valley");
			add(KoratioBlocks.COOKIE_FLOWER.asItem(), "Cookie Flower");
			add(KoratioBlocks.ICE_ROSE.asItem(), "Ice Rose");
			add(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.asItem(), "White Sugarglass Flower");
			add(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.asItem(), "Blue Sugarglass Flower");
			add(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.asItem(), "Green Sugarglass Flower");
			add(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.asItem(), "Yellow Sugarglass Flower");
			add(KoratioBlocks.RED_SUGARGLASS_FLOWER.asItem(), "Red Sugarglass Flower");

			add(KoratioBlocks.ANCIENT_STONE.asItem(), "Ancient Stone");
			add(KoratioBlocks.ANCIENT_STONE_STAIRS.asItem(), "Ancient Stone Stairs");
			add(KoratioBlocks.ANCIENT_STONE_SLAB.asItem(), "Ancient Stone Slab");
			add(KoratioBlocks.ANCIENT_COBBLESTONE.asItem(), "Ancient Cobblestone");
			add(KoratioBlocks.ANCIENT_COBBLESTONE_STAIRS.asItem(), "Ancient Cobblestone Stairs");
			add(KoratioBlocks.ANCIENT_COBBLESTONE_SLAB.asItem(), "Ancient Cobblestone Slab");
			add(KoratioBlocks.ANCIENT_COBBLESTONE_WALL.asItem(), "Ancient Cobblestone Wall");
			add(KoratioBlocks.ANCIENT_STONE_BRICKS.asItem(), "Ancient Stone Bricks");
			add(KoratioBlocks.CRACKED_ANCIENT_STONE_BRICKS.asItem(), "Cracked Ancient Stone Bricks");
			add(KoratioBlocks.CHISELED_ANCIENT_STONE_BRICKS.asItem(), "Chiseled Ancient Stone Bricks");
			add(KoratioBlocks.ANCIENT_STONE_BRICK_STAIRS.asItem(), "Ancient Stone Brick Stairs");
			add(KoratioBlocks.ANCIENT_STONE_BRICK_SLAB.asItem(), "Ancient Stone Brick Slab");
			add(KoratioBlocks.ANCIENT_STONE_BRICK_WALL.asItem(), "Ancient Stone Brick Wall");
			add(KoratioBlocks.POLISHED_ANCIENT_STONE.asItem(), "Polished Ancient Stone");
			add(KoratioBlocks.POLISHED_ANCIENT_STONE_STAIRS.asItem(), "Polished Ancient Stone Stairs");
			add(KoratioBlocks.POLISHED_ANCIENT_STONE_SLAB.asItem(), "Polished Ancient Stone Slab");
			add(KoratioBlocks.POLISHED_ANCIENT_STONE_WALL.asItem(), "Polished Ancient Stone Wall");
			add(KoratioBlocks.ANCIENT_STONE_TILES.asItem(), "Ancient Stone Tiles");
			add(KoratioBlocks.ANCIENT_STONE_TILE_STAIRS.asItem(), "Ancient Stone Tile Stairs");
			add(KoratioBlocks.ANCIENT_STONE_TILE_SLAB.asItem(), "Ancient Stone Tile Slab");
			add(KoratioBlocks.ANCIENT_STONE_TILE_WALL.asItem(), "Ancient Stone Tile Wall");
			add(KoratioBlocks.ANCIENT_FURNACE.asItem(), "Ancient Furnace");
			add(KoratioBlocks.ANCIENT_TELEPORTER.asItem(), "Ancient Teleporter");

			add(KoratioBlocks.PANGO_PLANKS.asItem(), "Pango Planks");
			add(KoratioBlocks.PANGO_LOG.asItem(), "Pango Log");
			add(KoratioBlocks.STRIPPED_PANGO_LOG.asItem(), "Stripped Pango Log");
			add(KoratioBlocks.PANGO_WOOD.asItem(), "Pango Wood");
			add(KoratioBlocks.STRIPPED_PANGO_WOOD.asItem(), "Stripped Pango Wood");
			add(KoratioBlocks.PANGO_LEAVES.asItem(), "Pango Leaves");
			add(KoratioBlocks.PANGO_LEAF_PANE.asItem(), "Pango Leaf Pane");
			add(KoratioBlocks.PANGO_SLAB.asItem(), "Pango Slab");
			add(KoratioBlocks.PANGO_STAIRS.asItem(), "Pango Stairs");
			add(KoratioBlocks.PANGO_SAPLING.asItem(), "Pango Sapling");
			add(KoratioBlocks.PANGO_FENCE.asItem(), "Pango Fence");
			add(KoratioBlocks.PANGO_SIGN.asItem(), "Pango Sign");
			add(KoratioBlocks.PANGO_HANGING_SIGN.asItem(), "Pango Hanging Sign");
			add(KoratioBlocks.PANGO_CHEST.asItem(), "Pango Chest");
			add(KoratioBlocks.TRAPPED_PANGO_CHEST.asItem(), "Trapped Pango Chest");
			add(KoratioBlocks.PANGO_BOOKSHELF.asItem(), "Pango Bookshelf");
			add(KoratioBlocks.PANGO_BUTTON.asItem(), "Pango Button");
			add(KoratioBlocks.PANGO_PRESSURE_PLATE.asItem(), "Pango Pressure Plate");
			add(KoratioBlocks.PANGO_DOOR.asItem(), "Pango Door");
			add(KoratioBlocks.TALL_PANGO_DOOR.asItem(), "Tall Pango Door");
			add(KoratioBlocks.PANGO_TRAPDOOR.asItem(), "Pango Trapdoor");
			add(KoratioBlocks.PANGO_FENCE_GATE.asItem(), "Pango Fence Gate");
			
			add(KoratioBlocks.RUGONA_PLANKS.asItem(), "Rugona Planks");
			add(KoratioBlocks.RUGONA_LOG.asItem(), "Rugona Log");
			add(KoratioBlocks.STRIPPED_RUGONA_LOG.asItem(), "Stripped Rugona Log");
			add(KoratioBlocks.RUGONA_WOOD.asItem(), "Rugona Wood");
			add(KoratioBlocks.STRIPPED_RUGONA_WOOD.asItem(), "Stripped Rugona Wood");
			add(KoratioBlocks.RUGONA_LEAVES.asItem(), "Rugona Leaves");
			add(KoratioBlocks.RUGONA_LEAF_PANE.asItem(), "Rugona Leaf Pane");
			add(KoratioBlocks.RUGONA_SLAB.asItem(), "Rugona Slab");
			add(KoratioBlocks.RUGONA_STAIRS.asItem(), "Rugona Stairs");
			add(KoratioBlocks.RUGONA_SAPLING.asItem(), "Rugona Sapling");
			add(KoratioBlocks.RUGONA_FENCE.asItem(), "Rugona Fence");
			add(KoratioBlocks.RUGONA_SIGN.asItem(), "Rugona Sign");
			add(KoratioBlocks.RUGONA_HANGING_SIGN.asItem(), "Rugona Hanging Sign");
			add(KoratioBlocks.RUGONA_CHEST.asItem(), "Rugona Chest");
			add(KoratioBlocks.TRAPPED_RUGONA_CHEST.asItem(), "Trapped Rugona Chest");
			add(KoratioBlocks.RUGONA_BOOKSHELF.asItem(), "Rugona Bookshelf");
			add(KoratioBlocks.RUGONA_BUTTON.asItem(), "Rugona Button");
			add(KoratioBlocks.RUGONA_PRESSURE_PLATE.asItem(), "Rugona Pressure Plate");
			add(KoratioBlocks.RUGONA_DOOR.asItem(), "Rugona Door");
			add(KoratioBlocks.TALL_RUGONA_DOOR.asItem(), "Tall Rugona Door");
			add(KoratioBlocks.RUGONA_TRAPDOOR.asItem(), "Rugona Trapdoor");
			add(KoratioBlocks.RUGONA_FENCE_GATE.asItem(), "Rugona Fence Gate");

			add(KoratioBlocks.ELVEN_PLANKS.asItem(), "Elven Planks");
			add(KoratioBlocks.BLUE_ELVEN_PLANKS.asItem(), "Blue Elven Planks");
			add(KoratioBlocks.CYAN_ELVEN_PLANKS.asItem(), "Cyan Elven Planks");
			add(KoratioBlocks.GREEN_ELVEN_PLANKS.asItem(), "Green Elven Planks");
			add(KoratioBlocks.ELVEN_LOG.asItem(), "Elven Log");
			add(KoratioBlocks.STRIPPED_ELVEN_LOG.asItem(), "Stripped Elven Log");
			add(KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.asItem(), "Stripped Blue Elven Log");
			add(KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.asItem(), "Stripped Cyan Elven Log");
			add(KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.asItem(), "Stripped Green Elven Log");
			add(KoratioBlocks.ELVEN_WOOD.asItem(), "Elven Wood");
			add(KoratioBlocks.STRIPPED_ELVEN_WOOD.asItem(), "Stripped Elven Wood");
			add(KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.asItem(), "Stripped Blue Elven Wood");
			add(KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.asItem(), "Stripped Cyan Elven Wood");
			add(KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.asItem(), "Stripped Green Elven Wood");
			add(KoratioBlocks.ELVEN_LEAVES.asItem(), "Elven Leaves");
			add(KoratioBlocks.ELVEN_LEAF_PANE.asItem(), "Elven Leaf Pane");
			add(KoratioBlocks.ELVEN_SLAB.asItem(), "Elven Slab");
			add(KoratioBlocks.BLUE_ELVEN_SLAB.asItem(), "Blue Elven Slab");
			add(KoratioBlocks.CYAN_ELVEN_SLAB.asItem(), "Cyan Elven Slab");
			add(KoratioBlocks.GREEN_ELVEN_SLAB.asItem(), "Green Elven Slab");
			add(KoratioBlocks.ELVEN_STAIRS.asItem(), "Elven Stairs");
			add(KoratioBlocks.BLUE_ELVEN_STAIRS.asItem(), "Blue Elven Stairs");
			add(KoratioBlocks.CYAN_ELVEN_STAIRS.asItem(), "Cyan Elven Stairs");
			add(KoratioBlocks.GREEN_ELVEN_STAIRS.asItem(), "Green Elven Stairs");
			add(KoratioBlocks.ELVEN_SAPLING.asItem(), "Elven Sapling");
			add(KoratioBlocks.ELVEN_FENCE.asItem(), "Elven Fence");
			add(KoratioBlocks.BLUE_ELVEN_FENCE.asItem(), "Blue Elven Fence");
			add(KoratioBlocks.CYAN_ELVEN_FENCE.asItem(), "Cyan Elven Fence");
			add(KoratioBlocks.GREEN_ELVEN_FENCE.asItem(), "Green Elven Fence");
			add(KoratioBlocks.ELVEN_SIGN.asItem(), "Elven Sign");
			add(KoratioBlocks.BLUE_ELVEN_SIGN.asItem(), "Blue Elven Sign");
			add(KoratioBlocks.CYAN_ELVEN_SIGN.asItem(), "Cyan Elven Sign");
			add(KoratioBlocks.GREEN_ELVEN_SIGN.asItem(), "Green Elven Sign");
			add(KoratioBlocks.ELVEN_HANGING_SIGN.asItem(), "Elven Hanging Sign");
			add(KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.asItem(), "Blue Elven Hanging Sign");
			add(KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.asItem(), "Cyan Elven Hanging Sign");
			add(KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.asItem(), "Green Elven Hanging Sign");
			add(KoratioBlocks.ELVEN_CHEST.asItem(), "Elven Chest");
			add(KoratioBlocks.TRAPPED_ELVEN_CHEST.asItem(), "Trapped Elven Chest");
			add(KoratioBlocks.BLUE_ELVEN_CHEST.asItem(), "Blue Elven Chest");
			add(KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.asItem(), "Trapped Blue Elven Chest");
			add(KoratioBlocks.CYAN_ELVEN_CHEST.asItem(), "Cyan Elven Chest");
			add(KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.asItem(), "Trapped Cyan Elven Chest");
			add(KoratioBlocks.GREEN_ELVEN_CHEST.asItem(), "Green Elven Chest");
			add(KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.asItem(), "Trapped Green Elven Chest");
			add(KoratioBlocks.ELVEN_BOOKSHELF.asItem(), "Elven Bookshelf");
			add(KoratioBlocks.BLUE_ELVEN_BOOKSHELF.asItem(), "Blue Elven Bookshelf");
			add(KoratioBlocks.CYAN_ELVEN_BOOKSHELF.asItem(), "Cyan Elven Bookshelf");
			add(KoratioBlocks.GREEN_ELVEN_BOOKSHELF.asItem(), "Green Elven Bookshelf");
			add(KoratioBlocks.ELVEN_BUTTON.asItem(), "Elven Button");
			add(KoratioBlocks.BLUE_ELVEN_BUTTON.asItem(), "Blue Elven Button");
			add(KoratioBlocks.CYAN_ELVEN_BUTTON.asItem(), "Cyan Elven Button");
			add(KoratioBlocks.GREEN_ELVEN_BUTTON.asItem(), "Green Elven Button");
			add(KoratioBlocks.ELVEN_PRESSURE_PLATE.asItem(), "Elven Pressure Plate");
			add(KoratioBlocks.BLUE_ELVEN_PRESSURE_PLATE.asItem(), "Blue Elven Pressure Plate");
			add(KoratioBlocks.CYAN_ELVEN_PRESSURE_PLATE.asItem(), "Cyan Elven Pressure Plate");
			add(KoratioBlocks.GREEN_ELVEN_PRESSURE_PLATE.asItem(), "Green Elven Pressure Plate");
			add(KoratioBlocks.ELVEN_DOOR.asItem(), "Elven Door");
			add(KoratioBlocks.TALL_ELVEN_DOOR.asItem(), "Tall Elven Door");
			add(KoratioBlocks.BLUE_ELVEN_DOOR.asItem(), "Blue Elven Door");
			add(KoratioBlocks.TALL_BLUE_ELVEN_DOOR.asItem(), "Tall Blue Elven Door");
			add(KoratioBlocks.CYAN_ELVEN_DOOR.asItem(), "Cyan Elven Door");
			add(KoratioBlocks.TALL_CYAN_ELVEN_DOOR.asItem(), "Tall Cyan Elven Door");
			add(KoratioBlocks.GREEN_ELVEN_DOOR.asItem(), "Green Elven Door");
			add(KoratioBlocks.TALL_GREEN_ELVEN_DOOR.asItem(), "Tall Green Elven Door");
			add(KoratioBlocks.ELVEN_TRAPDOOR.asItem(), "Elven Trapdoor");
			add(KoratioBlocks.BLUE_ELVEN_TRAPDOOR.asItem(), "Blue Elven Trapdoor");
			add(KoratioBlocks.CYAN_ELVEN_TRAPDOOR.asItem(), "Cyan Elven Trapdoor");
			add(KoratioBlocks.GREEN_ELVEN_TRAPDOOR.asItem(), "Green Elven Trapdoor");
			add(KoratioBlocks.ELVEN_FENCE_GATE.asItem(), "Elven Fence Gate");
			add(KoratioBlocks.BLUE_ELVEN_FENCE_GATE.asItem(), "Blue Elven Fence Gate");
			add(KoratioBlocks.CYAN_ELVEN_FENCE_GATE.asItem(), "Cyan Elven Fence Gate");
			add(KoratioBlocks.GREEN_ELVEN_FENCE_GATE.asItem(), "Green Elven Fence Gate");

			add(KoratioBlocks.CANDY_PLANKS.asItem(), "Candy Planks");
			add(KoratioBlocks.CANDY_LOG.asItem(), "Candy Log");
			add(KoratioBlocks.STRIPPED_CANDY_LOG.asItem(), "Stripped Candy Log");
			add(KoratioBlocks.CANDY_WOOD.asItem(), "Candy Wood");
			add(KoratioBlocks.STRIPPED_CANDY_WOOD.asItem(), "Stripped Candy Wood");
			add(KoratioBlocks.PINK_COTTON_CANDY_LEAVES.asItem(), "Pink Cotton Candy Leaves");
			add(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAVES.asItem(), "Light Blue Cotton Candy Leaves");
			add(KoratioBlocks.LIME_COTTON_CANDY_LEAVES.asItem(), "Lime Cotton Candy Leaves");
			add(KoratioBlocks.YELLOW_COTTON_CANDY_LEAVES.asItem(), "Yellow Cotton Candy Leaves");
			add(KoratioBlocks.PINK_COTTON_CANDY_LEAF_PANE.asItem(), "Pink Cotton Candy Leaf Pane");
			add(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAF_PANE.asItem(), "Light Blue Cotton Candy Leaf Pane");
			add(KoratioBlocks.LIME_COTTON_CANDY_LEAF_PANE.asItem(), "Lime Cotton Candy Leaf Pane");
			add(KoratioBlocks.YELLOW_COTTON_CANDY_LEAF_PANE.asItem(), "Yellow Cotton Candy Leaf Pane");
			add(KoratioBlocks.CANDY_SLAB.asItem(), "Candy Slab");
			add(KoratioBlocks.CANDY_STAIRS.asItem(), "Candy Stairs");
			add(KoratioBlocks.CANDY_SAPLING.asItem(), "Candy Sapling");
			add(KoratioBlocks.CANDY_FENCE.asItem(), "Candy Fence");
			add(KoratioBlocks.CANDY_SIGN.asItem(), "Candy Sign");
			add(KoratioBlocks.CANDY_HANGING_SIGN.asItem(), "Candy Hanging Sign");
			add(KoratioBlocks.CANDY_CHEST.asItem(), "Candy Chest");
			add(KoratioBlocks.TRAPPED_CANDY_CHEST.asItem(), "Trapped Candy Chest");
			add(KoratioBlocks.CANDY_BOOKSHELF.asItem(), "Candy Bookshelf");
			add(KoratioBlocks.CANDY_BUTTON.asItem(), "Candy Button");
			add(KoratioBlocks.CANDY_PRESSURE_PLATE.asItem(), "Candy Pressure Plate");
			add(KoratioBlocks.CANDY_DOOR.asItem(), "Candy Door");
			//add(KoratioBlocks.TALL_CANDY_DOOR.asItem(), "Tall Candy Door");
			add(KoratioBlocks.CANDY_TRAPDOOR.asItem(), "Candy Trapdoor");
			add(KoratioBlocks.CANDY_FENCE_GATE.asItem(), "Candy Fence Gate");
			
			add(KoratioBlocks.VARESO_PLANKS.asItem(), "Vareso Planks");
			add(KoratioBlocks.VARESO_LOG.asItem(), "Vareso Log");
			add(KoratioBlocks.STRIPPED_VARESO_LOG.asItem(), "Stripped Vareso Log");
			add(KoratioBlocks.VARESO_WOOD.asItem(), "Vareso Wood");
			add(KoratioBlocks.STRIPPED_VARESO_WOOD.asItem(), "Stripped Vareso Wood");
			add(KoratioBlocks.VARESO_LEAVES.asItem(), "Vareso Leaves");
			add(KoratioBlocks.VARESO_LEAF_PANE.asItem(), "Vareso Leaf Pane");
			add(KoratioBlocks.VARESO_SLAB.asItem(), "Vareso Slab");
			add(KoratioBlocks.VARESO_STAIRS.asItem(), "Vareso Stairs");
			add(KoratioBlocks.VARESO_SAPLING.asItem(), "Vareso Sapling");
			add(KoratioBlocks.VARESO_FENCE.asItem(), "Vareso Fence");
			add(KoratioBlocks.VARESO_SIGN.asItem(), "Vareso Sign");
			add(KoratioBlocks.VARESO_HANGING_SIGN.asItem(), "Vareso Hanging Sign");
			add(KoratioBlocks.VARESO_CHEST.asItem(), "Vareso Chest");
			add(KoratioBlocks.TRAPPED_VARESO_CHEST.asItem(), "Trapped Vareso Chest");
			add(KoratioBlocks.VARESO_BOOKSHELF.asItem(), "Vareso Bookshelf");
			add(KoratioBlocks.VARESO_BUTTON.asItem(), "Vareso Button");
			add(KoratioBlocks.VARESO_PRESSURE_PLATE.asItem(), "Vareso Pressure Plate");
			add(KoratioBlocks.VARESO_DOOR.asItem(), "Vareso Door");
			add(KoratioBlocks.TALL_VARESO_DOOR.asItem(), "Tall Vareso Door");
			add(KoratioBlocks.VARESO_TRAPDOOR.asItem(), "Vareso Trapdoor");
			add(KoratioBlocks.VARESO_FENCE_GATE.asItem(), "Vareso Fence Gate");
			
			add(KoratioBlocks.PURPLE_MUSHROOM.asItem(), "Purple Mushroom");
			add(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.asItem(), "Purple Mushroom Block");
			add(KoratioBlocks.GREEN_MUSHROOM.asItem(), "Green Mushroom");
			add(KoratioBlocks.GREEN_MUSHROOM_BLOCK.asItem(), "Green Mushroom Block");

			add(KoratioBlocks.GILDED_VINES.asItem(), "Gilded Vines");

			add(KoratioBlocks.RAINBOW_GEM_BLOCK.asItem(), "Block of Rainbow Gem");
			add(KoratioBlocks.RUBY_BLOCK.asItem(), "Block of Ruby");
			add(KoratioBlocks.SAPPHIRE_BLOCK.asItem(), "Block of Sapphire");
			add(KoratioBlocks.TOPAZ_BLOCK.asItem(), "Block of Topaz");
			add(KoratioBlocks.ONYX_BLOCK.asItem(), "Block of Onyx");

			add(KoratioBlocks.WHITE_SUGAR_BLOCK.asItem(), "Block of White Sugar");
			add(KoratioBlocks.LIGHT_GRAY_SUGAR_BLOCK.asItem(), "Block of Light Gray Sugar");
			add(KoratioBlocks.GRAY_SUGAR_BLOCK.asItem(), "Block of Gray Sugar");
			add(KoratioBlocks.BLACK_SUGAR_BLOCK.asItem(), "Block of Black Sugar");
			add(KoratioBlocks.BROWN_SUGAR_BLOCK.asItem(), "Block of Brown Sugar");
			add(KoratioBlocks.RED_SUGAR_BLOCK.asItem(), "Block of Red Sugar");
			add(KoratioBlocks.ORANGE_SUGAR_BLOCK.asItem(), "Block of Orange Sugar");
			add(KoratioBlocks.YELLOW_SUGAR_BLOCK.asItem(), "Block of Yellow Sugar");
			add(KoratioBlocks.LIME_SUGAR_BLOCK.asItem(), "Block of Lime Sugar");
			add(KoratioBlocks.GREEN_SUGAR_BLOCK.asItem(), "Block of Green Sugar");
			add(KoratioBlocks.CYAN_SUGAR_BLOCK.asItem(), "Block of Cyan Sugar");
			add(KoratioBlocks.LIGHT_BLUE_SUGAR_BLOCK.asItem(), "Block of Light Blue Sugar");
			add(KoratioBlocks.BLUE_SUGAR_BLOCK.asItem(), "Block of Blue Sugar");
			add(KoratioBlocks.PURPLE_SUGAR_BLOCK.asItem(), "Block of Purple Sugar");
			add(KoratioBlocks.MAGENTA_SUGAR_BLOCK.asItem(), "Block of Magenta Sugar");
			add(KoratioBlocks.PINK_SUGAR_BLOCK.asItem(), "Block of Pink Sugar");

			add(KoratioBlocks.STICKY_WHITE_SUGAR_BLOCK.asItem(), "Block of Sticky White Sugar");
			add(KoratioBlocks.STICKY_LIGHT_GRAY_SUGAR_BLOCK.asItem(), "Block of Sticky Light Gray Sugar");
			add(KoratioBlocks.STICKY_GRAY_SUGAR_BLOCK.asItem(), "Block of Sticky Gray Sugar");
			add(KoratioBlocks.STICKY_BLACK_SUGAR_BLOCK.asItem(), "Block of Sticky Black Sugar");
			add(KoratioBlocks.STICKY_BROWN_SUGAR_BLOCK.asItem(), "Block of Sticky Brown Sugar");
			add(KoratioBlocks.STICKY_RED_SUGAR_BLOCK.asItem(), "Block of Sticky Red Sugar");
			add(KoratioBlocks.STICKY_ORANGE_SUGAR_BLOCK.asItem(), "Block of Sticky Orange Sugar");
			add(KoratioBlocks.STICKY_YELLOW_SUGAR_BLOCK.asItem(), "Block of Sticky Yellow Sugar");
			add(KoratioBlocks.STICKY_LIME_SUGAR_BLOCK.asItem(), "Block of Sticky Lime Sugar");
			add(KoratioBlocks.STICKY_GREEN_SUGAR_BLOCK.asItem(), "Block of Sticky Green Sugar");
			add(KoratioBlocks.STICKY_CYAN_SUGAR_BLOCK.asItem(), "Block of Sticky Cyan Sugar");
			add(KoratioBlocks.STICKY_LIGHT_BLUE_SUGAR_BLOCK.asItem(), "Block of Sticky Light Blue Sugar");
			add(KoratioBlocks.STICKY_BLUE_SUGAR_BLOCK.asItem(), "Block of Sticky Blue Sugar");
			add(KoratioBlocks.STICKY_PURPLE_SUGAR_BLOCK.asItem(), "Block of Sticky Purple Sugar");
			add(KoratioBlocks.STICKY_MAGENTA_SUGAR_BLOCK.asItem(), "Block of Sticky Magenta Sugar");
			add(KoratioBlocks.STICKY_PINK_SUGAR_BLOCK.asItem(), "Block of Sticky Pink Sugar");

			add(KoratioBlocks.WHITE_ICING_BLOCK.asItem(), "Block of White Icing");
			add(KoratioBlocks.LIGHT_GRAY_ICING_BLOCK.asItem(), "Block of Light Gray Icing");
			add(KoratioBlocks.GRAY_ICING_BLOCK.asItem(), "Block of Gray Icing");
			add(KoratioBlocks.BLACK_ICING_BLOCK.asItem(), "Block of Black Icing");
			add(KoratioBlocks.BROWN_ICING_BLOCK.asItem(), "Block of Brown Icing");
			add(KoratioBlocks.RED_ICING_BLOCK.asItem(), "Block of Red Icing");
			add(KoratioBlocks.ORANGE_ICING_BLOCK.asItem(), "Block of Orange Icing");
			add(KoratioBlocks.YELLOW_ICING_BLOCK.asItem(), "Block of Yellow Icing");
			add(KoratioBlocks.LIME_ICING_BLOCK.asItem(), "Block of Lime Icing");
			add(KoratioBlocks.GREEN_ICING_BLOCK.asItem(), "Block of Green Icing");
			add(KoratioBlocks.CYAN_ICING_BLOCK.asItem(), "Block of Cyan Icing");
			add(KoratioBlocks.LIGHT_BLUE_ICING_BLOCK.asItem(), "Block of Light Blue Icing");
			add(KoratioBlocks.BLUE_ICING_BLOCK.asItem(), "Block of Blue Icing");
			add(KoratioBlocks.PURPLE_ICING_BLOCK.asItem(), "Block of Purple Icing");
			add(KoratioBlocks.MAGENTA_ICING_BLOCK.asItem(), "Block of Magenta Icing");
			add(KoratioBlocks.PINK_ICING_BLOCK.asItem(), "Block of Pink Icing");

			add(KoratioBlocks.WHITE_CANDY_BLOCK.asItem(), "White Candy Block");
			add(KoratioBlocks.LIGHT_GRAY_CANDY_BLOCK.asItem(), "Light Gray Candy Block");
			add(KoratioBlocks.GRAY_CANDY_BLOCK.asItem(), "Gray Candy Block");
			add(KoratioBlocks.BLACK_CANDY_BLOCK.asItem(), "Black Candy Block");
			add(KoratioBlocks.BROWN_CANDY_BLOCK.asItem(), "Brown Candy Block");
			add(KoratioBlocks.RED_CANDY_BLOCK.asItem(), "Red Candy Block");
			add(KoratioBlocks.ORANGE_CANDY_BLOCK.asItem(), "Orange Candy Block");
			add(KoratioBlocks.YELLOW_CANDY_BLOCK.asItem(), "Yellow Candy Block");
			add(KoratioBlocks.LIME_CANDY_BLOCK.asItem(), "Lime Candy Block");
			add(KoratioBlocks.GREEN_CANDY_BLOCK.asItem(), "Green Candy Block");
			add(KoratioBlocks.CYAN_CANDY_BLOCK.asItem(), "Cyan Candy Block");
			add(KoratioBlocks.LIGHT_BLUE_CANDY_BLOCK.asItem(), "Light Blue Candy Block");
			add(KoratioBlocks.BLUE_CANDY_BLOCK.asItem(), "Blue Candy Block");
			add(KoratioBlocks.PURPLE_CANDY_BLOCK.asItem(), "Purple Candy Block");
			add(KoratioBlocks.MAGENTA_CANDY_BLOCK.asItem(), "Magenta Candy Block");
			add(KoratioBlocks.PINK_CANDY_BLOCK.asItem(), "Pink Candy Block");

			add(KoratioBlocks.RAW_GINGERBREAD_BLOCK.asItem(), "Raw Gingerbread Block");
			add(KoratioBlocks.RAW_GINGERBREAD_STAIRS.asItem(), "Raw Gingerbread Stairs");
			add(KoratioBlocks.RAW_GINGERBREAD_SLAB.asItem(), "Raw Gingerbread Slab");
			add(KoratioBlocks.RAW_GINGERBREAD_BRICKS.asItem(), "Raw Gingerbread Bricks");
			add(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.asItem(), "Raw Gingerbread Brick Stairs");
			add(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.asItem(), "Raw Gingerbread Brick Slab");
			add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.asItem(), "Raw Large Gingerbread Bricks");
			add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.asItem(), "Raw Large Gingerbread Brick Stairs");
			add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.asItem(), "Raw Large Gingerbread Brick Slab");
			add(KoratioBlocks.RAW_GINGERBREAD_WALL.asItem(), "Raw Gingerbread Wall");
			add(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.asItem(), "Raw Gingerbread Brick Wall");
			add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.asItem(), "Raw Large Gingerbread Brick Wall");
			add(KoratioBlocks.GINGERBREAD_BLOCK.asItem(), "Gingerbread Block");
			add(KoratioBlocks.GINGERBREAD_STAIRS.asItem(), "Gingerbread Stairs");
			add(KoratioBlocks.GINGERBREAD_SLAB.asItem(), "Gingerbread Slab");
			add(KoratioBlocks.GINGERBREAD_BRICKS.asItem(), "Gingerbread Bricks");
			add(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.asItem(), "Gingerbread Brick Stairs");
			add(KoratioBlocks.GINGERBREAD_BRICK_SLAB.asItem(), "Gingerbread Brick Slab");
			add(KoratioBlocks.LARGE_GINGERBREAD_BRICKS.asItem(), "Large Gingerbread Bricks");
			add(KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.asItem(), "Large Gingerbread Brick Stairs");
			add(KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.asItem(), "Large Gingerbread Brick Slab");
			add(KoratioBlocks.GINGERBREAD_WALL.asItem(), "Gingerbread Wall");
			add(KoratioBlocks.GINGERBREAD_BRICK_WALL.asItem(), "Gingerbread Brick Wall");
			add(KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.asItem(), "Large Gingerbread Brick Wall");

			add(KoratioBlocks.MARSHMALLOW_BLOCK.asItem(), "Marshmallow Block");

			add(KoratioBlocks.WHITE_LEVITATING_WOOL.asItem(), "White Levitating Wool");
			add(KoratioBlocks.ORANGE_LEVITATING_WOOL.asItem(), "Orange Levitating Wool");
			add(KoratioBlocks.MAGENTA_LEVITATING_WOOL.asItem(), "Magenta Levitating Wool");
			add(KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL.asItem(), "Light Blue Levitating Wool");
			add(KoratioBlocks.YELLOW_LEVITATING_WOOL.asItem(), "Yellow Levitating Wool");
			add(KoratioBlocks.LIME_LEVITATING_WOOL.asItem(), "Lime Levitating Wool");
			add(KoratioBlocks.PINK_LEVITATING_WOOL.asItem(), "Pink Levitating Wool");
			add(KoratioBlocks.GRAY_LEVITATING_WOOL.asItem(), "Gray Levitating Wool");
			add(KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL.asItem(), "Light Gray Levitating Wool");
			add(KoratioBlocks.CYAN_LEVITATING_WOOL.asItem(), "Cyan Levitating Wool");
			add(KoratioBlocks.PURPLE_LEVITATING_WOOL.asItem(), "Purple Levitating Wool");
			add(KoratioBlocks.BLUE_LEVITATING_WOOL.asItem(), "Blue Levitating Wool");
			add(KoratioBlocks.BROWN_LEVITATING_WOOL.asItem(), "Brown Levitating Wool");
			add(KoratioBlocks.GREEN_LEVITATING_WOOL.asItem(), "Green Levitating Wool");
			add(KoratioBlocks.RED_LEVITATING_WOOL.asItem(), "Red Levitating Wool");
			add(KoratioBlocks.BLACK_LEVITATING_WOOL.asItem(), "Black Levitating Wool");

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

			add(KoratioEntityType.PANGO_BOAT.get(), "Boat");
			add(KoratioEntityType.PANGO_CHEST_BOAT.get(), "Chest Boat");
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