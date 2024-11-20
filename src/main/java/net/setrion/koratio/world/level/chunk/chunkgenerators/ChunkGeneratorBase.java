package net.setrion.koratio.world.level.chunk.chunkgenerators;

import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

import java.util.concurrent.CompletableFuture;

public abstract class ChunkGeneratorBase extends ChunkGenerator {
    public final ChunkGenerator delegate;

    public ChunkGeneratorBase(ChunkGenerator delegate) {
        super(delegate.getBiomeSource());

        this.delegate = delegate;
    }

    @Override
    public void applyCarvers(WorldGenRegion region, long seed, RandomState random, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunkAccess) {
        this.delegate.applyCarvers(region, seed, random, biomeManager, structureManager, chunkAccess);
    }

    @Override
    public void buildSurface(WorldGenRegion level, StructureManager manager, RandomState random, ChunkAccess chunkAccess) {
        this.delegate.buildSurface(level, manager, random, chunkAccess);
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion region) {
        this.delegate.spawnOriginalMobs(region);
    }

    @Override
    public int getSpawnHeight(LevelHeightAccessor level) {
        return this.delegate.getSpawnHeight(level);
    }

    @Override
    public int getGenDepth() {
        return this.delegate.getGenDepth();
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunk) {
        return this.delegate.fillFromNoise(blender, randomState, structureManager, chunk);
    }

    @Override
    public int getSeaLevel() {
        return this.delegate.getSeaLevel();
    }

    @Override
    public int getMinY() {
        return this.delegate.getMinY();
    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types heightMap, LevelHeightAccessor level, RandomState random) {
        return this.delegate.getBaseHeight(x, z, heightMap, level, random);
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor level, RandomState random) {
        return this.delegate.getBaseColumn(x, z, level, random);
    }
}