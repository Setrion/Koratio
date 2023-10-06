package net.setrion.koratio.world.level.levelgen.feature;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugePurpleMushroomFeature extends AbstractHugeMushroomFeature {
	
	public HugePurpleMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
		super(codec);
	}

	protected void makeCap(LevelAccessor level, RandomSource random, BlockPos pos, int radius, BlockPos.MutableBlockPos mutable, HugeMushroomFeatureConfiguration config) {
		for (int y = 2; y < 9; y++) {
			for (int x = -radius; x <=radius; x++) {
				for (int z = 0; z < 360; z++) {
					if (Math.sqrt(x^2 + x^2) <= 1 * (y*0.5) && !(x == -1 || x == 1) && x == 0) {
						mutable = pos.above(radius).above(y-2).east((int) ((x-(y*0.4))*Math.cos(z))).north((int) ((x-(y*0.4))*Math.sin(z))).mutable();
						BlockState blockstate = config.capProvider.getState(random, pos);
						if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH) && blockstate.hasProperty(HugeMushroomBlock.UP)) {
							blockstate = blockstate.setValue(HugeMushroomBlock.UP, Boolean.valueOf(y == 8)).setValue(HugeMushroomBlock.NORTH, Boolean.valueOf(pos.east((int) ((x-(y*0.4))*Math.sin(z))).getZ() >= mutable.getZ())).setValue(HugeMushroomBlock.SOUTH, Boolean.valueOf(pos.east((int) ((x-(y*0.4))*Math.sin(z))).getZ() <= mutable.getZ())).setValue(HugeMushroomBlock.WEST, Boolean.valueOf(pos.east((int) ((x+(y*0.125))*Math.sin(z))).getX() >= mutable.getX())).setValue(HugeMushroomBlock.EAST, Boolean.valueOf(pos.east((int) ((x+(y*0.125))*Math.sin(z))).getX() <= mutable.getX()));
						}
						this.setBlock(level, mutable, blockstate);
					}
				}
			}
		}
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