package com.gmail.zendarva.capabilities.API;

import net.minecraft.util.EnumFacing;

public interface ICapabilityProvider {

    public boolean hasCapability(EnumFacing direction, Class<? extends ICapability> capability);
    public ICapability getCapability(EnumFacing direction, Class<? extends ICapability> capability);

}
