package us.timberdnd.prisonheroes.manager;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import us.timberdnd.prisonheroes.PrisonHeroes;

public class PlayerEntity {
    
    FileConfiguration fc = PrisonHeroes.plugin.getConfig();
    Player player;
    int coins;
    public PlayerEntity(Player player) {
	this.player = player;
	load();
    }
    
    public PlayerEntity(UUID player) {
	this.player = Bukkit.getPlayer(player);
    }
    
    private void load() {
	coins = (Integer) SQLManager.getStatement(player.getUniqueId(), "coins");
    }
    
    public void unload() {
	SQLManager.setObject(player.getUniqueId(), "coins", coins);
    }
    
    public int getCoins() {
	return coins;
    }
    
    public boolean removeCoins(int amount) {
	if(getCoins() - amount < fc.getInt("min_balance")) {
	    return false;   
	}else{
	    coins -= amount;
	    return true;
	}
    }
    
    public boolean addCoins(int amount) {
	if(getCoins() + amount > fc.getInt("max_balance")) {
	    return false;
	}else{
	    coins += amount;
	    return true;
	}
    }
    
    public Player getPlayer() {
	return this.player;
    }
}