package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.fluids.capability.AbstractFluidTank;
import net.setrion.koratio.fluids.capability.FluidContainer;
import net.setrion.koratio.fluids.capability.MultiFluidContainer;
import net.setrion.koratio.registry.KoratioBlockEntityType;
import net.setrion.koratio.registry.KoratioFluids;
import net.setrion.koratio.world.inventory.CandyShaperMenu;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = Koratio.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CandyShaperBlockEntity extends AbstractFluidTankBlockEntity implements MenuProvider {

    public CandyShaperBlockEntity(BlockPos pos, BlockState blockState) {
        super(KoratioBlockEntityType.CANDY_SHAPER.get(), pos, blockState);

    }

    @SubscribeEvent
    private static void registerCapability(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                KoratioBlockEntityType.CANDY_SHAPER.get(),
                (be, context) -> be.FLUID_TANK);
    }

    private final AbstractFluidTank FLUID_TANK = new MultiFluidContainer<>(new FluidContainer(2000) {
        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.is(KoratioFluids.MOLTEN_SUGAR.get());
        }

        @Override
        protected void onContentsChanged() {
            CandyShaperBlockEntity.this.sendUpdate();
        }
    },
       new FluidContainer(2000) {
           @Override
           public boolean isFluidValid(FluidStack stack) {
               return stack.is(KoratioFluids.MOLTEN_BLUE_SUGAR.get());
           }

           @Override
           protected void onContentsChanged() {
               CandyShaperBlockEntity.this.sendUpdate();
           }
       },
       new FluidContainer(2000) {
           @Override
           public boolean isFluidValid(FluidStack stack) {
               return stack.is(KoratioFluids.MOLTEN_GREEN_SUGAR.get());
           }

           @Override
           protected void onContentsChanged() {
               CandyShaperBlockEntity.this.sendUpdate();
           }
       },
       new FluidContainer(2000) {
           @Override
           public boolean isFluidValid(FluidStack stack) {
               return stack.is(KoratioFluids.MOLTEN_YELLOW_SUGAR.get());
           }

           @Override
           protected void onContentsChanged() {
               CandyShaperBlockEntity.this.sendUpdate();
           }
       },
       new FluidContainer(2000) {
           @Override
           public boolean isFluidValid(FluidStack stack) {
               return stack.is(KoratioFluids.MOLTEN_RED_SUGAR.get());
           }

           @Override
           protected void onContentsChanged() {
               CandyShaperBlockEntity.this.sendUpdate();
           }
       });

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Fluid", FLUID_TANK.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        FLUID_TANK.deserializeNBT(registries, tag.getCompound("Fluid"));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CandyShaperMenu(i, inventory, this, ContainerLevelAccess.create(player.level(), this.worldPosition));
    }

    public void sendUpdate() {
        setChanged();
        if(this.level != null) this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    public FluidStack getFluidStack() {
        return FLUID_TANK.getFluid(0);
    }

    public IFluidHandler getFluidHandler() {
        return this.FLUID_TANK;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.candy_shaper");
    }
}