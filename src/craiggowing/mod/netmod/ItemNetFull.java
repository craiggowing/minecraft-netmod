package craiggowing.mod.netmod;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class ItemNetFull extends ItemNet
{
    public ItemNetFull(int par1) {
        super(par1);
        this.maxStackSize = 1;
        this.setIconIndex(0);
    }
	
    public String getItemNetName()
    {
    	return "Pig";
    }

    public String getMobName()
    {
    	return this.getItemNetName();
    }
    
    protected EntityLiving getEntityToSpawn(World par2World, EntityPlayer par3EntityPlayer)
    {
    	return new EntityPig(par2World);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(this.getItemNetName());
        NBTTagCompound tag = par1ItemStack.getTagCompound();
        if (tag != null)
        {
	        if (tag.hasKey("Health"))
	        {
	        	par3List.add("Health: " + tag.getShort("Health"));
	        }
	        if (tag.hasKey("Age"))
	        {
	        	par3List.add("Age: " + tag.getInteger("Age"));
	        }
	        if (tag.hasKey("InLove"))
	        {
	        	par3List.add("Love Meter: " + tag.getInteger("InLove"));
	        }
        }
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }
        
        /* Are we clicking on a diamond block? */
        float var4 = 1.0F;
        double var5 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)var4;
        double var7 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)var4 + 1.62D - (double)par3EntityPlayer.yOffset;
        double var9 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)var4;
        MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (var12 != null && var12.typeOfHit == EnumMovingObjectType.TILE)
        {
            int var13 = var12.blockX;
            int var14 = var12.blockY;
            int var15 = var12.blockZ;

            if (par2World.canMineBlock(par3EntityPlayer, var13, var14, var15) && par3EntityPlayer.canPlayerEdit(var13, var14, var15, var12.sideHit, par1ItemStack))
            {
                if (par2World.getBlockId(var13, var14, var15) == Block.blockDiamond.blockID)
                {
                    if (!par2World.isRemote)
                    {
	                	par2World.setBlockAndMetadataWithNotify(var13, var14, var15, Block.mobSpawner.blockID, 1);
	                	TileEntity te = par2World.getBlockTileEntity(var13, var14, var15);
	                	if (te != null && te instanceof TileEntityMobSpawner)
	                	{
	                		TileEntityMobSpawner tems = (TileEntityMobSpawner)te; 
	                		tems.setMobID(this.getMobName());
	                	}
                    }
                    return par1ItemStack;
                }
            }
        }
        
        /* Throw creature if not */
        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        
        if (!par2World.isRemote)
        {
        	EntityLiving spawnEntity = this.getEntityToSpawn(par2World, par3EntityPlayer);
			this.setEntityAttributes(par1ItemStack, spawnEntity, par2World, par3EntityPlayer);
        	par2World.spawnEntityInWorld(spawnEntity);
        }
        
        return par1ItemStack;
    }
    
    private void setEntityAttributes(ItemStack par1ItemStack, EntityLiving spawnEntity, World par2World, EntityPlayer par3EntityPlayer)
    {
    	spawnEntity.setLocationAndAngles(par3EntityPlayer.posX, par3EntityPlayer.posY + (double)par3EntityPlayer.getEyeHeight(), par3EntityPlayer.posZ, par3EntityPlayer.rotationYaw, par3EntityPlayer.rotationPitch);
    	spawnEntity.posX -= (double)(MathHelper.cos(spawnEntity.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
    	spawnEntity.posY -= 0.10000000149011612D;
    	spawnEntity.posZ -= (double)(MathHelper.sin(spawnEntity.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
    	spawnEntity.setPosition(spawnEntity.posX, spawnEntity.posY, spawnEntity.posZ);
    	spawnEntity.yOffset = 0.0F;
        float var3 = 0.4F;
        spawnEntity.motionX = (double)(-MathHelper.sin(spawnEntity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(spawnEntity.rotationPitch / 180.0F * (float)Math.PI) * var3) * 2.0;
        spawnEntity.motionZ = (double)(MathHelper.cos(spawnEntity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(spawnEntity.rotationPitch / 180.0F * (float)Math.PI) * var3) * 2.0;
        spawnEntity.motionY = (double)(-MathHelper.sin((spawnEntity.rotationPitch) / 180.0F * (float)Math.PI) * var3) * 2.0;
        if (par1ItemStack.getTagCompound() != null)
        {
        	spawnEntity.readEntityFromNBT(par1ItemStack.getTagCompound());
        }
    }
   
}
