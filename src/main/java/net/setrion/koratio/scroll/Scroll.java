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
		NOTE("note", 16777215, ChatFormatting.WHITE, 2, false),
		EASTER_EGG("easter_egg", 65535, ChatFormatting.YELLOW, 4, true),
		STORY("story", 16711680, ChatFormatting.GOLD, 6, false);
		
		private final String name;
		private final int color;
		private final ChatFormatting text_color;
		private final int cost;
		private final boolean isEnchanted;
		
		private ScrollType(String name, int color, ChatFormatting text_color, int cost, boolean isEnchanted) {
			this.name = name;
			this.color = color;
			this.text_color = text_color;
			this.cost = cost;
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
		
		public int getCost() {
			return cost;
		}
		
		public boolean isEnchanted() {
			return isEnchanted;
		}
	}
}