package net.setrion.koratio.world.level.levelgen.feature.trunkplacers;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.setrion.koratio.registry.KoratioTrunkPlacerTypes;

public class VaresoTrunkPlacer extends TrunkPlacer {
	public static final Codec<VaresoTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return trunkPlacerParts(instance).apply(instance, VaresoTrunkPlacer::new);
	});

	public VaresoTrunkPlacer(int height, int randomHeightA, int randomHeightB) {
		super(height, randomHeightA, randomHeightB);
	}

	protected TrunkPlacerType<?> type() {
		return KoratioTrunkPlacerTypes.VARESO_PLACER.get();
	}

	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource random, int height, BlockPos pos, TreeConfiguration config) {
		BlockPos blockpos = pos.below();
		pos = pos.east().south();
		setDirtAt(level, consumer, random, blockpos, config);
		setDirtAt(level, consumer, random, blockpos.east(), config);
		setDirtAt(level, consumer, random, blockpos.south(), config);
		setDirtAt(level, consumer, random, blockpos.south().east(), config);
		List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
		int i = height - random.nextInt(4);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int f = 3;

		int f1 = 4+random.nextInt(4);
		int f2 = i - 5 - random.nextInt(4);
		for (int y = 0; y < i; y++) {
			if ((y >= f1-1 && y <= f1+1) || (y >= f2-1 && y <= f2+1) || y == i-1) f = 4;
			else f = 3;
			for (int x = -f; x < f; x++) {
				for (int z = -f; z < f; z++) {
					blockpos$mutableblockpos = pos.above(y).east(x).north(z).mutable();
					if (pos.atY(blockpos$mutableblockpos.getY()).closerToCenterThan(blockpos$mutableblockpos.getCenter().add(0.5, 0, 0.5), f-2)) {
						if (y == f1) {
							int xDist = pos.getX() - blockpos$mutableblockpos.getX();
							if (!pos.atY(blockpos$mutableblockpos.getY()).closerToCenterThan(blockpos$mutableblockpos.getCenter().add(0, 0, 0.5), f-2.5) && (xDist < 1.5)) {
								this.placeLog(level, consumer, random, blockpos$mutableblockpos, config);
							}
						} else if (y == f2) {
							int yDist = pos.getZ() - blockpos$mutableblockpos.getZ();
							if (!pos.atY(blockpos$mutableblockpos.getY()).closerToCenterThan(blockpos$mutableblockpos.getCenter().add(0.5, 0, 0), f-2.5) && (yDist < 1.5)) {
								this.placeLog(level, consumer, random, blockpos$mutableblockpos, config);
							}
						} else {
							this.placeLog(level, consumer, random, blockpos$mutableblockpos, config);
						}
					}
				}
			}
		}
	
		list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(blockpos$mutableblockpos.getX()-1, blockpos$mutableblockpos.getY()-1, blockpos$mutableblockpos.getZ()+1), 4, false));

		return list;
	}
}