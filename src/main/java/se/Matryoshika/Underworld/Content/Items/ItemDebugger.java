package se.Matryoshika.Underworld.Content.Items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent.TileEntity;
import se.Matryoshika.Underworld.Underworld;

public class ItemDebugger extends Item{
	
	public ItemDebugger(){
		this.maxStackSize = 1;
		this.setCreativeTab(Underworld.UnderworldTab);
        this.setUnlocalizedName("itemDebugger");
        this.setRegistryName(Underworld.MODID, "itemDebugger");
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, 
			float hitX, float hitY, float hitZ){
		
		
		ITextComponent component = new TextComponentTranslation(player.worldObj.getBlockState(pos.up()).getActualState(world, pos.up()).toString());
		//ITextComponent component = new TextComponentTranslation(player.worldObj.getBlockState(pos.up()).getBlock().getRegistryName().toString());
		//ITextComponent tile = new TextComponentTranslation(player.worldObj.getTileEntity(pos))
		net.minecraft.tileentity.TileEntity hasTile = player.worldObj.getTileEntity(pos.up());
		if(hasTile != null){
			ITextComponent tile = new TextComponentTranslation("True");
			if(player.worldObj.isRemote)
			player.addChatMessage(tile);
		}
		else{
			ITextComponent tile = new TextComponentTranslation("False");
			if(player.worldObj.isRemote)
			player.addChatMessage(tile);
		}
		if(player.worldObj.isRemote)
		player.addChatMessage(component);
		
		
		
		return EnumActionResult.FAIL;
	}

}
