package se.Matryoshika.Underworld.Content.Items;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Underworld.Underworld;

public class ItemLumenite extends Item{
	
	public ItemLumenite(){
		this.setRegistryName(Underworld.MODID, "item_lumenite");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Underworld.UnderworldTab);
	}

	
	public static class LumeniteColorHandler implements IItemColor{
		
		public ArrayList<Integer> colours = new ArrayList<Integer>();
		public float currentCol = 0;

		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			//System.out.println();
			return Color.getHSBColor((((System.currentTimeMillis()/20) % 256) / 256f), 0.75f, 1f).getRGB();
		}
		
	}
}
