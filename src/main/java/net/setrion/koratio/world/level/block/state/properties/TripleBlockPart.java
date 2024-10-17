package net.setrion.koratio.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum TripleBlockPart implements StringRepresentable {
    UPPER(),
    MIDDLE(),
    LOWER();

    TripleBlockPart() {
    }

    @Override
    public String toString() {
        return this.getSerializedName();
    }

    @Override
    public String getSerializedName() {
        if (this == UPPER) return "upper";
        else if (this == MIDDLE) return "middle";
        else return "lower";
    }
}
