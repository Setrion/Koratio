package net.setrion.koratio.client.model.block;

import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.client.model.UnbakedModelLoader;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public enum GlazedModelLoader implements UnbakedModelLoader<GlazedBlockGeometry> {
    INSTANCE;

    @Override
    public GlazedBlockGeometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
        String name = jsonObject.get("base").getAsString();
        BlockModel core = loadModel(ResourceLocation.parse(jsonObject.get("base").getAsString()));
        BlockModel topTopMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_top_middle"));
        BlockModel topTopLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_top_left"));
        BlockModel topTopRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_top_right"));
        BlockModel topBottomMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_bottom_middle"));
        BlockModel topBottomLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_bottom_left"));
        BlockModel topBottomRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_bottom_right"));
        BlockModel topLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_left_middle"));
        BlockModel topRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_right_middle"));
        BlockModel topMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_top_middle"));
        BlockModel bottomTopMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_top_middle"));
        BlockModel bottomTopLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_top_left"));
        BlockModel bottomTopRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_top_right"));
        BlockModel bottomBottomMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_bottom_middle"));
        BlockModel bottomBottomLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_bottom_left"));
        BlockModel bottomBottomRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_bottom_right"));
        BlockModel bottomLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_left_middle"));
        BlockModel bottomRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_right_middle"));
        BlockModel bottomMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_bottom_middle"));
        BlockModel northTopMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_top_middle"));
        BlockModel northTopLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_top_left"));
        BlockModel northTopRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_top_right"));
        BlockModel northBottomMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_bottom_middle"));
        BlockModel northBottomLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_bottom_left"));
        BlockModel northBottomRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_bottom_right"));
        BlockModel northLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_left_middle"));
        BlockModel northRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_right_middle"));
        BlockModel northMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_north_middle"));
        BlockModel eastTopMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_top_middle"));
        BlockModel eastTopLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_top_left"));
        BlockModel eastTopRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_top_right"));
        BlockModel eastBottomMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_bottom_middle"));
        BlockModel eastBottomLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_bottom_left"));
        BlockModel eastBottomRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_bottom_right"));
        BlockModel eastLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_left_middle"));
        BlockModel eastRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_right_middle"));
        BlockModel eastMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_east_middle"));
        BlockModel southTopMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_top_middle"));
        BlockModel southTopLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_top_left"));
        BlockModel southTopRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_top_right"));
        BlockModel southBottomMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_bottom_middle"));
        BlockModel southBottomLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_bottom_left"));
        BlockModel southBottomRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_bottom_right"));
        BlockModel southLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_left_middle"));
        BlockModel southRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_right_middle"));
        BlockModel southMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_south_middle"));
        BlockModel westTopMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_top_middle"));
        BlockModel westTopLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_top_left"));
        BlockModel westTopRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_top_right"));
        BlockModel westBottomMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_bottom_middle"));
        BlockModel westBottomLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_bottom_left"));
        BlockModel westBottomRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_bottom_right"));
        BlockModel westLeftOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_left_middle"));
        BlockModel westRightOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_right_middle"));
        BlockModel westMiddleOverlay = loadModel(Koratio.prefix("block/overlay/glaze_west_middle"));

        return new GlazedBlockGeometry(name, core, topTopMiddleOverlay, topTopLeftOverlay, topTopRightOverlay, topBottomMiddleOverlay, topBottomLeftOverlay, topBottomRightOverlay, topLeftOverlay, topRightOverlay, topMiddleOverlay, bottomTopMiddleOverlay, bottomTopLeftOverlay, bottomTopRightOverlay, bottomBottomMiddleOverlay, bottomBottomLeftOverlay, bottomBottomRightOverlay, bottomLeftOverlay, bottomRightOverlay, bottomMiddleOverlay, northTopMiddleOverlay, northTopLeftOverlay, northTopRightOverlay, northBottomMiddleOverlay,northBottomLeftOverlay, northBottomRightOverlay, northLeftOverlay, northRightOverlay, northMiddleOverlay, eastTopMiddleOverlay, eastTopLeftOverlay, eastTopRightOverlay, eastBottomMiddleOverlay, eastBottomLeftOverlay, eastBottomRightOverlay, eastLeftOverlay, eastRightOverlay, eastMiddleOverlay, southTopMiddleOverlay, southTopLeftOverlay, southTopRightOverlay, southBottomMiddleOverlay, southBottomLeftOverlay, southBottomRightOverlay, southLeftOverlay, southRightOverlay, southMiddleOverlay, westTopMiddleOverlay, westTopLeftOverlay, westTopRightOverlay, westBottomMiddleOverlay, westBottomLeftOverlay, westBottomRightOverlay, westLeftOverlay, westRightOverlay, westMiddleOverlay);
    }

    private static BlockModel loadModel(ResourceLocation location) {
        ResourceManager manager = Minecraft.getInstance().getResourceManager();
        FileToIdConverter converter = new FileToIdConverter("models", ".json");
        try (InputStream stream = manager.open(converter.idToFile(location))) {
            return GsonHelper.fromJson(BlockModel.GSON, new InputStreamReader(stream), BlockModel.class);
        } catch (IOException e) {
            throw new JsonParseException("Failed to load part model '" + location + "'", e);
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