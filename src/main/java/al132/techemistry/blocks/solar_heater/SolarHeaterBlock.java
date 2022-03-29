package al132.techemistry.blocks.solar_heater;

import al132.alib.blocks.ABaseTileBlock;
import al132.techemistry.blocks.smeltery.SmelteryTile;
import al132.techemistry.blocks.steam_turbine.SteamTurbineContainer;
import al132.techemistry.blocks.steam_turbine.SteamTurbineTile;
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

public class SolarHeaterBlock extends ABaseTileBlock {

    public SolarHeaterBlock() {
        super(Block.Properties.of(Material.METAL).strength(2.0f), SolarHeaterTile.class, SolarHeaterContainer.class);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, getter, tooltips, flagIn);
        tooltips.add(new TextComponent(I18n.get("block.techemistry.solar_heater.tooltip")));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return (lvl, pos, blockState, t) -> {
            if (t instanceof SolarHeaterTile) {
                ((SolarHeaterTile) t).tickServer();
            }
        };
    }
}