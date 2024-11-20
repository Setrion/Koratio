package net.setrion.koratio.client.model.block;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class RemainsModel extends Model {

	private final ModelPart root;

	public RemainsModel(ModelPart root) {
		super(root, RenderType::entityTranslucent);
		this.root = root;
	}

	public static LayerDefinition createSkeletonLayer(CubeDeformation cubeDeformation, int x, int y) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("bone_1", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-2.0F, -1.0F, -5.0F, 0.2182F, 0.0F, 1.5708F));
		partdefinition.addOrReplaceChild("bone_2", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 12.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(6.0F, -1.0F, 2.0F, -1.5708F, 0.275F, 0.0F));
		partdefinition.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(-2.0F, -4.0F, 2.0F, 0.0F, 0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, x, y);
	}

	public static LayerDefinition createZombieLayer(CubeDeformation cubeDeformation, int x, int y) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(4.3F, -6.3F, 4.0F, -0.2233F, 0.0271F, 0.5659F));
		partdefinition.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(-2.0F, -6.0F, -3.0F, 0.7854F, 0.0F, 1.5708F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(-2.0F, -2.0F, -1.0F, -1.5708F, -0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, x, y);
	}

	public static LayerDefinition createZombieVillagerLayer(CubeDeformation cubeDeformation, int x, int y) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -1.5F, 8.0F, 10.0F, 8.0F, cubeDeformation)
				.texOffs(24, 0).addBox(-1.0F, 0.0F, -3.5F, 2.0F, 4.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(3.0F, -6.5F, -4.0F, -1.5708F, 0.1309F, 0.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, -12.0F, 0.0F, 4.0F, 12.0F, 4.0F, cubeDeformation).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 4.0F, 0.1134F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-4.9F, 0.0F, -4.0F, 1.5708F, 0.0F, -1.0647F));

		return LayerDefinition.create(meshdefinition, x, y);
	}

	public static LayerDefinition createPhantomLayer(CubeDeformation cubeDeformation, int x, int y) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.5F, -2.5F, 7.0F, 3.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(3F, -1.5F, 5.5F, 0.0F, -2.7489F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.4239F, -1.0F, -4.8827F, 5.0F, 3.0F, 9.0F, cubeDeformation), PartPose.offsetAndRotation(4.0F, -2.0F, -3.5F, 0.0F, 0.3927F, 0.0F));

		body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(23, 12).mirror().addBox(-6.9098F, -0.1604F, -8.3827F, 6.0F, 2.0F, 9.0F, cubeDeformation).mirror(false), PartPose.offsetAndRotation(-2.5F, -1.0F, 3.6F, 0.0F, 0.0F, -0.1745F));
		partdefinition.addOrReplaceChild("right_wing_tip", CubeListBuilder.create().texOffs(16, 24).mirror().addBox(-4.0F, -4.01F, -6.0F, 13.0F, 1.0F, 9.0F, cubeDeformation).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, x, y);
	}

	public static LayerDefinition createZombifiedPiglinLayer(CubeDeformation cubeDeformation, int x, int y) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -1.0F, 10.0F, 8.0F, 8.0F, cubeDeformation)
				.texOffs(31, 1).addBox(-3.0F, -1.75F, -2.0F, 4.0F, 4.0F, 1.0F, cubeDeformation)
				.texOffs(2, 4).addBox(1.0F, 0.25F, -2.0F, 1.0F, 2.0F, 1.0F, cubeDeformation)
				.texOffs(2, 0).addBox(-4.0F, 0.25F, -2.0F, 1.0F, 2.0F, 1.0F, cubeDeformation), PartPose.offsetAndRotation(-2.0F, -6.0F, 1.0F, 0.0F, 0.0F, -1.5708F));

		head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(51, 6).addBox(0.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(3.5F, -3.75F, 3.0F, 0.0F, 0.0F, -0.1745F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(-1.0F, 0.0F, -5.0F, 0.3927F, 0.0F, 1.5708F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -12.0F, 0.0F, 8.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(4.0F, 0.0F, 1.0F, 0.3752F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, x, y);
	}

	public void setupAnim(float yRot, float xRot) {
		this.root.yRot = yRot * (float) (Math.PI / 180);
		this.root.xRot = xRot * (float) (Math.PI / 180);
	}
}