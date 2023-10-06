package net.setrion.koratio.registry;

import java.util.List;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.setrion.koratio.Koratio;

public class KoratioItemTier {

	// harvestLevel, maxUses, efficiency, damage, enchantability

	public static final Tier RAINBOW_GEM = TierSortingRegistry.registerTier(
				new ForgeTier(5, 3046, 12.0F, 5, 25, KoratioTags.Blocks.NEEDS_RAINBOW_GEM_TOOL, () -> Ingredient.of(KoratioItems.RAINBOW_GEM.get())),
				Koratio.prefix("rainbow_gem"), List.of(Tiers.NETHERITE), List.of());
	public static final Tier ARSOY = TierSortingRegistry.registerTier(
				new ForgeTier(6, 4062, 18.0F, 6, 30, KoratioTags.Blocks.NEEDS_ARSOY_TOOL, () -> Ingredient.of(KoratioItems.ARSOY_INGOT.get())),
				Koratio.prefix("arsoy"), List.of(RAINBOW_GEM), List.of());
	public static final Tier BONE = TierSortingRegistry.registerTier(
				new ForgeTier(1, 196, 5.0F, 1.5F, 9, KoratioTags.Blocks.NEEDS_BONE_TOOL, () -> Ingredient.of(Items.BONE)),
				Koratio.prefix("bone"), List.of(Tiers.STONE), List.of(Tiers.IRON));
	public static final Tier WITHER_BONE = TierSortingRegistry.registerTier(
				new ForgeTier(3, 970, 7.0F, 2.5F, 13, KoratioTags.Blocks.NEEDS_WITHER_BONE_TOOL, () -> Ingredient.of(KoratioItems.WITHER_BONE.get())),
				Koratio.prefix("wither_bone"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
	
}