package net.setrion.koratio.world.level.levelgen.carver;

import java.util.Random;
import java.util.function.Function;

import org.apache.commons.lang3.mutable.MutableBoolean;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.setrion.koratio.registry.KoratioBlocks;

public class AmethystSpiderCavesCarver extends FantasiaCavesCarver {

	private final Random rand = new Random();

	public AmethystSpiderCavesCarver(Codec<CaveCarverConfiguration> codec) {
		super(codec);
	}

	@Override
	protected boolean carveBlock(CarvingContext ctx, CaveCarverConfiguration config, ChunkAccess access, Function<BlockPos, Holder<Biome>> biomePos, CarvingMask mask, BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos posUp, Aquifer aquifer, MutableBoolean isSurface) {
		BlockState blockstate = access.getBlockState(pos);
		if (blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(Blocks.MYCELIUM) || blockstate.is(Blocks.PODZOL) || blockstate.is(Blocks.DIRT_PATH)) {
			isSurface.setTrue();
		}

		if (pos.getY() < access.getMinBuildHeight() + 6) return false;

		if (!this.canReplaceBlock(config, blockstate) && !isDebugEnabled(config)) {
			return false;
		} else {
			BlockState blockstate2 = this.getCarveState(ctx, config, pos, aquifer);
			if (blockstate2 == null) {
				return false;
			} else {
				if (aquifer.shouldScheduleFluidUpdate() && !blockstate2.getFluidState().isEmpty()) {
					access.markPosForPostprocessing(pos);
				}

				for (Direction facing : Direction.values()) {
					FluidState aboveSurface = access.getFluidState(posUp.offset(pos.offset(0, 1, 0)));
					FluidState areaAround = access.getFluidState(posUp.relative(facing));
					FluidState areaAboveAround = access.getFluidState(posUp.offset(pos.offset(0, 1, 0).relative(facing)));

					if (areaAround.is(FluidTags.WATER) || areaAboveAround.is(FluidTags.WATER) || aboveSurface.is(FluidTags.WATER)) {
						return false;
					} else {
						access.setBlockState(pos, CAVE_AIR, false);
						if ((access.getBlockState(pos.above()).is(BlockTags.BASE_STONE_OVERWORLD) || access.getFluidState(pos.above()).is(FluidTags.WATER)) && access.getBlockState(pos).isAir()) {
							switch (rand.nextInt(45)) {
								case 0 -> access.setBlockState(pos.above(), KoratioBlocks.AMETHYST_COBWEB.get().defaultBlockState(), false);
							}
						}
						if ((access.getBlockState(pos.below()).is(BlockTags.BASE_STONE_OVERWORLD) || access.getFluidState(pos.below()).is(FluidTags.WATER)) && access.getBlockState(pos).isAir()) {
							switch (rand.nextInt(45)) {
								case 0 -> access.setBlockState(pos.below(), KoratioBlocks.AMETHYST_COBWEB.get().defaultBlockState(), false);
							}
						}
						if (isSurface.isTrue()) {
							BlockPos posDown = pos.setWithOffset(pos, Direction.DOWN).below();
							if (access.getBlockState(posDown).is(Blocks.DIRT)) {
								ctx.topMaterial(biomePos, access, posDown, !blockstate2.getFluidState().isEmpty()).ifPresent((state -> {
									access.setBlockState(posDown, state, false);
									if (!state.getFluidState().isEmpty()) {
										access.markPosForPostprocessing(posDown);
									}
								}));
							}
						}

					}
				}
				return true;
			}
		}
	}
}