package net.setrion.koratio.world.level.levelgen.feature.trunkplacers;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
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

import java.util.List;
import java.util.function.BiConsumer;

public class ElvenTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<ElvenTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
		return trunkPlacerParts(instance).apply(instance, ElvenTrunkPlacer::new);
	});

	public ElvenTrunkPlacer(int height, int randomHeightA, int randomHeightB) {
		super(height, randomHeightA, randomHeightB);
	}

	protected TrunkPlacerType<?> type() {
		return KoratioTrunkPlacerTypes.ELVEN_PLACER.get();
	}

	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource random, int height, BlockPos pos, TreeConfiguration config) {
		BlockPos blockpos = pos.below();
		pos = pos.east().south();
		setDirtAt(level, consumer, random, blockpos, config);
		setDirtAt(level, consumer, random, blockpos.east(), config);
		setDirtAt(level, consumer, random, blockpos.east(2), config);
		setDirtAt(level, consumer, random, blockpos.south(), config);
		setDirtAt(level, consumer, random, blockpos.south(2), config);
		setDirtAt(level, consumer, random, blockpos.east().south(), config);
		setDirtAt(level, consumer, random, blockpos.east(2).south(), config);
		setDirtAt(level, consumer, random, blockpos.east().south(2), config);
		setDirtAt(level, consumer, random, blockpos.east(2).south(2), config);
		List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
		int i = height - random.nextInt(4);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int f = 3;

		for (int y = 0; y < i; y++) {
			f = 3;
			if (y == 0) f+=2; else if (y <= 2) f+=1;
			for (int x = -f; x < f; x++) {
				for (int z = -f; z < f; z++) {
					blockpos$mutableblockpos = pos.above(y).east(x).north(z).mutable();
					if (pos.atY(blockpos$mutableblockpos.getY()).closerToCenterThan(blockpos$mutableblockpos.getCenter(), f-.5)) {
						this.placeLog(level, consumer, random, blockpos$mutableblockpos, config);
					}
				}
			}
		}
	
		list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(blockpos$mutableblockpos.getX(), blockpos$mutableblockpos.getY(), blockpos$mutableblockpos.getZ()), 4, false));

		return list;
	}
}