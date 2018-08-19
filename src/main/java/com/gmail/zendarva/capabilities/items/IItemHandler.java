package com.gmail.zendarva.capabilities.items;

import com.gmail.zendarva.capabilities.API.ICapability;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;

public interface IItemHandler extends ICapability, IInventory {

    public void lockSlot(int slot);
    public void unlockSlot(int slot);
    public boolean isSlotLocked(int slot);
}
