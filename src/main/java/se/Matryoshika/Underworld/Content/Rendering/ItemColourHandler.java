package se.Matryoshika.Underworld.Content.Rendering;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Content.Items.ItemLumeniteNugget;

public class ItemColourHandler {
	
	public static void init(){
		
		ItemColors items = Minecraft.getMinecraft().getItemColors();
		
		items.registerItemColorHandler(new LumeniteNuggetColorHandler(), ContentRegistry.Lumenite);
		items.registerItemColorHandler(new LumeniteIngotColorHandler(), ContentRegistry.LumeniteIngot);
		items.registerItemColorHandler(new LumeniteLanternColorHandler(), ContentRegistry.BlockLumeniteLantern);
		
	}
	
	
	public static class LumeniteNuggetColorHandler implements IItemColor{
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			return Color.getHSBColor((((System.currentTimeMillis()/20) % 256) / 256f), 0.75f, 1f).getRGB();
		}
	}
	
	public static class LumeniteIngotColorHandler implements IItemColor{
		//Offsetting by ~1x(256/3) just because I don't want the colors to be fully synced across different items 
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			return Color.getHSBColor(((((System.currentTimeMillis()/20)+85) % 256) / 256f), 0.75f, 1f).getRGB();
		}
	}
	
	public static class LumeniteLanternColorHandler implements IItemColor{
		//Offsetting by ~2x(256/3) just because I don't want the colors to be fully synced across different items 
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			//Here, only affect parts of the model with tintIndex = 0
			switch(tintIndex){
			case 0: return Color.WHITE.getRGB();
			case 1: return Color.getHSBColor(((((System.currentTimeMillis()/20)+170) % 256) / 256f), 0.75f, 1f).getRGB();
			default : return Color.WHITE.getRGB();
			}
		}
	}
	
}
