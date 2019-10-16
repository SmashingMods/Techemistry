package al132.techemistry.blocks.world;

import al132.techemistry.blocks.BaseBlock;
import net.minecraft.block.material.Material;

public class WorldBlock extends BaseBlock {

    public final int veinSize;
    public final int veinCount;

    public WorldBlock(String name, int veinSize, int veinCount) {
        super(name, Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 6.0f));
        this.veinSize = veinSize;
        this.veinCount = veinCount;
    }
}