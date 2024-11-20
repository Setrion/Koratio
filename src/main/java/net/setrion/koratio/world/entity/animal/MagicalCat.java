package net.setrion.koratio.world.entity.animal;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.event.EventHooks;
import net.setrion.koratio.registry.KoratioDataSerializers;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioRegistries;
import net.setrion.koratio.registry.KoratioTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class MagicalCat extends TamableAnimal implements VariantHolder<Holder<MagicalCatVariant>> {
	
	private static final EntityDataAccessor<Holder<MagicalCatVariant>> DATA_TYPE_ID = SynchedEntityData.defineId(MagicalCat.class, KoratioDataSerializers.MAGICAL_CAT_VARIANT.get());
	private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(MagicalCat.class, EntityDataSerializers.INT);

	private static final Ingredient TEMPT_INGREDIENT = Ingredient.of(Items.COD, Items.SALMON);
	@Nullable
	private TemptGoal temptgoal;

	public MagicalCat(EntityType<? extends MagicalCat> type, Level level) {
		super(type, level);
		this.setTame(false, false);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.MAX_HEALTH, 15.0D);
	}
	
	protected void registerGoals() {
		this.temptgoal = new TemptGoal(this, 0.6D, TEMPT_INGREDIENT, true);
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
		this.goalSelector.addGoal(3, temptgoal);
		this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F));
		this.goalSelector.addGoal(5, new BreedGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 10.0F));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_TYPE_ID, this.registryAccess().lookupOrThrow(KoratioRegistries.MAGICAL_CAT_VARIANT).getOrThrow(MagicalCatVariant.AKUMA));
		builder.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putString("variant", this.getVariant().unwrapKey().orElse(MagicalCatVariant.AKUMA).location().toString());
		tag.putByte("CollarColor", (byte)this.getCollarColor().getId());
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		Optional.ofNullable(ResourceLocation.tryParse(tag.getString("variant")))
				.map(location -> ResourceKey.create(KoratioRegistries.MAGICAL_CAT_VARIANT, location))
				.flatMap(key -> this.registryAccess().lookupOrThrow(KoratioRegistries.MAGICAL_CAT_VARIANT).get(key))
				.ifPresent(this::setVariant);
		if (tag.contains("CollarColor", 99)) {
			this.setCollarColor(DyeColor.byId(tag.getInt("CollarColor")));
		}
	}

	public Holder<MagicalCatVariant> getVariant() {
		return this.entityData.get(DATA_TYPE_ID);
	}

	public void setVariant(Holder<MagicalCatVariant> variant) {
		this.entityData.set(DATA_TYPE_ID, variant);
	}

	public DyeColor getCollarColor() {
		return DyeColor.byId(this.entityData.get(DATA_COLLAR_COLOR));
	}

	private void setCollarColor(DyeColor color) {
		this.entityData.set(DATA_COLLAR_COLOR, color.getId());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnType, @Nullable SpawnGroupData spawnGroupData) {
		level.registryAccess().lookupOrThrow(KoratioRegistries.MAGICAL_CAT_VARIANT).getRandomElementOf(KoratioTags.MagicalCatVariants.DEFAULT_SPAWNS, level.getRandom()).ifPresent(this::setVariant);
		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();
		if (this.level().isClientSide) {
			if (this.isTame() && this.isOwnedBy(player)) {
				return InteractionResult.SUCCESS;
			} else {
				return !this.isFood(itemstack) || !(this.getHealth() < this.getMaxHealth()) && this.isTame() ? InteractionResult.PASS : InteractionResult.SUCCESS;
			}
		} else {
			if (this.isTame()) {
				if (this.isOwnedBy(player)) {
					if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
						this.usePlayerItem(player, hand, itemstack);
						FoodProperties foodproperties = itemstack.get(DataComponents.FOOD);
						this.heal(foodproperties != null ? (float)foodproperties.nutrition() : 1.0F);
						return InteractionResult.CONSUME;
					}
					InteractionResult interactionresult = super.mobInteract(player, hand);
					if (!interactionresult.consumesAction() || this.isBaby()) {
						this.setOrderedToSit(!this.isOrderedToSit());
					}

					return interactionresult;
				}
			} else if (this.isFood(itemstack)) {
				this.usePlayerItem(player, hand, itemstack);
				if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, player)) {
					this.tame(player);
					this.setOrderedToSit(true);
					this.level().broadcastEntityEvent(this, (byte)7);
				} else {
					this.level().broadcastEntityEvent(this, (byte)6);
				}

				this.setPersistenceRequired();
				return InteractionResult.CONSUME;
			}

			InteractionResult interactionresult1 = super.mobInteract(player, hand);
			if (interactionresult1.consumesAction()) {
				this.setPersistenceRequired();
			}

			return interactionresult1;
		}
	}
	
	@Override
	public boolean isFood(ItemStack stack) {
		return TEMPT_INGREDIENT.test(stack);
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.CAT_DEATH;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource p_21239_) {
		return SoundEvents.CAT_HURT;
	}
	
	protected void usePlayerItem(Player player, InteractionHand hand, ItemStack stack) {
		if (this.isFood(stack)) {
			this.playSound(SoundEvents.CAT_EAT, 1.0F, 1.0F);
		}

		super.usePlayerItem(player, hand, stack);
	}
	
	public void tick() {
		super.tick();
		if (this.temptgoal != null && this.temptgoal.isRunning() && !this.isTame() && this.tickCount % 100 == 0) {
			this.playSound(SoundEvents.CAT_BEG_FOR_FOOD, 1.0F, 1.0F);
		}
	}
	
	public MagicalCat getBreedOffspring(ServerLevel level, AgeableMob mob) {
		MagicalCat cat = KoratioEntityType.MAGICAL_CAT.get().create(level, EntitySpawnReason.BREEDING);
		UUID uuid = this.getOwnerUUID();
		if (uuid != null) {
			cat.setOwnerUUID(uuid);
			cat.setTame(true, false);
			cat.setVariant(this.getVariant());
		}

		return cat;
	}
	
	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!this.isTame()) {
			return false;
		} else if (!(animal instanceof MagicalCat cat)) {
			return false;
		} else {
			if (!cat.isTame()) {
				return false;
			} else if (cat.isInSittingPose()) {
				return false;
			} else {
				return this.isInLove() && cat.isInLove();
			}
		}
	}
}