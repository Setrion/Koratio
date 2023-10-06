package net.setrion.koratio.world.level.levelgen.feature.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.setrion.koratio.registry.KoratioFoliagePlacerTypes;

public class RugonaFoliagePlacer extends FoliagePlacer {
	public static final Codec<RugonaFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return foliagePlacerParts(instance).apply(instance, RugonaFoliagePlacer::new);
	});
	
	public RugonaFoliagePlacer(IntProvider baseheight, IntProvider addheight) {
		super(baseheight, addheight);
	}

	protected FoliagePlacerType<?> type() {
		return KoratioFoliagePlacerTypes.RUGONA_PLACER.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader reader, FoliageSetter setter, RandomSource random, TreeConfiguration config, int p_225617_, FoliageAttachment attachment, int p_225619_, int p_225620_, int height) {
		BlockPos blockpos = attachment.pos().above(height);
		BlockPos currentPos;
		int f = 1;
		for (int y = height-2; y < height+2; y++) {
			if (y == height-2) f = 2;
			if (y == height-1 || y == height+1) f = 3;
			if (y == height) f = 4;
			for (int x = -f; x < f; x++) {
				for (int z = -f; z < f; z++) {
					currentPos = blockpos.above(y).east(x).north(z);
					if (blockpos.closerThan(currentPos.atY(blockpos.getY()), f-.6)) {	
						tryPlaceLeaf(reader, setter, random, config, currentPos);
					}
				}
			}
		}
	}

	@Override
	public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
		return 4;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource random, int p_225596_, int p_225597_, int p_225598_, int p_225599_, boolean p_225600_) {
		return p_225596_ == p_225599_ && p_225598_ == p_225599_ && (random.nextInt(2) == 0 || p_225597_ == 0);
	}
}
