package al132.techemistry.blocks.froth_flotation_chamber;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FrothFlotationContainer extends ABaseContainer {

    public FrothFlotationContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.frothFlotationContainer.get(), id, level, pos, playerInv, 4);
        FrothFlotationTile flotationTile = ((FrothFlotationTile) tile);
        addSlot(new SlotItemHandler(flotationTile.getInput(), 0, 73, 42));
        addSlot(new SlotItemHandler(flotationTile.getInput(), 1, 73, 60));
        addSlot(new SlotItemHandler(flotationTile.getOutput(), 0, 139, 42));
        addSlot(new SlotItemHandler(flotationTile.getOutput(), 1, 139, 60));
        addPlayerSlots();
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}