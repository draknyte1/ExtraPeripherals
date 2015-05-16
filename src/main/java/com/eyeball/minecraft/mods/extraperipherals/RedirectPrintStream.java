package com.eyeball.minecraft.mods.extraperipherals;

import java.io.File;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RedirectPrintStream extends PrintStream {

	Logger LOGGER = null;

	String type;
	
	public RedirectPrintStream(String type) throws Exception {
		super(new File(System.getProperty("user.home") + File.separator
				+ "MC.out"));
		this.type = type;
		LOGGER = LogManager.getLogger("ExtraPeripherals / " + type);
	}

	public void println(String x) {
		LOGGER.info(x);
	};

}
