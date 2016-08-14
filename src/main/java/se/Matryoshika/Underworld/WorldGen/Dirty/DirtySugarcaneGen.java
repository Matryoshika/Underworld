package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.Utils.Print;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class DirtySugarcaneGen implements IWorldGenerator{

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
		if(random.nextInt(100) >= 67){
			return;
		}
		
		int y = 32;
		int x = chunkX*16;
		int z = chunkZ*16;
		
		int max = (random.nextInt(8))+8;
		
		for(int i = 0; i < max; i++){
			BlockPos pos = new BlockPos(x+offset(8, random), y, z+offset(8, random));
			if(world.getBlockState(pos.down()).getBlock() == Blocks.DIRT || world.getBlockState(pos.down()).getBlock() == Blocks.SAND){
				if(world.isAirBlock(pos)){
					for(int dx = -1; dx < 2; dx++){
						for(int dz = -1; dz < 2; dz++){
							if(world.getBlockState(new BlockPos(pos.getX()+dx, pos.getY()-1, pos.getZ()+dz)).getBlock() == Blocks.WATER){
								world.setBlockState(pos, Blocks.REEDS.getDefaultState());
								//System.out.println("Placed sugarcane at: " + Print.print(pos.down()));
								if(random.nextInt(2) == 0){
									world.setBlockState(pos.up(), Blocks.REEDS.getDefaultState());
									if(random.nextInt(3) == 0){
										world.setBlockState(pos.up(2), Blocks.REEDS.getDefaultState());
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public int offset(int bound, Random rand){
		return rand.nextInt(bound*2)-bound;
	}
}
