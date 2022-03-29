package al132.techemistry.setup;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.calcination_chamber.CalcinationRecipe;
import al132.techemistry.blocks.calcination_chamber.CalcinationRegistry;
import al132.techemistry.blocks.distillery.DistilleryRecipe;
import al132.techemistry.blocks.distillery.DistilleryRegistry;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerRecipe;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerRegistry;
import al132.techemistry.blocks.fermenter.FermenterRecipe;
import al132.techemistry.blocks.fermenter.FermenterRegistry;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationRecipe;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationRegistry;
import al132.techemistry.blocks.macerator.MaceratorRecipe;
import al132.techemistry.blocks.macerator.MaceratorRegistry;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRecipe;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRegistry;
import al132.techemistry.blocks.smeltery.SmelteryRecipe;
import al132.techemistry.blocks.smeltery.SmelteryRegistry;
import al132.techemistry.capabilities.heat.CapabilityHeat;
import al132.techemistry.capabilities.player.PlayerDataEvents;
import al132.techemistry.data.ReactivitySeries;
import al132.techemistry.world.WorldGen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static al132.techemistry.Techemistry.MODID;

public class ModSetup {

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("Alchemistry") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.MACERATOR_BLOCK.get());
        }
    };

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(WorldGen::onBiomeLoad);
        bus.addGenericListener(Entity.class, PlayerDataEvents::onAttachCapabilitiesPlayer);
        bus.addListener(PlayerDataEvents::onPlayerCloned);
        bus.addListener(PlayerDataEvents::onRegisterCapabilities);
        bus.addListener(CapabilityHeat::onRegisterCapabilities);

    }

    public static void init(final FMLCommonSetupEvent e) {

        RecipeTypes.DISTILLERY = RecipeType.register(MODID + ":distillery");
        RecipeTypes.CALCINATION_CHAMBER = RecipeType.register(MODID + ":calcination_chamber");
        RecipeTypes.ELECTROLYZER = RecipeType.register(MODID + ":electrolyzer");
        RecipeTypes.FERMENTER = RecipeType.register(MODID + ":fermenter");
        RecipeTypes.FROTH_FLOTATION_CHAMBER = RecipeType.register(MODID + ":froth_flotation_chamber");
        RecipeTypes.MACERATOR = RecipeType.register(MODID + ":macerator");
        RecipeTypes.REACTION_CHAMBER = RecipeType.register(MODID + ":reaction_chamber");
        RecipeTypes.SMELTERY = RecipeType.register(MODID + ":smeltery");
        e.enqueueWork(() -> {
            //CapabilityHeat.register();
            //CapabilityPlayerData.register();
            WorldGen.registerFeatures();
            ReactivitySeries.init();
            FermenterRegistry.init();
            MaceratorRegistry.init();
            ElectrolyzerRegistry.init();
            DistilleryRegistry.init();
            CalcinationRegistry.init();
            ReactionChamberRegistry.init();
            SmelteryRegistry.init();
            FrothFlotationRegistry.init();
        });
        //CollectorRegistry.init(); //Make sure this is last! uses the previous machine recipes
    }
}
