package net.setrion.koratio.events;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;

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
}