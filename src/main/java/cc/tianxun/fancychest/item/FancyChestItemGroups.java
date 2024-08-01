package cc.tianxun.fancychest.item;

import cc.tianxun.fancychest.FancyChest;
import cc.tianxun.fancychest.block.FancyChestBlocks;
import cc.tianxun.fancychest.enchantment.FancyEnchantments;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FancyChestItemGroups {
	public static final ItemGroup FANCYCHEST_ITEMS = Registry.register(Registries.ITEM_GROUP,Identifier.of(FancyChest.MOD_ID,"fancychest_items"),
		ItemGroup.create(null,-1)
			.displayName(Text.translatable("itemGroup.fancychest_items"))
			.icon(() -> new ItemStack(FancyChestBlocks.FANCY_BLOCK))
			.entries((displayContext, entries) -> {
				entries.add(FancyChestItems.FANCY_BLOCK);
				entries.add(FancyChestItems.FANCY_ORE);
				entries.add(FancyChestItems.FANCY_INGOT);
				// 想要把附魔书添加到Group中但失败了不知道为什么
				displayContext.lookup().getOptionalWrapper(RegistryKeys.ENCHANTMENT).ifPresent((registryWrapper) -> {
					registryWrapper.streamEntries().map((enchantmentEntry) -> {
						ItemStack stack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantmentEntry, (enchantmentEntry.value()).getMaxLevel()));
						if (enchantmentEntry.getKey().get().equals(FancyEnchantments.REIMBURSEMENT)) {
							entries.add(stack, ItemGroup.StackVisibility.PARENT_TAB_ONLY);
						}
						return stack;
					});
				});
			}).build()
	);
	public static void registerFancyChestItemGroups() {
		FancyChest.LOGGER.info("registering groups");
	}
}
