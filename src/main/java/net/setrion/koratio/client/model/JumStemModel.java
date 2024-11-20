package net.setrion.koratio.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.setrion.koratio.client.renderer.entity.state.JumstemRenderState;

public class JumStemModel extends EntityModel<JumstemRenderState> {

	public JumStemModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leg = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(42, 0).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-7.0F, -13.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 2).addBox(-7.0F, -11.0F, -7.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 2).addBox(7.0F, -11.0F, -7.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-7.0F, -11.0F, -7.0F, 14.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-7.0F, -11.0F, 7.0F, 14.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(0, 23).addBox(-2.5F, -21.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		leg.addOrReplaceChild("head", CubeListBuilder.create().texOffs(21, 16).addBox(-3.5F, -16.0F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-1.5F, -11.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}