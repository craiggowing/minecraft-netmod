package craiggowing.mod.netmod;

import java.util.List;

import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;

public class ItemNetSheep extends ItemNetFull
{
	
    public ItemNetSheep(int par1) {
        super(par1);
        this.maxStackSize = 1;
        this.setIconIndex(2);
    }
	
    public String getItemNetName()
    {
    	return "Sheep";
    }

    protected EntityLiving getEntityToSpawn(World par2World, EntityPlayer par3EntityPlayer)
    {
    	return new EntitySheep(par2World);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        NBTTagCompound tag = par1ItemStack.getTagCompound();
        if (tag != null)
        {
	        if (tag.hasKey("Sheared"))
	        {
	        	par3List.add("Sheared: " + (tag.getBoolean("Sheared") ? "Yes" : "No"));
	        }
	        if (tag.hasKey("Color"))
	        {
	        	par3List.add("Fleece: " + ItemDye.dyeColorNames[tag.getByte("Color")]); // Avoiding the argument of Colour vs Color... Colour is correct :P
	        }
        }
    }
   
}
