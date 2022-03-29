package al132.techemistry.blocks;

import al132.techemistry.capabilities.heat.CapabilityHeat;
import al132.alib.tiles.ABaseTile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BaseTile extends ABaseTile {

    public BaseTile(BlockEntityType<?> BlockEntityTypeIn, BlockPos pos, BlockState state) {
        super(BlockEntityTypeIn, pos, state);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityHeat.HEAT && this instanceof HeatTile) {
            return ((HeatTile) this).getHeat().cast();
        } else return super.getCapability(cap, side);
    }
}