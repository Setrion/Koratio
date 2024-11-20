package net.setrion.koratio.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.setrion.koratio.Koratio;

import javax.annotation.Nullable;

public class MagicalCatRenderState extends LivingEntityRenderState {
    private static final ResourceLocation DEFAULT_TEXTURE = Koratio.prefix("textures/entity/magical_cat/red.png");
    public boolean isSitting;
    @Nullable
    public DyeColor collarColor;
    public ResourceLocation texture = DEFAULT_TEXTURE;

    public MagicalCatRenderState() {
    }
}