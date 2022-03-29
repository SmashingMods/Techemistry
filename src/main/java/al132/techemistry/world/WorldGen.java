package al132.techemistry.world;

import al132.techemistry.Config;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;

public final class WorldGen {

    public static final int OVERWORLD_VEINSIZE = 5;
    public static final int OVERWORLD_AMOUNT = 3;

    //public static Holder<PlacedFeature> DOLOMITE_OREGEN;
    public static Holder<PlacedFeature> SULFIDE_OREGEN;
    public static Holder<PlacedFeature> OXIDE_OREGEN;
    public static Holder<PlacedFeature> CARBONATE_OREGEN;
    public static Holder<PlacedFeature> PHOSPHATE_OREGEN;
    public static Holder<PlacedFeature> SULFUR_OREGEN;
    public static Holder<PlacedFeature> SULFATE_OREGEN;


    public static void  registerFeatures() {
        //register("dolomite", DOLOMITE);
        OreConfiguration sulfideConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES,
                Registration.SULFIDE_ORE_BLOCK.get().defaultBlockState(), OVERWORLD_VEINSIZE);
        SULFIDE_OREGEN = registerOre(sulfideConfig, "sulfide_ore");
        OreConfiguration oxideConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES,
                Registration.OXIDE_ORE_BLOCK.get().defaultBlockState(), OVERWORLD_VEINSIZE);
        OXIDE_OREGEN = registerOre(oxideConfig, "oxide_ore");
        OreConfiguration carbonateConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES,
                Registration.CARBONATE_ORE_BLOCK.get().defaultBlockState(), OVERWORLD_VEINSIZE);
        CARBONATE_OREGEN = registerOre(carbonateConfig, "carbonate_ore");
        OreConfiguration phosphateConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES,
                Registration.PHOSPHATE_ORE_BLOCK.get().defaultBlockState(), OVERWORLD_VEINSIZE);
        PHOSPHATE_OREGEN = registerOre(phosphateConfig, "phosphate_ore");
        OreConfiguration sulfurConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES,
                Registration.SULFUR_ORE_BLOCK.get().defaultBlockState(), OVERWORLD_VEINSIZE);
        SULFUR_OREGEN = registerOre(sulfurConfig, "sulfure_ore");
        OreConfiguration sulfateConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES,
                Registration.SULFATE_ORE_BLOCK.get().defaultBlockState(), OVERWORLD_VEINSIZE);
        SULFATE_OREGEN = registerOre(sulfateConfig, "sulfate_ore");

    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature>
    registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    public static Holder<PlacedFeature> registerOre(OreConfiguration config, String name) {
        return registerPlacedFeature(name, new ConfiguredFeature<>(Feature.ORE, config),
                CountPlacement.of(OVERWORLD_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));
    }


    public static void onBiomeLoad(BiomeLoadingEvent e) {
        if (!isValidBiome(e.getCategory())) return;
       // if (Config.GENERATE_DOLOMITE.get())
       //     e.getGeneration().addFeature(UNDERGROUND_ORES, DOLOMITE_OREGEN);
        if (Config.GENERATE_SULFIDE.get())
            e.getGeneration().addFeature(UNDERGROUND_ORES, SULFIDE_OREGEN);
        if (Config.GENERATE_OXIDE.get())
            e.getGeneration().addFeature(UNDERGROUND_ORES, OXIDE_OREGEN);
        if (Config.GENERATE_CARBONATE.get())
            e.getGeneration().addFeature(UNDERGROUND_ORES, CARBONATE_OREGEN);
        if (Config.GENERATE_PHOSPHATE.get())
            e.getGeneration().addFeature(UNDERGROUND_ORES, PHOSPHATE_OREGEN);
        if (Config.GENERATE_SULFUR.get())
            e.getGeneration().addFeature(UNDERGROUND_ORES, SULFUR_OREGEN);
        if (Config.GENERATE_SULFATE.get())
            e.getGeneration().addFeature(UNDERGROUND_ORES, SULFATE_OREGEN);

    }


    private static boolean isValidBiome(Biome.BiomeCategory biomeCategory) {
        return biomeCategory != Biome.BiomeCategory.THEEND && biomeCategory != Biome.BiomeCategory.NETHER;
    }
}
