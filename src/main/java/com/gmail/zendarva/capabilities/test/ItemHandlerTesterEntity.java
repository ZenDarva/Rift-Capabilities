package com.gmail.zendarva.capabilities.test;

import com.gmail.zendarva.capabilities.API.ICapability;
import com.gmail.zendarva.capabilities.API.ICapabilityContext;
import com.gmail.zendarva.capabilities.API.ICapabilityProvider;
import com.gmail.zendarva.capabilities.Capabilities;
import com.gmail.zendarva.capabilities.items.IItemHandler;
import com.gmail.zendarva.capabilities.items.ItemHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;

import java.util.Optional;

public class ItemHandlerTesterEntity extends TileEntity implements ICapabilityProvider {
    private IItemHandler itemStore = new ItemHandler(36, null);
    public ItemHandlerTesterEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }
    public ItemHandlerTesterEntity(){
        super(Capabilities.testEntity);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        itemStore.writeToNBT(tag);
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        itemStore.readFromNBT(tag);
    }

    @Override
    public Optional<? extends ICapability> getCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
        if (capability == IItemHandler.class){
            return Optional.of(itemStore);
        }
        return Optional.empty();
    }
}
