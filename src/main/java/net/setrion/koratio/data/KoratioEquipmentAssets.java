package net.setrion.koratio.data;

import net.minecraft.Util;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.setrion.koratio.Koratio;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class KoratioEquipmentAssets extends EquipmentAssetProvider {
    private final PackOutput.PathProvider pathProvider;

    static ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> BONE = createId("bone");
    public static final ResourceKey<EquipmentAsset> WITHER_BONE = createId("wither_bone");
    public static final ResourceKey<EquipmentAsset> VARYNIUM = createId("varynium");

    static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(ROOT_ID, Koratio.prefix(name));
    }

    public static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(BONE, EquipmentClientInfo.builder().addHumanoidLayers(Koratio.prefix("bone")).build());
        consumer.accept(WITHER_BONE, EquipmentClientInfo.builder().addHumanoidLayers(Koratio.prefix("wither_bone")).build());
        consumer.accept(VARYNIUM, EquipmentClientInfo.builder().addHumanoidLayers(Koratio.prefix("varynium")).addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, EquipmentClientInfo.Layer.leatherDyeable(Koratio.prefix("varynium"), false)).build());
    }

    public KoratioEquipmentAssets(PackOutput output) {
        super(output);
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/equipment");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> map = new HashMap<>();
        KoratioEquipmentAssets.bootstrap((resourceLocation, model) -> {
            if (map.putIfAbsent(resourceLocation, model) != null) {
                throw new IllegalStateException("Tried to register equipment model twice for id: " + resourceLocation);
            }
        });
        return DataProvider.saveAll(output, EquipmentClientInfo.CODEC, this.pathProvider::json, map);
    }

    @Override
    public String getName() {
        return "Equipment Model Definitions";
    }
}