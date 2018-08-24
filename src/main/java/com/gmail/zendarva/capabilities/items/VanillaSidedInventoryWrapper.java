package com.gmail.zendarva.capabilities.items;

import com.gmail.zendarva.api.capabilities.ICapabilityContext;
import com.gmail.zendarva.api.capabilities.items.IItemHandler;
import com.gmail.zendarva.api.capabilities.items.ItemHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class VanillaSidedInventoryWrapper implements ISidedInventory, IItemHandler {

    private final ISidedInventory inventory;
    private boolean isValid = true;

    @Override
    public int[] getSlotsForFace(EnumFacing enumFacing) {
        return inventory.getSlotsForFace(enumFacing);
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, @Nullable EnumFacing enumFacing) {
        return inventory.canInsertItem(i, itemStack, enumFacing);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, EnumFacing enumFacing) {
        return inventory.canExtractItem(i, itemStack, enumFacing);
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
    public ItemStack getStackInSlot(int i) {
        return inventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return inventory.decrStackSize(i, i1);
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        return inventory.removeStackFromSlot(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventory.setInventorySlotContents(i, itemStack);
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

    public VanillaSidedInventoryWrapper(ISidedInventory inventory){

        this.inventory = inventory;
    }

    @Override
    public void lockSlot(int slot) {

    }

    @Override
    public void unlockSlot(int slot) {

    }

    @Override
    public boolean isSlotLocked(int slot) {
        return false;
    }


    @Override
    public void readFromNBT(NBTTagCompound tag) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        return tag;
    }

    @Override
    public boolean matches(ICapabilityContext context) {
        if (context instanceof ItemHandlerContext){
            return true;
        }
        return false;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public void invalidate() {
        isValid=false;
    }
}
