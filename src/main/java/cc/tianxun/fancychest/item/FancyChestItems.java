package cc.tianxun.fancychest.item;

import cc.tianxun.fancychest.FancyChest;
import cc.tianxun.fancychest.block.FancyChestBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FancyChestItems {

	public static final Item FANCY_BLOCK = register(FancyChestBlocks.FANCY_BLOCK);
	public static final Item FANCY_ORE = register(FancyChestBlocks.FANCY_ORE);
	public static final Item FANCY_INGOT = register("fancy_ingot",new Item(new Item.Settings()));
	public static Item register(String id, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(FancyChest.MOD_ID, id), item);
	}
	public static Item register(Block block) {
		Item item = Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
		if (item instanceof BlockItem) {
			((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
		}
		return item;
	}
	public static void registerFancyChestItems(){
		FancyChest.LOGGER.info("Registering Items");
	}
}
