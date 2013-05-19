package craiggowing.mod.netmod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.SidedProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StringTranslate;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

@Mod(modid = "NetMod", name = "Net Mod", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class mod_NetMod
{
    @Instance("NetMod")
    public static mod_NetMod instance;

    @SidedProxy(clientSide="craiggowing.mod.netmod.ClientProxy", serverSide="craiggowing.mod.netmod.CommonProxy")
    public static CommonProxy proxy;

    public static Item itemNet;
    public static Item itemNetFull;
    public static Block blockNet;

    public static int mobTotal = 28;
    public static Class[] itemClasses = new Class[] {
            EntityChicken.class,
            EntitySheep.class,
            EntityPig.class,
            EntityCow.class,
            EntityBat.class,
            EntityMooshroom.class,
            EntityOcelot.class,
            EntitySquid.class,
            EntityVillager.class,
            EntityWolf.class,
            EntityBlaze.class,
            EntityCaveSpider.class,
            EntityCreeper.class,
            EntityEnderman.class,
            EntityGhast.class,
            EntityGiantZombie.class,
            EntityIronGolem.class,
            EntityMagmaCube.class,
            EntityPigZombie.class,
            EntitySilverfish.class,
            EntitySkeleton.class,
            EntitySlime.class,
            EntitySnowman.class,
            EntitySpider.class,
            EntityWitch.class,
            EntityZombie.class,
            EntityDragon.class,
            EntityWither.class
        };
    public static String[][] itemNames = new String[][] {
            {"entity.Chicken.name", "Chicken"},
            {"entity.Sheep.name", "Sheep"},
            {"entity.Pig.name", "Pig"},
            {"entity.Cow.name", "Cow"},
            {"entity.Bat.name", "Bat"},
            {"entity.MushroomCow.name", "MushroomCow"},
            {"entity.Ozelot.name", "Ozelot"},
            {"entity.Squid.name", "Squid"},
            {"entity.Villager.name", "Villager"},
            {"entity.Wolf.name", "Wolf"},
            {"entity.Blaze.name", "Blaze"},
            {"entity.CaveSpider.name", "CaveSpider"},
            {"entity.Creeper.name", "Creeper"},
            {"entity.Enderman.name", "Enderman"},
            {"entity.Ghast.name", "Ghast"},
            {"entity.Giant.name", "Giant"},
            {"entity.VillagerGolem.name", "VillagerGolem"},
            {"entity.LavaSlime.name", "LavaSlime"},
            {"entity.PigZombie.name", "PigZombie"},
            {"entity.Silverfish.name", "Silverfish"},
            {"entity.Skeleton.name", "Skeleton"},
            {"entity.Slime.name", "Slime"},
            {"entity.SnowMan.name", "SnowMan"},
            {"entity.Spider.name", "Spider"},
            {"entity.Witch.name", "Witch"},
            {"entity.Zombie.name", "Zombie"},
            {"entity.EnderDragon.name", "Pig"}, // Ender Dragon does not work well with Spawn Blocks
            {"entity.WitherBoss.name", "Pig"} // Wither does not work well with Spawn Blocks
        };
    public static float[][] itemProbs = new float[][] {
        // {Capture Prob, Break Prob}
        {0.8f, 0.05f}, // Chicken
        {0.7f, 0.05f}, // Sheep
        {0.6f, 0.05f}, // Pig
        {0.7f, 0.05f}, // Cow
        {0.3f, 0.1f}, // Bat
        {0.2f, 0.1f}, // Mushroom Cow
        {0.1f, 0.4f}, // Ocelot
        {0.25f, 0.05f}, // Squid
        {0.2f, 0.25f}, // Villager
        {0.2f, 0.4f}, // Wolf
        {0.05f, 1.0f}, // Blaze
        {0.2f, 0.2f}, // Cave Spider
        {0.2f, 0.1f}, // Creeper
        {0.02f, 0.3f}, // Enderman
        {0.1f, 0.3f}, // Ghast
        {0.2f, 0.4f}, // Giant
        {0.3f, 0.6f}, // Golem
        {0.2f, 1.0f}, // Magma Cube
        {0.1f, 0.5f}, // Zombie Pigman
        {0.05f, 0.0f}, // Silverfish
        {0.2f, 0.4f}, // Skeleton
        {0.3f, 0.0f}, // Slime
        {0.4f, 0.1f}, // Snowman
        {0.2f, 0.2f}, // Spider
        {0.2f, 0.25f}, // Witch
        {0.3f, 0.1f}, // Zombie
        {0.01f, 0.2f}, // Ender Dragon
        {0.02f, 0.2f} // Wither
    };

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @Init
    public void init(FMLInitializationEvent event)
    {
        proxy.load();

        blockNet = (new BlockNet(2801, 1)).setLightOpacity(1).setHardness(4.0F).setBlockName("blocknet");
        itemNet = new ItemNet(4001).setItemName("itemnet");
        itemNetFull = new ItemNetFull(4002).setItemName("itemnetfull");
        ModLoader.registerBlock(blockNet);
        ModLoader.addRecipe(new ItemStack(this.itemNet, 4), new Object[] { "DdD", "ddd", "DdD", 'D', Block.cobblestone, 'd', Block.fenceIron});
        ModLoader.addShapelessRecipe(new ItemStack(this.blockNet, 1), new Object[] {this.itemNet});
        ModLoader.addShapelessRecipe(new ItemStack(this.itemNet, 1), new Object[] {this.blockNet});
        EntityRegistry.registerModEntity(EntityItemNet.class, "Net", 1, instance, 64, 10, true);
        BlockDispenser.dispenseBehaviorRegistry.putObject(this.itemNet, new BehaviorNetDispense(ModLoader.getMinecraftServerInstance()));
        BlockDispenser.dispenseBehaviorRegistry.putObject(this.itemNetFull, new BehaviorNetFullDispense(ModLoader.getMinecraftServerInstance()));

        // Nets in all places
        ItemStack net = new ItemStack(itemNet, 1, 0);
        WeightedRandomChestContent tmp = new WeightedRandomChestContent(net, 1, 16, 1);
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR  ).addItem(tmp);
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(tmp);
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(tmp);
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR ).addItem(tmp);
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY  ).addItem(tmp);
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING ).addItem(tmp);
        ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH ).addItem(tmp);
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER ).addItem(tmp);
        // Golems in Villages
        net = new ItemStack(itemNetFull, 1, 16);
        tmp = new WeightedRandomChestContent(net, 1, 1, 1);
        ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH ).addItem(tmp);
        // Skeletons, Zombies and Creepers in Jungle dispensers
        net = new ItemStack(itemNetFull, 1, 12);
        tmp = new WeightedRandomChestContent(net, 1, 5, 7);
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER ).addItem(tmp);
        net = new ItemStack(itemNetFull, 1, 20);
        tmp = new WeightedRandomChestContent(net, 1, 5, 7);
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER ).addItem(tmp);
        net = new ItemStack(itemNetFull, 1, 25);
        tmp = new WeightedRandomChestContent(net, 1, 5, 7);
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER ).addItem(tmp);
        // Villager in Mineshaft
        net = new ItemStack(itemNetFull, 1, 8);
        tmp = new WeightedRandomChestContent(net, 1, 1, 1);
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR ).addItem(tmp);

        // Translations
        try
        {
            LanguageRegistry.instance().loadLocalization("/craiggowing/mod/netmod/lang/en_US.lang", "en_US", false);
        }
        catch(Exception e) {}
        try
        {
            BufferedReader var2 = new BufferedReader(new InputStreamReader(StringTranslate.class.getResourceAsStream("/craiggowing/mod/netmod/lang/languages.txt"), "UTF-8"));
            for (String var3 = var2.readLine(); var3 != null; var3 = var2.readLine())
            {
                String[] var4 = var3.split("=");
                if (var4 != null && var4.length == 2)
                {
                    LanguageRegistry.instance().loadLocalization("/craiggowing/mod/netmod/lang/"+var4[0]+".lang", var4[0], false);
                }
            }
        }
        catch(Exception e) {}
    }

    @PostInit
    public static void postInit(FMLPostInitializationEvent event)
    {
    }
}
