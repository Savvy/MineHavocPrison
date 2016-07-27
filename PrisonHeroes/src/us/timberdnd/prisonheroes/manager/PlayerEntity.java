package us.timberdnd.prisonheroes.manager;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerEntity {
    
    Player player;
    public PlayerEntity(Player player) {
	this.player = player;
    }
    
    public PlayerEntity(UUID player) {
	this.player = Bukkit.getPlayer(player);
    }
    
    public int getCoins() {
	return 0;
    }
    
    public boolean removeCoins() {
	return false;
    }
    
    public boolean addCoins() {
	return false;
    }
    
    public Player getPlayer() {
	return this.player;
    }
}