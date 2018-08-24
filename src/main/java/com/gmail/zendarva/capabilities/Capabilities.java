package com.gmail.zendarva.capabilities;

import com.gmail.zendarva.capabilities.test.ItemHandlerTesterBlock;
import com.gmail.zendarva.capabilities.test.ItemHandlerTesterEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;
import org.dimdev.rift.listener.TileEntityTypeAdder;

public class Capabilities implements BlockAdder, TileEntityTypeAdder, ItemAdder {
//    public static Block testBlock;
//    public static TileEntityType<ItemHandlerTesterEntity> testEntity;
    @Override
    public void registerBlocks() {
        //testBlock = new ItemHandlerTesterBlock(Block.Builder.create(Material.WOOD));
        //Block.register("capabilities:testblock",testBlock);
    }

    @Override
    public void registerTileEntityTypes() {
        //testEntity = TileEntityType.registerTileEntityType("capabilities:test", TileEntityType.Builder.create(ItemHandlerTesterEntity::new));
    }

    @Override
    public void registerItems() {
        Item.registerItemBlock(testBlock, ItemGroup.TRANSPORTATION);
    }
}
