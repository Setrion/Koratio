package net.setrion.koratio.world.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEntityType;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class Mooshroom extends Cow implements Shearable, VariantHolder<Mooshroom.MushroomType> {

    private static final EntityDataAccessor<String> DATA_TYPE;
    @Nullable
    private SuspiciousStewEffects stewEffects;
    @Nullable
    private UUID lastLightningBoltUUID;

    public Mooshroom(EntityType<? extends Mooshroom> entityType, Level level) {
        super(entityType, level);
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        return level.getBlockState(pos.below()).is(Blocks.MYCELIUM) ? 10.0F : level.getPathfindingCostFromLightLevels(pos);
    }

    public static boolean checkMushroomSpawnRules(EntityType<Mooshroom> mushroomCow, LevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource randomSource) {
        return level.getBlockState(pos.below()).is(BlockTags.MOOSHROOMS_SPAWNABLE_ON) && isBrightEnoughToSpawn(level, pos);
    }

    public void thunderHit(ServerLevel level, LightningBolt lightning) {
        UUID uuid = lightning.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            this.setVariant(this.getVariant() == Mooshroom.MushroomType.PURPLE ? Mooshroom.MushroomType.GREEN : Mooshroom.MushroomType.PURPLE);
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }

    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TYPE, Mooshroom.MushroomType.PURPLE.type);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(Items.BOWL) && !this.isBaby()) {
            boolean flag = false;
            ItemStack itemstack2;
            if (this.stewEffects != null) {
                flag = true;
                itemstack2 = new ItemStack(Items.SUSPICIOUS_STEW);
                itemstack2.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, this.stewEffects);
                this.stewEffects = null;
            } else {
                itemstack2 = new ItemStack(Items.MUSHROOM_STEW);
            }

            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, itemstack2, false);
            player.setItemInHand(hand, itemstack1);
            SoundEvent soundevent;
            if (flag) {
                soundevent = SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY;
            } else {
                soundevent = SoundEvents.MOOSHROOM_MILK;
            }

            this.playSound(soundevent, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        } else if (this.getVariant() == Mooshroom.MushroomType.GREEN && itemstack.is(ItemTags.SMALL_FLOWERS)) {
            if (this.stewEffects != null) {
                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.SMOKE, this.getX() + this.random.nextDouble() / 2.0, this.getY(0.5), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
                }
            } else {
                Optional<SuspiciousStewEffects> optional = this.getEffectsFromItemStack(itemstack);
                if (optional.isEmpty()) {
                    return InteractionResult.PASS;
                }

                itemstack.consume(1, player);

                for(int j = 0; j < 4; ++j) {
                    this.level().addParticle(ParticleTypes.EFFECT, this.getX() + this.random.nextDouble() / 2.0, this.getY(0.5), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
                }

                this.stewEffects = optional.get();
                this.playSound(SoundEvents.MOOSHROOM_EAT, 2.0F, 1.0F);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    public void shear(ServerLevel level, SoundSource category, ItemStack stack) {
        this.level().playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, category, 1.0F, 1.0F);
        if (!this.level().isClientSide()) {
            if (!EventHooks.canLivingConvert(this, EntityType.COW, (timer) -> {
            })) {
                return;
            }

            Cow cow = EntityType.COW.create(this.level(), EntitySpawnReason.CONVERSION);
            if (cow != null) {
                EventHooks.onLivingConvert(this, cow);
                ((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
                this.discard();
                cow.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                cow.setHealth(this.getHealth());
                cow.yBodyRot = this.yBodyRot;
                if (this.hasCustomName()) {
                    cow.setCustomName(this.getCustomName());
                    cow.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistenceRequired()) {
                    cow.setPersistenceRequired();
                }

                cow.setInvulnerable(this.isInvulnerable());
                this.level().addFreshEntity(cow);

                for(int i = 0; i < 5; ++i) {
                    ItemEntity item = this.spawnAtLocation((ServerLevel) level(), new ItemStack(this.getVariant().blockState.getBlock()));
                    if (item != null) {
                        item.setNoPickUpDelay();
                    }
                }
            }
        }

    }

    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby();
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Type", this.getVariant().getSerializedName());
        if (this.stewEffects != null) {
            SuspiciousStewEffects.CODEC.encodeStart(NbtOps.INSTANCE, this.stewEffects).ifSuccess((p_297973_) -> {
                compound.put("stew_effects", p_297973_);
            });
        }

    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(Mooshroom.MushroomType.byType(compound.getString("Type")));
        if (compound.contains("stew_effects", 9)) {
            SuspiciousStewEffects.CODEC.parse(NbtOps.INSTANCE, compound.get("stew_effects")).ifSuccess((p_330058_) -> {
                this.stewEffects = p_330058_;
            });
        }

    }

    private Optional<SuspiciousStewEffects> getEffectsFromItemStack(ItemStack stack) {
        SuspiciousEffectHolder suspiciouseffectholder = SuspiciousEffectHolder.tryGet(stack.getItem());
        return suspiciouseffectholder != null ? Optional.of(suspiciouseffectholder.getSuspiciousEffects()) : Optional.empty();
    }

    public void setVariant(Mooshroom.MushroomType variant) {
        this.entityData.set(DATA_TYPE, variant.type);
    }

    public Mooshroom.MushroomType getVariant() {
        return Mooshroom.MushroomType.byType(this.entityData.get(DATA_TYPE));
    }

    @Nullable
    public Mooshroom getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Mooshroom mushroomcow = KoratioEntityType.MOOSHROOM.get().create(level, EntitySpawnReason.BREEDING);
        if (mushroomcow != null) {
            mushroomcow.setVariant(this.getOffspringType((Mooshroom)otherParent));
        }

        return mushroomcow;
    }

    private Mooshroom.MushroomType getOffspringType(Mooshroom mate) {
        Mooshroom.MushroomType mushroomcow$mushroomtype = this.getVariant();
        Mooshroom.MushroomType mushroomcow$mushroomtype1 = mate.getVariant();
        Mooshroom.MushroomType mushroomcow$mushroomtype2;
        if (mushroomcow$mushroomtype == mushroomcow$mushroomtype1 && this.random.nextInt(1024) == 0) {
            mushroomcow$mushroomtype2 = mushroomcow$mushroomtype == Mooshroom.MushroomType.GREEN ? Mooshroom.MushroomType.PURPLE : Mooshroom.MushroomType.GREEN;
        } else {
            mushroomcow$mushroomtype2 = this.random.nextBoolean() ? mushroomcow$mushroomtype : mushroomcow$mushroomtype1;
        }

        return mushroomcow$mushroomtype2;
    }

    static {
        DATA_TYPE = SynchedEntityData.defineId(Mooshroom.class, EntityDataSerializers.STRING);
    }

    public static enum MushroomType implements StringRepresentable {
        PURPLE("purple", KoratioBlocks.PURPLE_MUSHROOM.get().defaultBlockState()),
        GREEN("green", KoratioBlocks.GREEN_MUSHROOM.get().defaultBlockState());

        public static final StringRepresentable.EnumCodec<Mooshroom.MushroomType> CODEC = StringRepresentable.fromEnum(Mooshroom.MushroomType::values);
        final String type;
        final BlockState blockState;

        private MushroomType(String type, BlockState blockState) {
            this.type = type;
            this.blockState = blockState;
        }

        public BlockState getBlockState() {
            return this.blockState;
        }

        public String getSerializedName() {
            return this.type;
        }

        static Mooshroom.MushroomType byType(String name) {
            return CODEC.byName(name, PURPLE);
        }
    }
}