package net.setrion.koratio.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

import java.util.Arrays;

public class CrystallizeModel extends EntityModel<LivingEntityRenderState> {
	private final ModelPart head;
	private final ModelPart[] crystals;

	public CrystallizeModel(ModelPart root) {
		super(root, RenderType::entityTranslucent);
		this.head = root.getChild("head");
		this.crystals = new ModelPart[4];
		Arrays.setAll(this.crystals, (i) -> root.getChild(getPartName(i)));
	}

	private static String getPartName(int i) {
		return "crystal" + i;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		float f = 0.0F;
		CubeListBuilder crystal = CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -3.0F, 0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 5.0F, 0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(1.0F, 8.0F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(1.0F, -5.0F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F));
		
		for(int i = 0; i < 4; ++i) {
			float f1 = Mth.cos(f) * 10.5F;
			float f2 = -2.0F + Mth.cos((float)(i * 2) * 0.25F);
			float f3 = Mth.sin(f) * 10.5F;
			partdefinition.addOrReplaceChild(getPartName(i), crystal, PartPose.offset(f1, f2, f3));
			++f;
		}

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		for(int i = 0; i < 4; ++i) {
			crystals[i].y = 6.0F + Mth.cos(((float)(i * 3) + state.ageInTicks) * 0.25F);
			crystals[i].x = (float) (Math.cos(Math.toRadians(90*(i+1)+(state.ageInTicks*5))) * 10.5F);
			crystals[i].z = (float) (Math.sin(Math.toRadians(90*(i+1)+(state.ageInTicks*5))) * 10.5F);
			crystals[i].yRot = i+state.ageInTicks/20;
		}
		head.yRot = state.yRot * ((float)Math.PI / 180F);
		head.xRot = state.xRot * ((float)Math.PI / 180F);
	}
}