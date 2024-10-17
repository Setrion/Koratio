package net.setrion.koratio.world.item;

import net.minecraft.world.item.Item;

public class CandyItem extends Item {

    public CandyItem(Properties properties) {
        super(properties);
    }

    public enum CandyType {
        CANDY_CANE("candy_cane"),
        LOLLIPOP("lollipop");

        private final String name;

        CandyType(String name) {
            this.name = name;
        }
    }

    public enum CandyColor {
        WHITE(15988214),
        BLUE(3901899),
        GREEN(4244277),
        YELLOW(14540605),
        RED(11675688);

        private final int color;

        CandyColor(int color) {
            this.color = color;
        }

        public int getColor() {
            return color;
        }
    }
}