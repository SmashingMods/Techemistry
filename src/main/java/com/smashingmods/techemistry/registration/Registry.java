package com.smashingmods.techemistry.registration;

import net.minecraftforge.eventbus.api.IEventBus;

public class Registry {

    public static void register(IEventBus pModEventBus) {
        BlockRegistry.register(pModEventBus);
        ItemRegistry.register(pModEventBus);
        BlockEntityRegistry.register(pModEventBus);
        MenuRegistry.register(pModEventBus);
        RecipeRegistry.register(pModEventBus);
    }
}
