package al132.techemistry.blocks;

import al132.techemistry.capabilities.heat.CapabilityHeat;
import al132.alib.tiles.ABaseTile;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BaseTile extends ABaseTile implements INamedContainerProvider {

    public BaseTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityHeat.HEAT_CAP && this instanceof HeatTile) {
            return ((HeatTile) this).getHeat().cast();
        } else return super.getCapability(cap, side);
    }
}