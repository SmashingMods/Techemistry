package al132.techemistry.blocks.calcination_chamber;


import al132.alib.blocks.ABaseTileBlock;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.List;

public class CalcinationBlock extends ABaseTileBlock {
    public CalcinationBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL).strength(2.0f), CalcinationTile.class, CalcinationContainer.class);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, getter, tooltips, flagIn);
        tooltips.add(new TextComponent(I18n.get("block.techemistry.calcination_chamber.tooltip")));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return (lvl, pos, blockState, t) -> {
            if (t instanceof CalcinationTile) {
                ((CalcinationTile) t).tickServer();
            }
        };
    }
}