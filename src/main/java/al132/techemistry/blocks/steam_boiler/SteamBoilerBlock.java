package al132.techemistry.blocks.steam_boiler;

import al132.techemistry.blocks.BaseTileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SteamBoilerBlock extends BaseTileBlock {

    public SteamBoilerBlock() {
        super("steam_boiler", SteamBoilerTile::new, Block.Properties.create(Material.IRON));
    }
}