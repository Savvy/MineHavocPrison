package us.timberdnd.prisonheroes.casino;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import us.timberdnd.api.ItemManager;
import us.timberdnd.prisonheroes.PrisonHeroes;
import us.timberdnd.prisonheroes.manager.InventoryManager;
import us.timberdnd.prisonheroes.manager.PlayerManagement;
import us.timberdnd.prisonheroes.manager.SimpleUtils;

public class CasinoInventory implements Listener {

    public Inventory getCasino(String buyIn) {
	InventoryManager manager = new InventoryManager(SimpleUtils.translate("Casino: $" + buyIn), 5);
	manager.layout(new char[][] {    
	    "mmmmmmmmm".toCharArray(),
	    "maaamaaam".toCharArray(),
	    "maaaBaaam".toCharArray(),
	    "maaamaaam".toCharArray(),
	    "mmmmmmmmm".toCharArray()
	});
	manager.item('m', new ItemManager(Material.EMERALD_BLOCK).name(" ").build());
	manager.item('B', new ItemManager(Material.DIAMOND).name("&aBuy In: $" + buyIn).build());
	return manager.mask().getInventory();
    }

    public static Map<String, Integer> casinoInt = new HashMap<String, Integer>();
    int vacate = 0;
    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
	if(event.getInventory().getTitle().startsWith("Casino: $")) {
	   vacate = PrisonHeroes.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(PrisonHeroes.plugin, new Runnable() {
		public void run() {
		    if(!(casinoInt.containsKey(event.getPlayer().getName()))) {
			casinoInt.put(event.getPlayer().getName(), 0);
		    }
		    if(casinoInt.containsKey(event.getPlayer().getName())) {
			if(casinoInt.get(event.getPlayer().getName()) < 30) {
			    int i = casinoInt.get(event.getPlayer().getName());
			    for(ItemStack item: event.getInventory().getContents()) {
				if(item != null && item.getType() == Material.EMERALD_BLOCK) {
				    item.setType(Material.DIAMOND_BLOCK);
				}else if(item != null && item.getType() == Material.DIAMOND_BLOCK) {
				    item.setType(Material.IRON_BLOCK);
				}else if(item != null && item.getType() == Material.IRON_BLOCK) {
				    item.setType(Material.REDSTONE_BLOCK);
				}else if(item != null && item.getType() == Material.REDSTONE_BLOCK) {
				    item.setType(Material.EMERALD_BLOCK);
				}
			    }
			    i++;
			    casinoInt.remove(event.getPlayer().getName());
			    casinoInt.put(event.getPlayer().getName(), i);
			}else if(casinoInt.get(event.getPlayer().getName()) == 30) {
			    int win;
			    int gamble = Integer.valueOf(event.getInventory().getTitle().replace("Casino: $", ""));
			    int payment = gamble + Integer.valueOf((int) (gamble *(80.0f/100.0f)));
			    Random r = new Random();
			    win = r.nextInt(100);
			    if(win <= 49) {
				PlayerManagement.getPlayer(event.getPlayer().getUniqueId()).addCoins(payment);
				event.getPlayer().sendMessage("You won");
			    }else{
				event.getPlayer().sendMessage("You lost");
			    }
			    casinoInt.remove(event.getPlayer().getName());
			    PrisonHeroes.plugin.getServer().getScheduler().cancelTask(vacate);
			}
		    }
		}
	    }, 0, 3L);
	}
    }
}