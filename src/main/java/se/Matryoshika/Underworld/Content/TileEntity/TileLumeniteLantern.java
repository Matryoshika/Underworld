package se.Matryoshika.Underworld.Content.TileEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class TileLumeniteLantern extends TileEntity {

	public static Map<World, ArrayList<ChunkPos>> claimedChunks = new HashMap<World, ArrayList<ChunkPos>>();

	public TileLumeniteLantern() {
		addChunks();
	}

	public void addChunks() {
		int chunkX = (getPos().getX() >> 4);
		int chunkZ = (getPos().getZ() >> 4);

		for (int x = chunkX - 1; x <= chunkX + 1; x++) {
			for (int z = chunkZ - 1; z <= chunkZ + 1; z++) {
				if (claimedChunks.get(world) == null)
					claimedChunks.put(world, new ArrayList<ChunkPos>());

				claimedChunks.get(world).add(new ChunkPos(x, z));
			}
		}
	}

	public void removeChunks() {
		int chunkX = (getPos().getX() >> 4);
		int chunkZ = (getPos().getZ() >> 4);

		for (int x = chunkX - 1; x <= chunkX + 1; x++) {
			for (int z = chunkZ - 1; z <= chunkZ + 1; z++) {
				if (claimedChunks.get(world) == null)
					claimedChunks.put(world, new ArrayList<ChunkPos>());

				claimedChunks.get(world).remove(new ChunkPos(x, z));
			}
		}
	}
}
