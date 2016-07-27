package us.timberdnd.prisonheroes.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HeroesCommand implements CommandExecutor {
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if(cmd.getName().equalsIgnoreCase("hero")) {
	    // TODO: Open GUI for player(sender).
	}
	return false;
    }
}
