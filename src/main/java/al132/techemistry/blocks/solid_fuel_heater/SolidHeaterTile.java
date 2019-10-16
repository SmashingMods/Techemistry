package al132.techemistry.blocks.solid_fuel_heater;

import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.utils.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SolidHeaterTile extends BaseInventoryTile implements ITickableTileEntity, GuiTile, HeatTile {
    public SolidHeaterTile() {
        super(Ref.solidHeaterTile);
    }

    protected HeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public final LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    protected int fuelTicksRemaining = 0;
    protected int currentFuelMaxTicks = 0;


    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new SolidHeaterContainer(id, world, pos, inv, player);
    }


    @Override
    public void tick() {
        if (world.isRemote) return;
        if (this.fuelTicksRemaining > 0) {
            //https://www.wolframalpha.com/input/?i=plot+%283x%5E%28-.2%29%29%5E2+from+x+%3D+0+to+x+%3D+1000
            double toReceive = Math.pow(3 * Math.pow(heat.getHeatStored(), -.2), 5);
            this.heat.receiveHeat(toReceive, false);
            this.fuelTicksRemaining--;
            if (this.fuelTicksRemaining == 0) currentFuelMaxTicks = 0;
        } else {
            ItemStack input = getInput().getStackInSlot(0);
            if (!input.isEmpty()) {
                this.fuelTicksRemaining = ForgeHooks.getBurnTime(input);
                this.currentFuelMaxTicks = fuelTicksRemaining;
                input.shrink(1);
            }
        }
        List<IHeatStorage> tiles = Utils.getSurroundingHeatTiles(world, pos);
        //System.out.println("tile.size = " + tiles.size());
        for (IHeatStorage tile : tiles) {
            if (tile.getHeatStored() + 2.0 < this.heat.getHeatStored()) {
                //System.out.println(world.getGameTime());
                tile.receiveHeat(this.heat.extractHeat(2.0, false), false);
            }
        }
        HeatHelper.balanceHeat(world, pos, heat);
        markDirtyGUI();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.fuelTicksRemaining = compound.getInt("fuelTicks");
        this.currentFuelMaxTicks = compound.getInt("currentFuelMaxTicks");
        heat = new HeatStorage(compound.getDouble("heat"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("fuelTicks", this.fuelTicksRemaining);
        compound.putInt("currentFuelMaxTicks", this.currentFuelMaxTicks);
        compound.putDouble("heat", heat.getHeatStored());
        return super.write(compound);
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (ForgeHooks.getBurnTime(stack) > 0) return super.isItemValid(slot, stack);
                else return false;
            }
        };
    }

    @Override
    public int outputSlots() {
        return 0;
    }
}