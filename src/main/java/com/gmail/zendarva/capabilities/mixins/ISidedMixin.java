package com.gmail.zendarva.capabilities.mixins;

import com.gmail.zendarva.api.capabilities.ICapability;
import com.gmail.zendarva.api.capabilities.ICapabilityContext;
import com.gmail.zendarva.api.capabilities.ICapabilityProvider;
import com.gmail.zendarva.api.capabilities.items.IItemHandler;
import com.gmail.zendarva.capabilities.items.VanillaSidedInventoryWrapper;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin({TileEntityFurnace.class, TileEntityBrewingStand.class})
public class ISidedMixin implements ICapabilityProvider {
    VanillaSidedInventoryWrapper wrapper = null;

    @Override
    public Optional<? extends ICapability> getCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
        if (wrapper == null){
            wrapper = new VanillaSidedInventoryWrapper((ISidedInventory) (Object)this);
        }
        if (capability == IItemHandler.class){
            return Optional.of(wrapper);
        }
        return Optional.empty();
    }

    @Override
    public List<Optional<? extends ICapability>> getCapabilities(ICapabilityContext context, Class<? extends ICapability> capability) {
        //Vanilla TE's aren't going to have more than one capability, by default anyway.
        return new ArrayList<>(getCapabilities(context,capability));
    }

}
