package al132.techemistry.blocks.reaction_chamber;

import al132.alib.tiles.CustomEnergyStorage;
import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.EnergyTile;
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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class ReactionChamberTile extends BaseInventoryTile implements ITickableTileEntity, EnergyTile, GuiTile, HeatTile {

    public final static int MAX_ENERGY = 10000;
    public int progressTicks = 0;
    public final static int TICKS_PER_OPERATION = 100;
    public final static int ENERGY_PER_TICK = 0;
    private Optional<ReactionChamberRecipe> currentRecipe = Optional.empty();
    protected IHeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    public ReactionChamberTile() {
        super(Ref.reactionChamberTile);
    }

    public void updateRecipe() {
        currentRecipe = ReactionChamberRegistry.getRecipeForInput(getInput());
    }

    @Override
    public void tick() {
        if (world.isRemote) return;
        updateRecipe();
        if (canProcess()) process();
        HeatHelper.balanceHeat(world, pos, heat);
        markDirtyGUI();
    }

    private boolean canProcess() {
        return currentRecipe.isPresent()
                && heat.getHeatStored() >= currentRecipe.get().minimumHeat
                && energy.getEnergyStored() >= ENERGY_PER_TICK
                && Utils.canStack(currentRecipe.get().output0, getOutput().getStackInSlot(0))
                && Utils.canStack(currentRecipe.get().output1, getOutput().getStackInSlot(1))
                && Utils.canStack(currentRecipe.get().output2, getOutput().getStackInSlot(2));
    }

    private void process() {
        progressTicks++;
        energy.extractEnergy(ENERGY_PER_TICK, false);
        if (progressTicks >= TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().output0.copy());
            getOutput().setOrIncrement(1, currentRecipe.get().output1.copy());
            getOutput().setOrIncrement(2, currentRecipe.get().output2.copy());
            getInput().getStackInSlot(0).shrink(currentRecipe.get().input0.getMatchingStacks()[0].getCount());
            if (currentRecipe.get().input1.getMatchingStacks().length > 0) {
                getInput().getStackInSlot(1).shrink(currentRecipe.get().input1.getMatchingStacks()[0].getCount());
            }
            if (currentRecipe.get().input2.getMatchingStacks().length > 0) {
                getInput().getStackInSlot(2).shrink(currentRecipe.get().input2.getMatchingStacks()[0].getCount());
            }
        }
        markDirtyClient();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.progressTicks = compound.getInt("progressTicks");
        //this.energy = new EnergyStorage(MAX_ENERGY, MAX_ENERGY, MAX_ENERGY, compound.getInt("energy"));
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(world, pos));
        }
        updateRecipe();
        markDirtyGUI();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("progressTicks", progressTicks);
        //compound.putInt("energy", this.energy.getEnergyStored());
        compound.putDouble("heat", heat.getHeatStored());
        return super.write(compound);
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
                return ReactionChamberRegistry.hasRecipe(stack, slot) && itemNotInOtherSlots(slot, stack);
            }

            @Override
            public void onContentsChanged(int slot) {
                updateRecipe();
                markDirtyGUI();
            }
        };
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInv, PlayerEntity player) {
        return new ReactionChamberContainer(i, world, pos, playerInv, player);
    }

    @Override
    public IEnergyStorage initEnergy() {
        return new CustomEnergyStorage(MAX_ENERGY) {
            @Override
            public void onEnergyChanged() {
                markDirtyGUI();
            }
        };
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
}