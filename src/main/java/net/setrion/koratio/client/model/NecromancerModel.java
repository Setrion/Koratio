/*package net.setrion.koratio.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.setrion.koratio.world.entity.demonic.Necromancer;

public class NecromancerModel<T extends Necromancer> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headwear;
	private final ModelPart body;
	private final ModelPart bodywear;
	private final ModelPart left_arm;
	private final ModelPart left_armwear;
	private final ModelPart right_arm;
	private final ModelPart right_armwear;
	private final ModelPart left_leg;
	private final ModelPart left_legwear;
	private final ModelPart right_leg;
	private final ModelPart right_legwear;
	private final ModelPart lantern;

	public NecromancerModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
		this.headwear = root.getChild("headwear");
		this.body = root.getChild("body");
		this.bodywear = root.getChild("bodywear");
		this.left_arm = root.getChild("left_arm");
		this.left_armwear = root.getChild("left_armwear");
		this.right_arm = root.getChild("right_arm");
		this.right_armwear = root.getChild("right_armwear");
		this.left_leg = root.getChild("left_leg");
		this.left_legwear = root.getChild("left_legwear");
		this.right_leg = root.getChild("right_leg");
		this.right_legwear = root.getChild("right_legwear");
		this.lantern = root.getChild("lantern");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("bodywear", CubeListBuilder.create().texOffs(40, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
		left_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -1.3963F, 0.0F, 0.0F));

		left_arm.addOrReplaceChild("lantern", CubeListBuilder.create().texOffs(0, 30).addBox(3.0F, -21.5F, -10.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(3.0F, -19.5F, -12.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 22.0F, 0.0F));


		PartDefinition left_armwear = partdefinition.addOrReplaceChild("left_armwear", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
		left_armwear.addOrReplaceChild("left_armwear_r1", CubeListBuilder.create().texOffs(56, 16).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -1.3963F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_armwear", CubeListBuilder.create().texOffs(56, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.1F));
		partdefinition.addOrReplaceChild("left_legwear", CubeListBuilder.create().texOffs(8, 16).mirror().addBox(-1.25F, 0.0F, -1.1F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.1F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.1F));
		partdefinition.addOrReplaceChild("right_legwear", CubeListBuilder.create().texOffs(8, 16).addBox(-0.75F, 0.0F, -1.1F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.0F, 12.0F, 0.1F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setupAnim(Necromancer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		headwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		bodywear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		left_armwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		right_armwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		left_legwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		right_legwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		lantern.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}*/