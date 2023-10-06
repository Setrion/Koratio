package net.setrion.koratio.world.level.biome;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.world.level.levelgen.carver.TerrainColumn;
import net.setrion.koratio.world.level.levelgen.layer.GenLayerBiomeStabilize;
import net.setrion.koratio.world.level.levelgen.layer.GenLayerFantasiaBiomes;
import net.setrion.koratio.world.level.levelgen.layer.GenLayerRiverMix;
import net.setrion.koratio.world.level.levelgen.layer.GenLayerFantasiaRiver;
import net.setrion.koratio.world.level.levelgen.vanilla.Layer;
import net.setrion.koratio.world.level.levelgen.vanilla.SmoothLayer;
import net.setrion.koratio.world.level.levelgen.vanilla.ZoomLayer;
import net.setrion.koratio.world.level.levelgen.vanilla.area.Area;
import net.setrion.koratio.world.level.levelgen.vanilla.area.AreaFactory;
import net.setrion.koratio.world.level.levelgen.vanilla.area.LazyArea;
import net.setrion.koratio.world.level.levelgen.vanilla.context.BigContext;
import net.setrion.koratio.world.level.levelgen.vanilla.context.LazyAreaContext;

public class FantasiaBiomeProvider extends BiomeSourceBase {
	public static final Codec<FantasiaBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			RegistryOps.retrieveGetter(Registries.BIOME),
			TerrainColumn.CODEC.listOf().fieldOf("biome_landscape").xmap(l -> l.stream().collect(Collectors.toMap(TerrainColumn::getResourceKey, Function.identity())), m -> List.copyOf(m.values())).forGetter(o -> o.biomeList),
			Codec.FLOAT.fieldOf("base_offset").forGetter(o -> o.baseOffset),
			Codec.FLOAT.fieldOf("base_factor").forGetter(o -> o.baseFactor)
	).apply(instance, instance.stable(FantasiaBiomeProvider::new)));

	private final HolderGetter<Biome> registry;
	private final Map<ResourceKey<Biome>, TerrainColumn> biomeList;
	private Layer genBiomes;
	private final float baseOffset;
	private final float baseFactor;

	public FantasiaBiomeProvider(HolderGetter<Biome> registryIn, List<TerrainColumn> list, float offset, float factor) {
		this(registryIn, list.stream().collect(Collectors.toMap(TerrainColumn::getResourceKey, Function.identity())), offset, factor);
	}

	public FantasiaBiomeProvider(HolderGetter<Biome> registryIn, Map<ResourceKey<Biome>, TerrainColumn> list, float offset, float factor) {
		super();

		this.baseOffset = offset;
		this.baseFactor = factor;

		this.registry = registryIn;
		this.biomeList = list;
	}

	public static int getBiomeId(ResourceKey<Biome> biome, HolderGetter<Biome> registry) {
		return ServerLifecycleHooks.getCurrentServer().registryAccess().registryOrThrow(Registries.BIOME).getId(registry.get(biome).get().get());
	}

	private static <T extends Area, C extends BigContext<T>> AreaFactory<T> makeLayers(LongFunction<C> seed, HolderGetter<Biome> registry, long rawSeed) {
 		AreaFactory<T> biomes = GenLayerFantasiaBiomes.INSTANCE.setup(registry).run(seed.apply(1L));

		biomes = ZoomLayer.NORMAL.run(seed.apply(1000L), biomes);
		biomes = ZoomLayer.NORMAL.run(seed.apply(1001L), biomes);

		biomes = GenLayerBiomeStabilize.INSTANCE.run(seed.apply(700L), biomes);

		biomes = ZoomLayer.NORMAL.run(seed.apply(1002), biomes);
		biomes = ZoomLayer.NORMAL.run(seed.apply(1003), biomes);
		biomes = ZoomLayer.NORMAL.run(seed.apply(1004), biomes);
		biomes = ZoomLayer.NORMAL.run(seed.apply(1005), biomes);

		AreaFactory<T> riverLayer = GenLayerFantasiaRiver.INSTANCE.setup(registry).run(seed.apply(1L), biomes);
		riverLayer = SmoothLayer.INSTANCE.run(seed.apply(7000L), riverLayer);
		biomes = GenLayerRiverMix.INSTANCE.setup(registry).run(seed.apply(100L), biomes, riverLayer);

		return biomes;
	}
	
	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return this.biomeList.values().stream().flatMap(TerrainColumn::getBiomes);
	}
	
	public static Layer makeLayers(long seed, HolderGetter<Biome> registry) {
		AreaFactory<LazyArea> areaFactory = makeLayers((context) -> new LazyAreaContext(25, seed, context), registry, seed);

		return new Layer(areaFactory) {
			@Override
			public Holder<Biome> get(HolderGetter<Biome> registry, int x, int y) {
				int i = this.area.get(x, y);
				Optional<Holder.Reference<Biome>> biome = ServerLifecycleHooks.getCurrentServer().registryAccess().registryOrThrow(Registries.BIOME).getHolder(i);
				if (biome.isEmpty())
					throw new IllegalStateException("Unknown biome id emitted by layers: " + i);
				return biome.get();
			}
		};
	}


	@Override
	protected Codec<? extends BiomeSource> codec() {
		return CODEC;
	}

	public float getBaseOffset() {
		return this.baseOffset;
	}

	public float getBaseFactor() {
		return this.baseFactor;
	}

	public float getBiomeDepth(int x, int z) {
		lazyLoadGenBiomes();
		return this.getBiomeDepth(this.genBiomes.get(this.registry, x, z));
	}

	public float getBiomeDepth(Holder<Biome> biome) {
		lazyLoadGenBiomes();
		return this.getBiomeValue(biome, TerrainColumn::depth, 0f);
	}

	public Optional<TerrainColumn> getTerrainColumn(int x, int z) {
		return this.getTerrainColumn(this.genBiomes.get(this.registry, x, z));
	}

	public Optional<TerrainColumn> getTerrainColumn(Holder<Biome> biome) {
		return this.biomeList.values().stream().filter(p -> p.is(biome)).findFirst();
	}

	public <T> T getBiomeValue(Holder<Biome> biome, Function<TerrainColumn, T> function, T other) {
		return this.getTerrainColumn(biome).map(function).orElse(other);
	}

	@Override
	public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
		lazyLoadGenBiomes();
		Holder<Biome> columnBiome = this.genBiomes.get(this.registry, x, z);
		return this.biomeList.get(columnBiome.unwrapKey().get()).getBiome(y, columnBiome);
	}
	
	private void lazyLoadGenBiomes() {
		if (genBiomes == null)
			this.genBiomes = makeLayers(KoratioDimensions.seed, registry);
	}
}