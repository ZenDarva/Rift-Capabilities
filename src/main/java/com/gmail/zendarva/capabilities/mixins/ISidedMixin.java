package com.gmail.zendarva.capabilities.mixins;

import com.gmail.zendarva.capabilities.API.ICapability;
import com.gmail.zendarva.capabilities.API.ICapabilityContext;
import com.gmail.zendarva.capabilities.API.ICapabilityProvider;
import com.gmail.zendarva.capabilities.items.IItemHandler;
import com.gmail.zendarva.capabilities.items.VanillaInventoryWrapper;
import com.gmail.zendarva.capabilities.items.VanillaSidedInventoryWrapper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;

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

}
