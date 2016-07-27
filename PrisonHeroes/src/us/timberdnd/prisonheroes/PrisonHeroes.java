package us.timberdnd.prisonheroes;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PrisonHeroes extends JavaPlugin {
    
    public static Plugin plugin;
    public void onEnable() {
	plugin = this;
    }
    public void onDisable() {
    }
}
