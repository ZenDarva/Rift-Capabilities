package com.gmail.zendarva.capabilities.API;

import net.minecraft.util.EnumFacing;

import java.util.Optional;

public interface ICapabilityProvider {

    public Optional<? extends ICapability> getCapability(ICapabilityContext context, Class<? extends ICapability> capability);
}
