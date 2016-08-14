package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class DirtyLilypadGen implements IWorldGenerator{

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()){
			//System.out.println("NOT 0! IT IS "+world.provider.getDimension());
			return;
		}
		if(!(world.getWorldType() instanceof WorldTypeCaves)){
			//System.out.println(world.getWorldType());
			return;
		}
		
		if(rand.nextInt(25) == 0){
			int x = (chunkX*16)+8;
			int y = 32; 
			int z = (chunkZ*16)+8;
			
			int max = (rand.nextInt(8))+8;
			
			for(int i = 0; i < max; i++){
				BlockPos pos = new BlockPos(x+offset(8, rand), y, z+offset(8, rand));
				if(world.getBlockState(pos.down()).getBlock() == Blocks.WATER){
					world.setBlockState(pos, Blocks.WATERLILY.getDefaultState());
				}
			}
		}
	}
	
	public int offset(int bound, Random rand){
		
		return rand.nextInt(bound*2)-bound;
	}
}
