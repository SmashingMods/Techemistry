package al132.techemistry.world;

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
            addSurfaceOre(biome, Ref.dolomite);
            addStandardOre(biome, Ref.sulfideOre);
            addStandardOre(biome, Ref.oxideOre);
            addStandardOre(biome, Ref.carbonateOre);
            addStandardOre(biome, Ref.phosphateOre);
            addStandardOre(biome,Ref.sulfurOre);
            addStandardOre(biome,Ref.sulfateOre);
        }
    }

    //CountRangeConfig(veins,bottomOffset(min-height?),topOffset,max-height?)
    public static void addSurfaceOre(Biome biome, WorldBlock ore) {
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(
                Feature.ORE,
                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ore.getDefaultState(), ore.veinSize),
                Placement.COUNT_RANGE,
                new CountRangeConfig(ore.veinCount, 80, 0, 255)));
    }
/*
    public static void test(Biome biome, WorldBlock ore){
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Biome.createDecoratedFeature()
    }
*/
    public static void addStandardOre(Biome biome, WorldBlock ore) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(
                Feature.ORE,
                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ore.getDefaultState(), ore.veinSize),
                Placement.COUNT_RANGE,
                new CountRangeConfig(ore.veinCount, 0, 0, 200)));
    }
}
