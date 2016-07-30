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
	manager.item('1', new ItemParser(new File(plugin.getDataFolder() + "/superman.yml")).parse(player));
	manager.item('2', new ItemParser(new File(plugin.getDataFolder() + "/batman.yml")).parse(player));
	manager.item('3', new ItemParser(new File(plugin.getDataFolder() + "/wolverine.yml")).parse(player));
	manager.item('4', new ItemParser(new File(plugin.getDataFolder() + "/hawkeye.yml")).parse(player));
	return manager.mask().getInventory();
    }
}
