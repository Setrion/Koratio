package net.setrion.koratio.world.entity.monster;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableMap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SilverEnderMan extends EnderMan {
	
	private int holding;
	public static Map<Block, Block> GILDED_BLOCKS = (new ImmutableMap.Builder<Block, Block>()).put(Blocks.SMOOTH_STONE, Blocks.GOLD_BLOCK).build();
	public static final Predicate<LivingEntity> SIZE_SELECTOR = (entity) -> {
		return entity.getType().getDimensions().height <= 1.0F && entity.getType().getDimensions().width <= 1.0F;
	};

	public SilverEnderMan(EntityType<? extends SilverEnderMan> type, Level level) {
		super(type, level);
	}
	
	@Override
	public void aiStep() {
		super.aiStep();
		if (getCarriedBlock() != null && getCarriedBlock().getBlock() != Blocks.AIR && getGilded(getCarriedBlock()).isPresent()) {
			holding++;
			if (holding >= 2400) {
				setCarriedBlock(getGilded(getCarriedBlock()).get());
			}
		} else {
			holding = 0;
		}
	}
	
	private Optional<BlockState> getGilded(BlockState state) {
		return Optional.ofNullable(GILDED_BLOCKS.get(state.getBlock())).map((block) -> {
			return block.defaultBlockState();
		});
	}
	
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("holding", holding);
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		holding = tag.getInt("holding");
	}
}