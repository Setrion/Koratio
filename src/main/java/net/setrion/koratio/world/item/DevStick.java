package net.setrion.koratio.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlocks;

public class DevStick extends Item {
	
	RandomSource random = RandomSource.create();
	int maxRadius, maxHeight;
	public static BlockState PLANKS = KoratioBlocks.NIGHY_LOG.get().defaultBlockState();
	public static BlockState LEAVES = KoratioBlocks.NIGHY_LEAVES.get().defaultBlockState();

	public DevStick(Properties prop) {
		super(prop);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockPos startPos = context.getClickedPos().above(2);
		BlockPos currentPos;
		Level level = context.getLevel();
		maxRadius = 1;
		int randomAdd = level.random.nextInt(4);
		maxHeight = 10 + randomAdd;
		int j = level.random.nextInt(5);
		for (int y = 0; y < maxHeight; y++) {
			if (y == 3+j) {
				for (Direction dir : Direction.values()) {
					if (dir.getAxis().isHorizontal()) {
						for (int y2 = 0; y2 < 3; y2++) {
							for (int x = -y2-1; x < y2+1; x++) {
								for (int z = -y2-1; z < y2+1; z++) {
									maxRadius = (x+z)*2;
									currentPos = startPos.above(y+y2).relative(dir, z).relative(dir, x);
									if (startPos.atY(currentPos.getY()).closerToCenterThan(currentPos.getCenter(), maxRadius+y2) && !startPos.atY(currentPos.getY()).closerToCenterThan(currentPos.getCenter(), maxRadius+3)) {
										level.setBlockAndUpdate(currentPos, PLANKS);
										if (y2 >= 2) {
											level.setBlockAndUpdate(currentPos.above(), LEAVES);
										}
									}
								}
							}
						}
					}
				}
			} else {
				currentPos = startPos.above(y);
				if (startPos.atY(currentPos.getY()).closerToCenterThan(currentPos.getCenter(), 1)) {
					level.setBlockAndUpdate(currentPos, PLANKS);
					if (y >= maxHeight-1) {
						level.setBlockAndUpdate(currentPos.above(), LEAVES);
					}
				}
			}
		}
		
		/*
		for (int y = maxHeight-2; y < maxHeight+3; y++) {
			if (y == maxHeight-2) maxRadius=4;
			if (y == maxHeight-1) maxRadius=6;
			if (y == maxHeight) maxRadius = 5;
			if (y == maxHeight+1) maxRadius = 3;
			if (y == maxHeight+2) maxRadius = 2;
			for (int x = -maxRadius; x < maxRadius; x++) {
				for (int z = -maxRadius; z < maxRadius; z++) {
					currentPos = startPos.above(y).east(x).north(z+1);
					if (startPos.atY(currentPos.getY()).closerToCenterThan(currentPos.getCenter().add(0.5, 0, 0.5), maxRadius-1)) {
						if (level.getBlockState(currentPos).isAir()) {
							level.setBlockAndUpdate(currentPos, LEAVES);
						}
					}
				}
			}
		}*/

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}