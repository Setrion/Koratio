package net.setrion.koratio.world.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.setrion.koratio.registry.*;
import net.setrion.koratio.world.item.CandyTemplateItem;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import net.setrion.koratio.world.level.block.entity.CandyShaperBlockEntity;

public class CandyShaperMenu extends AbstractFluidContainerMenu {
    private final ContainerLevelAccess access;
    private final Level level;
    long lastSoundTime;
    final Slot resultSlot;
    final FluidSlot mainFluidSlot;
    final FluidSlot secondFluidSlot;
    final Slot inputSlot;
    public final Container container = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            CandyShaperMenu.this.slotsChanged(this);
            CandyShaperMenu.this.slotUpdateListener.run();
        }
    };
    final ResultContainer resultContainer = new ResultContainer();
    protected final IFluidHandler fluidTank;
    protected final CandyShaperBlockEntity blockEntity;
    Runnable slotUpdateListener = () -> {
    };

    public CandyShaperMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, (CandyShaperBlockEntity) inventory.player.level().getBlockEntity(extraData.readBlockPos()), ContainerLevelAccess.NULL);
    }

    public CandyShaperMenu(int id, Inventory inventory, CandyShaperBlockEntity blockEntity, final ContainerLevelAccess access) {
        super(KoratioMenuTypes.CANDY_SHAPER.get(), id);
        this.access = access;
        this.level = inventory.player.level();
        this.blockEntity = blockEntity;
        this.fluidTank = level.getCapability(Capabilities.FluidHandler.BLOCK, blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, null);
        this.mainFluidSlot = addFluidSlot(new FluidSlot(fluidTank, 0, 153, 43) {
            public boolean mayPlace(FluidStack stack) {
                return stack.is(KoratioTags.Fluids.MOLTEN_SUGAR);
            }
        });
        this.secondFluidSlot = addFluidSlot(new FluidSlot(fluidTank, 1, 153, 43) {
            public boolean mayPlace(FluidStack stack) {
                return stack.is(KoratioTags.Fluids.MOLTEN_SUGAR);
            }
        });
        this.inputSlot = this.addSlot(new Slot(this.blockEntity, 0, 76, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof CandyTemplateItem;
            }

            @Override
            public void set(ItemStack stack) {
                CandyShaperMenu.this.blockEntity.setItem(0, stack);
                super.set(stack);
            }

            @Override
            public void setChanged() {
                CandyShaperMenu.this.blockEntity.setChanged();
            }
        });
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 148, 33) {
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            public void onTake(Player player, ItemStack stack) {
                stack.onCraftedBy(player.level(), player, stack.getCount());
                access.execute((level, pos) -> {
                    long l = level.getGameTime();
                    if (CandyShaperMenu.this.lastSoundTime != l) {
                        level.playSound(null, pos, SoundEvents.CANDLE_PLACE, SoundSource.BLOCKS, 2.0F, 1.5F);
                        CandyShaperMenu.this.lastSoundTime = l;
                    }
                });
                super.onTake(player, stack);
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public void slotsChanged(Container container) {
    }

    public boolean stillValid(Player player) {
        return stillValid(this.access, player, KoratioBlocks.CANDY_SHAPER.get());
    }

    public void registerUpdateListener(Runnable runnable) {
        this.slotUpdateListener = runnable;
    }

    public MenuType<?> getType() {
        return KoratioMenuTypes.CANDY_SHAPER.get();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            //Clicked from player inventory
            if (index < 36) {
                for(int i = index; i < this.slots.size(); i++) {
                    if(i == this.resultSlot.index) return ItemStack.EMPTY;
                    else if (!this.moveItemStackTo(itemstack1, 36, this.slots.size(), false)) {
                        return ItemStack.EMPTY;
                    }
                }
                //Clicked from opened container
            } else if (!this.moveItemStackTo(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    public void takeRecipeIngredients(CandyShaperRecipe recipe) {
        int sugarAmount = recipe.getFluidIngredients().get(0).amount();
        Fluid sugar = recipe.getFluidIngredients().get(0).ingredient().fluids().get(0).value();
        int blueSugarAmount = recipe.getFluidIngredients().get(1).amount();
        Fluid blueSugar = recipe.getFluidIngredients().get(1).ingredient().fluids().get(0).value();
        int greenSugarAmount = recipe.getFluidIngredients().get(2).amount();
        Fluid greenSugar = recipe.getFluidIngredients().get(2).ingredient().fluids().get(0).value();
        int yellowSugarAmount = recipe.getFluidIngredients().get(3).amount();
        Fluid yellowSugar = recipe.getFluidIngredients().get(3).ingredient().fluids().get(0).value();
        int redSugarAmount = recipe.getFluidIngredients().get(4).amount();
        Fluid redSugar = recipe.getFluidIngredients().get(4).ingredient().fluids().get(0).value();
        fluidTank.drain(new FluidStack(sugar, sugarAmount), IFluidHandler.FluidAction.EXECUTE);
        fluidTank.drain(new FluidStack(blueSugar, blueSugarAmount), IFluidHandler.FluidAction.EXECUTE);
        fluidTank.drain(new FluidStack(greenSugar, greenSugarAmount), IFluidHandler.FluidAction.EXECUTE);
        fluidTank.drain(new FluidStack(yellowSugar, yellowSugarAmount), IFluidHandler.FluidAction.EXECUTE);
        fluidTank.drain(new FluidStack(redSugar, redSugarAmount), IFluidHandler.FluidAction.EXECUTE);
    }
}