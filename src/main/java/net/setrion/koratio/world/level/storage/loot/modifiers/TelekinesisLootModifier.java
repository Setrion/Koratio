package net.setrion.koratio.world.level.storage.loot.modifiers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class TelekinesisLootModifier extends LootModifier {

    public static final MapCodec<TelekinesisLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> LootModifier.codecStart(inst).apply(inst, TelekinesisLootModifier::new));

    public TelekinesisLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> loot, LootContext context) {
        ObjectArrayList<ItemStack> ret = new ObjectArrayList<>();
        loot.forEach((stack) -> ret.add(teleport(stack, context)));
        return ret;
    }

    private static ItemStack teleport(ItemStack stack, LootContext context) {
        Entity entity = context.getParam(LootContext.EntityTarget.THIS.getParam());
        if (entity instanceof Player player) {
            return player.addItem(stack) ? ItemStack.EMPTY : stack;
        }
        return stack;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return TelekinesisLootModifier.CODEC;
    }
}