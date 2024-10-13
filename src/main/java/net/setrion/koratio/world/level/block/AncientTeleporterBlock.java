package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioParticles;

import java.util.EnumSet;

public class AncientTeleporterBlock extends Block {

    public static final DirectionProperty DIRECTION = DirectionProperty.create("direction", Direction.Plane.VERTICAL);

    public AncientTeleporterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DIRECTION, Direction.UP));
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);
        Level world = entity.level();
        float pitch, yaw;
        if (world instanceof ServerLevel serverworld) {
            if (entity.isOnPortalCooldown()) {
                return;
            }

            BlockPos fromPos = getOriginElevator(entity);
            if (fromPos == null) {
                return;
            }
    
            BlockPos.MutableBlockPos toPos = fromPos.mutable();
    
            var fromElevator = getElevator(world.getBlockState(fromPos));
            if (fromElevator == null) {
                return;
            }
            
            if (entity instanceof LivingEntity living) {
                pitch = living.getYRot();
                yaw = living.getXRot();
            } else {
                pitch = 0.0F;
                yaw = 0.0F;
            }
    
            while (true) {
                toPos.setY(toPos.getY() + state.getValue(DIRECTION).getStepY());
                if (world.isOutsideBuildHeight(toPos) || Math.abs(toPos.getY() - fromPos.getY()) > 64) {
                    break;
                }
    
                var toElevator = getElevator(world.getBlockState(toPos));
                if (toElevator != null && isValidPos(world, toPos)) {
                    double blockYOffset = world.getBlockState(toPos).getBlockSupportShape(world, toPos).max(Direction.Axis.Y);
                    entity.teleportTo(serverworld, entity.getX(), Math.max(toPos.getY(), toPos.getY() + blockYOffset), entity.getZ(), EnumSet.noneOf(RelativeMovement.class), pitch, yaw);
                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(new Vec3(1D, 0D, 1D)));
                    serverworld.playSound(null, toPos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1F, 1F);
                    entity.setPortalCooldown(50);
                    break;
                }
            }
        }
    }

    public static boolean isValidPos(BlockGetter world, BlockPos pos) {
        return !world.getBlockState(pos.above()).isSuffocating(world, pos);
    }

    public static AncientTeleporterBlock getElevator(BlockState blockState) {
        if (blockState.getBlock() instanceof AncientTeleporterBlock elevator) {
            return elevator;
        }

        return null;
    }

    private static BlockPos getOriginElevator(Entity player) {
        BlockPos pos = player.blockPosition();

        // Check the player's feet and the 2 blocks under it
        for (int i = 0; i < 3; i++) {
            if (getElevator(player.level().getBlockState(pos)) != null)
                return pos;
            pos = pos.below();
        }

        // Elevator doesn't exist or it's invalid
        return null;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (level.isClientSide) {
            for(int i = 1; i < 9; i++) {
                double angle = 45*i;
                double x = 0.35*Math.sin(Math.toRadians(angle));
                double z = 0.35*Math.cos(Math.toRadians(angle));
                if (state.getValue(DIRECTION) == Direction.UP) {
                    level.addParticle(KoratioParticles.TELEPORTER_ASCEND.get(), pos.getCenter().x + x, pos.getY() + 1, pos.getCenter().z + z, 0.0D, 0.0D, 0.0D);
                } else {
                    level.addParticle(KoratioParticles.TELEPORTER_DESCEND.get(), pos.getCenter().x + x, pos.getY() + 2.5, pos.getCenter().z + z, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DIRECTION);
    }
}