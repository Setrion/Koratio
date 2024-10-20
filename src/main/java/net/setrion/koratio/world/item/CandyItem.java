package net.setrion.koratio.world.item;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.setrion.koratio.registry.KoratioFoods;

import java.util.ArrayList;
import java.util.List;

public class CandyItem extends Item {

    public static final List<CandyItem> CANDY_LIST = new ArrayList<>();

    public CandyItem(Properties properties) {
        super(properties);
        CANDY_LIST.add(this);
    }

    public enum CandyType implements StringRepresentable {
        CANDY_CANE("candy_cane", KoratioFoods.CANDY_CANE, 400),
        LOLLIPOP("lollipop", KoratioFoods.LOLLIPOP, 300),
        BONBON("bonbon", KoratioFoods.BONBON, 200);

        private final String name;
        private final FoodProperties food;
        private final int cost;

        CandyType(String name, FoodProperties food, int cost) {
            this.name = name;
            this.food = food;
            this.cost = cost;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public FoodProperties getFood() {
            return food;
        }
    }

    public enum CandyColor implements StringRepresentable {
        WHITE("white", 16711422),
        LIGHT_GRAY("light_gray", 10329495),
        GRAY("gray", 4673362),
        BLACK("black", 2434345),
        BROWN("brown", 8606770),
        RED("red", 12071980),
        ORANGE("orange", 16356139),
        YELLOW("yellow", 16701759),
        LIME("lime", 8834086),
        GREEN("green", 6653465),
        CYAN("cyan", 1481628),
        LIGHT_BLUE("light_blue", 5162471),
        BLUE("blue", 4083122),
        PURPLE("purple", 9913293),
        MAGENTA("magenta", 14049489),
        PINK("pink", 16036553);

        private final int color;
        private final String name;

        CandyColor(String name, int color) {
            this.color = color;
            this.name = name;
        }

        public int getColor() {
            return color;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}