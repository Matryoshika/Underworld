package se.Matryoshika.Underworld.Content.Items;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Underworld.Underworld;

public class ItemLumeniteNugget extends Item{
	
	public ItemLumeniteNugget(){
		this.setRegistryName(Underworld.MODID, "item_lumenite");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Underworld.UnderworldTab);
	}

	
	
}
