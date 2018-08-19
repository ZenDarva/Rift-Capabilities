package com.gmail.zendarva.capabilities.mixins;

import com.gmail.zendarva.capabilities.API.ICapability;
import com.gmail.zendarva.capabilities.API.ICapabilityContext;
import com.gmail.zendarva.capabilities.API.ICapabilityProvider;
import com.gmail.zendarva.capabilities.items.IItemHandler;
import com.gmail.zendarva.capabilities.items.VanillaInventoryWrapper;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin({TileEntityChest.class, TileEntityDispenser.class, TileEntityDropper.class, TileEntityHopper.class})
public class IInventoryMixin implements ICapabilityProvider {

    VanillaInventoryWrapper wrapper = null;

    @Override
    public Optional<? extends ICapability> getCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
        if (wrapper == null){
            wrapper = new VanillaInventoryWrapper((IInventory) (Object)this);
        }
        if (capability == IItemHandler.class){
            return Optional.of(wrapper);
        }
        return Optional.empty();
    }

}
