package al132.techemistry;

import al132.alib.blocks.ABaseBlock;
import al132.alib.items.ABaseItem;
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
import al132.techemistry.items.drinks.*;
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

    public static ABaseItem yeast;
    public static ABaseItem sugarWine;
    public static ABaseItem crushedWheat;
    public static ABaseItem beer;
    public static ABaseItem cider;
    public static ABaseItem potatoWine;
    public static ABaseItem rum;
    public static ABaseItem vodka;
    public static ABaseItem whiskey;
    public static ABaseItem appleSauce;
    public static ABaseItem coke;
    public static ABaseItem yeastGrowthPlate;
    public static ABaseItem sulfurChunk;
    public static ABaseItem salAmmoniac;
    public static BaseItem natron;

    public static CrushedMelanterite crushedMelanterite;
    public static BaseItem crushedPyrite;

    //sulfide
    public static MineralItem galena;
    public static BaseItem crushedGalena;
    public static BaseItem galenaSlurry;

    public static MineralItem pyrite;
    public static MineralItem sphalerite;
    public static MineralItem chalcocite;
    public static MineralItem cinnabar;
    public static MineralItem stibnite;
    public static MineralItem millerite;
    public static MineralItem braggite;

    //oxide
    public static MineralItem cassiterite;
    public static MineralItem pyrolusite;
    public static MineralItem cuprite;
    public static MineralItem ilmenite;
    public static MineralItem magnetite;
    public static MineralItem hematite;
    public static MineralItem uraninite;

    //carbonate
    public static MineralItem strontianite;
    public static MineralItem rhodochrosite;
    public static MineralItem cerussite;
    public static MineralItem siderite;
    public static MineralItem spherocobaltite;

    //phosphate
    public static MineralItem vanadinite;
    public static MineralItem pyromorphite;

    //sulfate
    public static MineralItem melanterite;
    public static MineralItem barite;
    public static MineralItem celestite;
    public static MineralItem anglesite;

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

    public static String s(int input) {
        return Utils.getSubscript(input);
    }

    public static void initItems() {
        yeast = new YeastItem();
        sugarWine = new WineItem();
        crushedWheat = new CrushedWheatItem();
        beer = new BeerItem();
        cider = new CiderItem();
        rum = new RumItem();
        potatoWine = new PotatoWineItem();
        vodka = new VodkaItem();
        whiskey = new WhiskeyItem();
        appleSauce = new AppleSauceItem();
        coke = new CokeItem();
        //crushedEggShell = new BaseItem("crushed_egg_shell");
        platinumElectrode = new ElectrodeItem("electrode_platinum");
        yeastGrowthPlate = new YeastGrowthPlate();
        sulfurChunk = new BaseItem("sulfur_chunk");
        crushedMelanterite = new CrushedMelanterite();
        crushedGalena = new BaseItem("crushed_galena");
        galenaSlurry = new BaseItem("galena_slurry");
        crushedPyrite = new BaseItem("crushed_pyrite");
        salAmmoniac = new MineralItem("sal_ammoniac", new ChemicalStack("ammonium_chloride"));
        natron = new MineralItem("natron", new ChemicalStack("sodium_carbonate"));

        galena = new MineralItem("galena", new ChemicalStack("lead_oxide"));
        pyrite = new MineralItem("pyrite", new ChemicalStack("iron_disulfide"));
        sphalerite = new MineralItem("sphalerite", new ChemicalStack("zinc_sulfide"));
        cassiterite = new MineralItem("cassiterite", new ChemicalStack("tin_oxide"));
        pyrolusite = new MineralItem("pyrolusite", new ChemicalStack("manganese_oxide"));
        strontianite = new MineralItem("strontianite", new ChemicalStack("strontium_carbonate"));
        vanadinite = new MineralItem("vanadinite", "Pb", s(5), "(VO", s(4), ")", s(3), "Cl");
        pyromorphite = new MineralItem("pyromorphite", "Pb", s(5), "(PO", s(4), ")", s(3), "Cl");

        chalcocite = new MineralItem("chalcocite", new ChemicalStack("copper", 2), new ChemicalStack("sulfur"));
        melanterite = new MineralItem("melanterite", crushedMelanterite.components);
        cinnabar = new MineralItem("cinnabar", new ChemicalStack("mercury_sulfide"));
        stibnite = new MineralItem("stibnite", new ChemicalStack("antimony_trisulfide"));
        millerite = new MineralItem("millerite", new ChemicalStack("nickel_sulfide"));
        braggite = new MineralItem("braggite", "(Pt, Pd, Ni)S");
        hematite = new MineralItem("hematite", new ChemicalStack("iron_oxide"));
        ilmenite = new MineralItem("ilmenite", "FeTiO" + s(3));
        cuprite = new MineralItem("cuprite", "Cu" + s(2) + "O");
        magnetite = new MineralItem("magnetite", "Fe" + s(3) + "O" + s(4));
        uraninite = new MineralItem("uraninite", "UO" + s(2));
        rhodochrosite = new MineralItem("rhodochrosite", new ChemicalStack("manganese_carbonate"));
        cerussite = new MineralItem("cerussite", new ChemicalStack("lead_carbonate"));
        siderite = new MineralItem("siderite", new ChemicalStack("iron_carbonate"));
        spherocobaltite = new MineralItem("spherocobaltite", new ChemicalStack("cobalt_carbonate"));
        barite = new MineralItem("barite", new ChemicalStack("barium_sulfate"));
        celestite = new MineralItem("celestite", new ChemicalStack("strontium_sulfate"));
        anglesite = new MineralItem("anglesite", new ChemicalStack("lead_sulfate"));

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