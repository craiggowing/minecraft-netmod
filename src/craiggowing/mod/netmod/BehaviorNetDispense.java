package craiggowing.mod.netmod;

import net.minecraft.entity.IProjectile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;

public class BehaviorNetDispense extends BehaviorProjectileDispense
{
    /** Reference to the MinecraftServer object. */
    final MinecraftServer mcServer;

    public BehaviorNetDispense(MinecraftServer par1)
    {
        this.mcServer = par1;
    }

    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    protected IProjectile getProjectileEntity(World par1World, IPosition par2IPosition)
    {
    	EntityItemNet var3 = new EntityItemNet(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ());
        return var3;
    }
}

