package com.gmail.zendarva.api.capabilities;




import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface ICapabilityProvider {

    @Nonnull
    public Optional<? extends ICapability> queryCapability(ICapabilityContext context, Class<? extends ICapability> capability);
    @Nullable
    public ICapability getCapability(ICapabilityContext context, Class<? extends ICapability> capability);
    @Nonnull
    public List<? extends ICapability> getCapabilities(ICapabilityContext context, Class<? extends ICapability> capability);
}
