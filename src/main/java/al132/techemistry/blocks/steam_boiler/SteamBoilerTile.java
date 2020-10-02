package al132.techemistry.blocks.steam_boiler;

import al132.alib.tiles.FluidTile;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.blocks.steam_turbine.SteamTurbineTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

public class SteamBoilerTile extends BaseTile implements ITickableTileEntity, GuiTile, HeatTile, FluidTile {
    public SteamBoilerTile() {
        super(Ref.steamBoilerTile);
    }

    protected HeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    private final LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);
    protected FluidTank inputTank = new FluidTank(10000) {
        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() instanceof WaterFluid;
        }
    };
    protected LazyOptional<IFluidHandler> fluidHolder = LazyOptional.of(() -> inputTank);

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new SteamBoilerContainer(id, world, pos, inv, player);
    }

    @Override
    public void tick() {
        if (world.isRemote) return;
        if (world.getGameTime() % 5 == 0) {
            if (inputTank.getFluidAmount() >= 5 && heat.getHeatStored() >= 373.15) {
                TileEntity above = world.getTileEntity(pos.up());
                if (above instanceof SteamTurbineTile) {
                    int transferred = ((SteamTurbineTile) above).energy.receiveEnergy(100, true);
                    if (transferred > 0) ((SteamTurbineTile) above).energy.receiveEnergy(transferred, false);

                }
                inputTank.drain(5, IFluidHandler.FluidAction.EXECUTE);
            }
        }
        HeatHelper.balanceHeat(world, pos, heat);
        markDirtyGUI();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        inputTank.readFromNBT(compound.getCompound("inputTank"));
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(world, pos));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inputTank", inputTank.writeToNBT(new CompoundNBT()));
        compound.putDouble("heat", heat.getHeatStored());
        return super.write(compound);
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public LazyOptional<IFluidHandler> getFluidHandler() {
        return fluidHolder;
    }
}