package se.Matryoshika.Underworld.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.google.common.collect.Maps;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Content.TileEntity.TileSugarPile;

public class SugarPileList {
	
	private static HashMap<World,ArrayList<BlockPos>> tiles = Maps.newHashMap();
	
	
	public static void addSugarPile(World world, BlockPos pos){
		if(!tiles.containsKey(world))
			tiles.put(world, new ArrayList<BlockPos>());
		tiles.get(world).add(pos);
	}
	
	public static void removeSugarPile(World world, BlockPos pos){
		tiles.get(world).remove(pos);
	}
	
	public static ArrayList<BlockPos> getSugarPiles(World world){
		if(!tiles.containsKey(world))
			tiles.put(world, new ArrayList<BlockPos>());
		return tiles.get(world);
		
	}

}
