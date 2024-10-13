package net.setrion.koratio.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class GingerbreadManModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public GingerbreadManModel(ModelPart root) {
		super();
		this.root = root;
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-4.0F, -8.0F, -0.5F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -0.5F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(10, 26).addBox(-1.0F, -2.0F, -0.5F, 4.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -2.0F, -0.5F, 4.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 13).addBox(-1.9F, 0.0F, -0.6F, 4.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.1F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-2.1F, 0.0F, -0.6F, 4.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.1F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
	}
}