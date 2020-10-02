package al132.techemistry.blocks.solar_heater;

import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.utils.TUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.List;

public class SolarHeaterTile extends BaseInventoryTile implements ITickableTileEntity, GuiTile, HeatTile {
    public SolarHeaterTile() {
        super(Ref.solarHeaterTile);
    }

    protected HeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public final LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    protected int fuelTicksRemaining = 0;
    protected int currentFuelMaxTicks = 0;


    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new SolarHeaterContainer(id, world, pos, inv, player);
    }


    @Override
    public void tick() {
        if (world.isRemote) return;
        if (world.getGameTime() % 20 == 0) {
            if (canGenerateHeat()) generateHeat();
            List<IHeatStorage> tiles = TUtils.getSurroundingHeatTiles(world, pos);
            for (IHeatStorage tile : tiles) {
                if (tile.getHeatStored() + 2.0 < this.heat.getHeatStored()) {
                    tile.receiveHeat(this.heat.extractHeat(2.0, false), false);
                }
            }
            HeatHelper.balanceHeat(world, pos, heat);
            markDirtyGUI();
        }
    }

    public boolean canGenerateHeat() {
        return world.isDaytime() && world.canBlockSeeSky(pos.up()) && heat.getHeatStored() < 400;
    }

    public void generateHeat() {
        //https://www.wolframalpha.com/input/?i=%28-.25x%5E1.5+%2B+2000%29%2F1000+from+x+%3D+0+to+x+%3D+450
        double amount = (2000 - Math.pow(.25 * heat.getHeatStored(), 1.5)) / 1000;
        if (amount > 0) heat.receiveHeat(amount, false);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        heat = new HeatStorage(compound.getDouble("heat"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putDouble("heat", heat.getHeatStored());
        return super.write(compound);
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 0);
    }

    @Override
    public int outputSlots() {
        return 0;
    }
}