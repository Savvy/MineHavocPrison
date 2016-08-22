package us.timberdnd.prisonheroes.drinks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.timberdnd.prisonheroes.manager.ItemParser;
import us.timberdnd.prisonheroes.manager.SimpleUtils;

public class Drink {
    
    String name;
    private File file;
    private FileConfiguration fc;
    private List<PotionEffect> potions = new ArrayList<PotionEffect>();
    public Drink(File file) {
	this.file = file;
	this.fc = YamlConfiguration.loadConfiguration(file);
	name = file.getName().replace(".yml", "");
	load();
    }
    
    public ItemStack parse() {
	ItemStack item = new ItemParser(file).parse();
	item.getItemMeta().getLore().add(" ");
	item.getItemMeta().getLore().add(SimpleUtils.translate("&7Drink: &b" + getName()));
	return item;
    }
    
    public int getPrice() {
	return fc.getInt("price");
    }
    
    public List<PotionEffect> getPotions() {
	return potions;
    }
    
    private void load() {
	fc.getStringList("failed_potions").stream().forEach(failedPot -> {
	    String[] array = failedPot.split(":");
	    PotionEffect potType = new PotionEffect(PotionEffectType.getByName(array[0]), Integer.valueOf(array[1]), 1);
	    if(potType != null) {
		potions.add(potType);
	    }
	});
    }
    
    public String getName() {
	return name;
    }
    
    public File getFile() {
	return file;
    }
    
    public FileConfiguration getConfig() {
	return fc;
    }
}
