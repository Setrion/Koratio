package net.setrion.koratio.world.entity.demonic;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.setrion.koratio.registry.KoratioItems;

import java.time.LocalDate;
import java.util.function.Predicate;

public class DemonicSoldier extends Monster {
	
	private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (difficulty) -> {
		return difficulty == Difficulty.NORMAL;
	};
	private final BreakDoorGoal breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
	private boolean canBreakDoors;

	public DemonicSoldier(EntityType<? extends DemonicSoldier> type, Level level) {
		super(type, level);
	}
	
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.addBehaviourGoals();
	}

	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.27F).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}
	
	public boolean canBreakDoors() {
		return this.canBreakDoors;
	}

	public void setCanBreakDoors(boolean p_34337_) {
		if (this.supportsBreakDoorGoal() && GoalUtils.hasGroundPathNavigation(this)) {
			if (this.canBreakDoors != p_34337_) {
				this.canBreakDoors = p_34337_;
				((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(p_34337_);
				if (p_34337_) {
					this.goalSelector.addGoal(1, this.breakDoorGoal);
				} else {
					this.goalSelector.removeGoal(this.breakDoorGoal);
				}
			}
		} else if (this.canBreakDoors) {
			this.goalSelector.removeGoal(this.breakDoorGoal);
			this.canBreakDoors = false;
		}
	}

	protected boolean supportsBreakDoorGoal() {
		return true;
	}
	
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.2F : 0.1F)) {
			if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(KoratioItems.WITHER_BONE_AXE.get()));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(KoratioItems.WITHER_BONE_SWORD.get()));
			}
			this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(KoratioItems.WITHER_BONE_HELMET.get()));
			this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(KoratioItems.WITHER_BONE_CHESTPLATE.get()));
			this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(KoratioItems.WITHER_BONE_LEGGINGS.get()));
			this.setItemSlot(EquipmentSlot.FEET, new ItemStack(KoratioItems.WITHER_BONE_BOOTS.get()));
		} else {
			if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(KoratioItems.BONE_AXE.get()));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(KoratioItems.BONE_SWORD.get()));
			}
			this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(KoratioItems.BONE_HELMET.get()));
			this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(KoratioItems.BONE_CHESTPLATE.get()));
			this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(KoratioItems.BONE_LEGGINGS.get()));
			this.setItemSlot(EquipmentSlot.FEET, new ItemStack(KoratioItems.BONE_BOOTS.get()));
		}
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnType, @org.jetbrains.annotations.Nullable SpawnGroupData spawnGroupData) {
		RandomSource randomsource = level.getRandom();
		spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
		float f = difficulty.getSpecialMultiplier();
		this.setCanPickUpLoot(randomsource.nextFloat() < 0.55F * f);
		this.populateDefaultEquipmentSlots(randomsource, difficulty);
		this.populateDefaultEquipmentEnchantments(level, randomsource, difficulty);

		if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
			LocalDate localdate = LocalDate.now();
			int i = localdate.getDayOfMonth();
			int j = localdate.getMonth().getValue();
			if (j == 10 && i == 31 && randomsource.nextFloat() < 0.25F) {
				this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(randomsource.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
			}
		}

		this.setCanBreakDoors(this.supportsBreakDoorGoal());
		return spawnGroupData;
	}
}