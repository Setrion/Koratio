package net.setrion.koratio.world.level.biome;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.setrion.koratio.world.level.levelgen.carver.TerrainColumn;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class BiomeSourceBase extends BiomeSource {

	@Override
	protected MapCodec<? extends BiomeSource> codec() {
		return null;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return null;
	}

	public float getBaseOffset() {
		return 0.0F;
	}

	public float getBaseFactor() {
		return 0.0F;
	}

	public float getBiomeDepth(int x, int z) {
		return 0.0F;
	}

	public float getBiomeDepth(Holder<Biome> biome) {
		return 0.0F;
	}

	public Optional<TerrainColumn> getTerrainColumn(int x, int z) {
		return null;
	}

	public Optional<TerrainColumn> getTerrainColumn(Holder<Biome> biome) {
		return null;
	}

	public <T> T getBiomeValue(Holder<Biome> biome, Function<TerrainColumn, T> function, T other) {
		return null;
	}

	@Override
	public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
		return null;
	}
}