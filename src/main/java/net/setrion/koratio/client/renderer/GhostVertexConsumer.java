package net.setrion.koratio.client.renderer;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.neoforged.neoforge.client.model.pipeline.VertexConsumerWrapper;

public final class GhostVertexConsumer extends VertexConsumerWrapper
{
    private final int alpha;
    private final int red;
    private final int green;
    private final int blue;

    public GhostVertexConsumer(VertexConsumer wrapped, int alpha, int red, int green, int blue)
    {
        super(wrapped);
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public VertexConsumer setColor(int red, int green, int blue, int alpha) {
        return parent.setColor((red * this.red) / 0xFF, (green * this.green) / 0xFF, (blue * this.blue) / 0xFF, (alpha * this.alpha) / 0xFF);
    }
}