package net.setrion.koratio.world.item;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

public class ColoredCandyItem extends CandyItem {

    private static final List<ColoredCandyItem> CANDY = new ArrayList<>();
    private final CandyColor baseColor;
    private final CandyColor secondColor;
    private final CandyType type;

    public ColoredCandyItem(Properties properties, CandyType type, CandyColor color) {
        this(properties, type, color, color);
    }

    public ColoredCandyItem(Properties properties, CandyType type, CandyColor baseColor, CandyColor secondColor) {
        super(properties);
        this.baseColor = baseColor;
        this.secondColor = secondColor;
        this.type = type;
        CANDY.add(this);
    }

    public int getColor(int tintIndex) {
        if (tintIndex == 2) return -1;
        return tintIndex == 0 ? this.baseColor.getColor() : this.secondColor.getColor();
    }

    public CandyColor getBaseColor() {
        return baseColor;
    }

    public CandyColor getSecondColor() {
        return secondColor;
    }

    public CandyType getType() {
        return type;
    }

    public static Iterable<ColoredCandyItem> candy() {
        return Iterables.unmodifiableIterable(CANDY);
    }
}