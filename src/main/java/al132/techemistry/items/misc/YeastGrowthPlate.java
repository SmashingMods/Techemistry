package al132.techemistry.items.misc;

import al132.techemistry.Ref;
import al132.techemistry.items.BaseItem;
import al132.techemistry.utils.TUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class YeastGrowthPlate extends BaseItem {
    public YeastGrowthPlate() {
        super("yeast_incubation_tray");
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        World world = entity.getEntityWorld();
        if (world.rand.nextInt(200) == 0) {
            BlockState state = world.getBlockState(entity.getPosition());
            if (state.getBlock() instanceof SlabBlock
                    && state.hasProperty(BlockStateProperties.WATERLOGGED)
                    && state.get(BlockStateProperties.WATERLOGGED)
                    && state.hasProperty(BlockStateProperties.SLAB_TYPE)
                    && state.get(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
                List<BlockState> surrounding = TUtils.getSurroundingBlocks(world, entity.getPosition());
                if (surrounding.stream().allMatch(x -> x.getBlock() != Blocks.WATER)) {
                    stack.shrink(1);
                    BlockPos pos = entity.getPosition();
                    ItemEntity yeast = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Ref.yeast));
                    world.addEntity(yeast);
                }
            }
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent("Toss in a shallow pool of water, then wait and yeast will grow"));
        tooltip.add(new StringTextComponent("(A single block of water with a slab on the bottom half, no surrounding water)"));
    }
}