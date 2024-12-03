package net.setrion.koratio.data;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentModel;
import net.setrion.koratio.Koratio;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class KoratioEquipmentModels implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public static final ResourceLocation BONE = Koratio.prefix("bone");
    public static final ResourceLocation WITHER_BONE = Koratio.prefix("wither_bone");
    public static final ResourceLocation VARYNIUM = Koratio.prefix("varynium");

    public static void bootstrap(BiConsumer<ResourceLocation, EquipmentModel> consumer) {
        consumer.accept(BONE, EquipmentModel.builder().addHumanoidLayers(Koratio.prefix("bone")).build());
        consumer.accept(WITHER_BONE, EquipmentModel.builder().addHumanoidLayers(Koratio.prefix("wither_bone")).build());
        consumer.accept(VARYNIUM, EquipmentModel.builder().addHumanoidLayers(Koratio.prefix("varynium")).addLayers(EquipmentModel.LayerType.HORSE_BODY, EquipmentModel.Layer.leatherDyeable(Koratio.prefix("varynium"), false)).build());
    }

    public KoratioEquipmentModels(PackOutput output) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/equipment");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        Map<ResourceLocation, EquipmentModel> map = new HashMap<>();
        KoratioEquipmentModels.bootstrap((resourceLocation, model) -> {
            if (map.putIfAbsent(resourceLocation, model) != null) {
                throw new IllegalStateException("Tried to register equipment model twice for id: " + resourceLocation);
            }
        });
        return DataProvider.saveAll(output, EquipmentModel.CODEC, this.pathProvider, map);
    }

    @Override
    public String getName() {
        return "Equipment Model Definitions";
    }
}