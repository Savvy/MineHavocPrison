package us.timberdnd.prisonheroes.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManager {
    
    private ItemStack stack;
    
    public ItemManager(Material mat) {
	stack = new ItemStack(mat);
    }
    
    public ItemManager addLore(String string) {
	stack.getItemMeta().getLore().add(ChatColor.translateAlternateColorCodes('&', string));
	return this;
    }
    public ItemManager rename(String string) {
	stack.getItemMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', string));
	return this;
    }
    
    public ItemStack build() {
	return stack;
    }
}
