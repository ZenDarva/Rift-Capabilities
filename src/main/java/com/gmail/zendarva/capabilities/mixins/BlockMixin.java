package com.gmail.zendarva.capabilities.mixins;

import com.gmail.zendarva.api.capabilities.ICapabilityProvider;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "onReplaced", at = @At("HEAD"))
    private void onReplaced(IBlockState p_onReplaced_1_, World world, BlockPos blockPos, IBlockState p_onReplaced_4_, boolean p_onReplaced_5_, CallbackInfo ci) {
        if (world.isRemote)
            return;
        TileEntity entity = world.getTileEntity(blockPos);
        if (p_onReplaced_1_.getBlock().hasTileEntity() && entity instanceof ICapabilityProvider) {
            ((ICapabilityProvider) entity).invalidateCapabilities();
        }
    }
}
