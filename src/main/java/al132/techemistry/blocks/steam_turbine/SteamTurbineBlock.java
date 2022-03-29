package al132.techemistry.blocks.steam_turbine;

import al132.alib.blocks.ABaseTileBlock;
import al132.techemistry.blocks.steam_boiler.SteamBoilerTile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class SteamTurbineBlock extends ABaseTileBlock {

    public SteamTurbineBlock() {
        super(Block.Properties.of(Material.METAL).strength(2.0f), SteamTurbineTile.class, SteamTurbineContainer.class);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return (lvl, pos, blockState, t) -> {
            if (t instanceof SteamTurbineTile) {
                ((SteamTurbineTile) t).tickServer();
            }
        };
    }
}