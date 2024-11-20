package net.setrion.koratio.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.EmptyBlockAndTintGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.registry.KoratioBlocks;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class LevitatingBlockRenderState extends EntityRenderState implements BlockAndTintGetter {
    public BlockPos startBlockPos;
    public BlockPos blockPos;
    public BlockState blockState;
    @Nullable
    public Holder<Biome> biome;
    public BlockAndTintGetter level;

    public LevitatingBlockRenderState() {
        this.startBlockPos = BlockPos.ZERO;
        this.blockPos = BlockPos.ZERO;
        this.blockState = KoratioBlocks.WHITE_LEVITATING_WOOL.get().defaultBlockState();
        this.level = EmptyBlockAndTintGetter.INSTANCE;
    }

    public float getShade(Direction p_362170_, boolean p_362179_) {
        return this.level.getShade(p_362170_, p_362179_);
    }

    public LevelLightEngine getLightEngine() {
        return this.level.getLightEngine();
    }

    public int getBlockTint(BlockPos p_363218_, ColorResolver p_362552_) {
        return this.biome == null ? -1 : p_362552_.getColor((Biome)this.biome.value(), (double)p_363218_.getX(), (double)p_363218_.getZ());
    }

    @Nullable
    public BlockEntity getBlockEntity(BlockPos p_364380_) {
        return null;
    }

    public BlockState getBlockState(BlockPos p_365153_) {
        return p_365153_.equals(this.blockPos) ? this.blockState : Blocks.AIR.defaultBlockState();
    }

    public FluidState getFluidState(BlockPos p_362887_) {
        return this.getBlockState(p_362887_).getFluidState();
    }

    public int getHeight() {
        return 1;
    }

    public int getMinY() {
        return this.blockPos.getY();
    }
}