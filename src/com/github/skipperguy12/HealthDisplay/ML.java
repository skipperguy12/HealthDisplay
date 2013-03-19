package com.github.skipperguy12.HealthDisplay;

import net.minecraft.server.v1_5_R1.EntityPlayer;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ML implements Listener{
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e){
		Player p1 = (Player) e.getDamager();
		ScoreboardManager.update(p1.getName(), "[H]: " + p1.getHealth() + "", "");
		if(e.getEntity().getType().equals(EntityType.PLAYER)){
			Player p = (Player) e.getEntity();
		ScoreboardManager.update(p.getName(), "[H]: " + p.getHealth() + " ", "");
		
	}
	}
	
	@EventHandler
	public void respawn(PlayerRespawnEvent e){
		
			Player p1 = (Player) e.getPlayer();
			ScoreboardManager.update(p1.getName(), "[H]: " + p1.getHealth() + " ", "");
			
	}
	@EventHandler
	public void join(PlayerJoinEvent e){
		
			Player p1 = (Player) e.getPlayer();
			ScoreboardManager.update(p1.getName(), "[H]: " + p1.getHealth() + " ", "");
			
	}
	

}
