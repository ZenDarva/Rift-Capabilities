package com.gmail.zendarva.capabilities.mixins;

import com.gmail.zendarva.capabilities.API.ICapabilityProvider;
import com.gmail.zendarva.capabilities.items.IItemHandler;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.tileentity.TileEntityHopper.func_195484_a;

@Mixin(TileEntityHopper.class)
public abstract class HopperMixin extends TileEntityLockableLoot {


    //Really?  That's stupid.
    protected HopperMixin(TileEntityType<?> p_i48284_1_) {
        super(p_i48284_1_);
    }

    @Inject(method="getSourceInventory", at=@At("HEAD"), cancellable = true)
    private static void getSourceIInventory(IHopper hopper, CallbackInfoReturnable<IInventory> ci){
        BlockPos pos = new BlockPos(hopper.getXPos(),hopper.getYPos(),hopper.getZPos());
        IInventory inventory = getSidedInventory(hopper.getWorld(),EnumFacing.DOWN,pos.offset(EnumFacing.UP));
        if (inventory == null){
            inventory = func_195484_a(hopper.getWorld(), pos.offset(EnumFacing.UP));
        }
        ci.setReturnValue(inventory);
    }

    @Inject(method="getInventoryForHopperTransfer", at=@At("HEAD"), cancellable = true)
    private void getInventoryForHopperTransfer(CallbackInfoReturnable<IInventory> ci) {
        EnumFacing direction = ((TileEntityHopper)((Object)this)).getBlockState().getValue(BlockHopper.FACING);
        IInventory inventory = getSidedInventory(world,direction.getOpposite(),this.pos.offset(direction));
        if (inventory == null) {//Maybe a vanilla inventory?
            inventory = func_195484_a(world, this.pos.offset(direction));
            if (inventory !=null) {
                System.out.println("Vanilla inventory of type: " + inventory.getClass());
            }
        }
        ci.setReturnValue(inventory);
    }

    private static IInventory getSidedInventory(World world, EnumFacing direction, BlockPos pos){
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock().hasTileEntity()){
            TileEntity entity = world.getTileEntity(pos);
            if (entity instanceof ICapabilityProvider){
                if(((ICapabilityProvider) entity).hasCapability(null, IItemHandler.class)){
                    return (IInventory) ((ICapabilityProvider) entity).getCapability(direction, IItemHandler.class);
                }
            }
        }
        return null;
    }
}
