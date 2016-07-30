package us.timberdnd.prisonheroes.manager;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemParser {
    
    private File file;
    private FileConfiguration fc;
    private MessageUtils messageUtils;
    public ItemParser(File file) {
	this.file = file;
	fc = YamlConfiguration.loadConfiguration(this.file);
	messageUtils = new MessageUtils(fc);
    }
    
    public ItemStack parse(PlayerEntity player) {
	ItemStack item = new ItemStack(Material.valueOf(fc.getString("type").toUpperCase()));
	item.setAmount(fc.getInt("amount"));
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(messageUtils.getMessage("name", "", ""));
	ArrayList<String> lore = new ArrayList<String>();
	fc.getStringList("lore").stream().forEach(string -> {
	    lore.add(translate(string));
	});
	meta.setLore(lore);
	item.setItemMeta(meta);
	return item;
    }
    
    private String translate(String string) {
	return ChatColor.translateAlternateColorCodes('&', string);
    }
}