package al132.techemistry.blocks;

import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraftforge.common.util.LazyOptional;

public interface HeatTile {

    LazyOptional<IHeatStorage> getHeat();
}