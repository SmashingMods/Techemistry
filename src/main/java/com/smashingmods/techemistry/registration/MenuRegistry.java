package com.smashingmods.techemistry.registration;

import com.smashingmods.alchemylib.api.blockentity.container.AbstractProcessingMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.smashingmods.techemistry.Techemistry.MODID;

public class MenuRegistry {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

    private static <M extends AbstractProcessingMenu> RegistryObject<MenuType<M>> registerMenuType(IContainerFactory<M> pFactory, String pName) {
        return MENU_TYPES.register(pName, () -> IForgeMenuType.create(pFactory));
    }

    public static void register(IEventBus pEventBus) {
        MENU_TYPES.register(pEventBus);
    }
}
