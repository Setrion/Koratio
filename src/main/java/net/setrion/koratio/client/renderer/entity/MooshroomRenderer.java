package net.setrion.koratio.client.renderer.entity;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.renderer.entity.layer.MooshroomMushroomLayer;
import net.setrion.koratio.client.renderer.entity.state.MooshroomRenderState;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.Mooshroom;

import java.util.Map;

public class MooshroomRenderer extends AgeableMobRenderer<Mooshroom, MooshroomRenderState, CowModel> {

    private static final Map<Mooshroom.MushroomType, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(Mooshroom.MushroomType.GREEN, Koratio.prefix("textures/entity/mooshroom/green_mooshroom.png"));
        map.put(Mooshroom.MushroomType.PURPLE, Koratio.prefix("textures/entity/mooshroom/purple_mooshroom.png"));
    });

    public MooshroomRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel(context.bakeLayer(ModelLayers.MOOSHROOM)), new CowModel(context.bakeLayer(ModelLayers.MOOSHROOM)), 0.7F);
        this.addLayer(new MooshroomMushroomLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public MooshroomRenderState createRenderState() {
        return new MooshroomRenderState();
    }

    public ResourceLocation getTextureLocation(MooshroomRenderState state) {
        return TEXTURES.get(state.variant);
    }
}
