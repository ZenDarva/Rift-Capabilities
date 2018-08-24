//package com.gmail.zendarva.capabilities.test;
//
//import com.gmail.zendarva.api.capabilities.ICapability;
//import com.gmail.zendarva.api.capabilities.ICapabilityContext;
//import com.gmail.zendarva.api.capabilities.ICapabilityProvider;
//import com.gmail.zendarva.capabilities.Capabilities;
//import com.gmail.zendarva.api.capabilities.items.IItemHandler;
//import com.gmail.zendarva.capabilities.items.ItemHandler;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.tileentity.TileEntityType;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//
//public class ItemHandlerTesterEntity extends TileEntity implements ICapabilityProvider {
//    private IItemHandler itemStore = new ItemHandler(36, null);
//    public ItemHandlerTesterEntity(TileEntityType<?> p_i48289_1_) {
//        super(p_i48289_1_);
//    }
//    public ItemHandlerTesterEntity(){
//        super(Capabilities.testEntity);
//    }
//    private List<ICapability> proxiedCapabilities = new LinkedList<>();
//
//    @Override
//    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
//        super.writeToNBT(tag);
//        itemStore.writeToNBT(tag);
//        return tag;
//    }
//
//    @Override
//    public void readFromNBT(NBTTagCompound tag) {
//        super.readFromNBT(tag);
//        itemStore.readFromNBT(tag);
//    }
//
//    @Override
//    public Optional<? extends ICapability> queryCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
//        Optional<ICapability> proxiedCap = proxiedCapabilities.stream().filter(f->f.matches(context)).findFirst();
//        if (proxiedCap.isPresent())
//            return proxiedCap;
//        if (capability == IItemHandler.class){
//            return Optional.of(itemStore);
//        }
//        return Optional.empty();
//    }
//    @Override
//    public ICapability getCapability(ICapabilityContext context, Class<? extends ICapability> capability) {
//        Optional<ICapability> proxiedCap = proxiedCapabilities.stream().filter(f->f.matches(context)).findFirst();
//        if (proxiedCap.isPresent())
//            return proxiedCap.get();
//        if (capability == IItemHandler.class){
//            return itemStore;
//        }
//        return null;
//    }
//
//    @Override
//    public List<? extends ICapability> getCapabilities(ICapabilityContext context, Class<? extends ICapability> capability) {
//        List<ICapability> result = new LinkedList<>();
//        proxiedCapabilities.stream().filter(f->f.matches(context)).forEach(f->result.add(f));
//        if (capability == IItemHandler.class){
//            result.add(itemStore);
//        }
//        return result;
//    }
//
//    @Override
//    public void addProxyCapability(ICapability capability) {
//        if (!proxiedCapabilities.contains(capability)){
//            proxiedCapabilities.add(capability);
//        }
//    }
//
//    @Override
//    public void removeProxyCapability(ICapability capability) {
//        if (proxiedCapabilities.contains(capability)){
//            proxiedCapabilities.remove(capability);
//        }
//    }
//
//    @Override
//    public void invalidateCapabilities() {
//        itemStore.invalidate();
//    }
//}
