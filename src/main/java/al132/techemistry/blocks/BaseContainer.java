package al132.techemistry.blocks;

import al132.alib.container.ABaseContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseContainer extends ABaseContainer {
    public PlayerEntity player;

    public BaseContainer(ContainerType<?> type, int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player, int slotCount) {
        super(type, id, world, pos, playerInv, player, slotCount);
        this.player = player;
    }
}
