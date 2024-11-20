package net.setrion.koratio.client.model;

import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.PigRenderState;

import java.util.Set;

public class SpikyPigModel extends QuadrupedModel<PigRenderState> {
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(false, 4.0F, 4.0F, Set.of("head"));

	public SpikyPigModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(6, CubeDeformation.NONE);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, CubeDeformation.NONE).texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, CubeDeformation.NONE).texOffs(0, 0).addBox(-5.0F, -5.0F, -6.0F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE).texOffs(0, 0).addBox(4.0F, -5.0F, -6.0F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 12.0F, -6.0F));
		
		body.addOrReplaceChild("spike_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, CubeDeformation.NONE).texOffs(24, 0).addBox(-3.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(1.5F, -5.5F, 1.0F, -0.3927F, 0.0F, 0.0F));
		body.addOrReplaceChild("spike_r2", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, CubeDeformation.NONE)
		.texOffs(24, 0).addBox(-7.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 2.5F, 1.0F, -0.3927F, 0.0F, 0.0F));
		body.addOrReplaceChild("spike_r3", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, CubeDeformation.NONE)
		.texOffs(24, 0).addBox(-5.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -1.5F, 1.0F, -0.3927F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}