package net.setrion.koratio.world.level.chunk.chunkgenerators.warp;

public interface NoiseModifier {
    NoiseModifier PASS = ((density, height, zWidth, xWidth) -> density);

    double modifyNoise(double density, int height, int zWidth, int xWidth);
}