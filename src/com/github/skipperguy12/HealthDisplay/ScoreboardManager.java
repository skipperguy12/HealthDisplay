package com.github.skipperguy12.HealthDisplay;






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
	public ScoreboardManager(HealthDisplay plugin) {

	}
	static Scoreboard sb = new Scoreboard();//Create new scoreboard
	static String name = HealthDisplay.label.toString();


	public static void enableRegisterObjectives(){
		sb.registerObjective(name, new ScoreboardBaseCriteria(name));

	}
	public static void setUpObjectives(Player p){


		Packet206SetScoreboardObjective packet = new Packet206SetScoreboardObjective(sb.getObjective(name), 0);//Create Scoreboard create packet
		Packet208SetScoreboardDisplayObjective display = new Packet208SetScoreboardDisplayObjective(2, sb.getObjective(name));//Create display packet set to under name mode


		sendPacket(p, packet);//Send Scoreboard create packet
		sendPacket(p, display);//Send the display packet

		updateHealth(p.getName());


	}
	public static void updateHealth(String p){
		
		ScoreboardScore scoreItem2 = sb.getPlayerScoreForObjective(p, sb.getObjective(name));//Create a new item with the players name
		if(HealthDisplay.divide_by_two){
			if(HealthDisplay.health.get(p) != null){
			scoreItem2.setScore(HealthDisplay.health.get(p)/2);//this will set the integer under to the player's health
			}else{
				Bukkit.getLogger().severe("NULL");
				Bukkit.getLogger().severe("" + HealthDisplay.health.get(p));
			}
			if(HealthDisplay.health == null){
				Bukkit.getLogger().severe("NULLarray");
				Bukkit.getLogger().severe("" + HealthDisplay.health.get(p));
			}
		}else{
			scoreItem2.setScore(HealthDisplay.health.get(p));//this will set the integer under to the player's health / 2
		}

		Packet207SetScoreboardScore pScoreItem2 = new Packet207SetScoreboardScore(scoreItem2, 0);//Create score packet 1
		for(Player player : Bukkit.getOnlinePlayers()){//send to all the players on the server
			sendPacket(player, pScoreItem2);//Send score update packet
			
		}
		
		
		
		
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			
			if(!pl.getName().equalsIgnoreCase(p)){
				ScoreboardScore scoreItem = sb.getPlayerScoreForObjective(pl.getName(), sb.getObjective(name));//Create a new item with the players name
				if(HealthDisplay.divide_by_two){
					scoreItem.setScore(HealthDisplay.health.get(pl.getName())/2);//this will set the integer under to the player's health
				}else{
					scoreItem.setScore(HealthDisplay.health.get(pl.getName()));//this will set the integer under to the player's health / 2
				}
				Packet207SetScoreboardScore pScoreItem = new Packet207SetScoreboardScore(scoreItem, 0);//Create score packet 1
				sendPacket(Bukkit.getPlayer(p), pScoreItem);//Send score update packet
			}
		}
	}

	private static void sendPacket(Player player,
			Packet p) {
		// TODO Auto-generated method stub
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);

	}

}
