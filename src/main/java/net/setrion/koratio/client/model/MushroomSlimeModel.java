package net.setrion.koratio.client.model;

import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Slime;

public class MushroomSlimeModel<T extends Slime> extends SlimeModel<T> {

	public MushroomSlimeModel(ModelPart root) {
		super(root);
	}
	
	public ModelPart getSlime() {
		return root();
	}
}