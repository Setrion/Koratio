package net.setrion.koratio.world.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class AbilitySword extends SwordItem {

	public AbilitySword(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity entity) {
		if (entity instanceof Player player) {
			if (!player.getCooldowns().isOnCooldown(this)) {
				if (!player.level().isClientSide()) {
					if (player.isCrouching()) {
						target.setRemainingFireTicks(5);
						player.getCooldowns().addCooldown(this, 100);
						stack.hurtAndBreak(10, entity, EquipmentSlot.MAINHAND);
						return true;
					}
				}
			}
		}
		return super.hurtEnemy(stack, target, entity);
	}
}