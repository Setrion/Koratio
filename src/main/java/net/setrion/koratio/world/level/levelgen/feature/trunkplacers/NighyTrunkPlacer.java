package net.setrion.koratio.world.level.levelgen.feature.trunkplacers;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.setrion.koratio.registry.KoratioTrunkPlacerTypes;

public class NighyTrunkPlacer extends TrunkPlacer {
	public static final Codec<NighyTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return trunkPlacerParts(instance).apply(instance, NighyTrunkPlacer::new);
	});

	public NighyTrunkPlacer(int height, int randomHeightA, int randomHeightB) {
		super(height, randomHeightA, randomHeightB);
	}

	protected TrunkPlacerType<?> type() {
		return KoratioTrunkPlacerTypes.NIGHY_PLACER.get();
	}

	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource random, int height, BlockPos pos, TreeConfiguration config) {
		BlockPos blockpos = pos.below();
		setDirtAt(level, consumer, random, blockpos, config);
		List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
		int i = height + random.nextInt(4);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int f = 1;
		int j = random.nextInt(5);
		for (int y = 0; y < i; y++) {
			if (y == 3+j) {
				for (Direction dir : Direction.values()) {
					if (dir.getAxis().isHorizontal()) {
						for (int y2 = 0; y2 < 3; y2++) {
							for (int x = -y2-1; x < y2+1; x++) {
								for (int z = -y2-1; z < y2+1; z++) {
									f = (x+z)*2;
									blockpos$mutableblockpos = pos.above(y+y2).relative(dir, z).relative(dir, x).mutable();
									if (pos.atY(blockpos$mutableblockpos.getY()).closerToCenterThan(blockpos$mutableblockpos.getCenter(), f+y2) && !pos.atY(blockpos$mutableblockpos.getY()).closerToCenterThan(blockpos$mutableblockpos.getCenter(), f+3)) {
										placeLog(level, consumer, random, blockpos$mutableblockpos, config);
										if (y2 >= 2) {
											list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(blockpos$mutableblockpos.getX(), blockpos$mutableblockpos.getY()+1, blockpos$mutableblockpos.getZ()), 0, false));
										}
									}
								}
							}
						}
					}
				}
			} else {
				blockpos$mutableblockpos = pos.above(y).mutable();
				if (pos.atY(blockpos$mutableblockpos.getY()).closerToCenterThan(blockpos$mutableblockpos.getCenter(), 1)) {
					placeLog(level, consumer, random, blockpos$mutableblockpos, config);
					if (y >= i-1) {
						list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(blockpos$mutableblockpos.getX(), blockpos$mutableblockpos.getY()+1, blockpos$mutableblockpos.getZ()), 0, false));
					}
				}
			}
		}
		
		return list;
	}
}