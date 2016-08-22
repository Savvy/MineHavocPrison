package us.timberdnd.prisonheroes.signs;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

import us.timberdnd.prisonheroes.casino.CasinoInventory;
import us.timberdnd.prisonheroes.manager.SimpleUtils;

public class Casino extends PrisonSigns {

    @Override
    public void createSign(SignChangeEvent event) {
	if(!(event.getPlayer().hasPermission("ph.signs.create.casino"))) {
	    event.setLine(0, ChatColor.DARK_RED + "[Casino]");
	    // TODO: No permission.
	    breakBlock(event.getBlock());
	    return;
	}
	if(SimpleUtils.isInt(event.getLine(1))) {
	    event.setLine(0, ChatColor.DARK_BLUE + "[Casino]");
	    event.setLine(1, ChatColor.GREEN + "Amount: " + event.getLine(1).toUpperCase());
	    return;
	}
	event.setLine(0, ChatColor.DARK_RED + "[Casino]");
	breakBlock(event.getBlock());
	return;
    }

    @Override
    public void destroy(BlockBreakEvent event) {
	if(!(event.getPlayer().hasPermission("ph.signs.destory.casino"))) {
	    event.setCancelled(true);
	    // TODO: No Permission
	}
    }

    @Override
    public String getSignName() {
	return "Casino";
    }

    @Override
    public void interact(Player player, Sign sign) {
	if(!(player.hasPermission("ph.signs.use.casino"))) {
	    // TODO: No Permission:
	    return;
	}
	String buyIn = ChatColor.stripColor(sign.getLine(1).replace("Amount: ", ""));
	player.closeInventory();
	player.openInventory(new CasinoInventory().getCasino(buyIn));
	return;
    }
}