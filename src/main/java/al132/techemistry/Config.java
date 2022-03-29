package al132.techemistry;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class Config {
    public static String CATEGORY_LevelGEN = "Levelgen";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue GENERATE_CARBONATE;
    public static ForgeConfigSpec.BooleanValue GENERATE_DOLOMITE;
    public static ForgeConfigSpec.BooleanValue GENERATE_SULFIDE;
    public static ForgeConfigSpec.BooleanValue GENERATE_OXIDE;
    public static ForgeConfigSpec.BooleanValue GENERATE_PHOSPHATE;
    public static ForgeConfigSpec.BooleanValue GENERATE_SULFATE;
    public static ForgeConfigSpec.BooleanValue GENERATE_SULFUR;

    static {
        initLevelgenConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static void initLevelgenConfig() {
        COMMON_BUILDER.comment("Ores").push(CATEGORY_LevelGEN);
        GENERATE_CARBONATE = COMMON_BUILDER.comment("Generate Carbonate Ore").define("generateCarbonate", true);
        GENERATE_DOLOMITE = COMMON_BUILDER.comment("Generate Dolomite").define("generateDolomite", true);
        GENERATE_SULFIDE = COMMON_BUILDER.comment("Generate Sulfide Ore").define("generateSulfide", true);
        GENERATE_OXIDE = COMMON_BUILDER.comment("Generate Oxide Ore").define("generateOxide", true);
        GENERATE_PHOSPHATE = COMMON_BUILDER.comment("Generate Phosphate Ore").define("generatePhosphate", true);
        GENERATE_SULFATE = COMMON_BUILDER.comment("Generate Sulfate Ore").define("generateSulfate", true);
        GENERATE_SULFUR = COMMON_BUILDER.comment("Generate Sulfur Ore").define("generateSulfur", true);
        COMMON_BUILDER.pop();
    }


    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }
}
