package al132.techemistry.blocks.world;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class SulfurOre extends WorldBlock {
    public SulfurOre() {
        super("sulfur_ore", 18, 8);
    }

    protected int getExperience(Random rand) {
        return MathHelper.nextInt(rand, 0, 2);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}