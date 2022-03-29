package al132.techemistry.blocks.steam_boiler;

import al132.alib.blocks.ABaseTileBlock;
import al132.techemistry.blocks.solid_fuel_heater.SolidHeaterTile;
import al132.techemistry.blocks.steam_turbine.SteamTurbineContainer;
import al132.techemistry.blocks.steam_turbine.SteamTurbineTile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class SteamBoilerBlock extends ABaseTileBlock {

    public SteamBoilerBlock() {
        super(Block.Properties.of(Material.METAL).strength(2.0f), SteamBoilerTile.class, SteamBoilerContainer.class);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return (lvl, pos, blockState, t) -> {
            if (t instanceof SteamBoilerTile) {
                ((SteamBoilerTile) t).tickServer();
            }
        };
    }
}