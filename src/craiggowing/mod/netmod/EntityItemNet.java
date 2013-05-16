package craiggowing.mod.netmod;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityCow;

public class EntityItemNet extends EntityThrowable
{
    public EntityItemNet(World par1World)
    {
        super(par1World);
    }

    public EntityItemNet(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityItemNet(World par1World, double par2, double par4, double par6)
    {
        super(par1World);
        this.setPosition(par2, par4, par6);
        this.yOffset = 0.0F;
    }
    
    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
    	boolean capturedAnimal = false;
    	if (!this.worldObj.isRemote)
    	{
	        if (par1MovingObjectPosition.entityHit != null)
	        {
	        	EntityItem dropped = null;
	        	if (par1MovingObjectPosition.entityHit instanceof EntityChicken)
	        	{
	        		capturedAnimal = true;
	        		dropped = this.dropItem(mod_NetMod.itemNetChicken.itemID, 1);
	        	}
	        	else if (par1MovingObjectPosition.entityHit instanceof EntitySheep)
	        	{
	        		capturedAnimal = true;
	        		dropped = this.dropItem(mod_NetMod.itemNetSheep.itemID, 1);
	        	}
	        	else if (par1MovingObjectPosition.entityHit instanceof EntityPig)
	        	{
	        		capturedAnimal = true;
	        		dropped = this.dropItem(mod_NetMod.itemNetPig.itemID, 1);
	        	}
	        	else if (par1MovingObjectPosition.entityHit instanceof EntityCow)
	        	{
	        		capturedAnimal = true;
	        		dropped = this.dropItem(mod_NetMod.itemNetCow.itemID, 1);
	        	}
	        	if (capturedAnimal == true && dropped != null)
	        	{
	        		NBTTagCompound nbt = new NBTTagCompound();
	        		par1MovingObjectPosition.entityHit.writeToNBT(nbt);
	        		dropped.getEntityItem().setTagCompound(nbt);
	        		par1MovingObjectPosition.entityHit.setDead();
	        	}
	        	else if (par1MovingObjectPosition.entityHit instanceof EntityLiving)
	        	{
	        		EntityLiving eh = (EntityLiving)par1MovingObjectPosition.entityHit;

	        		int var13 = MathHelper.floor_double((eh.posX * 2.0 + this.posX) / 3.0);
                    int var14 = MathHelper.floor_double((eh.posY + (eh.height / 2.0) + this.posY * 2.0) / 3.0);
                    int var15 = MathHelper.floor_double((eh.posZ * 2.0 + this.posZ) / 3.0);
                    
                    if (this.worldObj.getBlockId(var13, var14, var15) == 0) // air
                    {
                        if (!this.worldObj.isRemote && this.getThrower() instanceof EntityPlayer)
                        {
                        	EntityPlayer ep = (EntityPlayer)this.getThrower();
                        	if (ep.canPlayerEdit(var13, var14, var15, 0, null))
                        	{
	                        	this.worldObj.setBlockAndMetadataWithNotify(var13, var14, var15, mod_NetMod.blockNet.blockID, 1);
	                        	capturedAnimal = true;
                        	}
                        }
                    }
	        	}
	        }
	        
	        if (!capturedAnimal)
	        {
	        	this.dropItem(mod_NetMod.itemNet.itemID, 1);
	        }
    	}

        for (int var5 = 0; var5 < 8; ++var5)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }

    public String getTexture()
    {
        return CommonProxy.ITEMS_PNG;
    }
}
