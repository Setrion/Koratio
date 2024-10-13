package net.setrion.koratio.world.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalInt;

public abstract class AbstractFluidContainerMenu extends AbstractContainerMenu {
    public final NonNullList<FluidSlot> fluidSlots = NonNullList.create();

    protected AbstractFluidContainerMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }
    protected FluidSlot addFluidSlot(FluidSlot slot) {
        fluidSlots.add(slot.index, slot);
        return slot;
    }

    public FluidSlot getFluidSlot(int slot) {
        return this.fluidSlots.get(slot);
    }

    protected ItemStack fillSlotAndStow(FluidSlot slot, ItemStack container, Player player) {
        IFluidHandlerItem containerTank = container.getCapability(Capabilities.FluidHandler.ITEM);
        if (container.isEmpty()) return ItemStack.EMPTY;
        if (containerTank != null) {
            if (container.getCount() == 1) {
                emptyContainer(slot, containerTank, player);
                return containerTank.getContainer();
            }
            else {
                ItemStack singleContainer = container.copyWithCount(1);
                containerTank = singleContainer.getCapability(Capabilities.FluidHandler.ITEM);
                if (containerTank != null) {
                    FluidStack emptied = emptyContainer(slot, containerTank, player);
                    if (emptied.getAmount() > 0) {
                        container.shrink(1);
                    }
                }
            }
        }
        return container;
    }

    private FluidStack emptyContainer(FluidSlot slot, IFluidHandlerItem containerTank, Player player) {
        FluidStack drainable = containerTank.drain(Integer.MAX_VALUE, IFluidHandler.FluidAction.SIMULATE);
        if (!drainable.isEmpty()) {
            DataComponentPatch fluidTag = drainable.getComponentsPatch();
            int fillableAmount = slot.fill(drainable, IFluidHandler.FluidAction.SIMULATE);
            if (fillableAmount > 0) {
                FluidStack transferred = new FluidStack(drainable.getFluidHolder(), fillableAmount, fluidTag);
                int drained = containerTank.drain(fillableAmount, IFluidHandler.FluidAction.SIMULATE).getAmount();
                if (drained == fillableAmount) {
                    transferred.getFluid().getPickupSound().ifPresent(sound -> {
                        player.level().playSound(null, player.blockPosition(), sound, SoundSource.PLAYERS,1.0F, 1.0F);
                    });
                    slot.fill(transferred.copyWithAmount(drained), IFluidHandler.FluidAction.EXECUTE);
                    return containerTank.drain(fillableAmount, IFluidHandler.FluidAction.EXECUTE);
                }
            }
        }
        return FluidStack.EMPTY;
    }

    protected ItemStack drainSlotAndStow(FluidSlot slot, ItemStack container, Player player) {
        IFluidHandlerItem containerTank = container.getCapability(Capabilities.FluidHandler.ITEM);
        if (container.isEmpty()) return ItemStack.EMPTY;
        if (containerTank != null) {
            if (container.getCount() == 1) {
                fillContainer(slot, containerTank, player);
                return containerTank.getContainer();
            }
            else {
                ItemStack singleContainer = container.copyWithCount(1);
                containerTank = singleContainer.getCapability(Capabilities.FluidHandler.ITEM);
                if (containerTank != null) {
                    int filledAmount = fillContainer(slot, containerTank, player);
                    if (filledAmount > 0) {
                        container.shrink(1);
                    }
                }
            }
        }
        return container;
    }

    private int fillContainer(FluidSlot slot, IFluidHandlerItem containerTank, Player player) {
        FluidStack fillable = slot.drain(Integer.MAX_VALUE, IFluidHandler.FluidAction.SIMULATE);
        if (!fillable.isEmpty()) {
            DataComponentPatch fluidTag = fillable.getComponentsPatch();
            int drainableAmount = containerTank.fill(fillable, IFluidHandler.FluidAction.SIMULATE);
            if (drainableAmount > 0) {
                FluidStack transferred = new FluidStack(fillable.getFluidHolder(), drainableAmount, fluidTag);
                int drained = slot.drain(transferred.getAmount(), IFluidHandler.FluidAction.SIMULATE).getAmount();
                if (drained == drainableAmount) {
                    transferred.getFluid().getPickupSound().ifPresent(sound -> {
                        player.level().playSound(null, player.blockPosition(), sound, SoundSource.PLAYERS,1.0F, 1.0F);
                    });
                    slot.drain(drained, IFluidHandler.FluidAction.EXECUTE);
                    return containerTank.fill(transferred.copyWithAmount(drained), IFluidHandler.FluidAction.EXECUTE);
                }
            }
        }
        return 0;
    }

    public static OptionalInt openContainer(ServerPlayer player, BlockPos pos) {
        final BlockEntity blockEntity = player.level().getBlockEntity(pos);

        if (!(blockEntity instanceof MenuProvider prov))
            return OptionalInt.empty();

        return player.openMenu(prov, pos);
    }
}