package us.timberdnd.prisonheroes.manager;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import us.timberdnd.prisonheroes.PrisonHeroes;
import us.timberdnd.prisonheroes.heroes.Heroes;
import us.timberdnd.prisonheroes.heroes.hero.Batman;
import us.timberdnd.prisonheroes.heroes.hero.Hawkeye;
import us.timberdnd.prisonheroes.heroes.hero.Superman;
import us.timberdnd.prisonheroes.heroes.hero.Wolverine;

public class PlayerEntity {

    FileConfiguration fc = PrisonHeroes.plugin.getConfig();
    Player player;
    int coins;
    String ability;
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

    public Heroes getAbility() {
	switch (ability) {
	    case "superman":
		return new Superman();
	    case "batman":
		return new Batman();
	    case "hawkeye":
		return new Hawkeye();
	    case "wolverine":
		return new Wolverine();
	    default:
		return null;
	}  
    }

    public boolean activateAbility() {
	if(!(isCooldown())) {
	    getAbility().activateAbility();
	    return true;
	}else{
	    return false;    
	}
    }

    public boolean isCooldown() {
	return false;
    }

    public Player getPlayer() {
	return this.player;
    }
}