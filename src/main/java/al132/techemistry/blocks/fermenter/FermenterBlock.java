package al132.techemistry.blocks.fermenter;

import al132.alib.blocks.ABaseTileBlock;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerTile;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberContainer;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberTile;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.List;

public class FermenterBlock extends ABaseTileBlock {
    public FermenterBlock() {
        super(Block.Properties.of(Material.METAL).strength(2.0f), FermenterTile.class, FermenterContainer.class);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, getter, tooltips, flagIn);
        tooltips.add(new TextComponent(I18n.get("block.techemistry.fermenter.tooltip")));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return (lvl, pos, blockState, t) -> {
            if (t instanceof FermenterTile) {
                ((FermenterTile) t).tickServer();
            }
        };
    }
}