package net.setrion.koratio.events;

import com.google.gson.JsonParseException;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.NeoForgeRenderTypes;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.setrion.koratio.client.model.block.GlazedModel;
import net.setrion.koratio.client.renderer.GhostVertexConsumer;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioTags;
import net.setrion.koratio.world.item.PipingBagItem;
import net.setrion.koratio.world.level.block.entity.GlazedBlockEntity;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@EventBusSubscriber(Dist.CLIENT)
public class ForgeClientEvents {
	
	private static float fade;

	@SubscribeEvent
	public static void onRenderLevelStage(final RenderLevelStageEvent event) {
		if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
			if (Minecraft.getInstance().hitResult instanceof BlockHitResult hit && hit.getType() == HitResult.Type.BLOCK) {
				if (Minecraft.getInstance().level.getBlockEntity(hit.getBlockPos()) instanceof GlazedBlockEntity glazed) {
					if (Minecraft.getInstance().player.getMainHandItem().is(KoratioItems.PIPING_BAG) && ((PipingBagItem) Minecraft.getInstance().player.getMainHandItem().getItem()).getAmount(Minecraft.getInstance().player.getMainHandItem()) > 0) {
						int dir = 0;
						if (hit.getDirection() == Direction.DOWN) {
							dir = 5;
						} else if (hit.getDirection() == Direction.UP) {
							dir = 4;
						} else if (hit.getDirection() == Direction.NORTH) {
							dir = 0;
						} else if (hit.getDirection() == Direction.EAST) {
							dir = 1;
						} else if (hit.getDirection() == Direction.SOUTH) {
							dir = 2;
						} else if (hit.getDirection() == Direction.WEST) {
							dir = 3;
						}
						if (glazed.getColor(dir) == GlazedBlockEntity.PartColor.NONE || (glazed.getColor(dir) == ((PipingBagItem) Minecraft.getInstance().player.getMainHandItem().getItem()).getColor(Minecraft.getInstance().player.getMainHandItem()) && !glazed.isFaceFull(hit.getDirection()))) {
							Vec3 offset = Vec3.atLowerCornerOf(hit.getBlockPos()).subtract(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition());
							event.getPoseStack().pushPose();
							event.getPoseStack().translate(offset.x, offset.y, offset.z);
							event.getPoseStack().scale(1.001F, 1.001F, 1.001F);
							event.getPoseStack().translate(-.0005F, -.0005F, -.0005F);
							int color = ((PipingBagItem) Minecraft.getInstance().player.getMainHandItem().getItem()).getColor(Minecraft.getInstance().player.getMainHandItem()).getColor();
							VertexConsumer builder = new GhostVertexConsumer(Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(NeoForgeRenderTypes.TRANSLUCENT_ON_PARTICLES_TARGET.get()), 120, Color.decode(color + "").getRed(), Color.decode(color + "").getGreen(), Color.decode(color + "").getBlue());
							if (!Minecraft.getInstance().player.isShiftKeyDown()) {
								BakedModel model = getModel(hit);
								GlazedBlockEntity.Part i = getHitPart(hit);
								if (model != null && !glazed.getPart(hit.getDirection(), i)) {
									Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(Minecraft.getInstance().level, model, Minecraft.getInstance().level.getBlockState(hit.getBlockPos()), hit.getBlockPos(), event.getPoseStack(), builder, true, RandomSource.create(), Minecraft.getInstance().level.getBlockState(hit.getBlockPos()).getSeed(hit.getBlockPos()), OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
								}
							} else {
								for (int i = 0; i < 5; i++) {
									if (hit.getDirection() == Direction.NORTH) {
										Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(Minecraft.getInstance().level, GlazedModel.getNorthOverlays()[i], Minecraft.getInstance().level.getBlockState(hit.getBlockPos()), hit.getBlockPos(), event.getPoseStack(), builder, false, RandomSource.create(), Minecraft.getInstance().level.getBlockState(hit.getBlockPos()).getSeed(hit.getBlockPos()), OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
									} else if (hit.getDirection() == Direction.EAST) {
										Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(Minecraft.getInstance().level, GlazedModel.getEastOverlays()[i], Minecraft.getInstance().level.getBlockState(hit.getBlockPos()), hit.getBlockPos(), event.getPoseStack(), builder, false, RandomSource.create(), Minecraft.getInstance().level.getBlockState(hit.getBlockPos()).getSeed(hit.getBlockPos()), OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
									} else if (hit.getDirection() == Direction.SOUTH) {
										Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(Minecraft.getInstance().level, GlazedModel.getSouthOverlays()[i], Minecraft.getInstance().level.getBlockState(hit.getBlockPos()), hit.getBlockPos(), event.getPoseStack(), builder, false, RandomSource.create(), Minecraft.getInstance().level.getBlockState(hit.getBlockPos()).getSeed(hit.getBlockPos()), OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
									} else if (hit.getDirection() == Direction.WEST) {
										Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(Minecraft.getInstance().level, GlazedModel.getWestOverlays()[i], Minecraft.getInstance().level.getBlockState(hit.getBlockPos()), hit.getBlockPos(), event.getPoseStack(), builder, false, RandomSource.create(), Minecraft.getInstance().level.getBlockState(hit.getBlockPos()).getSeed(hit.getBlockPos()), OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
									} else if (hit.getDirection() == Direction.UP) {
										Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(Minecraft.getInstance().level, GlazedModel.getTopOverlays()[i], Minecraft.getInstance().level.getBlockState(hit.getBlockPos()), hit.getBlockPos(), event.getPoseStack(), builder, false, RandomSource.create(), Minecraft.getInstance().level.getBlockState(hit.getBlockPos()).getSeed(hit.getBlockPos()), OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
									} else if (hit.getDirection() == Direction.DOWN) {
										Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(Minecraft.getInstance().level, GlazedModel.getBottomOverlays()[i], Minecraft.getInstance().level.getBlockState(hit.getBlockPos()), hit.getBlockPos(), event.getPoseStack(), builder, false, RandomSource.create(), Minecraft.getInstance().level.getBlockState(hit.getBlockPos()).getSeed(hit.getBlockPos()), OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
									}
								}
							}
							event.getPoseStack().popPose();
						}
					}
				}
			}
		}
	}

	private static BakedModel getModel(BlockHitResult hitResult) {
		GlazedBlockEntity.Part i = getHitPart(hitResult);
		int id = 10;
		if (i != null) {
			if (i == GlazedBlockEntity.Part.MIDDLE) id = 4;
			else if (i == GlazedBlockEntity.Part.TOP) id = 2;
			else if (i == GlazedBlockEntity.Part.RIGHT) id = 0;
			else if (i == GlazedBlockEntity.Part.BOTTOM) id = 3;
			else if (i == GlazedBlockEntity.Part.LEFT) id = 1;
			if (id < 5) {
				if (hitResult.getDirection() == Direction.NORTH) {
					return GlazedModel.getNorthOverlays()[id];
				} else if (hitResult.getDirection() == Direction.EAST) {
					return GlazedModel.getEastOverlays()[id];
				} else if (hitResult.getDirection() == Direction.SOUTH) {
					return GlazedModel.getSouthOverlays()[id];
				} else if (hitResult.getDirection() == Direction.WEST) {
					return GlazedModel.getWestOverlays()[id];
				} else if (hitResult.getDirection() == Direction.UP) {
					return GlazedModel.getTopOverlays()[id];
				} else if (hitResult.getDirection() == Direction.DOWN) {
					return GlazedModel.getBottomOverlays()[id];
				}
			}
		}
		return null;
	}

	private static GlazedBlockEntity.Part getHitPart(BlockHitResult hitResult) {
		Direction direction = hitResult.getDirection();
		BlockPos blockpos = hitResult.getBlockPos().relative(direction);
		Vec3 vec3 = hitResult.getLocation().subtract(blockpos.getX(), blockpos.getY(), blockpos.getZ());
		double d0 = vec3.x();
		double d1 = vec3.y();
		double d2 = vec3.z();

		if (direction == Direction.NORTH || direction == Direction.SOUTH) {
			if (d0 < 0.2 && d1 < 0.8) {
				if (direction == Direction.NORTH) return GlazedBlockEntity.Part.LEFT;
				return GlazedBlockEntity.Part.RIGHT;
			} else if (d1 < 0.2) {
				return GlazedBlockEntity.Part.BOTTOM;
			} else if (d1 > 0.8) {
				return GlazedBlockEntity.Part.TOP;
			} else if (d0 > 0.8 && d1 < 0.8) {
				if (direction == Direction.NORTH) return GlazedBlockEntity.Part.RIGHT;
				return GlazedBlockEntity.Part.LEFT;
			} else {
				return GlazedBlockEntity.Part.MIDDLE;
			}
		} else if (direction == Direction.EAST || direction == Direction.WEST) {
			if (d2 < 0.2 && d1 < 0.8) {
				if (direction == Direction.EAST) return GlazedBlockEntity.Part.LEFT;
				return GlazedBlockEntity.Part.RIGHT;
			} else if (d1 < 0.2) {
				return GlazedBlockEntity.Part.BOTTOM;
			} else if (d1 > 0.8) {
				return GlazedBlockEntity.Part.TOP;
			} else if (d2 > 0.8 && d1 < 0.8) {
				if (direction == Direction.EAST) return GlazedBlockEntity.Part.RIGHT;
				return GlazedBlockEntity.Part.LEFT;
			} else {
				return GlazedBlockEntity.Part.MIDDLE;
			}
		} else if (direction == Direction.UP || direction == Direction.DOWN) {
			if (d0 < 0.2 && d2 < 0.8) {
				return GlazedBlockEntity.Part.RIGHT;
			} else if (d2 < 0.2) {
				if (direction == Direction.UP) return GlazedBlockEntity.Part.TOP;
				return GlazedBlockEntity.Part.BOTTOM;
			} else if (d2 > 0.8) {
				if (direction == Direction.UP) return GlazedBlockEntity.Part.BOTTOM;
				return GlazedBlockEntity.Part.TOP;
			} else if (d0 > 0.8 && d2 < 0.8) {
				return GlazedBlockEntity.Part.LEFT;
			} else {
				return GlazedBlockEntity.Part.MIDDLE;
			}
		}
		return null;
	}
	
	@SubscribeEvent
	public static void onFogRenderDensity(ViewportEvent.RenderFog event) {
		Minecraft minecraft = Minecraft.getInstance();
		LocalPlayer player = minecraft.player;
		if (event.getMode() != FogMode.FOG_TERRAIN || player == null || player.isSpectator() || player.isInLava() || player.isUnderWater() || !player.isAlive() || player.level().dimension() != KoratioDimensions.FANTASIA_DIMENSION_KEY || player.hasEffect(MobEffects.BLINDNESS) || player.hasEffect(MobEffects.DARKNESS)) {
			return;
		}
		
		Holder<Biome> currentBiome = player.level().getBiome(player.blockPosition());
		if (!currentBiome.is(KoratioTags.Biomes.FOGGY_BIOMES)) {
			fade = Math.max(0, fade-1);
		} else {
			fade = Math.min(250, fade+1);
		}
		
		if (fade == 0) {
			return;
		}
		event.setCanceled(true);
		event.setFarPlaneDistance(minecraft.gameRenderer.getRenderDistance()-((minecraft.gameRenderer.getRenderDistance()-60)*(fade/250.0F)));
		float f = Mth.clamp(minecraft.gameRenderer.getRenderDistance()/10.0F, 4.0F, 64.0F);
		event.setNearPlaneDistance(minecraft.gameRenderer.getRenderDistance()-f-(minecraft.gameRenderer.getRenderDistance()-f)*(fade/250.0F));
	}
}