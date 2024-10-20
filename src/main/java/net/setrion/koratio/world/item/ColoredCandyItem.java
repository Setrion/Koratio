package net.setrion.koratio.world.item;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

public class ColoredCandyItem extends CandyItem {

    private static final List<ColoredCandyItem> CANDY = new ArrayList<>();
    private static final List<ColoredCandyItem> CANDY_CANES = new ArrayList<>();
    private static final List<ColoredCandyItem> LOLLIPOPS = new ArrayList<>();
    private static final List<ColoredCandyItem> BONBONS = new ArrayList<>();
    private final int baseColor;
    private final int secondColor;

    public ColoredCandyItem(Properties properties, CandyType type, int color) {
        this(properties, type, color, color);
    }

    public ColoredCandyItem(Properties properties, CandyType type, int baseColor, int secondColor) {
        super(properties);
        this.baseColor = baseColor;
        this.secondColor = secondColor;
        CANDY.add(this);
        if (type == CandyType.CANDY_CANE) CANDY_CANES.add(this);
        if (type == CandyType.LOLLIPOP) LOLLIPOPS.add(this);
        if (type == CandyType.BONBON) BONBONS.add(this);
    }

    public int getColor(int tintIndex) {
        if (tintIndex == 2) return -1;
        return tintIndex == 0 ? this.baseColor : this.secondColor;
    }

    public static Iterable<ColoredCandyItem> candy() {
        return Iterables.unmodifiableIterable(CANDY);
    }

    public static Iterable<ColoredCandyItem> candyCanes() {
        return Iterables.unmodifiableIterable(CANDY_CANES);
    }

    public static Iterable<ColoredCandyItem> lollipops() {
        return Iterables.unmodifiableIterable(LOLLIPOPS);
    }

    public static Iterable<ColoredCandyItem> bonbons() {
        return Iterables.unmodifiableIterable(BONBONS);
    }
}