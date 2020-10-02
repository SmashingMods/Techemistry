package al132.techemistry;

import al132.alib.ModData;
import al132.techemistry.blocks.calcination_chamber.CalcinationRegistry;
import al132.techemistry.blocks.distillery.DistilleryRegistry;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerRegistry;
import al132.techemistry.blocks.fermenter.FermenterRegistry;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationRegistry;
import al132.techemistry.blocks.gas_collector.CollectorRegistry;
import al132.techemistry.blocks.macerator.MaceratorRegistry;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRegistry;
import al132.techemistry.blocks.smeltery.SmelteryRegistry;
import al132.techemistry.capabilities.heat.CapabilityHeat;
import al132.techemistry.capabilities.player.CapabilityPlayerData;
import al132.techemistry.capabilities.player.PlayerData;
import al132.techemistry.capabilities.player.PlayerDataDispatcher;
import al132.techemistry.data.ReactivitySeries;
import al132.techemistry.setup.ClientProxy;
import al132.techemistry.setup.IProxy;
import al132.techemistry.setup.ServerProxy;
import al132.techemistry.world.WorldGen;
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
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Techemistry.MODID)
public class Techemistry {

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static final ModData data = new TechemistryData();

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "techemistry";


    public Techemistry() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("techemistry-common.toml"));
    }

    private void commonSetup(final FMLCommonSetupEvent e) {
        proxy.init();
        WorldGen.run();
        CapabilityHeat.register();
        CapabilityPlayerData.register();
        ReactivitySeries.init();
        FermenterRegistry.init();
        MaceratorRegistry.init();
        ElectrolyzerRegistry.init();
        DistilleryRegistry.init();
        CalcinationRegistry.init();
        ReactionChamberRegistry.init();
        SmelteryRegistry.init();
        FrothFlotationRegistry.init();
        CollectorRegistry.init(); //Make sure this is last! uses the previous machine recipes
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

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> e) {
            Ref.initBlocks();
            //data.BLOCKS.forEach(e.getRegistry()::register);
            for (Block block : data.BLOCKS) {
                e.getRegistry().register(block);
            }
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> e) {
            Ref.initItems();
            for (Item item : data.ITEMS) {
                e.getRegistry().register(item);
            }
            for (Block block : data.BLOCKS) {
                e.getRegistry().register(new BlockItem(block, new Item.Properties().group(data.itemGroup)).setRegistryName(block.getRegistryName()));
            }
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
        public static void onRecipeSerializerRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> e){
            e.getRegistry().register(Ref.DISTILLERY_SERIALIZER.setRegistryName(new ResourceLocation(MODID,"distillery")));
            e.getRegistry().register(Ref.CALCINATION_SERIALIZER.setRegistryName(new ResourceLocation(MODID,"calcination_chamber")));
            e.getRegistry().register(Ref.ELECTROLYZER_SERIALIZER.setRegistryName(new ResourceLocation(MODID,"electrolyzer")));
            e.getRegistry().register(Ref.FERMENTER_SERIALIZER.setRegistryName(new ResourceLocation(MODID,"fermenter")));
            e.getRegistry().register(Ref.FROTH_FLOTATION_SERIALIZER.setRegistryName(new ResourceLocation(MODID,"forth_flotation_chamber")));
            e.getRegistry().register(Ref.MACERATOR_SERIALIZER.setRegistryName(new ResourceLocation(MODID,"macerator")));
            e.getRegistry().register(Ref.REACTION_CHAMBER_SERIALIZER.setRegistryName(new ResourceLocation(MODID,"reaction_chamber")));
            e.getRegistry().register(Ref.SMELTERY_SERIALIZER.setRegistryName(new ResourceLocation(MODID,"smeltery")));
        }
    }
}