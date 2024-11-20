package net.setrion.koratio.client.model;

import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.setrion.koratio.client.renderer.entity.state.MagicalCatRenderState;

import java.util.Set;

public class MagicalCatModel extends EntityModel<MagicalCatRenderState> {
	private final ModelPart body;
	private final ModelPart tail;
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(Set.of());

	public MagicalCatModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -6.0F, 10.0F, 7.0F, 13.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -3.5F, 1.0F, 1.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -7.0F, -6.0F, -0.3927F, 0.0F, 0.0F));

		body.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -4.5F, -5.5F, 0.0F, 0.0F, -0.7854F));

		body.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 1.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(2.5F, -8.25F, -4.5F, 0.0F, 0.0F, 0.7854F));
		body.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(8, 28).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 1.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(-2.5F, -8.25F, -4.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 20).addBox(-1.0F, -1.9659F, 0.2588F, 2.0F, 2.0F, 6.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 22.0F, 6.0F, 0.2618F, 0.0F, 0.0F));

		tail.addOrReplaceChild("tail_2", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -0.999F, 0.0436F, 2.0F, 2.0F, 6.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -1.0F, 6.0F, -0.2182F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("front_right_paw", CubeListBuilder.create().texOffs(26, 20).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE), PartPose.offset(-2.5F, 23.0F, -3.0F));
		partdefinition.addOrReplaceChild("front_left_paw", CubeListBuilder.create().texOffs(10, 20).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE), PartPose.offset(2.5F, 23.0F, -3.0F));
		partdefinition.addOrReplaceChild("back_right_paw", CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE), PartPose.offset(-2.5F, 23.0F, 4.0F));
		partdefinition.addOrReplaceChild("back_left_paw", CubeListBuilder.create().texOffs(0, 5).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE), PartPose.offset(2.5F, 23.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(MagicalCatRenderState state) {
		super.setupAnim(state);
		if (state.isSitting) {
			body.xRot = -0.2618F;
			tail.xRot = -0.1745F;
			tail.getChild("tail_2").xRot = 0.1745F;
		} else {
			body.xRot = 0.0F;
			tail.xRot = 0.2618F;
			tail.getChild("tail_2").xRot = -0.2182F;
		}
	}
}