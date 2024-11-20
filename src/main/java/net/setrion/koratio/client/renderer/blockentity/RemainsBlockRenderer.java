package net.setrion.koratio.client.renderer.blockentity;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.block.RemainsModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.level.block.RemainsBlock;
import net.setrion.koratio.world.level.block.entity.RemainsBlockEntity;

import javax.annotation.Nullable;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class RemainsBlockRenderer implements BlockEntityRenderer<RemainsBlockEntity> {
    private final Map<RemainsBlock.Type, RemainsModel> modelByType;
    private final Map<RemainsBlock.Type, RemainsModel> modelOverlayByType;
    public static final Map<RemainsBlock.Type, ResourceLocation> SKIN_BY_TYPE = Util.make(Maps.newHashMap(), map -> {
        map.put(RemainsBlock.Type.SKELETON, ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png"));
        map.put(RemainsBlock.Type.WITHER_SKELETON, ResourceLocation.withDefaultNamespace("textures/entity/skeleton/wither_skeleton.png"));
        map.put(RemainsBlock.Type.STRAY, ResourceLocation.withDefaultNamespace("textures/entity/skeleton/stray.png"));
        map.put(RemainsBlock.Type.BOGGED, ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged.png"));
        map.put(RemainsBlock.Type.DEMONIC_SKELETON, Koratio.prefix("textures/entity/demonic/skeleton.png"));
        map.put(RemainsBlock.Type.ZOMBIE, ResourceLocation.withDefaultNamespace("textures/entity/zombie/zombie.png"));
        map.put(RemainsBlock.Type.HUSK, ResourceLocation.withDefaultNamespace("textures/entity/zombie/husk.png"));
        map.put(RemainsBlock.Type.DROWNED, ResourceLocation.withDefaultNamespace("textures/entity/zombie/drowned.png"));
        map.put(RemainsBlock.Type.DEMONIC_ZOMBIE, Koratio.prefix("textures/entity/demonic/zombie.png"));
        map.put(RemainsBlock.Type.ZOMBIE_VILLAGER, ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/zombie_villager.png"));
        map.put(RemainsBlock.Type.PHANTOM, ResourceLocation.withDefaultNamespace("textures/entity/phantom.png"));
        map.put(RemainsBlock.Type.ZOMBIFIED_PIGLIN, ResourceLocation.withDefaultNamespace("textures/entity/piglin/zombified_piglin.png"));
    });

    public static Map<RemainsBlock.Type, RemainsModel> createSkullRenderers(EntityModelSet entityModelSet) {
        ImmutableMap.Builder<RemainsBlock.Type, RemainsModel> builder = ImmutableMap.builder();
        builder.put(RemainsBlock.Type.SKELETON, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.SKELETON_REMAINS)));
        builder.put(RemainsBlock.Type.WITHER_SKELETON, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.WITHER_SKELETON_REMAINS)));
        builder.put(RemainsBlock.Type.STRAY, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.STRAY_REMAINS)));
        builder.put(RemainsBlock.Type.BOGGED, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.BOGGED_REMAINS)));
        builder.put(RemainsBlock.Type.DEMONIC_SKELETON, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.DEMONIC_SKELETON_REMAINS)));
        builder.put(RemainsBlock.Type.ZOMBIE, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.ZOMBIE_REMAINS)));
        builder.put(RemainsBlock.Type.HUSK, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.HUSK_REMAINS)));
        builder.put(RemainsBlock.Type.DROWNED, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.DROWNED_REMAINS)));
        builder.put(RemainsBlock.Type.DEMONIC_ZOMBIE, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.DEMONIC_ZOMBIE_REMAINS)));
        builder.put(RemainsBlock.Type.ZOMBIE_VILLAGER, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.ZOMBIE_VILLAGER_REMAINS)));
        builder.put(RemainsBlock.Type.PHANTOM, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.PHANTOM_REMAINS)));
        builder.put(RemainsBlock.Type.ZOMBIFIED_PIGLIN, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN_REMAINS)));
        return builder.build();
    }

    public static Map<RemainsBlock.Type, RemainsModel> createOverlayRenderers(EntityModelSet entityModelSet) {
        ImmutableMap.Builder<RemainsBlock.Type, RemainsModel> builder = ImmutableMap.builder();
        builder.put(RemainsBlock.Type.STRAY, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.STRAY_REMAINS_OVERLAY)));
        builder.put(RemainsBlock.Type.BOGGED, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.BOGGED_REMAINS_OVERLAY)));
        builder.put(RemainsBlock.Type.DROWNED, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.DROWNED_REMAINS_OVERLAY)));
        builder.put(RemainsBlock.Type.ZOMBIE_VILLAGER, new RemainsModel(entityModelSet.bakeLayer(ModelLayers.ZOMBIE_VILLAGER_REMAINS_OVERLAY)));
        return builder.build();
    }

    public RemainsBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.modelByType = createSkullRenderers(context.getModelSet());
        this.modelOverlayByType = createOverlayRenderers(context.getModelSet());
    }

    public void render(RemainsBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockstate = blockEntity.getBlockState();
        RemainsBlock.Type type = ((RemainsBlock)blockstate.getBlock()).getType();
        int rot = 0;
        if (type == RemainsBlock.Type.PHANTOM) {
            rot = 180;
        }
        RemainsModel pileModel = this.modelByType.get(type);
        RenderType rendertype = getRenderType(type);
        if (type == RemainsBlock.Type.DROWNED || type == RemainsBlock.Type.STRAY || type == RemainsBlock.Type.BOGGED || type == RemainsBlock.Type.ZOMBIE_VILLAGER) {
            RenderType rendertype2 = getOverlayRenderType(type);
            RemainsModel pileModel2 = this.modelOverlayByType.get(type);
            render2LayerRemains(null, blockstate.getValue(RemainsBlock.FACING).toYRot(), poseStack, bufferSource, packedLight, pileModel, pileModel2, rendertype, rendertype2);
        } else {
            renderRemains(null, blockstate.getValue(RemainsBlock.FACING).toYRot()+rot, poseStack, bufferSource, packedLight, pileModel, rendertype);
        }
    }

    public static void renderRemains(@Nullable Direction direction, float yRot, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, RemainsModel model, RenderType renderType) {
        poseStack.pushPose();
        if (direction == null) {
            poseStack.translate(0.5F, 0.0F, 0.5F);
        } else {
            float f = 0.25F;
            poseStack.translate(0.5F - (float)direction.getStepX() * 0.25F, 0.25F, 0.5F - (float)direction.getStepZ() * 0.25F);
        }
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(renderType);
        model.setupAnim(yRot, 0.0F);
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    public static void render2LayerRemains(@Nullable Direction direction, float yRot, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, RemainsModel model, RemainsModel model2, RenderType renderType, RenderType renderType2) {
        poseStack.pushPose();
        if (direction == null) {
            poseStack.translate(0.5F, 0.0F, 0.5F);
        } else {
            float f = 0.25F;
            poseStack.translate(0.5F - (float)direction.getStepX() * 0.25F, 0.25F, 0.5F - (float)direction.getStepZ() * 0.25F);
        }
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(renderType);
        model.setupAnim(yRot, 0.0F);
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.01F, 0.5F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexconsumer2 = bufferSource.getBuffer(renderType2);
        model2.setupAnim(yRot, 0.0F);
        model2.renderToBuffer(poseStack, vertexconsumer2, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    public static RenderType getRenderType(RemainsBlock.Type type) {
        ResourceLocation resourcelocation = SKIN_BY_TYPE.get(type);
        return RenderType.entityCutoutNoCullZOffset(resourcelocation);
    }

    public static RenderType getOverlayRenderType(RemainsBlock.Type type) {
        if (type == RemainsBlock.Type.STRAY) {
            return RenderType.entityCutoutNoCullZOffset(ResourceLocation.withDefaultNamespace("textures/entity/skeleton/stray_overlay.png"));
        } else if (type == RemainsBlock.Type.BOGGED) {
            return RenderType.entityCutoutNoCullZOffset(ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged_overlay.png"));
        } else if (type == RemainsBlock.Type.ZOMBIE_VILLAGER) {
            return RenderType.entityCutoutNoCullZOffset(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/type/plains.png"));
        } else {
            return RenderType.entityCutoutNoCullZOffset(ResourceLocation.withDefaultNamespace("textures/entity/zombie/drowned_outer_layer.png"));
        }
    }
}
