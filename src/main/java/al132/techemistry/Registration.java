package al132.techemistry;

import al132.chemlib.Utils;
import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.blocks.calcination_chamber.*;
import al132.techemistry.blocks.distillery.*;
import al132.techemistry.blocks.electrolyzer.*;
import al132.techemistry.blocks.fermenter.*;
import al132.techemistry.blocks.froth_flotation_chamber.*;
import al132.techemistry.blocks.gas_collector.GasCollectorBlock;
import al132.techemistry.blocks.gas_collector.GasCollectorContainer;
import al132.techemistry.blocks.gas_collector.GasCollectorTile;
import al132.techemistry.blocks.macerator.*;
import al132.techemistry.blocks.reaction_chamber.*;
import al132.techemistry.blocks.smeltery.*;
import al132.techemistry.blocks.solar_heater.SolarHeaterBlock;
import al132.techemistry.blocks.solar_heater.SolarHeaterContainer;
import al132.techemistry.blocks.solar_heater.SolarHeaterTile;
import al132.techemistry.blocks.solid_fuel_heater.SolidHeaterBlock;
import al132.techemistry.blocks.solid_fuel_heater.SolidHeaterContainer;
import al132.techemistry.blocks.solid_fuel_heater.SolidHeaterTile;
import al132.techemistry.blocks.steam_boiler.SteamBoilerBlock;
import al132.techemistry.blocks.steam_boiler.SteamBoilerContainer;
import al132.techemistry.blocks.steam_boiler.SteamBoilerTile;
import al132.techemistry.blocks.steam_turbine.SteamTurbineBlock;
import al132.techemistry.blocks.steam_turbine.SteamTurbineContainer;
import al132.techemistry.blocks.steam_turbine.SteamTurbineTile;
import al132.techemistry.blocks.world.*;
import al132.techemistry.items.BaseItem;
import al132.techemistry.items.minerals.Mineral;
import al132.techemistry.items.minerals.MineralItem;
import al132.techemistry.items.misc.*;
import al132.techemistry.items.parts.ElectrodeItem;
import al132.techemistry.items.parts.GearItem;
import al132.techemistry.items.parts.PartMaterial;
import al132.techemistry.items.parts.PartMaterialRegistry;
import al132.techemistry.setup.ModSetup;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;

import static al132.techemistry.Techemistry.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);
        RECIPE_SERIALIZERS.register(bus);

    }

    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    public static String s(int input) {
        return Utils.getSubscript(input);
    }


    //*****Blocks*****
    public static final RegistryObject<Block> FERMENTER_BLOCK = BLOCKS.register("fermenter", FermenterBlock::new);
    public static final RegistryObject<Block> MACERATOR_BLOCK = BLOCKS.register("macerator", MaceratorBlock::new);
    public static final RegistryObject<Block> GAS_COLLECTOR_BLOCK = BLOCKS.register("gas_collector", GasCollectorBlock::new);
    public static final RegistryObject<Block> ELECTROLYZER_BLOCK = BLOCKS.register("electrolyzer", ElectrolyzerBlock::new);
    public static final RegistryObject<Block> CALCINATION_CHAMBER_BLOCK = BLOCKS.register("calcination_chamber", CalcinationBlock::new);
    public static final RegistryObject<Block> REACTION_CHAMBER_BLOCK = BLOCKS.register("reaction_chamber", ReactionChamberBlock::new);
    public static final RegistryObject<Block> SMELTERY_BLOCK = BLOCKS.register("smeltery", SmelteryBlock::new);
    public static final RegistryObject<Block> FROTH_FLOTATION_BLOCK = BLOCKS.register("froth_flotation_chamber", FrothFlotationBlock::new);
    public static final RegistryObject<Block> SOLAR_HEATER_BLOCK = BLOCKS.register("solar_heater", SolarHeaterBlock::new);

    public static final RegistryObject<Block> DOLOMITE_BLOCK = BLOCKS.register("dolomite", DolomiteBlock::new);
    public static final RegistryObject<Block> SULFIDE_ORE_BLOCK = BLOCKS.register("sulfide_ore", SulfideOre::new);
    public static final RegistryObject<Block> OXIDE_ORE_BLOCK = BLOCKS.register("oxide_ore", OxideOre::new);
    public static final RegistryObject<Block> CARBONATE_ORE_BLOCK = BLOCKS.register("carbonate_ore", CarbonateOre::new);
    public static final RegistryObject<Block> PHOSPHATE_ORE_BLOCK = BLOCKS.register("phosphate_ore", PhosphateOre::new);
    public static final RegistryObject<Block> SULFUR_ORE_BLOCK = BLOCKS.register("sulfur_ore", SulfurOre::new);
    public static final RegistryObject<Block> SULFATE_ORE_BLOCK = BLOCKS.register("sulfate_ore", SulfateOre::new);

    public static final RegistryObject<Block> DISTILLERY_CONTROLLER_BLOCK = BLOCKS.register("distillery_controller", DistilleryControllerBlock::new);
    public static final RegistryObject<Block> DISTILLERY_COLUMN_BLOCK = BLOCKS.register("distillery_column", DistilleryColumnBlock::new);
    public static final RegistryObject<Block> SOLID_HEATER_BLOCK = BLOCKS.register("solid_fuel_heater", SolidHeaterBlock::new);
    public static final RegistryObject<Block> STEAM_BOILER_BLOCK = BLOCKS.register("steam_boiler", SteamBoilerBlock::new);
    public static final RegistryObject<Block> STEAM_TURBINE_BLOCK = BLOCKS.register("steam_turbine", SteamTurbineBlock::new);

    //*****Items*****
    public static final RegistryObject<Item> FERMENTER_ITEM = fromBlock(FERMENTER_BLOCK);
    public static final RegistryObject<Item> MACERATOR_ITEM = fromBlock(MACERATOR_BLOCK);
    public static final RegistryObject<Item> GAS_COLLECTOR_ITEM = fromBlock(GAS_COLLECTOR_BLOCK);
    public static final RegistryObject<Item> ELECTROLYZER_ITEM = fromBlock(ELECTROLYZER_BLOCK);
    public static final RegistryObject<Item> CALCINATION_CHAMBER_ITEM = fromBlock(CALCINATION_CHAMBER_BLOCK);
    public static final RegistryObject<Item> REACTION_CHAMBER_ITEM = fromBlock(REACTION_CHAMBER_BLOCK);
    public static final RegistryObject<Item> SMELTERY_ITEM = fromBlock(SMELTERY_BLOCK);
    public static final RegistryObject<Item> FROTH_FLOTATION_ITEM = fromBlock(FROTH_FLOTATION_BLOCK);
    public static final RegistryObject<Item> SOLAR_HEATER_ITEM = fromBlock(SOLAR_HEATER_BLOCK);
    public static final RegistryObject<Item> DOLOMITE_ITEM = fromBlock(DOLOMITE_BLOCK);
    public static final RegistryObject<Item> SULFIDE_ORE_ITEM = fromBlock(SULFIDE_ORE_BLOCK);
    public static final RegistryObject<Item> OXIDE_ORE_ITEM = fromBlock(OXIDE_ORE_BLOCK);
    public static final RegistryObject<Item> CARBONATE_ORE_ITEM = fromBlock(CARBONATE_ORE_BLOCK);
    public static final RegistryObject<Item> PHOSPHATE_ORE_ITEM = fromBlock(PHOSPHATE_ORE_BLOCK);
    public static final RegistryObject<Item> SULFUR_ORE_ITEM = fromBlock(SULFUR_ORE_BLOCK);
    public static final RegistryObject<Item> SULFATE_ORE_ITEM = fromBlock(SULFATE_ORE_BLOCK);

    public static final RegistryObject<Item> DISTILLERY_CONTROLLER_ITEM = fromBlock(DISTILLERY_CONTROLLER_BLOCK);
    public static final RegistryObject<Item> DISTILLERY_COLUMN_ITEM = fromBlock(DISTILLERY_COLUMN_BLOCK);
    public static final RegistryObject<Item> SOLID_HEATER_ITEM = fromBlock(SOLID_HEATER_BLOCK);
    public static final RegistryObject<Item> STEAM_BOILER_ITEM = fromBlock(STEAM_BOILER_BLOCK);
    public static final RegistryObject<Item> STEAM_TURBINE_ITEM = fromBlock(STEAM_TURBINE_BLOCK);


    public static final RegistryObject<Item> YEAST_ITEM = ITEMS.register("yeast", YeastItem::new);
    public static final RegistryObject<Item> SUGAR_WINE_ITEM = ITEMS.register("sugar_wine", () -> new DrinkItem("sugar_wine"));
    public static final RegistryObject<Item> CRUSHED_WHEAT_ITEM = ITEMS.register("crushed_wheat", CrushedWheatItem::new);
    public static final RegistryObject<Item> BEER_ITEM = ITEMS.register("beer", () -> new DrinkItem("beer", new FoodProperties.Builder().nutrition(6).saturationMod(0.4f).build()));
    public static final RegistryObject<Item> CIDER_ITEM = ITEMS.register("cider", () -> new DrinkItem("cider", new FoodProperties.Builder().nutrition(6).saturationMod(0.4f).build()));
    public static final RegistryObject<Item> POTATO_WINE_ITEM = ITEMS.register("potato_wine", () -> new DrinkItem("potato_wine"));
    public static final RegistryObject<Item> RUM_ITEM = ITEMS.register("rum", () -> new DrinkItem("rum"));
    public static final RegistryObject<Item> VODKA_ITEM = ITEMS.register("vodka", () -> new DrinkItem("vodka"));
    public static final RegistryObject<Item> WHISKEY_ITEM = ITEMS.register("whiskey", () -> new DrinkItem("whiskey"));
    public static final RegistryObject<Item> APPLE_BRANDY_ITEM = ITEMS.register("apple_brandy", () -> new DrinkItem("apple_brandy"));
    public static final RegistryObject<Item> APPLE_SAUCE_ITEM = ITEMS.register("apple_sauce", AppleSauceItem::new);
    public static final RegistryObject<Item> COKE_ITEM = ITEMS.register("coke", CokeItem::new);
    public static final RegistryObject<Item> YEAST_GROWTH_PLATE_ITEM = ITEMS.register("yeast_incubation_tray", YeastGrowthPlate::new);
    public static final RegistryObject<Item> SULFUR_CHUNK_ITEM = ITEMS.register("sulfur_chunk", () -> new BaseItem("sulfur_chunk"));
    public static final RegistryObject<Item> SAL_AMMONIAC_ITEM = ITEMS.register("sal_ammoniac", () -> new MineralItem("sal_ammoniac", new ChemicalStack("ammonium_chloride")));
    public static final RegistryObject<Item> NATRON_ITEM = ITEMS.register("natron", () -> new MineralItem("natron", new ChemicalStack("sodium_carbonate")));
    public static final RegistryObject<Item> PLATINUM_ELECTRODE_ITEM = ITEMS.register("platinum_electrode", () -> new ElectrodeItem("platinum_electrode"));

    public static final RegistryObject<Item> GEAR_IRON = ITEMS.register("gear_iron", () -> new GearItem(PartMaterialRegistry.PART_IRON));
    public static final RegistryObject<Item> GEAR_DEV = ITEMS.register("gear_dev", () -> new GearItem(PartMaterialRegistry.PART_DEV));
    public static final RegistryObject<Item> GEAR_WOOD = ITEMS.register("gear_wood", () -> new GearItem(PartMaterialRegistry.PART_WOOD));
    public static final RegistryObject<Item> GEAR_STONE = ITEMS.register("gear_stone", () -> new GearItem(PartMaterialRegistry.PART_STONE));
    public static final RegistryObject<Item> GEAR_COPPER = ITEMS.register("gear_copper", () -> new GearItem(PartMaterialRegistry.PART_COPPER));
    public static final RegistryObject<Item> GEAR_NICKEL = ITEMS.register("gear_nickel", () -> new GearItem(PartMaterialRegistry.PART_NICKEL));


    /*
    public static RegistryObject<Item> GALENA_ITEM = ITEMS.register(new Mineral("galena", new ChemicalStack("lead_oxide"));
    public static RegistryObject<Item> PYRITE_ITEM = ITEMS.register(new Mineral("pyrite", new ChemicalStack("iron_disulfide"));
    public static RegistryObject<Item> CHALCOCITE_ITEM = ITEMS.register(new Mineral("chalcocite", new ChemicalStack("copper", 2), new ChemicalStack("sulfur"));
    public static RegistryObject<Item> SPHALERITE_ITEM = ITEMS.register(new Mineral("sphalerite", new ChemicalStack("zinc_sulfide"));
    public static RegistryObject<Item> CINNABAR_ITEM = ITEMS.register(new Mineral("cinnabar", new ChemicalStack("mercury_sulfide"));
    public static RegistryObject<Item> STIBNITE_ITEM = ITEMS.register(new Mineral("stibnite", new ChemicalStack("antimony_trisulfide"));
    public static RegistryObject<Item> MILLERITE_ITEM = ITEMS.register(new Mineral("millerite", new ChemicalStack("nickel_sulfide"));
    public static RegistryObject<Item> BRAGGITE_ITEM = ITEMS.register(new Mineral("braggite", "(Pt, Pd, Ni)S");

    public static RegistryObject<Item> CASSITERITE_ITEM = ITEMS.register(new Mineral("cassiterite", new ChemicalStack("tin_oxide"));
    public static RegistryObject<Item> PYROLUSITE_ITEM = ITEMS.register(new Mineral("pyrolusite", new ChemicalStack("manganese_oxide"));
    public static RegistryObject<Item> STRONTIANITE_ITEM = ITEMS.register(new Mineral("strontianite", new ChemicalStack("strontium_carbonate"));
    public static RegistryObject<Item> VANADINITE_ITEM = ITEMS.register(new Mineral("vanadinite", "Pb", s(5), "(VO", s(4), ")", s(3), "Cl");
    public static RegistryObject<Item> PYROMORPHITE_ITEM = ITEMS.register(new Mineral("pyromorphite", "Pb", s(5), "(PO", s(4), ")", s(3), "Cl");
    public static RegistryObject<Item> HEMATITE_ITEM = ITEMS.register(new Mineral("hematite", new ChemicalStack("iron_oxide"));
    public static RegistryObject<Item> ILMENITE_ITEM = ITEMS.register(new Mineral("ilmenite", "FeTiO" + s(3));
    public static RegistryObject<Item> CUPRITE_ITEM = ITEMS.register("cuprite", () -> new Mineral("cuprite", "Cu" + s(2) + "O"));
    public static RegistryObject<Item> MAGNETITE_ITEM = ITEMS.register(new Mineral("magnetite", "Fe" + s(3) + "O" + s(4));
    public static RegistryObject<Item> URANINITE_ITEM = ITEMS.register(new Mineral("uraninite", "UO" + s(2));
    public static RegistryObject<Item> RHODOCROSITE_ITEM = ITEMS.register(new Mineral("rhodochrosite", new ChemicalStack("manganese_carbonate"));
    public static RegistryObject<Item> CERUSSITE_ITEM = ITEMS.register(new Mineral("cerussite", new ChemicalStack("lead_carbonate"));
    public static RegistryObject<Item> SIDERITE_ITEM = ITEMS.register(new Mineral("siderite", new ChemicalStack("iron_carbonate"));
    public static RegistryObject<Item> SPHEROCOBALTITE_ITEM = ITEMS.register(new Mineral("spherocobaltite", new ChemicalStack("cobalt_carbonate"));
    //SULFATE
    public static RegistryObject<Item> MELANTERITE_ITEM = ITEMS.register(new Mineral("melanterite", "FeSO", s(4), "7H", s(2), "O");
    public static RegistryObject<Item> BARITE_ITEM = ITEMS.register(new Mineral("barite", new ChemicalStack("barium_sulfate"));
    public static RegistryObject<Item> CELESTITE_ITEM = ITEMS.register(new Mineral("celestite", new ChemicalStack("strontium_sulfate"));
    public static RegistryObject<Item> ANGLESITE_ITEM = ITEMS.register(new Mineral("anglesite", new ChemicalStack("lead_sulfate"));
*/
    //*****Containers*****
    public static final RegistryObject<MenuType<FermenterContainer>> fermenterContainer = CONTAINERS.register("fermenter",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new FermenterContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<MaceratorContainer>> maceratorContainer = CONTAINERS.register("macerator",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new MaceratorContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<GasCollectorContainer>> gasCollectorContainer = CONTAINERS.register("gas_collector",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new GasCollectorContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<ElectrolyzerContainer>> electrolyzerContainer = CONTAINERS.register("electrolyzer",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new ElectrolyzerContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<DistilleryContainer>> distilleryContainer = CONTAINERS.register("distillery",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new DistilleryContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<SolidHeaterContainer>> solidHeaterContainer = CONTAINERS.register("solid_heater",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new SolidHeaterContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<SteamBoilerContainer>> steamBoilerContainer = CONTAINERS.register("steam_boiler",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new SteamBoilerContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<SteamTurbineContainer>> steamTurbineContainer = CONTAINERS.register("steam_turbine",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new SteamTurbineContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<CalcinationContainer>> calcinationContainer = CONTAINERS.register("calcination_chamber",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new CalcinationContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<ReactionChamberContainer>> reactionChamberContainer = CONTAINERS.register("reaction_chamber",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new ReactionChamberContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<SmelteryContainer>> smelteryContainer = CONTAINERS.register("smeltery",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new SmelteryContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<FrothFlotationContainer>> frothFlotationContainer = CONTAINERS.register("froth_flotation",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new FrothFlotationContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));
    public static final RegistryObject<MenuType<SolarHeaterContainer>> solarHeaterContainer = CONTAINERS.register("solar_heater",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new SolarHeaterContainer(windowId, Techemistry.proxy.getClientLevel(), data.readBlockPos(), inv)));

    //*****Block Entities*****
    public static final RegistryObject<BlockEntityType<DistilleryTile>> DISTILLERY_BE
            = BLOCK_ENTITIES.register("distillery",
            () -> BlockEntityType.Builder.of(DistilleryTile::new, DISTILLERY_CONTROLLER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<CalcinationTile>> CALCINATION_BE
            = BLOCK_ENTITIES.register("calcination_chamber",
            () -> BlockEntityType.Builder.of(CalcinationTile::new, CALCINATION_CHAMBER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<ElectrolyzerTile>> ELECTROLYZER_BE
            = BLOCK_ENTITIES.register("electrolyzer",
            () -> BlockEntityType.Builder.of(ElectrolyzerTile::new, ELECTROLYZER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<FermenterTile>> FERMENTER_BE
            = BLOCK_ENTITIES.register("fermenter",
            () -> BlockEntityType.Builder.of(FermenterTile::new, FERMENTER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<FrothFlotationTile>> FROTH_FLOTATION_BE
            = BLOCK_ENTITIES.register("froth_flotation_chamber",
            () -> BlockEntityType.Builder.of(FrothFlotationTile::new, FROTH_FLOTATION_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<MaceratorTile>> MACERATOR_BE
            = BLOCK_ENTITIES.register("macerator",
            () -> BlockEntityType.Builder.of(MaceratorTile::new, MACERATOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<ReactionChamberTile>> REACTION_CHAMBER_BE
            = BLOCK_ENTITIES.register("reaction_chamber",
            () -> BlockEntityType.Builder.of(ReactionChamberTile::new, REACTION_CHAMBER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<SmelteryTile>> SMELTERY_BE
            = BLOCK_ENTITIES.register("smeltery",
            () -> BlockEntityType.Builder.of(SmelteryTile::new, SMELTERY_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<GasCollectorTile>> GAS_COLLECTOR_BE
            = BLOCK_ENTITIES.register("gas_collector",
            () -> BlockEntityType.Builder.of(GasCollectorTile::new, GAS_COLLECTOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<SolarHeaterTile>> SOLAR_HEATER_BE
            = BLOCK_ENTITIES.register("solar_heater",
            () -> BlockEntityType.Builder.of(SolarHeaterTile::new, SOLAR_HEATER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<SolidHeaterTile>> SOLID_HEATER_BE
            = BLOCK_ENTITIES.register("solid_fuel_heater",
            () -> BlockEntityType.Builder.of(SolidHeaterTile::new, SOLID_HEATER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<SteamBoilerTile>> STEAM_BOILER_BE
            = BLOCK_ENTITIES.register("steam_boiler",
            () -> BlockEntityType.Builder.of(SteamBoilerTile::new, STEAM_BOILER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<SteamTurbineTile>> STEAM_TURBINE_BE
            = BLOCK_ENTITIES.register("steam_turbine",
            () -> BlockEntityType.Builder.of(SteamTurbineTile::new, STEAM_TURBINE_BLOCK.get()).build(null));

    //*****Recipe Serializers*****
    public static RegistryObject<DistilleryRecipeSerializer<DistilleryRecipe>> DISTILLERY_SERIALIZER
            = RECIPE_SERIALIZERS.register("distillery", () -> new DistilleryRecipeSerializer(DistilleryRecipe::new, 350));
    public static RegistryObject<CalcinationRecipeSerializer<CalcinationRecipe>> CALCINATION_SERIALIZER
            = RECIPE_SERIALIZERS.register("calcination_chamber", () -> new CalcinationRecipeSerializer(CalcinationRecipe::new, 350));
    public static RegistryObject<ElectrolyzerRecipeSerializer<ElectrolyzerRecipe>> ELECTROLYZER_SERIALIZER
            = RECIPE_SERIALIZERS.register("electrolyzer", () -> new ElectrolyzerRecipeSerializer(ElectrolyzerRecipe::new, 350));
    public static RegistryObject<FermenterRecipeSerializer<FermenterRecipe>> FERMENTER_SERIALIZER
            = RECIPE_SERIALIZERS.register("fermenter", () -> new FermenterRecipeSerializer(FermenterRecipe::new, 1000));
    public static RegistryObject<FrothFlotationRecipeSerializer<FrothFlotationRecipe>> FROTH_FLOTATION_SERIALIZER
            = RECIPE_SERIALIZERS.register("froth_flotation_chamber", () -> new FrothFlotationRecipeSerializer(FrothFlotationRecipe::new, 1000));
    public static RegistryObject<MaceratorRecipeSerializer<MaceratorRecipe>> MACERATOR_SERIALIZER
            = RECIPE_SERIALIZERS.register("macerator", () -> new MaceratorRecipeSerializer(MaceratorRecipe::new, 350));
    public static RegistryObject<ReactionChamberRecipeSerializer<ReactionChamberRecipe>> REACTION_CHAMBER_SERIALIZER
            = RECIPE_SERIALIZERS.register("reaction_chamber", () -> new ReactionChamberRecipeSerializer(ReactionChamberRecipe::new, 1000));
    public static RegistryObject<SmelteryRecipeSerializer<SmelteryRecipe>> SMELTERY_SERIALIZER
            = RECIPE_SERIALIZERS.register("smeltery", () -> new SmelteryRecipeSerializer(SmelteryRecipe::new, 1000));


    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}