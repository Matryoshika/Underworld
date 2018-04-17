package se.Matryoshika.Underworld.Utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class CreativeTabUnderworld extends CreativeTabs{
	
	public CreativeTabUnderworld(String lable){
		super(lable);
	}
	
	@Override
	public ItemStack getTabIconItem(){
		return new ItemStack(ContentRegistry.BlockLumeniteLantern);
	}
	
	@Override
	public String getTranslatedTabLabel(){
		return this.getTabLabel();
	}

}
