package cc.tianxun.fancychest.enchantment;

import cc.tianxun.fancychest.FancyChest;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class FancyEnchantments {
	public static Set<RegistryKey<Enchantment>> enchantmentSet = new HashSet<>();
	public static RegistryKey<Enchantment> REIMBURSEMENT = register("reimbursement");
	private static RegistryKey<Enchantment> register(String name) {
		return RegistryKey.of(RegistryKeys.ENCHANTMENT,Identifier.of(FancyChest.MOD_ID,name));
	}

	public static void registerEnchments() {
		enchantmentSet.add(REIMBURSEMENT);
		FancyChest.LOGGER.info("Registering Enchantments");
	}
}
