package net.setrion.koratio.client.model.block;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.block.model.TextureSlots;
import net.minecraft.client.resources.model.*;
import net.minecraft.util.context.ContextMap;

public record GlazedBlockGeometry(String name, BlockModel core, BlockModel topTopMiddleOverlay, BlockModel topTopLeftOverlay, BlockModel topTopRightOverlay, BlockModel topBottomMiddleOverlay, BlockModel topBottomLeftOverlay, BlockModel topBottomRightOverlay, BlockModel topLeftMiddleOverlay, BlockModel topRightMiddleOverlay, BlockModel topMiddleOverlay,
                                  BlockModel bottomTopMiddleOverlay, BlockModel bottomTopLeftOverlay, BlockModel bottomTopRightOverlay, BlockModel bottomBottomMiddleOverlay, BlockModel bottomBottomLeftOverlay, BlockModel bottomBottomRightOverlay, BlockModel bottomLeftMiddleOverlay, BlockModel bottomRightMiddleOverlay, BlockModel bottomMiddleOverlay,
                                  BlockModel northTopMiddleOverlay, BlockModel northTopLeftOverlay, BlockModel northTopRightOverlay, BlockModel northBottomMiddleOverlay, BlockModel northBottomLeftOverlay, BlockModel northBottomRightOverlay, BlockModel northLeftMiddleOverlay, BlockModel northRightMiddleOverlay, BlockModel northMiddleOverlay,
                                  BlockModel eastTopMiddleOverlay, BlockModel eastTopLeftOverlay, BlockModel eastTopRightOverlay, BlockModel eastBottomMiddleOverlay, BlockModel eastBottomLeftOverlay, BlockModel eastBottomRightOverlay, BlockModel eastLeftMiddleOverlay, BlockModel eastRightMiddleOverlay, BlockModel eastMiddleOverlay,
                                  BlockModel southTopMiddleOverlay, BlockModel southTopLeftOverlay, BlockModel southTopRightOverlay, BlockModel southBottomMiddleOverlay, BlockModel southBottomLeftOverlay, BlockModel southBottomRightOverlay, BlockModel southLeftMiddleOverlay, BlockModel southRightMiddleOverlay, BlockModel southMiddleOverlay,
                                  BlockModel westTopMiddleOverlay, BlockModel westTopLeftOverlay, BlockModel westTopRightOverlay, BlockModel westBottomMiddleOverlay, BlockModel westBottomLeftOverlay, BlockModel westBottomRightOverlay, BlockModel westLeftMiddleOverlay, BlockModel westRightMiddleOverlay, BlockModel westMiddleOverlay) implements UnbakedModel {

    @Override
    public BakedModel bake(TextureSlots textureSlots, ModelBaker modelBaker, ModelState modelState, boolean b, boolean b1, ItemTransforms itemTransforms) {
        BakedModel[] northOverlays = new BakedModel[9];
        BakedModel[] eastOverlays = new BakedModel[9];
        BakedModel[] southOverlays = new BakedModel[9];
        BakedModel[] westOverlays = new BakedModel[9];
        BakedModel[] topOverlays = new BakedModel[9];
        BakedModel[] bottomOverlays = new BakedModel[9];
        northOverlays[0] = northMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(northMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        northOverlays[1] = northTopMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(northTopMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        northOverlays[2] = northTopLeftOverlay.bake(UnbakedModel.getTopTextureSlots(northTopLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        northOverlays[3] = northTopRightOverlay.bake(UnbakedModel.getTopTextureSlots(northTopRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        northOverlays[4] = northBottomMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(northBottomMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        northOverlays[5] = northBottomLeftOverlay.bake(UnbakedModel.getTopTextureSlots(northBottomLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        northOverlays[6] = northBottomRightOverlay.bake(UnbakedModel.getTopTextureSlots(northBottomRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        northOverlays[7] = northLeftMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(northLeftMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        northOverlays[8] = northRightMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(northRightMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[0] = eastMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(eastMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[1] = eastTopMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(eastTopMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[2] = eastTopLeftOverlay.bake(UnbakedModel.getTopTextureSlots(eastTopLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[3] = eastTopRightOverlay.bake(UnbakedModel.getTopTextureSlots(eastTopRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[4] = eastBottomMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(eastBottomMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[5] = eastBottomLeftOverlay.bake(UnbakedModel.getTopTextureSlots(eastBottomLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[6] = eastBottomRightOverlay.bake(UnbakedModel.getTopTextureSlots(eastBottomRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[7] = eastLeftMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(eastLeftMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        eastOverlays[8] = eastRightMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(eastRightMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[0] = southMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(southMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[1] = southTopMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(southTopMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[2] = southTopLeftOverlay.bake(UnbakedModel.getTopTextureSlots(southTopLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[3] = southTopRightOverlay.bake(UnbakedModel.getTopTextureSlots(southTopRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[4] = southBottomMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(southBottomMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[5] = southBottomLeftOverlay.bake(UnbakedModel.getTopTextureSlots(southBottomLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[6] = southBottomRightOverlay.bake(UnbakedModel.getTopTextureSlots(southBottomRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[7] = southLeftMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(southLeftMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        southOverlays[8] = southRightMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(southRightMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[0] = westMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(westMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[1] = westTopMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(westTopMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[2] = westTopLeftOverlay.bake(UnbakedModel.getTopTextureSlots(westTopLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[3] = westTopRightOverlay.bake(UnbakedModel.getTopTextureSlots(westTopRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[4] = westBottomMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(westBottomMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[5] = westBottomLeftOverlay.bake(UnbakedModel.getTopTextureSlots(westBottomLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[6] = westBottomRightOverlay.bake(UnbakedModel.getTopTextureSlots(westBottomRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[7] = westLeftMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(westLeftMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        westOverlays[8] = westRightMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(westRightMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[0] = topMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(topMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[1] = topTopMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(topTopMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[2] = topTopLeftOverlay.bake(UnbakedModel.getTopTextureSlots(topTopLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[3] = topTopRightOverlay.bake(UnbakedModel.getTopTextureSlots(topTopRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[4] = topBottomMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(topBottomMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[5] = topBottomLeftOverlay.bake(UnbakedModel.getTopTextureSlots(topBottomLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[6] = topBottomRightOverlay.bake(UnbakedModel.getTopTextureSlots(topBottomRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[7] = topLeftMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(topLeftMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        topOverlays[8] = topRightMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(topRightMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[0] = bottomMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(bottomMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[1] = bottomTopMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(bottomTopMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[2] = bottomTopLeftOverlay.bake(UnbakedModel.getTopTextureSlots(bottomTopLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[3] = bottomTopRightOverlay.bake(UnbakedModel.getTopTextureSlots(bottomTopRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[4] = bottomBottomMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(bottomBottomMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[5] = bottomBottomLeftOverlay.bake(UnbakedModel.getTopTextureSlots(bottomBottomLeftOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[6] = bottomBottomRightOverlay.bake(UnbakedModel.getTopTextureSlots(bottomBottomRightOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[7] = bottomLeftMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(bottomLeftMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        bottomOverlays[8] = bottomRightMiddleOverlay.bake(UnbakedModel.getTopTextureSlots(bottomRightMiddleOverlay, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY);
        return new GlazedModel(name, core.bake(UnbakedModel.getTopTextureSlots(core, modelBaker.rootName()), modelBaker, modelState, b, b1, itemTransforms, ContextMap.EMPTY), northOverlays, eastOverlays, southOverlays, westOverlays, topOverlays, bottomOverlays);
    }

    @Override
    public void resolveDependencies(Resolver resolver) {
        core.resolveDependencies(resolver);
    }
}