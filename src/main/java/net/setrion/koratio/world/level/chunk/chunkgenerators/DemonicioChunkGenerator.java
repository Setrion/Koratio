package net.setrion.koratio.world.level.chunk.chunkgenerators;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.biome.BiomeSourceBase;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.KoratioBlendedNoise;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.KoratioChunkWarp;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.NoiseModifier;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.NoiseSlider;

public class DemonicioChunkGenerator extends KoratioChunkGenerator {
	public static final Codec<DemonicioChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			ChunkGenerator.CODEC.fieldOf("wrapped_generator").forGetter(o -> o.delegate),
			NoiseGeneratorSettings.CODEC.fieldOf("noise_generation_settings").forGetter(o -> o.noiseGeneratorSettings)
	).apply(instance, DemonicioChunkGenerator::new));

	private final Holder<NoiseGeneratorSettings> noiseGeneratorSettings;
	
	public DemonicioChunkGenerator(ChunkGenerator delegate, Holder<NoiseGeneratorSettings> noiseGenSettings) {
		super(delegate, noiseGenSettings);

		this.noiseGeneratorSettings = noiseGenSettings;

		if (delegate instanceof NoiseBasedChunkGenerator noiseGen && noiseGen.generatorSettings().isBound()) {
			this.defaultBlock = noiseGen.generatorSettings().get().defaultBlock();
			this.defaultFluid = noiseGen.generatorSettings().get().defaultFluid();
		} else {
			this.defaultBlock = KoratioBlocks.SOUL_STONE.get().defaultBlockState();
			this.defaultFluid = KoratioBlocks.BLOOD.get().defaultBlockState();
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
	public void buildSurface(ChunkAccess p_224262_, WorldGenerationContext p_224263_, RandomState p_224264_, StructureManager p_224265_, BiomeManager p_224266_, Registry<Biome> p_224267_, Blender p_224268_) {
		super.buildSurface(p_224262_, p_224263_, p_224264_, p_224265_, p_224266_, p_224267_, p_224268_);
	}
	
	@Override
	public void buildSurface(WorldGenRegion p_224232_, StructureManager p_224233_, RandomState p_224234_, ChunkAccess p_224235_) {
		super.buildSurface(p_224232_, p_224233_, p_224234_, p_224235_);
	}
	
	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}
}