package cc.tianxun.fancychest.client;

import cc.tianxun.fancychest.FancyChest;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class FancyChestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FancyChest.LOGGER.info("Initializing Client");
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.mouse.wasLeftButtonClicked() || client.mouse.wasRightButtonClicked()) {
				EasterEggs.checkEcnhantmentAndTiggerOne(client);
			}
		});
	}
}
