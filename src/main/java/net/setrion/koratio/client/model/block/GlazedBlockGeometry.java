package net.setrion.koratio.client.model.block;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

import java.util.List;
import java.util.function.Function;

public record GlazedBlockGeometry(String name, BlockModel core, BlockModel topTopMiddleOverlay, BlockModel topTopLeftOverlay, BlockModel topTopRightOverlay, BlockModel topBottomMiddleOverlay, BlockModel topBottomLeftOverlay, BlockModel topBottomRightOverlay, BlockModel topLeftMiddleOverlay, BlockModel topRightMiddleOverlay, BlockModel topMiddleOverlay,
                                  BlockModel bottomTopMiddleOverlay, BlockModel bottomTopLeftOverlay, BlockModel bottomTopRightOverlay, BlockModel bottomBottomMiddleOverlay, BlockModel bottomBottomLeftOverlay, BlockModel bottomBottomRightOverlay, BlockModel bottomLeftMiddleOverlay, BlockModel bottomRightMiddleOverlay, BlockModel bottomMiddleOverlay,
                                  BlockModel northTopMiddleOverlay, BlockModel northTopLeftOverlay, BlockModel northTopRightOverlay, BlockModel northBottomMiddleOverlay, BlockModel northBottomLeftOverlay, BlockModel northBottomRightOverlay, BlockModel northLeftMiddleOverlay, BlockModel northRightMiddleOverlay, BlockModel northMiddleOverlay,
                                  BlockModel eastTopMiddleOverlay, BlockModel eastTopLeftOverlay, BlockModel eastTopRightOverlay, BlockModel eastBottomMiddleOverlay, BlockModel eastBottomLeftOverlay, BlockModel eastBottomRightOverlay, BlockModel eastLeftMiddleOverlay, BlockModel eastRightMiddleOverlay, BlockModel eastMiddleOverlay,
                                  BlockModel southTopMiddleOverlay, BlockModel southTopLeftOverlay, BlockModel southTopRightOverlay, BlockModel southBottomMiddleOverlay, BlockModel southBottomLeftOverlay, BlockModel southBottomRightOverlay, BlockModel southLeftMiddleOverlay, BlockModel southRightMiddleOverlay, BlockModel southMiddleOverlay,
                                  BlockModel westTopMiddleOverlay, BlockModel westTopLeftOverlay, BlockModel westTopRightOverlay, BlockModel westBottomMiddleOverlay, BlockModel westBottomLeftOverlay, BlockModel westBottomRightOverlay, BlockModel westLeftMiddleOverlay, BlockModel westRightMiddleOverlay, BlockModel westMiddleOverlay) implements IUnbakedGeometry<GlazedBlockGeometry> {

    @Override
    public BakedModel bake(IGeometryBakingContext iGeometryBakingContext, ModelBaker modelBaker, Function<Material, TextureAtlasSprite> function, ModelState modelState, List<ItemOverride> list) {
        BakedModel[] northOverlays = new BakedModel[9];
        BakedModel[] eastOverlays = new BakedModel[9];
        BakedModel[] southOverlays = new BakedModel[9];
        BakedModel[] westOverlays = new BakedModel[9];
        BakedModel[] topOverlays = new BakedModel[9];
        BakedModel[] bottomOverlays = new BakedModel[9];
        northOverlays[0] = northMiddleOverlay.bake(modelBaker, function, modelState);
        northOverlays[1] = northTopMiddleOverlay.bake(modelBaker, function, modelState);
        northOverlays[2] = northTopLeftOverlay.bake(modelBaker, function, modelState);
        northOverlays[3] = northTopRightOverlay.bake(modelBaker, function, modelState);
        northOverlays[4] = northBottomMiddleOverlay.bake(modelBaker, function, modelState);
        northOverlays[5] = northBottomLeftOverlay.bake(modelBaker, function, modelState);
        northOverlays[6] = northBottomRightOverlay.bake(modelBaker, function, modelState);
        northOverlays[7] = northLeftMiddleOverlay.bake(modelBaker, function, modelState);
        northOverlays[8] = northRightMiddleOverlay.bake(modelBaker, function, modelState);
        eastOverlays[0] = eastMiddleOverlay.bake(modelBaker, function, modelState);
        eastOverlays[1] = eastTopMiddleOverlay.bake(modelBaker, function, modelState);
        eastOverlays[2] = eastTopLeftOverlay.bake(modelBaker, function, modelState);
        eastOverlays[3] = eastTopRightOverlay.bake(modelBaker, function, modelState);
        eastOverlays[4] = eastBottomMiddleOverlay.bake(modelBaker, function, modelState);
        eastOverlays[5] = eastBottomLeftOverlay.bake(modelBaker, function, modelState);
        eastOverlays[6] = eastBottomRightOverlay.bake(modelBaker, function, modelState);
        eastOverlays[7] = eastLeftMiddleOverlay.bake(modelBaker, function, modelState);
        eastOverlays[8] = eastRightMiddleOverlay.bake(modelBaker, function, modelState);
        southOverlays[0] = southMiddleOverlay.bake(modelBaker, function, modelState);
        southOverlays[1] = southTopMiddleOverlay.bake(modelBaker, function, modelState);
        southOverlays[2] = southTopLeftOverlay.bake(modelBaker, function, modelState);
        southOverlays[3] = southTopRightOverlay.bake(modelBaker, function, modelState);
        southOverlays[4] = southBottomMiddleOverlay.bake(modelBaker, function, modelState);
        southOverlays[5] = southBottomLeftOverlay.bake(modelBaker, function, modelState);
        southOverlays[6] = southBottomRightOverlay.bake(modelBaker, function, modelState);
        southOverlays[7] = southLeftMiddleOverlay.bake(modelBaker, function, modelState);
        southOverlays[8] = southRightMiddleOverlay.bake(modelBaker, function, modelState);
        westOverlays[0] = westMiddleOverlay.bake(modelBaker, function, modelState);
        westOverlays[1] = westTopMiddleOverlay.bake(modelBaker, function, modelState);
        westOverlays[2] = westTopLeftOverlay.bake(modelBaker, function, modelState);
        westOverlays[3] = westTopRightOverlay.bake(modelBaker, function, modelState);
        westOverlays[4] = westBottomMiddleOverlay.bake(modelBaker, function, modelState);
        westOverlays[5] = westBottomLeftOverlay.bake(modelBaker, function, modelState);
        westOverlays[6] = westBottomRightOverlay.bake(modelBaker, function, modelState);
        westOverlays[7] = westLeftMiddleOverlay.bake(modelBaker, function, modelState);
        westOverlays[8] = westRightMiddleOverlay.bake(modelBaker, function, modelState);
        topOverlays[0] = topMiddleOverlay.bake(modelBaker, function, modelState);
        topOverlays[1] = topTopMiddleOverlay.bake(modelBaker, function, modelState);
        topOverlays[2] = topTopLeftOverlay.bake(modelBaker, function, modelState);
        topOverlays[3] = topTopRightOverlay.bake(modelBaker, function, modelState);
        topOverlays[4] = topBottomMiddleOverlay.bake(modelBaker, function, modelState);
        topOverlays[5] = topBottomLeftOverlay.bake(modelBaker, function, modelState);
        topOverlays[6] = topBottomRightOverlay.bake(modelBaker, function, modelState);
        topOverlays[7] = topLeftMiddleOverlay.bake(modelBaker, function, modelState);
        topOverlays[8] = topRightMiddleOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[0] = bottomMiddleOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[1] = bottomTopMiddleOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[2] = bottomTopLeftOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[3] = bottomTopRightOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[4] = bottomBottomMiddleOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[5] = bottomBottomLeftOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[6] = bottomBottomRightOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[7] = bottomLeftMiddleOverlay.bake(modelBaker, function, modelState);
        bottomOverlays[8] = bottomRightMiddleOverlay.bake(modelBaker, function, modelState);
        return new GlazedModel(core.bake(modelBaker, function, modelState), northOverlays, eastOverlays, southOverlays, westOverlays, topOverlays, bottomOverlays);
    }

    @Override
    public void resolveDependencies(UnbakedModel.Resolver modelGetter, IGeometryBakingContext context) {
        core.resolveDependencies(modelGetter);
    }
}