package al132.techemistry.blocks.electrolyzer;

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
import al132.techemistry.items.parts.ElectrodeItem;
import al132.techemistry.utils.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class ElectrolyzerTile extends BaseInventoryTile
        implements INamedContainerProvider, ITickableTileEntity, EnergyTile, HeatTile, GuiTile {

    public static final int MAX_ENERGY = 10000;
    private Optional<ElectrolyzerRecipe> currentRecipe = Optional.empty();
    protected IHeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    protected int progressTicks = 0;

    public static final int TICKS_PER_OPERATION = 100;
    public static final int ENERGY_PER_TICK = 0;

    public ElectrolyzerTile() {
        super(Ref.electrolyzerTile);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInv, PlayerEntity player) {
        return new ElectrolyzerContainer(i, world, pos, playerInv, player);
    }

    public void updateRecipe() {
        this.currentRecipe = ElectrolyzerRegistry.getRecipeForInput(getInput1Stack(), getInput2Stack());
    }

    @Override
    public void tick() {
        if (world.isRemote) return;
        updateRecipe();
        if (canProcess()) process();
        HeatHelper.balanceHeat(world, pos, heat);
        markDirtyClient();
    }

    private boolean canProcess() {
        return currentRecipe.isPresent()
                && heat.getHeatStored() >= currentRecipe.get().heat
                && energy.getEnergyStored() >= ENERGY_PER_TICK
                && !getAnode().isEmpty()
                && !getCathode().isEmpty()
                && Utils.canStack(currentRecipe.get().output1, getOutput().getStackInSlot(0))
                && Utils.canStack(currentRecipe.get().output2, getOutput().getStackInSlot(1))
                && Utils.canStack(currentRecipe.get().output3, getOutput().getStackInSlot(2));
    }

    private void process() {
        progressTicks++;
        if (progressTicks >= TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().output1.copy());
            getOutput().setOrIncrement(1, currentRecipe.get().output2.copy());
            getOutput().setOrIncrement(2, currentRecipe.get().output3.copy());
            getInput().getStackInSlot(0).shrink(currentRecipe.get().input1.getMatchingStacks()[0].getCount());
            if (!currentRecipe.get().input2.hasNoMatchingItems()) {
                getInput().getStackInSlot(1).shrink(currentRecipe.get().input2.getMatchingStacks()[0].getCount());
            }
            getAnode().attemptDamageItem(1, world.rand, null);
            if (getAnode().getDamage() >= getAnode().getMaxDamage()) {
                getInput().setStackInSlot(2, ItemStack.EMPTY);
            }
            getCathode().attemptDamageItem(1, world.rand, null);
            if (getCathode().getDamage() >= getCathode().getMaxDamage()) {
                getInput().setStackInSlot(3, ItemStack.EMPTY);
            }
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.progressTicks = compound.getInt("progressTicks");
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(world, pos));
        }
        updateRecipe();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("progressTicks", progressTicks);
        compound.putDouble("heat", heat.getHeatStored());
        return super.write(compound);
    }

    public ItemStack getInput1Stack() {
        return this.getInput().getStackInSlot(0);
    }

    public ItemStack getInput2Stack() {
        return this.getInput().getStackInSlot(1);
    }

    public ItemStack getAnode() { //positive
        return this.getInput().getStackInSlot(2);
    }

    public ItemStack getCathode() { //negative
        return this.getInput().getStackInSlot(3);
    }


    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 4) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0 && ElectrolyzerRegistry.hasRecipe(stack)) return true;
                if (slot == 1) return true; //TODO
                else if ((slot == 2 || slot == 3) && stack.getItem() instanceof ElectrodeItem) return true;
                return false;
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
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public int outputSlots() {
        return 3;
    }
}
