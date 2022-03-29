package al132.techemistry.blocks.world;


import al132.techemistry.blocks.BaseBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class WorldBlock extends BaseBlock {

    public final int veinSize;
    public final int veinCount;

    public WorldBlock(int veinSize, int veinCount) {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(3.0f, 6.0f));
        this.veinSize = veinSize;
        this.veinCount = veinCount;
    }
}