package net.setrion.koratio.world.level.biome;

import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.block.FantasiaPortalBlock;

public class FantasiaPortalShape {
	public static final int MAX_WIDTH = 21;
	public static final int MAX_HEIGHT = 21;
	private static final BlockBehaviour.StatePredicate FRAME = (p_77720_, p_77721_, p_77722_) -> {
		return p_77720_.isPortalFrame(p_77721_, p_77722_);
	};
	private final LevelAccessor level;
	private final Direction.Axis axis;
	private final Direction rightDir;
	private int numPortalBlocks;
	@Nullable
	private BlockPos bottomLeft;
	private int height;
	private final int width;

	public static Optional<FantasiaPortalShape> findEmptyPortalShape(LevelAccessor accessor, BlockPos pos, Direction.Axis axis) {
		return findPortalShape(accessor, pos, (shape) -> {
			return shape.isValid() && shape.numPortalBlocks == 0;
		}, axis);
	}

	public static Optional<FantasiaPortalShape> findPortalShape(LevelAccessor accessor, BlockPos pos, Predicate<FantasiaPortalShape> predicate, Direction.Axis axis) {
		Optional<FantasiaPortalShape> optional = Optional.of(new FantasiaPortalShape(accessor, pos, axis)).filter(predicate);
		if (optional.isPresent()) {
			return optional;
		} else {
			Direction.Axis direction$axis = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
			return Optional.of(new FantasiaPortalShape(accessor, pos, direction$axis)).filter(predicate);
		}
	}

	public FantasiaPortalShape(LevelAccessor accessor, BlockPos pos, Direction.Axis axis) {
		this.level = accessor;
		this.axis = axis;
		this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
		this.bottomLeft = this.calculateBottomLeft(pos);
		if (this.bottomLeft == null) {
			this.bottomLeft = pos;
			this.width = 1;
			this.height = 1;
		} else {
			this.width = this.calculateWidth();
			if (this.width > 0) {
				this.height = this.calculateHeight();
			}
		}

	}

	@Nullable
	private BlockPos calculateBottomLeft(BlockPos pos) {
		for(int i = Math.max(this.level.getMinBuildHeight(), pos.getY() - 21); pos.getY() > i && isEmpty(this.level.getBlockState(pos.below())); pos = pos.below()) {
		}

		Direction direction = this.rightDir.getOpposite();
		int j = this.getDistanceUntilEdgeAboveFrame(pos, direction) - 1;
		return j < 0 ? null : pos.relative(direction, j);
	}

	private int calculateWidth() {
		int i = this.getDistanceUntilEdgeAboveFrame(this.bottomLeft, this.rightDir);
		return i >= 2 && i <= 21 ? i : 0;
	}

	private int getDistanceUntilEdgeAboveFrame(BlockPos pos, Direction direction) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int i = 0; i <= 21; ++i) {
			blockpos$mutableblockpos.set(pos).move(direction, i);
			BlockState blockstate = this.level.getBlockState(blockpos$mutableblockpos);
			if (!isEmpty(blockstate)) {
				if (FRAME.test(blockstate, this.level, blockpos$mutableblockpos)) {
					return i;
				}
				break;
			}

			BlockState blockstate1 = this.level.getBlockState(blockpos$mutableblockpos.move(Direction.DOWN));
			if (!FRAME.test(blockstate1, this.level, blockpos$mutableblockpos)) {
				break;
			}
		}

		return 0;
	}

	private int calculateHeight() {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int i = this.getDistanceUntilTop(blockpos$mutableblockpos);
		return i >= 3 && i <= 21 && this.hasTopFrame(blockpos$mutableblockpos, i) ? i : 0;
	}

	private boolean hasTopFrame(BlockPos.MutableBlockPos pos, int height) {
		for(int i = 0; i < this.width; ++i) {
			BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.set(this.bottomLeft).move(Direction.UP, height).move(this.rightDir, i);
			if (!FRAME.test(this.level.getBlockState(blockpos$mutableblockpos), this.level, blockpos$mutableblockpos)) {
				return false;
			}
		}

		return true;
	}

	private int getDistanceUntilTop(BlockPos.MutableBlockPos pos) {
		for(int i = 0; i < 21; ++i) {
			pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, -1);
			if (!FRAME.test(this.level.getBlockState(pos), this.level, pos)) {
				return i;
			}

			pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, this.width);
			if (!FRAME.test(this.level.getBlockState(pos), this.level, pos)) {
				return i;
			}

			for(int j = 0; j < this.width; ++j) {
				pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, j);
				BlockState blockstate = this.level.getBlockState(pos);
				if (!isEmpty(blockstate)) {
					return i;
				}

				if (blockstate.is(KoratioBlocks.FANTASIA_PORTAL.get())) {
					++this.numPortalBlocks;
				}
			}
		}

		return 21;
	}

	private static boolean isEmpty(BlockState state) {
		return state.isAir() || state.is(BlockTags.FIRE) || state.is(KoratioBlocks.FANTASIA_PORTAL.get()) || state.is(Blocks.NETHER_PORTAL);
	}

	public boolean isValid() {
		return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
	}

	public void createPortalBlocks() {
		BlockState blockstate = KoratioBlocks.FANTASIA_PORTAL.get().defaultBlockState().setValue(FantasiaPortalBlock.AXIS, this.axis);
		BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)).forEach((p_77725_) -> {
			this.level.setBlock(p_77725_, blockstate, 18);
		});
	}

	public boolean isComplete() {
		return this.isValid() && this.numPortalBlocks == this.width * this.height;
	}

	public static Vec3 getRelativePosition(BlockUtil.FoundRectangle rect, Direction.Axis axis, Vec3 vec, EntityDimensions dimensions) {
		double d0 = (double)rect.axis1Size - (double)dimensions.width;
		double d1 = (double)rect.axis2Size - (double)dimensions.height;
		BlockPos blockpos = rect.minCorner;
		double d2;
		if (d0 > 0.0D) {
			float f = (float)blockpos.get(axis) + dimensions.width / 2.0F;
			d2 = Mth.clamp(Mth.inverseLerp(vec.get(axis) - (double)f, 0.0D, d0), 0.0D, 1.0D);
		} else {
			d2 = 0.5D;
		}

		double d4;
		if (d1 > 0.0D) {
			Direction.Axis direction$axis = Direction.Axis.Y;
			d4 = Mth.clamp(Mth.inverseLerp(vec.get(direction$axis) - (double)blockpos.get(direction$axis), 0.0D, d1), 0.0D, 1.0D);
		} else {
			d4 = 0.0D;
		}

		Direction.Axis direction$axis1 = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
		double d3 = vec.get(direction$axis1) - ((double)blockpos.get(direction$axis1) + 0.5D);
		return new Vec3(d2, d4, d3);
	}

	public static PortalInfo createPortalInfo(ServerLevel level, BlockUtil.FoundRectangle rect, Direction.Axis axis, Vec3 vec, EntityDimensions dimensions, Vec3 pos, float yRot, float xRot) {
		BlockPos blockpos = rect.minCorner;
		BlockState blockstate = level.getBlockState(blockpos);
		Direction.Axis direction$axis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
		double d0 = (double)rect.axis1Size;
		double d1 = (double)rect.axis2Size;
		int i = axis == direction$axis ? 0 : 90;
		Vec3 vec3 = axis == direction$axis ? pos : new Vec3(pos.z, pos.y, -pos.x);
		double d2 = (double)dimensions.width / 2.0D + (d0 - (double)dimensions.width) * vec.x();
		double d3 = (d1 - (double)dimensions.height) * vec.y();
		double d4 = 0.5D + vec.z();
		boolean flag = direction$axis == Direction.Axis.X;
		Vec3 vec31 = new Vec3((double)blockpos.getX() + (flag ? d2 : d4), (double)blockpos.getY() + d3, (double)blockpos.getZ() + (flag ? d4 : d2));
		return new PortalInfo(vec31, vec3, yRot + (float)i, xRot);
	}
}