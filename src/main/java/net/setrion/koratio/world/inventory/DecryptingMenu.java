package net.setrion.koratio.world.inventory;

import com.mojang.datafixers.util.Pair;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioCriteriaTriggers;
import net.setrion.koratio.registry.KoratioMenuTypes;
import net.setrion.koratio.scroll.Scroll;
import net.setrion.koratio.scroll.ScrollUtils;
import net.setrion.koratio.world.item.DecryptingBookItem;
import net.setrion.koratio.world.item.ScrollItem;

public class DecryptingMenu extends AbstractContainerMenu {
	
	public static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("textures/atlas/blocks.png");
	public static final ResourceLocation EMPTY_SCROLL_SLOT = new ResourceLocation(Koratio.MOD_ID, "item/empty_scroll_slot");
	public static final ResourceLocation EMPTY_BOOK_SLOT = new ResourceLocation(Koratio.MOD_ID, "item/empty_book_slot");
	public static final ResourceLocation EMPTY_PAPER_SLOT = new ResourceLocation(Koratio.MOD_ID, "item/empty_paper_slot");
	private final ContainerLevelAccess access;
	private final DataSlot chance = DataSlot.standalone();
	private final DataSlot cost = DataSlot.standalone();
	
	final Container slot = new SimpleContainer(3) {
		public void setChanged() {
			super.setChanged();
			DecryptingMenu.this.slotsChanged(this);
		}
	};

	public DecryptingMenu(int id, Inventory inventory) {
		this(id, inventory, ContainerLevelAccess.NULL);
	}
	
	public DecryptingMenu(int id, Inventory inventory, ContainerLevelAccess access) {
		super(KoratioMenuTypes.DECRYPTING.get(), id);
		this.access = access;
		this.addSlot(new Slot(slot, 0, 73, 22) {
			@Override
			public int getMaxStackSize() {
				return 1;
			}
			
			@Override
			public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
				return Pair.of(DecryptingMenu.BLOCK_ATLAS, DecryptingMenu.EMPTY_SCROLL_SLOT);
			}
			
			@Override
			public boolean mayPlace(ItemStack stack) {
				if (stack.getItem() instanceof ScrollItem scroll && ScrollUtils.hasScrollData(stack)) {
					return !ScrollUtils.isEncrypted(stack);
				}
				return false;
			}
		});
		this.addSlot(new Slot(slot, 1, 73, 46) {
			@Override
			public int getMaxStackSize() {
				return 1;
			}
			
			@Override
			public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
				return Pair.of(DecryptingMenu.BLOCK_ATLAS, DecryptingMenu.EMPTY_BOOK_SLOT);
			}
			
			@Override
			public boolean mayPlace(ItemStack stack) {
				if (stack.getItem() instanceof DecryptingBookItem) {
					return true;
				}
				return false;
			}
		});
		this.addSlot(new Slot(slot, 2, 152, 54) {
			
			@Override
			public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
				return Pair.of(DecryptingMenu.BLOCK_ATLAS, DecryptingMenu.EMPTY_PAPER_SLOT);
			}
			
			@Override
			public boolean mayPlace(ItemStack stack) {
				if (stack.getItem() == Items.PAPER) {
					return true;
				}
				return false;
			}
		});
		for(int l = 0; l < 3; ++l) {
			for(int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(inventory, k + l * 9 + 9, 8 + k * 18, 84 + l * 18));
			}
		}

		for(int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 142));
		}
		this.addDataSlot(chance);
		this.addDataSlot(cost);
	}
	
	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (getSlot(0).getItem().getItem() instanceof ScrollItem && ScrollUtils.isEncrypted(getSlot(0).getItem())) {
			chance.set((int)(5 * id));
			cost.set(id);
			if (getSlot(1).getItem().getItem() instanceof DecryptingBookItem book) {
				chance.set(chance.get() + book.getPower());
			}
			boolean flag = player.getAbilities().instabuild;
			if (flag || (chance.get() > 0 && cost.get() > 0 && player.experienceLevel >= cost.get() && getSlot(2).getItem().getCount() >= 4)) {
				if (flag || player.getRandom().nextInt(100/chance.get()) == 0) {
					getSlot(0).set(ScrollUtils.decryptScroll(getSlot(0).getItem()));
					Scroll scroll = ScrollUtils.getScroll(getSlot(0).getItem());
					KoratioCriteriaTriggers.DECRYPTED_SCROLL.trigger((ServerPlayer) player, scroll.getName(), scroll.getType());
				}
				if (!flag) {
					player.giveExperienceLevels(-this.getCost());
					getSlot(2).remove(4);
					getSlot(1).remove(1);
				}
				return true;
			}
		}
		return super.clickMenuButton(player, id);
	}
	
	public void removed(Player player) {
		super.removed(player);
		if (!player.level().isClientSide) {
			for (int i = 0; i <= 2; i++) {
				ItemStack itemstack = this.slot.removeItem(i, this.slot.getMaxStackSize());
				if (!itemstack.isEmpty()) {
					player.drop(itemstack, false);
				}
			}
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (this.moveItemStackTo(itemstack1, 0, 1, false)) { //Forge Fix Shift Clicking in beacons with stacks larger then 1.
				return ItemStack.EMPTY;
			} else if (this.moveItemStackTo(itemstack1, 1, 2, false)) {
				return ItemStack.EMPTY;
			} else if (index >= 2 && index <= 28) {
				if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 29 && index < 38) {
				if (!this.moveItemStackTo(itemstack1, 1, 29, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 1, 38, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean stillValid(Player player) {
		return super.stillValid(access, player, KoratioBlocks.DECRYPTING_TABLE.get());
	}
	
	public int getCost() {
		return this.cost.get();
	}
	
	public void setCost(int amount) {
		this.cost.set(amount);
	}
	
	public int getChance() {
		return this.chance.get();
	}
	
	public void setChance(int amount) {
		this.chance.set(amount);
	}
}