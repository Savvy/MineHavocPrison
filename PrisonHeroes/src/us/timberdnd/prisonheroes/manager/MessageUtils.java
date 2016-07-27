package us.timberdnd.prisonheroes.manager;


import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class MessageUtils {
    
    public FileConfiguration fc;
    
    public MessageUtils(FileConfiguration fc) {
	this.fc = fc;
    }
    
    public String getMessage(String message, String replace, String replacement) {
	return ChatColor.translateAlternateColorCodes('&', 
		fc.getString(message).replace(replace, replacement));
    }
}
