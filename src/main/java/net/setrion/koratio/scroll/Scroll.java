package net.setrion.koratio.scroll;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.setrion.koratio.registry.KoratioScrolls;

public class Scroll {
	
	private final String name;
	private final ScrollType type;

	public Scroll(String name, ScrollType type) {
		this.type = type;
		this.name = name;
		KoratioScrolls.SCROLLS.add(this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ScrollType getType() {
		return this.type;
	}
	
	public MutableComponent getTitle() {
		return Component.translatable("scroll." + this.getName() + ".title");
	}
	
	public enum ScrollType {
		NOTE("note", 16777215, ChatFormatting.WHITE, false),
		EASTER_EGG("easter_egg", 65535, ChatFormatting.YELLOW, true),
		STORY("story", 16711680, ChatFormatting.GOLD, false);
		
		private final String name;
		private final int color;
		private final ChatFormatting text_color;
		private final boolean isEnchanted;
		
		private ScrollType(String name, int color, ChatFormatting text_color,boolean isEnchanted) {
			this.name = name;
			this.color = color;
			this.text_color = text_color;
			this.isEnchanted = isEnchanted;
		}
		
		public String getName() {
			return name;
		}
		
		public int getColor() {
			return color;
		}
		
		public ChatFormatting getTextColor() {
			return text_color;
		}

		public boolean isEnchanted() {
			return isEnchanted;
		}
	}
}