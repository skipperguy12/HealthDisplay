package com.github.skipperguy12.HealthDisplay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;





public class HealthDisplay extends JavaPlugin{
	static boolean divide_by_two;
	static String label;
	static HashMap<String,Integer> health = new HashMap<String,Integer>();
	public void onEnable() {

		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ML(), this);
		 PluginDescriptionFile pdfFile = getDescription();
	      System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
		//CONFIGURATION FILE GENERATION
		try {
    		Metrics metrics = new Metrics(this); metrics.start();
    		} catch (IOException e) { // Failed to submit the stats :-(
    		System.out.println("Error Submitting stats!");
    		}
				final File file = new File(getDataFolder() + File.separator + "config.yml");

				if (!file.exists()) {
					this.getLogger().info("Generating config files");
					this.getConfig().addDefault("label", "Hearts");
					this.getConfig().addDefault("divide_by_two", true);
					this.getConfig().options().copyDefaults(true);
					this.saveConfig();
				}
				if (this.getDataFolder().exists())
		        {
		         this.getLogger().info("File is there.");
		        }else
		        {
		         createConfig(new File(this.getDataFolder(), "config.yml"));
		         this.getLogger().info("File didn't exist");
		        }
				if(this.getConfig().getBoolean("divide_by_two")){
					divide_by_two = true;
				}else{
					divide_by_two = false;
				}
				if(this.getConfig().getString("label") != null){
				label = this.getConfig().getString("label");
				}else{
					label = "Hearts[x]";
				}
				ScoreboardManager.enableRegisterObjectives();
				//END FILE GENERATION
	}
	
	
	public void onDisable() {
		 PluginDescriptionFile pdfFile = getDescription();
	      System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!" );
	}
    public void createConfig(File f){
        InputStream cfgStream = this.getResource("config.yml");
        if (!this.getDataFolder().exists()){
            this.getDataFolder().mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ReadableByteChannel rbc = Channels.newChannel(cfgStream);
            fos.getChannel().transferFrom(rbc, 0, 1 << 24);
            fos.close();
         
        } catch (Exception e) {
          //log here is the instance of the logger obtained with getLogger().
            this.getLogger().info("There was an error in creating the config. Using bukkit methods to do so.");
            this.getConfig().options().copyDefaults(true);
            this.saveConfig();
        }
        }
}
