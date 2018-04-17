package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Utils.ConfigHandler;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class DirtySpawnerGen implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()) {
			// System.out.println("NOT 0! IT IS "+world.provider.getDimension());
			return;
		}
		if (!(world.getWorldType() instanceof WorldTypeCaves) && !ConfigHandler.forceUnderworld) {
			// System.out.println(world.getWorldType());
			return;
		}

		if (rand.nextInt(8) == 0) {
			world.setBlockState(new BlockPos(chunkX * 16, 129, chunkZ * 16), ContentRegistry.Spawner.getDefaultState());
		}
	}

}
