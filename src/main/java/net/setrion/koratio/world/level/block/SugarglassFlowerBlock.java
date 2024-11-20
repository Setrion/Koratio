package net.setrion.koratio.world.level.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.setrion.koratio.world.item.CandyItem;

public class SugarglassFlowerBlock extends FlowerBlock {

    private final CandyItem.CandyColor color;

    public SugarglassFlowerBlock(CandyItem.CandyColor color, BlockBehaviour.Properties properties) {
        super(MobEffects.MOVEMENT_SPEED, 8, properties);
        this.color = color;
    }

    public CandyItem.CandyColor getColor() {
        return color;
    }
}