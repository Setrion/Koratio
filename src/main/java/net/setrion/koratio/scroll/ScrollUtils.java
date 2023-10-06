package net.setrion.koratio.scroll;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioScrolls;

public class ScrollUtils {

	public static boolean hasScrollData(ItemStack stack) {
		if (stack.getTag() == null) return false;
		if (stack.getTag().getCompound("Scroll") == null) return false;
		if (stack.getTag().getCompound("Scroll").get("Name") == null) return false;
		return true;
	}
	
	public static Scroll getScroll(ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		if (!hasScrollData(stack)) return null;
		for (Scroll scroll : KoratioScrolls.SCROLLS) {
			if (tag.getCompound("Scroll").get("Name").getAsString().equals(scroll.getName())) {
				return scroll;
			}
		}
		return null;
	}
	
	public static ItemStack addScrollToStack(ItemStack stack, Scroll scroll, boolean encrypted) {
		CompoundTag scrollTag = new CompoundTag();
		scrollTag.putString("Name", scroll.getName());
		scrollTag.putBoolean("isEncrypted", encrypted);
		stack.getOrCreateTag().put("Scroll", scrollTag);
		return stack;
	}
	
	public static boolean isEncrypted(ItemStack stack) {
		return stack.getTag().getCompound("Scroll").getBoolean("isEncrypted");
	}
	
	public static ItemStack decryptScroll(ItemStack stack) {
		stack.getTag().getCompound("Scroll").putBoolean("isDecrypted", true);
		return stack;
	}
	
	public static ItemStack createForScroll(Scroll scroll, boolean encrypted) {
		ItemStack itemstack = new ItemStack(KoratioItems.SCROLL.get());
		addScrollToStack(itemstack, scroll, encrypted);
		return itemstack;
	}
	
	public static Scroll getScrollByName(String name) {
		for (Scroll scroll : KoratioScrolls.SCROLLS) {
			if (name.equals(scroll.getName())) {
				return scroll;
			}
		}
		return KoratioScrolls.BASIC;
	}
}