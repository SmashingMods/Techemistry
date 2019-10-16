package al132.techemistry.blocks;

import al132.techemistry.Techemistry;
import al132.alib.blocks.ABaseTileBlock;
import net.minecraft.tileentity.TileEntity;

import java.util.function.Supplier;

public class BaseTileBlock extends ABaseTileBlock {
    public BaseTileBlock(String name, Supplier<TileEntity> tile, Properties properties) {
        super(Techemistry.data, name, properties.hardnessAndResistance(2.0f), tile);
    }
}