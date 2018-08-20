package com.gmail.zendarva.api.capabilities;

import java.util.Optional;

public interface ICapabilityProvider {

    public Optional<? extends ICapability> getCapability(ICapabilityContext context, Class<? extends ICapability> capability);
}
