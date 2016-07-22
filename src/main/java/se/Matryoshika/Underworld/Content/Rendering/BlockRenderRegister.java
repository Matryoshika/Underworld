package se.Matryoshika.Underworld.Content.Rendering;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.BlockRegistry;

public class BlockRenderRegister {
	
	public static void registerBlockRenderer(){
		reg(BlockRegistry.BlockHangVine);
		reg(BlockRegistry.BlockDirt);
	}
	
	
	public static void reg(Block block) {
	    //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Underworld.MODID + ":" + "hangVine", "inventory"));
	    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

}
