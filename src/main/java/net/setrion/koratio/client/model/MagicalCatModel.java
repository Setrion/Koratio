package net.setrion.koratio.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.setrion.koratio.world.entity.animal.MagicalCat;

public class MagicalCatModel<T extends MagicalCat> extends AgeableListModel<T> {
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart front_right_paw;
	private final ModelPart front_left_paw;
	private final ModelPart back_right_paw;
	private final ModelPart back_left_paw;

	public MagicalCatModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = root.getChild("tail");
		this.front_right_paw = root.getChild("front_right_paw");
		this.front_left_paw = root.getChild("front_left_paw");
		this.back_right_paw = root.getChild("back_right_paw");
		this.back_left_paw = root.getChild("back_left_paw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -6.0F, 10.0F, 7.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("horn_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -3.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -6.0F, -0.3927F, 0.0F, 0.0F));

		body.addOrReplaceChild("nose_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, -5.5F, 0.0F, 0.0F, -0.7854F));

		body.addOrReplaceChild("right_ear_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -8.25F, -4.5F, 0.0F, 0.0F, 0.7854F));
		body.addOrReplaceChild("left_ear_r1", CubeListBuilder.create().texOffs(8, 28).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -8.25F, -4.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 20).addBox(-1.0F, -1.9659F, 0.2588F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, 6.0F, 0.2618F, 0.0F, 0.0F));

		tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -0.999F, 0.0436F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 6.0F, -0.2182F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("front_right_paw", CubeListBuilder.create().texOffs(26, 20).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 23.0F, -3.0F));
		partdefinition.addOrReplaceChild("front_left_paw", CubeListBuilder.create().texOffs(10, 20).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 23.0F, -3.0F));
		partdefinition.addOrReplaceChild("back_right_paw", CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 23.0F, 4.0F));
		partdefinition.addOrReplaceChild("back_left_paw", CubeListBuilder.create().texOffs(0, 5).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 23.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void prepareMobModel(T entity, float f, float f2, float f3) {
		if (entity.isInSittingPose()) {
			body.xRot = -0.2618F;
			tail.xRot = -0.1745F;
			tail.getChild("cube_r1").xRot = 0.1745F;
		} else {
			body.xRot = 0.0F;
			tail.xRot = 0.2618F;
			tail.getChild("cube_r1").xRot = -0.2182F;
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

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		if (this.young) {
			poseStack.pushPose();
			poseStack.scale(0.5F, 0.5F, 0.5F);
			poseStack.translate(0.0F, 1.5F, 0.0F);
			ImmutableList.of(this.body, this.back_left_paw, this.back_right_paw, this.front_left_paw, this.front_right_paw, this.tail).forEach((part) -> {
				part.render(poseStack, buffer, packedLight, packedOverlay);
			});
			poseStack.popPose();
		} else {
			poseStack.pushPose();
			ImmutableList.of(this.body, this.back_left_paw, this.back_right_paw, this.front_left_paw, this.front_right_paw, this.tail).forEach((part) -> {
				part.render(poseStack, buffer, packedLight, packedOverlay);
			});
			poseStack.popPose();
		}
	}
}