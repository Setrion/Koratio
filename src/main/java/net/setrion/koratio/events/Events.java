package net.setrion.koratio.events;

import java.util.Optional;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.biome.FantasiaPortalShape;

@Mod.EventBusSubscriber(modid = Koratio.MOD_ID)
public class Events {

	@SubscribeEvent
	public void onDimensionTravel (EntityTravelToDimensionEvent event) {
		if (event.getEntity() instanceof ItemEntity item) {
			if (item.getItem().getItem() == Items.AMETHYST_SHARD) {
				event.setCanceled(true);
				if (item.level().dimension() == Level.OVERWORLD) {
					Optional<FantasiaPortalShape> optional = FantasiaPortalShape.findEmptyPortalShape(item.level(), item.blockPosition(), Direction.Axis.X);
					if (optional.isPresent()) {
						optional.get().createPortalBlocks();
						item.kill();
						return;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onItemBurn(EntityLeaveLevelEvent event) {
		if (event.getEntity() instanceof ItemEntity item && item.isOnFire() && item.getItem().getItem() == Items.AMETHYST_SHARD) {
			if (event.getLevel().getBlockState(event.getEntity().blockPosition()).getBlock() instanceof BaseFireBlock) {
				event.getLevel().setBlock(event.getEntity().blockPosition(), KoratioBlocks.AMETHYST_FIRE.get().defaultBlockState(), 3);
			}
		} else if (event.getEntity() instanceof ItemEntity item && item.isOnFire() && item.getItem().getItem() == Items.EMERALD) {
			if (event.getLevel().getBlockState(event.getEntity().blockPosition()).getBlock() instanceof BaseFireBlock) {
				event.getLevel().setBlock(event.getEntity().blockPosition(), KoratioBlocks.EMERALD_FIRE.get().defaultBlockState(), 3);
			}
		}
	}
}