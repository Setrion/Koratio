package net.setrion.koratio.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.setrion.koratio.events.ClientEvents;
import net.setrion.koratio.scroll.ScrollUtils;

import java.util.List;

public class ScrollItem extends Item {
	
	private static final ResourceLocation ALT_FONT = ResourceLocation.fromNamespaceAndPath("minecraft", "alt");
	private static final Style ENCRYPTED_STYLE = Style.EMPTY.withFont(ALT_FONT);

	public ScrollItem(Properties properties) {
		super(properties);
	}
	
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!ScrollUtils.hasScrollData(itemstack)) return InteractionResult.FAIL;
		if (ScrollUtils.isEncrypted(itemstack)) return InteractionResult.FAIL;
		if (level.isClientSide) {
			ClientEvents.openMenu(player, itemstack);
		}
		player.awardStat(Stats.ITEM_USED.get(this));
		return InteractionResult.SUCCESS;
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
		if (!ScrollUtils.hasScrollData(stack)) return false;
		return ScrollUtils.getScroll(stack).type().isEnchanted();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
		if (!ScrollUtils.hasScrollData(stack)) {
			tooltipComponents.add(Component.translatable("scroll.no_data").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED)); return;
		}
		tooltipComponents.add(ScrollUtils.getScroll(stack).getTitle().withStyle((ScrollUtils.isEncrypted(stack) ? ENCRYPTED_STYLE.withColor(ScrollUtils.getScroll(stack).type().getTextColor()) : Style.EMPTY.withColor(ScrollUtils.getScroll(stack).type().getTextColor()))));
        if (ScrollUtils.isEncrypted(stack)) {
            tooltipComponents.add(Component.translatable("scroll.not_readable").withStyle(ChatFormatting.DARK_RED));
        } else {
            tooltipComponents.add(Component.translatable("scroll.readable").withStyle(ChatFormatting.AQUA));
        }
    }
}