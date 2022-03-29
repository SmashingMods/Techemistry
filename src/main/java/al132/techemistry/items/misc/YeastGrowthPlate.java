package al132.techemistry.items.misc;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.items.BaseItem;
import al132.techemistry.utils.TUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;


import javax.annotation.Nullable;
import java.util.List;

public class YeastGrowthPlate extends BaseItem {
    public YeastGrowthPlate() {
        super("yeast_incubation_tray");
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        Level level = entity.getLevel();
        if (level.random.nextInt(200) == 0) {
            BlockState state = level.getBlockState(new BlockPos(entity.getPosition(0)));
            if (state.getBlock() instanceof SlabBlock
                    && state.hasProperty(BlockStateProperties.WATERLOGGED)
                    && state.getValue(BlockStateProperties.WATERLOGGED)
                    && state.hasProperty(BlockStateProperties.SLAB_TYPE)
                    && state.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
                List<BlockState> surrounding = TUtils.getSurroundingBlocks(level, new BlockPos(entity.getPosition(0)));
                if (surrounding.stream().allMatch(x -> x.getBlock() != Blocks.WATER)) {
                    stack.shrink(1);
                    BlockPos pos = new BlockPos(entity.getPosition(0));
                    ItemEntity yeast = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Registration.YEAST_ITEM.get()));
                    level.addFreshEntity(yeast);
                }
            }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, levelIn, tooltips, flagIn);
        tooltips.add(new TextComponent("Toss in a shallow pool of water, then wait and yeast will grow"));
        tooltips.add(new TextComponent("(A single block of water with a slab on the bottom half, no surrounding water)"));
    }
}