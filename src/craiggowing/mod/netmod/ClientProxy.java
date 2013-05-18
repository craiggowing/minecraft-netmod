package craiggowing.mod.netmod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    @Override
    public void load()
    {
        MinecraftForgeClient.preloadTexture(ITEMS_PNG);
        RenderingRegistry.registerEntityRenderingHandler(EntityItemNet.class, new RenderNet(0));
    }
}