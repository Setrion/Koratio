package net.setrion.koratio.world.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class DecryptingBookItem extends Item {

	private final int power;
	
	public DecryptingBookItem(Properties properties, int power) {
		super(properties);
		this.power = power;
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
	
	public int getPower() {
		return power;
	}
	
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
		super.appendHoverText(stack, level, components, flag);
		components.add(Component.translatable("decrypting_book.power").append(Component.literal(""+getPower()).withStyle(ChatFormatting.GOLD))); return;
	}
}