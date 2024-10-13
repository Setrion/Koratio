package net.setrion.koratio.world.level.biome;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.setrion.koratio.registry.KoratioBiomes;

public class FantasiaSurfaceRules {

	private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
	private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
	private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
	private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
	private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
	private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
	private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);

	private static SurfaceRules.RuleSource makeStateRule(Block block) {
		return SurfaceRules.state(block.defaultBlockState());
	}

	public static SurfaceRules.RuleSource surface() {
		SurfaceRules.RuleSource overworldLike = SurfaceRules.sequence(				
				SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
						SurfaceRules.sequence(
								SurfaceRules.ifTrue(
										SurfaceRules.isBiome(KoratioBiomes.RIVER),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE),
												SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), GRASS_BLOCK), SAND)),
								SurfaceRules.ifTrue(
										SurfaceRules.isBiome(KoratioBiomes.MUSHROOM_FOREST),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MYCELIUM))),
								SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.yStartCheck(VerticalAnchor.absolute(-4), 1), GRASS_BLOCK))),
								SurfaceRules.ifTrue(
										SurfaceRules.not(
												SurfaceRules.yStartCheck(VerticalAnchor.absolute(-4), 1)),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.not(
																SurfaceRules.waterBlockCheck(-1, 0)), DIRT))))),

				SurfaceRules.ifTrue(SurfaceRules.waterStartCheck(-6, -1),
						SurfaceRules.sequence(
								SurfaceRules.ifTrue(
										SurfaceRules.yStartCheck(VerticalAnchor.absolute(-4), 1),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT))))));

		ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		builder

				.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK))
				.add(overworldLike);

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));
		return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
	}
}