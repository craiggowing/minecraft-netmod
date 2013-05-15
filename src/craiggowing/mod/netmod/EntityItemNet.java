package craiggowing.mod.netmod;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
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
