package us.timberdnd.prisonheroes.heroes;

import java.io.File;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import us.timberdnd.prisonheroes.PrisonHeroes;
import us.timberdnd.prisonheroes.manager.InventoryManager;
import us.timberdnd.prisonheroes.manager.ItemParser;
import us.timberdnd.prisonheroes.manager.MessageUtils;
import us.timberdnd.prisonheroes.manager.PlayerEntity;

public class Hero {
    public static Inventory getInv(PlayerEntity player) {
	MessageUtils utils = new MessageUtils(PrisonHeroes.plugin.getConfig());
	Plugin plugin = PrisonHeroes.plugin;
	InventoryManager manager = new InventoryManager(utils.getMessage("heroInv.name", "", ""), 2);
	manager.layout(new char[][] {
	    "aaa12aaaa".toCharArray(),
	    "aaa34aaaa".toCharArray()
	});
	manager.item('1', new ItemParser(new File(plugin.getDataFolder() + "/abilities/superman.yml")).parse());
	manager.item('2', new ItemParser(new File(plugin.getDataFolder() + "/abilities/batman.yml")).parse());
	manager.item('3', new ItemParser(new File(plugin.getDataFolder() + "/abilities/wolverine.yml")).parse());
	manager.item('4', new ItemParser(new File(plugin.getDataFolder() + "/abilities/hawkeye.yml")).parse());
	return manager.mask().getInventory();
    }
}