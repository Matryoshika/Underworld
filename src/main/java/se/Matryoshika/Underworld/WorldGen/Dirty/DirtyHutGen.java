package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.IWorldGenerator;
import scala.actors.threadpool.Arrays;
import se.Matryoshika.Underworld.Content.BlockRegistry;
import se.Matryoshika.Underworld.Content.Blocks.BlockHangVine;
import se.Matryoshika.Underworld.Utils.Print;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class DirtyHutGen implements IWorldGenerator{
	
	IBlockState[] approved = {Blocks.STONE.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRASS.getDefaultState(), Blocks.GRAVEL.getDefaultState(), Blocks.SAND.getDefaultState()};
	
	final int [][] COBBLE_BLOCKS = new int [][]{
		{2,0,4},{2,0,3},{2,0,2},{2,0,1},{2,0,0},{2,0,-1},{2,0,-2},{2,0,-3},{2,0,-4},
		{1,0,-4},{0,0,-4},{-1,0,-4},{-2,0,-4},{-3,0,-4},
		{-3,0,-3},{-3,0,-2},{-3,0,-1},{-3,0,0},{-3,0,1},{-3,0,2},{-3,0,3},{-3,0,4},
		{-2,0,4},{-1,0,4},{0,0,4},{1,0,4},
		{1,1,4},{1,2,4},{1,3,4},{2,3,3},{2,2,3},{2,1,3},
		{2,1,-3},{2,2,-3},{2,3,-3},{1,3,-4},{1,2,-4},{1,1,-4},
		{-2,1,-4},{-2,2,-4},{-2,3,-4},{-3,3,-3},{-3,2,-3},{-3,1,-3},
		{-3,1,3},{-3,2,3},{-3,3,3},{-2,3,4},{-2,2,4},{-2,1,4}
	};
	final int [][] PLANK_BLOCKS = new int [][]{
		{-2,0,3},{-2,0,2},{-2,0,1},{-2,0,0},{-2,0,-1},{-2,0,-2},{-2,0,-3},
		{-1,0,3},{-1,0,2},{-1,0,1},{-1,0,0},{-1,0,-1},{-1,0,-2},{-1,0,-3},
		{0,0,3},{0,0,2},{0,0,1},{0,0,0},{0,0,-1},{0,0,-2},{0,0,-3},
		{1,0,3},{1,0,2},{1,0,1},{1,0,0},{1,0,-1},{1,0,-2},{1,0,-3},			
		{0,1,4},{-1,1,4},{0,3,4},{-1,3,4},
		{-1,1,-4},{0,1,-4},{-1,3,-4},{0,3,-4},
		{2,1,-2},{2,1,-1},{2,1,0},{2,1,2},{2,2,2},{2,2,0},{2,3,-2},{2,3,-1},{2,3,0},{2,3,1},{2,3,2},
		{-3,1,2},{-3,1,1},{-3,1,0},{-3,1,-1},{-3,1,-2},{-3,2,-2},{-3,2,0},{-3,3,2},{-3,3,1},{-3,3,0},{-3,3,-1},{-3,3,-2},			
		{-2,4,3},{-2,4,2},{-2,4,1},{-2,4,0},{-2,4,-1},{-2,4,-2},{-2,4,-3},
		{-1,4,3},{-1,4,2},{-1,4,1},{-1,4,0},{-1,4,-1},{-1,4,-2},{-1,4,-3},
		{0,4,3},{0,4,2},{0,4,1},{0,4,0},{0,4,-1},{0,4,-2},{0,4,-3},
		{1,4,3},{1,4,2},{1,4,1},{1,4,0},{1,4,-1},{1,4,-2},{1,4,-3},
	};
	final int [][] GLASS_BLOCKS = new int [][]{
		{2,2,-1},{2,2,-2},{0,2,-4},{-1,2,-4},{-3,2,-2},{-3,2,-1},{-3,2,1},{-3,2,2},{-1,2,4},{0,2,4}
	};
	final int [][] COBBLE_PILLARS = new int [][]{
		{2,1,4},{2,2,4},{2,1,-4},{2,2,-4},{-3,1,-4},{-3,2,-4},{-3,1,4},{-3,2,4}
	};
	final int [][] WOODEN_SLABS = new int [][]{
		{2,4,4},{1,5,3},{2,4,-4},{1,5,-3},{-3,4,-4},{-3,4,4},{-2,5,-3},{-2,5,3},{0,6,2},{-1,6,2},{0,6,-2},{-1,6,-2}
	};
	final int [][] STAIRS = new int [][]{
		{3,3,4,1},{3,3,3,1},{3,3,2,1},{3,3,1,1},{3,3,0,1},{3,3,-1,1},{3,3,-2,1},{3,3,-3,1},{3,3,-4,1},{2,4,3,1},{2,4,2,1},{2,4,1,1},{2,4,0,1},{2,4,-1,1},{2,4,-2,1},{2,4,-3,1},{1,5,-2,1},{1,5,-1,1},{1,5,0,1},{1,5,1,1},{1,5,2,1},{0,6,1,1},{0,6,0,1},
		{0,6,-1,1},
		{-4,3,-4,0},{-4,3,-3,0},{-4,3,-2,0},{-4,3,-1,0},{-4,3,0,0},{-4,3,1,0},{-4,3,2,0},{-4,3,3,0},{-4,3,4,0},{-3,4,3,0},{-3,4,2,0},{-3,4,1,0},{-3,4,0,0},{-3,4,-1,0},{-3,4,-2,0},{-3,4,-3,0},{-2,5,-2,0},{-2,5,-1,0},{-2,5,0,0},{-2,5,1,0},{-2,5,2,0},
		{-1,6,1,0},{-1,6,0,0},{-1,6,-1,0},
		{-3,3,-5,2},{-2,3,-5,2},{-1,3,-5,2},{0,3,-5,2},{1,3,-5,2},{2,3,-5,2},{1,4,-4,2},{0,4,-4,2},{-1,4,-4,2},{-2,4,-4,2},{-1,5,-3,2},{0,5,-3,2},
		{-3,3,5,3},{-2,3,5,3},{-1,3,5,3},{0,3,5,3},{1,3,5,3},{2,3,5,3},{1,4,4,3},{0,4,4,3},{-1,4,4,3},{-2,4,4,3},{-1,5,3,3},{0,5,3,3}
	
	};

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()){
			//System.out.println("NOT 0! IT IS "+world.provider.getDimension());
			return;
		}
		if(!(world.getWorldType() instanceof WorldTypeCaves)){
			//System.out.println(world.getWorldType());
			return;
		}
		
		
		
		for(int y = 32; y < 80; y++){
			int blockX = chunkX * 16 + 8;
			int blockY = y + 1;
			int blockZ = chunkZ * 16 + 8;
			
			if (canGenerate(world, blockX, blockY, blockZ)){
				//System.out.println("Placed a house at: " + Print.print(blockX, blockY-1, blockZ));
				generateStructure(world, blockX, blockY-1, blockZ, random);
			}
		}
	}
	private boolean canGenerate(World world, int x, int y, int z) {
		
		BlockPos corner1 = new BlockPos(x-5,y,z);
		BlockPos corner2 = new BlockPos(x+5,y,z);
		BlockPos corner3 = new BlockPos(x,y,z-3);
		BlockPos corner4 = new BlockPos(x,y,z+3);
		BlockPos[] pos = {corner1, corner2, corner3, corner4};
		int count = 0;
		
		if(world.isAirBlock(corner1) && world.isAirBlock(corner2) && world.isAirBlock(corner3) && world.isAirBlock(corner4)){
			//System.out.println("All air, boss");
			if(!world.isAirBlock(corner1.down()) && !world.isAirBlock(corner2.down()) && !world.isAirBlock(corner3.down()) && !world.isAirBlock(corner4.down())){
				if(world.getBlockState(corner1.down()) != Blocks.WATER && world.getBlockState(corner2.down()) != Blocks.WATER && world.getBlockState(corner3.down()) != Blocks.WATER && world.getBlockState(corner4.down()) != Blocks.WATER){
					return true;
				} 
			}
		}
		//System.out.println("Couldn't place hut");
		return false;
	}
	
	
	private void generateStructure(World world, int x, int y, int z, Random rand) {
		Random genRand = new Random();
		int radius = 5;
		
		if(genRand.nextInt(40) == 0){
			
			for(int[] coords : COBBLE_BLOCKS){
				int dx = x + coords[0];
				int dy = y + coords[1];
				int dz = z + coords[2];
				world.setBlockState(new BlockPos(dx, dy, dz), Blocks.COBBLESTONE.getDefaultState());
			}
			for(int[] coords : PLANK_BLOCKS){
				int dx = x + coords[0];
				int dy = y + coords[1];
				int dz = z + coords[2];
				world.setBlockState(new BlockPos(dx, dy, dz), Blocks.PLANKS.getDefaultState());
			}
			for(int[] coords : GLASS_BLOCKS){
				int dx = x + coords[0];
				int dy = y + coords[1];
				int dz = z + coords[2];
				world.setBlockState(new BlockPos(dx, dy, dz), Blocks.GLASS_PANE.getDefaultState());
			}
			IBlockState mossyPillar = Blocks.COBBLESTONE_WALL.getDefaultState().withProperty(BlockWall.VARIANT, BlockWall.EnumType.MOSSY);
			for(int[] coords : COBBLE_PILLARS){
				int dx = x + coords[0];
				int dy = y + coords[1];
				int dz = z + coords[2];
				world.setBlockState(new BlockPos(dx, dy, dz), mossyPillar);
			}
			for(int[] coords : WOODEN_SLABS){
				int dx = x + coords[0];
				int dy = y + coords[1];
				int dz = z + coords[2];
				world.setBlockState(new BlockPos(dx, dy, dz), Blocks.WOODEN_SLAB.getDefaultState());
			}
			for(int[] coords : STAIRS){
				int dx = x + coords[0];
				int dy = y + coords[1];
				int dz = z + coords[2];
				int meta = coords[3];
				world.setBlockState(new BlockPos(dx, dy, dz), Blocks.OAK_STAIRS.getStateFromMeta(meta));
			} 
			world.setBlockState(new BlockPos(x+2, y+1, z+1), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.OPEN, true));
			world.setBlockState(new BlockPos(x+2, y+2, z+1), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.OPEN, true).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER));
			world.setBlockState(new BlockPos(x+3, y+1, z+2), BlockRegistry.BlockBrazierOn.getDefaultState());
			world.setBlockState(new BlockPos(x-2, y+1, z-3), Blocks.BOOKSHELF.getDefaultState());
			world.setBlockState(new BlockPos(x+1, y+1, z+3), Blocks.FURNACE.getDefaultState());
			
			IBlockState iblockstate1 = Blocks.BED.getDefaultState()
					.withProperty(BlockBed.FACING, EnumFacing.byName("north"))
					.withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);
			IBlockState iblockstate2 = Blocks.BED.getDefaultState()
					.withProperty(BlockBed.FACING, EnumFacing.byName("north"))
					.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD);
			
			world.setBlockState(new BlockPos(x+1, y+1, z-2), iblockstate1);
			world.setBlockState(new BlockPos(x+1, y+1, z-3), iblockstate2);
			
			world.setBlockState(new BlockPos(x-2, y+1, z+3), Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.byName("north")));
			TileEntity tileentity1 = world.getTileEntity(new BlockPos(x-2, y+1, z+3));

            if (tileentity1 instanceof TileEntityChest){
                ((TileEntityChest)tileentity1).setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, rand.nextLong());
            }
			
			
			//System.out.print("Spawned a house at: "+ x + ", " + y + ", " + z);
		}
	}
}
