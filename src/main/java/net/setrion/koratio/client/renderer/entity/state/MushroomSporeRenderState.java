package net.setrion.koratio.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MushroomSporeRenderState extends EntityRenderState {
    public float yRot;
    public float xRot;

    public MushroomSporeRenderState() {
    }
}
