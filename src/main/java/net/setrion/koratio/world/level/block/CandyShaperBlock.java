package net.setrion.koratio.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.setrion.koratio.registry.KoratioStats;
import net.setrion.koratio.registry.KoratioTags;
import net.setrion.koratio.world.inventory.CandyShaperMenu;
import net.setrion.koratio.world.level.block.entity.CandyShaperBlockEntity;

import javax.annotation.Nullable;

public class CandyShaperBlock extends BaseEntityBlock {

    public static final MapCodec<CandyShaperBlock> CODEC = simpleCodec(CandyShaperBlock::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.candy_shaper");

    public CandyShaperBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() instanceof BucketItem bucket && bucket.content.is(KoratioTags.Fluids.MOLTEN_SUGAR)) {
            if (!level.isClientSide && player instanceof ServerPlayer) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof CandyShaperBlockEntity candyShaper) {
                    if (candyShaper.getFluidHandler().fill(new FluidStack(bucket.content, FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.SIMULATE) >= FluidType.BUCKET_VOLUME) {
                        candyShaper.getFluidHandler().fill(new FluidStack(bucket.content, FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
                        player.setItemInHand(hand, BucketItem.getEmptySuccessItem(stack, player));
                    } else {
                        return InteractionResult.PASS;
                    }
                }
                return InteractionResult.CONSUME;
            }
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            CandyShaperMenu.openContainer((ServerPlayer) player, pos);
            player.awardStat(KoratioStats.INTERACT_WITH_CANDY_SHAPER.get());
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof CandyShaperBlockEntity candyShaper) {
            return new SimpleMenuProvider((id, inventory, player) -> new CandyShaperMenu(id, inventory, candyShaper, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
        }
        return null;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CandyShaperBlockEntity(pos, state);
    }
}