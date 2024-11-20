package net.setrion.koratio.world.entity.demonic;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioParticles;
import net.setrion.koratio.util.EntityUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Necromancer extends Monster {

    protected static final EntityDataAccessor<Optional<UUID>> DATA_SKULL_UUID_ID;
    protected static final EntityDataAccessor<Integer> DATA_SOULS_ID;

    public Necromancer(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SKULL_UUID_ID, Optional.empty());
        builder.define(DATA_SOULS_ID, 0);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.2F).add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        List<Entity> entities = level().getEntities((Entity) null, getBoundingBox().inflate(10), entity -> entity.getType().is(EntityTypeTags.UNDEAD));
        int undead = entities.size();
        if (!entities.isEmpty()) {
            addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, (int) Math.min(undead*0.5, 3)));
            if (undead >= 5) {
                for (Entity entity : entities) {
                    if (entity instanceof Mob mob) {
                        mob.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, 4));
                    }
                }
            }
        }
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (getSkullUUID() != null) {
            compound.putUUID("Skull", getSkullUUID());
        }
        compound.putInt("Souls", getSouls());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        UUID uuid;
        if (compound.hasUUID("Skull")) {
            uuid = compound.getUUID("Skull");
        } else {
            String s = compound.getString("Skull");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(getServer(), s);
        }

        if (uuid != null) {
            setSkullUUID(uuid);
        }
    }

    @javax.annotation.Nullable
    public UUID getSkullUUID() {
        return (UUID)((Optional<?>)entityData.get(DATA_SKULL_UUID_ID)).orElse(null);
    }

    public void setSkullUUID(@javax.annotation.Nullable UUID uuid) {
        entityData.set(DATA_SKULL_UUID_ID, Optional.ofNullable(uuid));
    }

    @javax.annotation.Nullable
    public LivingEntity getSkull() {
        UUID uuid = getSkullUUID();
        if (uuid == null) {
            return null;
        } else {
            List<Entity> list = EntityUtils.getNecromancerSkull(level(), uuid);
            if (!list.isEmpty()) {
                Entity entity = list.get(0);
                if (entity instanceof NecromancerSkull skull) {
                    return skull;
                }
            }
        }
        return null;
    }

    public void setSouls(int amount) {
        entityData.set(DATA_SOULS_ID, amount);
    }

    public int getSouls() {
        return entityData.get(DATA_SOULS_ID);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnType, @Nullable SpawnGroupData spawnGroupData) {
        NecromancerSkull skull = KoratioEntityType.NECROMANCER_SKULL.get().create(level.getLevel(), EntitySpawnReason.MOB_SUMMONED);
        skull.setOwnerUUID(uuid);
        skull.moveTo(getX(), getY()+2, getZ());
        level.addFreshEntity(skull);
        setSkullUUID(skull.getUUID());
        setSouls(random.nextInt(50));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public void die(DamageSource damageSource) {
        if (!level().isClientSide()) {
            if (getSkull() != null && getSkull().isAlive()) {
                getSkull().kill((ServerLevel) level());
            }
        }
        level().playSound(this, getOnPos().above(), SoundEvents.SOUL_ESCAPE.value(), SoundSource.HOSTILE, 1.0F, 1.0F);
        for (int i = 0; i < getSouls(); i++) {
            level().addAlwaysVisibleParticle(KoratioParticles.DEMONIC_SOUL.get(), false, getX(), getY()+1, getZ(), (random.nextInt(-2, 2)/random.nextFloat())/20F, (random.nextInt(-2, 2)/random.nextFloat())/20F, (random.nextInt(-2, 2)/random.nextFloat())/20F);
        }
        super.die(damageSource);
    }

    static {
        DATA_SKULL_UUID_ID = SynchedEntityData.defineId(Necromancer.class, EntityDataSerializers.OPTIONAL_UUID);
        DATA_SOULS_ID = SynchedEntityData.defineId(Necromancer.class, EntityDataSerializers.INT);
    }
}