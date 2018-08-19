package com.gmail.zendarva.capabilities.API;

import net.minecraft.nbt.NBTTagCompound;

public interface ICapability {

    //These functions will not be called automatically.
    //You are responsible for saving/loading your own capabilities.
    public void readFromNBT(NBTTagCompound tag);
    public void writeToNBT(NBTTagCompound tag);
}