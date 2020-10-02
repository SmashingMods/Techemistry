package al132.techemistry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {

        DataGenerator gen = e.getGenerator();

        if (e.includeServer()) {
            gen.addProvider(new Recipes(gen));
            gen.addProvider(new Tags(gen));
        }
    }
}

