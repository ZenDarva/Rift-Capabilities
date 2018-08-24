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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Mixin({TileEntityChest.class, TileEntityDispenser.class, TileEntityDropper.class, TileEntityHopper.class, TileEntityShulkerBox.class,TileEntityJukebox.class, TileEntityTrappedChest.class})
public class IInventoryMixin implements ICapabilityProvider {

    VanillaInventoryWrapper wrapper = null;
    private List<ICapability> proxiedCapabilities = new LinkedList<>();




    private void initWrapper(){
        if (wrapper == null){
            wrapper = new VanillaInventoryWrapper((IInventory) (Object)this);
        }
    }

    @Override
    public Optional<? extends ICapability> queryCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
        initWrapper();
        Optional<ICapability> proxiedCap = proxiedCapabilities.stream().filter(f->f.matches(context)).findFirst();
        if (proxiedCap.isPresent())
            return proxiedCap;
        if (capability == IItemHandler.class){
            return Optional.of(wrapper);
        }
        return Optional.empty();
    }
    @Override
    public ICapability getCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
        initWrapper();
        Optional<ICapability> proxiedCap = proxiedCapabilities.stream().filter(f->f.matches(context)).findFirst();
        if (proxiedCap.isPresent())
            return proxiedCap.get();
        if (capability == IItemHandler.class){
            return wrapper;
        }
        return null;
    }

    @Override
    public List<? extends ICapability> getCapabilities(ICapabilityContext context, Class<? extends ICapability> capability) {
        initWrapper();
        List<ICapability> result = new LinkedList<>();
        proxiedCapabilities.stream().filter(f->f.matches(context)).forEach(f->result.add(f));
        if (capability == IItemHandler.class){
            result.add(wrapper);
        }
        return result;
    }

    @Override
    public void addProxyCapability(ICapability capability) {
        if (!proxiedCapabilities.contains(capability)){
            proxiedCapabilities.add(capability);
        }
    }

    @Override
    public void removeProxyCapability(ICapability capability) {
        if (proxiedCapabilities.contains(capability)){
            proxiedCapabilities.remove(capability);
        }
    }

    @Override
    public void invalidateCapabilities() {
        if (wrapper!=null)
            wrapper.invalidate();
    }
}
