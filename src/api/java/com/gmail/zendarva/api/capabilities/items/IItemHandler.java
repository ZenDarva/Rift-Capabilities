package com.gmail.zendarva.api.capabilities.items;

import com.gmail.zendarva.api.capabilities.ICapability;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IItemHandler extends ICapability, IInventory {

    /*These methods are for implementing transactionality.
    After you take an item from a slot, you may lock the slot, which will prevent any operation from using that slot.
    Once it has reached its destination (Which, presumably, you have also locked), the slot may be unlocked.
    If the transfer fails, you would return the item to this inventory, unlock the slot, and replace the ItemStack.
    */
    public void lockSlot(int slot);
    public void unlockSlot(int slot);
    public boolean isSlotLocked(int slot);

    //This will insert the provided itemstack into the inventory, wherever it will fit.
    //Any leftovers will be returned.
    public ItemStack insertStack(ItemStack stack);
}
