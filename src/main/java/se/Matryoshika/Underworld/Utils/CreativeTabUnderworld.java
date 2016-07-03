package se.Matryoshika.Underworld.Utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabUnderworld extends CreativeTabs{
	
	public CreativeTabUnderworld(String lable){
		super(lable);
	}
	
	@Override
	public Item getTabIconItem(){
		return null;
	}
	
	@Override
	public String getTranslatedTabLabel(){
		return this.getTabLabel();
	}

}
