package al132.techemistry;

import al132.alib.blocks.ABaseBlock;
import al132.chemlib.Utils;
import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.blocks.calcination_chamber.CalcinationBlock;
import al132.techemistry.blocks.calcination_chamber.CalcinationContainer;
import al132.techemistry.blocks.calcination_chamber.CalcinationTile;
import al132.techemistry.blocks.distillery.DistilleryColumnBlock;
import al132.techemistry.blocks.distillery.DistilleryContainer;
import al132.techemistry.blocks.distillery.DistilleryControllerBlock;
import al132.techemistry.blocks.distillery.DistilleryTile;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerBlock;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerContainer;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerTile;
import al132.techemistry.blocks.fermenter.FermenterBlock;
import al132.techemistry.blocks.fermenter.FermenterContainer;
import al132.techemistry.blocks.fermenter.FermenterTile;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationBlock;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationContainer;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationTile;
import al132.techemistry.blocks.gas_collector.GasCollectorBlock;
import al132.techemistry.blocks.gas_collector.GasCollectorContainer;
import al132.techemistry.blocks.gas_collector.GasCollectorTile;
import al132.techemistry.blocks.macerator.MaceratorBlock;
import al132.techemistry.blocks.macerator.MaceratorContainer;
import al132.techemistry.blocks.macerator.MaceratorTile;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberBlock;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberContainer;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberTile;
import al132.techemistry.blocks.smeltery.SmelteryBlock;
import al132.techemistry.blocks.smeltery.SmelteryContainer;
import al132.techemistry.blocks.smeltery.SmelteryTile;
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
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;
import java.util.List;

public class Ref {

    public final static int TEXT_COLOR = 16777215;

    public static BaseItem yeast;
    public static BaseItem sugarWine;
    public static BaseItem crushedWheat;
    public static BaseItem beer;
    public static BaseItem cider;
    public static BaseItem potatoWine;
    public static BaseItem rum;
    public static BaseItem vodka;
    public static BaseItem whiskey;
    public static BaseItem appleSauce;
    public static BaseItem coke;
    public static BaseItem yeastGrowthPlate;
    public static BaseItem sulfurChunk;
    public static MineralItem salAmmoniac;
    public static MineralItem natron;


    public static List<Mineral> minerals = new ArrayList<>();
    //sulfide
    public static Mineral galena;
    public static Mineral pyrite;
    public static Mineral sphalerite;
    public static Mineral chalcocite;
    public static Mineral cinnabar;
    public static Mineral stibnite;
    public static Mineral millerite;
    public static Mineral braggite;

    //oxide
    public static Mineral cassiterite;
    public static Mineral pyrolusite;
    public static Mineral cuprite;
    public static Mineral ilmenite;
    public static Mineral magnetite;
    public static Mineral hematite;
    public static Mineral uraninite;

    //carbonate
    public static Mineral strontianite;
    public static Mineral rhodochrosite;
    public static Mineral cerussite;
    public static Mineral siderite;
    public static Mineral spherocobaltite;

    //phosphate
    public static Mineral vanadinite;
    public static Mineral pyromorphite;

    //sulfate
    public static Mineral melanterite;
    public static Mineral barite;
    public static Mineral celestite;
    public static Mineral anglesite;

    public static ElectrodeItem platinumElectrode;

    //public static BucketItem rumBucket;

    public static ABaseBlock fermenter;
    public static ABaseBlock macerator;
    public static ABaseBlock gasCollector;
    public static ABaseBlock electrolyzer;
    public static ABaseBlock calcinationChamber;
    public static ABaseBlock reactionChamber;
    public static ABaseBlock smeltery;
    public static ABaseBlock frothFlotationChamber;
    public static ABaseBlock distilleryController;
    public static ABaseBlock distilleryColumn;
    public static ABaseBlock solidFuelHeater;
    public static ABaseBlock steamBoiler;
    public static ABaseBlock steamTurbine;
    public static ABaseBlock solarHeater;

    public static WorldBlock dolomite;
    public static WorldBlock sulfideOre;
    public static WorldBlock oxideOre;
    public static WorldBlock carbonateOre;
    public static WorldBlock phosphateOre;
    public static WorldBlock sulfurOre;
    public static WorldBlock sulfateOre;


// static FlowingFluidBlock rumBlock;
    // public static FlowingFluid rum;
    //public static FlowingFluid flowingRum;

    public static List<TileEntityType<?>> types = new ArrayList<>();
    public static TileEntityType<FermenterTile> fermenterTile;
    public static TileEntityType<MaceratorTile> maceratorTile;
    public static TileEntityType<GasCollectorTile> gasCollectorTile;
    public static TileEntityType<ElectrolyzerTile> electrolyzerTile;
    public static TileEntityType<DistilleryTile> distilleryTile;
    public static TileEntityType<SolidHeaterTile> solidHeaterTile;
    public static TileEntityType<SteamBoilerTile> steamBoilerTile;
    public static TileEntityType<SteamTurbineTile> steamTurbineTile;
    public static TileEntityType<CalcinationTile> calcinationTile;
    public static TileEntityType<ReactionChamberTile> reactionChamberTile;
    public static TileEntityType<SmelteryTile> smelteryTile;
    public static TileEntityType<FrothFlotationTile> frothFlotationTile;
    public static TileEntityType<SolarHeaterTile> solarHeaterTile;


    public static ContainerType<FermenterContainer> fermenterContainer;
    public static ContainerType<MaceratorContainer> maceratorContainer;
    public static ContainerType<GasCollectorContainer> gasCollectorContainer;
    public static ContainerType<ElectrolyzerContainer> electrolyzerContainer;
    public static ContainerType<DistilleryContainer> distilleryContainer;
    public static ContainerType<SolidHeaterContainer> solidHeaterContainer;
    public static ContainerType<SteamBoilerContainer> steamBoilerContainer;
    public static ContainerType<SteamTurbineContainer> steamTurbineContainer;
    public static ContainerType<CalcinationContainer> calcinationContainer;
    public static ContainerType<ReactionChamberContainer> reactionChamberContainer;
    public static ContainerType<SmelteryContainer> smelteryContainer;
    public static ContainerType<FrothFlotationContainer> frothFlotationContainer;
    public static ContainerType<SolarHeaterContainer> solarHeaterContainer;

    public static String s(int input) {
        return Utils.getSubscript(input);
    }

    public static void initItems() {
        yeast = new YeastItem();
        sugarWine = new DrinkItem("sugar_wine");
        crushedWheat = new CrushedWheatItem();
        beer = new DrinkItem("beer");
        cider = new DrinkItem("cider");
        rum = new DrinkItem("rum");
        potatoWine = new DrinkItem("potato_wine");
        vodka = new DrinkItem("vodka");
        whiskey = new DrinkItem("whiskey");
        appleSauce = new AppleSauceItem();
        coke = new CokeItem();
        platinumElectrode = new ElectrodeItem("platinum_electrode");
        yeastGrowthPlate = new YeastGrowthPlate();
        sulfurChunk = new BaseItem("sulfur_chunk");
        salAmmoniac = new MineralItem("sal_ammoniac", new ChemicalStack("ammonium_chloride"));
        natron = new MineralItem("natron", new ChemicalStack("sodium_carbonate"));

        galena = new Mineral("galena", new ChemicalStack("lead_oxide"));
        pyrite = new Mineral("pyrite", new ChemicalStack("iron_disulfide"));
        chalcocite = new Mineral("chalcocite", new ChemicalStack("copper", 2), new ChemicalStack("sulfur"));
        sphalerite = new Mineral("sphalerite", new ChemicalStack("zinc_sulfide"));
        cinnabar = new Mineral("cinnabar", new ChemicalStack("mercury_sulfide"));
        stibnite = new Mineral("stibnite", new ChemicalStack("antimony_trisulfide"));
        millerite = new Mineral("millerite", new ChemicalStack("nickel_sulfide"));
        braggite = new Mineral("braggite", "(Pt, Pd, Ni)S");

        cassiterite = new Mineral("cassiterite", new ChemicalStack("tin_oxide"));
        pyrolusite = new Mineral("pyrolusite", new ChemicalStack("manganese_oxide"));
        strontianite = new Mineral("strontianite", new ChemicalStack("strontium_carbonate"));
        vanadinite = new Mineral("vanadinite", "Pb", s(5), "(VO", s(4), ")", s(3), "Cl");
        pyromorphite = new Mineral("pyromorphite", "Pb", s(5), "(PO", s(4), ")", s(3), "Cl");
        hematite = new Mineral("hematite", new ChemicalStack("iron_oxide"));
        ilmenite = new Mineral("ilmenite", "FeTiO" + s(3));
        cuprite = new Mineral("cuprite", "Cu" + s(2) + "O");
        magnetite = new Mineral("magnetite", "Fe" + s(3) + "O" + s(4));
        uraninite = new Mineral("uraninite", "UO" + s(2));
        rhodochrosite = new Mineral("rhodochrosite", new ChemicalStack("manganese_carbonate"));
        cerussite = new Mineral("cerussite", new ChemicalStack("lead_carbonate"));
        siderite = new Mineral("siderite", new ChemicalStack("iron_carbonate"));
        spherocobaltite = new Mineral("spherocobaltite", new ChemicalStack("cobalt_carbonate"));
        //SULFATE
        melanterite = new Mineral("melanterite", "FeSO", s(4), "7H", s(2), "O");
        barite = new Mineral("barite", new ChemicalStack("barium_sulfate"));
        celestite = new Mineral("celestite", new ChemicalStack("strontium_sulfate"));
        anglesite = new Mineral("anglesite", new ChemicalStack("lead_sulfate"));

        for (PartMaterial material : PartMaterialRegistry.materials) {
            new GearItem(material);
        }
        /*rumBucket = new BucketItem(() -> rum,
                new Item.Properties().group(Alchemistry2.data.itemGroup).maxStackSize(1).containerItem(Items.BUCKET)) {
            @Override
            protected ItemStack emptyBucket(ItemStack stack, PlayerEntity entity) {
                return !entity.abilities.isCreativeMode ? new ItemStack(Ref.rumBucket) : stack;
            }
        };
        rumBucket.setRegistryName(Utils.toLocation("rum_bucket"));
         */
    }

    public static void initBlocks() {
        dolomite = new DolomiteBlock();
        sulfideOre = new SulfideOre();
        oxideOre = new OxideOre();
        carbonateOre = new CarbonateOre();
        phosphateOre = new PhosphateOre();
        sulfurOre = new SulfurOre();
        sulfateOre = new SulfateOre();

        fermenter = new FermenterBlock();
        macerator = new MaceratorBlock();
        gasCollector = new GasCollectorBlock();
        electrolyzer = new ElectrolyzerBlock();
        calcinationChamber = new CalcinationBlock();
        reactionChamber = new ReactionChamberBlock();
        smeltery = new SmelteryBlock();
        frothFlotationChamber = new FrothFlotationBlock();
        solarHeater = new SolarHeaterBlock();

        distilleryController = new DistilleryControllerBlock();
        distilleryColumn = new DistilleryColumnBlock();

        solidFuelHeater = new SolidHeaterBlock();
        steamBoiler = new SteamBoilerBlock();
        steamTurbine = new SteamTurbineBlock();


        //  rumBlock = createFluidBlock(() -> Ref.rum);
        // rumBlock.setRegistryName(Utils.toLocation("rum"));
    }

    /*
    public static void initFluids() {
        ForgeFlowingFluid.Properties rumProperties = Alchemistry2Data.fluidProperties("rum", () -> rum, () -> flowingRum)
                .block(() -> rumBlock)
                .bucket(() -> rumBucket);
        flowingRum = Alchemistry2Data.registerFluid("flowing_rum", new ForgeFlowingFluid.Flowing(rumProperties));
        rum = Alchemistry2Data.registerFluid("rum", new ForgeFlowingFluid.Flowing(rumProperties));
    }

    public static FlowingFluidBlock createFluidBlock(Supplier<FlowingFluid> fluid) {
        return new FlowingFluidBlock(fluid,
                Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100F).noDrops());
    }
*/
}