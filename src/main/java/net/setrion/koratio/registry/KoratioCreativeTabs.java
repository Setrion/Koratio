package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.scroll.Scroll;
import net.setrion.koratio.scroll.ScrollUtils;
import net.setrion.koratio.world.item.CandyItem;
import net.setrion.koratio.world.item.PipingBagItem;
import net.setrion.koratio.world.level.block.entity.GlazedBlockEntity;

import java.util.Collection;
import java.util.stream.Collectors;

public class KoratioCreativeTabs {
	
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Koratio.MOD_ID);
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = TABS.register("main", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 1)
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS).icon(() -> new ItemStack(KoratioItems.RAINBOW_GEM.get()))
			.title(Component.translatable("itemGroup.koratio.main"))
			.displayItems((parameters, output) -> {
				output.accept(KoratioBlocks.DECRYPTING_TABLE.get());
				output.accept(KoratioBlocks.WOODCUTTER.get());
				output.accept(KoratioBlocks.CANDY_SHAPER.get());
				output.accept(KoratioItems.PIPING_BAG.get());
				createPipingBags(output);
				output.accept(KoratioItems.WOODEN_ICING_SPATULA.get());
				output.accept(KoratioItems.STONE_ICING_SPATULA.get());
				output.accept(KoratioItems.GOLDEN_ICING_SPATULA.get());
				output.accept(KoratioItems.IRON_ICING_SPATULA.get());
				output.accept(KoratioItems.DIAMOND_ICING_SPATULA.get());
				output.accept(KoratioItems.NETHERITE_ICING_SPATULA.get());
				output.accept(KoratioItems.DECRYPTING_BOOK.get());
				output.accept(KoratioItems.BETTER_DECRYPTING_BOOK.get());
				output.accept(KoratioItems.FANTASTIC_DECRYPTING_BOOK.get());
				output.accept(KoratioBlocks.FLIPPED_FARMLAND.get());
				output.accept(KoratioItems.RUBY.get());
				output.accept(KoratioBlocks.RUBY_BLOCK.get());
				output.accept(KoratioBlocks.RUBY_ORE.get());
				output.accept(KoratioBlocks.DEEPSLATE_RUBY_ORE.get());
				output.accept(KoratioItems.SAPPHIRE.get());
				output.accept(KoratioBlocks.SAPPHIRE_BLOCK.get());
				output.accept(KoratioBlocks.SAPPHIRE_ORE.get());
				output.accept(KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
				output.accept(KoratioItems.TOPAZ.get());
				output.accept(KoratioBlocks.TOPAZ_BLOCK.get());
				output.accept(KoratioBlocks.TOPAZ_ORE.get());
				output.accept(KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get());
				output.accept(KoratioItems.ONYX.get());
				output.accept(KoratioBlocks.ONYX_BLOCK.get());
				output.accept(KoratioBlocks.ONYX_ORE.get());
				output.accept(KoratioBlocks.DEEPSLATE_ONYX_ORE.get());

				output.accept(KoratioBlocks.SKELETON_REMAINS.get());
				output.accept(KoratioBlocks.WITHER_SKELETON_REMAINS.get());
				output.accept(KoratioBlocks.STRAY_REMAINS.get());
				output.accept(KoratioBlocks.BOGGED_REMAINS.get());
				output.accept(KoratioBlocks.DEMONIC_SKELETON_REMAINS.get());

				output.accept(KoratioBlocks.ZOMBIE_VILLAGER_REMAINS.get());

				output.accept(KoratioBlocks.ZOMBIE_REMAINS.get());
				output.accept(KoratioBlocks.HUSK_REMAINS.get());
				output.accept(KoratioBlocks.DROWNED_REMAINS.get());
				output.accept(KoratioBlocks.DEMONIC_ZOMBIE_REMAINS.get());

				output.accept(KoratioBlocks.PHANTOM_REMAINS.get());
				output.accept(KoratioBlocks.ZOMBIFIED_PIGLIN_REMAINS.get());

				output.accept(KoratioBlocks.OAK_LEAF_PANE.get());
				output.accept(KoratioBlocks.SPRUCE_LEAF_PANE.get());
				output.accept(KoratioBlocks.BIRCH_LEAF_PANE.get());
				output.accept(KoratioBlocks.JUNGLE_LEAF_PANE.get());
				output.accept(KoratioBlocks.ACACIA_LEAF_PANE.get());
				output.accept(KoratioBlocks.DARK_OAK_LEAF_PANE.get());
				output.accept(KoratioBlocks.MANGROVE_LEAF_PANE.get());
				output.accept(KoratioBlocks.AZALEA_LEAF_PANE.get());
				output.accept(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.get());
				output.accept(KoratioBlocks.CHERRY_LEAF_PANE.get());

				output.accept(KoratioBlocks.TALL_OAK_DOOR.get());
				output.accept(KoratioBlocks.TALL_SPRUCE_DOOR.get());
				output.accept(KoratioBlocks.TALL_BIRCH_DOOR.get());
				output.accept(KoratioBlocks.TALL_JUNGLE_DOOR.get());
				output.accept(KoratioBlocks.TALL_ACACIA_DOOR.get());
				output.accept(KoratioBlocks.TALL_DARK_OAK_DOOR.get());
				output.accept(KoratioBlocks.TALL_MANGROVE_DOOR.get());
				output.accept(KoratioBlocks.TALL_CHERRY_DOOR.get());
				output.accept(KoratioBlocks.TALL_BAMBOO_DOOR.get());
				output.accept(KoratioBlocks.TALL_CRIMSON_DOOR.get());
				output.accept(KoratioBlocks.TALL_WARPED_DOOR.get());
				output.accept(KoratioBlocks.TALL_IRON_DOOR.get());
				output.accept(KoratioBlocks.TALL_COPPER_DOOR.get());
				output.accept(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get());
				output.accept(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get());
				output.accept(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get());
				output.accept(KoratioBlocks.TALL_WAXED_COPPER_DOOR.get());
				output.accept(KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get());
				output.accept(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get());
				output.accept(KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get());

				createScrolls(output);
				createSpawnEggsAlphabetical(output);
			}).build());
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FANTASIA = TABS.register("fantasia", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 1)
			.withTabsBefore(MAIN.getKey()).icon(() -> new ItemStack(KoratioBlocks.MINIATURE_FANTASIA_PORTAL.get()))
			.title(Component.translatable("itemGroup.koratio.fantasia"))
			.displayItems((parameters, output) -> {

				output.accept(KoratioItems.RAINBOW_GEM.get());
				output.accept(KoratioBlocks.RAINBOW_GEM_BLOCK.get());
				output.accept(KoratioItems.RAINBOW_TORCH.get());
				output.accept(KoratioBlocks.RAINBOW_LANTERN.get());
				output.accept(KoratioBlocks.RAINBOW_CAMPFIRE.get());
				output.accept(KoratioBlocks.RAINBOW_CANDLE.get());
				output.accept(KoratioItems.RAINBOW_CRYSTAL_SHARD.get());
				output.accept(KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.get());
				output.accept(KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD.get());
				output.accept(KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD.get());
				output.accept(KoratioBlocks.RAINBOW_CRYSTAL_CLUSTER.get());
				output.accept(KoratioBlocks.RAINBOW_CRYSTAL_BLOCK.get());
				output.accept(KoratioBlocks.BUDDING_RAINBOW_CRYSTAL.get());
				output.accept(KoratioBlocks.RAINBOW_CRYSTAL_GLASS.get());
				output.accept(KoratioBlocks.RAINBOW_CRYSTAL_GLASS_PANE.get());

				//Environment
				output.accept(KoratioBlocks.ANCIENT_STONE.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_STAIRS.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_SLAB.get());
				output.accept(KoratioBlocks.ANCIENT_COBBLESTONE.get());
				output.accept(KoratioBlocks.ANCIENT_COBBLESTONE_STAIRS.get());
				output.accept(KoratioBlocks.ANCIENT_COBBLESTONE_SLAB.get());
				output.accept(KoratioBlocks.ANCIENT_COBBLESTONE_WALL.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_BRICKS.get());
				output.accept(KoratioBlocks.CRACKED_ANCIENT_STONE_BRICKS.get());
				output.accept(KoratioBlocks.CHISELED_ANCIENT_STONE_BRICKS.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_BRICK_STAIRS.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_BRICK_SLAB.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_BRICK_WALL.get());
				output.accept(KoratioBlocks.POLISHED_ANCIENT_STONE.get());
				output.accept(KoratioBlocks.POLISHED_ANCIENT_STONE_STAIRS.get());
				output.accept(KoratioBlocks.POLISHED_ANCIENT_STONE_SLAB.get());
				output.accept(KoratioBlocks.POLISHED_ANCIENT_STONE_WALL.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_TILES.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_TILE_STAIRS.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_TILE_SLAB.get());
				output.accept(KoratioBlocks.ANCIENT_STONE_TILE_WALL.get());

				output.accept(KoratioBlocks.ANCIENT_FURNACE.get());
				output.accept(KoratioBlocks.ANCIENT_TELEPORTER.get());

				//Minerals
				
				output.accept(KoratioItems.LIGHT_GRAY_SUGAR.get());
				output.accept(KoratioItems.GRAY_SUGAR.get());
				output.accept(KoratioItems.BLACK_SUGAR.get());
				output.accept(KoratioItems.BROWN_SUGAR.get());
				output.accept(KoratioItems.RED_SUGAR.get());
				output.accept(KoratioItems.ORANGE_SUGAR.get());
				output.accept(KoratioItems.YELLOW_SUGAR.get());
				output.accept(KoratioItems.LIME_SUGAR.get());
				output.accept(KoratioItems.GREEN_SUGAR.get());
				output.accept(KoratioItems.CYAN_SUGAR.get());
				output.accept(KoratioItems.LIGHT_BLUE_SUGAR.get());
				output.accept(KoratioItems.BLUE_SUGAR.get());
				output.accept(KoratioItems.PURPLE_SUGAR.get());
				output.accept(KoratioItems.MAGENTA_SUGAR.get());
				output.accept(KoratioItems.PINK_SUGAR.get());

				output.accept(KoratioItems.STICKY_WHITE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_LIGHT_GRAY_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_GRAY_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_BLACK_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_BROWN_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_RED_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_ORANGE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_YELLOW_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_LIME_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_GREEN_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_CYAN_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_LIGHT_BLUE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_BLUE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_PURPLE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_MAGENTA_SUGAR_BUCKET.get());
				output.accept(KoratioItems.STICKY_PINK_SUGAR_BUCKET.get());

				output.accept(KoratioItems.MOLTEN_WHITE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_LIGHT_GRAY_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_GRAY_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_BLACK_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_BROWN_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_RED_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_ORANGE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_LIME_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_CYAN_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_LIGHT_BLUE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_PURPLE_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_MAGENTA_SUGAR_BUCKET.get());
				output.accept(KoratioItems.MOLTEN_PINK_SUGAR_BUCKET.get());

				output.accept(KoratioBlocks.COOKIE_BLOCK.get());
				output.accept(KoratioBlocks.COOKIE_BLOCK_STAIRS.get());
				output.accept(KoratioBlocks.COOKIE_BLOCK_SLAB.get());

				output.accept(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get());
				output.accept(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get());
				output.accept(KoratioBlocks.RAW_GINGERBREAD_SLAB.get());
				output.accept(KoratioBlocks.GINGERBREAD_BLOCK.get());
				output.accept(KoratioBlocks.GINGERBREAD_STAIRS.get());
				output.accept(KoratioBlocks.GINGERBREAD_SLAB.get());
				output.accept(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get());
				output.accept(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get());
				output.accept(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get());
				output.accept(KoratioBlocks.GINGERBREAD_BRICKS.get());
				output.accept(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get());
				output.accept(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get());
				output.accept(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get());
				output.accept(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.get());
				output.accept(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.get());
				output.accept(KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get());
				output.accept(KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.get());
				output.accept(KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.get());
				output.accept(KoratioBlocks.RAW_GINGERBREAD_WALL.get());
				output.accept(KoratioBlocks.GINGERBREAD_WALL.get());
				output.accept(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.get());
				output.accept(KoratioBlocks.GINGERBREAD_BRICK_WALL.get());
				output.accept(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.get());
				output.accept(KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.get());
				output.accept(KoratioBlocks.MARSHMALLOW_BLOCK.get());

				output.accept(KoratioBlocks.WHITE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.LIGHT_GRAY_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.GRAY_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.BLACK_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.BROWN_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.RED_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.ORANGE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.YELLOW_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.LIME_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.GREEN_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.CYAN_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.LIGHT_BLUE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.BLUE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.PURPLE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.MAGENTA_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.PINK_SUGAR_BLOCK.get());

				output.accept(KoratioBlocks.STICKY_WHITE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_LIGHT_GRAY_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_GRAY_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_BLACK_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_BROWN_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_RED_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_ORANGE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_YELLOW_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_LIME_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_GREEN_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_CYAN_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_LIGHT_BLUE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_BLUE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_PURPLE_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_MAGENTA_SUGAR_BLOCK.get());
				output.accept(KoratioBlocks.STICKY_PINK_SUGAR_BLOCK.get());

				output.accept(KoratioBlocks.WHITE_ICING_BLOCK.get());
				output.accept(KoratioBlocks.LIGHT_GRAY_ICING_BLOCK.get());
				output.accept(KoratioBlocks.GRAY_ICING_BLOCK.get());
				output.accept(KoratioBlocks.BLACK_ICING_BLOCK.get());
				output.accept(KoratioBlocks.BROWN_ICING_BLOCK.get());
				output.accept(KoratioBlocks.RED_ICING_BLOCK.get());
				output.accept(KoratioBlocks.ORANGE_ICING_BLOCK.get());
				output.accept(KoratioBlocks.YELLOW_ICING_BLOCK.get());
				output.accept(KoratioBlocks.LIME_ICING_BLOCK.get());
				output.accept(KoratioBlocks.GREEN_ICING_BLOCK.get());
				output.accept(KoratioBlocks.CYAN_ICING_BLOCK.get());
				output.accept(KoratioBlocks.LIGHT_BLUE_ICING_BLOCK.get());
				output.accept(KoratioBlocks.BLUE_ICING_BLOCK.get());
				output.accept(KoratioBlocks.PURPLE_ICING_BLOCK.get());
				output.accept(KoratioBlocks.MAGENTA_ICING_BLOCK.get());
				output.accept(KoratioBlocks.PINK_ICING_BLOCK.get());

				output.accept(KoratioBlocks.WHITE_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.LIGHT_GRAY_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.GRAY_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.BLACK_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.BROWN_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.RED_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.ORANGE_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.YELLOW_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.LIME_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.GREEN_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.CYAN_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.LIGHT_BLUE_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.BLUE_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.PURPLE_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.MAGENTA_CANDY_BLOCK.get());
				output.accept(KoratioBlocks.PINK_CANDY_BLOCK.get());

				output.accept(KoratioBlocks.WHITE_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.GRAY_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.BLACK_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.BROWN_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.RED_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.ORANGE_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.YELLOW_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.LIME_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.GREEN_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.CYAN_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.BLUE_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.PURPLE_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.MAGENTA_LEVITATING_WOOL.get());
				output.accept(KoratioBlocks.PINK_LEVITATING_WOOL.get());

				//Food
				output.accept(KoratioItems.RAW_PANGO.get());
				output.accept(KoratioItems.CRACKED_PANGO.get());
				output.accept(KoratioItems.FLUFFER.get());
				output.accept(KoratioItems.COOKED_FLUFFER.get());
				output.accept(KoratioItems.SPIKED_PORKCHOP.get());
				output.accept(KoratioItems.COOKED_SPIKED_PORKCHOP.get());
				output.accept(KoratioItems.CHOCOLATE_MILK_BUCKET.get());
				
				//Wood
				output.accept(KoratioBlocks.PANGO_LOG.get());
				output.accept(KoratioBlocks.PANGO_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_PANGO_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_PANGO_WOOD.get());
				output.accept(KoratioBlocks.PANGO_PLANKS.get());
				output.accept(KoratioBlocks.PANGO_STAIRS.get());
				output.accept(KoratioBlocks.PANGO_SLAB.get());
				output.accept(KoratioBlocks.PANGO_FENCE.get());
				output.accept(KoratioBlocks.PANGO_FENCE_GATE.get());
				output.accept(KoratioBlocks.PANGO_DOOR.get());
				output.accept(KoratioBlocks.TALL_PANGO_DOOR.get());
				output.accept(KoratioBlocks.PANGO_TRAPDOOR.get());
				output.accept(KoratioBlocks.PANGO_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.PANGO_BUTTON.get());
				output.accept(KoratioItems.PANGO_SIGN.get());
				output.accept(KoratioItems.PANGO_HANGING_SIGN.get());
				output.accept(KoratioItems.PANGO_BOAT.get());
				output.accept(KoratioItems.PANGO_CHEST_BOAT.get());
				output.accept(KoratioBlocks.PANGO_BOOKSHELF.get());
				output.accept(KoratioBlocks.PANGO_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_PANGO_CHEST.get());
				
				output.accept(KoratioBlocks.RUGONA_LOG.get());
				output.accept(KoratioBlocks.RUGONA_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_RUGONA_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_RUGONA_WOOD.get());
				output.accept(KoratioBlocks.RUGONA_PLANKS.get());
				output.accept(KoratioBlocks.RUGONA_STAIRS.get());
				output.accept(KoratioBlocks.RUGONA_SLAB.get());
				output.accept(KoratioBlocks.RUGONA_FENCE.get());
				output.accept(KoratioBlocks.RUGONA_FENCE_GATE.get());
				output.accept(KoratioBlocks.RUGONA_DOOR.get());
				output.accept(KoratioBlocks.TALL_RUGONA_DOOR.get());
				output.accept(KoratioBlocks.RUGONA_TRAPDOOR.get());
				output.accept(KoratioBlocks.RUGONA_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.RUGONA_BUTTON.get());
				output.accept(KoratioItems.RUGONA_SIGN.get());
				output.accept(KoratioItems.RUGONA_HANGING_SIGN.get());
				output.accept(KoratioItems.RUGONA_BOAT.get());
				output.accept(KoratioItems.RUGONA_CHEST_BOAT.get());
				output.accept(KoratioBlocks.RUGONA_BOOKSHELF.get());
				output.accept(KoratioBlocks.RUGONA_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_RUGONA_CHEST.get());

				output.accept(KoratioBlocks.CANDY_LOG.get());
				output.accept(KoratioBlocks.CANDY_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_CANDY_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_CANDY_WOOD.get());
				output.accept(KoratioBlocks.CANDY_PLANKS.get());
				output.accept(KoratioBlocks.CANDY_STAIRS.get());
				output.accept(KoratioBlocks.CANDY_SLAB.get());
				output.accept(KoratioBlocks.CANDY_FENCE.get());
				output.accept(KoratioBlocks.CANDY_FENCE_GATE.get());
				output.accept(KoratioBlocks.CANDY_DOOR.get());
				//output.accept(KoratioBlocks.TALL_CANDY_DOOR.get());
				output.accept(KoratioBlocks.CANDY_TRAPDOOR.get());
				output.accept(KoratioBlocks.CANDY_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.CANDY_BUTTON.get());
				output.accept(KoratioItems.CANDY_SIGN.get());
				output.accept(KoratioItems.CANDY_HANGING_SIGN.get());
				output.accept(KoratioItems.CANDY_BOAT.get());
				output.accept(KoratioItems.CANDY_CHEST_BOAT.get());
				output.accept(KoratioBlocks.CANDY_BOOKSHELF.get());
				output.accept(KoratioBlocks.CANDY_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_CANDY_CHEST.get());

				output.accept(KoratioBlocks.CHOCOLATE_OAK_LOG.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_CHOCOLATE_OAK_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_CHOCOLATE_OAK_WOOD.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_PLANKS.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_STAIRS.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_SLAB.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_FENCE.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_FENCE_GATE.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_DOOR.get());
				//output.accept(KoratioBlocks.TALL_CHOCOLATE_OAK_DOOR.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_TRAPDOOR.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_BUTTON.get());
				output.accept(KoratioItems.CHOCOLATE_OAK_SIGN.get());
				output.accept(KoratioItems.CHOCOLATE_OAK_HANGING_SIGN.get());
				output.accept(KoratioItems.CHOCOLATE_OAK_BOAT.get());
				output.accept(KoratioItems.CHOCOLATE_OAK_CHEST_BOAT.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_BOOKSHELF.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_CHOCOLATE_OAK_CHEST.get());

				output.accept(KoratioBlocks.ELVEN_LOG.get());
				output.accept(KoratioBlocks.ELVEN_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_ELVEN_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_ELVEN_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.get());
				output.accept(KoratioBlocks.ELVEN_PLANKS.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_PLANKS.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_PLANKS.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_PLANKS.get());
				output.accept(KoratioBlocks.ELVEN_STAIRS.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_STAIRS.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_STAIRS.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_STAIRS.get());
				output.accept(KoratioBlocks.ELVEN_SLAB.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_SLAB.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_SLAB.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_SLAB.get());
				output.accept(KoratioBlocks.ELVEN_FENCE.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_FENCE.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_FENCE.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_FENCE.get());
				output.accept(KoratioBlocks.ELVEN_FENCE_GATE.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get());
				output.accept(KoratioBlocks.ELVEN_DOOR.get());
				output.accept(KoratioBlocks.TALL_ELVEN_DOOR.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_DOOR.get());
				output.accept(KoratioBlocks.TALL_BLUE_ELVEN_DOOR.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_DOOR.get());
				output.accept(KoratioBlocks.TALL_CYAN_ELVEN_DOOR.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_DOOR.get());
				output.accept(KoratioBlocks.TALL_GREEN_ELVEN_DOOR.get());
				output.accept(KoratioBlocks.ELVEN_TRAPDOOR.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_TRAPDOOR.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_TRAPDOOR.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_TRAPDOOR.get());
				output.accept(KoratioBlocks.ELVEN_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.ELVEN_BUTTON.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_BUTTON.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_BUTTON.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_BUTTON.get());
				output.accept(KoratioItems.ELVEN_SIGN.get());
				output.accept(KoratioItems.BLUE_ELVEN_SIGN.get());
				output.accept(KoratioItems.CYAN_ELVEN_SIGN.get());
				output.accept(KoratioItems.GREEN_ELVEN_SIGN.get());
				output.accept(KoratioItems.ELVEN_HANGING_SIGN.get());
				output.accept(KoratioItems.BLUE_ELVEN_HANGING_SIGN.get());
				output.accept(KoratioItems.CYAN_ELVEN_HANGING_SIGN.get());
				output.accept(KoratioItems.GREEN_ELVEN_HANGING_SIGN.get());
				output.accept(KoratioItems.ELVEN_BOAT.get());
				output.accept(KoratioItems.BLUE_ELVEN_BOAT.get());
				output.accept(KoratioItems.CYAN_ELVEN_BOAT.get());
				output.accept(KoratioItems.GREEN_ELVEN_BOAT.get());
				output.accept(KoratioItems.ELVEN_CHEST_BOAT.get());
				output.accept(KoratioItems.BLUE_ELVEN_CHEST_BOAT.get());
				output.accept(KoratioItems.CYAN_ELVEN_CHEST_BOAT.get());
				output.accept(KoratioItems.GREEN_ELVEN_CHEST_BOAT.get());
				output.accept(KoratioBlocks.ELVEN_BOOKSHELF.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get());
				output.accept(KoratioBlocks.ELVEN_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_ELVEN_CHEST.get());
				output.accept(KoratioBlocks.BLUE_ELVEN_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get());
				output.accept(KoratioBlocks.CYAN_ELVEN_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get());
				output.accept(KoratioBlocks.GREEN_ELVEN_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get());
				
				output.accept(KoratioBlocks.VARESO_LOG.get());
				output.accept(KoratioBlocks.VARESO_WOOD.get());
				output.accept(KoratioBlocks.STRIPPED_VARESO_LOG.get());
				output.accept(KoratioBlocks.STRIPPED_VARESO_WOOD.get());
				output.accept(KoratioBlocks.VARESO_PLANKS.get());
				output.accept(KoratioBlocks.VARESO_STAIRS.get());
				output.accept(KoratioBlocks.VARESO_SLAB.get());
				output.accept(KoratioBlocks.VARESO_FENCE.get());
				output.accept(KoratioBlocks.VARESO_FENCE_GATE.get());
				output.accept(KoratioBlocks.VARESO_DOOR.get());
				output.accept(KoratioBlocks.TALL_VARESO_DOOR.get());
				output.accept(KoratioBlocks.VARESO_TRAPDOOR.get());
				output.accept(KoratioBlocks.VARESO_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.VARESO_BUTTON.get());
				output.accept(KoratioItems.VARESO_SIGN.get());
				output.accept(KoratioItems.VARESO_HANGING_SIGN.get());
				output.accept(KoratioItems.VARESO_BOAT.get());
				output.accept(KoratioItems.VARESO_CHEST_BOAT.get());
				output.accept(KoratioBlocks.VARESO_BOOKSHELF.get());
				output.accept(KoratioBlocks.VARESO_CHEST.get());
				output.accept(KoratioBlocks.TRAPPED_VARESO_CHEST.get());
				
				//Plants
				output.accept(KoratioBlocks.PANGO_LEAVES.get());
				output.accept(KoratioBlocks.PANGO_LEAF_PANE.get());
				output.accept(KoratioBlocks.PANGO_SAPLING.get());
				output.accept(KoratioBlocks.RUGONA_LEAVES.get());
				output.accept(KoratioBlocks.RUGONA_LEAF_PANE.get());
				output.accept(KoratioBlocks.RUGONA_SAPLING.get());
				output.accept(KoratioBlocks.VARESO_LEAVES.get());
				output.accept(KoratioBlocks.VARESO_LEAF_PANE.get());
				output.accept(KoratioBlocks.VARESO_SAPLING.get());
				output.accept(KoratioBlocks.PINK_COTTON_CANDY_LEAVES.get());
				output.accept(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAVES.get());
				output.accept(KoratioBlocks.LIME_COTTON_CANDY_LEAVES.get());
				output.accept(KoratioBlocks.YELLOW_COTTON_CANDY_LEAVES.get());
				output.accept(KoratioBlocks.PINK_COTTON_CANDY_LEAF_PANE.get());
				output.accept(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAF_PANE.get());
				output.accept(KoratioBlocks.LIME_COTTON_CANDY_LEAF_PANE.get());
				output.accept(KoratioBlocks.YELLOW_COTTON_CANDY_LEAF_PANE.get());
				output.accept(KoratioBlocks.CANDY_SAPLING.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_LEAVES.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_LEAF_PANE.get());
				output.accept(KoratioBlocks.CHOCOLATE_OAK_SAPLING.get());
				output.accept(KoratioBlocks.ELVEN_LEAVES.get());
				output.accept(KoratioBlocks.ELVEN_LEAF_PANE.get());
				output.accept(KoratioBlocks.ELVEN_SAPLING.get());
				output.accept(KoratioBlocks.GILDED_VINES.get());
				output.accept(KoratioBlocks.RAINBOW_ROSE.get());
				output.accept(KoratioBlocks.RAINBOW_ALLIUM.get());
				output.accept(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get());
				output.accept(KoratioBlocks.COOKIE_FLOWER.get());
				output.accept(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.LIGHT_GRAY_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.GRAY_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.BLACK_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.BROWN_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.RED_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.ORANGE_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.LIME_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.CYAN_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.LIGHT_BLUE_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.PURPLE_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.MAGENTA_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.PINK_SUGARGLASS_FLOWER.get());
				output.accept(KoratioBlocks.PURPLE_MUSHROOM.get());
				output.accept(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get());
				output.accept(KoratioBlocks.GREEN_MUSHROOM.get());
				output.accept(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get());
				
				//Weapons, Tools & Armor
				
			}).build());

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CANDY = TABS.register("candy", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 1)
			.withTabsBefore(FANTASIA.getKey()).icon(() -> new ItemStack(KoratioBlocks.CANDY_SHAPER.get()))
			.title(Component.translatable("itemGroup.koratio.candy"))
			.displayItems((parameters, output) -> {
				for (CandyItem candy : CandyItem.CANDY_LIST) {
					output.accept(candy);
				}
			}).build());

	private static void createScrolls(CreativeModeTab.Output output) {
		if (KoratioScrolls.SCROLLS.isEmpty()) return;
		for (Scroll scroll : KoratioScrolls.SCROLLS) {
			output.accept(ScrollUtils.createForScroll(scroll, false));
		}
	}

	private static void createPipingBags(CreativeModeTab.Output output) {
		for (GlazedBlockEntity.PartColor color : GlazedBlockEntity.PartColor.values()) {
			if (color != GlazedBlockEntity.PartColor.NONE) {
				output.accept(createPipingBagForColor(color));
			}
		}
	}

	private static ItemStack createPipingBagForColor(GlazedBlockEntity.PartColor color) {
		ItemStack stack = new ItemStack(KoratioItems.PIPING_BAG.get());
		PipingBagItem bag = (PipingBagItem) stack.getItem();
		bag.setColorAndAmount(stack, color, 69);
		return stack;
	}
	
	private static void createSpawnEggsAlphabetical(CreativeModeTab.Output output) {
		Collection<Item> eggs = KoratioItems.SPAWN_EGGS.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toList());
		eggs.forEach(output::accept);
	}
}