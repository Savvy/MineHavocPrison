package us.timberdnd.prisonheroes.manager;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import us.timberdnd.prisonheroes.PrisonHeroes;
import us.timberdnd.prisonheroes.manager.SQLManager.RESULT;

public class SimpleUtils {

    public static boolean isInt(String s) {
	try {
	    Integer.parseInt(s);
	    return true;
	} catch (NumberFormatException ex) {
	    return false;
	}
    }
    
    public static LinkedList<String> getRegions(Player p) {
	RegionContainer container = PrisonHeroes.instance.getWorldGuard().getRegionContainer();
	RegionManager regionManager = container.get(p.getWorld());
	if(regionManager != null) {
	    ApplicableRegionSet set = regionManager.getApplicableRegions(p.getLocation());
	    LinkedList<String> parentNames = new LinkedList<String>();
	    LinkedList<String> regions = new LinkedList<String>();
	    for ( ProtectedRegion region : set ) {
		String id = region.getId();
		regions.add( id );
		ProtectedRegion parent = region.getParent();
		while ( parent != null ) {
		    parentNames.add( parent.getId());
		    parent = parent.getParent();
		}
	    }
	    for ( String name : parentNames ) {
		regions.remove( name );
	    }
	    return regions;
	}
	return null;
    }
    
    public static void registerNewPlayer(Player player) throws SQLException {
	SQLManager manager = new SQLManager();
	if(manager.checkExists(player.getUniqueId()) != RESULT.TRUE) {
	    manager.addPlayer(player.getUniqueId(), player.getName());
	} else {
	    if(manager.checkExists(player.getUniqueId()) == RESULT.SUCCESS) {
		if(manager.getName(player.getUniqueId()) != null) {
		    if(manager.getName(player.getUniqueId()) != player.getName()) {
			manager.updatePlayerName(player.getUniqueId(), player.getName());
		    }
		}
	    }
	}
	PlayerManagement.putPlayer(player);
    }

    public static boolean isPickaxe(ItemStack item) {
	if(item.getType() == Material.WOOD_PICKAXE || item.getType() == Material.STONE_PICKAXE
		|| item.getType() == Material.IRON_PICKAXE || item.getType() == Material.GOLD_PICKAXE
		|| item.getType() == Material.DIAMOND_PICKAXE) {
	    return true;
	}
	return false;
    }

    public static Object formulate() throws ScriptException {
	ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
	String formula = PrisonHeroes.plugin.getConfig().getString("ability.superman.cooldown_expression");
	Map<String, Object> vars = new HashMap<String, Object>();
	vars.put("{current_level}", 2);
	vars.put("{next_level", 1);
	return engine.eval(formula, new SimpleBindings(vars));
    }

    public static String translate(String string) {
	return ChatColor.translateAlternateColorCodes('&', string);
    }
}
