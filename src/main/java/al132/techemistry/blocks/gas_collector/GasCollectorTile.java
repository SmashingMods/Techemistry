package al132.techemistry.blocks.gas_collector;

import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.utils.TUtils;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class GasCollectorTile extends BaseInventoryTile implements GuiTile, ITickableTileEntity {

    private int progressTicks = 0;

    public GasCollectorTile() {
        super(Ref.gasCollectorTile);
    }

    public ItemStack getOutputStack() {
        return getOutput().getStackInSlot(0);
    }

    private boolean canStackWithOutput(ItemStack item) {
        return getOutputStack().isEmpty() || getOutputStack().getItem() == item.getItem();
    }

    @Override
    public void tick() {
        if (world.isRemote) return;
        if (progressTicks % 100 == 0) {
            progressTicks = 0;
            if (getOutputStack().getCount() == getOutputStack().getMaxStackSize()) return;
            Block down1 = world.getBlockState(pos.down()).getBlock();
            Block down2 = world.getBlockState(pos.down(2)).getBlock();
            if (down1 instanceof AirBlock) {
                if (down2 instanceof FireBlock) {
                    ItemStack carbonDioxide = TUtils.toStack("carbon_dioxide");
                    if (canStackWithOutput(carbonDioxide)) { //Maybe "smoke" instead of co2?
                        getOutput().setOrIncrement(0, carbonDioxide);
                    }
                } else {
                    BlockPos corner1 = this.pos.down().north().west();
                    BlockPos corner2 = this.pos.down().south().east();
                    ItemStack methane = TUtils.toStack("methane");
                    if (canStackWithOutput(methane)) {
                        if (!world.getEntitiesWithinAABB(EntityType.COW, new AxisAlignedBB(corner1, corner2), (entity -> true)).isEmpty()) {
                            getOutput().setOrIncrement(0, methane);
                        }
                    }
                }
            } else if (down1.getBlock() instanceof LeavesBlock) {
                ItemStack oxygen = TUtils.toStack("oxygen", 2);
                if (canStackWithOutput(oxygen)) {
                    getOutput().setOrIncrement(0, oxygen);
                }
            } else if (down1.getBlock() instanceof CampfireBlock) {
                ItemStack carbonDioxide = TUtils.toStack("carbon_dioxide");
                if (canStackWithOutput(carbonDioxide)) {
                    getOutput().setOrIncrement(0, carbonDioxide);
                }
            }
        }
        progressTicks++;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        progressTicks = compound.getInt("progressTicks");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("progressTicks", progressTicks);
        return super.write(compound);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInv, PlayerEntity player) {
        return new GasCollectorContainer(i, world, pos, playerInv, player);
    }

    @Override
    public int getWidth() {
        return 175;
    }

    @Override
    public int getHeight() {
        return 183;
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 0);
    }

    @Override
    public int outputSlots() {
        return 1;
    }
}