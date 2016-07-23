package us.timberdnd.prisonheroes.manager;

import org.bukkit.entity.Player;

public class PlayerEntity {
    
    Player player;
    public PlayerEntity(Player player) {
	this.player = player;
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