package net.setrion.koratio.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugeGreenMushroomFeature extends AbstractHugeMushroomFeature {
	
	public HugeGreenMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
		super(codec);
	}

	protected void makeCap(LevelAccessor level, RandomSource random, BlockPos pos, int radius, BlockPos.MutableBlockPos mutable, HugeMushroomFeatureConfiguration config) {
		for (int y = 6; y > 0; y--) {
			for (int x = -2; x <=2; x++) {
				for (int z = 0; z < 360; z++) {
					mutable = pos.below(y).above(radius+6).east((int) ((x-(y*0.4))*Math.cos(z))).north((int) ((x-(y*0.4))*Math.sin(z))).mutable();
					if (x == -2 || x == 2) {
						BlockState blockstate = config.capProvider.getState(random, pos);
						if ((mutable.getX() == pos.getX() && mutable.getZ() == pos.getZ())) {
							blockstate = Blocks.MUSHROOM_STEM.defaultBlockState();
							if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH) && blockstate.hasProperty(HugeMushroomBlock.UP)) {
								blockstate = blockstate.setValue(HugeMushroomBlock.DOWN, false).setValue(HugeMushroomBlock.UP, false);
							}
						} else {
							blockstate = config.capProvider.getState(random, pos);
							if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH) && blockstate.hasProperty(HugeMushroomBlock.UP)) {
								blockstate = blockstate.setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(y == 6)).setValue(HugeMushroomBlock.NORTH, Boolean.valueOf(pos.east((int) ((x-(y*0.4))*Math.sin(z))).getZ() >= mutable.getZ())).setValue(HugeMushroomBlock.SOUTH, Boolean.valueOf(pos.east((int) ((x-(y*0.4))*Math.sin(z))).getZ() <= mutable.getZ())).setValue(HugeMushroomBlock.WEST, Boolean.valueOf(pos.east((int) ((x+(y*0.4))*Math.sin(z))).getX() >= mutable.getX())).setValue(HugeMushroomBlock.EAST, Boolean.valueOf(pos.east((int) ((x+(y*0.4))*Math.sin(z))).getX() <= mutable.getX()));
							}
						}
						this.setBlock(level, mutable, blockstate);
					}
				}
			}
		}
		this.setBlock(level, pos.above(radius+6), config.capProvider.getState(random, pos));
		this.setBlock(level, pos.above(radius+5), config.capProvider.getState(random, pos));
	}

	protected int getTreeRadiusForHeight(int p_65977_, int p_65978_, int p_65979_, int p_65980_) {
		int i = 0;
		if (p_65980_ < p_65978_ && p_65980_ >= p_65978_ - 3) {
			i = p_65979_;
		} else if (p_65980_ == p_65978_) {
			i = p_65979_;
		}

		return i;
	}
}