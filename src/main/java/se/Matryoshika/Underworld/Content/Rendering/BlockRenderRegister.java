package se.Matryoshika.Underworld.Content.Rendering;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Utils.ConfigHandler;

public class BlockRenderRegister {
	
	public static void registerBlockRenderer(){
		
		for(Block block : ContentRegistry.BlockList){
			if(!((Boolean) ConfigHandler.isBlockEnabledMap.get(block.getRegistryName().toString()))){
				continue;
			}
			reg(block);
		}
	}
	
	
	public static void reg(Block block) {
	    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
