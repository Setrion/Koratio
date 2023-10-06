package net.setrion.koratio.client.model;

import java.util.Arrays;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class CrystallizeModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart[] crystals;

	public CrystallizeModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.root = root;
		this.head = root.getChild("head");
		this.crystals = new ModelPart[4];
		Arrays.setAll(this.crystals, (i) -> {
			return root.getChild(getPartName(i));
		});
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
	
	public ModelPart root() {
		return root;
	}
	
	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer builder, int light, int overlay, float red, float green, float blue, float alpha) {
		for (ModelPart crystal : crystals) {
			crystal.render(stack, builder, light, overlay, red, green, blue, 0.75F);
		}
		head.render(stack, builder, light, overlay, red, green, blue, 1.0F);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		for(int i = 0; i < 4; ++i) {
			crystals[i].y = 6.0F + Mth.cos(((float)(i * 3) + ageInTicks) * 0.25F);
			Math.sin(Math.toRadians(90*(i+1)+(ageInTicks*5)));
			crystals[i].x = (float) (Math.cos(Math.toRadians(90*(i+1)+(ageInTicks*5))) * 10.5F);
			crystals[i].z = (float) (Math.sin(Math.toRadians(90*(i+1)+(ageInTicks*5))) * 10.5F);
			crystals[i].yRot = i+ageInTicks/20;
		}
		head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		head.xRot = headPitch * ((float)Math.PI / 180F);
	}
}