package se.Matryoshika.Underworld.Content.TileEntity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Underworld;

public class TileSugarPile extends TileEntity implements ITickable{
	
	private int age = 0;

	@Override
	public void update() {
		age++;
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		int radius = 8;
		List<EntityLiving> entities = worldObj.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB((double)(x - radius), (double)(y - radius), (double)(z - radius), (double)(x + radius), (double)(y + radius), (double)(z + radius)));
		for(EntityLiving entity : entities){
			
			
			
			double distance;
			String entityName;
            Class entityClass;
			
			if (entity instanceof EntityAnimal ||  
					(distance = entity.getDistance((double)x, (double)y, (double)z)) >= (double)8 || 
					distance == 0.0) 
				continue;
			
			if(distance < 1)
				distance = 1;
			
			Underworld.proxy.spawnCustomParticle("firefly", worldObj, entity.posX-0.4, entity.posY+((entity.height/3)*1), entity.posZ-0.4, 1, 1, 0.1f, 0.2f);
			Underworld.proxy.spawnCustomParticle("firefly", worldObj, entity.posX-0.4, entity.posY+((entity.height/3)*2), entity.posZ-0.4, 1, 1, 0.1f, 0.2f);
			Underworld.proxy.spawnCustomParticle("firefly", worldObj, entity.posX-0.4, entity.posY+((entity.height/3)*3), entity.posZ-0.4, 1, 1, 0.1f, 0.2f);
			double extra = 0;
			if(entity instanceof EntityZombie){
				EntityZombie ent = (EntityZombie) entity;
				if(ent.isChild()){
					extra = 8;
				}
			}
			
			double knockbackMult = 1.2 + extra + 1/distance;
			double reduction = 0.01;
			Vec3d angle = new Vec3d((double)(entity.posX - ((double)x + 0.5)), (double)(entity.posY - (double)y), (double)(entity.posZ - ((double)z + 0.5)));
			double xForce = angle.xCoord * knockbackMult * reduction;
            double yForce = angle.yCoord * knockbackMult * reduction;
            double zForce = angle.zCoord * knockbackMult * reduction;
            
            entity.motionX += xForce;
            entity.motionY += yForce;
            entity.motionZ += zForce;
            
		}
		if(age >= 6000)
			worldObj.setBlockToAir(getPos());
	}

}
