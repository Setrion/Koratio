package net.setrion.koratio.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.setrion.koratio.world.entity.monster.JumStem;

public class JumstemRenderState extends LivingEntityRenderState {
    public JumStem.Variant variant;
    public int mushrooms;

    public JumstemRenderState() {
        this.variant = JumStem.Variant.PURPLE;
    }

    public JumStem.Variant getVariant() {
        return variant;
    }
}