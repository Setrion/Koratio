package net.setrion.koratio.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.setrion.koratio.world.entity.animal.UnicornCat;

public class UnicornCatModel<T extends UnicornCat> extends AgeableListModel<T> {
	
	private final ModelPart body;
	private final ModelPart front_left_paw;
	private final ModelPart front_right_paw;
	private final ModelPart back_left_paw;
	private final ModelPart back_right_paw;
	private final ModelPart tail;
	
	public UnicornCatModel(ModelPart part) {
		this.body = part.getChild("body");
		this.front_left_paw = part.getChild("front_left_paw");
		this.front_right_paw = part.getChild("front_right_paw");
		this.back_left_paw = part.getChild("back_left_paw");
		this.back_right_paw = part.getChild("back_right_paw");
		this.tail = this.body.getChild("tail");
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -0.5F, -6.0F, 8.0F, 4.0F, 12.0F, false), PartPose.offset(0.0F, 20.0F, 0.0F));
		body.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(8, 0).addBox(-0.5F, -3.4748F, 0.1567F, 1.0F, 4.0F, 1.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, -5.6F, 0.3187F, 0.0F, 0.0F));
		body.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(0, 16).addBox(-0.3536F, -0.3536F, 0.0F, 2.0F, 2.0F, 1.0F, false), PartPose.offsetAndRotation(-3.0F, -1.7F, -5.5F, 0.0F, 0.0F, 0.7854F));
		body.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(0, 19).addBox(-0.3536F, -0.3536F, 0.0F, 2.0F, 2.0F, 1.0F, false), PartPose.offsetAndRotation(3.0F, -1.7F, -5.5F, 0.0F, 0.0F, 0.7854F));
		partdefinition.addOrReplaceChild("front_right_paw", CubeListBuilder.create().texOffs(8, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, false), PartPose.offset(-2.0F, 22.0F, -3.0F));
		partdefinition.addOrReplaceChild("front_left_paw", CubeListBuilder.create().texOffs(16, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, false), PartPose.offset(2.0F, 22.0F, -3.0F));
		partdefinition.addOrReplaceChild("back_left_paw", CubeListBuilder.create().texOffs(0, 5).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, false), PartPose.offset(2.0F, 22.0F, 3.0F));
		partdefinition.addOrReplaceChild("back_right_paw", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, false), PartPose.offset(-2.0F, 22.0F, 3.0F));
		body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, -0.4917F, -0.0905F, 1.0F, 1.0F, 6.0F, false), PartPose.offsetAndRotation(0.0F, 2.0F, 5.0F, -0.182F, 0.0F, 0.0F));
		
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	
	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int i, int i2, float f, float f2, float f3, float f4) {
		if (this.young) {
			stack.pushPose();
			stack.scale(0.5F, 0.5F, 0.5F);
			stack.translate(0.0F, 1.5F, 0.0F);
			ImmutableList.of(this.body, this.back_left_paw, this.back_right_paw, this.front_left_paw, this.front_right_paw).forEach((part) -> {
				part.render(stack, consumer, i, i2);
			});
			stack.popPose();
		} else {
			stack.pushPose();
			ImmutableList.of(this.body, this.back_left_paw, this.back_right_paw, this.front_left_paw, this.front_right_paw).forEach((part) -> {
				part.render(stack, consumer, i, i2);
			});
			stack.popPose();
		}
	}
	
	@Override
	public void prepareMobModel(T entity, float f, float f2, float f3) {
		if (entity.isInSittingPose()) {
			this.body.xRot = -0.0873F;
			this.tail.xRot = -0.0511F;
		} else {
			this.body.xRot = 0.0F;
			this.tail.xRot = -0.182F;
		}
	}
	
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of();
	}

	@Override
	public void setupAnim(T entity, float f, float f2, float f3, float f4, float f5) {
		this.tail.yRot = 0.3F * Mth.sin(f * 0.3F + 0.4F);
		
		this.back_right_paw.xRot = Mth.cos(f * 0.6662F) * 1.4F * f2;
		this.back_left_paw.xRot = Mth.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f2;
		this.front_right_paw.xRot = Mth.cos(f * 0.6662F) * 1.4F * f2;
		this.front_left_paw.xRot = Mth.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f2;
	}
}