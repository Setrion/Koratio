package net.setrion.koratio.world.level.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.world.entity.projectile.RevivingSoul;
import net.setrion.koratio.world.level.block.entity.RemainsBlockEntity;

import javax.annotation.Nullable;

public class RemainsBlock extends BaseEntityBlock {

    public static final MapCodec<RemainsBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Type.CODEC.fieldOf("kind").forGetter(RemainsBlock::getType), propertiesCodec()).apply(instance, RemainsBlock::new));
    public static final EnumProperty<Direction> FACING;

    private final Type type;
    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 5.0, 15.0);

    public RemainsBlock(Type type, Properties properties) {
        super(properties);
        this.type = type;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public Type getType() {
        return this.type;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RemainsBlockEntity(blockPos, blockState);
    }

    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!level.isClientSide()) {
            if (projectile instanceof RevivingSoul soul) {
                Mob undead = getRevivingMob(level);
                undead.finalizeSpawn((ServerLevel) level, level.getCurrentDifficultyAt(hit.getBlockPos()), EntitySpawnReason.MOB_SUMMONED, null);
                undead.moveTo(hit.getBlockPos().getX() + .5, hit.getBlockPos().getY(), hit.getBlockPos().getZ() + .5);
                level.addFreshEntity(undead);
                level.setBlockAndUpdate(hit.getBlockPos(), Blocks.AIR.defaultBlockState());
                soul.discard();
            }
        }
    }

    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public Mob getRevivingMob(Level level) {
        return switch (getType()) {
            case SKELETON -> EntityType.SKELETON.create(level, EntitySpawnReason.MOB_SUMMONED);
            case WITHER_SKELETON -> EntityType.WITHER_SKELETON.create(level, EntitySpawnReason.MOB_SUMMONED);
            case STRAY -> EntityType.STRAY.create(level, EntitySpawnReason.MOB_SUMMONED);
            case BOGGED -> EntityType.BOGGED.create(level, EntitySpawnReason.MOB_SUMMONED);
            case DEMONIC_SKELETON -> KoratioEntityType.DEMONIC_SKELETON.get().create(level, EntitySpawnReason.MOB_SUMMONED);
            case ZOMBIE -> EntityType.ZOMBIE.create(level, EntitySpawnReason.MOB_SUMMONED);
            case HUSK -> EntityType.HUSK.create(level, EntitySpawnReason.MOB_SUMMONED);
            case DROWNED -> EntityType.DROWNED.create(level, EntitySpawnReason.MOB_SUMMONED);
            case DEMONIC_ZOMBIE -> KoratioEntityType.DEMONIC_ZOMBIE.get().create(level, EntitySpawnReason.MOB_SUMMONED);
            case ZOMBIE_VILLAGER -> EntityType.ZOMBIE_VILLAGER.create(level, EntitySpawnReason.MOB_SUMMONED);
            case PHANTOM -> EntityType.PHANTOM.create(level, EntitySpawnReason.MOB_SUMMONED);
            case ZOMBIFIED_PIGLIN -> EntityType.ZOMBIFIED_PIGLIN.create(level, EntitySpawnReason.MOB_SUMMONED);
        };
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
    }

    public enum Type implements StringRepresentable {
        SKELETON("skeleton"),
        WITHER_SKELETON("wither_skeleton"),
        STRAY("stray"),
        BOGGED("bogged"),
        DEMONIC_SKELETON("demonic_skeleton"),
        ZOMBIE("zombie"),
        HUSK("husk"),
        DROWNED("drowned"),
        DEMONIC_ZOMBIE("demonic_zombie"),
        ZOMBIE_VILLAGER("zombie_villager"),
        PHANTOM("phantom"),
        ZOMBIFIED_PIGLIN("zombified_piglin");

        public static final Codec<RemainsBlock.Type> CODEC = StringRepresentable.fromEnum(RemainsBlock.Type::values);
        private final String name;

        private Type(String name) {
            this.name = name;
        }

        public String getSerializedName() {
            return name;
        }
    }
}