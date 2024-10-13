package net.setrion.koratio.core.cauldron;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.setrion.koratio.registry.KoratioFluids;

public class EmptyCauldronInteraction extends CauldronInteraction {
    private final Fluid type;
    private EmptyCauldronInteraction(Fluid type) {
        this.type = type;
    }

    public static final EmptyCauldronInteraction WATER = createLayered(Fluids.WATER);
    public static final EmptyCauldronInteraction MOLTEN_SUGAR = createBasic(KoratioFluids.MOLTEN_SUGAR.get());
    public static final EmptyCauldronInteraction MOLTEN_BLUE_SUGAR = createBasic(KoratioFluids.MOLTEN_BLUE_SUGAR.get());
    public static final EmptyCauldronInteraction MOLTEN_GREEN_SUGAR = createBasic(KoratioFluids.MOLTEN_GREEN_SUGAR.get());
    public static final EmptyCauldronInteraction MOLTEN_YELLOW_SUGAR = createBasic(KoratioFluids.MOLTEN_YELLOW_SUGAR.get());
    public static final EmptyCauldronInteraction MOLTEN_RED_SUGAR = createBasic(KoratioFluids.MOLTEN_RED_SUGAR.get());
    public static final EmptyCauldronInteraction LAVA = createBasic(Fluids.LAVA);

    //Fills the bucket. Empties the cauldron.
    @Override
    public ItemInteractionResult interact(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        IFluidHandlerItem fluidHandlerItem = stack.getCapability(Capabilities.FluidHandler.ITEM);
        if(fluidHandlerItem != null) {
            FluidStack fluidStack = new FluidStack(type, FluidType.BUCKET_VOLUME);
            if (fluidHandlerItem.getTankCapacity(0) - fluidHandlerItem.getFluidInTank(0).getAmount() >= FluidType.BUCKET_VOLUME) {
                if(!level.isClientSide()) {
                    if(!player.getAbilities().instabuild) transferFluid(level, player, hand, pos);
                    emptyTheCauldron(player, level, stack, fluidStack, pos, Blocks.CAULDRON.defaultBlockState());
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected void emptyTheCauldron(Player player, Level level, ItemStack stack, FluidStack fluidStack, BlockPos pos, BlockState newState) {
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        level.setBlockAndUpdate(pos, newState);
        SoundEvent fillSound = fluidStack.getFluidType().getSound(player, level, pos, SoundActions.BUCKET_FILL);
        if (fillSound != null) level.playSound(null, pos, fillSound, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
    }

    private static EmptyCauldronInteraction createBasic(Fluid fluid) {
        return new EmptyCauldronInteraction(fluid);
    }

    private static EmptyCauldronInteraction createLayered(Fluid fluid) {
        return new EmptyCauldronInteraction(fluid) {
            @Override
            public ItemInteractionResult interact(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
                if (state.getValue(LayeredCauldronBlock.LEVEL) == 3) return super.interact(state, level, pos, player, hand, stack);
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        };
    }
}