package se.Matryoshika.Underworld.Utils;

import net.minecraft.util.math.BlockPos;

public class Print {
	
	public static String print(double x, double y, double z){
		return x+", "+y+", "+z;
	}
	
	public static String print(BlockPos pos){
		return pos.getX()+", "+pos.getY()+", "+pos.getZ();
	}

}
