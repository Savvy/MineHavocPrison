package us.timberdnd.prisonheroes.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CoinsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if(cmd.getName().equalsIgnoreCase("coins")) {
	    if(args.length == 3) {
		if(args[0].equalsIgnoreCase("give")) {
		    
		}else if(args[0].equalsIgnoreCase("take")) {

		}else if(args[0].equalsIgnoreCase("send")) {

		}else{
		    // TODO: Incorrect arguments.
		}
	    }else if(args.length == 1) {
		if(args[0].equalsIgnoreCase("balance")) {

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
