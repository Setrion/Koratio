package net.setrion.koratio.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlufferRenderState extends LivingEntityRenderState {
    public boolean isSheared;
    public DyeColor woolColor;
    public int id;

    public FlufferRenderState() {
        this.woolColor = DyeColor.WHITE;
    }
}