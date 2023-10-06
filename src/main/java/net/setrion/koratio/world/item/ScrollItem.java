package net.setrion.koratio.world.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.setrion.koratio.client.gui.screens.inventory.ScrollScreen;
import net.setrion.koratio.scroll.ScrollUtils;

public class ScrollItem extends Item {
	
	private static final ResourceLocation ALT_FONT = new ResourceLocation("minecraft", "alt");
	private static final Style ENCRYPTED_STYLE = Style.EMPTY.withFont(ALT_FONT);

	public ScrollItem(Properties properties) {
		super(properties);
	}
	
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (ScrollUtils.isEncrypted(itemstack)) return InteractionResultHolder.fail(itemstack);
		if (level.isClientSide) {
			Minecraft.getInstance().setScreen(new ScrollScreen(ScrollUtils.getScroll(itemstack)));
		}
		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
		if (!ScrollUtils.hasScrollData(stack)) return false;
		return ScrollUtils.getScroll(stack).getType().isEnchanted();
	}
	
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
		super.appendHoverText(stack, level, components, flag);
		if (!ScrollUtils.hasScrollData(stack)) {
			components.add(Component.translatable("scroll.no_data").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED)); return;
		}
		components.add(ScrollUtils.getScroll(stack).getTitle().withStyle((ScrollUtils.isEncrypted(stack) ? ENCRYPTED_STYLE.withColor(ScrollUtils.getScroll(stack).getType().getTextColor()) : Style.EMPTY.withColor(ScrollUtils.getScroll(stack).getType().getTextColor()))));
		if (ScrollUtils.isEncrypted(stack) ? components.add(Component.translatable("scroll.not_readable").withStyle(ChatFormatting.DARK_RED)) : components.add(Component.translatable("scroll.readable").withStyle(ChatFormatting.AQUA)));
	}
}