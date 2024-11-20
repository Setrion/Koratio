package net.setrion.koratio.client.model.block;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.setrion.koratio.world.level.block.entity.GlazedBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlazedModel extends BakedModelWrapper<BakedModel> {
    private static final Map<CacheKey, List<BakedQuad>> MODEL_CACHE = new ConcurrentHashMap<>();

    private static BakedModel[] northOverlays;
    private static BakedModel[] eastOverlays;
    private static BakedModel[] southOverlays;
    private static BakedModel[] westOverlays;
    private static BakedModel[] topOverlays;
    private static BakedModel[] bottomOverlays;

    public GlazedModel(BakedModel core, BakedModel[] northOverlays, BakedModel[] eastOverlays, BakedModel[] southOverlays, BakedModel[] westOverlays, BakedModel[] topOverlays, BakedModel[] bottomOverlays) {
        super(core);
        GlazedModel.northOverlays = northOverlays;
        GlazedModel.eastOverlays = eastOverlays;
        GlazedModel.southOverlays = southOverlays;
        GlazedModel.westOverlays = westOverlays;
        GlazedModel.topOverlays = topOverlays;
        GlazedModel.bottomOverlays = bottomOverlays;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction facing, RandomSource randomSource, ModelData modelData, @Nullable RenderType renderType) {
        if (facing == null) {
            CacheKey key = cacheModel(modelData);
            List<BakedQuad> cachedQuads = MODEL_CACHE.get(key);
            if (cachedQuads == null) {
                cachedQuads = new ArrayList<>();
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_MIDDLE))) {
                    cachedQuads.addAll(northOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_TOP_MIDDLE))) {
                    cachedQuads.addAll(northOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_TOP_LEFT))) {
                    cachedQuads.addAll(northOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_TOP_RIGHT))) {
                    cachedQuads.addAll(northOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_MIDDLE))) {
                    cachedQuads.addAll(northOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_LEFT))) {
                    cachedQuads.addAll(northOverlays[5].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_RIGHT))) {
                    cachedQuads.addAll(northOverlays[6].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_LEFT))) {
                    cachedQuads.addAll(northOverlays[7].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_RIGHT))) {
                    cachedQuads.addAll(northOverlays[8].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_MIDDLE))) {
                    cachedQuads.addAll(eastOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_TOP_MIDDLE))) {
                    cachedQuads.addAll(eastOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_TOP_LEFT))) {
                    cachedQuads.addAll(eastOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_TOP_RIGHT))) {
                    cachedQuads.addAll(eastOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_BOTTOM_MIDDLE))) {
                    cachedQuads.addAll(eastOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_BOTTOM_LEFT))) {
                    cachedQuads.addAll(eastOverlays[5].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_BOTTOM_RIGHT))) {
                    cachedQuads.addAll(eastOverlays[6].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_LEFT))) {
                    cachedQuads.addAll(eastOverlays[7].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_RIGHT))) {
                    cachedQuads.addAll(eastOverlays[8].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_MIDDLE))) {
                    cachedQuads.addAll(southOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_TOP_MIDDLE))) {
                    cachedQuads.addAll(southOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_TOP_LEFT))) {
                    cachedQuads.addAll(southOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_TOP_RIGHT))) {
                    cachedQuads.addAll(southOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_MIDDLE))) {
                    cachedQuads.addAll(southOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_LEFT))) {
                    cachedQuads.addAll(southOverlays[5].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_RIGHT))) {
                    cachedQuads.addAll(southOverlays[6].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_LEFT))) {
                    cachedQuads.addAll(southOverlays[7].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_RIGHT))) {
                    cachedQuads.addAll(southOverlays[8].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_MIDDLE))) {
                    cachedQuads.addAll(westOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_TOP_MIDDLE))) {
                    cachedQuads.addAll(westOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_TOP_LEFT))) {
                    cachedQuads.addAll(westOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_TOP_RIGHT))) {
                    cachedQuads.addAll(westOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_BOTTOM_MIDDLE))) {
                    cachedQuads.addAll(westOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_BOTTOM_LEFT))) {
                    cachedQuads.addAll(westOverlays[5].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_BOTTOM_RIGHT))) {
                    cachedQuads.addAll(westOverlays[6].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_LEFT))) {
                    cachedQuads.addAll(westOverlays[7].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_RIGHT))) {
                    cachedQuads.addAll(westOverlays[8].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_MIDDLE))) {
                    cachedQuads.addAll(topOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_TOP_MIDDLE))) {
                    cachedQuads.addAll(topOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_TOP_LEFT))) {
                    cachedQuads.addAll(topOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_TOP_RIGHT))) {
                    cachedQuads.addAll(topOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_BOTTOM_MIDDLE))) {
                    cachedQuads.addAll(topOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_BOTTOM_LEFT))) {
                    cachedQuads.addAll(topOverlays[5].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_BOTTOM_RIGHT))) {
                    cachedQuads.addAll(topOverlays[6].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_LEFT))) {
                    cachedQuads.addAll(topOverlays[7].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_RIGHT))) {
                    cachedQuads.addAll(topOverlays[8].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_MIDDLE))) {
                    cachedQuads.addAll(bottomOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_TOP_MIDDLE))) {
                    cachedQuads.addAll(bottomOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_TOP_LEFT))) {
                    cachedQuads.addAll(bottomOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_TOP_RIGHT))) {
                    cachedQuads.addAll(bottomOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_MIDDLE))) {
                    cachedQuads.addAll(bottomOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_LEFT))) {
                    cachedQuads.addAll(bottomOverlays[5].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_RIGHT))) {
                    cachedQuads.addAll(bottomOverlays[6].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_LEFT))) {
                    cachedQuads.addAll(bottomOverlays[7].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                if (Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_RIGHT))) {
                    cachedQuads.addAll(bottomOverlays[8].getQuads(blockState, facing, randomSource, modelData, renderType));
                }
                cachedQuads.addAll(this.originalModel.getQuads(blockState, facing, randomSource, modelData, renderType));
                MODEL_CACHE.put(key, cachedQuads);
            }
            return cachedQuads;
        } else {
            List<BakedQuad> quads = new ArrayList<>();
            quads.addAll(this.originalModel.getQuads(blockState, facing, randomSource, modelData, renderType));
            return quads;
        }
    }

    private CacheKey cacheModel(ModelData modelData) {
        boolean[] boolData = new boolean[54];
        boolData[0] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_MIDDLE));
        boolData[1] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_TOP_MIDDLE));
        boolData[2] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_TOP_LEFT));
        boolData[3] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_TOP_RIGHT));
        boolData[4] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_MIDDLE));
        boolData[5] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_LEFT));
        boolData[6] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_RIGHT));
        boolData[7] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_LEFT));
        boolData[8] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_NORTH_RIGHT));
        boolData[9] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_MIDDLE));
        boolData[10] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_TOP_MIDDLE));
        boolData[11] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_TOP_LEFT));
        boolData[12] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_TOP_RIGHT));
        boolData[13] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_BOTTOM_MIDDLE));
        boolData[14] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_BOTTOM_LEFT));
        boolData[15] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_BOTTOM_RIGHT));
        boolData[16] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_LEFT));
        boolData[17] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_EAST_RIGHT));
        boolData[18] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_MIDDLE));
        boolData[19] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_TOP_MIDDLE));
        boolData[20] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_TOP_LEFT));
        boolData[21] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_TOP_RIGHT));
        boolData[22] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_MIDDLE));
        boolData[23] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_LEFT));
        boolData[24] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_RIGHT));
        boolData[25] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_LEFT));
        boolData[26] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_SOUTH_RIGHT));
        boolData[27] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_MIDDLE));
        boolData[28] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_TOP_MIDDLE));
        boolData[29] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_TOP_LEFT));
        boolData[30] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_TOP_RIGHT));
        boolData[31] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_BOTTOM_MIDDLE));
        boolData[32] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_BOTTOM_LEFT));
        boolData[33] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_BOTTOM_RIGHT));
        boolData[34] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_LEFT));
        boolData[35] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_WEST_RIGHT));
        boolData[36] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_MIDDLE));
        boolData[37] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_TOP_MIDDLE));
        boolData[38] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_TOP_LEFT));
        boolData[39] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_TOP_RIGHT));
        boolData[40] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_BOTTOM_MIDDLE));
        boolData[41] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_BOTTOM_LEFT));
        boolData[42] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_BOTTOM_RIGHT));
        boolData[43] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_LEFT));
        boolData[44] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_TOP_RIGHT));
        boolData[45] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_MIDDLE));
        boolData[46] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_TOP_MIDDLE));
        boolData[47] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_TOP_LEFT));
        boolData[48] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_TOP_RIGHT));
        boolData[49] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_MIDDLE));
        boolData[50] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_LEFT));
        boolData[51] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_RIGHT));
        boolData[52] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_LEFT));
        boolData[53] = Boolean.TRUE.equals(modelData.get(GlazedBlockEntity.GLAZED_BOTTOM_RIGHT));

        long hash = makeIntKey(boolData);
        return new CacheKey(hash);
    }

    long makeIntKey(boolean[] bools) {
        assert(bools.length <= 54);
        long result = 0;
        for (int i = 0; i < bools.length; i++) {
            if (bools[i]) result |= 1L << i;
        }
        return result;
    }

    private record CacheKey(long modelHash) {
    }

    public static BakedModel[] getNorthOverlays() {
        return GlazedModel.northOverlays;
    }

    public static BakedModel[] getEastOverlays() {
        return GlazedModel.eastOverlays;
    }

    public static BakedModel[] getSouthOverlays() {
        return GlazedModel.southOverlays;
    }

    public static BakedModel[] getWestOverlays() {
        return GlazedModel.westOverlays;
    }

    public static BakedModel[] getTopOverlays() {
        return GlazedModel.topOverlays;
    }

    public static BakedModel[] getBottomOverlays() {
        return GlazedModel.bottomOverlays;
    }
}