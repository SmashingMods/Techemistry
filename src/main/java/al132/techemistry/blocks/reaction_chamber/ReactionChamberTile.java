package al132.techemistry.blocks.reaction_chamber;

import al132.alib.tiles.CustomEnergyStorage;
import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.EnergyTile;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.utils.TUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class ReactionChamberTile extends BaseInventoryTile implements EnergyTile, GuiTile, HeatTile {

    public final static int MAX_ENERGY = 10000;
    public int progressTicks = 0;
    public final static int TICKS_PER_OPERATION = 100;
    public final static int ENERGY_PER_TICK = 0;
    private Optional<ReactionChamberRecipe> currentRecipe = Optional.empty();
    protected IHeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    public ReactionChamberTile(BlockPos pos, BlockState state) {
        super(Registration.REACTION_CHAMBER_BE.get(), pos, state);
    }

    public void updateRecipe() {
        currentRecipe = ReactionChamberRegistry.getRecipeForInput(level, getInput());
    }

    public void tickServer() {
        if (level.isClientSide) return;
        updateRecipe();
        if (canProcess()) process();
        HeatHelper.balanceHeat(level, getBlockPos(), heat);
        setChanged();
        updateGUIEvery(5);

    }

    private boolean canProcess() {
        return currentRecipe.isPresent()
                && heat.getHeatStored() >= currentRecipe.get().minimumHeat
                && energy.getEnergyStored() >= ENERGY_PER_TICK
                && TUtils.canStack(currentRecipe.get().output0, getOutput().getStackInSlot(0))
                && TUtils.canStack(currentRecipe.get().output1, getOutput().getStackInSlot(1))
                && TUtils.canStack(currentRecipe.get().output2, getOutput().getStackInSlot(2))
                && TUtils.isQuantityAdequate(getInput().getStackInSlot(0), currentRecipe.get().input0)
                && TUtils.isQuantityAdequate(getInput().getStackInSlot(1), currentRecipe.get().input1)
                && TUtils.isQuantityAdequate(getInput().getStackInSlot(2), currentRecipe.get().input2);

    }

    private void process() {
        progressTicks++;
        energy.extractEnergy(ENERGY_PER_TICK, false);
        if (progressTicks >= TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().output0.copy());
            getOutput().setOrIncrement(1, currentRecipe.get().output1.copy());
            getOutput().setOrIncrement(2, currentRecipe.get().output2.copy());
            getInput().getStackInSlot(0).shrink(currentRecipe.get().input0.getItems()[0].getCount());
            if (currentRecipe.get().input1.getItems().length > 0) {
                getInput().getStackInSlot(1).shrink(currentRecipe.get().input1.getItems()[0].getCount());
            }
            if (currentRecipe.get().input2.getItems().length > 0) {
                getInput().getStackInSlot(2).shrink(currentRecipe.get().input2.getItems()[0].getCount());
            }
        }
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.progressTicks = compound.getInt("progressTicks");
        //this.energy = new EnergyStorage(MAX_ENERGY, MAX_ENERGY, MAX_ENERGY, compound.getInt("energy"));
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(level, getBlockPos()));
        }
        updateRecipe();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("progressTicks", progressTicks);
        //compound.putInt("energy", this.energy.getEnergyStored());
        compound.putDouble("heat", heat.getHeatStored());
    }


    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 3) {
            private boolean itemNotInOtherSlots(int slot, ItemStack stack) {
                for (int i = 0; i < 3; i++) {
                    if (i != slot) {
                        if (this.getStackInSlot(i).getItem() == stack.getItem()) return false;
                    }
                }
                return true;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return ReactionChamberRegistry.hasRecipe(level, stack, slot) && itemNotInOtherSlots(slot, stack);
            }

            @Override
            public void onContentsChanged(int slot) {
                updateRecipe();
            }
        };
    }

    @Override
    public IEnergyStorage initEnergy() {
        return new CustomEnergyStorage(MAX_ENERGY);
    }

    @Override
    public IEnergyStorage getEnergy() {
        return energy;
    }

    @Override
    public int outputSlots() {
        return 3;
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public Component getName() {
        return null;
    }
}