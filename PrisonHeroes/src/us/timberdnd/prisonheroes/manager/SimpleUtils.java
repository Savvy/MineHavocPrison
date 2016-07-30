package us.timberdnd.prisonheroes.manager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SimpleUtils {

    public static boolean isInt(String s) {
	try {
	    Integer.parseInt(s);
	    return true;
	} catch (NumberFormatException ex) {
	    return false;
	}
    }
    
    public static boolean isPickaxe(ItemStack item) {
	if(item.getType() == Material.WOOD_PICKAXE || item.getType() == Material.STONE_PICKAXE
		|| item.getType() == Material.IRON_PICKAXE || item.getType() == Material.GOLD_PICKAXE
		|| item.getType() == Material.DIAMOND_PICKAXE) {
	    return true;
	}
	return false;
    }
}
