package us.timberdnd.prisonheroes;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import us.timberdnd.prisonheroes.manager.SQLManager;
import us.timberdnd.prisonheroes.signs.Signs;

public class PrisonHeroes extends JavaPlugin {
    
    public static Plugin plugin;
    
    public static Signs signs;
    
    private SQLManager sql;
    public void onEnable() {
	plugin = this;
	saveDefaultConfig();
	sql = new SQLManager();
	sql.createTable();
	signs = new Signs();
    }
    
    public static Signs getSigns() {
	return signs;
    }
    public void onDisable() {
    }
}
