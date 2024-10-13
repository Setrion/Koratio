package net.setrion.koratio.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.setrion.koratio.Koratio;

import java.util.List;

public class KoratioItemTier {

	public static final Tier BONE = new SimpleTier(BlockTags.INCORRECT_FOR_STONE_TOOL, 196, 5.0F, 1.5F, 9, () -> Ingredient.of(Items.BONE));
	public static final Tier WITHER_BONE = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 970, 7.0F, 2.5F, 13, () -> Ingredient.of(KoratioItems.WITHER_BONE.get()));
	
}