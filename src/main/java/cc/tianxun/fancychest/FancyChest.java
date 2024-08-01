package cc.tianxun.fancychest;

import cc.tianxun.fancychest.block.FancyChestBlocks;
import cc.tianxun.fancychest.enchantment.FancyEnchantments;
import cc.tianxun.fancychest.item.FancyChestItemGroups;
import cc.tianxun.fancychest.item.FancyChestItems;
import cc.tianxun.fancychest.listener.ReimbursementListeners;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FancyChest implements ModInitializer {
	public static final String MOD_ID = "fancychest";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		FancyChestBlocks.registerFancyChestBlocks();
		FancyChestItems.registerFancyChestItems();
		FancyEnchantments.registerEnchments();
		FancyChestItemGroups.registerFancyChestItemGroups();
		ReimbursementListeners.registerListeners();
		LOGGER.info("Mod suffceely loaded!");
	}
}
