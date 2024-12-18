package net.setrion.koratio.registry;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.setrion.koratio.Koratio;

import java.util.Map;

public class KoratioTrimMaterials {

    public static final ResourceKey<TrimMaterial> RUBY = registerKey("ruby");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = registerKey("sapphire");
    public static final ResourceKey<TrimMaterial> TOPAZ = registerKey("topaz");
    public static final ResourceKey<TrimMaterial> ONYX = registerKey("onyx");

    private static ResourceKey<TrimMaterial> registerKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Koratio.prefix(name));
    }

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, RUBY, KoratioItems.RUBY, Style.EMPTY.withColor(8065056));
        register(context, SAPPHIRE, KoratioItems.SAPPHIRE, Style.EMPTY.withColor(1056891));
        register(context, TOPAZ, KoratioItems.TOPAZ, Style.EMPTY.withColor(8088336));
        register(context, ONYX, KoratioItems.ONYX, Style.EMPTY.withColor(4539717));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Holder<Item> trimItem, Style color) {
        register(context, trimKey, trimItem, color, Map.of());
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Holder<Item> trimItem, Style color, Map<ResourceKey<EquipmentAsset>, String> map) {
        TrimMaterial material = new TrimMaterial(trimKey.location().getPath(), trimItem, map, Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(color));
        context.register(trimKey, material);
    }

}