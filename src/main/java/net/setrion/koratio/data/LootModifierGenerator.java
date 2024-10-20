package net.setrion.koratio.data;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioEnchantments;
import net.setrion.koratio.world.level.storage.loot.modifiers.TelekinesisLootModifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LootModifierGenerator extends GlobalLootModifierProvider {
    
    public LootModifierGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Koratio.MOD_ID);
    }

    @Override
    protected void start() {
        add("telekinesis_teleporting", new TelekinesisLootModifier(new LootItemCondition[]{MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.MINING_ENCHANTABLE).withSubPredicate(ItemSubPredicates.ENCHANTMENTS,
                ItemEnchantmentsPredicate.enchantments(
                        List.of(new EnchantmentPredicate(registries.holderOrThrow(KoratioEnchantments.TELEKINESIS), MinMaxBounds.Ints.atLeast(1)))
                ))).build()}));
    }
}