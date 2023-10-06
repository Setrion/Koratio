package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class InfestedBlock extends Block {

	public InfestedBlock(Block host, Properties properties) {
		super(properties.destroyTime(host.defaultDestroyTime() / 2.0F).explosionResistance(0.75F));
	}
	
	private void spawnInfestation(ServerLevel level, BlockPos pos) {
		Silverfish silverfish = EntityType.SILVERFISH.create(level);
		if (silverfish != null) {
			silverfish.moveTo((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
			level.addFreshEntity(silverfish);
			silverfish.spawnAnim();
		}
	}

	public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean spawnXP) {
		super.spawnAfterBreak(state, level, pos, stack, spawnXP);
		if (level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && stack.getEnchantmentLevel(Enchantments.SILK_TOUCH) == 0) {
			this.spawnInfestation(level, pos);
		}
	}
}