package net.setrion.koratio.client.model.block;

import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public enum GlazedModelLoader implements IGeometryLoader<GlazedBlockGeometry> {
    INSTANCE;

    @Override
    public GlazedBlockGeometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
        BlockModel core = loadModel(ResourceLocation.parse(jsonObject.get("base").getAsString()));
        BlockModel topTopOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_top_top"));
        BlockModel topBottomOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_top_bottom"));
        BlockModel topLeftOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_top_left"));
        BlockModel topRightOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_top_right"));
        BlockModel topMiddleOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_top_middle"));
        BlockModel bottomTopOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_bottom_top"));
        BlockModel bottomBottomOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_bottom_bottom"));
        BlockModel bottomLeftOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_bottom_left"));
        BlockModel bottomRightOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_bottom_right"));
        BlockModel bottomMiddleOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_bottom_middle"));
        BlockModel northTopOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_north_top"));
        BlockModel northBottomOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_north_bottom"));
        BlockModel northLeftOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_north_left"));
        BlockModel northRightOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_north_right"));
        BlockModel northMiddleOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_north_middle"));
        BlockModel eastTopOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_east_top"));
        BlockModel eastBottomOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_east_bottom"));
        BlockModel eastLeftOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_east_left"));
        BlockModel eastRightOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_east_right"));
        BlockModel eastMiddleOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_east_middle"));
        BlockModel southTopOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_south_top"));
        BlockModel southBottomOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_south_bottom"));
        BlockModel southLeftOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_south_left"));
        BlockModel southRightOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_south_right"));
        BlockModel southMiddleOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_south_middle"));
        BlockModel westTopOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_west_top"));
        BlockModel westBottomOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_west_bottom"));
        BlockModel westLeftOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_west_left"));
        BlockModel westRightOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_west_right"));
        BlockModel westMiddleOverlay = loadModel(Koratio.prefix("block/overlay/gingerbread_glaze_west_middle"));

        return new GlazedBlockGeometry("gingerbread", core, topTopOverlay, topBottomOverlay, topLeftOverlay, topRightOverlay, topMiddleOverlay, bottomTopOverlay, bottomBottomOverlay, bottomLeftOverlay, bottomRightOverlay, bottomMiddleOverlay, northTopOverlay, northBottomOverlay, northLeftOverlay, northRightOverlay, northMiddleOverlay, eastTopOverlay, eastBottomOverlay, eastLeftOverlay, eastRightOverlay, eastMiddleOverlay, southTopOverlay, southBottomOverlay, southLeftOverlay, southRightOverlay, southMiddleOverlay, westTopOverlay, westBottomOverlay, westLeftOverlay, westRightOverlay, westMiddleOverlay);
    }

    private static BlockModel loadModel(ResourceLocation location) {
        ResourceManager manager = Minecraft.getInstance().getResourceManager();
        ResourceLocation file = ModelBakery.MODEL_LISTER.idToFile(location);
        try (InputStream stream = manager.getResourceOrThrow(file).open()) {
            return BlockModel.fromStream(new InputStreamReader(stream));
        } catch (IOException e) {
            throw new JsonParseException("Failed to load part model '" + file + "'", e);
        }
    }

    public static class GingerbreadBlockModelLoaderBuilder extends CustomLoaderBuilder<BlockModelBuilder> {
        public GingerbreadBlockModelLoaderBuilder(BlockModelBuilder parent, ExistingFileHelper existingFileHelper) {
            super(
                    Koratio.prefix("glazed"),
                    parent,
                    existingFileHelper,
                    false
            );
        }

        private ResourceLocation baseModel;

        public ResourceLocation getBaseModel() {
            return baseModel;
        }

        public void setBaseModel(ResourceLocation baseModel) {
            this.baseModel = baseModel;
        }

        @Override
        public JsonObject toJson(JsonObject json) {
            json.add("base", new JsonPrimitive(baseModel.toString()));
            return super.toJson(json);
        }
    }
}