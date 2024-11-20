package net.setrion.koratio.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.scroll.ScrollUtils;
import net.setrion.koratio.world.item.ColoredCandyItem;
import net.setrion.koratio.world.item.PipingBagItem;
import net.setrion.koratio.world.level.block.SugarglassFlowerBlock;
import net.setrion.koratio.world.level.block.entity.GlazedBlockEntity;

@EventBusSubscriber(modid = Koratio.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorHandler {

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, getter, pos, tintIndex) -> getter != null && pos != null ? BiomeColors.getAverageGrassColor(getter, pos) : GrassColor.getDefaultColor(), KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get(), KoratioBlocks.COTTON_CANDY_GRASS.get(), KoratioBlocks.MAGIC_PATH.get());
		event.register((state, getter, pos, tintIndex) -> getter != null && pos != null ? BiomeColors.getAverageFoliageColor(getter, pos) : GrassColor.getDefaultColor());
		event.register((state, getter, pos, tintIndex) -> getter != null && pos != null ? BiomeColors.getAverageFoliageColor(getter, pos) : GrassColor.getDefaultColor(), KoratioBlocks.OAK_LEAF_PANE.get(), KoratioBlocks.SPRUCE_LEAF_PANE.get(), KoratioBlocks.BIRCH_LEAF_PANE.get(), KoratioBlocks.JUNGLE_LEAF_PANE.get(), KoratioBlocks.ACACIA_LEAF_PANE.get(), KoratioBlocks.DARK_OAK_LEAF_PANE.get(), KoratioBlocks.MANGROVE_LEAF_PANE.get());
		event.register((state, getter, pos, tintIndex) -> FoliageColor.getEvergreenColor(), KoratioBlocks.SPRUCE_LEAF_PANE.get());
		event.register((state, getter, pos, tintIndex) -> FoliageColor.getBirchColor(), KoratioBlocks.BIRCH_LEAF_PANE.get());
		event.register((state, getter, pos, tintIndex) -> getter != null && pos != null ? BiomeColors.getAverageFoliageColor(getter, pos) : FoliageColor.getDefaultColor(), KoratioBlocks.OAK_LEAF_PANE.get(), KoratioBlocks.JUNGLE_LEAF_PANE.get(), KoratioBlocks.ACACIA_LEAF_PANE.get(), KoratioBlocks.DARK_OAK_LEAF_PANE.get(), KoratioBlocks.MANGROVE_LEAF_PANE.get());
		event.register((state, getter, pos, tintIndex) -> {
			if (getter != null && pos != null) {
				if (state.getBlock() instanceof SugarglassFlowerBlock sugarglassFlowerBlock) {
					if (tintIndex == 1) {
						return sugarglassFlowerBlock.getColor().getColor();
					}
				}
			}
			return -1;
		}, KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get(), KoratioBlocks.LIGHT_GRAY_SUGARGLASS_FLOWER.get(), KoratioBlocks.GRAY_SUGARGLASS_FLOWER.get(), KoratioBlocks.BLACK_SUGARGLASS_FLOWER.get(), KoratioBlocks.BROWN_SUGARGLASS_FLOWER.get(), KoratioBlocks.RED_SUGARGLASS_FLOWER.get(), KoratioBlocks.ORANGE_SUGARGLASS_FLOWER.get(), KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get(), KoratioBlocks.LIME_SUGARGLASS_FLOWER.get(), KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get(), KoratioBlocks.CYAN_SUGARGLASS_FLOWER.get(), KoratioBlocks.LIGHT_BLUE_SUGARGLASS_FLOWER.get(), KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get(), KoratioBlocks.PURPLE_SUGARGLASS_FLOWER.get(), KoratioBlocks.MAGENTA_SUGARGLASS_FLOWER.get(), KoratioBlocks.PINK_SUGARGLASS_FLOWER.get());
		event.register((state, getter, pos, tintIndex) -> {
			if (getter != null && pos != null) {
				if (getter.getBlockEntity(pos) instanceof GlazedBlockEntity gingerbread) {
					if (tintIndex < 7 && tintIndex > 0) {
						if (gingerbread.getColor(tintIndex - 1) != GlazedBlockEntity.PartColor.NONE) {
							return gingerbread.getColor(tintIndex - 1).getColor();
						}
					}
				}
			}
			return -1;
		}, KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(), KoratioBlocks.GINGERBREAD_BLOCK.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(), KoratioBlocks.GINGERBREAD_BRICKS.get(), KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get(), KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get());
		event.register((state, getter, pos, tintIndex) -> {
			if (tintIndex > 15) return 0xFFFFFF;

			if (getter == null || pos == null) {
				return 0x48B518;
			} else {
				int red = 0;
				int green = 0;
				int blue = 0;

				for (int dz = -1; dz <= 1; ++dz) {
					for (int dx = -1; dx <= 1; ++dx) {
						int color = BiomeColors.getAverageFoliageColor(getter, pos);
						red += (color & 0xFF0000) >> 16;
						green += (color & 0xFF00) >> 8;
						blue += color & 0xFF;
					}
				}

				// RAINBOW!
				red = pos.getX() * 16 + pos.getY() * 16;
				if ((red & 256) != 0) {
					red = 255 - (red & 255);
				}
				red &= 255;

				green = pos.getY() * 16 + pos.getZ() * 16;
				if ((green & 256) != 0) {
					green = 255 - (green & 255);
				}
				green &= 255;

				blue = pos.getX() * 16 + pos.getZ() * 16;
				if ((blue & 256) != 0) {
					blue = 255 - (blue & 255);
				}
				blue &= 255;

				return red << 16 | green << 8 | blue;
			}
		}, KoratioBlocks.RAINBOW_ROSE.get(), KoratioBlocks.RAINBOW_ALLIUM.get(), KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), KoratioBlocks.POTTED_RAINBOW_ROSE.get(), KoratioBlocks.POTTED_RAINBOW_ALLIUM.get(), KoratioBlocks.POTTED_RAINBOW_LILY_OF_THE_VALLEY.get());
	}
	
	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register((stack, tintIndex) -> {
			if (tintIndex == 0) {
				return GrassColor.getDefaultColor();
			}
			return -1;
		}, KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get(), KoratioBlocks.COTTON_CANDY_GRASS.get(), KoratioBlocks.MAGIC_PATH.get());
		event.register((stack, tintIndex) -> 2162815+GrassColor.getDefaultColor());
		event.register((stack, tintIndex) -> {
			BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
			return event.getBlockColors().getColor(blockstate, null, null, tintIndex);
		}, KoratioBlocks.OAK_LEAF_PANE.get(), KoratioBlocks.SPRUCE_LEAF_PANE.get(), KoratioBlocks.BIRCH_LEAF_PANE.get(), KoratioBlocks.JUNGLE_LEAF_PANE.get(), KoratioBlocks.ACACIA_LEAF_PANE.get(), KoratioBlocks.DARK_OAK_LEAF_PANE.get());
		event.register((stack, tintIndex) -> FoliageColor.getMangroveColor(), KoratioBlocks.MANGROVE_LEAF_PANE.get());
		event.register((stack, tintIndex) -> {
			if (!ScrollUtils.hasScrollData(stack) && tintIndex == 1) return 0;
			return tintIndex == 1 ? ARGB.opaque(ScrollUtils.getScroll(stack).type().getColor()) : -1;
		}, KoratioItems.SCROLL.get());
		event.register((stack, tintIndex) -> {
			if (stack.getItem() instanceof BlockItem blockItem) {
				if (blockItem.getBlock() instanceof SugarglassFlowerBlock sugarglassFlowerBlock) {
					if (tintIndex == 1) {
						return ARGB.opaque(sugarglassFlowerBlock.getColor().getColor());
					}
				}
			}
			return -1;
		}, KoratioBlocks.WHITE_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.LIGHT_GRAY_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.GRAY_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.BLACK_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.BROWN_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.RED_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.ORANGE_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.LIME_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.GREEN_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.CYAN_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.LIGHT_BLUE_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.BLUE_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.PURPLE_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.MAGENTA_SUGARGLASS_FLOWER.asItem(), KoratioBlocks.PINK_SUGARGLASS_FLOWER.asItem());
		event.register((stack, tintIndex) -> {
			if (stack.is(KoratioItems.PIPING_BAG)) {
				PipingBagItem bag = (PipingBagItem) stack.getItem();
				if (bag.isEmpty(stack) || tintIndex == 0) {
					return -1;
				}
				return ARGB.opaque(bag.getColor(stack).getColor());
			}
			return 0;
		}, KoratioItems.PIPING_BAG.get());
		for (ColoredCandyItem coloredCandyItem : ColoredCandyItem.candy()) {
			event.register((stack, tintIndex) -> ARGB.opaque(coloredCandyItem.getColor(tintIndex)), coloredCandyItem);
		}
	}
}