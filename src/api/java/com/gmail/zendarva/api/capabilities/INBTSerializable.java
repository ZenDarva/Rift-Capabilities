package com.gmail.zendarva.api.capabilities;

import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;

public interface INBTSerializable<T extends INBTBase> {
    void readFromNBT(T tag);
    T writeToNBT(T tag);
}
