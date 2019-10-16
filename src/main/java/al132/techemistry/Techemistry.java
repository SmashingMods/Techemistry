package al132.techemistry;

import al132.alib.ModData;
import al132.techemistry.blocks.calcination_chamber.CalcinationRegistry;
import al132.techemistry.blocks.distillery.DistilleryRegistry;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerRegistry;
import al132.techemistry.blocks.fermenter.FermenterRegistry;
import al132.techemistry.blocks.gas_collector.CollectorRegistry;
import al132.techemistry.blocks.macerator.MaceratorRegistry;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRegistry;
import al132.techemistry.blocks.smeltery.SmelteryRegistry;
import al132.techemistry.capabilities.heat.CapabilityHeat;
import al132.techemistry.capabilities.player.CapabilityPlayerData;
import al132.techemistry.capabilities.player.PlayerData;
import al132.techemistry.capabilities.player.PlayerDataDispatcher;
import al132.techemistry.setup.ClientProxy;
import al132.techemistry.setup.IProxy;
import al132.techemistry.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Techemistry.MODID)
public class Techemistry {

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static final ModData data = new TechemistryData();

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "techemistry";


    public Techemistry() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        //MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        proxy.init();
        WorldGen.run();
        CapabilityHeat.register();
        CapabilityPlayerData.register();
    }

    @SubscribeEvent
    public void onEntityConstructing(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof PlayerEntity) {
            if (!e.getObject().getCapability(CapabilityPlayerData.PLAYER_DATA_CAP).isPresent()) {
                e.addCapability(new ResourceLocation(Techemistry.MODID, "player_data"), new PlayerDataDispatcher());
            }
        }
    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            LazyOptional<PlayerData> capability = event.getOriginal().getCapability(CapabilityPlayerData.PLAYER_DATA_CAP);
            capability.ifPresent(oldStore -> {
                event.getPlayer().getCapability(CapabilityPlayerData.PLAYER_DATA_CAP).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }


    @SubscribeEvent
    public void serverStarting(final FMLServerStartingEvent e) {
        ReactivitySeries.init();
        FermenterRegistry.init();
        MaceratorRegistry.init();
        ElectrolyzerRegistry.init();
        DistilleryRegistry.init();
        CalcinationRegistry.init();
        ReactionChamberRegistry.init();
        SmelteryRegistry.init();
        CollectorRegistry.init(); //Make sure this is last! uses the previous machine recipes
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> e) {
            Ref.initBlocks();
            // e.getRegistry().register(Ref.rumBlock);
            data.BLOCKS.forEach(e.getRegistry()::register);
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> e) {
            Ref.initItems();
            data.ITEMS.forEach(e.getRegistry()::register);
            // e.getRegistry().register(Ref.rumBucket);
            data.BLOCKS.forEach(x -> e.getRegistry()
                    .register(new BlockItem(x, new Item.Properties().group(data.itemGroup)).setRegistryName(x.getRegistryName())));
        }

        @SubscribeEvent
        public static void onTERegistry(final RegistryEvent.Register<TileEntityType<?>> e) {
            data.registerTiles(e);
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> e) {
            data.registerContainers(e);
        }

        @SubscribeEvent
        public static void onRecipeRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> e) {
        }

        /*
        @SubscribeEvent
        public static void onFluidRegistry(final RegistryEvent.Register<Fluid> e) {
            Ref.initFluids();
            e.getRegistry().register(Ref.rum);
            e.getRegistry().register(Ref.flowingRum);
            // e.getRegistry().register();
        }*/
    }
}