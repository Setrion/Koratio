package net.setrion.koratio.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.BoatModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.vehicle.Boat;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

public class BoatRenderer extends EntityRenderer<Boat> {
	private final Map<Boat.Type, Pair<ResourceLocation, BoatModel>> boatResources;

	public BoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
		super(context);
		this.shadowRadius = 0.8F;
		this.boatResources = Stream.of(Boat.Type.values()).collect(ImmutableMap.toImmutableMap((type) -> {
			return type;
		}, (type) -> {
			return Pair.of(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, getTextureLocation(type, hasChest)), this.createBoatModel(context, type, hasChest));
		}));
	}

	private BoatModel createBoatModel(EntityRendererProvider.Context context, Boat.Type type, boolean hasChest) {
		ModelLayerLocation modellayerlocation = hasChest ? ModelLayers.createChestBoatModelName(type) : ModelLayers.createBoatModelName(type);
		return new BoatModel(context.bakeLayer(modellayerlocation), hasChest);
	}

	private static String getTextureLocation(Boat.Type type, boolean hasChest) {
		return hasChest ? "textures/entity/chest_boat/" + type.getName() + ".png" : "textures/entity/boat/" + type.getName() + ".png";
	}

	public void render(Boat boat, float p_113930_, float p_113931_, PoseStack stack, MultiBufferSource buffer, int p_113934_) {
		stack.pushPose();
		stack.translate(0.0D, 0.375D, 0.0D);
		stack.mulPose(Axis.YP.rotationDegrees(180.0F - p_113930_));
		float f = (float)boat.getHurtTime() - p_113931_;
		float f1 = boat.getDamage() - p_113931_;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			stack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)boat.getHurtDir()));
		}

		float f2 = boat.getBubbleAngle(p_113931_);
		if (!Mth.equal(f2, 0.0F)) {
			stack.mulPose(new Quaternionf().setAngleAxis(boat.getBubbleAngle(p_113931_) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
		}

		Pair<ResourceLocation, BoatModel> pair = getModelWithLocation(boat);
		ResourceLocation resourcelocation = pair.getFirst();
		BoatModel boatmodel = pair.getSecond();
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.mulPose(Axis.YP.rotationDegrees(90.0F));
		boatmodel.setupAnim(boat, p_113931_, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexconsumer = buffer.getBuffer(boatmodel.renderType(resourcelocation));
		boatmodel.renderToBuffer(stack, vertexconsumer, p_113934_, OverlayTexture.NO_OVERLAY);
		if (!boat.isUnderWater()) {
			VertexConsumer vertexconsumer1 = buffer.getBuffer(RenderType.waterMask());
			boatmodel.waterPatch().render(stack, vertexconsumer1, p_113934_, OverlayTexture.NO_OVERLAY);
		}

		stack.popPose();
		super.render(boat, p_113930_, p_113931_, stack, buffer, p_113934_);
	}

	@Deprecated // forge: override getModelWithLocation to change the texture / model
	public ResourceLocation getTextureLocation(Boat boat) {
	return getModelWithLocation(boat).getFirst();
	}

	public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) { return this.boatResources.get(boat.getVariant()); }
}
