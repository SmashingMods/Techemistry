package com.smashingmods.techemistry.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface HeatCapability extends INBTSerializable<CompoundTag> {

    int getHeat();

    void setHeat(int pHeat);

    void incrementHeat(int pHeat);

    int getMaxHeat();
}
