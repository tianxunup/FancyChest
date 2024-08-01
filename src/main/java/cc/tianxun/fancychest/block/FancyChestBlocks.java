package cc.tianxun.fancychest.block;

import cc.tianxun.fancychest.FancyChest;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FancyChestBlocks {
	public static final  Block FANCY_BLOCK = register("fancy_block",new Block(AbstractBlock.Settings.create().requiresTool().strength(2.5F)));
	public static final  Block FANCY_ORE = register("fancy_ore",new Block(AbstractBlock.Settings.create().requiresTool().strength(2.5F)));
	public static Block register(String id, Block block) {
		return Registry.register(Registries.BLOCK, Identifier.of(FancyChest.MOD_ID,id), block);
	}
	public static void registerFancyChestBlocks() {
		FancyChest.LOGGER.info("registering Blocks");
	}
}
