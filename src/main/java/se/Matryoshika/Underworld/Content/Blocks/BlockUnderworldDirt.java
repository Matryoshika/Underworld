package se.Matryoshika.Underworld.Content.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.BlockRegistry;

public class BlockUnderworldDirt extends Block{
	
	/**
	 * Used in worldgen instead of vanilla dirt.
	 * Updates itself based on block above, and in what biome it is
	 * Making it much easier for a pretty & dynamic landscape 
	 */
	public static Block BlockVanillaDirt = Blocks.DIRT;
	
	//public static final IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

	public BlockUnderworldDirt() {
		super(Material.GROUND);
		this.setTickRandomly(true);
		this.setCreativeTab(Underworld.UnderworldTab);
        this.setUnlocalizedName("blockDirt");
        this.setRegistryName(Underworld.MODID, "blockDirt");
        //this.setDefaultState(blockState.getBaseState().withProperty(BlockLiquid.LEVEL, 15));
	}
	
	@Override
	public int tickRate(World world){
		return 0;
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		grow(world, pos, state, rand);
		//update(world, pos, state, rand);
    }
	
	public void update(World world, BlockPos pos, IBlockState state, Random rand){
		
		int range = 4;
		
		List<BlockPos> otherPos = new ArrayList<BlockPos>();
		for(int x = -range; x < range+1; x++){
			for(int z = -range; z < range+1; z++){
				for(int y = -range; y < range+1; y++){
					otherPos.add(pos.add(x, y, z));
				}
			}
		}
		
		Biome biome = world.getChunkFromBlockCoords(pos).getBiome(pos, world.getBiomeProvider());
		
		for(int i = 1; i<otherPos.size(); i++){
			BlockPos thisPos = otherPos.get(i);
			if(world.isAirBlock(thisPos.up()) && world.getBlockState(thisPos) == BlockRegistry.BlockDirt.getDefaultState()){
				//For some reason, Biome.TempCategory.COLD does not see these biomes as cold at all...
				if(biome == Biomes.COLD_TAIGA || biome == Biomes.TAIGA || biome == Biomes.TAIGA_HILLS || biome == Biomes.COLD_BEACH || biome == Biomes.COLD_TAIGA_HILLS
						|| biome == Biomes.FROZEN_OCEAN || biome == Biomes.FROZEN_RIVER || biome == Biomes.ICE_MOUNTAINS || biome == Biomes.ICE_PLAINS ||
						biome == Biomes.MUTATED_ICE_FLATS || biome == Biomes.MUTATED_TAIGA || biome == Biomes.MUTATED_TAIGA_COLD
						){
					world.setBlockState(otherPos.get(i), Blocks.SNOW_LAYER.getDefaultState());
				}
				else if(biome.getTempCategory() == Biome.TempCategory.WARM){
					//Prevents sand from falling and clogging up resources
					if(world.isAirBlock(otherPos.get(i).down())){
						world.setBlockState(otherPos.get(i), Blocks.SANDSTONE.getDefaultState());
					}
					else{
						world.setBlockState(otherPos.get(i), Blocks.SAND.getDefaultState());
					}
				}
				else{
					world.setBlockState(otherPos.get(i), Blocks.GRASS.getDefaultState());
				}
			}
			else if(!world.isAirBlock(thisPos.up()) && world.getBlockState(thisPos) == BlockRegistry.BlockDirt.getDefaultState()){
				if(biome.getTempCategory() == Biome.TempCategory.COLD){
					world.setBlockState(otherPos.get(i), Blocks.SNOW_LAYER.getDefaultState());
				}
				else if(biome.getTempCategory() == Biome.TempCategory.WARM){
					//Prevents sand from falling and clogging up resources
					if(world.isAirBlock(otherPos.get(i).down())){
						world.setBlockState(otherPos.get(i), Blocks.SANDSTONE.getDefaultState());
					}
					else{
						world.setBlockState(otherPos.get(i), Blocks.SAND.getDefaultState());
					}
				}
				else{
					world.setBlockState(otherPos.get(i), Blocks.DIRT.getDefaultState());
				}
			}
		}
	}
	
	public void grow(World world, BlockPos pos, IBlockState state, Random rand){
		for(int x = pos.getX()-3; x < pos.getX()+4; x++){
			for(int z = pos.getZ()-3; z < pos.getZ()+4; z++){
				for(int y = pos.getY()-3; y < pos.getY()+4; y++){
					if(world.getBlockState(new BlockPos(x,y,z)) == BlockRegistry.BlockDirt.getDefaultState()){
						update(world, new BlockPos(x,y,z), state, rand);
					}
				}
			}
		}
		update(world, pos, state, rand);
	}
}
