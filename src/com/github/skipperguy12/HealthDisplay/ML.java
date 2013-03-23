package com.github.skipperguy12.HealthDisplay;

import net.minecraft.server.v1_5_R2.EntityPlayer;

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
		ScoreboardManager.updateHealth(p1.getName());
		if(e.getEntity().getType().equals(EntityType.PLAYER)){
			Player p = (Player) e.getEntity();
		ScoreboardManager.updateHealth(p.getName());
		
	}
	}
	
	@EventHandler
	public void respawn(PlayerRespawnEvent e){
		
			Player p1 = (Player) e.getPlayer();
			ScoreboardManager.updateHealth(p1.getName());
			
	}
	@EventHandler
	public void join(PlayerJoinEvent e){
		
			Player p1 = (Player) e.getPlayer();
			ScoreboardManager.setUpObjectives(p1);
			
	}
	

}
