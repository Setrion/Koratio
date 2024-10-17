package net.setrion.koratio.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum AncientDoorState implements StringRepresentable {
    IDLE("idle"),
    BLOCKING("blocking"),
    PASS_THROUGH("pass_through");

    private final String name;

    AncientDoorState(String pName) {
        this.name = pName;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}