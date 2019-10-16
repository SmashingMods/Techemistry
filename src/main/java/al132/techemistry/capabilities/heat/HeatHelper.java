package al132.techemistry.capabilities.heat;

import al132.techemistry.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.text.DecimalFormat;

public class HeatHelper {

    public enum TempType {
        KELVIN("K"),
        CELSIUS("C"),
        FAHRENHEIT("F");

        public final String symbol;

        TempType(String symbol) {
            this.symbol = symbol;
        }

        public TempType next() {
            if (this == KELVIN) return CELSIUS;
            else if (this == CELSIUS) return FAHRENHEIT;
            else return KELVIN;
        }
    }

    public static final double ROOM_TEMP = 295;

    private static DecimalFormat heatFormat = new DecimalFormat("#.##");

    public static String format(IHeatStorage heat, TempType type) {
        return format(heat.getHeatStored(), type);
    }

    public static double toFahrenheit(double heat) {
        return (heat * 9.0 / 5.0) - 459.67;
    }

    public static double toCelsius(double heat) {
        return heat - 273.15;
    }

    public static String format(double heat, TempType type) {
        String formatted;// = heatFormat.format(heat);
        switch (type) {
            case KELVIN:
                formatted = heatFormat.format(heat);
                return formatted + " K";
            case CELSIUS:
                formatted = heatFormat.format(toCelsius(heat));
                return formatted + " C";
            case FAHRENHEIT:
                formatted = heatFormat.format(toFahrenheit(heat));
                return formatted + " F";
            default:
                return heatFormat.format(heat);
        }
    }

    public static double getBiomeHeat(World world, BlockPos pos) {
        Biome.TempCategory cat = world.getBiome(pos).getTempCategory();
        switch (cat) {
            case COLD:
                return 245;
            case OCEAN:
                return 282;
            case WARM:
                return 305;
            case MEDIUM:
            default:
                return ROOM_TEMP;
        }
    }

    public static double getBlockHeat(World world, BlockPos pos, BlockState state) {
        Block block = state.getBlock();
        /*TileEntity tile = world.getTileEntity(pos);
        if (tile != null) {
            LazyOptional<Double> temp = tile.getCapability(CapabilityHeat.HEAT_CAP).map(IHeatStorage::getHeatStored);
            if (temp.isPresent()) return temp.orElse(-1.0);
        }
        */
        if (block == Blocks.LAVA) return 1273.0;
        else if (block == Blocks.MAGMA_BLOCK) return 900.0;
        else if (block == Blocks.TORCH) return 600.0;
        else if (block == Blocks.SNOW_BLOCK) return 250.0;
        else if (block == Blocks.SNOW) return 250.0;
        else if (block == Blocks.ICE) return 255.0;
        else if (block == Blocks.PACKED_ICE) return 240.0;
        else if (block == Blocks.BLUE_ICE) return 200.0;
        else return getBiomeHeat(world, pos);
    }

    public static void balanceHeat(World world, BlockPos pos, IHeatStorage heat) {
        double base = Utils.getSurroundingBlocks(world, pos).stream().mapToDouble(x -> HeatHelper.getBlockHeat(world, pos, x)).sum() / 6.0;
        if (base > heat.getHeatStored() + 1) {
            heat.receiveHeat(0.01f, false);
        } else if (base + 1 < heat.getHeatStored()) {
            heat.extractHeat(0.01f, false);
        }
    }
}