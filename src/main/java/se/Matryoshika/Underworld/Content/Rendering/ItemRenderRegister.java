package se.Matryoshika.Underworld.Content.Rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import se.Matryoshika.Underworld.Content.ItemRegistry;

public class ItemRenderRegister {
	
	public static void registerItemRenderer(){
		reg(ItemRegistry.Debugger);
		reg(ItemRegistry.Lantern);
	}
	
	public static void reg(Item item){
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
