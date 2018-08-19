package com.gmail.zendarva.capabilities.mixins;

import com.gmail.zendarva.capabilities.API.ICapability;
import com.gmail.zendarva.capabilities.API.ICapabilityProvider;
import com.gmail.zendarva.capabilities.items.IItemHandler;
import com.gmail.zendarva.capabilities.items.VanillaInventoryWrapper;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TileEntityChest.class)
public class ChestMixin implements ICapabilityProvider {

    VanillaInventoryWrapper wrapper = new VanillaInventoryWrapper((IInventory) (Object)this);
    @Override
    public boolean hasCapability(EnumFacing direction, Class<? extends ICapability> capability) {
        if (capability == IItemHandler.class)
            return true;
        return false;
    }

    @Override
    public ICapability getCapability(EnumFacing direction, Class<? extends ICapability> capability) {
        if (capability == IItemHandler.class){
            return wrapper;
        }
        return null;
    }
}
