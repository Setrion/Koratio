package net.setrion.koratio.world.entity.monster;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.setrion.koratio.registry.KoratioBlocks;

public class AmethystSpider extends Spider {

	public AmethystSpider(EntityType<? extends AmethystSpider> type, Level level) {
		super(type, level);
	}
	
	public static AttributeSupplier.Builder createAmethystSpider() {
		return Spider.createAttributes().add(Attributes.MAX_HEALTH, 12.0D);
	}
	
	public static boolean checkMonsterSpawnRules(EntityType<? extends Monster> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(level, pos, random) && checkForAmethystCobweb(level, pos) && checkMobSpawnRules(type, level, spawnType, pos, random);
	}
	
	public static boolean checkForAmethystCobweb(ServerLevelAccessor level, BlockPos pos) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = -1; k < 2; k++) {
					if (level.getBlockState(pos.north(i).east(j).above(k)).getBlock() == KoratioBlocks.AMETHYST_COBWEB.get()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		return data;
	}

	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 0.30F;
	}
}