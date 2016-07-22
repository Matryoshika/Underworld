package se.Matryoshika.Underworld.Content;

import se.Matryoshika.Underworld.Content.Blocks.*;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class BlockRegistry {
	
	public static final Set<Block> BLOCKS = new HashSet<>();

	public static Block BlockHangVine;
	public static Block BlockDirt;
	
	static{
		BlockHangVine = registerBlock(new BlockHangVine());
		BlockDirt = registerBlock(new BlockUnderworldDirt());
	}
	
	
	public static void createBlocks(){
	}
	
	protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block) {
		return registerBlock(block, ItemBlock::new);
	}
	
	protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block, @Nullable Function<BLOCK, ItemBlock> itemFactory) {
		GameRegistry.register(block);

		if (itemFactory != null) {
			final ItemBlock itemBlock = itemFactory.apply(block);
			itemBlock.setRegistryName(block.getRegistryName());
			GameRegistry.register(itemBlock);
		}

		BLOCKS.add(block);
		return block;
	}
}
