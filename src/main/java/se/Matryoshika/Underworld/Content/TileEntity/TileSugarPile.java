package se.Matryoshika.Underworld.Content.TileEntity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class TileSugarPile extends TileEntity implements ITickable {

	private int age = 0;

	public TileSugarPile() {
	}

	@Override
	public void update() {
		
		age++;
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		int radius = 8;
		List<EntityLiving> entities = world.getEntitiesWithinAABB(EntityLiving.class,
				new AxisAlignedBB((double) (x - radius), (double) (y - radius), (double) (z - radius),
						(double) (x + radius), (double) (y + radius), (double) (z + radius)));
		for (EntityLiving entity : entities) {

			double distance;
			String entityName;
			Class entityClass;

			if (entity instanceof EntityAnimal
					|| (distance = entity.getDistance((double) x, (double) y, (double) z)) >= (double) 8
					|| distance == 0.0
					|| !entity.isNonBoss())
				continue;

			if (distance < 1)
				distance = 1;

			for (int i = 0; i < 1; i++) {
				double pos1 = entity.posX - 0.4 + (Math.random() - Math.random());
				double pos2 = (entity.posY + 1) + (Math.random() * 2 - 1);
				double pos3 = entity.posZ - 0.4 + (Math.random() - Math.random());
				//Underworld.proxy.spawnCustomParticle("firefly", worldObj, pos1, pos2, pos3, 0.3, 0.3, 0.3, 3, 1, 0.1f, 0.2f, true);
				//Underworld.proxy.spawnCustomParticle("firefly", worldObj, pos1, pos2, pos3, 0.3, 0.3, 0.3, 5, 1, 0.1f, 0.2f, true);
				Underworld.proxy.spawnCustomParticle("firefly", world, pos1, pos2, pos3, 0, 0, 0, 3, 1, 0.1f, 0.2f, true);
				
				//System.out.println(Print.print(pos1, pos2, pos3));
			}

		}
		if (age >= 6000 && !world.isRemote) {
			world.setBlockToAir(getPos());
			if (world.getBlockState(getPos().down()).getBlock() == ContentRegistry.BlockMossStone) {
				if (new Random().nextInt(100) < 50){
					world.setBlockState(getPos().down(), ContentRegistry.BlockGlowMossStone.getDefaultState());
				}
			}
		}
	}

}
