package com.smashingmods.techemistry.common.capability;

import net.minecraft.nbt.CompoundTag;

public class HeatHandler implements HeatCapability {

    private int heat;
    private final int maxHeat;

    public HeatHandler(int pMaxHeat) {
        this.maxHeat = pMaxHeat;
    }

    @Override
    public int getHeat() {
        return heat;
    }

    @Override
    public void setHeat(int pHeat) {
        this.heat = pHeat;
    }

    public int getMaxHeat() {
        return maxHeat;
    }

    public void incrementHeat(int pHeat) {
        if (heat + pHeat <= maxHeat) {
            heat += pHeat;
        } else {
            heat = maxHeat;
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt("heat", heat);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag pTag) {
        this.heat = pTag.getInt("heat");
    }
}
