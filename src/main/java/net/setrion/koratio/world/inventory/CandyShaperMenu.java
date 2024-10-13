package net.setrion.koratio.world.inventory;

import com.google.common.collect.Lists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioFluids;
import net.setrion.koratio.registry.KoratioMenuTypes;
import net.setrion.koratio.registry.KoratioRecipeType;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;

import java.util.List;

public class CandyShaperMenu extends AbstractFluidContainerMenu {
    private final ContainerLevelAccess access;
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final Level level;
    private List<RecipeHolder<CandyShaperRecipe>> recipes;
    long lastSoundTime;
    final Slot resultSlot;
    final FluidSlot moltenSugarSlot;
    final FluidSlot moltenBlueSugarSlot;
    final FluidSlot moltenGreenSugarSlot;
    final FluidSlot moltenYellowSugarSlot;
    final FluidSlot moltenRedSugarSlot;
    final ResultContainer resultContainer = new ResultContainer();
    protected final IFluidHandler fluidTank;
    protected final BlockEntity blockEntity;
    Runnable slotUpdateListener = () -> {
    };

    public CandyShaperMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), ContainerLevelAccess.NULL);
    }

    public CandyShaperMenu(int id, Inventory inventory, BlockEntity blockEntity, final ContainerLevelAccess access) {
        super(KoratioMenuTypes.CANDY_SHAPER.get(), id);
        this.access = access;
        this.recipes = Lists.newArrayList();
        this.level = inventory.player.level();
        this.blockEntity = blockEntity;
        this.fluidTank = level.getCapability(Capabilities.FluidHandler.BLOCK, blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, null);
        this.moltenSugarSlot = addFluidSlot(new FluidSlot(fluidTank, 0, 153, 43) {
            public boolean mayPlace(FluidStack stack) {
                return stack.is(KoratioFluids.MOLTEN_SUGAR.get());
            }
        });
        this.moltenBlueSugarSlot = addFluidSlot(new FluidSlot(fluidTank, 1, 153, 43) {
            public boolean mayPlace(FluidStack stack) {
                return stack.is(KoratioFluids.MOLTEN_BLUE_SUGAR.get());
            }
        });
        this.moltenGreenSugarSlot = addFluidSlot(new FluidSlot(fluidTank, 2, 153, 43) {
            public boolean mayPlace(FluidStack stack) {
                return stack.is(KoratioFluids.MOLTEN_GREEN_SUGAR.get());
            }
        });
        this.moltenYellowSugarSlot = addFluidSlot(new FluidSlot(fluidTank, 3, 153, 43) {
            public boolean mayPlace(FluidStack stack) {
                return stack.is(KoratioFluids.MOLTEN_YELLOW_SUGAR.get());
            }
        });
        this.moltenRedSugarSlot = addFluidSlot(new FluidSlot(fluidTank, 4, 153, 43) {
            public boolean mayPlace(FluidStack stack) {
                return stack.is(KoratioFluids.MOLTEN_RED_SUGAR.get());
            }
        });
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            public void onTake(Player player, ItemStack stack) {
                stack.onCraftedBy(player.level(), player, stack.getCount());
                RecipeHolder<CandyShaperRecipe> candyshaper_recipe = CandyShaperMenu.this.recipes.get(CandyShaperMenu.this.selectedRecipeIndex.get());
                CandyShaperMenu.this.takeRecipeIngredients(candyshaper_recipe.value());
                System.out.println(hasEnoughForSameRecipe(candyshaper_recipe));
                if(!hasEnoughForSameRecipe(candyshaper_recipe)) {
                    CandyShaperMenu.this.setupRecipeList();
                } else {
                    CandyShaperMenu.this.setupResultSlot();
                }

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
        this.addDataSlot(this.selectedRecipeIndex);
        setupRecipeList();
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public void slotsChanged(Container container) {
        setupRecipeList();
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }

    public List<RecipeHolder<CandyShaperRecipe>> getRecipes() {
        return this.recipes;
    }

    public int getNumRecipes() {
        return this.recipes.size();
    }

    public boolean stillValid(Player player) {
        return stillValid(this.access, player, KoratioBlocks.CANDY_SHAPER.get());
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.isValidRecipeIndex(id)) {
            this.selectedRecipeIndex.set(id);
            this.setupResultSlot();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int index) {
        return index >= 0 && index < this.recipes.size();
    }

    private boolean hasEnoughForSameRecipe(RecipeHolder<CandyShaperRecipe> recipe) {
        int neededWhite = recipe.value().getFluidIngredients().get(0).amount();
        int neededBlue = recipe.value().getFluidIngredients().get(1).amount();
        int neededGreen = recipe.value().getFluidIngredients().get(2).amount();
        int neededYellow = recipe.value().getFluidIngredients().get(3).amount();
        int neededRed = recipe.value().getFluidIngredients().get(4).amount();
        return (CandyShaperMenu.this.getFluidSlot(0).getFluid().getAmount() >= neededWhite && CandyShaperMenu.this.getFluidSlot(1).getFluid().getAmount() >= neededBlue && CandyShaperMenu.this.getFluidSlot(2).getFluid().getAmount() >= neededGreen && CandyShaperMenu.this.getFluidSlot(3).getFluid().getAmount() >= neededYellow && CandyShaperMenu.this.getFluidSlot(4).getFluid().getAmount() >= neededRed);
    }

    public void setupRecipeList() {
        this.recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!this.level.getRecipeManager().getAllRecipesFor(KoratioRecipeType.CANDY_SHAPING.get()).isEmpty()) {
            for (RecipeHolder<CandyShaperRecipe> recipeHolder : this.level.getRecipeManager().getAllRecipesFor(KoratioRecipeType.CANDY_SHAPING.get())) {
                CandyShaperRecipe recipe = recipeHolder.value();
                if ((getFluidSlot(0).getFluid().getAmount() >= recipe.getFluidIngredients().get(0).amount() && getFluidSlot(1).getFluid().getAmount() >= recipe.getFluidIngredients().get(1).amount() && getFluidSlot(2).getFluid().getAmount() >= recipe.getFluidIngredients().get(2).amount() && getFluidSlot(3).getFluid().getAmount() >= recipe.getFluidIngredients().get(3).amount() && getFluidSlot(4).getFluid().getAmount() >= recipe.getFluidIngredients().get(4).amount())) {
                    recipes.add(recipeHolder);
                }
            }
        }
    }

    public void registerUpdateListener(Runnable runnable) {
        this.slotUpdateListener = runnable;
    }

    void setupResultSlot() {
        if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
            RecipeHolder<CandyShaperRecipe> candyshaper_recipe = this.recipes.get(this.selectedRecipeIndex.get());
            if (getFluidSlot(0).getFluid().getAmount() >= candyshaper_recipe.value().getFluidIngredients().get(0).amount() && getFluidSlot(1).getFluid().getAmount() >= candyshaper_recipe.value().getFluidIngredients().get(1).amount() && getFluidSlot(2).getFluid().getAmount() >= candyshaper_recipe.value().getFluidIngredients().get(2).amount() && getFluidSlot(3).getFluid().getAmount() >= candyshaper_recipe.value().getFluidIngredients().get(3).amount() && getFluidSlot(4).getFluid().getAmount() >= candyshaper_recipe.value().getFluidIngredients().get(4).amount()) {

                ItemStack itemstack = candyshaper_recipe.value().getResultItem(this.level.registryAccess()).copy();
                if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
                    this.resultContainer.setRecipeUsed(candyshaper_recipe);
                    this.resultSlot.set(itemstack);
                } else {
                    this.resultSlot.set(ItemStack.EMPTY);
                }
            } else {
                this.resultSlot.set(ItemStack.EMPTY);
            }
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    public MenuType<?> getType() {
        return KoratioMenuTypes.CANDY_SHAPER.get();
    }

    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultContainer && super.canTakeItemForPickAll(stack, slot);
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
        Fluid sugar = recipe.getFluidIngredients().get(0).ingredient().getStacks()[0].getFluid();
        int blueSugarAmount = recipe.getFluidIngredients().get(1).amount();
        Fluid blueSugar = recipe.getFluidIngredients().get(1).ingredient().getStacks()[0].getFluid();
        int greenSugarAmount = recipe.getFluidIngredients().get(2).amount();
        Fluid greenSugar = recipe.getFluidIngredients().get(2).ingredient().getStacks()[0].getFluid();
        int yellowSugarAmount = recipe.getFluidIngredients().get(3).amount();
        Fluid yellowSugar = recipe.getFluidIngredients().get(3).ingredient().getStacks()[0].getFluid();
        int redSugarAmount = recipe.getFluidIngredients().get(4).amount();
        Fluid redSugar = recipe.getFluidIngredients().get(4).ingredient().getStacks()[0].getFluid();
        fluidTank.drain(new FluidStack(sugar, sugarAmount), IFluidHandler.FluidAction.EXECUTE);
        fluidTank.drain(new FluidStack(blueSugar, blueSugarAmount), IFluidHandler.FluidAction.EXECUTE);
        fluidTank.drain(new FluidStack(greenSugar, greenSugarAmount), IFluidHandler.FluidAction.EXECUTE);
        fluidTank.drain(new FluidStack(yellowSugar, yellowSugarAmount), IFluidHandler.FluidAction.EXECUTE);
        fluidTank.drain(new FluidStack(redSugar, redSugarAmount), IFluidHandler.FluidAction.EXECUTE);
    }

    public void removed(Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
    }
}