package us.timberdnd.prisonheroes.drinks;

import org.bukkit.entity.Player;

import us.timberdnd.prisonheroes.manager.PlayerEntity;
import us.timberdnd.prisonheroes.manager.PlayerManagement;

public class Drinks {
    
    public static void purchaseDrink(Player player, String drinkName) {
	Drink drink = DrinkManager.drinks.get(drinkName);
	PlayerEntity pe = PlayerManagement.getPlayer(player);
	if(pe.getCoins() >= drink.getPrice()) {
	    pe.removeCoins(drink.getPrice());
	    player.getInventory().addItem(drink.parse());
	    // TODO: Purchased Drink
	}else{
	    // TODO: Not enough coins.
	}
    }
}
