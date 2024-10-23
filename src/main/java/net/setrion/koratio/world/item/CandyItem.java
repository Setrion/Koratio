package net.setrion.koratio.world.item;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.setrion.koratio.registry.KoratioFluids;
import net.setrion.koratio.registry.KoratioFoods;
import net.setrion.koratio.registry.KoratioItems;

import java.util.ArrayList;
import java.util.List;

public class CandyItem extends Item {

    public static final List<CandyItem> CANDY_LIST = new ArrayList<>();

    public CandyItem(Properties properties) {
        super(properties);
        CANDY_LIST.add(this);
    }

    public enum CandyType implements StringRepresentable {
        CANDY_CANE("candy_cane", KoratioFoods.CANDY_CANE, KoratioItems.CANDY_CANE_CASTING_TEMPLATE, 400),
        LOLLIPOP("lollipop", KoratioFoods.LOLLIPOP, KoratioItems.LOLLIPOP_CASTING_TEMPLATE, 300),
        BONBON("bonbon", KoratioFoods.BONBON, KoratioItems.BONBON_CASTING_TEMPLATE, 200);

        private final String name;
        private final FoodProperties food;
        private final DeferredItem<CandyTemplateItem> template;
        private final int cost;

        CandyType(String name, FoodProperties food, DeferredItem<CandyTemplateItem> template, int cost) {
            this.name = name;
            this.food = food;
            this.template = template;
            this.cost = cost;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public FoodProperties getFood() {
            return food;
        }

        public DeferredItem<CandyTemplateItem> getTemplate() {
            return template;
        }

        public int getCost() {
            return cost;
        }
    }

    public enum CandyColor implements StringRepresentable {
        WHITE("white", 16711422, KoratioFluids.MOLTEN_WHITE_SUGAR),
        LIGHT_GRAY("light_gray", 10329495, KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR),
        GRAY("gray", 4673362, KoratioFluids.MOLTEN_GRAY_SUGAR),
        BLACK("black", 2434345, KoratioFluids.MOLTEN_BLACK_SUGAR),
        BROWN("brown", 8606770, KoratioFluids.MOLTEN_BROWN_SUGAR),
        RED("red", 12071980, KoratioFluids.MOLTEN_RED_SUGAR),
        ORANGE("orange", 16356139, KoratioFluids.MOLTEN_ORANGE_SUGAR),
        YELLOW("yellow", 16701759, KoratioFluids.MOLTEN_YELLOW_SUGAR),
        LIME("lime", 8834086, KoratioFluids.MOLTEN_LIME_SUGAR),
        GREEN("green", 6653465, KoratioFluids.MOLTEN_GREEN_SUGAR),
        CYAN("cyan", 1481628, KoratioFluids.MOLTEN_CYAN_SUGAR),
        LIGHT_BLUE("light_blue", 5162471, KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR),
        BLUE("blue", 4083122, KoratioFluids.MOLTEN_BLUE_SUGAR),
        PURPLE("purple", 9913293, KoratioFluids.MOLTEN_PURPLE_SUGAR),
        MAGENTA("magenta", 14049489, KoratioFluids.MOLTEN_MAGENTA_SUGAR),
        PINK("pink", 16036553, KoratioFluids.MOLTEN_PINK_SUGAR);

        private final int color;
        private final DeferredHolder<Fluid, FlowingFluid> fluid;
        private final String name;

        CandyColor(String name, int color, DeferredHolder<Fluid, FlowingFluid> fluid) {
            this.color = color;
            this.fluid = fluid;
            this.name = name;
        }

        public int getColor() {
            return color;
        }

        public DeferredHolder<Fluid, FlowingFluid> getFluid() {
            return fluid;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}