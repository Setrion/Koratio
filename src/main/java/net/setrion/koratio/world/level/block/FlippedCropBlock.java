package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.koratio.registry.KoratioBlocks;

public class FlippedCropBlock extends FlippedBushBlock implements BonemealableBlock {
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 6.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public FlippedCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(KoratioBlocks.FLIPPED_FARMLAND.get());
    }

    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 7;
    }

    public int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), Integer.valueOf(age));
    }

    public final boolean isMaxAge(BlockState state) {
        return this.getAge(state) >= this.getMaxAge();
    }

    public boolean isRandomlyTicking(BlockState state) {
        return !this.isMaxAge(state);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (level.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getGrowthSpeed(state, level, pos);
                if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, pos, state, random.nextInt((int)(25.0F / f) + 1) == 0)) {
                    level.setBlock(pos, this.getStateForAge(i + 1), 2);
                    net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
                }
            }
        }
    }

    public void growCrops(Level level, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(level);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        level.setBlock(pos, this.getStateForAge(i), 2);
    }

    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 2, 5);
    }

    protected static float getGrowthSpeed(BlockState blockState, BlockGetter getter, BlockPos pos) {
        Block p_52273_ = blockState.getBlock();
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                float f1 = 0.0F;
                BlockState blockstate = getter.getBlockState(blockpos.offset(i, 0, j));
                net.neoforged.neoforge.common.util.TriState soilDecision = blockstate.canSustainPlant(getter, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, blockState);
                if (soilDecision.isDefault() ? blockstate.getBlock() instanceof net.minecraft.world.level.block.FarmBlock : soilDecision.isTrue()) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(getter, pos.offset(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = getter.getBlockState(blockpos3).is(p_52273_) || getter.getBlockState(blockpos4).is(p_52273_);
        boolean flag1 = getter.getBlockState(blockpos1).is(p_52273_) || getter.getBlockState(blockpos2).is(p_52273_);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = getter.getBlockState(blockpos3.north()).is(p_52273_)
                    || getter.getBlockState(blockpos4.north()).is(p_52273_)
                    || getter.getBlockState(blockpos4.south()).is(p_52273_)
                    || getter.getBlockState(blockpos3.south()).is(p_52273_);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return (reader.getRawBrightness(pos, 0) >= 8) && super.canSurvive(state, reader, pos);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Ravager && net.neoforged.neoforge.event.EventHooks.canEntityGrief(level, entity)) {
            level.destroyBlock(pos, true, entity);
        }

        super.entityInside(state, level, pos, entity);
    }

    protected ItemLike getBaseSeedId() {
        return Items.WHEAT_SEEDS;
    }

    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(this.getBaseSeedId());
    }

    public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState state) {
        return !this.isMaxAge(state);
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.growCrops(level, pos, state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}