package net.setrion.koratio.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

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

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
		tooltipComponents.add(Component.translatable("decrypting_book.power").append(Component.literal(""+getPower()).withStyle(ChatFormatting.GOLD)));
    }
}