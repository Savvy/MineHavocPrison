package us.timberdnd.prisonheroes.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.timberdnd.prisonheroes.manager.PlayerEntity;
import us.timberdnd.prisonheroes.manager.PlayerManagement;

public class CoinsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if(!(sender instanceof Player)) {
	    sender.sendMessage("Only players may use this command.");
	}
	PlayerEntity pe = PlayerManagement.getPlayer(((Player) sender).getUniqueId());
	MessageUtils messageUtils = new MessageUtils();
	if(cmd.getName().equalsIgnoreCase("coins")) {
	    if(args.length == 3) {
		if(args[0].equalsIgnoreCase("give")) {
		    if(pe.addCoins()) {
			
		    }else{
			// TODO: Was not able to add coins.
		    }
		}else if(args[0].equalsIgnoreCase("take")) {
		    if(pe.removeCoins()) {
			
		    }else{
			// TODO: Was not able to remove coins.
		    }
		}else if(args[0].equalsIgnoreCase("send")) {

		}else{
		    // TODO: Incorrect arguments.
		}
	    }else if(args.length == 1) {
		if(args[0].equalsIgnoreCase("balance")) {
		    sender.sendMessage(messageUtils.getMessage("playerBalance", "{0}", String.valueOf(pe.getCoins())));
		}else{
		    // TODO: Incorrect arguments.
		}
	    }else if(args.length == 0) {
		// TODO: Send help
	    }else{
		// TODO: Incorrect arguments.
	    }
	}
	return false;
    }
}
