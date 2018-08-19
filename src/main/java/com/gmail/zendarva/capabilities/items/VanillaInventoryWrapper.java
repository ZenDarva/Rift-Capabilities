package com.gmail.zendarva.capabilities.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class VanillaInventoryWrapper implements IItemHandler {

    private final IInventory inventory;
    private final boolean[] locked;

    public VanillaInventoryWrapper(IInventory inventory) {

        this.inventory = inventory;
        locked = new boolean[inventory.getSizeInventory()];
        for (int i = 0; i < locked.length; i++) {
            locked[i] = false;
        }
    }

    @Override
    public int getSizeInventory() {
        return inventory.getSizeInventory();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (locked[slot]) {
            return ItemStack.EMPTY;
        }
        return inventory.getStackInSlot(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (locked[slot]) {
            return ItemStack.EMPTY;
        }
        return inventory.decrStackSize(slot, amount);
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        if (locked[slot]) {
            return ItemStack.EMPTY;
        }
        return inventory.removeStackFromSlot(slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (locked[slot]) {
            return;
        }
        inventory.setInventorySlotContents(slot, itemStack);
    }

    @Override
    public int getInventoryStackLimit() {
        return inventory.getInventoryStackLimit();
    }

    @Override
    public void markDirty() {
        inventory.markDirty();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return inventory.isUsableByPlayer(entityPlayer);
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {
        inventory.openInventory(entityPlayer);
    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {
        inventory.closeInventory(entityPlayer);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return inventory.isItemValidForSlot(i, itemStack);
    }

    @Override
    public int getField(int i) {
        return inventory.getField(i);
    }

    @Override
    public void setField(int i, int i1) {
        inventory.setField(i, i1);
    }

    @Override
    public int getFieldCount() {
        return inventory.getFieldCount();
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public int getHeight() {
        return inventory.getHeight();
    }

    @Override
    public int getWidth() {
        return inventory.getWidth();
    }

    @Override
    public ITextComponent getName() {
        return inventory.getName();
    }

    @Override
    public boolean hasCustomName() {
        return inventory.hasCustomName();
    }

    @Override
    public ITextComponent getDisplayName() {
        return inventory.getDisplayName();
    }

    @Override
    @Nullable
    public ITextComponent getCustomName() {
        return inventory.getCustomName();
    }

    @Override
    public void lockSlot(int slot) {
        locked[slot] = true;
    }

    @Override
    public void unlockSlot(int slot) {
        locked[slot] = false;
    }

    @Override
    public boolean isSlotLocked(int slot) {
        return locked[slot];
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {

    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {

    }
}
