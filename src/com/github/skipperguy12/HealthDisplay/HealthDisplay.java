package com.github.skipperguy12.HealthDisplay;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class HealthDisplay extends JavaPlugin{
	public void onEnable() {

		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ML(), this);

		
	}
	
	
	public void onDisable() {
	
	}
}
