package se.Matryoshika.Underworld.Integration.JEI;

import java.util.ArrayList;

import mezz.jei.api.IModRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class JEIDescriptions {
	
	
	public static void addDescriptions(IModRegistry registry){
		
		registry.addDescription(new ItemStack(ContentRegistry.Lumenite), Underworld.MODID+".description.jei.lumenite1", Underworld.MODID+".description.jei.lumenite2");

		registry.addDescription(new ArrayList<ItemStack>(Arrays.asList(new ItemStack[]{new ItemStack(Items.SUGAR), new ItemStack(ContentRegistry.BlockSugarPile)})), Underworld.MODID+".description.jei.sugar1", Underworld.MODID+".description.jei.sugar2");
	}

}
