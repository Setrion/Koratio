package net.setrion.koratio.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class GoldenFoxSpiritModel extends EntityModel<LivingEntityRenderState> {
	private final ModelPart head;
	private final ModelPart leaf1;
	private final ModelPart leaf2;
	private final ModelPart body;

	public GoldenFoxSpiritModel(ModelPart root) {
		super(root, RenderType::entityTranslucent);
		head = root.getChild("head");
		leaf1 = head.getChild("leaf1");
		leaf2 = head.getChild("leaf2");
		body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 13).addBox(-3.0F, -9.0F, 0.5F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -3.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -15.0F, -1.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(29, 0).addBox(3.0F, -17.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-3.0F, -17.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 0).addBox(-1.0F, -11.0F, -4.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 16.5F, -3.0F));

		head.addOrReplaceChild("leaf1", CubeListBuilder.create().texOffs(18, 17).addBox(-0.975F, -0.5F, -0.375F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -15.5F, 2.5F, 0.0F, 0.5236F, 0.0F));

		head.addOrReplaceChild("leaf2", CubeListBuilder.create().texOffs(9, 13).mirror().addBox(-3.5F, -0.5F, -3.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -15.5F, 3.5F, 0.0F, 0.5236F, 0.0F));

		body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(40, 5).addBox(-1.0F, -6.548F, -5.508F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.001F, 0.0F, 3.0F, -0.48F, 0.0F, 0.0F));

		body.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(32, 5).addBox(-1.0F, -6.548F, -5.508F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.001F, 0.0F, 3.0F, -0.48F, 0.0F, 0.0F));

		body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 18).addBox(2.0F, 3.0642F, 3.8693F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 4.0F, 9.0F, 2.4435F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		/*if (state.) {
			leaf1.visible = true;
			leaf2.visible = true;
		} else {
			leaf1.visible = false;
			leaf2.visible = false;
		}*/
		for(int i = 0; i < 4; ++i) {
			body.y = 12.0F + Mth.cos(((float)(i * 3) + state.ageInTicks) * 0.125F);
			head.y = 12.0F + Mth.cos(((float)(i * 3) + state.ageInTicks) * 0.125F);
		}
		head.yRot = state.xRot * ((float)Math.PI / 180F);
		head.xRot = state.yRot * ((float)Math.PI / 180F);
	}
}