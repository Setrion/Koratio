package net.setrion.koratio.world.item;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

public class RainbowCandyItem extends CandyItem {

    private static final List<RainbowCandyItem> CANDY = new ArrayList<>();
    private static final List<RainbowCandyItem> CANDY_CANES = new ArrayList<>();
    private static final List<RainbowCandyItem> LOLLIPOPS = new ArrayList<>();

    private final int baseColor;
    private final int[] rainbowColor;

    public RainbowCandyItem(Properties properties, CandyType type, int baseColor, int color2, int color3, int color4, int color5) {
        super(properties);
        this.baseColor = baseColor;
        this.rainbowColor = new int[4];
        rainbowColor[0] = color2;
        rainbowColor[1] = color3;
        rainbowColor[2] = color4;
        rainbowColor[3] = color5;
        CANDY.add(this);
        if (type == CandyType.CANDY_CANE) CANDY_CANES.add(this);
        if (type == CandyType.LOLLIPOP) LOLLIPOPS.add(this);
    }

    public int getRainbowColor(int tintIndex) {
        if (tintIndex == 5) return -1;
        return tintIndex == 0 ? baseColor : rainbowColor[tintIndex-1];
    }

    public static Iterable<RainbowCandyItem> candy() {
        return Iterables.unmodifiableIterable(CANDY);
    }

    public static Iterable<RainbowCandyItem> candyCanes() {
        return Iterables.unmodifiableIterable(CANDY_CANES);
    }

    public static Iterable<RainbowCandyItem> lollipops() {
        return Iterables.unmodifiableIterable(LOLLIPOPS);
    }
}