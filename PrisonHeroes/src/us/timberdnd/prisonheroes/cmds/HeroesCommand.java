package us.timberdnd.prisonheroes.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.timberdnd.prisonheroes.heroes.Hero;
import us.timberdnd.prisonheroes.manager.PlayerManagement;

public class HeroesCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if(cmd.getName().equalsIgnoreCase("hero")) {
	    if(sender instanceof Player) {
		Player player = (Player) sender;
		player.closeInventory();
		player.openInventory(Hero.getInv(PlayerManagement.getPlayer(player)));
	    }
	}
	return false;
    }
}
