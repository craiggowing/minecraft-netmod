package craiggowing.mod.netmod;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;

public class ItemNetCow extends ItemNetFull
{
    public ItemNetCow(int par1) {
        super(par1);
        this.maxStackSize = 1;
        this.setIconIndex(4);
    }
	
    public String getItemNetName()
    {
    	return "Cow";
    }

    protected Entity getEntityToSpawn(World par2World, EntityPlayer par3EntityPlayer)
    {
    	return new EntityCow(par2World);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        //par3List.add(this.getItemNetName()); // TODO: Add attrs like age etc.
    }
   
}
