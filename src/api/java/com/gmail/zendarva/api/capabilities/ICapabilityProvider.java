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

    //Add a proxy that would like to override this blocks capabilities.  Could be used for multiparts, walls to block
    //transfers, capability extenders (If you're creative...)
    public void addProxyCapability(ICapability capability);
    //Unregister a capability override.
    public void removeProxyCapability(ICapability capability);

    //This is called while your tileEntity is being removed.  Use it to mark the Capabilities your TileEntity provides
    //as invalid.
    public void invalidateCapabilities();
}
