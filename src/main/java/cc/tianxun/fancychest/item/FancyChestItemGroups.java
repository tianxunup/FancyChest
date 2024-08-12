package cc.tianxun.fancychest.item;

import cc.tianxun.fancychest.FancyChest;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FancyChestItemGroups {
	public static final ItemGroup FANCYCHEST_ITEMS = Registry.register(Registries.ITEM_GROUP,Identifier.of(FancyChest.MOD_ID,"fancychest_items"),
		ItemGroup.create(null,-1)
			.displayName(Text.translatable("itemGroup.fancychest_items"))
			.icon(() -> new ItemStack(FancyChestItems.FANCY_INGOT))
			.entries((displayContext, entries) -> {
				entries.add(FancyChestItems.FANCY_BLOCK);
				entries.add(FancyChestItems.FANCY_ORE);
				entries.add(FancyChestItems.FANCY_INGOT);
				// 想要把附魔书添加到Group中但失败了不知道为什么
//				displayContext.lookup().getOptionalWrapper(RegistryKeys.ENCHANTMENT).ifPresent(registryWrapper -> {
//					registryWrapper.streamEntries().map(enchantmentEntry -> EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantmentEntry, enchantmentEntry.value().getMaxLevel())))
//						.forEach(stack -> {
//							for (RegistryEntry<Enchantment> entry : stack.getEnchantments().getEnchantments()) {
//								FancyChest.LOGGER.info(entry.getIdAsString());
//								if (entry.getKey().isPresent() && FancyEnchantments.enchantmentSet.contains(entry.getKey().get())) {
//									entries.add(stack, ItemGroup.StackVisibility.PARENT_TAB_ONLY);
//								}
//							}
//						});
//				});
			}).build()
	);
	public static void registerFancyChestItemGroups() {
		FancyChest.LOGGER.info("registering groups");
	}
}
