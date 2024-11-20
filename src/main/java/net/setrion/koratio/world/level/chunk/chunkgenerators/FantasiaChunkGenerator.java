package net.setrion.koratio.world.level.chunk.chunkgenerators;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.world.level.biome.BiomeSourceBase;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.KoratioBlendedNoise;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.KoratioChunkWarp;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.NoiseModifier;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.NoiseSlider;

import java.util.Optional;

public class FantasiaChunkGenerator extends KoratioChunkGenerator {
	public static final MapCodec<FantasiaChunkGenerator> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
			ChunkGenerator.CODEC.fieldOf("wrapped_generator").forGetter(o -> o.delegate),
			NoiseGeneratorSettings.CODEC.fieldOf("noise_generation_settings").forGetter(o -> o.noiseGeneratorSettings)
	).apply(instance, FantasiaChunkGenerator::new));

	private final Holder<NoiseGeneratorSettings> noiseGeneratorSettings;
	
	public FantasiaChunkGenerator(ChunkGenerator delegate, Holder<NoiseGeneratorSettings> noiseGenSettings) {
		super(delegate, noiseGenSettings);

		this.noiseGeneratorSettings = noiseGenSettings;

		if (delegate instanceof NoiseBasedChunkGenerator noiseGen && noiseGen.generatorSettings().isBound()) {
			this.defaultBlock = noiseGen.generatorSettings().value().defaultBlock();
			this.defaultFluid = noiseGen.generatorSettings().value().defaultFluid();
		} else {
			this.defaultBlock = Blocks.STONE.defaultBlockState();
			this.defaultFluid = Blocks.WATER.defaultBlockState();
		}
		if (noiseGenSettings.isBound()) {
			NoiseSettings settings = noiseGenSettings.value().noiseSettings();
			if (delegate.getBiomeSource() instanceof BiomeSourceBase source) {
				WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
				KoratioBlendedNoise blendedNoise = new KoratioBlendedNoise(random);
				NoiseModifier modifier = NoiseModifier.PASS;
				this.warper = Optional.of(new KoratioChunkWarp(settings.getCellWidth(), settings.getCellHeight(), settings.height() / settings.getCellHeight(), source, new NoiseSlider(-10.0D, 3, 0), new NoiseSlider(63.0D, 3, 0), settings, blendedNoise, modifier));
			} else {
				this.warper = Optional.empty();
			}
		} else {
			this.warper = Optional.empty();
		}
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return KoratioDimensions.FANTASIA_CHUNK_GENERATOR.get();
	}
}