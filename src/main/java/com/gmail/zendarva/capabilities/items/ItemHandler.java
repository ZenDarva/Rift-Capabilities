package com.gmail.zendarva.capabilities.items;

import com.gmail.zendarva.api.capabilities.ICapabilityContext;
import com.gmail.zendarva.api.capabilities.items.IItemHandler;
import com.gmail.zendarva.api.capabilities.items.ItemHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;

public class ItemHandler implements IItemHandler {

    protected int size;
    protected NonNullList<ItemStack> items;
    protected final boolean[] locked;
    private EnumFacing side;
    public BiPredicate<Integer, ItemStack> slotValidator = null;
    private boolean isValid;

    public ItemHandler(int size, EnumFacing side){
        this.size = size;
        items = NonNullList.withSize(size,ItemStack.EMPTY);
        locked = new boolean[size];
        this.side = side;
        for (int i = 0; i < size; i++) {
            locked[i]=false;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        ItemStackHelper.loadAllItems(tag,items);
        if (tag.hasKey("inventorySize"))
            this.size= tag.getInteger("inventorySize");
        if (tag.hasKey("side"))
            this.side= EnumFacing.byIndex(tag.getInteger("side"));

    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        ItemStackHelper.saveAllItems(tag,items);
        tag.setInteger("inventorySize",size);
        tag.setInteger("side",side.getIndex());

    }

    @Override
    public boolean matches(ICapabilityContext context) {
        if (context instanceof ItemHandlerContext){
            ItemHandlerContext ctx = (ItemHandlerContext) context;
            if (ctx.side == side || ctx.side == null){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }

    public void setValid(boolean value){
        this.isValid=value;
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
        return items.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (locked[slot]){
            return ItemStack.EMPTY;
        }
        if (items.get(slot).isEmpty())
            return ItemStack.EMPTY;
        if (items.get(slot).getCount() <= amount){
            ItemStack newStack = items.get(slot);
            items.set(slot,ItemStack.EMPTY);
            markDirty();
            return newStack;
        }
        ItemStack newStack = items.get(slot).copy();
        items.get(slot).shrink(amount);
        newStack.setCount(amount);
        return newStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        if (locked[slot]){
            return ItemStack.EMPTY;
        }
        if (items.get(slot).isEmpty())
            return ItemStack.EMPTY;
        ItemStack newStack = items.get(slot);
        items.set(slot,ItemStack.EMPTY);
        return newStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (locked[slot])
            return;
        items.set(slot,itemStack);
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
            items.set(i,ItemStack.EMPTY);
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

    @Override
    public ItemStack insertStack(ItemStack stack) {
        for (ItemStack item : items) {
            if (item.isStackable() &&
                    item.isItemEqual(stack) &&
                    item.getCount() < item.getMaxStackSize()){
                int dif = item.getMaxStackSize() -item.getCount();
                if (dif > stack.getCount())
                    dif =stack.getCount();
                stack.shrink(dif);
                item.grow(dif);
            }
            if (stack.isEmpty())
                break;
        }
        return stack;
    }


}
