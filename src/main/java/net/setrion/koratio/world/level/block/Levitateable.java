package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.world.entity.item.LevitatingBlockEntity;

public interface Levitateable {
    default void onLand(Level level, BlockPos pos, BlockState state, BlockState replaceableState, LevitatingBlockEntity levitatingBlock) {
    }

    default void onBrokenAfterFall(Level level, BlockPos pos, LevitatingBlockEntity levitatingBlock) {
    }

    default DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().fallingBlock(entity);
    }
}
