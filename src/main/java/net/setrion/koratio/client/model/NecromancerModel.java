package net.setrion.koratio.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class NecromancerModel<S extends HumanoidRenderState> extends HumanoidModel<S> {

	public NecromancerModel(ModelPart root) {
        super(root);
	}

	public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(cubeDeformation, 0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation)
				.texOffs(34, 30).addBox(-3.0F, -7.0F, -3.5F, 6.0F, 6.0F, 6.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.getChild("body").addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 41).addBox(-4.0F, 12.0F, -2.0F, 8.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
		left_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, cubeDeformation).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -1.3963F, 0.0F, 0.0F));

		left_arm.addOrReplaceChild("lantern", CubeListBuilder.create().texOffs(0, 30).addBox(3.0F, -21.5F, -10.0F, 4.0F, 2.0F, 0.0F, cubeDeformation)
				.texOffs(0, 32).addBox(3.0F, -19.5F, -12.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 22.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, cubeDeformation), PartPose.offset(-5.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, cubeDeformation).mirror(false), PartPose.offset(2.0F, 12.0F, 0.1F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, cubeDeformation), PartPose.offset(-2.0F, 12.0F, 0.1F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(S state) {
		super.setupAnim(state);
		this.leftArm.xRot = 0;
		this.leftArm.yRot = 0;
	}
}