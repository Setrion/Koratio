package net.setrion.koratio.world.level.biome;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ColumnPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.registry.KoratioPoiTypes;
import net.setrion.koratio.world.level.block.FantasiaPortalBlock;

public class FantasiaTeleporter implements ITeleporter {
	protected final ServerLevel level;

	public FantasiaTeleporter(ServerLevel serverlevel) {
		this.level = serverlevel;
	}

	public Optional<BlockUtil.FoundRectangle> findPortalAround(BlockPos pos, boolean dynamic, WorldBorder border) {
		PoiManager poimanager = this.level.getPoiManager();
		int i = dynamic ? 16 : 128;
		poimanager.ensureLoadedAndValid(this.level, pos, i);
		Optional<PoiRecord> optional = poimanager.getInSquare((p_230634_) -> {
			return p_230634_.is(KoratioPoiTypes.FANTASIA_PORTAL.getKey());
		}, pos, i, PoiManager.Occupancy.ANY).filter((p_192981_) -> {
			return border.isWithinBounds(p_192981_.getPos());
		}).sorted(Comparator.<PoiRecord>comparingDouble((p_192984_) -> {
			return p_192984_.getPos().distSqr(pos);
		}).thenComparingInt((p_192992_) -> {
			return p_192992_.getPos().getY();
		})).filter((p_192990_) -> {
			return this.level.getBlockState(p_192990_.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS);
		}).findFirst();
		return optional.map((p_192975_) -> {
			BlockPos blockpos = p_192975_.getPos();
			this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
			BlockState blockstate = this.level.getBlockState(blockpos);
			return BlockUtil.getLargestRectangleAround(blockpos, blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (p_192978_) -> {
				return this.level.getBlockState(p_192978_) == blockstate;
			});
		});
	}

	@Nullable
	@Override
	public PortalInfo getPortalInfo(Entity entity, ServerLevel dest, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
		PortalInfo pos;

		// Scale the coords based on the dimension type coordinate_scale
		ServerLevel dim = dest.getServer().getLevel(KoratioDimensions.FANTASIA_DIMENSION_KEY);
		double scale = dim == null ? 1D : dim.dimensionType().coordinateScale();
		scale = dest.dimension().equals(KoratioDimensions.FANTASIA_DIMENSION_KEY) ? 1F / scale : scale;
		BlockPos destPos = dest.getWorldBorder().clampToBounds(entity.blockPosition().getX() * scale, entity.blockPosition().getY(), entity.blockPosition().getZ() * scale);

		/*if ((pos = placeInExistingPortal(dest, entity, destPos)) == null) {
			pos = placeInExistingPortal(dest, entity, BlockPos.containing(pos.pos));
		}*/
		pos = placeInExistingPortal(dest, entity, destPos);
		return pos == null ? ITeleporter.super.getPortalInfo(entity, dest, defaultPortalInfo) : pos;
		//return ITeleporter.super.getPortalInfo(entity, dest, defaultPortalInfo);
	}
	
	@Nullable
	private PortalInfo placeInExistingPortal(ServerLevel destDim, Entity entity, BlockPos pos) {
		boolean flag = true;
		BlockPos blockpos;
		ColumnPos columnPos = new ColumnPos(entity.blockPosition().getX(), entity.blockPosition().getZ());

		Optional<BlockUtil.FoundRectangle> portalPosition = findPortalAround(pos, false, destDim.getWorldBorder());
		entity.setPortalCooldown();
		if (portalPosition != null && portalPosition.isPresent()) {
			blockpos = portalPosition.get().minCorner;
			flag = false;
			if (blockpos == null || !destDim.getBlockState(blockpos).is(KoratioBlocks.FANTASIA_PORTAL.get())) {
				Koratio.getLogger().debug("Portal Invalid, recreating.");
				blockpos = null;
			}
		} else {
			blockpos = createPortal(pos, Direction.Axis.X).get().minCorner;
		}

		if (blockpos == null) {
			return null;
		} else {
			if (flag) {
				destDim.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, new BlockPos(columnPos.x(), blockpos.getY(), columnPos.z()));
			}

			double portalX = blockpos.getX() - 0.5;
			double portalY = blockpos.getY();
			double portalZ = blockpos.getZ() - 0.5;

			return new PortalInfo(new Vec3(portalX, portalY, portalZ), Vec3.ZERO, entity.getYRot(), entity.getXRot());
		}
	}
	
	public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis) {
		Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
		double d0 = -1.0D;
		BlockPos blockpos = null;
		double d1 = -1.0D;
		BlockPos blockpos1 = null;
		WorldBorder worldborder = this.level.getWorldBorder();
		int i = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

		for(BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
			int j = Math.min(i, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getZ()));
			if (worldborder.isWithinBounds(blockpos$mutableblockpos1) && worldborder.isWithinBounds(blockpos$mutableblockpos1.move(direction, 1))) {
				blockpos$mutableblockpos1.move(direction.getOpposite(), 1);

				for(int l = j; l >= this.level.getMinBuildHeight(); --l) {
					blockpos$mutableblockpos1.setY(l);
					if (this.level.isEmptyBlock(blockpos$mutableblockpos1)) {
						int i1;
						for(i1 = l; l > this.level.getMinBuildHeight() && this.level.isEmptyBlock(blockpos$mutableblockpos1.move(Direction.DOWN)); --l) {
						}

						if (l + 4 <= i) {
							int j1 = i1 - l;
							if (j1 <= 0 || j1 >= 3) {
								blockpos$mutableblockpos1.setY(l);
								if (this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, 0)) {
									double d2 = pos.distSqr(blockpos$mutableblockpos1);
									if (this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, -1) && this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, 1) && (d0 == -1.0D || d0 > d2)) {
										d0 = d2;
										blockpos = blockpos$mutableblockpos1.immutable();
									}

									if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
										d1 = d2;
										blockpos1 = blockpos$mutableblockpos1.immutable();
									}
								}
							}
						}
					}
				}
			}
		}

		if (d0 == -1.0D && d1 != -1.0D) {
			blockpos = blockpos1;
			d0 = d1;
		}

		if (d0 == -1.0D) {
			int k1 = Math.max(this.level.getMinBuildHeight() - -1, 70);
			int i2 = i - 9;
			if (i2 < k1) {
				return Optional.empty();
			}

			blockpos = (new BlockPos(pos.getX(), Mth.clamp(pos.getY(), k1, i2), pos.getZ())).immutable();
			Direction direction1 = direction.getClockWise();
			if (!worldborder.isWithinBounds(blockpos)) {
				return Optional.empty();
			}

			for(int i3 = -1; i3 < 2; ++i3) {
				for(int j3 = 0; j3 < 2; ++j3) {
					for(int k3 = -1; k3 < 3; ++k3) {
						BlockState blockstate1 = k3 < 0 ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.AIR.defaultBlockState();
						blockpos$mutableblockpos.setWithOffset(blockpos, j3 * direction.getStepX() + i3 * direction1.getStepX(), k3, j3 * direction.getStepZ() + i3 * direction1.getStepZ());
						this.level.setBlockAndUpdate(blockpos$mutableblockpos, blockstate1);
					}
				}
			}
		}

		for(int l1 = -1; l1 < 3; ++l1) {
			for(int j2 = -1; j2 < 4; ++j2) {
				if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3) {
					blockpos$mutableblockpos.setWithOffset(blockpos, l1 * direction.getStepX(), j2, l1 * direction.getStepZ());
					this.level.setBlock(blockpos$mutableblockpos, Blocks.OBSIDIAN.defaultBlockState(), 3);
				}
			}
		}

		BlockState blockstate = KoratioBlocks.FANTASIA_PORTAL.get().defaultBlockState().setValue(FantasiaPortalBlock.AXIS, axis);

		for(int k2 = 0; k2 < 2; ++k2) {
			for(int l2 = 0; l2 < 3; ++l2) {
				blockpos$mutableblockpos.setWithOffset(blockpos, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
				this.level.setBlock(blockpos$mutableblockpos, blockstate, 18);
			}
		}

		return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
	}
	
	private boolean canHostFrame(BlockPos p_77662_, BlockPos.MutableBlockPos p_77663_, Direction p_77664_, int p_77665_) {
		Direction direction = p_77664_.getClockWise();
		for(int i = -1; i < 3; ++i) {
			for(int j = -1; j < 4; ++j) {
				p_77663_.setWithOffset(p_77662_, p_77664_.getStepX() * i + direction.getStepX() * p_77665_, j, p_77664_.getStepZ() * i + direction.getStepZ() * p_77665_);
				if (j < 0 && !this.level.getBlockState(p_77663_).isSolid()) {
					return false;
				}

				if (j >= 0 && !this.level.isEmptyBlock(p_77663_)) {
					return false;
				}
			}
		}

		return true;
	}
}