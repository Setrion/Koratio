package net.setrion.koratio.world.entity.animal;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.event.EventHooks;
import net.setrion.koratio.registry.KoratioEntityType;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MagicalCat extends TamableAnimal {
	
	private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(MagicalCat.class, EntityDataSerializers.INT);
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
		builder.define(DATA_TYPE_ID, 0);
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("CatType", this.getCatType());
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setCatType(tag.getInt("CatType"));
	}
	
	public int getCatType() {
		return this.entityData.get(DATA_TYPE_ID);
	}

	public void setCatType(int id) {
		this.entityData.set(DATA_TYPE_ID, id);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		this.setCatType(this.random.nextInt(8));
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
						this.heal((float)item.getFoodProperties(itemstack, player).nutrition());
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
		MagicalCat unicorn_cat = KoratioEntityType.MAGICAL_CAT.get().create(level);
		UUID uuid = this.getOwnerUUID();
		if (uuid != null) {
			unicorn_cat.setOwnerUUID(uuid);
			unicorn_cat.setTame(true, false);
			unicorn_cat.setCatType(this.getCatType());
		}

		return unicorn_cat;
	}
	
	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!this.isTame()) {
			return false;
		} else if (!(animal instanceof MagicalCat unicorn_cat)) {
			return false;
		} else {
			if (!unicorn_cat.isTame()) {
				return false;
			} else if (unicorn_cat.isInSittingPose()) {
				return false;
			} else {
				return this.isInLove() && unicorn_cat.isInLove();
			}
		}
	}
}