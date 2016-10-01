package se.Matryoshika.Underworld.Content.TileEntity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.API.MetamorphicTableRecipes;
import se.Matryoshika.Underworld.API.TableRecipes;
import se.Matryoshika.Underworld.Content.Blocks.BlockMetamorphicTable;
import se.Matryoshika.Underworld.Content.TileEntity.InvHandler.IContainerCallbacks;
import se.Matryoshika.Underworld.Content.TileEntity.InvHandler.IWorldContainer;
import se.Matryoshika.Underworld.Content.TileEntity.InvHandler.ItemHandlerItem;
import se.Matryoshika.Underworld.Content.TileEntity.InvHandler.ItemHandlerNameable;
import se.Matryoshika.Underworld.Content.TileEntity.InvHandler.NameableCombinedInvWrapper;
import se.Matryoshika.Underworld.Content.TileEntity.InvHandler.TEInventoryHandler;

public class TileMetamorphicTable extends TEInventoryHandler implements IContainerCallbacks, ITickable{
	
	public boolean isActivated = false;
	public boolean shouldGlitter = false;
	public static float risingFrame = 1f;
	public boolean shouldBoom = false;
	public boolean checkedBoom = false;
	
	public boolean renderMultiblock = false;
	public int multiblockTimer = 0;
	
	public TileMetamorphicTable(){
		this.setName("Metamorphic_table");
	}
	
	private static final int INVENTORY_SIZE = 1;
	
	private static final ITextComponent DEFAULT_NAME = new TextComponentTranslation("container."+Underworld.MODID+":metamorphic_table");

	@Override
	protected IItemHandler createInventory() {
		return new ItemHandlerItem(INVENTORY_SIZE, DEFAULT_NAME, (IWorldContainer) this.worldObj);
	}

	@Override
	public Container createContainer(EntityPlayer player) {
		final IItemHandlerModifiable playerInventory = (IItemHandlerModifiable) player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		final IItemHandler playerInventoryWrapper = new NameableCombinedInvWrapper(player.inventory, playerInventory);

		return new ContainerMetamorphicTable(playerInventoryWrapper, inventory, player, this);
	}

	@Override
	public void onContainerOpened(EntityPlayer player) {
		
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64;
	}
	
	public void setDisplayName(String displayName){
		((ItemHandlerNameable) inventory).setDisplayName(new TextComponentString(displayName));
	}

	@Override
	public void update() {
		
		dispatchTEToNearbyPlayers(getWorld(), getPos());
		BlockMetamorphicTable table = (BlockMetamorphicTable)worldObj.getBlockState(pos).getBlock();
		if(renderMultiblock)
			multiblockTimer++;
		
		if(multiblockTimer >= 200){
			renderMultiblock = false;
			multiblockTimer = 0;
		}
		
		//Set to true further down. Should only be active one tick.
		if(shouldBoom){
			shouldBoom = false;
			Underworld.proxy.boomParticles(worldObj, pos, new Random());
		}
			
		
		if(shouldGlitter){
			risingFrame += 0.10f;
			
			Underworld.proxy.trailParticles(worldObj, pos, new Random());
		}
			
			
		
		if(risingFrame >= 15f){
			if(worldObj.getBlockState(pos.north(3)).getBlock() == table.ACTIVATION_BLOCK &&
				worldObj.getBlockState(pos.east(3)).getBlock() == table.ACTIVATION_BLOCK &&
				worldObj.getBlockState(pos.south(3)).getBlock() == table.ACTIVATION_BLOCK &&
				worldObj.getBlockState(pos.west(3)).getBlock() == table.ACTIVATION_BLOCK
					){
				isActivated = true;
				worldObj.setBlockToAir(pos.north(3));
				worldObj.setBlockToAir(pos.east(3));
				worldObj.setBlockToAir(pos.south(3));
				worldObj.setBlockToAir(pos.west(3));
				if(worldObj.isRemote){
					worldObj.getBlockState(pos).getBlock().randomDisplayTick(worldObj.getBlockState(pos), worldObj, pos, new Random());
				}
					
				
				shouldGlitter = false;
				risingFrame = 1f;
				shouldBoom = true;
				worldObj.scheduleUpdate(pos, worldObj.getBlockState(pos).getBlock(), 0);
				markDirty();
			}
			else
				return;
			
		}
		
		if(table.isBlocked(worldObj, pos))
			return;
		
		if(this.inventory.getStackInSlot(0) != null){
			tryCraft();
		}
		
	}
	
	
	public void tryCraft(){
		BlockMetamorphicTable table = (BlockMetamorphicTable) this.worldObj.getBlockState(pos).getBlock();
		
		if(table.isBlocked(worldObj, pos))
			return;
		
		TableRecipes recipe = null;
		if(!worldObj.isRemote) {
			if(hasValidRecipe()){
				
				for(TableRecipes recipe_ : MetamorphicTableRecipes.metamorphicTableRecipes) {
					if(recipe_.matches((IItemHandler) this.inventory)) {
						recipe = recipe_;
						break;
					}
				}
				//System.out.println("has valid recipe");
				ItemStack output = recipe.result().copy();
				this.inventory.extractItem(0, this.inventory.getStackInSlot(0).stackSize, false);
				
				EntityItem entity = new EntityItem(worldObj, this.pos.getX()+0.5, this.pos.getY()+0.71, this.pos.getZ()+0.5, output);
				worldObj.spawnEntityInWorld(entity);
				entity.motionX =0; entity.motionY =0; entity.motionZ =0;
				entity.setPickupDelay(20);
			}
		}
	}
	
	public boolean hasValidRecipe() {
		
		for(TableRecipes recipe : MetamorphicTableRecipes.metamorphicTableRecipes){
			if(recipe.matches((IItemHandler) this.inventory))
				return true;
		}

		return false;
	}
	
	public static boolean hasValidInput(ItemStack stack){
		
		for(TableRecipes recipe : MetamorphicTableRecipes.metamorphicTableRecipes){
			for(ItemStack dstack : recipe.getIngredient()){
				if(dstack.getItem() == stack.getItem())
					return true;
			}
		}
		
		return false;
	}
	
	public static ItemStack getOutputFromInput(ItemStack input){
		
		return MetamorphicTableRecipes.registry.get(String.valueOf(input.getItem())+String.valueOf(input.getMetadata()));
	}
	
	@Override
	public boolean shouldRenderInPass(int pass){
        return pass == 0;
    }
	
	public static void dispatchTEToNearbyPlayers(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if(tile != null)
			dispatchTEToNearbyPlayers(tile);
	}
	
	public static void dispatchTEToNearbyPlayers(TileEntity tile) {
		World world = tile.getWorld();
		List players = world.playerEntities;
		if(!tile.getWorld().isRemote){
			for(Object player : players)
				if(player instanceof EntityPlayerMP) {
					EntityPlayerMP mp = (EntityPlayerMP) player;
					if(pointDistancePlane(mp.posX, mp.posZ, tile.getPos().getX() + 0.5, tile.getPos().getZ() + 0.5) < 64){
						final SPacketUpdateTileEntity updatePacket = tile.getUpdatePacket();
						if(updatePacket != null)
							mp.connection.sendPacket(tile.getUpdatePacket());
					}
						
				}
		}
		
	}
	
	public static float pointDistancePlane(double x1, double y1, double x2, double y2) {
		return (float) Math.hypot(x1 - x2, y1 - y2);
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		isActivated = compound.getBoolean("metamorphictable_activated");
		shouldGlitter = compound.getBoolean("metamorphictable_glitter");
		risingFrame = compound.getFloat("metamorphictable_risingframe");
		}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("metamorphictable_activated", isActivated);
		compound.setBoolean("metamorphictable_glitter", shouldGlitter);
		compound.setFloat("metamorphictable_risingframe", risingFrame);
		return compound;
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Nullable
	@Override
    public SPacketUpdateTileEntity getUpdatePacket(){
		NBTTagCompound nbttagcompound = this.writeToNBT(new NBTTagCompound());
        return new SPacketUpdateTileEntity(this.pos, 2, nbttagcompound);
    }

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		
		readFromNBT(pkt.getNbtCompound());
	}

}
