package al132.techemistry.world;

import al132.techemistry.Config;
import al132.techemistry.Ref;
import al132.techemistry.blocks.world.WorldBlock;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

import static net.minecraft.world.gen.feature.Feature.ORE;

//Referenced Mekanism (MIT license) https://github.com/mekanism/Mekanism/blob/1.16.x/src/main/java/mekanism/common/world/GenHandler.java
public final class WorldGen {


    public static void run() {
        /*
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (Config.GENERATE_DOLOMITE.get()) addSurfaceOre(biome, Ref.dolomite);
            if (Config.GENERATE_SULFIDE.get()) addStandardOre(biome, Ref.sulfideOre);
            if (Config.GENERATE_OXIDE.get()) addStandardOre(biome, Ref.oxideOre);
            if (Config.GENERATE_CARBONATE.get()) addStandardOre(biome, Ref.carbonateOre);
            if (Config.GENERATE_PHOSPHATE.get()) addStandardOre(biome, Ref.phosphateOre);
            if (Config.GENERATE_SULFUR.get()) addStandardOre(biome, Ref.sulfurOre);
            if (Config.GENERATE_SULFATE.get()) addStandardOre(biome, Ref.sulfateOre);
        }
        */
    }

    public static void onBiomeLoad(BiomeLoadingEvent e) {
        if (!isValidBiome(e.getCategory())) return;
        BiomeGenerationSettingsBuilder generation = e.getGeneration();
        if (Config.GENERATE_DOLOMITE.get()) addFeature(generation, getStandardOreFeature(Ref.dolomite));
        if (Config.GENERATE_SULFIDE.get()) addFeature(generation, getStandardOreFeature(Ref.sulfideOre));
        if (Config.GENERATE_OXIDE.get()) addFeature(generation, getStandardOreFeature(Ref.oxideOre));
        if (Config.GENERATE_CARBONATE.get()) addFeature(generation, getStandardOreFeature(Ref.carbonateOre));
        if (Config.GENERATE_PHOSPHATE.get()) addFeature(generation, getStandardOreFeature(Ref.phosphateOre));
        if (Config.GENERATE_SULFUR.get()) addFeature(generation, getStandardOreFeature(Ref.sulfurOre));
        if (Config.GENERATE_SULFATE.get()) addFeature(generation, getStandardOreFeature(Ref.sulfateOre));

    }

    @Nullable
    private static ConfiguredFeature<?, ?> getStandardOreFeature(WorldBlock ore) {
        return ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.field_241882_a, ore.getDefaultState(), ore.veinSize))
                .func_242733_d(200)
                .func_242728_a()
                .func_242731_b(ore.veinCount);
    }

    private static boolean isValidBiome(Biome.Category biomeCategory) {
        return biomeCategory != Biome.Category.THEEND && biomeCategory != Biome.Category.NETHER;
    }

    private static void addFeature(BiomeGenerationSettingsBuilder generation, @Nullable ConfiguredFeature<?, ?> feature) {
        if (feature != null) {
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature);
        }
    }


    /*
    //CountRangeConfig(veins,bottomOffset(min-height?),topOffset,max-height?)
    public static void addSurfaceOre(Biome biome, WorldBlock ore) {
        biome.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, Feature.ORE.withConfiguration(
                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ore.getDefaultState(), ore.veinSize))
                .withPlacement(Placement.COUNT_RANGE.configure(
                        new CountRangeConfig(ore.veinCount, 80, 0, 255))));
    }

    public static void addStandardOre(Biome biome, WorldBlock ore) {
        WorldGenRegistries.field_243653_e.getOrDefault(ore.getRegistryName());

        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, ore.getDefaultState(), ore.veinSize))
                .withPlacement(Placement.COUNT_RANGE.configure(
                        new CountRangeConfig(ore.veinCount, 0, 0, 200))));
    }
     */
    // OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, Features.States.field_244046_aj, 9))
    // .func_242733_d(64).func_242728_a().func_242731_b(20));

}
