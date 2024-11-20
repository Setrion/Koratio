package net.setrion.koratio.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.util.Mth;

public class DemonicZombieModel<S extends ZombieRenderState> extends HumanoidModel<S> {

	public DemonicZombieModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.getChild("body");
		PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(1.0F, -6.0F, 2.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		left_wing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(18, 32).addBox(0.0F, -12.5F, 0.0F, 7.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 1.5F, 2.0F, 0.0F, -0.0873F, 0.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 32).addBox(-10.0F, -6.0F, 2.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		right_wing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 32).mirror().addBox(-7.0F, -12.5F, 0.0F, 7.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.0F, 1.5F, 2.0F, 0.0F, 0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(S state) {
		super.setupAnim(state);

		float f = state.ageInTicks * 10.0F * (float) (Math.PI / 360);
		float f1 = Mth.cos(f) * (float) Math.PI * 0.075F;

		this.body.getChild("right_wing").yRot = -(float) (-Math.PI / 64) + f1 + (0.1309F*2);
		this.body.getChild("left_wing").yRot = -(float) (Math.PI / 64) - f1 - (0.1309F*2);
	}
}