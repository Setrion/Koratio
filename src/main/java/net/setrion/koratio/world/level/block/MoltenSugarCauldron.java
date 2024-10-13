package net.setrion.koratio.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.LavaCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class MoltenSugarCauldron extends AbstractCauldronBlock {

    public static final MapCodec<LavaCauldronBlock> CODEC = simpleCodec(LavaCauldronBlock::new);

    public MapCodec<LavaCauldronBlock> codec() {
        return CODEC;
    }

    public MoltenSugarCauldron(BlockBehaviour.Properties properties, CauldronInteraction.InteractionMap interaction) {
        super(properties, interaction);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return Items.CAULDRON.getDefaultInstance();
    }

    protected double getContentHeight(BlockState state) {
        return 0.9375;
    }

    public boolean isFull(BlockState state) {
        return true;
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (this.isEntityInsideContent(state, pos, entity)) {
            entity.lavaHurt();
        }

    }

    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return 3;
    }
}