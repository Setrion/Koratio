package net.setrion.koratio.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.SkullBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.client.model.block.RemainsModel;
import net.setrion.koratio.client.renderer.blockentity.RemainsBlockRenderer;
import net.setrion.koratio.world.level.block.RemainsBlock;

import javax.annotation.Nullable;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class RemainsSpecialRenderer implements NoDataSpecialModelRenderer {
    private final RemainsBlock.Type type;
    private final RemainsModel model;
    @Nullable
    private final ResourceLocation textureOverride;

    public RemainsSpecialRenderer(RemainsBlock.Type type, RemainsModel model, @Nullable ResourceLocation textureOverride) {
        this.type = type;
        this.model = model;
        this.textureOverride = textureOverride;
    }

    public void render(ItemDisplayContext context, PoseStack stack, MultiBufferSource buffer, int x, int y, boolean b) {
        RenderType renderType = RemainsBlockRenderer.getRenderType(this.type);
        if (type == RemainsBlock.Type.DROWNED || type == RemainsBlock.Type.STRAY || type == RemainsBlock.Type.BOGGED || type == RemainsBlock.Type.ZOMBIE_VILLAGER) {
            RenderType overlayRenderType = RemainsBlockRenderer.getOverlayRenderType(this.type);
            //RemainsBlockRenderer.renderRemains(null, 180F);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static record Unbaked(RemainsBlock.Type kind, Optional<ResourceLocation> textureOverride, float animation) implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<RemainsSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                RemainsBlock.Type.CODEC.fieldOf("kind").forGetter(RemainsSpecialRenderer.Unbaked::kind),
                                ResourceLocation.CODEC.optionalFieldOf("texture").forGetter(RemainsSpecialRenderer.Unbaked::textureOverride),
                                Codec.FLOAT.optionalFieldOf("animation", 0.0F).forGetter(RemainsSpecialRenderer.Unbaked::animation)
                        )
                        .apply(instance, RemainsSpecialRenderer.Unbaked::new)
        );

        public Unbaked(RemainsBlock.Type type) {
            this(type, Optional.empty(), 0.0F);
        }

        @Override
        public MapCodec<RemainsSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Nullable
        @Override
        public SpecialModelRenderer<?> bake(EntityModelSet modelSet) {
            /*
            RemainsModel skullmodelbase = SkullBlockRenderer.createModel(modelSet, this.kind);
            ResourceLocation resourcelocation = this.textureOverride
                    .map(resourceLocation -> resourceLocation.withPath(name -> "textures/entity/" + name + ".png"))
                    .orElse(null);
            return skullmodelbase != null ? new RemainsSpecialRenderer(this.kind, skullmodelbase, resourcelocation, this.animation) : null;*/
            return null;
        }
    }
}