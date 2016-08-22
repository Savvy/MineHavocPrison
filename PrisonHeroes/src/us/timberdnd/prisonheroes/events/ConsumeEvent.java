package us.timberdnd.prisonheroes.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import us.timberdnd.prisonheroes.drinks.Drink;
import us.timberdnd.prisonheroes.drinks.DrinkManager;
import us.timberdnd.prisonheroes.manager.PlayerEntity;
import us.timberdnd.prisonheroes.manager.PlayerManagement;
import us.timberdnd.prisonheroes.manager.SimpleUtils;

public class ConsumeEvent implements Listener {
    
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
	if(event.getItem().getType() == Material.POTION) {
	    ItemStack item = event.getItem();
	    if(item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
		for(String s: event.getItem().getItemMeta().getLore()) {
		    for(String drinkNames: DrinkManager.drinks.keySet()) {
			if(s.equalsIgnoreCase(SimpleUtils.translate("&7Drink: &b" + drinkNames))) {
			    Drink drink = DrinkManager.drinks.get(drinkNames);
			    PlayerEntity pe = PlayerManagement.getPlayer(event.getPlayer());
			    Random random = new Random();
			    int randomInt = random.nextInt(100);
			    if(randomInt > 50) {
				pe.addCoins(drink.getPrice() * 2);
			    }else{
				for(PotionEffect pot: drink.getPotions()) {
				    event.getPlayer().addPotionEffect(pot);
				}
			    }
			}
		    }
		}
	    }
	}
    }
}