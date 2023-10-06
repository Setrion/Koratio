package net.setrion.koratio.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.scroll.ScrollUtils;

@Mod.EventBusSubscriber(modid = Koratio.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorHandler {

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, getter, pos, tintIndex) -> {
         return getter != null && pos != null ? BiomeColors.getAverageGrassColor(getter, pos) : GrassColor.getDefaultColor();
		}, KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get(), KoratioBlocks.COTTON_CANDY_GRASS.get());
		event.register((state, getter, pos, tintIndex) -> {
			return getter != null && pos != null ? BiomeColors.getAverageFoliageColor(getter, pos) : GrassColor.getDefaultColor();
		}, KoratioBlocks.NIGHY_LEAVES.get());
		event.register((state, getter, pos, tintIndex) -> {
			if (tintIndex == 0) return 0xFFFFFF;
	
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
	
				red = pos.getX() * 32 + pos.getY() * 16;
				if ((red & 256) != 0) {
					red = 255 - (red & 255);
				}
				red &= 255;
	
				blue = pos.getY() * 32 + pos.getZ() * 16;
				if ((blue & 256) != 0) {
					blue = 255 - (blue & 255);
				}
				blue ^= 255;
	
				green = pos.getX() * 16 + pos.getZ() * 32;
				if ((green & 256) != 0) {
					green = 255 - (green & 255);
				}
				green &= 255;
				return red << 16 | blue << 8 | green;
			}
		}, KoratioBlocks.RAINBOW_ROSE.get(), KoratioBlocks.RAINBOW_ALLIUM.get(), KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), KoratioBlocks.POTTED_RAINBOW_ROSE.get(), KoratioBlocks.POTTED_RAINBOW_ALLIUM.get(), KoratioBlocks.POTTED_RAINBOW_LILY_OF_THE_VALLEY.get());
	}
	
	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register((stack, tintIndex) -> {
			return GrassColor.getDefaultColor();
		}, KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get(), KoratioBlocks.COTTON_CANDY_GRASS.get());
		event.register((stack, tintIndex) -> {
			return 2162815+GrassColor.getDefaultColor();
		}, KoratioBlocks.NIGHY_LEAVES.get());
		event.register((stack, tintIndex) -> {
			if (!ScrollUtils.hasScrollData(stack) && tintIndex == 1) return 0;
			return tintIndex == 1 ? (ScrollUtils.getScroll(stack).getType().getColor()) : -1;
		}, KoratioItems.SCROLL.get());
	}
}