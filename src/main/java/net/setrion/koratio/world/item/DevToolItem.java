package net.setrion.koratio.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlocks;

public class DevToolItem extends Item {
	
	RandomSource random = RandomSource.create();
	int maxRadius, maxHeight;
	public static BlockState LOGS = KoratioBlocks.CANDY_LOG.get().defaultBlockState();
	public static BlockState LEAVES = KoratioBlocks.LIME_COTTON_CANDY_LEAVES.get().defaultBlockState();

	public DevToolItem(Properties prop) {
		super(prop);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockPos startPos = context.getClickedPos().above(2);
		BlockPos currentPos;
		Level level = context.getLevel();
		maxRadius = 3;
		float leafRadius = 4;
		int randomAdd = level.random.nextInt(3);
		maxHeight = 7 + randomAdd;
		int j = level.random.nextInt(5);

		//Logs
		/*for (int y = 0; y < maxHeight; y++) {
			maxRadius = 3;
			if (y == 0) maxRadius+=2; else if (y <= 2) maxRadius+=1;
			for (int x = -maxRadius; x < maxRadius; x++) {
				for (int z = -maxRadius; z < maxRadius; z++) {
					currentPos = startPos.above(y).east(x).north(z);
					if (startPos.atY(currentPos.getY()).closerToCenterThan(currentPos.getCenter(), maxRadius-.5)) {
						if (level.getBlockState(currentPos).isAir()) {
							level.setBlockAndUpdate(currentPos, LOGS);
						}
					}
				}
			}
		}*/

		for (int y = 0; y < maxHeight; y++) {
			currentPos = startPos.above(y);
			if (level.getBlockState(currentPos).isAir()) {
				level.setBlockAndUpdate(currentPos, LOGS);
			}
		}

		//Leaves
		BlockPos spherecenter = startPos.above(maxHeight-7 + (int)leafRadius);
		for (int y = maxHeight-7; y <= maxHeight; y++) {
			for (int x = (int) -leafRadius; x < leafRadius; x++) {
				for (int z = (int) -leafRadius; z < leafRadius; z++) {
					currentPos = startPos.above(y).east(x).north(z);
					if (currentPos.distSqr(new Vec3i(spherecenter.getX(), spherecenter.getY(), spherecenter.getZ())) <= leafRadius*leafRadius-2.2) {
						if (level.getBlockState(currentPos).isAir()) {
							level.setBlockAndUpdate(currentPos, LEAVES);
						}
					}
				}
			}
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}