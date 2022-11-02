package com.smashingmods.techemistry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataGenerators {

    public static void gatherData(final GatherDataEvent pEvent) {
        DataGenerator generator = pEvent.getGenerator();
    }
}
