package al132.techemistry.blocks.distillery;

import al132.techemistry.blocks.BaseBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;


public class DistilleryColumnBlock extends BaseBlock {
    public DistilleryColumnBlock() {
        super(Block.Properties.of(Material.METAL).strength(2.0f));
    }

}