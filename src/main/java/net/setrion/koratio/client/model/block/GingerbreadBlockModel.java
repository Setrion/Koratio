package net.setrion.koratio.client.model.block;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.setrion.koratio.world.level.block.entity.GingerbreadBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GingerbreadBlockModel extends BakedModelWrapper<BakedModel> {
    private static final Map<CacheKey, List<BakedQuad>> MODEL_CACHE = new ConcurrentHashMap<>();
    private final BakedModel[] northOverlays;
    private final BakedModel[] eastOverlays;
    private final BakedModel[] southOverlays;
    private final BakedModel[] westOverlays;
    private final BakedModel[] topOverlays;
    private final BakedModel[] bottomOverlays;

    public GingerbreadBlockModel(BakedModel core, BakedModel[] northOverlays, BakedModel[] eastOverlays, BakedModel[] southOverlays, BakedModel[] westOverlays, BakedModel[] topOverlays, BakedModel[] bottomOverlays) {
        super(core);
        this.northOverlays = northOverlays;
        this.eastOverlays = eastOverlays;
        this.southOverlays = southOverlays;
        this.westOverlays = westOverlays;
        this.topOverlays = topOverlays;
        this.bottomOverlays = bottomOverlays;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction facing, RandomSource randomSource, ModelData modelData, @Nullable RenderType renderType) {
        List<BakedQuad> cachedQuads = new ArrayList<>();
        if (cachedQuads != null) {
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_LEFT))) {
                cachedQuads.addAll(northOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_RIGHT))) {
                cachedQuads.addAll(northOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_TOP))) {
                cachedQuads.addAll(northOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_BOTTOM))) {
                cachedQuads.addAll(northOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_MIDDLE))) {
                cachedQuads.addAll(northOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_EAST_LEFT))) {
                cachedQuads.addAll(eastOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_EAST_RIGHT))) {
                cachedQuads.addAll(eastOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_EAST_TOP))) {
                cachedQuads.addAll(eastOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_EAST_BOTTOM))) {
                cachedQuads.addAll(eastOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_EAST_MIDDLE))) {
                cachedQuads.addAll(eastOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_SOUTH_LEFT))) {
                cachedQuads.addAll(southOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_SOUTH_RIGHT))) {
                cachedQuads.addAll(southOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_SOUTH_TOP))) {
                cachedQuads.addAll(southOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_SOUTH_BOTTOM))) {
                cachedQuads.addAll(southOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_SOUTH_MIDDLE))) {
                cachedQuads.addAll(southOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_WEST_LEFT))) {
                cachedQuads.addAll(westOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_WEST_RIGHT))) {
                cachedQuads.addAll(westOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_WEST_TOP))) {
                cachedQuads.addAll(westOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_WEST_BOTTOM))) {
                cachedQuads.addAll(westOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_WEST_MIDDLE))) {
                cachedQuads.addAll(westOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_TOP_LEFT))) {
                cachedQuads.addAll(topOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_TOP_RIGHT))) {
                cachedQuads.addAll(topOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_TOP_TOP))) {
                cachedQuads.addAll(topOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_TOP_BOTTOM))) {
                cachedQuads.addAll(topOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_TOP_MIDDLE))) {
                cachedQuads.addAll(topOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_BOTTOM_LEFT))) {
                cachedQuads.addAll(bottomOverlays[0].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_BOTTOM_RIGHT))) {
                cachedQuads.addAll(bottomOverlays[1].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_BOTTOM_TOP))) {
                cachedQuads.addAll(bottomOverlays[2].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_BOTTOM_BOTTOM))) {
                cachedQuads.addAll(bottomOverlays[3].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            if (Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_BOTTOM_MIDDLE))) {
                cachedQuads.addAll(bottomOverlays[4].getQuads(blockState, facing, randomSource, modelData, renderType));
            }
            //MODEL_CACHE.put(key, cachedQuads);
        }
        cachedQuads.addAll(this.originalModel.getQuads(blockState, facing, randomSource, modelData, renderType));
        return cachedQuads;
    }

    private CacheKey cacheModel(int hash, ModelData modelData) {
        return new CacheKey(hash, Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_LEFT)), Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_RIGHT)), Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_TOP)), Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_BOTTOM)), Boolean.TRUE.equals(modelData.get(GingerbreadBlockEntity.GLAZED_NORTH_MIDDLE)));
    }

    private record CacheKey(int modelHash, boolean... beProps) {
    }
}