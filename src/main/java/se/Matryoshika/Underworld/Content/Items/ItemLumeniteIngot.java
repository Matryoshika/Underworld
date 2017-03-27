package se.Matryoshika.Underworld.Content.Items;

import net.minecraft.item.Item;
import se.Matryoshika.Underworld.Underworld;

public class ItemLumeniteIngot extends Item{
	
	public ItemLumeniteIngot(){
		this.setRegistryName(Underworld.MODID, "item_lumenite_ingot");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Underworld.UnderworldTab);
	}

}
