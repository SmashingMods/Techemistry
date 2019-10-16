package al132.techemistry.blocks.steam_turbine;

import al132.techemistry.blocks.BaseTileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SteamTurbineBlock extends BaseTileBlock {

    public SteamTurbineBlock() {
        super("steam_turbine", SteamTurbineTile::new, Block.Properties.create(Material.IRON));
    }
}