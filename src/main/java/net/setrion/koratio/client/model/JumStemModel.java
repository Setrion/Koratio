package net.setrion.koratio.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.setrion.koratio.world.entity.monster.JumStem;

public class JumStemModel<T extends JumStem> extends EntityModel<T> {
	private final ModelPart leg;

	public JumStemModel(ModelPart root) {
		this.leg = root.getChild("leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leg = partdefinition.addOrReplaceChild("leg", CubeListBuilder.create().texOffs(42, 0).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-7.0F, -13.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 2).addBox(-7.0F, -11.0F, -7.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 2).addBox(7.0F, -11.0F, -7.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-7.0F, -11.0F, -7.0F, 14.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-7.0F, -11.0F, 7.0F, 14.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(0, 23).addBox(-2.5F, -21.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		leg.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(21, 16).addBox(-3.5F, -16.0F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-1.5F, -11.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}