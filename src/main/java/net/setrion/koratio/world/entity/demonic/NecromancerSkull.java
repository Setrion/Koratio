package net.setrion.koratio.world.entity.demonic;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioParticles;
import net.setrion.koratio.util.EntityUtils;
import net.setrion.koratio.world.entity.projectile.RevivingSoul;
import net.setrion.koratio.world.level.block.RemainsBlock;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class NecromancerSkull extends Monster {

    protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID;
    protected static final EntityDataAccessor<Integer> DATA_COOLDOWN_ID;

    public NecromancerSkull(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new NecromancerSkull.SkullMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new NecromancerSkull.NecromancerSkullHoverAboveOwnerGoal());
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SkullShootOnRemainsGoal());
    }

    @Override
    public void tick() {
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.27F).add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_OWNERUUID_ID, Optional.empty());
        builder.define(DATA_COOLDOWN_ID, 0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getOwnerUUID() != null) {
            compound.putUUID("Owner", this.getOwnerUUID());
        }
        compound.putInt("Cooldown", this.getCooldown());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        UUID uuid;
        if (compound.hasUUID("Owner")) {
            uuid = compound.getUUID("Owner");
        } else {
            String s = compound.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }
        setCooldown(compound.getInt("Cooldown"));

        if (uuid != null) {
            this.setOwnerUUID(uuid);
        }
    }

    @javax.annotation.Nullable
    public UUID getOwnerUUID() {
        return (UUID)((Optional<?>)this.entityData.get(DATA_OWNERUUID_ID)).orElse(null);
    }

    public void setOwnerUUID(@javax.annotation.Nullable UUID uuid) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(uuid));
    }

    public boolean isOwnedBy(LivingEntity entity) {
        return entity == this.getOwner();
    }

    @Nullable
    public Necromancer getOwner() {
        UUID uuid = this.getOwnerUUID();
        if (uuid == null) {
            return null;
        } else {
            List<Entity> list = EntityUtils.getNecromancerSkullOwner(level(), uuid);
            if (!list.isEmpty()) {
                Entity entity = list.get(0);
                if (entity instanceof Necromancer necromancer) {
                    return necromancer;
                }
            }
        }
        return null;
    }

    public int getCooldown() {
        return this.entityData.get(DATA_COOLDOWN_ID);
    }

    public void setCooldown(int cooldown) {
        this.entityData.set(DATA_COOLDOWN_ID, cooldown);
    }

    public void aiStep() {
        if (this.level().isClientSide) {
            if (random.nextInt(3) < 1) {
                this.level().addParticle(KoratioParticles.DEMONIC_FIRE_FLAME.get(), this.getRandomX(0.6), this.getRandomY(), this.getRandomZ(0.6), 0, 0, 0);
            }
        }
        if (getCooldown() > 0) {
            setCooldown(getCooldown() - 1);
        }
        super.aiStep();
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source.is(DamageTypes.GENERIC_KILL)) {
            super.hurt(source, amount);
        }
        return false;
    }

    static {
        DATA_OWNERUUID_ID = SynchedEntityData.defineId(NecromancerSkull.class, EntityDataSerializers.OPTIONAL_UUID);
        DATA_COOLDOWN_ID = SynchedEntityData.defineId(NecromancerSkull.class, EntityDataSerializers.INT);
    }

    class NecromancerSkullHoverAboveOwnerGoal extends Goal {
        public NecromancerSkullHoverAboveOwnerGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            LivingEntity owner = NecromancerSkull.this.getOwner();
            return owner != null
                    && owner.isAlive()
                    && !NecromancerSkull.this.getMoveControl().hasWanted()
                    && NecromancerSkull.this.distanceToSqr(owner) > 2.0;
        }

        @Override
        public boolean canContinueToUse() {
            return NecromancerSkull.this.getMoveControl().hasWanted();
        }

        @Override
        public void start() {
            LivingEntity livingentity = NecromancerSkull.this.getOwner();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                NecromancerSkull.this.moveControl.setWantedPosition(vec3.x+(Math.cos(livingentity.yBodyRot)*.5), vec3.y+.5, vec3.z+(Math.sin(livingentity.yBodyRot)*.5), 1.0);
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingentity = NecromancerSkull.this.getOwner();
            if (livingentity != null) {
                double d0 = NecromancerSkull.this.distanceToSqr(livingentity);
                if (d0 < 2.0) {
                    Vec3 vec3 = livingentity.getEyePosition();
                    NecromancerSkull.this.moveControl.setWantedPosition(vec3.x+(Math.cos(livingentity.yBodyRot)*.5), vec3.y+.5, vec3.z+(Math.sin(livingentity.yBodyRot)*.5), 1.0);
                }
            }
        }
    }

    class SkullShootOnRemainsGoal extends Goal {

        private BlockPos targetPos;
        private boolean hasTarget;

        @Override
        public boolean canUse() {
            LivingEntity owner = NecromancerSkull.this.getOwner();
            return owner != null
                    && owner.isAlive()
                    && NecromancerSkull.this.distanceToSqr(owner) < 5.0;
        }

        @Override
        public void tick() {
            if (NecromancerSkull.this.getCooldown() <= 0 && NecromancerSkull.this.getOwner().getSouls() > 0) {
                if (hasTarget) {
                    NecromancerSkull.this.lookAt(EntityAnchorArgument.Anchor.EYES, targetPos.getCenter());
                    RevivingSoul soul = new RevivingSoul(NecromancerSkull.this.level(), NecromancerSkull.this);
                    double d0 = targetPos.getX()+.5 - NecromancerSkull.this.getX();
                    double d1 = targetPos.getY() - soul.getY();
                    double d2 = targetPos.getZ()+.5 - NecromancerSkull.this.getZ();
                    double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                    soul.shoot(d0, (d1 + d3 * 0.025) - .2, d2, 0.8F, 0F);
                    NecromancerSkull.this.playSound(SoundEvents.SOUL_ESCAPE.value(), 1.0F, 1.0F / (NecromancerSkull.this.getRandom().nextFloat() * 0.4F + 0.8F));
                    NecromancerSkull.this.level().addFreshEntity(soul);
                    NecromancerSkull.this.setCooldown(100);
                    NecromancerSkull.this.getOwner().setSouls(NecromancerSkull.this.getOwner().getSouls()-1);
                    hasTarget = false;
                } else {
                    Stream<BlockEntity> blockEntities = BlockPos.betweenClosedStream(NecromancerSkull.this.getBoundingBox().inflate(15)).map(level()::getBlockEntity);
                    List<BlockEntity> list = blockEntities.toList();
                    if (!list.isEmpty()) {
                        for (BlockEntity blockEntity : list) {
                            if (blockEntity != null) {
                                if (blockEntity.getBlockState().getBlock() instanceof RemainsBlock) {
                                    targetPos = blockEntity.getBlockPos();
                                    hasTarget = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    class SkullMoveControl extends MoveControl {
        public SkullMoveControl(NecromancerSkull skull) {
            super(skull);
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - NecromancerSkull.this.getX(), this.wantedY - NecromancerSkull.this.getY(), this.wantedZ - NecromancerSkull.this.getZ());
                double d0 = vec3.length();
                if (d0 < NecromancerSkull.this.getBoundingBox().getSize()) {
                    this.operation = MoveControl.Operation.WAIT;
                    NecromancerSkull.this.setDeltaMovement(NecromancerSkull.this.getDeltaMovement().scale(0.5));
                } else {
                    NecromancerSkull.this.setDeltaMovement(NecromancerSkull.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05 / d0)));
                    if (NecromancerSkull.this.getTarget() == null) {
                        Vec3 vec31 = NecromancerSkull.this.getDeltaMovement();
                        NecromancerSkull.this.setYRot(-((float) Mth.atan2(vec31.x, vec31.z)) * (180.0F / (float)Math.PI));
                        NecromancerSkull.this.yBodyRot = NecromancerSkull.this.getYRot();
                    } else {
                        double d2 = NecromancerSkull.this.getTarget().getX() - NecromancerSkull.this.getX();
                        double d1 = NecromancerSkull.this.getTarget().getZ() - NecromancerSkull.this.getZ();
                        NecromancerSkull.this.setYRot(-((float)Mth.atan2(d2, d1)) * (180.0F / (float)Math.PI));
                        NecromancerSkull.this.yBodyRot = NecromancerSkull.this.getYRot();
                    }
                }
            }
        }
    }
}