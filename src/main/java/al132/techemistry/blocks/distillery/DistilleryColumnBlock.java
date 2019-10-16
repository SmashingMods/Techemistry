package al132.techemistry.blocks.distillery;

import al132.techemistry.blocks.BaseBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DistilleryColumnBlock extends BaseBlock {
    public DistilleryColumnBlock() {
        super("distillery_column", Block.Properties.create(Material.IRON));
    }

}