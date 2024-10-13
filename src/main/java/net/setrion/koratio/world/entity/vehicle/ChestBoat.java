package net.setrion.koratio.world.entity.vehicle;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootTable;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioItems;

import javax.annotation.Nullable;

public class ChestBoat extends Boat implements HasCustomInventoryScreen, ContainerEntity {
	private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
	@Nullable
	private ResourceKey<LootTable> lootTable;
	private long lootTableSeed;

	public ChestBoat(EntityType<? extends Boat> type, Level level) {
		super(type, level);
	}

	public ChestBoat(Level level, double x, double y, double z) {
		this(KoratioEntityType.CHEST_BOAT.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

    protected int getMaxPassengers() {
		return 1;
	}

	protected void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.addChestVehicleSaveData(tag, this.registryAccess());
	}

	protected void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.readChestVehicleSaveData(tag, this.registryAccess());
	}

	public void destroy(DamageSource source) {
		super.destroy(source);
		this.chestVehicleDestroyed(source, this.level(), this);
	}

	public void remove(Entity.RemovalReason reason) {
		if (!this.level().isClientSide() && reason.shouldDestroy()) {
			Containers.dropContents(this.level(), this, this);
		}

		super.remove(reason);
	}

	public InteractionResult interact(Player p_219898_, InteractionHand p_219899_) {
		if (this.canAddPassenger(p_219898_) && !p_219898_.isSecondaryUseActive()) {
			return super.interact(p_219898_, p_219899_);
		} else {
			InteractionResult interactionresult = this.interactWithContainerVehicle(p_219898_);
			if (interactionresult.consumesAction()) {
				this.gameEvent(GameEvent.CONTAINER_OPEN, p_219898_);
				PiglinAi.angerNearbyPiglins(p_219898_, true);
			}

			return interactionresult;
		}
	}

	public void openCustomInventoryScreen(Player player) {
		player.openMenu(this);
		if (!player.level().isClientSide()) {
			this.gameEvent(GameEvent.CONTAINER_OPEN, player);
			PiglinAi.angerNearbyPiglins(player, true);
		}
	}

	public Item getDropItem() {
        return switch (this.getVariant()) {
            case VARESO -> KoratioItems.VARESO_CHEST_BOAT.get();
            case RUGONA -> KoratioItems.RUGONA_CHEST_BOAT.get();
            case ELVEN -> KoratioItems.ELVEN_CHEST_BOAT.get();
            case BLUE_ELVEN -> KoratioItems.BLUE_ELVEN_CHEST_BOAT.get();
            case CYAN_ELVEN -> KoratioItems.CYAN_ELVEN_CHEST_BOAT.get();
            case GREEN_ELVEN -> KoratioItems.GREEN_ELVEN_CHEST_BOAT.get();
            default -> KoratioItems.PANGO_CHEST_BOAT.get();
        };
	}

	public void clearContent() {
		this.clearChestVehicleContent();
	}

	public int getContainerSize() {
		return 27;
	}

	public ItemStack getItem(int slot) {
		return this.getChestVehicleItem(slot);
	}

	public ItemStack removeItem(int slot, int amount) {
		return this.removeChestVehicleItem(slot, amount);
	}

	public ItemStack removeItemNoUpdate(int slot) {
		return this.removeChestVehicleItemNoUpdate(slot);
	}

	public void setItem(int slot, ItemStack stack) {
		this.setChestVehicleItem(slot, stack);
	}

	public SlotAccess getSlot(int slot) {
		return this.getChestVehicleSlot(slot);
	}

	public void setChanged() {
	}

	public boolean stillValid(Player player) {
		return this.isChestVehicleStillValid(player);
	}

	@Nullable
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		if (this.lootTable != null && player.isSpectator()) {
			return null;
		} else {
			this.unpackLootTable(inventory.player);
			return ChestMenu.threeRows(id, inventory, this);
		}
	}

	public void unpackLootTable(@Nullable Player player) {
		this.unpackChestVehicleLootTable(player);
	}

	@Nullable
	public ResourceKey<LootTable> getLootTable() {
		return this.lootTable;
	}

	public void setLootTable(@Nullable ResourceKey<LootTable> lootTable) {
		this.lootTable = lootTable;
	}

	public long getLootTableSeed() {
		return this.lootTableSeed;
	}

	public void setLootTableSeed(long seed) {
		this.lootTableSeed = seed;
	}

	public NonNullList<ItemStack> getItemStacks() {
		return this.itemStacks;
	}

	public void clearItemStacks() {
		this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	}
}