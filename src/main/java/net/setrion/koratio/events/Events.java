package net.setrion.koratio.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEnchantments;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.world.entity.animal.Mooshroom;
import net.setrion.koratio.world.entity.demonic.Necromancer;

import java.util.Iterator;
import java.util.List;

@EventBusSubscriber(modid = Koratio.MOD_ID)
public class Events {

	@SubscribeEvent
	public static void onEntitySpawn(FinalizeSpawnEvent event) {
		if ((event.getSpawnType() == EntitySpawnReason.NATURAL || event.getSpawnType() == EntitySpawnReason.CHUNK_GENERATION) && event.getLevel().getBiome(new BlockPos((int) event.getX(),(int) event.getY(),(int) event.getZ())).is(KoratioBiomes.MUSHROOM_FOREST)) {
			if (event.getEntity() instanceof MushroomCow mushroomcow) {
				if (mushroomcow.getRandom().nextInt(50) < 25) {
					mushroomcow.setVariant(MushroomCow.Variant.BROWN);
				}
			} else if (event.getEntity() instanceof Mooshroom mooshroom) {
				if (mooshroom.getRandom().nextInt(50) < 25) {
					mooshroom.setVariant(Mooshroom.MushroomType.GREEN);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onToolUseOnBlock(BlockEvent.BlockToolModificationEvent event) {
		Block block = event.getState().getBlock();
		if (event.getItemAbility() == ItemAbilities.HOE_TILL) {
			if (block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH || block == Blocks.DIRT) {
				if (event.getContext().getClickedFace() == Direction.DOWN) {
					event.setFinalState(KoratioBlocks.FLIPPED_FARMLAND.get().defaultBlockState());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (!event.getEntity().level().isClientSide()) {
			List<Entity> entities = event.getEntity().level().getEntities((Entity) null, event.getEntity().getBoundingBox().inflate(10), entity -> entity.getType() == KoratioEntityType.NECROMANCER.get());
			if (!entities.isEmpty()) {
				if (!event.isCanceled() && event.getEntity().getType().is(EntityTypeTags.UNDEAD)) {
					if (event.getEntity() instanceof Zombie zombie) {
						if (event.getEntity().onGround()) {
							if (zombie.level().getBlockState(zombie.getOnPos().above()).canBeReplaced()) {
								zombie.level().setBlock(zombie.getOnPos().above(), getZombieType(zombie).defaultBlockState().rotate(zombie.level(), zombie.getOnPos().above(), Rotation.getRandom(zombie.getRandom())), 3);
							}
						}
					} else if (event.getEntity() instanceof AbstractSkeleton skeleton) {
						if (event.getEntity().onGround()) {
							if (skeleton.level().getBlockState(skeleton.getOnPos().above()).canBeReplaced()) {
								skeleton.level().setBlock(skeleton.getOnPos().above(), getSkeletonType(skeleton).defaultBlockState().rotate(skeleton.level(), skeleton.getOnPos().above(), Rotation.getRandom(skeleton.getRandom())), 3);
							}
						}
					}
				} else {
					Entity entity = entities.get(event.getEntity().getRandom().nextInt(entities.size()));
					if (entity instanceof Necromancer necromancer) {
						necromancer.setSouls((int) (necromancer.getSouls()+event.getEntity().getMaxHealth()/4));
					}
				}
			}
		}
	}

	public static Block getZombieType(Entity zombie) {
		if (zombie.getType() == EntityType.ZOMBIE) return KoratioBlocks.ZOMBIE_REMAINS.get();
		if (zombie.getType() == EntityType.HUSK) return KoratioBlocks.HUSK_REMAINS.get();
		if (zombie.getType() == EntityType.DROWNED) return KoratioBlocks.DROWNED_REMAINS.get();
		if (zombie.getType() == KoratioEntityType.DEMONIC_ZOMBIE.get()) return KoratioBlocks.DEMONIC_ZOMBIE_REMAINS.get();
		return Blocks.AIR;
	}

	public static Block getSkeletonType(Entity skeleton) {
		if (skeleton.getType() == EntityType.SKELETON) return KoratioBlocks.SKELETON_REMAINS.get();
		if (skeleton.getType() == EntityType.WITHER_SKELETON) return KoratioBlocks.WITHER_SKELETON_REMAINS.get();
		if (skeleton.getType() == EntityType.STRAY) return KoratioBlocks.STRAY_REMAINS.get();
		if (skeleton.getType() == EntityType.BOGGED) return KoratioBlocks.BOGGED_REMAINS.get();
		if (skeleton.getType() == KoratioEntityType.DEMONIC_SKELETON.get()) return KoratioBlocks.DEMONIC_SKELETON_REMAINS.get();
		return Blocks.AIR;
	}

	@SubscribeEvent(priority= EventPriority.HIGH)
	public static void onPlayerDrops(LivingDropsEvent event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			Entity killer = event.getSource().getDirectEntity();
			if (event.getSource().getEntity() instanceof Player) {
				killer = event.getSource().getEntity();
			}
			if (killer instanceof Player player) {
				if (EnchantmentHelper.getEnchantmentLevel(player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(KoratioEnchantments.TELEKINESIS), player) > 0) {
					Iterator<ItemEntity> drops = event.getDrops().iterator();
					while (drops.hasNext()) {
						ItemStack current = drops.next().getItem();
						if (player.addItem(current)) {
							drops.remove();
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerFish(ItemFishedEvent event) {
		Player player = event.getEntity();
        if (EnchantmentHelper.getEnchantmentLevel(player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(KoratioEnchantments.TELEKINESIS), player) > 0) {
            Iterator<ItemStack> drops = event.getDrops().iterator();
            while (drops.hasNext()) {
                ItemStack current = drops.next();
                if (player.addItem(current)) {
                    drops.remove();
                }
            }
        }
    }
}