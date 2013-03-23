package com.github.skipperguy12.HealthDisplay;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import net.minecraft.server.v1_5_R2.Packet;
import net.minecraft.server.v1_5_R2.Packet206SetScoreboardObjective;
import net.minecraft.server.v1_5_R2.Packet207SetScoreboardScore;
import net.minecraft.server.v1_5_R2.Packet208SetScoreboardDisplayObjective;
import net.minecraft.server.v1_5_R2.Scoreboard;
import net.minecraft.server.v1_5_R2.ScoreboardBaseCriteria;
import net.minecraft.server.v1_5_R2.ScoreboardScore;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_5_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;



public class ScoreboardManager {
	private final HealthDisplay plugin;


	public ScoreboardManager(HealthDisplay plugin) {
		this.plugin = plugin;

	}
	static Scoreboard sb = new Scoreboard();//Create new scoreboard
	static String name = "Health";
	
	public static void enableRegisterObjectives(){
		sb.registerObjective(name, new ScoreboardBaseCriteria(name));
	}
	public static void setUpObjectives(Player p){
		
		 
		Packet206SetScoreboardObjective packet = new Packet206SetScoreboardObjective(sb.getObjective(name), 0);//Create Scoreboard create packet
		Packet208SetScoreboardDisplayObjective display = new Packet208SetScoreboardDisplayObjective(2, sb.getObjective(name));//Create display packet set to under name mode
		
		
			sendPacket(p, packet);//Send Scoreboard create packet
			sendPacket(p, display);//Send the display packet
		
		
	}
	public static void updateHealth(String p){
		ScoreboardScore scoreItem2 = sb.getPlayerScoreForObjective(p, sb.getObjective("Health"));//Create a new item with the players name
		scoreItem2.setScore(Bukkit.getPlayer(p).getHealth());//this will set the integer under to the player's name specified above to 42
		 
		Packet207SetScoreboardScore pScoreItem2 = new Packet207SetScoreboardScore(scoreItem2, 0);//Create score packet 1
		for(Player player : Bukkit.getOnlinePlayers()){//send to all the players on the server
		sendPacket(player, pScoreItem2);//Send score update packet
	}
	}

	private static void sendPacket(Player player,
			Packet p) {
		// TODO Auto-generated method stub
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);
		
	}
	 
}
