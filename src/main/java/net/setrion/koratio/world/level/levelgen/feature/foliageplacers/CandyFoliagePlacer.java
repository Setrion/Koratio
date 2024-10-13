package net.setrion.koratio.world.level.levelgen.feature.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.setrion.koratio.registry.KoratioFoliagePlacerTypes;

public class CandyFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<CandyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> foliagePlacerParts(instance).apply(instance, CandyFoliagePlacer::new));

	public CandyFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	protected FoliagePlacerType<?> type() {
		return KoratioFoliagePlacerTypes.CANDY_PLACER.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader reader, FoliageSetter setter, RandomSource random, TreeConfiguration config, int p_225617_, FoliageAttachment attachment, int p_225619_, int p_225620_, int height) {
		BlockPos currentPos;
		int f = 4;
		BlockPos spherecenter = attachment.pos().above(height-7 + f);
		for (int y = height-7; y <= height; y++) {
			for (int x = -f; x < f; x++) {
				for (int z = -f; z < f; z++) {
					currentPos = attachment.pos().above(y).east(x).north(z);
					if (currentPos.distSqr(new Vec3i(spherecenter.getX(), spherecenter.getY(), spherecenter.getZ())) <= f*f-2.2) {
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
