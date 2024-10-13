package net.setrion.koratio.client.model.block;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;
import org.joml.Vector3f;

import java.util.function.Function;

public record GingerbreadBlockGeometry(String name, BlockModel core, BlockModel topTopOverlay, BlockModel topBottomOverlay, BlockModel topLeftOverlay, BlockModel topRightOverlay, BlockModel topMiddleOverlay,
                                       BlockModel bottomTopOverlay, BlockModel bottomBottomOverlay, BlockModel bottomLeftOverlay, BlockModel bottomRightOverlay, BlockModel bottomMiddleOverlay,
                                       BlockModel northTopOverlay, BlockModel northBottomOverlay, BlockModel northLeftOverlay, BlockModel northRightOverlay, BlockModel northMiddleOverlay,
                                       BlockModel eastTopOverlay, BlockModel eastBottomOverlay, BlockModel eastLeftOverlay, BlockModel eastRightOverlay, BlockModel eastMiddleOverlay,
                                       BlockModel southTopOverlay, BlockModel southBottomOverlay, BlockModel southLeftOverlay, BlockModel southRightOverlay, BlockModel southMiddleOverlay,
                                       BlockModel westTopOverlay, BlockModel westBottomOverlay, BlockModel westLeftOverlay, BlockModel westRightOverlay, BlockModel westMiddleOverlay) implements IUnbakedGeometry<GingerbreadBlockGeometry> {
    private static final Vector3f BLOCK_CENTER = new Vector3f(0.5f, 0.5f, 0.5f);

    private static final BlockModelRotation[] ROTATIONS = new BlockModelRotation[] {
            BlockModelRotation.X180_Y0,
            BlockModelRotation.X0_Y0,
            BlockModelRotation.X90_Y0,
            BlockModelRotation.X90_Y180,
            BlockModelRotation.X90_Y270,
            BlockModelRotation.X90_Y90
    };

    @Override
    public BakedModel bake(IGeometryBakingContext iGeometryBakingContext, ModelBaker modelBaker, Function<Material, TextureAtlasSprite> function, ModelState modelState, ItemOverrides itemOverrides) {
        BakedModel[] northOverlays = new BakedModel[5];
        BakedModel[] eastOverlays = new BakedModel[5];
        BakedModel[] southOverlays = new BakedModel[5];
        BakedModel[] westOverlays = new BakedModel[5];
        BakedModel[] topOverlays = new BakedModel[5];
        BakedModel[] bottomOverlays = new BakedModel[5];
        northOverlays[0] = northLeftOverlay.bake(modelBaker, northLeftOverlay, function, modelState, true);
        northOverlays[1] = northRightOverlay.bake(modelBaker, northRightOverlay, function, modelState, true);
        northOverlays[2] = northTopOverlay.bake(modelBaker, northTopOverlay, function, modelState, true);
        northOverlays[3] = northBottomOverlay.bake(modelBaker, northBottomOverlay, function, modelState, true);
        northOverlays[4] = northMiddleOverlay.bake(modelBaker, northMiddleOverlay, function, modelState, true);
        eastOverlays[0] = eastLeftOverlay.bake(modelBaker, eastLeftOverlay, function, modelState, true);
        eastOverlays[1] = eastRightOverlay.bake(modelBaker, eastRightOverlay, function, modelState, true);
        eastOverlays[2] = eastTopOverlay.bake(modelBaker, eastTopOverlay, function, modelState, true);
        eastOverlays[3] = eastBottomOverlay.bake(modelBaker, eastBottomOverlay, function, modelState, true);
        eastOverlays[4] = eastMiddleOverlay.bake(modelBaker, eastMiddleOverlay, function, modelState, true);
        southOverlays[0] = southLeftOverlay.bake(modelBaker, southLeftOverlay, function, modelState, true);
        southOverlays[1] = southRightOverlay.bake(modelBaker, southRightOverlay, function, modelState, true);
        southOverlays[2] = southTopOverlay.bake(modelBaker, southTopOverlay, function, modelState, true);
        southOverlays[3] = southBottomOverlay.bake(modelBaker, southBottomOverlay, function, modelState, true);
        southOverlays[4] = southMiddleOverlay.bake(modelBaker, southMiddleOverlay, function, modelState, true);
        westOverlays[0] = westLeftOverlay.bake(modelBaker, westLeftOverlay, function, modelState, true);
        westOverlays[1] = westRightOverlay.bake(modelBaker, westRightOverlay, function, modelState, true);
        westOverlays[2] = westTopOverlay.bake(modelBaker, westTopOverlay, function, modelState, true);
        westOverlays[3] = westBottomOverlay.bake(modelBaker, westBottomOverlay, function, modelState, true);
        westOverlays[4] = westMiddleOverlay.bake(modelBaker, westMiddleOverlay, function, modelState, true);
        topOverlays[0] = topLeftOverlay.bake(modelBaker, topLeftOverlay, function, modelState, true);
        topOverlays[1] = topRightOverlay.bake(modelBaker, topRightOverlay, function, modelState, true);
        topOverlays[2] = topTopOverlay.bake(modelBaker, topTopOverlay, function, modelState, true);
        topOverlays[3] = topBottomOverlay.bake(modelBaker, topBottomOverlay, function, modelState, true);
        topOverlays[4] = topMiddleOverlay.bake(modelBaker, topMiddleOverlay, function, modelState, true);
        bottomOverlays[0] = bottomLeftOverlay.bake(modelBaker, bottomLeftOverlay, function, modelState, true);
        bottomOverlays[1] = bottomRightOverlay.bake(modelBaker, bottomRightOverlay, function, modelState, true);
        bottomOverlays[2] = bottomTopOverlay.bake(modelBaker, bottomTopOverlay, function, modelState, true);
        bottomOverlays[3] = bottomBottomOverlay.bake(modelBaker, bottomBottomOverlay, function, modelState, true);
        bottomOverlays[4] = bottomMiddleOverlay.bake(modelBaker, bottomMiddleOverlay, function, modelState, true);
        return new GingerbreadBlockModel(core.bake(modelBaker, core, function, modelState, true), northOverlays, eastOverlays, southOverlays, westOverlays, topOverlays, bottomOverlays);
    }

    @Override
    public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
        core.resolveParents(modelGetter);
    }
}