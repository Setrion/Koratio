package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.koratio.registry.KoratioParticles;

import javax.annotation.Nullable;
import java.util.List;

public class FlippedFarmBlock extends Block {
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 1.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public FlippedFarmBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, Integer.valueOf(0)));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor accessor, BlockPos pos, BlockPos newPos) {
        if (direction == Direction.DOWN && !state.canSurvive(accessor, pos)) {
            accessor.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, newState, accessor, pos, newPos);
    }

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        BlockState blockstate = reader.getBlockState(pos.below());
        return !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Blocks.DIRT.defaultBlockState() : super.getStateForPlacement(context);
    }

    public boolean useShapeForLightOcclusion(BlockState p_53295_) {
        return true;
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            turnToDirt((Entity)null, state, level, pos);
        }
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int i = state.getValue(MOISTURE);
        if (!isNearWater(level, pos)) {
            if (i > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);
            } else if (!shouldMaintainFarmland(level, pos)) {
                turnToDirt((Entity)null, state, level, pos);
            }
        } else if (i < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(7)), 2);
        }

    }

    public static void turnToDirt(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
        BlockState blockstate = pushEntitiesDown(state, Blocks.DIRT.defaultBlockState(), level, pos);
        level.setBlockAndUpdate(pos, blockstate);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockstate));
    }

    public static BlockState pushEntitiesDown(BlockState state, BlockState dirt, LevelAccessor accessor, BlockPos pos) {
        VoxelShape voxelshape = Shapes.joinUnoptimized(state.getCollisionShape(accessor, pos), dirt.getCollisionShape(accessor, pos), BooleanOp.ONLY_SECOND).move((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        if (voxelshape.isEmpty()) {
            return dirt;
        } else {
            for(Entity entity : accessor.getEntities((Entity)null, voxelshape.bounds())) {
                double d0 = Shapes.collide(Direction.Axis.Y, entity.getBoundingBox().move(0.0D, -1.0D, 0.0D), List.of(voxelshape), -1.0D);
                entity.teleportRelative(0.0D, -1.0D + d0, 0.0D);
            }

            return dirt;
        }
    }

    private static boolean shouldMaintainFarmland(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    private static boolean isNearWater(LevelReader reader, BlockPos pos) {
        BlockState state = reader.getBlockState(pos);
        for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (state.canBeHydrated(reader, pos, reader.getFluidState(blockpos), blockpos)) {
                return true;
            }
        }
        return net.neoforged.neoforge.common.FarmlandWaterManager.hasBlockWaterTicket(reader, pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType computationType) {
        return false;
    }
}