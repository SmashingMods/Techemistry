package com.smashingmods.techemistry;

import com.mojang.logging.LogUtils;
import com.smashingmods.techemistry.datagen.DataGenerators;
import com.smashingmods.techemistry.registration.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Techemistry.MODID)
public class Techemistry {

    public static final String MODID = "techemistry";
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogUtils.getLogger();

    public Techemistry() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registry.register(modEventBus);
        modEventBus.addListener(DataGenerators::gatherData);
        modEventBus.addListener(this::clientSetupEvent);
    }

    public void clientSetupEvent(final FMLClientSetupEvent pEvent) {
        pEvent.enqueueWork(() -> {

        });
    }
}
