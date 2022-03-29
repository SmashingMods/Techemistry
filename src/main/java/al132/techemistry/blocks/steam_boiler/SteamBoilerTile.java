package al132.techemistry.blocks.steam_boiler;

import al132.alib.tiles.FluidTile;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.blocks.steam_turbine.SteamTurbineTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

public class SteamBoilerTile extends BaseTile implements GuiTile, HeatTile, FluidTile {
    public SteamBoilerTile(BlockPos pos, BlockState state) {
        super(Registration.STEAM_BOILER_BE.get(), pos, state);
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


    public void tickServer() {
        if (level.isClientSide) return;
        if (level.getGameTime() % 5 == 0) {
            if (inputTank.getFluidAmount() >= 5 && heat.getHeatStored() >= 373.15) {
                BlockEntity above = level.getBlockEntity(getBlockPos().above());
                if (above instanceof SteamTurbineTile) {
                    int transferred = ((SteamTurbineTile) above).energy.receiveEnergy(100, true);
                    if (transferred > 0) ((SteamTurbineTile) above).energy.receiveEnergy(transferred, false);

                }
                inputTank.drain(5, IFluidHandler.FluidAction.EXECUTE);
            }
        }
        HeatHelper.balanceHeat(level, getBlockPos(), heat);
        setChanged();
        updateGUIEvery(5);

    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        inputTank.readFromNBT(compound.getCompound("inputTank"));
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(level, getBlockPos()));
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inputTank", inputTank.writeToNBT(new CompoundTag()));
        compound.putDouble("heat", heat.getHeatStored());
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public LazyOptional<IFluidHandler> getFluidHandler() {
        return fluidHolder;
    }

    @Override
    public Component getName() {
        return null;
    }
}