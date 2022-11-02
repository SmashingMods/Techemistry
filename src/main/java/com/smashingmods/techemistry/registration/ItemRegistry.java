package com.smashingmods.techemistry.registration;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.smashingmods.techemistry.Techemistry.MODID;

public class ItemRegistry {

    public static final CreativeModeTab MACHINE_TAB = new CreativeModeTab("techemistry") {
        @Override
        public ItemStack makeIcon() {
            return ItemStack.EMPTY;
        }
    };

    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(MACHINE_TAB);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static <B extends Block> void fromBlock(RegistryObject<B> pBlock) {
        ITEMS.register(pBlock.getId().getPath(), () -> new BlockItem(pBlock.get(), ITEM_PROPERTIES));
    }

    public static void register(IEventBus pEventBus) {
        BlockRegistry.BLOCKS.getEntries().forEach(ItemRegistry::fromBlock);
        ITEMS.register(pEventBus);
    }
}
