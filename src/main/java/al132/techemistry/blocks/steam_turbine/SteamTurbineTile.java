package al132.techemistry.blocks.steam_turbine;

import al132.alib.tiles.CustomEnergyStorage;
import al132.alib.tiles.EnergyTile;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseTile;
import al132.techemistry.utils.TUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.Optional;

public class SteamTurbineTile extends BaseTile implements GuiTile, EnergyTile {
    public SteamTurbineTile(BlockPos pos, BlockState state) {
        super(Registration.STEAM_TURBINE_BE.get(), pos, state);
    }

    public static final int MAX_ENERGY = 100000;


    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.energy = new EnergyStorage(MAX_ENERGY, MAX_ENERGY, MAX_ENERGY, compound.getInt("energy"));
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("energy", energy.getEnergyStored());
    }


    public void tickServer() {
        if (this.level.isClientSide) return;
        //energy.receiveEnergy(1, false);
        distributeEnergy();
        setChanged();
        updateGUIEvery(5);
    }

    private void distributeEnergy() {
        Optional<IEnergyStorage> neighbors = TUtils.getSurroundingEnergyTiles(level, getBlockPos()).stream()
                .filter(LazyOptional::isPresent)
                .map(x -> x.orElse(null))
                .filter(x -> x.getEnergyStored() < x.getMaxEnergyStored())
                .findFirst();
        neighbors.ifPresent(x -> {
            int transferred = energy.extractEnergy(x.receiveEnergy(100, true), true);
            energy.extractEnergy(transferred, false);
            x.receiveEnergy(transferred, false);
        });
    }

    @Override
    public IEnergyStorage initEnergy() {
        return new CustomEnergyStorage(MAX_ENERGY);
    }

    @Override
    public IEnergyStorage getEnergy() {
        return this.energy;
    }

    @Override
    public Component getName() {
        return null;
    }
}