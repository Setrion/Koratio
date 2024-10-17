package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.setrion.koratio.fluids.capability.FluidContainer;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class AbstractFluidTankBlockEntity extends BlockEntity {

    public AbstractFluidTankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    protected boolean shouldSyncOnUpdate() {
        return true;
    }

    protected void findRecipe() {}

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return shouldSyncOnUpdate() ? ClientboundBlockEntityDataPacket.create(this) : null;
    }

    protected void saveSynced(CompoundTag tag) {}

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveSynced(tag);
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        super.saveAdditional(pTag, registries);
        saveSynced(pTag);
    }

    public void sendUpdate(){
        setChanged();
        if(level != null && !isRemoved() && level.hasChunkAt(worldPosition)) level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    protected FluidContainer createDefaultTank() {return createBasicTank(16000);}
    protected FluidContainer createBasicTank(int size, Runnable... run) {
        return new FluidContainer(size) {
            @Override
            protected void onContentsChanged() {
                sendUpdate();
                for (Runnable runnable : run) runnable.run();
            }
        };
    }
    protected FluidContainer createRecipeFinderTank(int size) {
        return new FluidContainer(size) {
            @Override
            protected void onContentsChanged() {
                findRecipe();
                sendUpdate();
            }
        };
    }
    protected FluidContainer createBasicTank(int size, boolean canDrain, boolean canFill, Runnable... run) {
        return new FluidContainer(size) {
            @Override
            protected void onContentsChanged() {
                sendUpdate();
                for (Runnable runnable : run) runnable.run();
            }
            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                return canDrain ? super.drain(maxDrain, action) : FluidStack.EMPTY;
            }
            @Override
            public int fill(FluidStack resource, FluidAction action) {
                return canFill ? super.fill(resource, action) : 0;
            }
        };
    }
    protected FluidContainer createRecipeFinderTank(int size, boolean canDrain, boolean canFill) {
        return new FluidContainer(size) {
            @Override
            protected void onContentsChanged() {
                findRecipe();
                sendUpdate();
            }
            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                return canDrain ? super.drain(maxDrain, action) : FluidStack.EMPTY;
            }
            @Override
            public int fill(FluidStack resource, FluidAction action) {
                return canFill ? super.fill(resource, action) : 0;
            }
        };
    }
    protected FluidContainer createBasicTank(int size, Predicate<FluidStack> validator, boolean canDrain, boolean canFill, Runnable... run) {
        return new FluidContainer(size, validator) {
            @Override
            protected void onContentsChanged() {
                sendUpdate();
                for (Runnable runnable : run) runnable.run();
            }
            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                return canDrain ? super.drain(maxDrain, action) : FluidStack.EMPTY;
            }
            @Override
            public int fill(FluidStack resource, FluidAction action) {
                return canFill ? super.fill(resource, action) : 0;
            }
        };
    }
    protected FluidContainer createRecipeFinderTank(int size, Predicate<FluidStack> validator, boolean canDrain, boolean canFill) {
        return new FluidContainer(size, validator) {
            @Override
            protected void onContentsChanged() {
                findRecipe();
                sendUpdate();
            }
            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                return canDrain ? super.drain(maxDrain, action) : FluidStack.EMPTY;
            }
            @Override
            public int fill(FluidStack resource, FluidAction action) {
                return canFill ? super.fill(resource, action) : 0;
            }
        };
    }
}