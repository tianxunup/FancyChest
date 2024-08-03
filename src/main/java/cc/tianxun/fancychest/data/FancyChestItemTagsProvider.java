package cc.tianxun.fancychest.data;

import cc.tianxun.fancychest.FancyChest;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class FancyChestItemTagsProvider extends FabricTagProvider.ItemTagProvider {
	public FancyChestItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		FabricTagBuilder builder = getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of(FancyChest.MOD_ID,"all_item")));
		for (Item item : Registries.ITEM) {
			builder.add(item);
		}
	}
}
