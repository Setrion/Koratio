package net.setrion.koratio.registry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;

public class KoratioVillagerTypes {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, Koratio.MOD_ID);

    public static final DeferredHolder<VillagerProfession, VillagerProfession> OUTCAST = PROFESSIONS.register("outcast", () -> new VillagerProfession("outcast", holder -> holder.is(KoratioPoiTypes.OUTCAST.getKey()), holder -> holder.is(KoratioPoiTypes.OUTCAST.getKey()), ImmutableSet.of(Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.BONE_MEAL), ImmutableSet.of(Blocks.FARMLAND), SoundEvents.VILLAGER_WORK_LIBRARIAN));

    public static void register() {
        VillagerTrades.TRADES.put(OUTCAST.get(), toIntMap(
                ImmutableMap.of(
                        1,
                        new VillagerTrades.ItemListing[]{
                                new EmeraldForItems(Items.WHEAT, 20, 16, 2),
                                new EmeraldForItems(Items.POTATO, 26, 16, 2),
                                new EmeraldForItems(Items.CARROT, 22, 16, 2),
                                new EmeraldForItems(Items.BEETROOT, 15, 16, 2),
                                new ItemsForEmeralds(Items.AMETHYST_SHARD, 1, 6, 16, 1)
                        }
        )));
    }

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> map) {
        return new Int2ObjectOpenHashMap<>(map);
    }

    static class ItemsForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeralds(Block block, int emeraldCost, int amount, int maxUses, int xp) {
            this(new ItemStack(block), emeraldCost, amount, maxUses, xp);
        }

        public ItemsForEmeralds(Item item, int emeraldCost, int amount, int xp) {
            this(new ItemStack(item), emeraldCost, amount, 12, xp);
        }

        public ItemsForEmeralds(Item item, int emeraldCost, int amount, int maxUses, int xp) {
            this(new ItemStack(item), emeraldCost, amount, maxUses, xp);
        }

        public ItemsForEmeralds(ItemStack stack, int emeraldCost, int amount, int maxUses, int xp) {
            this(stack, emeraldCost, amount, maxUses, xp, 0.05F);
        }

        public ItemsForEmeralds(Item item, int emeraldCost, int amount, int maxUses, int xp, float multiplier) {
            this(new ItemStack(item), emeraldCost, amount, maxUses, xp, multiplier);
        }

        public ItemsForEmeralds(ItemStack stack, int emeraldCost, int amount, int maxUses, int xp, float multiplier) {
            this.itemStack = stack;
            this.emeraldCost = emeraldCost;
            this.itemStack.setCount(amount);
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = multiplier;
        }

        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            return new MerchantOffer(new ItemCost(Items.EMERALD, this.emeraldCost), this.itemStack.copy(), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class EmeraldForItems implements VillagerTrades.ItemListing {
        private final ItemCost itemStack;
        private final int maxUses;
        private final int villagerXp;
        private final int emeraldAmount;
        private final float priceMultiplier;

        public EmeraldForItems(ItemLike item, int cost, int maxUses, int xp) {
            this(item, cost, maxUses, xp, 1);
        }

        public EmeraldForItems(ItemLike item, int cost, int maxUses, int xp, int emeraldAmount) {
            this(new ItemCost(item.asItem(), cost), maxUses, xp, emeraldAmount);
        }

        public EmeraldForItems(ItemCost stack, int maxUses, int xp, int emeraldAmount) {
            this.itemStack = stack;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.emeraldAmount = emeraldAmount;
            this.priceMultiplier = 0.05F;
        }

        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            return new MerchantOffer(
                    this.itemStack, new ItemStack(Items.EMERALD, this.emeraldAmount), this.maxUses, this.villagerXp, this.priceMultiplier
            );
        }
    }
}