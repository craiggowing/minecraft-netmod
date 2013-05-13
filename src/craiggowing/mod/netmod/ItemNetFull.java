package craiggowing.mod.netmod;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySilverfish;

public class ItemNetFull extends ItemNet
{
    public ItemNetFull(int par1) {
        super(par1);
        this.maxStackSize = 1;
        this.setIconIndex(0);
    }
	
    public String getItemNetName()
    {
    	return "Full";
    }

    private Entity getEntityToSpawn(World par2World, EntityPlayer par3EntityPlayer)
    {
    	return new EntitySilverfish(par2World);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(this.getItemNetName());
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }
        
        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        
        if (!par2World.isRemote)
        {
        	Entity spawnEntity = this.getEntityToSpawn(par2World, par3EntityPlayer);
			this.setEntityAttributes(spawnEntity, par2World, par3EntityPlayer);
        	par2World.spawnEntityInWorld(spawnEntity);
        }
        
        return par1ItemStack;
    }
    
    private void setEntityAttributes(Entity spawnEntity, World par2World, EntityPlayer par3EntityPlayer)
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
    }
   
}
