package net.setrion.koratio.world.entity.animal;

import net.minecraft.resources.ResourceLocation;

public record MagicalCatVariant(ResourceLocation texture) {

    public MagicalCatVariant(ResourceLocation texture) {
        this.texture = texture;
    }

    public ResourceLocation texture() {
        return this.texture;
    }
}