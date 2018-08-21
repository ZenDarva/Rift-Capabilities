package com.gmail.zendarva.api.capabilities.energy;

import com.gmail.zendarva.api.capabilities.ActionType;
import com.gmail.zendarva.api.capabilities.ICapability;

public interface IEnergyHandler extends ICapability {

    public int insert(int amount, ActionType actionType);
    public int extract(int amount, ActionType actionType);
    public int getEnergyStored();
}
