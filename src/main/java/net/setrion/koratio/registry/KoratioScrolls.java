package net.setrion.koratio.registry;

import java.util.ArrayList;
import java.util.List;

import net.setrion.koratio.scroll.Scroll;

public class KoratioScrolls {
	
	public static List<Scroll> SCROLLS = new ArrayList<Scroll>();
	
	public static final Scroll A_NEW_WORLD = new Scroll("a_new_world", Scroll.ScrollType.STORY);
	public static final Scroll DEMONS = new Scroll("demons", Scroll.ScrollType.STORY);
	public static final Scroll BASIC = new Scroll("basic", Scroll.ScrollType.NOTE);
	public static final Scroll EGG = new Scroll("egg", Scroll.ScrollType.EASTER_EGG);

}