package net.setrion.koratio.client.model.item;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedOverrides;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.NeoForgeRenderTypes;
import net.neoforged.neoforge.client.RenderTypeGroup;
import net.neoforged.neoforge.client.model.CompositeModel;
import net.neoforged.neoforge.client.model.ExtraFaceData;
import net.neoforged.neoforge.client.model.geometry.*;
import net.setrion.koratio.util.ConversionUtils;
import net.setrion.koratio.world.item.Convertible;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ConvertibleItemModel implements IUnbakedGeometry<ConvertibleItemModel> {

    private final ItemLike item;

    public ConvertibleItemModel(ItemLike item) {
        this.item = item;
    }

    public ConvertibleItemModel withItem(ItemLike item) {
        return new ConvertibleItemModel(item);
    }

    @Override
    public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, List<ItemOverride> overrides) {
        Material baseLocation = context.hasMaterial("layer0") ? context.getMaterial("layer0") : null;
        RenderTypeGroup normalRenderTypes = new RenderTypeGroup(RenderType.translucent(), NeoForgeRenderTypes.ITEM_UNSORTED_TRANSLUCENT.get());
        CompositeModel.Baked.Builder builder = CompositeModel.Baked.builder(context, spriteGetter.apply(baseLocation), context.getTransforms());
        if (baseLocation != null) {
            TextureAtlasSprite sprite = spriteGetter.apply(baseLocation);
            List<BlockElement> unbaked = UnbakedGeometryHelper.createUnbakedItemElements(0, sprite, ExtraFaceData.DEFAULT);
            List<BakedQuad> quads = UnbakedGeometryHelper.bakeElements(unbaked, ($) -> sprite, modelState);
            builder.addQuads(normalRenderTypes, quads);
        }
        var itemContext = StandaloneGeometryBakingContext.builder(context).withGui3d(false).withUseBlockLight(false).build(ResourceLocation.fromNamespaceAndPath("neoforge", "dynamic_fluid_container"));
        BakedModel bakedModel = builder.build();
        var bakedOverrides = new ConvertibleItemModel.ConvertibleItemOverrideHandler(new BakedOverrides(baker, overrides, spriteGetter), bakedModel, baker, itemContext, this);
        return new ItemModel.BakedModelWithOverrides(bakedModel, bakedOverrides);
    }

    public static final class Loader implements IGeometryLoader<ConvertibleItemModel> {
        public static final ConvertibleItemModel.Loader INSTANCE = new ConvertibleItemModel.Loader();

        @Override
        public ConvertibleItemModel read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) {
            if (!jsonObject.has("base"))
                throw new RuntimeException("ConvertibleItem model requires 'base' value.");

            ResourceLocation itemName = ResourceLocation.parse(jsonObject.get("base").getAsString());

            ItemLike item = BuiltInRegistries.ITEM.getValue(itemName);

            // create new model with correct liquid
            return new ConvertibleItemModel(item);
        }
    }

    private static final class ConvertibleItemOverrideHandler extends BakedOverrides {
        private final Map<String, BakedModel> cache = Maps.newHashMap(); // contains all the baked models since they'll never change
        private final BakedOverrides nested;
        private final BakedModel baseModel;
        private final ModelBaker baker;
        private final IGeometryBakingContext owner;
        private final ConvertibleItemModel parent;

        private ConvertibleItemOverrideHandler(BakedOverrides nested, BakedModel baseModel, ModelBaker baker, IGeometryBakingContext owner, ConvertibleItemModel parent) {
            this.nested = nested;
            this.baseModel = baseModel;
            this.baker = baker;
            this.owner = owner;
            this.parent = parent;
        }

        @Override
        @Nullable
        public BakedModel findOverride(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
            BakedModel overridden = nested.findOverride(stack, level, entity, seed);
            if (overridden != null) return overridden;

            if (stack.getItem() instanceof Convertible convertible) {
                ConversionUtils.ConversionItem item = convertible.getConvertibles().get(ConversionUtils.getConversionDimension(stack));
                String name = BuiltInRegistries.ITEM.getKey(item.item()).toString();

                if (!cache.containsKey(name)) {
                    ConvertibleItemModel unbaked = this.parent.withItem(item.item());
                    BakedModel bakedModel = unbaked.bake(owner, baker, Material::sprite, BlockModelRotation.X0_Y0, List.of());
                    cache.put(name, bakedModel);
                    return bakedModel;
                }

                return cache.get(name);
            }

            return baseModel;
        }
    }
}