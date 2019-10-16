package al132.techemistry;

import al132.alib.blocks.ABaseBlock;
import al132.alib.items.ABaseItem;
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

    public static ABaseItem yeast;// = new YeastItem();
    public static ABaseItem sugarWine;// = new WineItem();
    public static ABaseItem crushedWheat;// = new CrushedWheatItem();
    public static ABaseItem beer;// = new BeerItem();
    public static ABaseItem cider;// = new CiderItem();
    public static ABaseItem potatoWine;
    public static ABaseItem rum;
    public static ABaseItem vodka;
    public static ABaseItem whiskey;
    public static ABaseItem appleSauce;
    public static ABaseItem coke;
    public static ABaseItem yeastGrowthPlate;
    public static ABaseItem sulfurChunk;
    public static CrushedMelanterite crushedMelanterite;
    public static ABaseItem salAmmoniac;
    public static BaseItem natron;

    //sulfide
    public static MineralItem galena;
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

    //carbonate
    public static MineralItem strontianite;

    //phosphate
    public static MineralItem vanadinite;

    //sulfate
    public static MineralItem melanterite;

    public static ElectrodeItem platinumElectrode;

    //public static BucketItem rumBucket;

    public static ABaseBlock fermenter;
    public static ABaseBlock macerator;
    public static ABaseBlock gasCollector;
    public static ABaseBlock electrolyzer;
    public static ABaseBlock calcinationChamber;
    public static ABaseBlock reactionChamber;
    public static ABaseBlock smeltery;

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
        salAmmoniac = new BaseItem("sal_ammoniac");
        natron = new BaseItem("natron");

        galena = new MineralItem("galena", new ChemicalStack("lead_oxide"));
        pyrite = new MineralItem("pyrite", new ChemicalStack("iron_disulfide"));
        sphalerite = new MineralItem("sphalerite", new ChemicalStack("zinc_sulfide"));
        cassiterite = new MineralItem("cassiterite", new ChemicalStack("tin_oxide"));
        pyrolusite = new MineralItem("pyrolusite", new ChemicalStack("manganese_oxide"));
        strontianite = new MineralItem("strontianite", new ChemicalStack("strontium_carbonate"));
        vanadinite = new MineralItem("vanadinite");
        chalcocite = new MineralItem("chalcocite", new ChemicalStack("copper", 2), new ChemicalStack("sulfur"));
        melanterite = new MineralItem("melanterite", crushedMelanterite.components);
        cinnabar = new MineralItem("cinnabar", new ChemicalStack("mercury_sulfide"));
        stibnite = new MineralItem("stibnite", new ChemicalStack("antimony_trisulfide"));
        millerite = new MineralItem("millerite", new ChemicalStack("nickel_sulfide"));
        braggite = new MineralItem("braggite");
        /*
        rumBucket = new BucketItem(() -> rum,
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

        distilleryController = new DistilleryControllerBlock();
        distilleryColumn = new DistilleryColumnBlock();

        solidFuelHeater = new SolidHeaterBlock();
        steamBoiler = new SteamBoilerBlock();
        steamTurbine = new SteamTurbineBlock();

        for (PartMaterial material : PartMaterialRegistry.materials) {
            new GearItem(material);
        }

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