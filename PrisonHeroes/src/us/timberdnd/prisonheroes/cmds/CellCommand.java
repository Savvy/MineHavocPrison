package us.timberdnd.prisonheroes.cmds;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.protection.managers.RegionManager;

import java.util.LinkedList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.timberdnd.prisonheroes.PrisonHeroes;
import us.timberdnd.prisonheroes.manager.PlayerEntity;
import us.timberdnd.prisonheroes.manager.PlayerManagement;
import us.timberdnd.prisonheroes.manager.SimpleUtils;

public class CellCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if(cmd.getName().equalsIgnoreCase("buycell")) {
	    if(sender instanceof Player) {
		Player p = (Player) sender;
		LinkedList<String> list = SimpleUtils.getRegions(p);
		RegionContainer container = PrisonHeroes.instance.getWorldGuard().getRegionContainer();
		RegionManager regionManager = container.get(p.getWorld());
		if(regionManager != null) {
		    for(String s: list) {
			if(s.startsWith("cells_")) {
			    String cellName = s;
			    if(regionManager.getRegion(cellName).getOwners().size() < 1) {
				if(regionManager.getRegion(cellName).getOwners().contains(p.getName())) {
				    p.sendMessage(SimpleUtils.translate(
					    PrisonHeroes.plugin.getConfig().getString("failedRegionPurchase")
					    .replace("{0}", "Already purchased.")));
				    break;
				}
				PlayerEntity pe = PlayerManagement.getPlayer(p.getUniqueId());
				if(pe.getCoins() >= PrisonHeroes.plugin.getConfig().getInt("cells_price")) {
				    pe.removeCoins(PrisonHeroes.plugin.getConfig().getInt("cells_price"));
				    regionManager.getRegion(cellName).getOwners().addPlayer(p.getName());
				    p.sendMessage(SimpleUtils.translate(
					    PrisonHeroes.plugin.getConfig().getString("regionPurchased")));
				}else{
				    p.sendMessage(SimpleUtils.translate(
					    PrisonHeroes.plugin.getConfig().getString("failedRegionPurchase")
					    .replace("{0}", "Insufficient Funds")));
				}
			    }else{
				p.sendMessage(SimpleUtils.translate(
					PrisonHeroes.plugin.getConfig().getString("failedRegionPurchase"))
					.replace("{0}", "Owned by other"));
			    }
			}else{
			    p.sendMessage(SimpleUtils.translate(
				    PrisonHeroes.plugin.getConfig().getString("failedRegionPurchase")
				    .replace("{0}", "Invalid Cell")));
			}
		    }
		}
	    }
	}
	return true;
    }
}