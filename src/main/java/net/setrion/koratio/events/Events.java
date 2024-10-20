package net.setrion.koratio.events;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEnchantments;

import java.util.Iterator;

@EventBusSubscriber(modid = Koratio.MOD_ID)
public class Events {

	@SubscribeEvent
	public static void onToolUseOnBlock(BlockEvent.BlockToolModificationEvent event) {
		Block block = event.getState().getBlock();
		if (event.getItemAbility() == ItemAbilities.HOE_TILL) {
			if (block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH || block == Blocks.DIRT) {
				if (event.getContext().getClickedFace() == Direction.DOWN) {
					event.setFinalState(KoratioBlocks.FLIPPED_FARMLAND.get().defaultBlockState());
				}
			}
		}
	}

	@SubscribeEvent(priority= EventPriority.HIGH)
	public static void onPlayerDrops(LivingDropsEvent event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			Entity killer = event.getSource().getDirectEntity();
			if (event.getSource().getEntity() instanceof Player) {
				killer = event.getSource().getEntity();
			}
			if (killer instanceof Player player) {
				if (EnchantmentHelper.getEnchantmentLevel(player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(KoratioEnchantments.TELEKINESIS), player) > 0) {
					Iterator<ItemEntity> drops = event.getDrops().iterator();
					while (drops.hasNext()) {
						ItemStack current = drops.next().getItem();
						if (player.addItem(current)) {
							drops.remove();
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerFish(ItemFishedEvent event) {
		Player player = event.getEntity();
        if (EnchantmentHelper.getEnchantmentLevel(player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(KoratioEnchantments.TELEKINESIS), player) > 0) {
            Iterator<ItemStack> drops = event.getDrops().iterator();
            while (drops.hasNext()) {
                ItemStack current = drops.next();
                if (player.addItem(current)) {
                    drops.remove();
                }
            }
        }
    }
}