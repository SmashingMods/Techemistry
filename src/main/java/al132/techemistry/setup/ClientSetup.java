package al132.techemistry.setup;

import al132.techemistry.Registration;
import al132.techemistry.blocks.calcination_chamber.CalcinationScreen;
import al132.techemistry.blocks.distillery.DistilleryScreen;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerScreen;
import al132.techemistry.blocks.fermenter.FermenterScreen;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationScreen;
import al132.techemistry.blocks.gas_collector.GasCollectorScreen;
import al132.techemistry.blocks.macerator.MaceratorScreen;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberScreen;
import al132.techemistry.blocks.smeltery.SmelteryScreen;
import al132.techemistry.blocks.solar_heater.SolarHeaterScreen;
import al132.techemistry.blocks.solid_fuel_heater.SolidHeaterScreen;
import al132.techemistry.blocks.steam_boiler.SteamBoilerScreen;
import al132.techemistry.blocks.steam_turbine.SteamTurbineScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


public class ClientSetup {
    public static void init(final FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            MenuScreens.register(Registration.fermenterContainer.get(), FermenterScreen::new);
            MenuScreens.register(Registration.maceratorContainer.get(), MaceratorScreen::new);
            MenuScreens.register(Registration.gasCollectorContainer.get(), GasCollectorScreen::new);
            MenuScreens.register(Registration.electrolyzerContainer.get(), ElectrolyzerScreen::new);
            MenuScreens.register(Registration.distilleryContainer.get(), DistilleryScreen::new);
            MenuScreens.register(Registration.steamBoilerContainer.get(), SteamBoilerScreen::new);
            MenuScreens.register(Registration.solidHeaterContainer.get(), SolidHeaterScreen::new);
            MenuScreens.register(Registration.steamTurbineContainer.get(), SteamTurbineScreen::new);
            MenuScreens.register(Registration.calcinationContainer.get(), CalcinationScreen::new);
            MenuScreens.register(Registration.reactionChamberContainer.get(), ReactionChamberScreen::new);
            MenuScreens.register(Registration.smelteryContainer.get(), SmelteryScreen::new);
            MenuScreens.register(Registration.frothFlotationContainer.get(), FrothFlotationScreen::new);
            MenuScreens.register(Registration.solarHeaterContainer.get(), SolarHeaterScreen::new);
        });
    }
}
