package net.setrion.koratio.world.level.biome;

import com.google.common.collect.ImmutableList;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.registry.KoratioBlocks;

public class DemonicioSurfaceRules {

	private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
	private static final SurfaceRules.RuleSource SOUL_STONE = makeStateRule(KoratioBlocks.SOUL_STONE.get());
	private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
	private static final SurfaceRules.RuleSource BLOOD_STAINED_DEEPSLATE = makeStateRule(KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get());
	private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
	private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
	private static final SurfaceRules.RuleSource SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
	private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);

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
										SurfaceRules.isBiome(KoratioBiomes.SKULL_DESERT),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SOUL_SAND))),
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
																SurfaceRules.waterBlockCheck(-1, 0)), DIRT))))));

		ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		builder

				.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK))
				.add(overworldLike);

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("koratio:blood_stained_deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), BLOOD_STAINED_DEEPSLATE));
		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("koratio:soul_stone", VerticalAnchor.absolute(200), VerticalAnchor.absolute(220)), SOUL_STONE));
		return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
	}
}