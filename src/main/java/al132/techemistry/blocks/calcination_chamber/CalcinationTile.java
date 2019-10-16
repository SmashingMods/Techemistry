package al132.techemistry.blocks.calcination_chamber;

import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.blocks.gas_collector.GasCollectorTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.utils.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class CalcinationTile extends BaseInventoryTile
        implements INamedContainerProvider, ITickableTileEntity, HeatTile, GuiTile {

    protected Optional<CalcinationRecipe> currentRecipe = Optional.empty();
    protected IHeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    protected int progressTicks = 0;

    public static final int TICKS_PER_OPERATION = 100;

    public CalcinationTile() {
        super(Ref.calcinationTile);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInv, PlayerEntity player) {
        return new CalcinationContainer(i, world, pos, playerInv, player);
    }

    public void updateRecipe() {
        this.currentRecipe = CalcinationRegistry.getRecipeForInput(getInputStack());
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
                && getInputStack().getCount() >= currentRecipe.get().input.getMatchingStacks()[0].getCount()
                && Utils.canStack(currentRecipe.get().output, getOutputStack())
                && Utils.canStack(currentRecipe.get().output2, getOutput2Stack());
    }

    private void process() {
        progressTicks++;
        if (progressTicks >= TICKS_PER_OPERATION) {
            TileEntity temp = world.getTileEntity(pos.up());
            if (temp instanceof GasCollectorTile) {
                GasCollectorTile collectorTile = (GasCollectorTile) temp;
                ItemStack s = collectorTile.getOutputStack();
                if (s.isEmpty() || s.getItem() == currentRecipe.get().gas.getItem()) {
                    collectorTile.getOutput().setOrIncrement(0, currentRecipe.get().gas.copy());
                }
            }
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().output.copy());
            getOutput().setOrIncrement(1, currentRecipe.get().output2.copy());
            getInput().getStackInSlot(0).shrink(currentRecipe.get().input.getMatchingStacks()[0].getCount());
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

    public ItemStack getInputStack() {
        return this.getInput().getStackInSlot(0);
    }

    public ItemStack getOutputStack() {
        return this.getOutput().getStackInSlot(0);
    }

    public ItemStack getOutput2Stack() {
        return this.getOutput().getStackInSlot(1);
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return CalcinationRegistry.hasRecipe(stack);
            }
        };
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public int outputSlots() {
        return 2;
    }
}