package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.WorldGen.ChunkGeneratorCaves;
import se.Matryoshika.Underworld.WorldGen.caves.UWCave;

public class DirtyCaves implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!(chunkGenerator instanceof ChunkGeneratorCaves))
			return;

		UWCave cave = new UWCave(chunkProvider.provideChunk(chunkX, chunkZ), 200, 100, 62, 6, 190);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = cave.caveMax; y >= cave.caveMin; y--) {
					BlockPos pos = new BlockPos((chunkX << 4) + x, y, (chunkZ << 4) + z);
					if (!stillInChunk(cave, pos))
						continue;
					if (cave.insideGiantCave(pos.getX(), pos.getY(), pos.getZ())) {

						IBlockState state = world.getBlockState(pos);
						if (state.getBlock() instanceof BlockFalling)
							world.setBlockState(pos, y > 126 ? Blocks.STONE.getDefaultState() : y > 63 ? Blocks.BRICK_BLOCK.getDefaultState() : Blocks.STONEBRICK.getDefaultState());

					} else
						world.setBlockState(pos, Blocks.AIR.getDefaultState());

				}
			}
		}
	}

	public boolean stillInChunk(UWCave cave, BlockPos pos) {
		return cave.chunk.x == pos.getX() >> 4 && cave.chunk.z == pos.getZ() >> 4;
	}

}
