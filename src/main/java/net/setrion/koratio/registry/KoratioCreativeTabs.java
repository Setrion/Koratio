package net.setrion.koratio.registry;

import java.util.Collection;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.scroll.Scroll;
import net.setrion.koratio.scroll.ScrollUtils;

@Mod.EventBusSubscriber(modid = Koratio.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoratioCreativeTabs {
	
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Koratio.MOD_ID);
	
	public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("main", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 1)
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS).icon(() -> new ItemStack(KoratioItems.RAINBOW_GEM.get()))
			.title(Component.translatable("itemGroup.koratio.main"))
			.displayItems((parameters, output) -> {
				output.accept(KoratioItems.DECRYPTING_TABLE.get());
				output.accept(KoratioItems.DECRYPTING_BOOK.get());
				output.accept(KoratioItems.BETTER_DECRYPTING_BOOK.get());
				output.accept(KoratioItems.FANTASTIC_DECRYPTING_BOOK.get());
				output.accept(KoratioItems.RAINBOW_GEM.get());
				output.accept(KoratioItems.RAINBOW_GEM_BLOCK.get());
				output.accept(KoratioItems.RAINBOW_GEM_SWORD.get());
				output.accept(KoratioItems.RAINBOW_GEM_SHOVEL.get());
				output.accept(KoratioItems.RAINBOW_GEM_PICKAXE.get());
				output.accept(KoratioItems.RAINBOW_GEM_AXE.get());
				output.accept(KoratioItems.RAINBOW_GEM_HOE.get());
				output.accept(KoratioItems.RAINBOW_GEM_HELMET.get());
				output.accept(KoratioItems.RAINBOW_GEM_CHESTPLATE.get());
				output.accept(KoratioItems.RAINBOW_GEM_LEGGINGS.get());
				output.accept(KoratioItems.RAINBOW_GEM_BOOTS.get());
				
				createScrolls(output);
				createSpawnEggsAlphabetical(output);
			}).build());
	
	public static final RegistryObject<CreativeModeTab> FANTASIA = TABS.register("fantasia", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 1)
			.withTabsBefore(MAIN.getKey()).icon(() -> new ItemStack(KoratioItems.MINIATURE_FANTASIA_PORTAL.get()))
			.title(Component.translatable("itemGroup.koratio.fantasia"))
			.displayItems((parameters, output) -> {
				
				//Environment
				output.accept(KoratioItems.AMETHYST_BRICKS.get());
				output.accept(KoratioItems.AMETHYST_BRICK_STAIRS.get());
				output.accept(KoratioItems.AMETHYST_BRICK_SLAB.get());
				output.accept(KoratioItems.CHISELED_AMETHYST_BLOCK.get());
				output.accept(KoratioItems.AMETHYST_PILLAR.get());
				output.accept(KoratioItems.AMETHYST_TORCH.get());
				output.accept(KoratioItems.AMETHYST_LANTERN.get());
				output.accept(KoratioItems.AMETHYST_CAMPFIRE.get());
				output.accept(KoratioItems.AMETHYST_GRASS.get());
				output.accept(KoratioItems.AMETHYST_COBWEB.get());
				
				output.accept(KoratioItems.EMERALD_BRICKS.get());
				output.accept(KoratioItems.EMERALD_BRICK_STAIRS.get());
				output.accept(KoratioItems.EMERALD_BRICK_SLAB.get());
				output.accept(KoratioItems.CHISELED_EMERALD_BLOCK.get());
				output.accept(KoratioItems.EMERALD_PILLAR.get());
				output.accept(KoratioItems.EMERALD_TORCH.get());
				output.accept(KoratioItems.EMERALD_LANTERN.get());
				output.accept(KoratioItems.EMERALD_CAMPFIRE.get());
				output.accept(KoratioItems.EMERALD_GRASS.get());
				output.accept(KoratioItems.EMERALD_COBWEB.get());
				
				//Minerals
				output.accept(KoratioItems.RAW_ARSOY.get());
				output.accept(KoratioItems.ARSOY_INGOT.get());
				output.accept(KoratioItems.ARSOY_NUGGET.get());
				output.accept(KoratioItems.ARSOY_ORE.get());
				output.accept(KoratioItems.DEEPSLATE_ARSOY_ORE.get());
				output.accept(KoratioItems.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get());
				output.accept(KoratioItems.RAW_ARSOY_BLOCK.get());
				output.accept(KoratioItems.ARSOY_BLOCK.get());
				
				output.accept(KoratioItems.RED_SUGAR.get());
				output.accept(KoratioItems.BLUE_SUGAR.get());
				output.accept(KoratioItems.YELLOW_SUGAR.get());
				output.accept(KoratioItems.GREEN_SUGAR.get());
				
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
				output.accept(KoratioBlocks.MARSHMALLOW_BLOCK.get());
				output.accept(KoratioBlocks.MARSHMALLOW_STAIRS.get());
				output.accept(KoratioBlocks.MARSHMALLOW_SLAB.get());
				output.accept(KoratioItems.SUGAR_BLOCK.get());
				output.accept(KoratioItems.RED_SUGAR_BLOCK.get());
				output.accept(KoratioItems.BLUE_SUGAR_BLOCK.get());
				output.accept(KoratioItems.YELLOW_SUGAR_BLOCK.get());
				output.accept(KoratioItems.GREEN_SUGAR_BLOCK.get());
				
				//Food
				output.accept(KoratioItems.RAW_PANGO.get());
				output.accept(KoratioItems.CRACKED_PANGO.get());
				output.accept(KoratioItems.SPIKED_PORKCHOP.get());
				output.accept(KoratioItems.COOKED_SPIKED_PORKCHOP.get());
				output.accept(KoratioItems.GOLDEN_CHICKEN.get());
				output.accept(KoratioItems.COOKED_GOLDEN_CHICKEN.get());
				output.accept(KoratioItems.GOLDEN_BREAD.get());
				output.accept(KoratioItems.GOLDEN_EGG.get());
				output.accept(KoratioItems.GOLDEN_WHEAT_SEEDS.get());
				output.accept(KoratioItems.GOLDEN_WHEAT.get());
				output.accept(KoratioItems.GOLDEN_BABY_CARROTS.get());
				output.accept(KoratioItems.CHOCOLATE_MILK_BUCKET.get());
				
				//Wood
				
				//Vanilla
				
				output.accept(KoratioItems.PANGO_LOG.get());
				output.accept(KoratioItems.PANGO_WOOD.get());
				output.accept(KoratioItems.STRIPPED_PANGO_LOG.get());
				output.accept(KoratioItems.STRIPPED_PANGO_WOOD.get());
				output.accept(KoratioItems.PANGO_PLANKS.get());
				output.accept(KoratioItems.PANGO_STAIRS.get());
				output.accept(KoratioItems.PANGO_SLAB.get());
				output.accept(KoratioItems.PANGO_FENCE.get());
				output.accept(KoratioItems.PANGO_FENCE_GATE.get());
				output.accept(KoratioItems.PANGO_DOOR.get());
				output.accept(KoratioItems.PANGO_TRAPDOOR.get());
				output.accept(KoratioItems.PANGO_PRESSURE_PLATE.get());
				output.accept(KoratioItems.PANGO_BUTTON.get());
				output.accept(KoratioItems.PANGO_SIGN.get());
				output.accept(KoratioItems.PANGO_HANGING_SIGN.get());
				output.accept(KoratioItems.PANGO_BOAT.get());
				output.accept(KoratioItems.PANGO_CHEST_BOAT.get());
				output.accept(KoratioItems.PANGO_CHEST.get());
				
				output.accept(KoratioItems.RUGONA_LOG.get());
				output.accept(KoratioItems.RUGONA_WOOD.get());
				output.accept(KoratioItems.STRIPPED_RUGONA_LOG.get());
				output.accept(KoratioItems.STRIPPED_RUGONA_WOOD.get());
				output.accept(KoratioItems.RUGONA_PLANKS.get());
				output.accept(KoratioItems.RUGONA_STAIRS.get());
				output.accept(KoratioItems.RUGONA_SLAB.get());
				output.accept(KoratioItems.RUGONA_FENCE.get());
				output.accept(KoratioItems.RUGONA_FENCE_GATE.get());
				output.accept(KoratioItems.RUGONA_DOOR.get());
				output.accept(KoratioItems.RUGONA_TRAPDOOR.get());
				output.accept(KoratioItems.RUGONA_PRESSURE_PLATE.get());
				output.accept(KoratioItems.RUGONA_BUTTON.get());
				output.accept(KoratioItems.RUGONA_SIGN.get());
				output.accept(KoratioItems.RUGONA_HANGING_SIGN.get());
				output.accept(KoratioItems.RUGONA_BOAT.get());
				output.accept(KoratioItems.RUGONA_CHEST_BOAT.get());
				output.accept(KoratioItems.RUGONA_CHEST.get());
				
				output.accept(KoratioItems.VARESO_LOG.get());
				output.accept(KoratioItems.VARESO_WOOD.get());
				output.accept(KoratioItems.STRIPPED_VARESO_LOG.get());
				output.accept(KoratioItems.STRIPPED_VARESO_WOOD.get());
				output.accept(KoratioItems.VARESO_PLANKS.get());
				output.accept(KoratioItems.VARESO_STAIRS.get());
				output.accept(KoratioItems.VARESO_SLAB.get());
				output.accept(KoratioItems.VARESO_FENCE.get());
				output.accept(KoratioItems.VARESO_FENCE_GATE.get());
				output.accept(KoratioItems.VARESO_DOOR.get());
				output.accept(KoratioItems.VARESO_TRAPDOOR.get());
				output.accept(KoratioItems.VARESO_PRESSURE_PLATE.get());
				output.accept(KoratioItems.VARESO_BUTTON.get());
				output.accept(KoratioItems.VARESO_SIGN.get());
				output.accept(KoratioItems.VARESO_HANGING_SIGN.get());
				output.accept(KoratioItems.VARESO_BOAT.get());
				output.accept(KoratioItems.VARESO_CHEST_BOAT.get());
				output.accept(KoratioItems.VARESO_CHEST.get());
				
				//Plants
				output.accept(KoratioItems.PANGO_LEAVES.get());
				output.accept(KoratioItems.PANGO_SAPLING.get());
				output.accept(KoratioItems.RUGONA_LEAVES.get());
				output.accept(KoratioItems.RUGONA_SAPLING.get());
				output.accept(KoratioItems.VARESO_LEAVES.get());
				output.accept(KoratioItems.VARESO_SAPLING.get());
				output.accept(KoratioItems.GILDED_VINES.get());
				output.accept(KoratioItems.RAINBOW_ROSE.get());
				output.accept(KoratioItems.RAINBOW_ALLIUM.get());
				output.accept(KoratioItems.RAINBOW_LILY_OF_THE_VALLEY.get());
				output.accept(KoratioItems.COOKIE_FLOWER.get());
				output.accept(KoratioItems.WHITE_SUGARGLASS_FLOWER.get());
				output.accept(KoratioItems.BLUE_SUGARGLASS_FLOWER.get());
				output.accept(KoratioItems.GREEN_SUGARGLASS_FLOWER.get());
				output.accept(KoratioItems.YELLOW_SUGARGLASS_FLOWER.get());
				output.accept(KoratioItems.RED_SUGARGLASS_FLOWER.get());
				output.accept(KoratioItems.PURPLE_MUSHROOM.get());
				output.accept(KoratioItems.PURPLE_MUSHROOM_BLOCK.get());
				output.accept(KoratioItems.GREEN_MUSHROOM.get());
				output.accept(KoratioItems.GREEN_MUSHROOM_BLOCK.get());
				
				//Weapons, Tools & Armor
				output.accept(KoratioItems.ARSOY_SWORD.get());
				output.accept(KoratioItems.ARSOY_SHOVEL.get());
				output.accept(KoratioItems.ARSOY_PICKAXE.get());
				output.accept(KoratioItems.ARSOY_AXE.get());
				output.accept(KoratioItems.ARSOY_HOE.get());
				output.accept(KoratioItems.ARSOY_HELMET.get());
				output.accept(KoratioItems.ARSOY_CHESTPLATE.get());
				output.accept(KoratioItems.ARSOY_LEGGINGS.get());
				output.accept(KoratioItems.ARSOY_BOOTS.get());
				
			}).build());
	
	public static final RegistryObject<CreativeModeTab> DEMONICIO = TABS.register("demonicio", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 2)
			.withTabsBefore(FANTASIA.getKey()).icon(() -> new ItemStack(KoratioItems.MINIATURE_FANTASIA_PORTAL.get()))
			.title(Component.translatable("itemGroup.koratio.demonicio"))
			.displayItems((parameters, output) -> {
				
				//Environment
				output.accept(KoratioBlocks.SOUL_STONE.get());
				output.accept(KoratioBlocks.SOUL_STONE_STAIRS.get());
				output.accept(KoratioBlocks.SOUL_STONE_SLAB.get());
				output.accept(KoratioBlocks.SOUL_STONE_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.SOUL_STONE_BUTTON.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE_STAIRS.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE_SLAB.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE_PRESSURE_PLATE.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE_BUTTON.get());
				output.accept(KoratioBlocks.SOUL_COBBLESTONE.get());
				output.accept(KoratioBlocks.SOUL_COBBLESTONE_STAIRS.get());
				output.accept(KoratioBlocks.SOUL_COBBLESTONE_SLAB.get());
				output.accept(KoratioBlocks.SOUL_COBBLESTONE_WALL.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_COBBLESTONE.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_STAIRS.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_SLAB.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_WALL.get());
				output.accept(KoratioBlocks.SMOOTH_SOUL_STONE.get());
				output.accept(KoratioBlocks.SMOOTH_SOUL_STONE_SLAB.get());
				output.accept(KoratioBlocks.SOUL_STONE_BRICKS.get());
				output.accept(KoratioBlocks.CRACKED_SOUL_STONE_BRICKS.get());
				output.accept(KoratioBlocks.SOUL_STONE_BRICK_STAIRS.get());
				output.accept(KoratioBlocks.SOUL_STONE_BRICK_SLAB.get());
				output.accept(KoratioBlocks.SOUL_STONE_BRICK_WALL.get());
				output.accept(KoratioBlocks.CHISELED_SOUL_STONE_BRICKS.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE_BRICKS.get());
				output.accept(KoratioBlocks.CRACKED_INFESTED_SOUL_STONE_BRICKS.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_STAIRS.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_SLAB.get());
				output.accept(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_WALL.get());
				output.accept(KoratioBlocks.CHISELED_INFESTED_SOUL_STONE_BRICKS.get());
				
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get());
				output.accept(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get());
				output.accept(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get());
				output.accept(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB.get());
				output.accept(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS.get());
				output.accept(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get());
				output.accept(KoratioBlocks.CHISELED_BLOOD_STAINED_DEEPSLATE.get());
				output.accept(KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_BRICKS.get());
				output.accept(KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_TILES.get());
				
				//Minerals
				output.accept(KoratioItems.WITHER_BONE.get());
				
				//Food
				
				//Wood
				output.accept(KoratioItems.NIGHY_LOG.get());
				output.accept(KoratioItems.NIGHY_WOOD.get());
				output.accept(KoratioItems.STRIPPED_NIGHY_LOG.get());
				output.accept(KoratioItems.STRIPPED_NIGHY_WOOD.get());
				output.accept(KoratioItems.NIGHY_PLANKS.get());
				output.accept(KoratioItems.NIGHY_STAIRS.get());
				output.accept(KoratioItems.NIGHY_SLAB.get());
				output.accept(KoratioItems.NIGHY_FENCE.get());
				output.accept(KoratioItems.NIGHY_FENCE_GATE.get());
				output.accept(KoratioItems.NIGHY_DOOR.get());
				output.accept(KoratioItems.NIGHY_TRAPDOOR.get());
				output.accept(KoratioItems.NIGHY_PRESSURE_PLATE.get());
				output.accept(KoratioItems.NIGHY_BUTTON.get());
				output.accept(KoratioItems.NIGHY_SIGN.get());
				output.accept(KoratioItems.NIGHY_HANGING_SIGN.get());
				output.accept(KoratioItems.NIGHY_BOAT.get());
				output.accept(KoratioItems.NIGHY_CHEST_BOAT.get());
				output.accept(KoratioItems.NIGHY_CHEST.get());
				
				//Plants
				output.accept(KoratioItems.NIGHY_LEAVES.get());
				output.accept(KoratioItems.NIGHY_SAPLING.get());
				
				//Weapons, Tools & Armor
				output.accept(KoratioItems.WITHER_BONE_SWORD.get());
				output.accept(KoratioItems.WITHER_BONE_SHOVEL.get());
				output.accept(KoratioItems.WITHER_BONE_PICKAXE.get());
				output.accept(KoratioItems.WITHER_BONE_AXE.get());
				output.accept(KoratioItems.WITHER_BONE_HOE.get());
				output.accept(KoratioItems.WITHER_BONE_HELMET.get());
				output.accept(KoratioItems.WITHER_BONE_CHESTPLATE.get());
				output.accept(KoratioItems.WITHER_BONE_LEGGINGS.get());
				output.accept(KoratioItems.WITHER_BONE_BOOTS.get());
				output.accept(KoratioItems.BONE_SWORD.get());
				output.accept(KoratioItems.BONE_SHOVEL.get());
				output.accept(KoratioItems.BONE_PICKAXE.get());
				output.accept(KoratioItems.BONE_AXE.get());
				output.accept(KoratioItems.BONE_HOE.get());
				output.accept(KoratioItems.BONE_HELMET.get());
				output.accept(KoratioItems.BONE_CHESTPLATE.get());
				output.accept(KoratioItems.BONE_LEGGINGS.get());
				output.accept(KoratioItems.BONE_BOOTS.get());
				
			}).build());		
	
	private static void createScrolls(CreativeModeTab.Output output) {
		if (KoratioScrolls.SCROLLS.isEmpty()) return;
		for (Scroll scroll : KoratioScrolls.SCROLLS) {
			output.accept(ScrollUtils.createForScroll(scroll, false));
		}
	}
	
	private static void createSpawnEggsAlphabetical(CreativeModeTab.Output output) {
		Collection<Item> eggs = KoratioItems.SPAWN_EGGS.getEntries().stream().map(RegistryObject::get).toList();
		eggs.forEach(output::accept);
	}
}