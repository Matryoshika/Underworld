package se.Matryoshika.Underworld.Content.Blocks;

import java.util.HashMap;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class BlockMossStone extends Block{
	public HashMap<BlockPos, Boolean> updateList = Maps.newHashMap();
	
	public BlockMossStone() {
		super(Material.ROCK);
		this.setRegistryName("moss_stone");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Underworld.UnderworldTab);
		this.setTickRandomly(true);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
	}
	
	@Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(Blocks.MOSSY_COBBLESTONE);
    }

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		if(updateList.get(pos) == null){
			int x = rand.nextInt(80);
			if(x < 1){
				updateList.put(pos, true);
				world.setBlockState(pos, ContentRegistry.BlockGlowMossStone.getDefaultState());
			}
			else
				updateList.put(pos, false);
		}
    }
	
	
}
