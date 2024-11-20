package net.setrion.koratio.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.world.entity.animal.Mooshroom;

@OnlyIn(Dist.CLIENT)
public class MooshroomRenderState extends LivingEntityRenderState {
    public Mooshroom.MushroomType variant;

    public MooshroomRenderState() {
        this.variant = Mooshroom.MushroomType.PURPLE;
    }
}
