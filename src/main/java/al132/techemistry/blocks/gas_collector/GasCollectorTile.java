package al132.techemistry.blocks.gas_collector;

import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.utils.TUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;


import javax.annotation.Nullable;

public class GasCollectorTile extends BaseInventoryTile implements GuiTile {

    private int progressTicks = 0;

    public GasCollectorTile(BlockPos pos, BlockState state) {
        super(Registration.GAS_COLLECTOR_BE.get(), pos, state);
    }

    public ItemStack getOutputStack() {
        return getOutput().getStackInSlot(0);
    }

    private boolean canStackWithOutput(ItemStack item) {
        return getOutputStack().isEmpty() || getOutputStack().getItem() == item.getItem();
    }

    public void tickServer() {
        if (level.isClientSide) return;
        if (progressTicks % 200 == 0) {
            progressTicks = 0;
            if (getOutputStack().getCount() == getOutputStack().getMaxStackSize()) return;
            Block down1 = level.getBlockState(getBlockPos().below()).getBlock();
            Block down2 = level.getBlockState(getBlockPos().below(2)).getBlock();
            if (down1 instanceof AirBlock) {
                if (down2 instanceof FireBlock) {
                    ItemStack carbonDioxide = TUtils.toStack("carbon_dioxide");
                    if (canStackWithOutput(carbonDioxide)) { //Maybe "smoke" instead of co2?
                        getOutput().setOrIncrement(0, carbonDioxide);
                    }
                } else {
                    BlockPos corner1 = this.getBlockPos().below().north().west();
                    BlockPos corner2 = this.getBlockPos().below().south().east();
                    ItemStack methane = TUtils.toStack("methane");
                    if (canStackWithOutput(methane)) {
                        if (!level.getEntitiesOfClass(Cow.class, AABB.of(BoundingBox.fromCorners(corner1, corner2)), (entity -> true)).isEmpty()) {
                            getOutput().setOrIncrement(0, methane);
                        }
                    }
                }
            } else if (down1 instanceof LeavesBlock) {
                ItemStack oxygen = TUtils.toStack("oxygen", 2);
                if (canStackWithOutput(oxygen)) {
                    getOutput().setOrIncrement(0, oxygen);
                }
            } else if (down1 instanceof CampfireBlock) {
                ItemStack carbonDioxide = TUtils.toStack("carbon_dioxide");
                if (canStackWithOutput(carbonDioxide)) {
                    getOutput().setOrIncrement(0, carbonDioxide);
                }
            }
        }
        setChanged();
        progressTicks++;
        updateGUIEvery(5);

    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        progressTicks = compound.getInt("progressTicks");
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("progressTicks", progressTicks);
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

    @Override
    public Component getName() {
        return null;
    }
}