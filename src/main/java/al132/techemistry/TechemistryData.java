package al132.techemistry;

import al132.alib.ModData;
import al132.techemistry.blocks.calcination_chamber.CalcinationContainer;
import al132.techemistry.blocks.calcination_chamber.CalcinationTile;
import al132.techemistry.blocks.distillery.DistilleryContainer;
import al132.techemistry.blocks.distillery.DistilleryTile;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerContainer;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerTile;
import al132.techemistry.blocks.fermenter.FermenterContainer;
import al132.techemistry.blocks.fermenter.FermenterTile;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationContainer;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationTile;
import al132.techemistry.blocks.gas_collector.GasCollectorContainer;
import al132.techemistry.blocks.gas_collector.GasCollectorTile;
import al132.techemistry.blocks.macerator.MaceratorContainer;
import al132.techemistry.blocks.macerator.MaceratorTile;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberContainer;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberTile;
import al132.techemistry.blocks.smeltery.SmelteryContainer;
import al132.techemistry.blocks.smeltery.SmelteryTile;
import al132.techemistry.blocks.solar_heater.SolarHeaterContainer;
import al132.techemistry.blocks.solar_heater.SolarHeaterTile;
import al132.techemistry.blocks.solid_fuel_heater.SolidHeaterContainer;
import al132.techemistry.blocks.solid_fuel_heater.SolidHeaterTile;
import al132.techemistry.blocks.steam_boiler.SteamBoilerContainer;
import al132.techemistry.blocks.steam_boiler.SteamBoilerTile;
import al132.techemistry.blocks.steam_turbine.SteamTurbineContainer;
import al132.techemistry.blocks.steam_turbine.SteamTurbineTile;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;

public class TechemistryData extends ModData {

    public TechemistryData() {
        super(Techemistry.MODID, new ItemStack(Ref.reactionChamber));
    }

    @Override
    public void registerTiles(RegistryEvent.Register<TileEntityType<?>> e) {
        Ref.fermenterTile = Techemistry.data.registerTile(FermenterTile::new, Ref.fermenter, "fermenter");
        Ref.maceratorTile = Techemistry.data.registerTile(MaceratorTile::new, Ref.macerator, "macerator");
        Ref.gasCollectorTile = Techemistry.data.registerTile(GasCollectorTile::new, Ref.gasCollector, "gas_collector");
        Ref.electrolyzerTile = Techemistry.data.registerTile(ElectrolyzerTile::new, Ref.electrolyzer, "electrolyzer");
        Ref.distilleryTile = Techemistry.data.registerTile(DistilleryTile::new, Ref.distilleryController, "distillery_controller");
        Ref.solidHeaterTile = Techemistry.data.registerTile(SolidHeaterTile::new, Ref.solidFuelHeater, "solid_fuel_heater");
        Ref.steamBoilerTile = Techemistry.data.registerTile(SteamBoilerTile::new, Ref.steamBoiler, "steam_boiler");
        Ref.steamTurbineTile = Techemistry.data.registerTile(SteamTurbineTile::new, Ref.steamTurbine, "steam_turbine");
        Ref.calcinationTile = Techemistry.data.registerTile(CalcinationTile::new, Ref.calcinationChamber, "calcination_chamber");
        Ref.reactionChamberTile = Techemistry.data.registerTile(ReactionChamberTile::new, Ref.reactionChamber, "reaction_chamber");
        Ref.smelteryTile = Techemistry.data.registerTile(SmelteryTile::new, Ref.smeltery, "smeltery");
        Ref.frothFlotationTile = Techemistry.data.registerTile(FrothFlotationTile::new, Ref.frothFlotationChamber, "froth_flotation_chamber");
        Ref.solarHeaterTile = Techemistry.data.registerTile(SolarHeaterTile::new, Ref.solarHeater, "solar_heater");

        Techemistry.data.TILES.forEach(e.getRegistry()::register);
    }

    @Override
    public void registerContainers(RegistryEvent.Register<ContainerType<?>> e) {
        Ref.fermenterContainer = IForgeContainerType.create((windowID, inv, data) ->
                new FermenterContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.fermenterContainer, "fermenter");

        Ref.maceratorContainer = IForgeContainerType.create((windowID, inv, data) ->
                new MaceratorContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.maceratorContainer, "macerator");

        Ref.gasCollectorContainer = IForgeContainerType.create((windowID, inv, data) ->
                new GasCollectorContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.gasCollectorContainer, "gas_collector");

        Ref.electrolyzerContainer = IForgeContainerType.create((windowID, inv, data) ->
                new ElectrolyzerContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.electrolyzerContainer, "electrolyzer");

        Ref.distilleryContainer = IForgeContainerType.create((windowID, inv, data) ->
                new DistilleryContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.distilleryContainer, "distillery");

        Ref.solidHeaterContainer = IForgeContainerType.create((windowID, inv, data) ->
                new SolidHeaterContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.solidHeaterContainer, "solid_fuel_heater");

        Ref.steamBoilerContainer = IForgeContainerType.create((windowID, inv, data) ->
                new SteamBoilerContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.steamBoilerContainer, "steam_boiler");

        Ref.steamTurbineContainer = IForgeContainerType.create((windowID, inv, data) ->
                new SteamTurbineContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.steamTurbineContainer, "steam_turbine");

        Ref.calcinationContainer = IForgeContainerType.create((windowID, inv, data) ->
                new CalcinationContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.calcinationContainer, "calcination_chamber");

        Ref.reactionChamberContainer = IForgeContainerType.create((windowID, inv, data) ->
                new ReactionChamberContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.reactionChamberContainer, "reaction_chamber");

        Ref.smelteryContainer = IForgeContainerType.create((windowID, inv, data) ->
                new SmelteryContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.smelteryContainer, "smeltery");

        Ref.frothFlotationContainer = IForgeContainerType.create((windowID, inv, data) ->
                new FrothFlotationContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.frothFlotationContainer, "froth_flotation_chamber");

        Ref.solarHeaterContainer = IForgeContainerType.create((windowID, inv, data) ->
                new SolarHeaterContainer(windowID, Techemistry.proxy.getClientWorld(), data.readBlockPos(), inv, Techemistry.proxy.getClientPlayer()));
        registerContainer(Ref.solarHeaterContainer, "solar_heater");

        Techemistry.data.CONTAINERS.forEach(e.getRegistry()::register);
    }
}