package cc.tianxun.fancychest.client;

import cc.tianxun.fancychest.FancyChest;
import cc.tianxun.fancychest.enchantment.FancyEnchantments;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

@Environment(EnvType.CLIENT)
public class EasterEggs {
	private final static int tiggerableMethodCount = 3;
	private static long lastTigger = 0;
	public static void checkEcnhantmentAndTiggerOne(MinecraftClient client) {
		if (client.player == null) {
			return;
		}
		for (RegistryEntry<Enchantment> enchantment : client.player.getInventory().getMainHandStack().getEnchantments().getEnchantments()) {
			if (enchantment.getKey().isPresent() && enchantment.getKey().get().equals(FancyEnchantments.DONOT_CLICK)) {
				tiggerOne(client);
				return;
			}
		}
	}
	public static void tiggerOne(MinecraftClient client) {
		if (client.player == null) {
			return;
		}
		if (System.currentTimeMillis()-lastTigger > 100) {
			return;
		}
		lastTigger = System.currentTimeMillis();
		client.player.sendMessage(Text.translatable("debug.fancychest.donot_click.clicked"));
		int tiggeredMethodId = (int) (System.currentTimeMillis()%tiggerableMethodCount);
		switch (tiggeredMethodId) {
			case 0: openRickroll(client);break;
			case 1: client.getWindow().setScaleFactor(10);break;
			case 2: makeWindowWalk(client);break;
		}
	}
	private static void openRickroll(MinecraftClient client) {
		String rickrollUrl = "https://www.bilibili.com/video/BV1GJ411x7h7";
//		Desktop desktop = Desktop. getDesktop();
//		if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
//			try {
//				URI uri = new URI(rickrollUrl);
//				desktop.browse(uri);
//			}
//			catch (Exception e) {
//				FancyChest.LOGGER.error(e.toString());
//			}
//		}
		try {
			Runtime.getRuntime().exec(new String[]{"cmd","/c","start",rickrollUrl});
			client.player.sendMessage(Text.translatable("text.fancychest.donot_click.rickrolled"));
		}
		catch (Exception e) {
			FancyChest.LOGGER.error(e.toString());
		}
	}
	private static void makeWindowWalk(MinecraftClient client) {
		new Thread(() -> {
			GLFWVidMode mode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
			if (mode == null) {
				return;
			}
			client.getWindow().setWindowedSize(854,480);
			int x=0 , y=client.getWindow().getY();
			client.mouse.unlockCursor();
			client.openGameMenu(false);
			while (!client.getWindow().shouldClose()) {
				if (client.getWindow().isFullscreen()) {
					client.getWindow().toggleFullscreen();
				}
				GLFW.glfwSetWindowPos(client.getWindow().getHandle(),x,y);
				x += 1;
				if (x > mode.width()) {
					x = -client.getWindow().getWidth();
					y += 10;
					if (y > mode.height()) {
						y = 0;
					}
				}
			}
		}).start();
	}
}
