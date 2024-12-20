package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import net.setrion.koratio.registry.KoratioBlockEntityType;

public class GlazedBlockEntity extends BlockEntity {

    public static final ModelProperty<Boolean> GLAZED_NORTH_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_TOP_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_TOP_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_TOP_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_BOTTOM_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_BOTTOM_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_BOTTOM_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedNorth = new boolean[9];

    public static final ModelProperty<Boolean> GLAZED_EAST_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_TOP_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_TOP_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_TOP_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_BOTTOM_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_BOTTOM_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_BOTTOM_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedEast = new boolean[9];

    public static final ModelProperty<Boolean> GLAZED_SOUTH_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_TOP_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_TOP_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_TOP_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_BOTTOM_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_BOTTOM_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_BOTTOM_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedSouth = new boolean[9];

    public static final ModelProperty<Boolean> GLAZED_WEST_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_TOP_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_TOP_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_TOP_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_BOTTOM_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_BOTTOM_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_BOTTOM_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedWest = new boolean[9];

    public static final ModelProperty<Boolean> GLAZED_TOP_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_TOP_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_TOP_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_TOP_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_BOTTOM_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_BOTTOM_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_BOTTOM_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedTop = new boolean[9];

    public static final ModelProperty<Boolean> GLAZED_BOTTOM_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_TOP_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_TOP_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_TOP_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_BOTTOM_MIDDLE = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_BOTTOM_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_BOTTOM_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedBottom = new boolean[9];
    private final PartColor[] glazedColor = new PartColor[6];

    public GlazedBlockEntity(BlockPos pos, BlockState blockState) {
        super(KoratioBlockEntityType.GLAZED_BLOCK.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        CompoundTag Glaze = new CompoundTag();
        for (Direction facing : Direction.values()) {
            CompoundTag Facing = new CompoundTag();
            if (facing == Direction.NORTH) {
                Facing.putBoolean(Part.LEFT.name, glazedNorth[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedNorth[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP_MIDDLE.name, glazedNorth[Part.TOP_MIDDLE.id]);
                Facing.putBoolean(Part.TOP_LEFT.name, glazedNorth[Part.TOP_LEFT.id]);
                Facing.putBoolean(Part.TOP_RIGHT.name, glazedNorth[Part.TOP_RIGHT.id]);
                Facing.putBoolean(Part.BOTTOM_MIDDLE.name, glazedNorth[Part.BOTTOM_MIDDLE.id]);
                Facing.putBoolean(Part.BOTTOM_LEFT.name, glazedNorth[Part.BOTTOM_LEFT.id]);
                Facing.putBoolean(Part.BOTTOM_RIGHT.name, glazedNorth[Part.BOTTOM_RIGHT.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedNorth[Part.MIDDLE.id]);
                if (glazedColor[0] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[0].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.EAST) {
                Facing.putBoolean(Part.LEFT.name, glazedEast[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedEast[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP_MIDDLE.name, glazedEast[Part.TOP_MIDDLE.id]);
                Facing.putBoolean(Part.TOP_LEFT.name, glazedEast[Part.TOP_LEFT.id]);
                Facing.putBoolean(Part.TOP_RIGHT.name, glazedEast[Part.TOP_RIGHT.id]);
                Facing.putBoolean(Part.BOTTOM_MIDDLE.name, glazedEast[Part.BOTTOM_MIDDLE.id]);
                Facing.putBoolean(Part.BOTTOM_LEFT.name, glazedEast[Part.BOTTOM_LEFT.id]);
                Facing.putBoolean(Part.BOTTOM_RIGHT.name, glazedEast[Part.BOTTOM_RIGHT.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedEast[Part.MIDDLE.id]);
                if (glazedColor[1] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[1].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.SOUTH) {
                Facing.putBoolean(Part.LEFT.name, glazedSouth[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedSouth[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP_MIDDLE.name, glazedSouth[Part.TOP_MIDDLE.id]);
                Facing.putBoolean(Part.TOP_LEFT.name, glazedSouth[Part.TOP_LEFT.id]);
                Facing.putBoolean(Part.TOP_RIGHT.name, glazedSouth[Part.TOP_RIGHT.id]);
                Facing.putBoolean(Part.BOTTOM_MIDDLE.name, glazedSouth[Part.BOTTOM_MIDDLE.id]);
                Facing.putBoolean(Part.BOTTOM_LEFT.name, glazedSouth[Part.BOTTOM_LEFT.id]);
                Facing.putBoolean(Part.BOTTOM_RIGHT.name, glazedSouth[Part.BOTTOM_RIGHT.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedSouth[Part.MIDDLE.id]);
                if (glazedColor[2] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[2].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.WEST) {
                Facing.putBoolean(Part.LEFT.name, glazedWest[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedWest[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP_MIDDLE.name, glazedWest[Part.TOP_MIDDLE.id]);
                Facing.putBoolean(Part.TOP_LEFT.name, glazedWest[Part.TOP_LEFT.id]);
                Facing.putBoolean(Part.TOP_RIGHT.name, glazedWest[Part.TOP_RIGHT.id]);
                Facing.putBoolean(Part.BOTTOM_MIDDLE.name, glazedWest[Part.BOTTOM_MIDDLE.id]);
                Facing.putBoolean(Part.BOTTOM_LEFT.name, glazedWest[Part.BOTTOM_LEFT.id]);
                Facing.putBoolean(Part.BOTTOM_RIGHT.name, glazedWest[Part.BOTTOM_RIGHT.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedWest[Part.MIDDLE.id]);
                if (glazedColor[3] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[3].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.UP) {
                Facing.putBoolean(Part.LEFT.name, glazedTop[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedTop[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP_MIDDLE.name, glazedTop[Part.TOP_MIDDLE.id]);
                Facing.putBoolean(Part.TOP_LEFT.name, glazedTop[Part.TOP_LEFT.id]);
                Facing.putBoolean(Part.TOP_RIGHT.name, glazedTop[Part.TOP_RIGHT.id]);
                Facing.putBoolean(Part.BOTTOM_MIDDLE.name, glazedTop[Part.BOTTOM_MIDDLE.id]);
                Facing.putBoolean(Part.BOTTOM_LEFT.name, glazedTop[Part.BOTTOM_LEFT.id]);
                Facing.putBoolean(Part.BOTTOM_RIGHT.name, glazedTop[Part.BOTTOM_RIGHT.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedTop[Part.MIDDLE.id]);
                if (glazedColor[4] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[4].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.DOWN) {
                Facing.putBoolean(Part.LEFT.name, glazedBottom[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedBottom[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP_MIDDLE.name, glazedBottom[Part.TOP_MIDDLE.id]);
                Facing.putBoolean(Part.TOP_LEFT.name, glazedBottom[Part.TOP_LEFT.id]);
                Facing.putBoolean(Part.TOP_RIGHT.name, glazedBottom[Part.TOP_RIGHT.id]);
                Facing.putBoolean(Part.BOTTOM_MIDDLE.name, glazedBottom[Part.BOTTOM_MIDDLE.id]);
                Facing.putBoolean(Part.BOTTOM_LEFT.name, glazedBottom[Part.BOTTOM_LEFT.id]);
                Facing.putBoolean(Part.BOTTOM_RIGHT.name, glazedBottom[Part.BOTTOM_RIGHT.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedBottom[Part.MIDDLE.id]);
                if (glazedColor[5] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[5].name);
                }
                Glaze.put(facing.getName(), Facing);
            }
        }
        tag.put("Glaze", Glaze);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);
        requestModelDataUpdate();
        setChanged();
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider registries) {
        super.onDataPacket(connection, packet, registries);
        requestModelDataUpdate();
        setChanged();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        CompoundTag north = tag.getCompound("Glaze").getCompound("north");
        for (int n = 0; n < 9; n++) {
            glazedNorth[n] = north.getBoolean(Part.byId(n).name);
        }
        glazedColor[0] = PartColor.byName(north.getString("color"));
        CompoundTag east = tag.getCompound("Glaze").getCompound("east");
        for (int e = 0; e < 9; e++) {
            glazedEast[e] = east.getBoolean(Part.byId(e).name);
        }
        glazedColor[1] = PartColor.byName(east.getString("color"));
        CompoundTag south = tag.getCompound("Glaze").getCompound("south");
        for (int s = 0; s < 9; s++) {
            glazedSouth[s] = south.getBoolean(Part.byId(s).name);
        }
        glazedColor[2] = PartColor.byName(south.getString("color"));
        CompoundTag west = tag.getCompound("Glaze").getCompound("west");
        for (int w = 0; w < 9; w++) {
            glazedWest[w] = west.getBoolean(Part.byId(w).name);
        }
        glazedColor[3] = PartColor.byName(west.getString("color"));
        CompoundTag top = tag.getCompound("Glaze").getCompound("up");
        for (int t = 0; t < 9; t++) {
            glazedTop[t] = top.getBoolean(Part.byId(t).name);
        }
        glazedColor[4] = PartColor.byName(top.getString("color"));
        CompoundTag bottom = tag.getCompound("Glaze").getCompound("down");
        for (int b = 0; b < 9; b++) {
            glazedBottom[b] = bottom.getBoolean(Part.byId(b).name);
        }
        glazedColor[5] = PartColor.byName(bottom.getString("color"));
    }

    public PartColor getColor(int i) {
        if (glazedColor[i] == null) {
            return PartColor.NONE;
        }
        return glazedColor[i];
    }

    public void setColor(int i, PartColor color) {
        glazedColor[i] = color;
        setChanged();
    }

    @Override
    public void setChanged() {
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        super.setChanged();
    }

    public void setPart(Direction facing, Part part, boolean shown) {
        if (facing == Direction.NORTH) {
            glazedNorth[part.id] = shown;
        } else if (facing == Direction.EAST) {
            glazedEast[part.id] = shown;
        } else if (facing == Direction.SOUTH) {
            glazedSouth[part.id] = shown;
        } else if (facing == Direction.WEST) {
            glazedWest[part.id] = shown;
        } else if (facing == Direction.UP) {
            glazedTop[part.id] = shown;
        } else if (facing == Direction.DOWN) {
            glazedBottom[part.id] = shown;
        }
        setChanged();
    }

    public boolean getPart(Direction facing, Part part) {
        if (facing == Direction.NORTH) {
            return glazedNorth[part.id];
        } else if (facing == Direction.EAST) {
            return glazedEast[part.id];
        } else if (facing == Direction.SOUTH) {
            return glazedSouth[part.id];
        } else if (facing == Direction.WEST) {
            return glazedWest[part.id];
        } else if (facing == Direction.UP) {
            return glazedTop[part.id];
        } else if (facing == Direction.DOWN) {
            return glazedBottom[part.id];
        }
        return false;
    }

    public boolean hasOverlay() {
        for(Direction direction : Direction.values()) {
            for (Part part : Part.values()) {
                if (getPart(direction, part)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFaceFull(Direction facing) {
        for (Part part : Part.values()) {
            if (!getPart(facing, part)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasOverlayOnSide(Direction facing) {
        for (Part part : Part.values()) {
            if (getPart(facing, part)) {
                return true;
            }
        }
        return false;
    }

    public void clearOverlays() {
        for (int c = 0; c < 6; c++) {
            glazedColor[c] = PartColor.NONE;
        }
        for (int i = 0; i < 5; i++) {
            glazedNorth[i] = false;
            glazedEast[i] = false;
            glazedSouth[i] = false;
            glazedWest[i] = false;
            glazedTop[i] = false;
            glazedBottom[i] = false;
        }
        setChanged();
        requestModelDataUpdate();
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
    }

    public void clearOverlay(Direction facing) {
        for (int i = 0; i < 9; i++) {
            if (facing == Direction.NORTH) {
                glazedNorth[i] = false;
                glazedColor[0] = PartColor.NONE;
            } else if (facing == Direction.EAST) {
                glazedEast[i] = false;
                glazedColor[1] = PartColor.NONE;
            } else if (facing == Direction.SOUTH) {
                glazedSouth[i] = false;
                glazedColor[2] = PartColor.NONE;
            } else if (facing == Direction.WEST) {
                glazedWest[i] = false;
                glazedColor[3] = PartColor.NONE;
            } else if (facing == Direction.UP) {
                glazedTop[i] = false;
                glazedColor[4] = PartColor.NONE;
            } else if (facing == Direction.DOWN) {
                glazedBottom[i] = false;
                glazedColor[5] = PartColor.NONE;
            }
        }
        setChanged();
        requestModelDataUpdate();
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public ModelData getModelData() {
        return ModelData.builder()
                .with(GlazedBlockEntity.GLAZED_NORTH_MIDDLE, glazedNorth[0])
                .with(GlazedBlockEntity.GLAZED_NORTH_TOP_MIDDLE, glazedNorth[1])
                .with(GlazedBlockEntity.GLAZED_NORTH_TOP_LEFT, glazedNorth[2])
                .with(GlazedBlockEntity.GLAZED_NORTH_TOP_RIGHT, glazedNorth[3])
                .with(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_MIDDLE, glazedNorth[4])
                .with(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_LEFT, glazedNorth[5])
                .with(GlazedBlockEntity.GLAZED_NORTH_BOTTOM_RIGHT, glazedNorth[6])
                .with(GlazedBlockEntity.GLAZED_NORTH_LEFT, glazedNorth[7])
                .with(GlazedBlockEntity.GLAZED_NORTH_RIGHT, glazedNorth[8])
                .with(GlazedBlockEntity.GLAZED_EAST_MIDDLE, glazedEast[0])
                .with(GlazedBlockEntity.GLAZED_EAST_TOP_MIDDLE, glazedEast[1])
                .with(GlazedBlockEntity.GLAZED_EAST_TOP_LEFT, glazedEast[2])
                .with(GlazedBlockEntity.GLAZED_EAST_TOP_RIGHT, glazedEast[3])
                .with(GlazedBlockEntity.GLAZED_EAST_BOTTOM_MIDDLE, glazedEast[4])
                .with(GlazedBlockEntity.GLAZED_EAST_BOTTOM_LEFT, glazedEast[5])
                .with(GlazedBlockEntity.GLAZED_EAST_BOTTOM_RIGHT, glazedEast[6])
                .with(GlazedBlockEntity.GLAZED_EAST_LEFT, glazedEast[7])
                .with(GlazedBlockEntity.GLAZED_EAST_RIGHT, glazedEast[8])
                .with(GlazedBlockEntity.GLAZED_SOUTH_MIDDLE, glazedSouth[0])
                .with(GlazedBlockEntity.GLAZED_SOUTH_TOP_MIDDLE, glazedSouth[1])
                .with(GlazedBlockEntity.GLAZED_SOUTH_TOP_LEFT, glazedSouth[2])
                .with(GlazedBlockEntity.GLAZED_SOUTH_TOP_RIGHT, glazedSouth[3])
                .with(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_MIDDLE, glazedSouth[4])
                .with(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_LEFT, glazedSouth[5])
                .with(GlazedBlockEntity.GLAZED_SOUTH_BOTTOM_RIGHT, glazedSouth[6])
                .with(GlazedBlockEntity.GLAZED_SOUTH_LEFT, glazedSouth[7])
                .with(GlazedBlockEntity.GLAZED_SOUTH_RIGHT, glazedSouth[8])
                .with(GlazedBlockEntity.GLAZED_WEST_MIDDLE, glazedWest[0])
                .with(GlazedBlockEntity.GLAZED_WEST_TOP_MIDDLE, glazedWest[1])
                .with(GlazedBlockEntity.GLAZED_WEST_TOP_LEFT, glazedWest[2])
                .with(GlazedBlockEntity.GLAZED_WEST_TOP_RIGHT, glazedWest[3])
                .with(GlazedBlockEntity.GLAZED_WEST_BOTTOM_MIDDLE, glazedWest[4])
                .with(GlazedBlockEntity.GLAZED_WEST_BOTTOM_LEFT, glazedWest[5])
                .with(GlazedBlockEntity.GLAZED_WEST_BOTTOM_RIGHT, glazedWest[6])
                .with(GlazedBlockEntity.GLAZED_WEST_LEFT, glazedWest[7])
                .with(GlazedBlockEntity.GLAZED_WEST_RIGHT, glazedWest[8])
                .with(GlazedBlockEntity.GLAZED_TOP_MIDDLE, glazedTop[0])
                .with(GlazedBlockEntity.GLAZED_TOP_TOP_MIDDLE, glazedTop[1])
                .with(GlazedBlockEntity.GLAZED_TOP_TOP_LEFT, glazedTop[2])
                .with(GlazedBlockEntity.GLAZED_TOP_TOP_RIGHT, glazedTop[3])
                .with(GlazedBlockEntity.GLAZED_TOP_BOTTOM_MIDDLE, glazedTop[4])
                .with(GlazedBlockEntity.GLAZED_TOP_BOTTOM_LEFT, glazedTop[5])
                .with(GlazedBlockEntity.GLAZED_TOP_BOTTOM_RIGHT, glazedTop[6])
                .with(GlazedBlockEntity.GLAZED_TOP_LEFT, glazedTop[7])
                .with(GlazedBlockEntity.GLAZED_TOP_RIGHT, glazedTop[8])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_MIDDLE, glazedBottom[0])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_TOP_MIDDLE, glazedBottom[1])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_TOP_LEFT, glazedBottom[2])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_TOP_RIGHT, glazedBottom[3])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_MIDDLE, glazedBottom[4])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_LEFT, glazedBottom[5])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_BOTTOM_RIGHT, glazedBottom[6])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_LEFT, glazedBottom[7])
                .with(GlazedBlockEntity.GLAZED_BOTTOM_RIGHT, glazedBottom[8])
                .build();
    }

    public enum PartColor implements StringRepresentable {
        NONE("none", 0xFFFFFF),
        WHITE("white", 0xFEFEFE),
        LIGHT_GRAY("light_gray", 0x9D9D97),
        GRAY("gray", 0x474F52),
        BLACK("black", 0x252529),
        BROWN("brown", 0x835432),
        RED("red", 0xB8342C),
        ORANGE("orange", 0xF9932B),
        YELLOW("yellow", 0xFED93F),
        LIME("lime", 0x86CC26),
        GREEN("green", 0x658619),
        CYAN("cyan", 0x169B9C),
        LIGHT_BLUE("light_blue", 0x4EC5E7),
        BLUE("blue", 0x3E4DB2),
        PURPLE("purple", 0x9743CD),
        MAGENTA("magenta", 0xD660D1),
        PINK("pink", 0xF4B2C9);

        private final String name;
        private final int color;

        PartColor(String name, int color) {
            this.name = name;
            this.color = color;
        }

        public static PartColor byName(String name) {
            for (PartColor color : PartColor.values()) {
                if(color.name.equalsIgnoreCase(name)) {
                    return color;
                }
            }
            return null;
        }

        public int getColor() {
            return color;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public enum Part implements StringRepresentable {
        TOP_MIDDLE("top_middle", 1),
        TOP_LEFT("top_left", 2),
        TOP_RIGHT("top_right", 3),
        BOTTOM_MIDDLE("bottom_middle", 4),
        BOTTOM_LEFT("bottom_left", 5),
        BOTTOM_RIGHT("bottom_right", 6),
        LEFT("left", 7),
        RIGHT("right", 8),
        MIDDLE("middle", 0);

        private final String name;
        private final int id;

        Part(String name, int id) {
            this.name  = name;
            this.id = id;
        }

        public static Part byId(int id) {
            for (Part part : Part.values()) {
                if(part.id == id) {
                    return part;
                }
            }
            return null;
        }

        public int getId() {
            return id;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}