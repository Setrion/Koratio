package net.setrion.koratio.client.model.item;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;

public class ConvertibleItemModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
    private ResourceLocation item;

    public static <T extends ModelBuilder<T>> ConvertibleItemModelBuilder<T> begin(T parent, ExistingFileHelper existingFileHelper) {
        return new ConvertibleItemModelBuilder<>(parent, existingFileHelper);
    }

    protected ConvertibleItemModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
        super(Koratio.prefix("convertible_item"), parent, existingFileHelper, false);
    }

    public ConvertibleItemModelBuilder<T> item(ItemLike item) {
        Preconditions.checkNotNull(item, "item must not be null");
        this.item = BuiltInRegistries.ITEM.getKey(item.asItem());
        return this;
    }

    public JsonObject toJson(JsonObject json) {
        json = super.toJson(json);
        Preconditions.checkNotNull(this.item, "item must not be null");
        json.addProperty("item", this.item.toString());

        return json;
    }
}