package net.setrion.koratio.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.setrion.koratio.Koratio;

public class KoratioEnchantments {

    public static final ResourceKey<Enchantment> TELEKINESIS = registerKey("telekinesis");

    private static ResourceKey<Enchantment> registerKey(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, Koratio.prefix(name));
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        register(context, TELEKINESIS, new Enchantment.Builder(Enchantment.definition(
                items.getOrThrow(KoratioTags.Items.TELEKINESIS_ENCHANTABLE),
                    1,
                    1,
                    Enchantment.dynamicCost(25, 25),
                    Enchantment.dynamicCost(75, 25),
                    7,
                    EquipmentSlotGroup.MAINHAND
                )
            )
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}