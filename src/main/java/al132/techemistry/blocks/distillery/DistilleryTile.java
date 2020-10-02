package al132.techemistry.blocks.distillery;

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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class DistilleryTile extends BaseInventoryTile implements HeatTile, GuiTile, ITickableTileEntity {//}, FluidTile {

    protected IHeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    protected LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    public Optional<DistilleryRecipe> currentRecipe = Optional.empty();

    protected int progressTicks = 0;
    public static final int TICKS_PER_OPERATION = 200;

    public DistilleryTile() {
        super(Ref.distilleryTile);
    }

    @Override
    public void tick() {
        if (world.isRemote) return;
        updateRecipe();
        if (canProcess()) process();
        HeatHelper.balanceHeat(world, pos, heat);
        markDirtyGUI();
    }

    public ItemStack getInputStack() {
        return getInput().getStackInSlot(0);
    }

    public ItemStack getOutput1Stack() {
        return getOutput().getStackInSlot(0);
    }

    public ItemStack getOutput2Stack() {
        return getOutput().getStackInSlot(1);
    }

    private boolean canProcess() {
        return isStructureFormed()
                && currentRecipe.isPresent()
                && this.heat.getHeatStored() >= currentRecipe.get().minimumHeat
                && TUtils.canStack(currentRecipe.get().getRecipeOutput(), getOutput1Stack())
                && TUtils.canStack(currentRecipe.get().output2, getOutput2Stack());
    }

    private void updateRecipe() {
        this.currentRecipe = DistilleryRegistry.getRecipeForInput(world, getInputStack());
    }

    private void process() {
        progressTicks++;
        if (progressTicks >= TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().getRecipeOutput().copy());
            getOutput().setOrIncrement(1, currentRecipe.get().output2.copy());
            getInput().getStackInSlot(0).shrink(1);
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        this.progressTicks = compound.getInt("progressTicks");
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(world, pos));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("progressTicks", progressTicks);
        compound.putDouble("heat", heat.getHeatStored());
        return super.write(compound);
    }


    public boolean isStructureFormed() {
        for (int i = 1; i <= 3; i++) {
            if (world.getBlockState(pos.up(i)).getBlock() != Ref.distilleryColumn) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return DistilleryRegistry.inputHasRecipe(world, stack);
            }
        };
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new DistilleryContainer(id, world, pos, inv, player);
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