package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlockEntityType;
import net.setrion.koratio.registry.KoratioParticles;
import net.setrion.koratio.world.level.block.entity.CampfireBlockEntity;

import javax.annotation.Nullable;

public class CampfireBlock extends net.minecraft.world.level.block.CampfireBlock {

    private final boolean spawnParticles;

    public CampfireBlock(boolean spawnParticles, int fireDamage, Properties properties) {
        super(spawnParticles, fireDamage, properties);
        this.spawnParticles = spawnParticles;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CampfireBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level instanceof ServerLevel serverlevel) {
            if (state.getValue(LIT)) {
                RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> cachedcheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
                return createTickerHelper(
                        blockEntityType,
                        KoratioBlockEntityType.CAMPFIRE.get(),
                        (l, blockPos, blockState, blockEntity) -> net.minecraft.world.level.block.entity.CampfireBlockEntity.cookTick(serverlevel, blockPos, blockState, blockEntity, cachedcheck)
                );
            } else {
                return createTickerHelper(blockEntityType, KoratioBlockEntityType.CAMPFIRE.get(), net.minecraft.world.level.block.entity.CampfireBlockEntity::cooldownTick);
            }
        } else {
            return state.getValue(LIT) ? createTickerHelper(blockEntityType, KoratioBlockEntityType.CAMPFIRE.get(), net.minecraft.world.level.block.entity.CampfireBlockEntity::particleTick) : null;
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            if (random.nextInt(10) == 0) {
                level.playLocalSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + random.nextFloat(), random.nextFloat() * 0.7F + 0.6F, false);
            }

            if (this.spawnParticles && random.nextInt(5) == 0) {
                for(int i = 0; i < random.nextInt(1) + 1; ++i) {
                    level.addParticle(KoratioParticles.RAINBOW_LAVA.get(), (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, random.nextFloat() / 2.0F, 5.0E-5, random.nextFloat() / 2.0F);
                }
            }
        }
    }
}