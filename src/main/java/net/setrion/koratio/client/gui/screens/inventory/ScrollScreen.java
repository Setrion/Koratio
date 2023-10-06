package net.setrion.koratio.client.gui.screens.inventory;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.scroll.Scroll;

public class ScrollScreen extends Screen {
	
	public static final ResourceLocation SCROLL_LOCATION = new ResourceLocation(Koratio.MOD_ID, "textures/gui/scroll.png");
	public Scroll scroll;

	public ScrollScreen(Scroll scroll) {
		super(GameNarrator.NO_TITLE);
		this.scroll = scroll;
	}
	
	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(graphics);
		int i = (this.width - 192) / 2;
		int j = (this.height - 256) / 2;
		graphics.blit(SCROLL_LOCATION, i, j, 0, 0, 192, 256);
		this.renderText(graphics, i+5, j+8, partialTicks);
		super.render(graphics, mouseX, mouseY, partialTicks);
	}
	
	public void renderText(GuiGraphics graphics, int x, int y, float partialTicks) {
		MutableInt mutableint = new MutableInt();
		MutableBoolean mutableboolean = new MutableBoolean();
		String text = Component.translatable("scroll."+scroll.getName()+".text").getString();
		this.font.getSplitter().splitLines(text, 180, Style.EMPTY, false, (style, p_239847_, p_239848_) -> {
			int k3 = mutableint.getAndIncrement();
			String s2 = text.substring(p_239847_, p_239848_);
			mutableboolean.setValue(s2.endsWith("\n"));
			String s3 = StringUtils.stripEnd(s2, " \n");
			int l3 = k3 * 9;
			graphics.drawString(font, s3, x, y+l3, 8998438, false);
		});
	}
}