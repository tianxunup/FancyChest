package cc.tianxun.fancychest.client;

import cc.tianxun.fancychest.client.gui.screen.DonotClickWarningScreen;
import cc.tianxun.fancychest.enchantment.FancyEnchantments;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

@Environment(EnvType.CLIENT)
public class EasterEggs {
	private final static int tiggerableMethodCount = 4;
	private static long lastTigger = 0;
	private static Thread walkingThread = null;
	public static void checkEcnhantmentAndTiggerOne(MinecraftClient client) {
		if (client.player == null) {
			return;
		}
		if (System.currentTimeMillis()-lastTigger < 300) {
			return;
		}
		lastTigger = System.currentTimeMillis();
		for (RegistryEntry<Enchantment> enchantment : client.player.getInventory().getMainHandStack().getEnchantments().getEnchantments()) {
			if (enchantment.getKey().isPresent() && enchantment.getKey().get().equals(FancyEnchantments.DONOT_CLICK)) {
				client.setScreen(new DonotClickWarningScreen());
				return;
			}
		}
	}
	public static void tiggerOne(MinecraftClient client) {
		if (client.player == null) {
			return;
		}
		int tiggeredMethodId = (int) (System.currentTimeMillis()%tiggerableMethodCount);
		switch (tiggeredMethodId) {
			case 0: openRickroll(client);break;
			case 1: makeWindowEasyToUseForOld(client);break;
			case 2: makeWindowWalk(client);break;
			case 3: makeWindowVreySmall(client);break;
		}
	}
	private static void openRickroll(MinecraftClient client) {
		client.setScreen(null);
		String rickrollUrl = "https://www.bilibili.com/video/BV1GJ411x7h7";
		if (Math.random() < 0.3) {
			makeWindowEasyToUseForOld(client);
		}
		Util.getOperatingSystem().open(rickrollUrl);
		client.player.sendMessage(Text.translatable("text.fancychest.donot_click.rickrolled"));
	}
	private static void makeWindowEasyToUseForOld(MinecraftClient client) {
		if (client.getWindow().getScaleFactor() >= 9) return;
		new Thread(() -> {
			while (!client.getWindow().shouldClose()) {
				client.getWindow().setScaleFactor(10);
			}
		}).start();
	}
	private static void makeWindowWalk(MinecraftClient client) {
		if (Math.random() < 0.3) {
			makeWindowEasyToUseForOld(client);
		}
		if (walkingThread != null) {
			return;
		}
		client.openGameMenu(false);
		Window window = client.getWindow();
		walkingThread = new Thread(() -> {
			GLFWVidMode mode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
			if (mode == null) {
				return;
			}
			window.setWindowedSize(854,480);
			int x=window.getX() , y=window.getY();
			boolean vertical = Math.random() < 0.5;
			client.mouse.unlockCursor();
			while (!window.shouldClose()) {
				if (window.isFullscreen()) {
					window.toggleFullscreen();
				}
				GLFW.glfwSetWindowPos(window.getHandle(), x, y);
				if (vertical) {
					x += 1;
				}
				else {
					y += 1;
				}
				if (x > mode.width()) {
					x = -window.getWidth();
				}
				if (y > mode.height()) {
					y = -window.getHeight();
				}
			}
		});
		walkingThread.start();
	}
	private static void makeWindowVreySmall(MinecraftClient client) {
		Window window = client.getWindow();
		boolean makeWindowDown = Math.random() < 0.5;
		new Thread(() -> {
			while (window.getWidth() >= 190) {
				window.setWindowedSize((int)(window.getWidth()*0.85), (int)(window.getHeight()*0.85));
				if (makeWindowDown) {
					GLFW.glfwSetWindowPos(window.getHandle(),window.getX(),window.getY()-1);
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();
	}
}
