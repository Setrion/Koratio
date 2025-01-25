package net.setrion.koratio.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
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
    private final RemainsModel overlayModel;

    public RemainsSpecialRenderer(RemainsBlock.Type type, RemainsModel model) {
        this(type, model, null);
    }

    public RemainsSpecialRenderer(RemainsBlock.Type type, RemainsModel model, @Nullable RemainsModel overlayModel) {
        this.type = type;
        this.model = model;
        this.overlayModel = overlayModel;
    }

    public void render(ItemDisplayContext context, PoseStack stack, MultiBufferSource buffer, int x, int y, boolean b) {
        RenderType renderType = RemainsBlockRenderer.getRenderType(this.type);
        if (type == RemainsBlock.Type.DROWNED || type == RemainsBlock.Type.STRAY || type == RemainsBlock.Type.BOGGED || type == RemainsBlock.Type.ZOMBIE_VILLAGER) {
            RenderType overlayRenderType = RemainsBlockRenderer.getOverlayRenderType(this.type);
            RemainsBlockRenderer.render2LayerRemains(null, 180F, stack, buffer, x, model, overlayModel, renderType, overlayRenderType);
        } else {
            RemainsBlockRenderer.renderRemains(null, 180F, stack, buffer, x, model, renderType);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static record Unbaked(RemainsBlock.Type kind, Optional<ResourceLocation> textureOverride) implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<RemainsSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                RemainsBlock.Type.CODEC.fieldOf("kind").forGetter(RemainsSpecialRenderer.Unbaked::kind),
                                ResourceLocation.CODEC.optionalFieldOf("texture").forGetter(RemainsSpecialRenderer.Unbaked::textureOverride)
                        )
                        .apply(instance, RemainsSpecialRenderer.Unbaked::new)
        );

        public Unbaked(RemainsBlock.Type type) {
            this(type, Optional.empty());
        }

        @Override
        public MapCodec<RemainsSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Nullable
        @Override
        public SpecialModelRenderer<?> bake(EntityModelSet modelSet) {

            RemainsModel overlayModel;
            if (kind == RemainsBlock.Type.DROWNED || kind == RemainsBlock.Type.STRAY || kind == RemainsBlock.Type.BOGGED || kind == RemainsBlock.Type.ZOMBIE_VILLAGER) {
                overlayModel = RemainsBlockRenderer.createOverlayRenderers(modelSet).get(kind);
            } else {
                overlayModel = null;
            }

            RemainsModel skullmodelbase = RemainsBlockRenderer.createSkullRenderers(modelSet).get(kind);
            return skullmodelbase != null ? new RemainsSpecialRenderer(this.kind, skullmodelbase, overlayModel) : null;
        }
    }
}