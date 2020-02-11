package al132.techemistry.world;

import al132.techemistry.Config;
import al132.techemistry.Ref;
import al132.techemistry.blocks.world.WorldBlock;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public final class WorldGen {

    public static void run() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (Config.GENERATE_DOLOMITE.get()) addSurfaceOre(biome, Ref.dolomite);
            if (Config.GENERATE_SULFIDE.get()) addStandardOre(biome, Ref.sulfideOre);
            if (Config.GENERATE_OXIDE.get()) addStandardOre(biome, Ref.oxideOre);
            if (Config.GENERATE_CARBONATE.get()) addStandardOre(biome, Ref.carbonateOre);
            if (Config.GENERATE_PHOSPHATE.get()) addStandardOre(biome, Ref.phosphateOre);
            if (Config.GENERATE_SULFUR.get()) addStandardOre(biome, Ref.sulfurOre);
            if (Config.GENERATE_SULFATE.get()) addStandardOre(biome, Ref.sulfateOre);
        }
    }


    //CountRangeConfig(veins,bottomOffset(min-height?),topOffset,max-height?)
    public static void addSurfaceOre(Biome biome, WorldBlock ore) {
        biome.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, Feature.ORE.func_225566_b_(
                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ore.getDefaultState(), ore.veinSize))
                .func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(
                        new CountRangeConfig(ore.veinCount, 80, 0, 255))));
    }

    /*
        public static void test(Biome biome, WorldBlock ore){
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Biome.createDecoratedFeature()
        }
    */
    public static void addStandardOre(Biome biome, WorldBlock ore) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(
                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ore.getDefaultState(), ore.veinSize))
                .func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(
                        new CountRangeConfig(ore.veinCount, 0, 0, 200))));
    }
}
