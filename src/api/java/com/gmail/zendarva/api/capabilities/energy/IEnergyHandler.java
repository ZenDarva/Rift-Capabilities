package com.gmail.zendarva.api.capabilities.energy;

import com.gmail.zendarva.api.capabilities.ICapability;

public interface IEnergyHandler extends ICapability {

    public int takeEnergy(int amount, boolean simulate);
    public int putEnergy(int amount, boolean simulate);
    public int getEnergyStored();
}
