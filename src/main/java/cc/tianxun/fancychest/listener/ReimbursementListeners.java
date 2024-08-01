package cc.tianxun.fancychest.listener;

import cc.tianxun.fancychest.FancyChest;
import cc.tianxun.fancychest.enchantment.FancyEnchantments;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReimbursementListeners {
	private static final Map<Item, Item> escalationResult = new HashMap<>();
	private static final Map<String,List<ItemStack>> reswpanItems = new HashMap<>();
	private static final Map<String,List<ItemStack>> reswpanArmors = new HashMap<>();
	private static final Map<String,ItemStack> reswpanOffhand = new HashMap<>();

	static {
		escalationResult.put(Items.WOODEN_AXE,Items.STONE_AXE);
		escalationResult.put(Items.WOODEN_PICKAXE,Items.STONE_PICKAXE);
		escalationResult.put(Items.WOODEN_HOE,Items.STONE_HOE);
		escalationResult.put(Items.WOODEN_SHOVEL,Items.STONE_SHOVEL);
		escalationResult.put(Items.WOODEN_SWORD,Items.STONE_SWORD);
		escalationResult.put(Items.STONE_AXE,Items.IRON_AXE);
		escalationResult.put(Items.STONE_PICKAXE,Items.IRON_PICKAXE);
		escalationResult.put(Items.STONE_HOE,Items.IRON_HOE);
		escalationResult.put(Items.STONE_SHOVEL,Items.IRON_SHOVEL);
		escalationResult.put(Items.STONE_SWORD,Items.IRON_SWORD);
		escalationResult.put(Items.IRON_AXE,Items.DIAMOND_AXE);
		escalationResult.put(Items.IRON_PICKAXE,Items.DIAMOND_PICKAXE);
		escalationResult.put(Items.IRON_HOE,Items.DIAMOND_HOE);
		escalationResult.put(Items.IRON_SHOVEL,Items.DIAMOND_SHOVEL);
		escalationResult.put(Items.IRON_SWORD,Items.DIAMOND_SWORD);
		escalationResult.put(Items.IRON_BOOTS,Items.DIAMOND_BOOTS);
		escalationResult.put(Items.IRON_CHESTPLATE,Items.DIAMOND_CHESTPLATE);
		escalationResult.put(Items.IRON_HELMET,Items.DIAMOND_HELMET);
		escalationResult.put(Items.IRON_LEGGINGS,Items.DIAMOND_LEGGINGS);
		escalationResult.put(Items.DIAMOND_AXE,Items.NETHERITE_AXE);
		escalationResult.put(Items.DIAMOND_PICKAXE,Items.NETHERITE_PICKAXE);
		escalationResult.put(Items.DIAMOND_HOE,Items.NETHERITE_HOE);
		escalationResult.put(Items.DIAMOND_SHOVEL,Items.NETHERITE_SHOVEL);
		escalationResult.put(Items.DIAMOND_SWORD,Items.NETHERITE_SWORD);
		escalationResult.put(Items.DIAMOND_BOOTS,Items.NETHERITE_BOOTS);
		escalationResult.put(Items.DIAMOND_CHESTPLATE,Items.NETHERITE_CHESTPLATE);
		escalationResult.put(Items.DIAMOND_HELMET,Items.NETHERITE_HELMET);
		escalationResult.put(Items.DIAMOND_LEGGINGS,Items.NETHERITE_LEGGINGS);
	}
	public static void registerListeners() {
		FancyChest.LOGGER.info("Registering Listeners");
		ServerLivingEntityEvents.ALLOW_DEATH.register(ReimbursementListeners::onEnityDealth);
		ServerPlayerEvents.AFTER_RESPAWN.register(ReimbursementListeners::onPlayerRespwan);
	}
	public static boolean onEnityDealth(LivingEntity enity, DamageSource source, float v) {
		if (!(enity instanceof PlayerEntity player)) {
			return true;
		}
		reswpanItems.put(player.getUuidAsString(),new ArrayList<>());
		reswpanArmors.put(player.getUuidAsString(),new ArrayList<>());
		List<ItemStack> removeStacks = new ArrayList<>();
		// 这里代码写得跟shi一样，以后再优化吧：）
		for (ItemStack stack : player.getInventory().main) {
			for(RegistryEntry<Enchantment> entry : stack.getEnchantments().getEnchantments()) {
				if (entry.getKey().get().equals(FancyEnchantments.REIMBURSEMENT)) {
					ItemStack newStack = stack.copy();
					newStack.setDamage(0);
					int level = stack.getEnchantments().getLevel(entry);
					if (escalationResult.containsKey(stack.getItem())) {
						if ((level == 2 && Math.random() < 0.05) || (level == 3 && Math.random() < 0.20)) {
							newStack = newStack.copyComponentsToNewStack(escalationResult.get(stack.getItem()),stack.getCount());
						}
					}
					removeStacks.add(stack);
					reswpanItems.get(player.getUuidAsString()).add(newStack);
					break;
				}
			}
		}
		for (ItemStack stack : player.getInventory().armor) {
			boolean flag = false;
			for(RegistryEntry<Enchantment> entry : stack.getEnchantments().getEnchantments()) {
				if (entry.getKey().get().equals(FancyEnchantments.REIMBURSEMENT)) {
					ItemStack newStack = stack.copy();
					newStack.setDamage(0);
					int level = stack.getEnchantments().getLevel(entry);
					if (escalationResult.containsKey(stack.getItem())) {
						if ((level == 2 && Math.random() < 0.05) || (level == 3 && Math.random() < 0.20)) {
							newStack = newStack.copyComponentsToNewStack(escalationResult.get(stack.getItem()),stack.getCount());
						}
					}
					removeStacks.add(stack);
					reswpanArmors.get(player.getUuidAsString()).add(newStack);
					flag = true;
					break;
				}
			}
			if (!flag) {
				reswpanArmors.get(player.getUuidAsString()).add(ItemStack.EMPTY);
			}
		}
		for(RegistryEntry<Enchantment> entry : player.getOffHandStack().getEnchantments().getEnchantments()) {
			if (entry.getKey().get().equals(FancyEnchantments.REIMBURSEMENT)) {
				ItemStack newStack = player.getOffHandStack().copy();
				newStack.setDamage(0);
				int level =player.getOffHandStack().getEnchantments().getLevel(entry);
				if (escalationResult.containsKey(player.getOffHandStack().getItem())) {
					if ((level == 2 && Math.random() < 0.05) || (level == 3 && Math.random() < 0.20)) {
						newStack = newStack.copyComponentsToNewStack(escalationResult.get(player.getOffHandStack().getItem()),player.getOffHandStack().getCount());
					}
				}
				removeStacks.add(player.getOffHandStack());
				reswpanOffhand.put(player.getUuidAsString(),newStack);
				break;
			}
		}
		for (ItemStack stack : removeStacks) {
			player.getInventory().removeOne(stack);
		}
		return true;
	}
	public static void onPlayerRespwan(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
		for (ItemStack stack : reswpanItems.get(oldPlayer.getUuidAsString())) {
			newPlayer.giveItemStack(stack);
		}
		int i=0;
		for (ItemStack stack : reswpanArmors.get(oldPlayer.getUuidAsString())) {
			newPlayer.getInventory().armor.set(i,stack);
			i++;
		}
		newPlayer.getInventory().offHand.set(0,reswpanOffhand.get(oldPlayer.getUuidAsString()));
		reswpanItems.remove(oldPlayer.getUuidAsString());
		reswpanArmors.remove(oldPlayer.getUuidAsString());
		reswpanOffhand.remove(oldPlayer.getUuidAsString());
	}
}
