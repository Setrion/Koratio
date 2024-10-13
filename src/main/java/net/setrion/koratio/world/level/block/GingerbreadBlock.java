package net.setrion.koratio.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioBlockEntityType;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioTags;
import net.setrion.koratio.world.level.block.entity.GingerbreadBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.OptionalInt;

public class GingerbreadBlock extends BaseEntityBlock {

    public GingerbreadBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(KoratioTags.Items.SUGAR)) {
            if (level.getBlockEntity(pos) instanceof GingerbreadBlockEntity gingerbread) {
                if (stack.is(Items.IRON_INGOT)) {
                    gingerbread.clearOverlays();
                }
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            if (level.getBlockEntity(pos) instanceof GingerbreadBlockEntity gingerbread) {
                Direction facing = hitResult.getDirection();
                int dir = 0;
                GingerbreadBlockEntity.PartColor color = GingerbreadBlockEntity.PartColor.NONE;
                if (stack.is(Items.SUGAR)) color = GingerbreadBlockEntity.PartColor.WHITE;
                if (stack.is(KoratioItems.BLUE_SUGAR)) color = GingerbreadBlockEntity.PartColor.BLUE;
                if (stack.is(KoratioItems.GREEN_SUGAR)) color = GingerbreadBlockEntity.PartColor.GREEN;
                if (stack.is(KoratioItems.YELLOW_SUGAR)) color = GingerbreadBlockEntity.PartColor.YELLOW;
                if (stack.is(KoratioItems.RED_SUGAR)) color = GingerbreadBlockEntity.PartColor.RED;
                if (facing == Direction.DOWN) {
                    dir = 5;
                } else if (facing == Direction.UP) {
                    dir = 4;
                } else if (facing == Direction.NORTH) {
                    dir = 0;
                } else if (facing == Direction.EAST) {
                    dir = 1;
                } else if (facing == Direction.SOUTH) {
                    dir = 2;
                } else if (facing == Direction.WEST) {
                    dir = 3;
                }
                GingerbreadBlockEntity.Part part = getHitPart(hitResult, hitResult.getDirection());
                if (part != null && !gingerbread.getPart(hitResult.getDirection(), part)) {
                    if (gingerbread.getColor(dir) == GingerbreadBlockEntity.PartColor.NONE) {
                        gingerbread.setColor(dir, color);
                    }
                    if ((stack.is(Items.SUGAR) && gingerbread.getColor(dir) == GingerbreadBlockEntity.PartColor.WHITE) || (stack.is(KoratioItems.BLUE_SUGAR) && gingerbread.getColor(dir) == GingerbreadBlockEntity.PartColor.BLUE) || (stack.is(KoratioItems.GREEN_SUGAR) && gingerbread.getColor(dir) == GingerbreadBlockEntity.PartColor.GREEN) || (stack.is(KoratioItems.YELLOW_SUGAR) && gingerbread.getColor(dir) == GingerbreadBlockEntity.PartColor.YELLOW) || (stack.is(KoratioItems.RED_SUGAR) && gingerbread.getColor(dir) == GingerbreadBlockEntity.PartColor.RED)) {
                        gingerbread.setPart(hitResult.getDirection(), part, true);
                    }
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private static GingerbreadBlockEntity.Part getHitPart(BlockHitResult hitResult, Direction face) {
        Direction direction = hitResult.getDirection();
        if (face != direction) {
            return null;
        } else {
            BlockPos blockpos = hitResult.getBlockPos().relative(direction);
            Vec3 vec3 = hitResult.getLocation().subtract(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            double d0 = vec3.x();
            double d1 = vec3.y();
            double d2 = vec3.z();

            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                if (d0 < 0.25 && d1 < 0.75) {
                    if (direction == Direction.NORTH) return GingerbreadBlockEntity.Part.LEFT;
                    return GingerbreadBlockEntity.Part.RIGHT;
                } else if (d0 < 0.75 && d1 < 0.25) {
                    return GingerbreadBlockEntity.Part.BOTTOM;
                } else if (d0 < 0.75 && d1 > 0.75) {
                    return GingerbreadBlockEntity.Part.TOP;
                } else if (d0 < 0.75 && d1 < 0.75) {
                    return GingerbreadBlockEntity.Part.MIDDLE;
                } else if (d0 > 0.75 && d1 < 0.75) {
                    if (direction == Direction.NORTH) return GingerbreadBlockEntity.Part.RIGHT;
                    return GingerbreadBlockEntity.Part.LEFT;
                }
            } else if (direction == Direction.EAST || direction == Direction.WEST) {
                if (d2 < 0.25 && d1 < 0.75) {
                    if (direction == Direction.EAST) return GingerbreadBlockEntity.Part.LEFT;
                    return GingerbreadBlockEntity.Part.RIGHT;
                } else if (d2 < 0.75 && d1 < 0.25) {
                    return GingerbreadBlockEntity.Part.BOTTOM;
                } else if (d2 < 0.75 && d1 > 0.75) {
                    return GingerbreadBlockEntity.Part.TOP;
                } else if (d2 < 0.75 && d1 < 0.75) {
                    return GingerbreadBlockEntity.Part.MIDDLE;
                } else if (d2 > 0.75 && d1 < 0.75) {
                    if (direction == Direction.EAST) return GingerbreadBlockEntity.Part.RIGHT;
                    return GingerbreadBlockEntity.Part.LEFT;
                }
            } else if (direction == Direction.UP || direction == Direction.DOWN) {
                if (d0 < 0.25 && d2 < 0.75) {
                    return GingerbreadBlockEntity.Part.RIGHT;
                } else if (d0 < 0.75 && d2 < 0.25) {
                    if (direction == Direction.UP) return GingerbreadBlockEntity.Part.TOP;
                    return GingerbreadBlockEntity.Part.BOTTOM;
                } else if (d0 < 0.75 && d2 > 0.75) {
                    if (direction == Direction.UP) return GingerbreadBlockEntity.Part.BOTTOM;
                    return GingerbreadBlockEntity.Part.TOP;
                } else if (d0 < 0.75 && d2 < 0.75) {
                    return GingerbreadBlockEntity.Part.MIDDLE;
                } else if (d0 > 0.75 && d2 < 0.75) {
                    return GingerbreadBlockEntity.Part.LEFT;
                }
            }//x & y < 0.75 = middle / x < 0.75 & y > 0.75 = top / x < 0.75 & y < 0.25 = bottom / x > 0.75 & y < 0.75 = right / x < 0.25 & y < 0.75 = left
        }
        return null;
    }

    private static int getSection(float x) {
        float f = 0.0625F;
        float f1 = 0.375F;
        if (x < 0.375F) {
            return 0;
        } else {
            float f2 = 0.6875F;
            return x < 0.6875F ? 1 : 2;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return KoratioBlockEntityType.GINGERBREAD_BLOCK.get().create(blockPos, blockState);
    }
}