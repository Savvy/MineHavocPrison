package us.timberdnd.prisonheroes.cmds;

import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.timberdnd.prisonheroes.PrisonHeroes;
import us.timberdnd.prisonheroes.manager.MessageUtils;
import us.timberdnd.prisonheroes.manager.PlayerEntity;
import us.timberdnd.prisonheroes.manager.PlayerManagement;

public class CoinsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if(!(sender instanceof Player)) {
	    sender.sendMessage("Only players may use this command.");
	}
	PlayerEntity pe = PlayerManagement.getPlayer(((Player) sender).getUniqueId());
	MessageUtils messageUtils = new MessageUtils(PrisonHeroes.plugin.getConfig());
	if(cmd.getName().equalsIgnoreCase("coins")) {
	    if(args.length == 3) {
		if(args[0].equalsIgnoreCase("give")) {
		    Player player = Bukkit.getPlayer(args[1]);
		    if(player != null && player.isOnline()) {
			PlayerEntity target = PlayerManagement.getPlayer(player.getUniqueId());
			if(PrisonHeroes.isInt(args[2])) {
			    int amount = Integer.parseInt(args[2]);
			    if(target.addCoins(amount)) {
				sender.sendMessage(messageUtils.getMessage("modifiedBalance", "{0}", "+")
					.replace("{1}", String.valueOf(amount)
						.replace("{2}", String.valueOf(target.getCoins()))));
				player.sendMessage(messageUtils.getMessage("modifiedBalance", "{0}", "+")
					.replace("{1}", String.valueOf(amount)
						.replace("{2}", String.valueOf(target.getCoins()))));
			    }else{
				sender.sendMessage(messageUtils.getMessage("cannotModifyBalance", "", ""));
			    }
			}else{
			    sender.sendMessage(messageUtils.getMessage("amountMustBeNumber", "", ""));
			}
		    }else{
			sender.sendMessage(messageUtils.getMessage("playerNotOnline", "{0}", args[1]));
		    }
		}else if(args[0].equalsIgnoreCase("take")) {
		    Player player = Bukkit.getPlayer(args[1]);
		    if(player != null && player.isOnline()) {
			PlayerEntity target = PlayerManagement.getPlayer(player.getUniqueId());
			if(PrisonHeroes.isInt(args[2])) {
			    int amount = Integer.parseInt(args[2]);
			    if(target.removeCoins(amount)) {
				sender.sendMessage(messageUtils.getMessage("modifiedBalance", "{0}", "-")
					.replace("{1}", String.valueOf(amount)
						.replace("{2}", String.valueOf(target.getCoins()))));
				player.sendMessage(messageUtils.getMessage("modifiedBalance", "{0}", "-")
					.replace("{1}", String.valueOf(amount)
						.replace("{2}", String.valueOf(target.getCoins()))));
			    }else{
				sender.sendMessage(messageUtils.getMessage("cannotModifyBalance", "", ""));
			    }
			}else{
			    sender.sendMessage(messageUtils.getMessage("amountMustBeNumber", "", ""));
			}
		    }else{
			sender.sendMessage(messageUtils.getMessage("playerNotOnline", "{0}", args[1]));
		    }
		}else if(args[0].equalsIgnoreCase("send")) {
		    Player player = Bukkit.getPlayer(args[1]);
		    if(player != null && player.isOnline()) {
			PlayerEntity target = PlayerManagement.getPlayer(player.getUniqueId());
			if(PrisonHeroes.isInt(args[2])) {
			    int amount = Integer.parseInt(args[2]);
			    if(target.addCoins(amount) && pe.removeCoins(amount)) {
				sender.sendMessage(messageUtils.getMessage("modifiedBalance", "{0}", "-")
					.replace("{1}", String.valueOf(amount)
						.replace("{2}", String.valueOf(pe.getCoins()))));
				player.sendMessage(messageUtils.getMessage("modifiedBalance", "{0}", "+")
					.replace("{1}", String.valueOf(amount)
						.replace("{2}", String.valueOf(target.getCoins()))));
			    }else{
				sender.sendMessage(messageUtils.getMessage("cannotModifyBalance", "", ""));
			    }
			}else{
			    sender.sendMessage(messageUtils.getMessage("amountMustBeNumber", "", ""));
			}
		    }else{
			sender.sendMessage(messageUtils.getMessage("playerNotOnline", "{0}", args[1]));
		    }
		}else{
		    sender.sendMessage(messageUtils.getMessage("incorrectArguments", "{0}", "/coins send [player] [amount]"));
		}
	    }else if(args.length == 1) {
		if(args[0].equalsIgnoreCase("balance")) {
		    sender.sendMessage(messageUtils.getMessage("playerBalance", "{0}", String.valueOf(pe.getCoins())));
		}else{
		    sender.sendMessage(messageUtils.getMessage("incorrectArguments", "{0}", "/coins balance"));
		}
	    }else{
		List<String> messages = PrisonHeroes.plugin.getConfig().getStringList("help");
		messages.stream().forEach(message -> {
		    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		});
	    }
	}
	return false;
    }
}
