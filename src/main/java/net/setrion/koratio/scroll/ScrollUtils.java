package net.setrion.koratio.scroll;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.registry.KoratioDataComponents;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioScrolls;

public class ScrollUtils {

	public static boolean hasScrollData(ItemStack stack) {
        return stack.getComponents().has(KoratioDataComponents.SCROLL_DATA.get());
    }
	
	public static Scroll getScroll(ItemStack stack) {
		DataComponentMap map = stack.getComponents();
		if (!hasScrollData(stack)) return KoratioScrolls.FAILURE;
		for (Scroll scroll : KoratioScrolls.SCROLLS) {
			if (map.get(KoratioDataComponents.SCROLL_DATA.get()).name().equals(scroll.name())) {
				return scroll;
			}
		}
		return KoratioScrolls.FAILURE;
	}
	
	public static ItemStack addScrollToStack(ItemStack stack, Scroll scroll, boolean encrypted) {
		KoratioDataComponents.ScrollRecord scrollRecord = new KoratioDataComponents.ScrollRecord(scroll.name(), encrypted);
		stack.set(KoratioDataComponents.SCROLL_DATA.get(), scrollRecord);
		return stack;
	}
	
	public static boolean isEncrypted(ItemStack stack) {
		return stack.getComponents().get(KoratioDataComponents.SCROLL_DATA.get()).isEncrypted();
	}
	
	public static ItemStack decryptScroll(ItemStack stack) {
		KoratioDataComponents.ScrollRecord scrollRecord = new KoratioDataComponents.ScrollRecord(stack.get(KoratioDataComponents.SCROLL_DATA.get()).name(), false);
		stack.set(KoratioDataComponents.SCROLL_DATA.get(), scrollRecord);
		return stack;
	}
	
	public static ItemStack createForScroll(Scroll scroll, boolean encrypted) {
		ItemStack itemstack = new ItemStack(KoratioItems.SCROLL.get());
		addScrollToStack(itemstack, scroll, encrypted);
		return itemstack;
	}
	
	public static Scroll getScrollByName(String name) {
		for (Scroll scroll : KoratioScrolls.SCROLLS) {
			if (name.equals(scroll.name())) {
				return scroll;
			}
		}
		return KoratioScrolls.BASIC;
	}
}