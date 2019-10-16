package al132.techemistry.capabilities.heat;


import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityHeat {

    @CapabilityInject(IHeatStorage.class)
    public static Capability<IHeatStorage> HEAT_CAP = null;


    public static void register() {
        CapabilityManager.INSTANCE.register(IHeatStorage.class, new Capability.IStorage<IHeatStorage>() {

            @Nullable
            @Override
            public INBT writeNBT(Capability<IHeatStorage> capability, IHeatStorage instance, Direction side) {
                return new DoubleNBT(instance.getHeatStored());
            }

            @Override
            public void readNBT(Capability<IHeatStorage> capability, IHeatStorage instance, Direction side, INBT nbt) {
                if (!(instance instanceof HeatStorage))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                ((HeatStorage) instance).heat = ((DoubleNBT) nbt).getDouble();
            }
        }, () -> new HeatStorage(HeatHelper.ROOM_TEMP));
    }

}