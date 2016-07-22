package se.Matryoshika.Underworld.Utils;

import net.minecraft.util.math.BlockPos;

public class Print {
	
	public static String print(int x, int y, int z){
		return x+", "+y+", "+z;
	}
	
	public static String print(BlockPos pos){
		return pos.getX()+", "+pos.getY()+", "+pos.getZ();
	}

}
