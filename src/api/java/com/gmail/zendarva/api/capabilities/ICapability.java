package com.gmail.zendarva.api.capabilities;

import net.minecraft.nbt.NBTTagCompound;

public interface ICapability {

    //These functions will not be called automatically.
    //You are responsible for saving/loading your own capabilities.
    public void readFromNBT(NBTTagCompound tag);
    public NBTTagCompound writeToNBT(NBTTagCompound tag);
    public boolean matches(ICapabilityContext context);
    public boolean isValid();
    public void invalidate();
}
