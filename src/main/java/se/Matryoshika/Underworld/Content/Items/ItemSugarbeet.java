package se.Matryoshika.Underworld.Content.Items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Content.Blocks.BlockSugarbeet;

public class ItemSugarbeet extends ItemSeeds {

	private static BlockSugarbeet crops;
	private static Block soilBlockID;

	public ItemSugarbeet() {
		super(ContentRegistry.BlockSugarBeets, Blocks.DIRT);
		this.setCreativeTab(Underworld.UnderworldTab);
		this.setRegistryName("sugarbeets");
		this.setUnlocalizedName(getRegistryName().toString());
		this.crops = (BlockSugarbeet) ContentRegistry.BlockSugarBeets;
		this.soilBlockID = Blocks.DIRT;

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, player.getHeldItem(hand)) && worldIn.isAirBlock(pos.up()) && this.crops.canSustainBush(state)) {
			worldIn.setBlockState(pos.up(), this.crops.getDefaultState());
			player.getHeldItem(hand).shrink(1);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

}
