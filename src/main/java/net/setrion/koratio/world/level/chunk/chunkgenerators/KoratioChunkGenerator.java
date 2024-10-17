package net.setrion.koratio.world.level.chunk.chunkgenerators;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.world.level.chunk.chunkgenerators.warp.KoratioChunkWarp;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings({"deprecation"})
public abstract class KoratioChunkGenerator extends ChunkGeneratorBase {
	private final Holder<NoiseGeneratorSettings> noiseGeneratorSettings;

	public BlockState defaultBlock;
	public BlockState defaultFluid;
	public Optional<KoratioChunkWarp> warper;
	private final Supplier<Aquifer.FluidPicker> globalFluidPicker;

	public KoratioChunkGenerator(ChunkGenerator delegate, Holder<NoiseGeneratorSettings> noiseGenSettings) {
		super(delegate);

		this.noiseGeneratorSettings = noiseGenSettings;
		
		this.globalFluidPicker = Suppliers.memoize(() -> {
			return createFluidPicker(noiseGenSettings.value());
		});
	}

	private static Aquifer.FluidPicker createFluidPicker(NoiseGeneratorSettings p_249264_) {
		Aquifer.FluidStatus aquifer$fluidstatus = new Aquifer.FluidStatus(-54, Blocks.LAVA.defaultBlockState());
		int i = p_249264_.seaLevel();
		Aquifer.FluidStatus aquifer$fluidstatus1 = new Aquifer.FluidStatus(i, p_249264_.defaultFluid());
		return (p_224274_, p_224275_, p_224276_) -> {
			return p_224275_ < Math.min(-54, i) ? aquifer$fluidstatus : aquifer$fluidstatus1;
		};
	}

	@Override
	public int getBaseHeight(int x, int z, Heightmap.Types heightMap, LevelHeightAccessor level, RandomState random) {
		if (warper.isEmpty()) {
			return super.getBaseHeight(x, z, heightMap, level, random);
		} else {
			NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
			int minY = Math.max(settings.minY(), level.getMinBuildHeight());
			int maxY = Math.min(settings.minY() + settings.height(), level.getMaxBuildHeight());
			int minCell = Mth.floorDiv(minY, settings.getCellHeight());
			int maxCell = Mth.floorDiv(maxY - minY, settings.getCellHeight());
			return maxCell <= 0 ? level.getMinBuildHeight() : this.iterateNoiseColumn(random, x, z, null, heightMap.isOpaque(), minCell, maxCell).orElse(level.getMinBuildHeight());
		}
	}

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor level, RandomState random) {
		if (warper.isEmpty()) {
			return super.getBaseColumn(x, z, level, random);
		} else {
			NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
			int minY = Math.max(settings.minY(), level.getMinBuildHeight());
			int maxY = Math.min(settings.minY() + settings.height(), level.getMaxBuildHeight());
			int minCell = Mth.floorDiv(minY, settings.getCellHeight());
			int maxCell = Mth.floorDiv(maxY - minY, settings.getCellHeight());
			if (maxCell <= 0) {
				return new NoiseColumn(minY, new BlockState[0]);
			} else {
				BlockState[] ablockstate = new BlockState[maxCell * settings.getCellHeight()];
				this.iterateNoiseColumn(random, x, z, ablockstate, null, minCell, maxCell);
				return new NoiseColumn(minY, ablockstate);
			}
		}
	}

	protected OptionalInt iterateNoiseColumn(RandomState random, int x, int z, BlockState[] states, @Nullable Predicate<BlockState> predicate, int min, int max) {
		NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
		int cellWidth = settings.getCellWidth();
		int cellHeight = settings.getCellHeight();
		int xDiv = Math.floorDiv(x, cellWidth);
		int zDiv = Math.floorDiv(z, cellWidth);
		int xMod = Math.floorMod(x, cellWidth);
		int zMod = Math.floorMod(z, cellWidth);
		int xMin = xMod / cellWidth;
		int zMin = zMod / cellWidth;
		double[][] columns = new double[][] {
				this.makeAndFillNoiseColumn(random, xDiv, zDiv, min, max),
				this.makeAndFillNoiseColumn(random, xDiv, zDiv + 1, min, max),
				this.makeAndFillNoiseColumn(random, xDiv + 1, zDiv, min, max),
				this.makeAndFillNoiseColumn(random, xDiv + 1, zDiv + 1, min, max)
		};

		for (int cell = max - 1; cell >= 0; cell--) {
			double d00 = columns[0][cell];
			double d10 = columns[1][cell];
			double d20 = columns[2][cell];
			double d30 = columns[3][cell];
			double d01 = columns[0][cell + 1];
			double d11 = columns[1][cell + 1];
			double d21 = columns[2][cell + 1];
			double d31 = columns[3][cell + 1];

			for (int height = cellHeight - 1; height >= 0; height--) {
				double dcell = height / (double)cellHeight;
				double lcell = Mth.lerp3(dcell, xMin, zMin, d00, d01, d20, d21, d10, d11, d30, d31);
				int layer = cell * cellHeight + height;
				int maxlayer = layer + min * cellHeight;
				BlockState state = this.generateBaseState(lcell, layer);

				if (states != null) {
					states[layer] = state;
				}

				if (predicate != null && predicate.test(state)) {
					return OptionalInt.of(maxlayer + 1);
				}
			}
		}

		return OptionalInt.empty();
	}

	@Override
	public CompletableFuture<ChunkAccess> createBiomes(RandomState randomState, Blender blender, StructureManager structureManager, ChunkAccess chunkAccess) {
		return CompletableFuture.supplyAsync(Util.wrapThreadWithTaskName("init_biomes", () -> {
			chunkAccess.fillBiomesFromNoise(this.getBiomeSource(), Climate.empty());
			return chunkAccess;
		}), Util.backgroundExecutor());
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState random, StructureManager structureManager, ChunkAccess chunkAccess) {
		if (warper.isEmpty()) {
			return null;
		} else {
			NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
			int cellHeight = settings.getCellHeight();
			int minY = Math.max(settings.minY(), chunkAccess.getMinBuildHeight());
			int maxY = Math.min(settings.minY() + settings.height(), chunkAccess.getMaxBuildHeight());
			int mincell = Mth.floorDiv(minY, cellHeight);
			int maxcell = Mth.floorDiv(maxY - minY, cellHeight);

			if (maxcell <= 0) {
				return CompletableFuture.completedFuture(chunkAccess);
			} else {
				int maxIndex = chunkAccess.getSectionIndex(maxcell * cellHeight - 1 + minY);
				int minIndex = chunkAccess.getSectionIndex(minY);
				Set<LevelChunkSection> sections = Sets.newHashSet();

				for (int index = maxIndex; index >= minIndex; index--) {
					LevelChunkSection section = chunkAccess.getSection(index);
					section.acquire();
					sections.add(section);
				}

				return CompletableFuture.supplyAsync(() -> this.doFill(random, chunkAccess, mincell, maxcell), Util.backgroundExecutor()).whenCompleteAsync((chunk, throwable) -> {
					for (LevelChunkSection section : sections) {
						section.release();
					}
				});
			}
		}
	}

	private ChunkAccess doFill(RandomState random, ChunkAccess access, int min, int max) {
		NoiseSettings settings = noiseGeneratorSettings.value().noiseSettings();
		int cellWidth = settings.getCellWidth();
		int cellHeight = settings.getCellHeight();
		int cellCountX = 16 / cellWidth;
		int cellCountZ = 16 / cellWidth;
		Heightmap oceanfloor = access.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
		Heightmap surface = access.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
		ChunkPos chunkpos = access.getPos();
		int minX = chunkpos.getMinBlockX();
		int minZ = chunkpos.getMinBlockZ();
		NoiseInterpolator interpolator = new NoiseInterpolator(cellCountX, max, cellCountZ, chunkpos, min, this::fillNoiseColumn);
		List<NoiseInterpolator> list = Lists.newArrayList(interpolator);
		list.forEach(noiseint -> noiseint.initialiseFirstX(random));
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for (int cellX = 0; cellX < cellCountX; cellX++) {
			int advX = cellX;
			list.forEach((noiseint) -> noiseint.advanceX(random, advX));

			for (int cellZ = 0; cellZ < cellCountZ; cellZ++) {
				int count = access.getSectionsCount()-1;
				LevelChunkSection section = access.getSection(access.getSectionsCount() - 1);

				for (int cellY = max - 1; cellY >= 0; cellY--) {
					int advY = cellY;
					int advZ = cellZ;
					list.forEach((noiseint) -> noiseint.selectYZ(advY, advZ));

					for(int height = cellHeight - 1; height >= 0; height--) {
						int minheight = (min + cellY) * cellHeight + height;
						int mincellY = minheight & 15;
						int minindexY = access.getSectionIndex(minheight);
						if (count != minindexY) {
							section = access.getSection(minindexY);
						}

						double heightdiv = (double)height / (double)cellHeight;
						list.forEach((noiseint) -> noiseint.updateY(heightdiv));

						for (int widthX = 0; widthX < cellWidth; widthX++) {
							int minwidthX = minX + cellX * cellWidth + widthX;
							int mincellX = minwidthX & 15;
							double widthdivX = (double)widthX / (double)cellWidth;
							list.forEach((noiseint) -> noiseint.updateX(widthdivX));

							for (int widthZ = 0; widthZ < cellWidth; widthZ++) {
								int minwidthZ = minZ + cellZ * cellWidth + widthZ;
								int mincellZ = minwidthZ & 15;
								double widthdivZ = (double)widthZ / (double)cellWidth;
								double noiseval = interpolator.updateZ(widthdivZ);
								BlockState state = this.generateBaseState(noiseval, minheight);
								
								if (state != Blocks.AIR.defaultBlockState()) {
									if (state.getLightEmission() != 0 && access instanceof ProtoChunk proto) {
										mutable.set(minwidthX, minheight, minwidthZ);
										proto.initializeLightSources();
									}

									section.setBlockState(mincellX, mincellY, mincellZ, state, false);
									oceanfloor.update(mincellX, minheight, mincellZ, state);
									surface.update(mincellX, minheight, mincellZ, state);
								}
							}
						}
					}
				}
			}

			list.forEach(NoiseInterpolator::swapSlices);
		}
		
		return access;
	}

	private double[] makeAndFillNoiseColumn(RandomState state, int x, int z, int min, int max) {
		double[] columns = new double[max + 1];
		this.fillNoiseColumn(state, columns, x, z, min, max);
		return columns;
	}

	private void fillNoiseColumn(RandomState state, double[] columns, int x, int z, int min, int max) {
		this.warper.get().fillNoiseColumn(state, columns, x, z, this.getSeaLevel(), min, max);
	}

	private BlockState generateBaseState(double noiseVal, double level) {
		if (noiseVal > 0.0D) {
			return this.defaultBlock;
		} else if (level < this.getSeaLevel()) {
			return this.defaultFluid;
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	@Override
	public void buildSurface(WorldGenRegion region, StructureManager structure, RandomState randomstate, ChunkAccess access) {
		if (!SharedConstants.debugVoidTerrain(access.getPos())) {
			WorldGenerationContext worldgenerationcontext = new WorldGenerationContext(this, region);
			this.buildSurface(access, worldgenerationcontext, randomstate, structure, region.getBiomeManager(), region.registryAccess().registryOrThrow(Registries.BIOME), Blender.of(region));
		}
	}

	@VisibleForTesting
	public void buildSurface(ChunkAccess access, WorldGenerationContext context, RandomState randomstate, StructureManager structure, BiomeManager biomemanager, Registry<Biome> registry, Blender blender) {
		NoiseChunk noisechunk = access.getOrCreateNoiseChunk((p_224321_) -> {
			return this.createNoiseChunk(p_224321_, structure, blender, randomstate);
		});
		NoiseGeneratorSettings noisegeneratorsettings = this.noiseGeneratorSettings.value();
		randomstate.surfaceSystem().buildSurface(randomstate, biomemanager, registry, false, context, access, noisechunk, noisegeneratorsettings.surfaceRule());
	}
	
	private NoiseChunk createNoiseChunk(ChunkAccess access, StructureManager structure, Blender blender, RandomState randomstate) {
		return NoiseChunk.forChunk(access, randomstate, Beardifier.forStructuresInChunk(structure, access.getPos()), this.noiseGeneratorSettings.value(), this.globalFluidPicker.get(), blender);
	}

	@Override
	public void addDebugScreenInfo(List<String> p_223175_, RandomState p_223176_, BlockPos p_223177_) {
	}

	protected final BlockPos withY(BlockPos old, int y) {
		return new BlockPos(old.getX(), y, old.getZ());
	}

	static void forceHeightMapLevel(ChunkAccess chunk, Heightmap.Types type, BlockPos pos, int dY) {
		chunk.getOrCreateHeightmapUnprimed(type).setHeight(pos.getX() & 15, pos.getZ() & 15, dY + 1);
	}

	@Override
	public WeightedRandomList<MobSpawnSettings.SpawnerData> getMobsAt(Holder<Biome> biome, StructureManager structureManager, MobCategory mobCategory, BlockPos pos) {
		return super.getMobsAt(biome, structureManager, mobCategory, pos);
	}
}