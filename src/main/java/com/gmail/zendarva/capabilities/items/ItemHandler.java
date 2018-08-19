package com.gmail.zendarva.capabilities.items;

import com.gmail.zendarva.capabilities.API.ICapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class ItemHandler implements IItemHandler {

    protected final int size;
    protected final ItemStack[] items;
    protected final boolean[] locked;
    public BiPredicate<Integer, ItemStack> slotValidator = null;

    public ItemHandler(int size){
        this.size = size;
        items = new ItemStack[size];
        locked = new boolean[size];

        for (int i = 0; i < size; i++) {
            items[i] =ItemStack.EMPTY;
            locked[i]=false;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {

    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {

    }

    @Override
    public int getSizeInventory() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack item : items) {
            if (!item.isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return items[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (locked[slot]){
            return ItemStack.EMPTY;
        }
        if (items[slot].isEmpty())
            return ItemStack.EMPTY;
        if (items[slot].getCount() <= amount){
            ItemStack newStack = items[slot];
            items[slot]= ItemStack.EMPTY;
            markDirty();
            return newStack;
        }
        //Wrong.
        ItemStack newStack = items[slot].copy();
        items[slot].shrink(amount);
        newStack.setCount(amount);
        return newStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        if (locked[slot]){
            return ItemStack.EMPTY;
        }
        if (items[slot].isEmpty())
            return ItemStack.EMPTY;
        ItemStack newStack = items[slot];
        items[slot]=ItemStack.EMPTY;
        return newStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (locked[slot])
            return;
        items[slot]=itemStack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (locked[slot])
            return false;
        if (slotValidator != null)
            return slotValidator.test(slot,itemStack);
        return true;
    }

    @Override
    public int getField(int i) {
        return 0;
    }

    @Override
    public void setField(int field, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i]=ItemStack.EMPTY;
            locked[i] = false;
        }
    }

    @Override
    public ITextComponent getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return null;
    }

    @Override
    public void lockSlot(int slot) {
        locked[slot]=true;
    }

    @Override
    public void unlockSlot(int slot) {
        locked[slot]=false;
    }

    @Override
    public boolean isSlotLocked(int slot) {
        return locked[slot];
    }
}
