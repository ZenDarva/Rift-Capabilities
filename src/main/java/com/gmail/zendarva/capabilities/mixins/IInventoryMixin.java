package com.gmail.zendarva.capabilities.mixins;

import com.gmail.zendarva.api.capabilities.ICapability;
import com.gmail.zendarva.api.capabilities.ICapabilityContext;
import com.gmail.zendarva.api.capabilities.ICapabilityProvider;
import com.gmail.zendarva.api.capabilities.items.IItemHandler;
import com.gmail.zendarva.capabilities.items.VanillaInventoryWrapper;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.*;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin({TileEntityChest.class, TileEntityDispenser.class, TileEntityDropper.class, TileEntityHopper.class, TileEntityShulkerBox.class,TileEntityJukebox.class, TileEntityTrappedChest.class})
public class IInventoryMixin implements ICapabilityProvider {

    VanillaInventoryWrapper wrapper = null;

    @Override
    public Optional<? extends ICapability> queryCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
        if (wrapper == null){
            wrapper = new VanillaInventoryWrapper((IInventory) (Object)this);
        }
        if (capability == IItemHandler.class){
            return Optional.of(wrapper);
        }
        return Optional.empty();
    }
    @Override
    public ICapability getCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
        if (wrapper == null){
            wrapper = new VanillaInventoryWrapper((IInventory) (Object)this);
        }
        if (capability == IItemHandler.class){
            return wrapper;
        }
        return null;
    }

    @Override
    public List<ICapability> getCapabilities(ICapabilityContext context, Class<? extends ICapability> capability) {
        //Vanilla TE's aren't going to have more than one capability, by default anyway.
        return new ArrayList<>(getCapabilities(context,capability));
    }

}
