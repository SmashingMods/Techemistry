package com.smashingmods.techemistry.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class Capabilities {
    public static Capability<HeatCapability> HEAT_HANDLER = CapabilityManager.get(new CapabilityToken<>(){});
}
