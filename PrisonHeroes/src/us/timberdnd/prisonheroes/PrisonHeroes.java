package us.timberdnd.prisonheroes;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import us.timberdnd.prisonheroes.casino.CasinoInventory;
import us.timberdnd.prisonheroes.cmds.CellCommand;
import us.timberdnd.prisonheroes.cmds.CoinsCommand;
import us.timberdnd.prisonheroes.cmds.HeroesCommand;
import us.timberdnd.prisonheroes.drinks.DrinkManager;
import us.timberdnd.prisonheroes.events.InteractEvent;
import us.timberdnd.prisonheroes.events.JoinEvent;
import us.timberdnd.prisonheroes.events.QuitEvent;
import us.timberdnd.prisonheroes.events.SignEvent;
import us.timberdnd.prisonheroes.manager.SQLManager;
import us.timberdnd.prisonheroes.manager.SimpleUtils;
import us.timberdnd.prisonheroes.signs.Signs;

public class PrisonHeroes extends JavaPlugin {

    public static Plugin plugin;
    public static Signs signs;
    public static PrisonHeroes instance;

    SQLManager sql;
    @Override
    public void onEnable() {
	plugin = this;
	instance = this;
	saveDefaultConfig();
	sql = new SQLManager();
	signs = new Signs();
	sql.createTable();
	getServer().getPluginManager().registerEvents(new InteractEvent(), this);
	getServer().getPluginManager().registerEvents(new JoinEvent(), this);
	getServer().getPluginManager().registerEvents(new QuitEvent(), this);
	getServer().getPluginManager().registerEvents(new CasinoInventory(), this);
	getServer().getPluginManager().registerEvents(new SignEvent(), this);
	getCommand("coins").setExecutor(new CoinsCommand());
	getCommand("hero").setExecutor(new HeroesCommand());
	getCommand("buycell").setExecutor(new CellCommand());
	DrinkManager.register();
	signs.registerSign();
	for(Player p: Bukkit.getOnlinePlayers()) {
	    try {
		SimpleUtils.registerNewPlayer(p);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    public WorldGuardPlugin getWorldGuard() {
	Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	    return null;
	}
	return (WorldGuardPlugin) plugin;
    }

    public static Plugin getInstance() {
	return plugin;
    }

    public static Signs getSigns() {
	return signs;
    }
    public void onDisable() {
    }
}
