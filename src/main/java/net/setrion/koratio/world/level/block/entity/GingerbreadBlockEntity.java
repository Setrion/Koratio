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

public class GingerbreadBlockEntity extends BlockEntity {

    public static final ModelProperty<Boolean> GLAZED_NORTH_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_TOP = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_BOTTOM = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_NORTH_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedNorth = new boolean[5];

    public static final ModelProperty<Boolean> GLAZED_EAST_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_TOP = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_BOTTOM = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_EAST_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedEast = new boolean[5];

    public static final ModelProperty<Boolean> GLAZED_SOUTH_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_TOP = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_BOTTOM = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_SOUTH_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedSouth = new boolean[5];

    public static final ModelProperty<Boolean> GLAZED_WEST_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_TOP = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_BOTTOM = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_WEST_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedWest = new boolean[5];

    public static final ModelProperty<Boolean> GLAZED_TOP_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_TOP = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_BOTTOM = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_TOP_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedTop = new boolean[5];

    public static final ModelProperty<Boolean> GLAZED_BOTTOM_LEFT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_RIGHT = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_TOP = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_BOTTOM = new ModelProperty<>();
    public static final ModelProperty<Boolean> GLAZED_BOTTOM_MIDDLE = new ModelProperty<>();
    private final boolean[] glazedBottom = new boolean[5];
    private final PartColor[] glazedColor = new PartColor[6];

    public GingerbreadBlockEntity(BlockPos pos, BlockState blockState) {
        super(KoratioBlockEntityType.GINGERBREAD_BLOCK.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        CompoundTag Glaze = new CompoundTag();
        for (Direction facing : Direction.values()) {
            if (facing == Direction.NORTH) {
                CompoundTag Facing = new CompoundTag();
                Facing.putBoolean(Part.LEFT.name, glazedNorth[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedNorth[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP.name, glazedNorth[Part.TOP.id]);
                Facing.putBoolean(Part.BOTTOM.name, glazedNorth[Part.BOTTOM.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedNorth[Part.MIDDLE.id]);
                if (glazedColor[0] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[0].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.EAST) {
                CompoundTag Facing = new CompoundTag();
                Facing.putBoolean(Part.LEFT.name, glazedEast[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedEast[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP.name, glazedEast[Part.TOP.id]);
                Facing.putBoolean(Part.BOTTOM.name, glazedEast[Part.BOTTOM.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedEast[Part.MIDDLE.id]);
                if (glazedColor[1] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[1].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.SOUTH) {
                CompoundTag Facing = new CompoundTag();
                Facing.putBoolean(Part.LEFT.name, glazedSouth[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedSouth[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP.name, glazedSouth[Part.TOP.id]);
                Facing.putBoolean(Part.BOTTOM.name, glazedSouth[Part.BOTTOM.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedSouth[Part.MIDDLE.id]);
                if (glazedColor[2] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[2].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.WEST) {
                CompoundTag Facing = new CompoundTag();
                Facing.putBoolean(Part.LEFT.name, glazedWest[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedWest[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP.name, glazedWest[Part.TOP.id]);
                Facing.putBoolean(Part.BOTTOM.name, glazedWest[Part.BOTTOM.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedWest[Part.MIDDLE.id]);
                if (glazedColor[3] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[3].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.UP) {
                CompoundTag Facing = new CompoundTag();
                Facing.putBoolean(Part.LEFT.name, glazedTop[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedTop[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP.name, glazedTop[Part.TOP.id]);
                Facing.putBoolean(Part.BOTTOM.name, glazedTop[Part.BOTTOM.id]);
                Facing.putBoolean(Part.MIDDLE.name, glazedTop[Part.MIDDLE.id]);
                if (glazedColor[4] == null) {
                    Facing.putString("color", PartColor.NONE.name);
                } else {
                    Facing.putString("color", glazedColor[4].name);
                }
                Glaze.put(facing.getName(), Facing);
            } else if (facing == Direction.DOWN) {
                CompoundTag Facing = new CompoundTag();
                Facing.putBoolean(Part.LEFT.name, glazedBottom[Part.LEFT.id]);
                Facing.putBoolean(Part.RIGHT.name, glazedBottom[Part.RIGHT.id]);
                Facing.putBoolean(Part.TOP.name, glazedBottom[Part.TOP.id]);
                Facing.putBoolean(Part.BOTTOM.name, glazedBottom[Part.BOTTOM.id]);
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
        for (int n = 0; n < 5; n++) {
            glazedNorth[n] = north.getBoolean(Part.byId(n).name);
        }
        glazedColor[0] = PartColor.byName(north.getString("color"));
        CompoundTag east = tag.getCompound("Glaze").getCompound("east");
        for (int e = 0; e < 5; e++) {
            glazedEast[e] = east.getBoolean(Part.byId(e).name);
        }
        glazedColor[1] = PartColor.byName(east.getString("color"));
        CompoundTag south = tag.getCompound("Glaze").getCompound("south");
        for (int s = 0; s < 5; s++) {
            glazedSouth[s] = south.getBoolean(Part.byId(s).name);
        }
        glazedColor[2] = PartColor.byName(south.getString("color"));
        CompoundTag west = tag.getCompound("Glaze").getCompound("west");
        for (int w = 0; w < 5; w++) {
            glazedWest[w] = west.getBoolean(Part.byId(w).name);
        }
        glazedColor[3] = PartColor.byName(west.getString("color"));
        CompoundTag top = tag.getCompound("Glaze").getCompound("up");
        for (int t = 0; t < 5; t++) {
            glazedTop[t] = top.getBoolean(Part.byId(t).name);
        }
        glazedColor[4] = PartColor.byName(top.getString("color"));
        CompoundTag bottom = tag.getCompound("Glaze").getCompound("down");
        for (int b = 0; b < 5; b++) {
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

    @Override
    public ModelData getModelData() {
        return ModelData.builder()
                .with(GingerbreadBlockEntity.GLAZED_NORTH_LEFT, glazedNorth[3])
                .with(GingerbreadBlockEntity.GLAZED_NORTH_RIGHT, glazedNorth[4])
                .with(GingerbreadBlockEntity.GLAZED_NORTH_TOP, glazedNorth[1])
                .with(GingerbreadBlockEntity.GLAZED_NORTH_BOTTOM, glazedNorth[2])
                .with(GingerbreadBlockEntity.GLAZED_NORTH_MIDDLE, glazedNorth[0])
                .with(GingerbreadBlockEntity.GLAZED_EAST_LEFT, glazedEast[3])
                .with(GingerbreadBlockEntity.GLAZED_EAST_RIGHT, glazedEast[4])
                .with(GingerbreadBlockEntity.GLAZED_EAST_TOP, glazedEast[1])
                .with(GingerbreadBlockEntity.GLAZED_EAST_BOTTOM, glazedEast[2])
                .with(GingerbreadBlockEntity.GLAZED_EAST_MIDDLE, glazedEast[0])
                .with(GingerbreadBlockEntity.GLAZED_SOUTH_LEFT, glazedSouth[3])
                .with(GingerbreadBlockEntity.GLAZED_SOUTH_RIGHT, glazedSouth[4])
                .with(GingerbreadBlockEntity.GLAZED_SOUTH_TOP, glazedSouth[1])
                .with(GingerbreadBlockEntity.GLAZED_SOUTH_BOTTOM, glazedSouth[2])
                .with(GingerbreadBlockEntity.GLAZED_SOUTH_MIDDLE, glazedSouth[0])
                .with(GingerbreadBlockEntity.GLAZED_WEST_LEFT, glazedWest[3])
                .with(GingerbreadBlockEntity.GLAZED_WEST_RIGHT, glazedWest[4])
                .with(GingerbreadBlockEntity.GLAZED_WEST_TOP, glazedWest[1])
                .with(GingerbreadBlockEntity.GLAZED_WEST_BOTTOM, glazedWest[2])
                .with(GingerbreadBlockEntity.GLAZED_WEST_MIDDLE, glazedWest[0])
                .with(GingerbreadBlockEntity.GLAZED_TOP_LEFT, glazedTop[3])
                .with(GingerbreadBlockEntity.GLAZED_TOP_RIGHT, glazedTop[4])
                .with(GingerbreadBlockEntity.GLAZED_TOP_TOP, glazedTop[1])
                .with(GingerbreadBlockEntity.GLAZED_TOP_BOTTOM, glazedTop[2])
                .with(GingerbreadBlockEntity.GLAZED_TOP_MIDDLE, glazedTop[0])
                .with(GingerbreadBlockEntity.GLAZED_BOTTOM_LEFT, glazedBottom[3])
                .with(GingerbreadBlockEntity.GLAZED_BOTTOM_RIGHT, glazedBottom[4])
                .with(GingerbreadBlockEntity.GLAZED_BOTTOM_TOP, glazedBottom[1])
                .with(GingerbreadBlockEntity.GLAZED_BOTTOM_BOTTOM, glazedBottom[2])
                .with(GingerbreadBlockEntity.GLAZED_BOTTOM_MIDDLE, glazedBottom[0])
                .build();
    }

    public enum PartColor implements StringRepresentable {
        NONE("none", 0xFFFFFF),
        WHITE("white", 0xF3F5F6),
        BLUE("blue", 0x3B89CB),
        GREEN("green", 0x40C335),
        YELLOW("yellow", 0xDDDF3D),
        RED("red", 0xB22828);

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
        TOP("top", 1),
        BOTTOM("bottom", 2),
        RIGHT("right", 3),
        LEFT("left", 4),
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