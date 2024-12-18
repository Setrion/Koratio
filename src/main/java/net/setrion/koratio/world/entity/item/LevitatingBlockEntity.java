package net.setrion.koratio.world.entity.item;

import com.mojang.logging.LogUtils;
import net.minecraft.CrashReportCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.world.level.block.Levitateable;
import net.setrion.koratio.world.level.block.LevitatingBlock;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class LevitatingBlockEntity extends Entity {
    private static final Logger LOGGER = LogUtils.getLogger();
    private BlockState blockState = KoratioBlocks.WHITE_LEVITATING_WOOL.get().defaultBlockState();
    public int time;
    public boolean dropItem = true;
    private boolean cancelDrop;
    private boolean hurtEntities;
    private int fallDamageMax = 40;
    private float fallDamagePerDistance;
    @Nullable
    public CompoundTag blockData;
    public boolean forceTickAfterTeleportToDuplicate;
    protected static final EntityDataAccessor<BlockPos> DATA_START_POS = SynchedEntityData.defineId(LevitatingBlockEntity.class, EntityDataSerializers.BLOCK_POS);

    public LevitatingBlockEntity(EntityType<? extends LevitatingBlockEntity> entityType, Level level) {
        super(entityType, level);
    }

    private LevitatingBlockEntity(Level level, double x, double y, double z, BlockState state) {
        this(KoratioEntityType.LEVITATING_BLOCK.get(), level);
        blockState = state;
        blocksBuilding = true;
        setPos(x, y, z);
        setDeltaMovement(Vec3.ZERO);
        xo = x;
        yo = y;
        zo = z;
        setStartPos(blockPosition());
    }

    public static LevitatingBlockEntity levitate(Level level, BlockPos pos, BlockState blockState) {
        LevitatingBlockEntity levitating = new LevitatingBlockEntity(level, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, blockState.hasProperty(BlockStateProperties.WATERLOGGED) ? blockState.setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE) : blockState);
        level.setBlock(pos, blockState.getFluidState().createLegacyBlock(), 3);
        level.addFreshEntity(levitating);
        return levitating;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    public void setStartPos(BlockPos startPos) {
        entityData.set(DATA_START_POS, startPos);
    }

    public BlockPos getStartPos() {
        return entityData.get(DATA_START_POS);
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_START_POS, BlockPos.ZERO);
    }

    @Override
    public boolean isPickable() {
        return !isRemoved();
    }

    @Override
    protected double getDefaultGravity() {
        return -0.04;
    }

    @Override
    public void tick() {
        if (blockState.isAir()) {
            discard();
        } else {
            Block block = blockState.getBlock();
            ++time;
            applyGravity();
            move(MoverType.SELF, getDeltaMovement());
            handlePortal();
            if (!level().isClientSide && (isAlive() || forceTickAfterTeleportToDuplicate)) {
                BlockPos blockpos = blockPosition();

                if (!isOnCeiling(level(), blockpos)) {
                    if (!level().isClientSide && (time > 100 && (blockpos.getY() <= level().getMinY() || blockpos.getY() > level().getMaxY()) || time > 600)) {
                        if (dropItem && ((ServerLevel)level()).getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            spawnAtLocation((ServerLevel) level(), block);
                        }

                        discard();
                    }
                } else {
                    BlockState blockstate = level().getBlockState(blockpos);
                    setDeltaMovement(getDeltaMovement().multiply(0.7, -0.5, 0.7));
                    if (!blockstate.is(Blocks.MOVING_PISTON)) {
                        if (cancelDrop) {
                            discard();
                            callOnBrokenAfterFall(block, blockpos);
                        } else {

                            boolean flag2 = blockstate.canBeReplaced(new DirectionalPlaceContext(level(), blockpos, Direction.UP, ItemStack.EMPTY, Direction.DOWN));
                            boolean flag3 = LevitatingBlock.isFree(level().getBlockState(blockpos.above()));
                            boolean flag4 = blockState.canSurvive(level(), blockpos) && !flag3;
                            if (flag2 && flag4) {
                                if (blockState.hasProperty(BlockStateProperties.WATERLOGGED) && level().getFluidState(blockpos).getType() == Fluids.WATER) {
                                    blockState = blockState.setValue(BlockStateProperties.WATERLOGGED, true);
                                }
                                if (!level().getBlockState(blockpos.above()).isFaceSturdy(level(), blockpos.above(), Direction.DOWN) && !level().getBlockState(blockpos.above()).canBeReplaced()) {

                                    if (dropItem && ((ServerLevel)level()).getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                                        discard();
                                        callOnBrokenAfterFall(block, blockpos);
                                        spawnAtLocation((ServerLevel)level(), block);
                                    }
                                } else if (level().setBlock(blockpos, blockState, 3)){
                                    ((ServerLevel)level()).getChunkSource().chunkMap.broadcast(this, new ClientboundBlockUpdatePacket(blockpos, level().getBlockState(blockpos)));
                                    discard();
                                    if (block instanceof Levitateable levitateable) {
                                        levitateable.onLand(level(), blockpos, blockState, blockstate, this);
                                    }

                                    if (blockData != null && blockState.hasBlockEntity()) {
                                        BlockEntity blockentity = level().getBlockEntity(blockpos);
                                        if (blockentity != null) {
                                            CompoundTag compoundtag = blockentity.saveWithoutMetadata(level().registryAccess());

                                            for (String s : blockData.getAllKeys()) {
                                                compoundtag.put(s, blockData.get(s).copy());
                                            }

                                            try {
                                                blockentity.loadWithComponents(compoundtag, level().registryAccess());
                                            } catch (Exception var15) {
                                                LOGGER.error("Failed to load block entity from falling block", var15);
                                            }

                                            blockentity.setChanged();
                                        }
                                    }
                                }
                            } else {
                                discard();
                                if (dropItem && ((ServerLevel)level()).getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                                    callOnBrokenAfterFall(block, blockpos);
                                    spawnAtLocation((ServerLevel)level(), block);
                                }
                            }
                        }
                    }
                }
            }

            setDeltaMovement(getDeltaMovement().scale(0.98));
        }
    }

    public boolean isOnCeiling(Level level, BlockPos pos) {
        return !level.getBlockState(pos.above()).isAir();
    }

    public void callOnBrokenAfterFall(Block block, BlockPos pos) {
        if (block instanceof Levitateable) {
            ((Levitateable)block).onBrokenAfterFall(level(), pos, this);
        }
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        if (hurtEntities) {
            int i = Mth.ceil(fallDistance - 1.0F);
            if (i >= 0) {
                Predicate<Entity> predicate = EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE);
                DamageSource damagesource = blockState.getBlock() instanceof Fallable fallable
                        ? fallable.getFallDamageSource(this)
                        : damageSources().fallingBlock(this);
                float f = (float) Math.min(Mth.floor((float) i * fallDamagePerDistance), fallDamageMax);
                level().getEntities(this, getBoundingBox(), predicate).forEach(p_149649_ -> p_149649_.hurt(damagesource, f));
                boolean flag = blockState.is(BlockTags.ANVIL);
                if (flag && f > 0.0F && random.nextFloat() < 0.05F + (float) i * 0.05F) {
                    BlockState blockstate = AnvilBlock.damage(blockState);
                    if (blockstate == null) {
                        cancelDrop = true;
                    } else {
                        blockState = blockstate;
                    }
                }

            }
        }
        return false;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.put("BlockState", NbtUtils.writeBlockState(blockState));
        compound.putInt("Time", time);
        compound.putBoolean("DropItem", dropItem);
        compound.putBoolean("HurtEntities", hurtEntities);
        compound.putFloat("FallHurtAmount", fallDamagePerDistance);
        compound.putInt("FallHurtMax", fallDamageMax);
        if (blockData != null) {
            compound.put("TileEntityData", blockData);
        }

        compound.putBoolean("CancelDrop", cancelDrop);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        blockState = NbtUtils.readBlockState(level().holderLookup(Registries.BLOCK), compound.getCompound("BlockState"));
        time = compound.getInt("Time");
        if (compound.contains("HurtEntities", 99)) {
            hurtEntities = compound.getBoolean("HurtEntities");
            fallDamagePerDistance = compound.getFloat("FallHurtAmount");
            fallDamageMax = compound.getInt("FallHurtMax");
        } else if (blockState.is(BlockTags.ANVIL)) {
            hurtEntities = true;
        }

        if (compound.contains("DropItem", 99)) {
            dropItem = compound.getBoolean("DropItem");
        }

        if (compound.contains("TileEntityData", 10)) {
            blockData = compound.getCompound("TileEntityData").copy();
        }

        cancelDrop = compound.getBoolean("CancelDrop");
        if (blockState.isAir()) {
            blockState = KoratioBlocks.WHITE_LEVITATING_WOOL.get().defaultBlockState();
        }
    }

    public void setHurtsEntities(float fallDamagePerDistance, int fallDamageMax) {
        hurtEntities = true;
        this.fallDamagePerDistance = fallDamagePerDistance;
        this.fallDamageMax = fallDamageMax;
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    public void disableDrop() {
        cancelDrop = true;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public void fillCrashReportCategory(CrashReportCategory category) {
        super.fillCrashReportCategory(category);
        category.setDetail("Imitating BlockState", blockState.toString());
    }

    public BlockState getBlockState() {
        return blockState;
    }

    @Override
    protected Component getTypeName() {
        return Component.translatable("entity.minecraft.falling_block_type", this.blockState.getBlock().getName());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, entity, Block.getId(getBlockState()));
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        blockState = Block.stateById(packet.getData());
        blocksBuilding = true;
        double d0 = packet.getX();
        double d1 = packet.getY();
        double d2 = packet.getZ();
        setPos(d0, d1, d2);
        setStartPos(blockPosition());
    }

    @Nullable
    @Override
    public Entity teleport(TeleportTransition transition) {
        ResourceKey<Level> resourcekey = transition.newLevel().dimension();
        ResourceKey<Level> resourcekey1 = level().dimension();
        boolean flag = (resourcekey1 == Level.END || resourcekey == Level.END) && resourcekey1 != resourcekey;
        Entity entity = super.teleport(transition);
        forceTickAfterTeleportToDuplicate = entity != null && flag;
        return entity;
    }
}