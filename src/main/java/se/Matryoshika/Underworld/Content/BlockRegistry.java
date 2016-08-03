package se.Matryoshika.Underworld.Content;

import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.Blocks.*;
import se.Matryoshika.Underworld.Content.Rendering.BlockRenderRegister;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockRegistry {
	
	public static final Set<Block> BLOCKS = new HashSet<>();

	public static Block BlockHangVine;
	public static Block BlockDirt;
	public static Block BlockBrazierOff;
	public static Block BlockBrazierOn;
	public static Block Light;
	
	
	public static List<Block>BlockList=new ArrayList<Block>();
	
	public static void registerBlocks(){
		BlockList.add(BlockHangVine = new BlockHangVine());
		BlockList.add(BlockDirt = new BlockUnderworldDirt());
		BlockList.add(BlockBrazierOff = new BlockBrazierOff());
		BlockList.add(BlockBrazierOn = new BlockBrazierOn().setLightLevel(1.0F));
		BlockList.add(Light = new BlockCustomLight());
		
		
		for(Block block:BlockList){
			GameRegistry.register(block);
			System.out.println("Registered : " + block.getUnlocalizedName());
			ItemBlock iblock = new ItemBlock(block);
			iblock.setRegistryName(block.getRegistryName());
			GameRegistry.register(iblock);
		}
	}
}
