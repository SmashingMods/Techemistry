package al132.techemistry.setup;

import al132.techemistry.Ref;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {
    @Override
    public void init() {
        ScreenManager.registerFactory(Ref.fermenterContainer, FermenterScreen::new);
        ScreenManager.registerFactory(Ref.maceratorContainer, MaceratorScreen::new);
        ScreenManager.registerFactory(Ref.gasCollectorContainer, GasCollectorScreen::new);
        ScreenManager.registerFactory(Ref.electrolyzerContainer, ElectrolyzerScreen::new);
        ScreenManager.registerFactory(Ref.distilleryContainer, DistilleryScreen::new);
        ScreenManager.registerFactory(Ref.steamBoilerContainer, SteamBoilerScreen::new);
        ScreenManager.registerFactory(Ref.solidHeaterContainer, SolidHeaterScreen::new);
        ScreenManager.registerFactory(Ref.steamTurbineContainer, SteamTurbineScreen::new);
        ScreenManager.registerFactory(Ref.calcinationContainer, CalcinationScreen::new);
        ScreenManager.registerFactory(Ref.reactionChamberContainer, ReactionChamberScreen::new);
        ScreenManager.registerFactory(Ref.smelteryContainer, SmelteryScreen::new);
        ScreenManager.registerFactory(Ref.frothFlotationContainer, FrothFlotationScreen::new);
        ScreenManager.registerFactory(Ref.solarHeaterContainer, SolarHeaterScreen::new);

    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
