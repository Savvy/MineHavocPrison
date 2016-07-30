package us.timberdnd.prisonheroes.manager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public class InventoryManager {

    Inventory inventory;
    String inventoryName;
    char[][] data;
    HashMap<Character, ItemStack> dictionary;

    public InventoryManager(String inventoryName, int size, char[][] data, HashMap<Character, ItemStack> dictionary) {
	inventory = Bukkit.createInventory(null, size * 9, 
		translate(inventoryName));
	this.inventoryName = inventoryName;
	this.data = data;
	this.dictionary = dictionary;
    }

    public InventoryManager(String inventoryName, int size, HashMap<Character, ItemStack> dictionary) {
	inventory = Bukkit.createInventory(null, size * 9, 
		translate(inventoryName));
	this.inventoryName = inventoryName;
	this.dictionary = dictionary;
    }

    public InventoryManager(String inventoryName, int size, char[][] data) {
	inventory = Bukkit.createInventory(null, size * 9, 
		translate(inventoryName));
	this.inventoryName = inventoryName;
	this.data = data;
	dictionary = new HashMap<>();
    }

    public InventoryManager(String inventoryName, int size) {
	this.inventoryName = inventoryName;
	inventory = Bukkit.createInventory(null, size * 9, 
		translate(inventoryName));
	dictionary = new HashMap<>();
    }

    public InventoryManager mask() {
	for (int i = 0; i < data.length; i++) {
	    char[] row = data[i];
	    for (int c = 0; c < row.length; c++) {
		char ch = row[c];
		if (dictionary.containsKey(ch)) {
		    inventory.setItem((9 * i) + c, dictionary.get(ch));
		}
	    }
	}
	return this;
    }

    public Inventory getInventory() {
	return inventory;
    }

    public HashMap<Character, ItemStack> getDictionary() {
	return dictionary;
    }

    public String getName() {
	return getInventory().getName();
    }

    public InventoryManager layout(char[][] data) {
	this.data = data;
	return this;
    }

    public InventoryManager item(char character, ItemStack item) {
	dictionary.put(character, item);
	return this;
    }

    public InventoryManager openFor(Player player) {
	player.closeInventory();
	player.openInventory(getInventory());
	mask();
	return this;
    }

    public InventoryManager openFor(Player player, Permission permission) {
	if (player.hasPermission(permission)) {
	    player.closeInventory();
	    player.openInventory(getInventory());
	}
	return this;
    }

    public InventoryManager openFor(Player[] player) {
	for (Player players : player) {
	    openFor(players);
	}
	return this;
    }

    public InventoryManager openFor(Player[] player, Permission permission) {
	for (Player players : player) {
	    if (players.hasPermission(permission)) {
		openFor(players);
	    }
	}
	return this;
    }

    private String translate(String string) {
	return ChatColor.translateAlternateColorCodes('&', string);
    }
}