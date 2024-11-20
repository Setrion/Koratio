package net.setrion.koratio.world.entity.demonic;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioParticles;

import javax.annotation.Nullable;
import java.util.Objects;

public class DemonicSkeletonHorse extends AbstractHorse {
    private static final EntityDimensions BABY_DIMENSIONS;

    public DemonicSkeletonHorse(EntityType<? extends DemonicSkeletonHorse> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createBaseHorseAttributes().add(Attributes.MAX_HEALTH, 15.0).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224);
    }

    public static boolean checkSkeletonHorseSpawnRules(EntityType<? extends Animal> animal, LevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        return !EntitySpawnReason.isSpawner(spawnType) ? Animal.checkAnimalSpawnRules(animal, level, spawnType, pos, random) : EntitySpawnReason.ignoresLightRequirements(spawnType) || isBrightEnoughToSpawn(level, pos);
    }

    protected void randomizeAttributes(RandomSource random) {
        AttributeInstance var10000 = this.getAttribute(Attributes.JUMP_STRENGTH);
        Objects.requireNonNull(random);
        var10000.setBaseValue(generateJumpStrength(random::nextDouble));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_HORSE_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_HORSE_HURT;
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return KoratioEntityType.DEMONIC_SKELETON_HORSE.get().create(level, EntitySpawnReason.BREEDING);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return !this.isTamed() ? InteractionResult.PASS : super.mobInteract(player, hand);
    }

    protected void addBehaviourGoals() {
    }

    public void aiStep() {
        if (this.level().isClientSide) {
            for(int i = 0; i < 1; ++i) {
                this.level().addParticle(KoratioParticles.DEMONIC_FIRE_FLAME.get(), this.getRandomX(0.6), this.getRandomY() - 0.25, this.getRandomZ(0.6), 0, 0, 0);
            }
        }

        super.aiStep();
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        return this.isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    static {
        BABY_DIMENSIONS = KoratioEntityType.DEMONIC_SKELETON_HORSE.get().getDimensions().withAttachments(EntityAttachments.builder().attach(EntityAttachment.PASSENGER, 0.0F, KoratioEntityType.DEMONIC_SKELETON_HORSE.get().getHeight() - 0.03125F, 0.0F)).scale(0.5F);
    }
}