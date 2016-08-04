package se.Matryoshika.Underworld.Content.TileEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;
import se.Matryoshika.Underworld.Utils.Print;

public class TileInvisMobSpawner extends CustomTileClass implements ITickable{
	
	Biome biome;
	List<String> allEntities;
	int counter = 0;
	int i = 0;
	
	public TileInvisMobSpawner(){
		this.setName("InvisMobSpawner");
	}

	@Override
	public void update() {
		BlockPos pos = this.pos;
		if(biome == null){
			biome = this.worldObj.getBiomeForCoordsBody(pos);
			allEntities= EntityList.getEntityNameList();
			
		}
		
		
		counter++;
		
		if(counter >= 1){
			
			BlockPos possiblePos = getLocation(this.worldObj, pos);
			if(isSuitable(possiblePos, this.worldObj)){
				//System.out.println("WORKS BOSS");
				Class<? extends Entity> entity = getCreature(this.worldObj, possiblePos);
				if(entity != null)
				doSpawn(this.worldObj, entity, possiblePos);
				counter = 0;
			}
		}
	}

	public Class<? extends Entity> getCreature(World world, BlockPos pos){
		Random rand = new Random();
		String name = allEntities.get(rand.nextInt(allEntities.size()));
		
		return EntityList.NAME_TO_CLASS.get(name);
	}
	
	public BlockPos getLocation(World world, BlockPos pos){
		Random randX = new Random();
		Random randY = new Random();
		Random randZ = new Random();
		
		BlockPos possibleBlock = new BlockPos(pos.getX()+ (randX.nextInt(64)-32), pos.getY()+ (randY.nextInt(120)-128), pos.getZ()+(randZ.nextInt(64)-32));
		
		return possibleBlock;
	}
	
	public boolean isSuitable(BlockPos possibleBlock, World world){
		if(world.getBlockState(possibleBlock) == Blocks.AIR.getDefaultState()){
			if(world.getBlockState(possibleBlock.down()).getBlock() != Blocks.AIR && world.getBlockState(possibleBlock.down()).getBlock() != Blocks.WATER){
				return true;
			}
		}
		return false;
	}
	
	public void doSpawn(World world, Class<? extends Entity> possEntity, BlockPos pos){
		Constructor<?> constructor;
		Entity entity;
		try {
			constructor = possEntity.getConstructor(World.class);
			entity = (Entity) constructor.newInstance(new Object[] { world });
			if(!(entity instanceof EntityAnimal) || world.isRemote){
				return;
			}
			List<EntityAnimal> list = world.getEntitiesWithinAABB(EntityAnimal.class, new AxisAlignedBB(this.pos.getX()-32, 0, this.pos.getZ()-32, this.pos.getX()+32, 128, this.pos.getZ()+32));
			if(list.size() > 10){
				return;
			}
			if(world.getBiomeForCoordsBody(pos).getSpawnableList(EnumCreatureType.CREATURE).toString().contains(entity.getClass().getSimpleName())){
				entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), world.rand.nextFloat() * 360.0F, 0.0F);
				world.spawnEntityInWorld(entity);
		        world.playEvent(2004, pos, 0);
			}
	        
		} catch ( NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

			e.printStackTrace();
		}
	}
}
