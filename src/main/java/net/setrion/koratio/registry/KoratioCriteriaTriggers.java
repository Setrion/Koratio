package net.setrion.koratio.registry;

import net.minecraft.advancements.CriteriaTriggers;
import net.setrion.koratio.advancements.critereon.DecryptScrollTrigger;

public class KoratioCriteriaTriggers {
	
	public static final DecryptScrollTrigger DECRYPTED_SCROLL = CriteriaTriggers.register(new DecryptScrollTrigger());
	
	public static void init() {}
}