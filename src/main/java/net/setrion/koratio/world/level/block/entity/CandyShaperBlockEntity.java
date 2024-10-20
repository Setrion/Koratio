package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.fluids.capability.FluidContainer;
import net.setrion.koratio.fluids.capability.MultiFluidContainer;
import net.setrion.koratio.registry.KoratioBlockEntityType;
import net.setrion.koratio.registry.KoratioTags;
import net.setrion.koratio.world.inventory.CandyShaperMenu;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = Koratio.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CandyShaperBlockEntity extends AbstractFluidTankBlockEntity implements MenuProvider, Container {

    protected NonNullList<ItemStack> items;

    public CandyShaperBlockEntity(BlockPos pos, BlockState blockState) {
        super(KoratioBlockEntityType.CANDY_SHAPER.get(), pos, blockState);
        this.items = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    @SubscribeEvent
    private static void registerCapability(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                KoratioBlockEntityType.CANDY_SHAPER.get(),
                (be, context) -> be.FLUID_TANK);
    }

    private final MultiFluidContainer<?> FLUID_TANK = new MultiFluidContainer<>(
            new FluidContainer(2000) {
                @Override
                public boolean isFluidValid(FluidStack stack) {
                    return stack.is(KoratioTags.Fluids.MOLTEN_SUGAR);
                }

                @Override
                protected void onContentsChanged() {
                    CandyShaperBlockEntity.this.sendUpdate();
                }
            },
            new FluidContainer(2000) {
                @Override
                public boolean isFluidValid(FluidStack stack) {
                    return stack.is(KoratioTags.Fluids.MOLTEN_SUGAR);
                }

                @Override
                protected void onContentsChanged() {
                    CandyShaperBlockEntity.this.sendUpdate();
                }
            });

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, items, registries);
        tag.put("Fluid", FLUID_TANK.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        items = NonNullList.withSize(items.size(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items, registries);
        FLUID_TANK.deserializeNBT(registries, tag.getCompound("Fluid"));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CandyShaperMenu(i, inventory, this, ContainerLevelAccess.create(player.level(), this.worldPosition));
    }

    @Override
    public int getContainerSize() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    public ItemStack removeItem(int slot, int amount) {
        ItemStack itemstack = ContainerHelper.removeItem(items, slot, amount);
        if (!itemstack.isEmpty()) {
            setChanged();
        }

        return itemstack;
    }

    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
        stack.limitSize(getMaxStackSize(stack));
        setChanged();
    }

    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    public void clearContent() {
        items.clear();
    }

    public void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public void sendUpdate() {
        setChanged();
        if(this.level != null) this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    public IFluidHandler getFluidHandler() {
        return this.FLUID_TANK;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.candy_shaper");
    }
}