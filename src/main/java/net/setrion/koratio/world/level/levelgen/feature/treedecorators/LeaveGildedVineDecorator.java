package net.setrion.koratio.world.level.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioTreeDecoratorTypes;

public class LeaveGildedVineDecorator extends TreeDecorator {

	public static final Codec<LeaveGildedVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(LeaveGildedVineDecorator::new, (decorator) -> {
		return decorator.probability;
	}).codec();
	private final float probability;

	protected TreeDecoratorType<?> type() {
		return KoratioTreeDecoratorTypes.GILDED_VINE_LEAVE_DECORATOR.get();
	}

	public LeaveGildedVineDecorator(float probability) {
		this.probability = probability;
	}
	
	public void place(Context context) {
		RandomSource randomsource = context.random();
		context.leaves().forEach((pos) -> {
			if (randomsource.nextFloat() < this.probability) {
				BlockPos blockpos = pos.west();
				if (context.isAir(blockpos)) {
					addHangingVine(blockpos, VineBlock.EAST, context);
				}
			}

			if (randomsource.nextFloat() < this.probability) {
				BlockPos blockpos1 = pos.east();
				if (context.isAir(blockpos1)) {
					addHangingVine(blockpos1, VineBlock.WEST, context);
				}
			}

			if (randomsource.nextFloat() < this.probability) {
				BlockPos blockpos2 = pos.north();
				if (context.isAir(blockpos2)) {
					addHangingVine(blockpos2, VineBlock.SOUTH, context);
				}
			}

			if (randomsource.nextFloat() < this.probability) {
				BlockPos blockpos3 = pos.south();
				if (context.isAir(blockpos3)) {
					addHangingVine(blockpos3, VineBlock.NORTH, context);
				}
			}
		});
	}

	private static void addHangingVine(BlockPos pos, BooleanProperty bool, Context context) {
		context.setBlock(pos, KoratioBlocks.GILDED_VINES.get().defaultBlockState().setValue(bool, Boolean.valueOf(true)));
		int i = 4;

		for(BlockPos blockpos = pos.below(); context.isAir(blockpos) && i > 0; --i) {
			context.setBlock(blockpos, KoratioBlocks.GILDED_VINES.get().defaultBlockState().setValue(bool, Boolean.valueOf(true)));
			blockpos = blockpos.below();
		}
	}
}