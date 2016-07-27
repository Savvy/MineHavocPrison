package us.timberdnd.prisonheroes;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import us.timberdnd.prisonheroes.manager.SQLManager;

public class PrisonHeroes extends JavaPlugin {
    
    public static Plugin plugin;
    private SQLManager sql;
    public void onEnable() {
	plugin = this;
	saveDefaultConfig();
	sql = new SQLManager();
	sql.createTable();
    }
    public void onDisable() {
    }
    
    public static boolean isInt(String s) {
	try {
	    Integer.parseInt(s);
	    return true;
	} catch (NumberFormatException ex) {
	    return false;
	}
    }
}
