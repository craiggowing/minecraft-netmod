package craiggowing.mod.netmod;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;

public class ItemNetChicken extends ItemNetFull
{
    public ItemNetChicken(int par1) {
        super(par1);
        this.maxStackSize = 1;
        this.setIconIndex(1);
    }
	
    public String getItemNetName()
    {
    	return "Chicken";
    }

    protected Entity getEntityToSpawn(World par2World, EntityPlayer par3EntityPlayer)
    {
    	return new EntityChicken(par2World);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        //par3List.add(this.getItemNetName()); // TODO: Add attrs like age etc.
    }
   
}
