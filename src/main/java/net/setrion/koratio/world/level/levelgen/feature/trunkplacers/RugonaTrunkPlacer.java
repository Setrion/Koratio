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

public class RugonaTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<RugonaTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
		return trunkPlacerParts(instance).apply(instance, RugonaTrunkPlacer::new);
	});

	public RugonaTrunkPlacer(int height, int randomHeightA, int randomHeightB) {
		super(height, randomHeightA, randomHeightB);
	}

	protected TrunkPlacerType<?> type() {
		return KoratioTrunkPlacerTypes.RUGONA_PLACER.get();
	}

	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource random, int height, BlockPos pos, TreeConfiguration config) {
		setDirtAt(level, consumer, random, pos.below(), config);
		List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
		int i = height - random.nextInt(4) - 1;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int f = 1;

		for (int y = 0; y < i; y++) {
			if (y > i-2 || y < 3) f=2;
			else f = 1;
			for (int x = -f; x < f; x++) {
				for (int z = -f; z < f; z++) {
					blockpos$mutableblockpos = pos.above(y).east(x).north(z).mutable();
					if (pos.distManhattan(blockpos$mutableblockpos.atY(pos.getY())) < f-0.5) {
						this.placeLog(level, consumer, random, blockpos$mutableblockpos, config);
					}
				}
			}
		}
	
		list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(blockpos$mutableblockpos.getX()-1, blockpos$mutableblockpos.getY()-1, blockpos$mutableblockpos.getZ()+1), 4, false));

		return list;
	}
}