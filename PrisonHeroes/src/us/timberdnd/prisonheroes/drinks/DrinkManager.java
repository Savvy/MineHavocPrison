package us.timberdnd.prisonheroes.drinks;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import us.timberdnd.prisonheroes.PrisonHeroes;

public class DrinkManager {
    
    public static Map<String, Drink> drinks = new HashMap<String, Drink>();


    public static void register() {
	for(File file: getDrinkFiles()) {
	    Drink drink = new Drink(file);
	    drinks.put(drink.getName(), drink);
	}
    }
    
    public static File[] getDrinkFiles() {
	File dir = new File(PrisonHeroes.getInstance().getDataFolder() + "/drinks");
	return dir.listFiles();
    }
}
