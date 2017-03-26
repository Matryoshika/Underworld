package se.Matryoshika.Underworld.Content.Rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Content.Items.ItemLumenite;

public class ItemColourHandler {
	
	public static void init(){
		
		ItemColors items = Minecraft.getMinecraft().getItemColors();
		
		items.registerItemColorHandler(new ItemLumenite.LumeniteColorHandler(), ContentRegistry.Lumenite);
		
	}
	
}
