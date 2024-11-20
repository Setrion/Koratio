package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.koratio.registry.KoratioDimensions;

import javax.annotation.Nullable;

public class FantasiaPortalBlock extends HalfTransparentBlock implements Portal {

	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	public static final VoxelShape SHAPE_WEST = Shapes.or(
			Block.box(9.0D, 0.0D, 5.0D, 11.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 5.0D, 9.0D, 16.0D, 7.0D)
	);
	public static final VoxelShape SHAPE_NORTH = Shapes.or(
			Block.box(9.0D, 0.0D, 0.0D, 11.0D, 16.0D, 11.0D),
			Block.box(0.0D, 0.0D, 9.0D, 9.0D, 16.0D, 11.0D)
	);
	public static final VoxelShape SHAPE_EAST = Shapes.or(
			Block.box(5.0D, 0.0D, 5.0D, 7.0D, 16.0D, 16.0D),
			Block.box(7.0D, 0.0D, 5.0D, 16.0D, 16.0D, 7.0D)
	);
	public static final VoxelShape SHAPE_SOUTH = Shapes.or(
			Block.box(5.0D, 0.0D, 0.0D, 7.0D, 16.0D, 11.0D),
			Block.box(5.0D, 0.0D, 9.0D, 16.0D, 16.0D, 11.0D)
	);


	public FantasiaPortalBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		switch (state.getValue(FACING)) {
		case NORTH:
			return SHAPE_NORTH;
		case SOUTH:
			return SHAPE_SOUTH;
		case EAST:
			return SHAPE_EAST;
		case WEST:
		default:
			return SHAPE_WEST;
		}
	}

	@Override
	public int getPortalTransitionTime(ServerLevel level, Entity entity) {
		int var10000;
		if (entity instanceof Player player) {
			var10000 = Math.max(1, level.getGameRules().getInt(player.getAbilities().invulnerable ? GameRules.RULE_PLAYERS_NETHER_PORTAL_CREATIVE_DELAY : GameRules.RULE_PLAYERS_NETHER_PORTAL_DEFAULT_DELAY));
		} else {
			var10000 = 0;
		}

		return var10000;
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity.canUsePortal(false)) {
			entity.setAsInsidePortal(this, pos);
		}
	}

	@Nullable
	@Override
	public TeleportTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
		ResourceKey<Level> resourcekey = level.dimension() == KoratioDimensions.FANTASIA_DIMENSION_KEY ? Level.OVERWORLD : KoratioDimensions.FANTASIA_DIMENSION_KEY;

		ServerLevel serverlevel = level.getServer().getLevel(resourcekey);
		if (serverlevel == null) {
			return null;
		} else {
			WorldBorder worldborder = serverlevel.getWorldBorder();
			double d0 = DimensionType.getTeleportationScale(level.dimensionType(), serverlevel.dimensionType());
			BlockPos blockpos = worldborder.clampToBounds(entity.getX() * d0, entity.getY(), entity.getZ() * d0);
			return this.getExitPortal(serverlevel, entity, pos, blockpos);
		}
	}

	@Nullable
	private TeleportTransition getExitPortal(ServerLevel level, Entity entity, BlockPos pos, BlockPos exitPos) {
		level.getChunkAt(exitPos);
		TeleportTransition.PostTeleportTransition dimensiontransition$postdimensiontransition;
		BlockPos fPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, exitPos);
		dimensiontransition$postdimensiontransition = TeleportTransition.PLAY_PORTAL_SOUND.then(entity1 -> entity1.placePortalTicket(fPos));

		return getDimensionTransitionFromExit(entity, fPos, level, dimensiontransition$postdimensiontransition);
	}

	private static TeleportTransition getDimensionTransitionFromExit(Entity entity, BlockPos pos, ServerLevel level, TeleportTransition.PostTeleportTransition postDimensionTransition) {
		Vec3 vec3 = pos.getCenter();
		return createDimensionTransition(level, vec3, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), postDimensionTransition);
	}

	private static TeleportTransition createDimensionTransition(ServerLevel level, Vec3 position, Entity entity, Vec3 speed, float yRot, float xRot, TeleportTransition.PostTeleportTransition postDimensionTransition) {
		EntityDimensions entitydimensions = entity.getDimensions(entity.getPose());
		Vec3 vec32 = PortalShape.findCollisionFreePosition(position, level, entity, entitydimensions);
		return new TeleportTransition(level, vec32, speed, yRot, xRot, postDimensionTransition);
	}

	@Override
	public Portal.Transition getLocalTransition() {
		return Transition.CONFUSION;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}