package se.Matryoshika.Underworld.Content.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Underworld;

public class ItemDebugger extends Item {

	public ItemDebugger() {
		this.maxStackSize = 1;
		this.setCreativeTab(Underworld.UnderworldTab);
		this.setUnlocalizedName("itemDebugger");
		this.setRegistryName(Underworld.MODID, "itemDebugger");
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ITextComponent component = new TextComponentTranslation(player.world.getBlockState(pos.east()).getActualState(world, pos.east()).toString());
		// ITextComponent component = new
		// TextComponentTranslation(player.worldObj.getBlockState(pos.up()).getBlock().getRegistryName().toString());
		// ITextComponent tile = new
		// TextComponentTranslation(player.worldObj.getTileEntity(pos))
		// ITextComponent component = new TextComponentTranslation(Print.print(pos));
		net.minecraft.tileentity.TileEntity hasTile = player.world.getTileEntity(pos.east());
		if (hasTile != null) {
			ITextComponent tile = new TextComponentTranslation("True");
			if (player.world.isRemote)
				player.sendMessage(tile);
		} else {
			ITextComponent tile = new TextComponentTranslation("False");
			if (player.world.isRemote)
				player.sendMessage(tile);
		}
		if (player.world.isRemote)
			player.sendMessage(component);

		return EnumActionResult.FAIL;
	}

}
