package com.eyeball.minecraft.mods.extraperipherals;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.Logger;

import com.eyeball.minecraft.mods.extraperipherals.api.IIconNeeded;
import com.eyeball.minecraft.mods.extraperipherals.block.BlockChatBox;
import com.eyeball.minecraft.mods.extraperipherals.block.BlockPlayerDetector;
import com.eyeball.minecraft.mods.extraperipherals.block.BlockRegistry;
import com.eyeball.minecraft.mods.extraperipherals.integration.ComputerCraftIntegrationUtil;
import com.eyeball.minecraft.mods.extraperipherals.integration.forestry.backpacks.ComputerEngineerBackpack;
import com.eyeball.minecraft.mods.extraperipherals.integration.waila.WailaCompat;
import com.eyeball.minecraft.mods.extraperipherals.item.ItemRegistry;
import com.eyeball.minecraft.mods.extraperipherals.network.packet.ChatMessagePacket;
import com.eyeball.minecraft.mods.extraperipherals.network.packet.ChatMessagePacket.ChatMessagePacketHandler;
import com.eyeball.minecraft.mods.extraperipherals.turtle.upgrade.RightClickTurtleUpgrade;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;

@Mod(modid = ExtraPeripheralsMod.MODID, version = ExtraPeripheralsMod.VERSION, name = ExtraPeripheralsMod.NAME, dependencies = ExtraPeripheralsMod.DEPENDENCIES)
public class ExtraPeripheralsMod {
	public static final String MODID = "ExtraPeripherals";
	public static final String NAME = "Extra Peripherals";
	public static final String VERSION = "BETA-1.2.4";
	public static final String DEPENDENCIES = "required-after:ComputerCraft@[1.70,];"// You
																						// know
																						// why!
			+ " after:PeripheralsPlusPlus;"// For chatbox! xD
			+ " after:MoarPeripherals;"// Also chatbox! ChatBox FTW!!
			+ " after:OpenPeripheralCore;"// Even more chat related stuff?
											// Really?
			+ " after:appliedenergistics2;"// To let you see things, craft and
											// manage everything
			+ " after:IC2;"// Power Management from your IPhone!
			+ " after:Thaumcraft;"// Play the game of "What's in that node?"
			+ " after:Forestry;"// Go scan that bee please. Also, I need a
								// backpack.
			+ " after:Railcraft;"// Tell me what's in that tank. I'm color
									// blind! (JK only)
			+ " after:ThermalExpansion;"// Oh no! We have a power shortage!
			+ " after:BuildCraft|Core;"// Should
			// come
			// packaged
			// together.
			// + " before:Waila;" // We all want to know what that is.
			+ " after:NotEnoughItems;" // Just for
										// fun.
			+ " after:EnderStorage;" // Find out about that chest!
			+ " after:WR-CBE|Core; after:WR-CBE|Addons; after:WR-CBE|Logic"; // RETher
																				// upgrade.
	public static Logger LOGGER;
	public static Configuration CONFIGRATIONMAIN;
	public static Configuration CONFIGRATIONMODULES;
	public static File configFolder;
	public static File suggested;
	public static final HashMap<String, Boolean> enabledConfigs = new HashMap<String, Boolean>();
	public static SimpleNetworkWrapper NETWORK;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOGGER = event.getModLog();
		LOGGER.info("(Pre-Initialization) started");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		NETWORK.registerMessage(ChatMessagePacketHandler.class,
				ChatMessagePacket.class, 0, Side.CLIENT);
		suggested = event.getModConfigurationDirectory();
		suggested.mkdirs();
		configFolder = suggested.getParentFile();
		File main = new File(suggested, "main.cfg");
		File modules = new File(suggested, "modules.cfg");
		CONFIGRATIONMAIN = new Configuration(main);
		CONFIGRATIONMODULES = new Configuration(modules);
		loadConfigs();
		CONFIGRATIONMAIN.save();
		CONFIGRATIONMODULES.save();
		stopWatch.stop();
		LOGGER.info("(Pre-Initialization ended ->" + stopWatch.getTime()
				+ "ms )");
	}

	private void loadConfigs() {
		enabledConfigs.put("redirectOut", CONFIGRATIONMAIN.getBoolean(
				"redirection", "ShouldRedirectOutput", true,
				"Should redirect STDOUT and STDERR to a custom Logger"));

		// Apply

		boolean redirectOut = enabledConfigs.get("redirectOut");
		if (redirectOut) {
			PrintStream defaultOut = System.out;
			PrintStream defaultErr = System.err;
			try {
				System.setOut(new RedirectPrintStream("OUT"));
				System.setErr(new RedirectPrintStream("ERR"));
				System.out.println("STDOUT Test!");
				System.err.println("STDERR Test!");
			} catch (Exception e) {
				System.setOut(defaultOut);
				System.setErr(defaultErr);
				LOGGER.info("Failed to redirect STDOUT!");
				e.printStackTrace();

			}
		}

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info("(Initialization) started");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		LOGGER.info("Loading ExtraPeripherals Blocks...");
		for (String key : BlockRegistry.blocks.keySet()) {
			GameRegistry.registerBlock(BlockRegistry.blocks.get(key), key);
		}
		LOGGER.info("Done!");
		LOGGER.info("Now loading ExtraPeripherals Items...");
		for (String key : ItemRegistry.items.keySet()) {
			GameRegistry.registerItem(ItemRegistry.items.get(key), key);
		}
		LOGGER.info("Now loading ExtraPeripherals Recpies...");
		LOGGER.warn("WARNING: The mouse requires any rubber adding mod to be crafted!");
		for (IRecipe recipe : RecipeRegistry.recipes.values()) {
			GameRegistry.addRecipe(recipe);
		}

		LOGGER.info("Done!");
		LOGGER.info("Now adding TileEntities...");
		for (String key : BlockRegistry.blocks.keySet()) {
			try {
				String className = key.substring(0, 1).toUpperCase()
						+ key.substring(1);
				@SuppressWarnings("unchecked")
				Class<? extends TileEntity> clazz = (Class<? extends TileEntity>) Class
						.forName("com.eyeball.minecraft.mods.extraperipherals.tile.Tile"
								+ className);
				LOGGER.info("Registering TE with class " + clazz.getName());
				GameRegistry.registerTileEntity(clazz, key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Done!");
		LOGGER.info("Now adding ExtraPeripheral's blocks as Peripherals...");
		ComputerCraftIntegrationUtil
				.registerPeripheralHandler(new BlockChatBox());
		ComputerCraftIntegrationUtil
				.registerPeripheralHandler(new BlockPlayerDetector());
		if (Loader.isModLoaded("IC2")) {
			LOGGER.info("IC2 is loaded! Attempting IC2 Integration...");
			ComputerCraftIntegrationUtil
					.registerPeripheralHandler(Reference.PeripheralHandlers.IC2POWERHANDLER);
		}
		if (Loader.isModLoaded("ThermalExpansion")) {
			LOGGER.info("ThermalExpansion is loaded! Attempting TE4 Integration...");
			ComputerCraftIntegrationUtil
					.registerPeripheralHandler(Reference.PeripheralHandlers.TE4POWERHANDLER);
		}

		if (Loader.isModLoaded("Forestry")) {
			LOGGER.info("Forestry is loaded! Attempting Forestry Integration...");
			ComputerEngineerBackpack computerEngineerBackpack = new ComputerEngineerBackpack();
			Item backpackT1 = BackpackManager.backpackInterface.addBackpack(
					computerEngineerBackpack, EnumBackpackType.T1);
			Item backpackT2 = BackpackManager.backpackInterface.addBackpack(
					computerEngineerBackpack, EnumBackpackType.T2);
			backpackT2.setCreativeTab(EPCreativeTab.CREATIVETAB);
			backpackT1.setCreativeTab(EPCreativeTab.CREATIVETAB);
			GameRegistry.registerItem(backpackT1, "ccbackpack1");
			GameRegistry.registerItem(backpackT2, "ccbackpack2");
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(backpackT1), "sws", "dcd", "sws", 's',
					Items.string, 'w', Blocks.wool, 'd',
					ComputerCraftIntegrationUtil.getCCItem("disk"), 'c',
					Blocks.chest));
			ItemStack silkStack = new ItemStack(GameRegistry.findItem(
					"Forestry", "craftingMaterial"), 1, 3);
			RecipeManagers.carpenterManager.addRecipe(30, new FluidStack(
					FluidRegistry.WATER, 1000), null,
					new ItemStack(backpackT2), "SDS", "SBS", "SSS", 'S',
					silkStack, 'D', "gemDiamond", 'B',
					new ItemStack(backpackT1));
		}

		LOGGER.info("Peripherals done! Now doing turtle upgrades!");
		ComputerCraftIntegrationUtil
				.registerTurtleUpgrade(new RightClickTurtleUpgrade());
		LOGGER.info("Sucessfully registered " + EPCreativeTab.upgrades.size()
				+ " turtle upgrades.");
		LOGGER.info("Done!");
		LOGGER.info("Now loading Textures...");
		for (ITurtleUpgrade upgrade : EPCreativeTab.upgrades) {
			if (upgrade instanceof IIconNeeded) {
				IIconNeeded icon = (IIconNeeded) upgrade;
				icon.registerIcons(Minecraft.getMinecraft()
						.getTextureMapBlocks());
			}
		}

		stopWatch.stop();
		LOGGER.info("(Initialization ended ->" + stopWatch.getTime() + "ms )");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOGGER.info("(Post-Initialization) started");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		LOGGER.info("Checking mod integration...");
		String deps = Reference.DEPENDENCIES;
		String[] mods = deps.split(";");
		for (String mod : mods) {

			String modID = null;

			mod = mod.trim();

			if (mod.isEmpty()) {
				continue;
			} else if (mod.startsWith("required-after:")) {
				modID = mod.substring("required-after:".length());
			} else if (mod.startsWith("after:")) {
				modID = mod.substring("after:".length());
			} else {
				LOGGER.info("Somehow got an invalid modID during checking! ModID: "
						+ mod);
				continue;
			}
			if (Loader.isModLoaded(modID)) {
				LOGGER.info("Integration enabled: " + modID);
			} else {
				LOGGER.info("Integration disabled: " + modID);
			}
		}

		LOGGER.info("Attempting Waila Integration (com.eyeball.minecraft.mods.extraperipherals.integration.WailaCompat)");
		if (FMLInterModComms.sendMessage("Waila", "register",
				WailaCompat.class.getName() + ".register")) {
			LOGGER.info("Sucess!");
		} else {
			LOGGER.info("Failed. :( Did you miss you waila?");
		}
		stopWatch.stop();
		LOGGER.info("(Post-Initialization ended ->" + stopWatch.getTime()
				+ "ms )");
	}

	@EventHandler
	public void loadComplete(FMLLoadCompleteEvent event) {
		LOGGER.info("Minecraft is done loading!");
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
	}
}
