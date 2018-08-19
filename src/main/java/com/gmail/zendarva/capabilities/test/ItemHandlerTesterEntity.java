package com.gmail.zendarva.capabilities.test;

import com.gmail.zendarva.capabilities.API.ICapability;
import com.gmail.zendarva.capabilities.API.ICapabilityProvider;
import com.gmail.zendarva.capabilities.Capabilities;
import com.gmail.zendarva.capabilities.items.IItemHandler;
import com.gmail.zendarva.capabilities.items.ItemHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;

public class ItemHandlerTesterEntity extends TileEntity implements ICapabilityProvider {
    private IItemHandler itemStore = new ItemHandler(36);
    public ItemHandlerTesterEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }
    public ItemHandlerTesterEntity(){
        super(Capabilities.testEntity);
    }

    @Override
    public boolean hasCapability(EnumFacing direction, Class<? extends ICapability> capability) {
        if (capability == IItemHandler.class)
            return true;
        return false;
    }

    @Override
    public ICapability getCapability(EnumFacing direction, Class<? extends ICapability> capability) {
        if (capability == IItemHandler.class)
            return itemStore;
        return null;
    }
}
