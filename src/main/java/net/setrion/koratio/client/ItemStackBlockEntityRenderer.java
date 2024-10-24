package net.setrion.koratio.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.block.ChestBlock;
import net.setrion.koratio.world.level.block.TrappedChestBlock;
import net.setrion.koratio.world.level.block.entity.ChestBlockEntity;
import net.setrion.koratio.world.level.block.entity.TrappedChestBlockEntity;

import java.util.HashMap;
import java.util.Map;

public class ItemStackBlockEntityRenderer extends BlockEntityWithoutLevelRenderer {
	private final Map<Block, ChestBlockEntity> chests = Util.make(new HashMap<>(), map -> {
		makeInstance(map, KoratioBlocks.PANGO_CHEST);
		makeInstance(map, KoratioBlocks.RUGONA_CHEST);
		makeInstance(map, KoratioBlocks.VARESO_CHEST);
		makeInstance(map, KoratioBlocks.CANDY_CHEST);
		makeInstance(map, KoratioBlocks.CHOCOLATE_OAK_CHEST);
		makeInstance(map, KoratioBlocks.ELVEN_CHEST);
		makeInstance(map, KoratioBlocks.BLUE_ELVEN_CHEST);
		makeInstance(map, KoratioBlocks.CYAN_ELVEN_CHEST);
		makeInstance(map, KoratioBlocks.GREEN_ELVEN_CHEST);
	});
	private final Map<Block, TrappedChestBlockEntity> trapped_chests = Util.make(new HashMap<>(), map -> {
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_PANGO_CHEST);
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_RUGONA_CHEST);
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_VARESO_CHEST);
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_CANDY_CHEST);
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_CHOCOLATE_OAK_CHEST);
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_ELVEN_CHEST);
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST);
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST);
		makeTrappedInstance(map, KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST);
	});
	
	public ItemStackBlockEntityRenderer() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext camera, PoseStack ms, MultiBufferSource buffers, int light, int overlay) {
		Item item = stack.getItem();
		if (item instanceof BlockItem blockItem) {
			Block block = blockItem.getBlock();
			if (block instanceof ChestBlock) {
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(chests.get(block), ms, buffers, light, overlay);
			} else if (block instanceof TrappedChestBlock) {
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(trapped_chests.get(block), ms, buffers, light, overlay);
			} else {
				if (block instanceof EntityBlock be) {
					BlockEntity blockEntity = be.newBlockEntity(BlockPos.ZERO, block.defaultBlockState());
					if (blockEntity != null) {
						Minecraft.getInstance().getBlockEntityRenderDispatcher().getRenderer(blockEntity).render(null, 0, ms, buffers, light, overlay);
					}
				}
			}
		}
	}

	public static void makeInstance(Map<Block, ChestBlockEntity> map, DeferredBlock<? extends net.minecraft.world.level.block.ChestBlock> registryObject) {
		net.minecraft.world.level.block.ChestBlock block = registryObject.get();
		map.put(block, new ChestBlockEntity(BlockPos.ZERO, block.defaultBlockState()));
	}

	public static void makeTrappedInstance(Map<Block, TrappedChestBlockEntity> map, DeferredBlock<? extends TrappedChestBlock> registryObject) {
		net.minecraft.world.level.block.ChestBlock block = registryObject.get();
		map.put(block, new TrappedChestBlockEntity(BlockPos.ZERO, block.defaultBlockState()));
	}
}