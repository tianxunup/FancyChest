package cc.tianxun.fancychest.client.gui.screen;

import cc.tianxun.fancychest.client.EasterEggs;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

@Environment(value= EnvType.CLIENT)
public class DonotClickWarningScreen extends ConfirmScreen {
	public DonotClickWarningScreen() {
		super(new Consumer(),Text.translatable("donot_click.screen.title"), Text.translatable("donot_click.screen.message"));
	}
	@Override
	protected void addButtons(int y) {
		this.addButton(ButtonWidget.builder(ScreenTexts.OK, button -> this.callback.accept(true)).dimensions(this.width / 2 - 235, y, 150, 20).build());
		this.addButton(ButtonWidget.builder(ScreenTexts.OK, button -> this.callback.accept(true)).dimensions(this.width / 2 - 235 + 160, y, 150, 20).build());
		this.addButton(ButtonWidget.builder(ScreenTexts.OK, button -> this.callback.accept(true)).dimensions(this.width / 2 - 235 + 160 + 160, y, 150, 20).build());
	}
}

@Environment(value=EnvType.CLIENT)
class Consumer implements BooleanConsumer {
	@Override
	public void accept(boolean t) {
		EasterEggs.lastTigger = System.currentTimeMillis();
		MinecraftClient.getInstance().setScreen(null);
		EasterEggs.tiggerOne(MinecraftClient.getInstance());
		MinecraftClient.getInstance().setScreen(null);
	}
}