package se.Matryoshika.Underworld.Content.Items;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.BlockRegistry;
import se.Matryoshika.Underworld.Content.ItemRegistry;

public class ItemLantern extends Item{
	
	private Random rand = new Random();
	
	public int counter;
	int broken = this.getMaxDamage()-1;

	public ItemLantern(){
		this.maxStackSize = 1;
		this.setCreativeTab(Underworld.UnderworldTab);
        this.setUnlocalizedName("itemLantern");
        this.setRegistryName(Underworld.MODID, "itemLantern");
        this.setMaxDamage(31);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected){
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			
			//If not currently held, stop this update.
			if(!selected){
				return;
			}
			//Do not do anything if this is 'broken'
			if(stack.getItemDamage() >= this.getMaxDamage()-1){
				return;
			}
			
			/* To be added. Right now it doesn't make use of fuel.
			
			counter++;
			//The hell, 40 ticks/second or something? All calculations fall short with ticks happening every 0.5 estimated tick
			if(counter >= 1200*2 && !world.isRemote){
				System.out.println(stack.getItemDamage());
				this.setDamage(stack, stack.getItemDamage()+1);
				counter = 0;
			}
			*/
			
			BlockPos pos = new BlockPos(player.posX, player.posY+1, player.posZ);
			
			for(int x = -1; x < 2; x++){
				for(int z = -1; z < 2; z++){
					for(int y = -1; y < 2; y++){
						BlockPos scannedBlock = new BlockPos(pos.getX()+x, pos.getY()+y, pos.getZ()+z); 
						
						//If the scan finds a single Light-block, stop this update.
						if(world.getBlockState(scannedBlock) == BlockRegistry.Light.getDefaultState()){
							return;
						}
						//Else place a new light.
						else{
							if(world.isAirBlock(pos))
							world.setBlockState(pos, BlockRegistry.Light.getDefaultState());
						}
					}
				}
			}
			
			
		}
	}
}
