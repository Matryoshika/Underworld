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
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;

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
}
