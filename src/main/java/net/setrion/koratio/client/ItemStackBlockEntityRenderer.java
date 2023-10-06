package net.setrion.koratio.client;

import java.util.HashMap;
import java.util.Map;

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
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.block.ChestBlock;
import net.setrion.koratio.world.level.block.entity.ChestBlockEntity;

public class ItemStackBlockEntityRenderer extends BlockEntityWithoutLevelRenderer {
	private final Map<Block, ChestBlockEntity> chestEntities = Util.make(new HashMap<>(), map -> {
		makeInstance(map, KoratioBlocks.PANGO_CHEST);
		makeInstance(map, KoratioBlocks.RUGONA_CHEST);
		makeInstance(map, KoratioBlocks.VARESO_CHEST);
		makeInstance(map, KoratioBlocks.NIGHY_CHEST);
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
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.chestEntities.get(block), ms, buffers, light, overlay);
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

	public static void makeInstance(Map<Block, ChestBlockEntity> map, RegistryObject<? extends net.minecraft.world.level.block.ChestBlock> registryObject) {
		net.minecraft.world.level.block.ChestBlock block = registryObject.get();
		map.put(block, new ChestBlockEntity(BlockPos.ZERO, block.defaultBlockState()));
	}
}