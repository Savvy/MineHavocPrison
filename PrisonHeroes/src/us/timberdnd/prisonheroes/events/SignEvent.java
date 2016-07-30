package us.timberdnd.prisonheroes.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import us.timberdnd.prisonheroes.PrisonHeroes;
import us.timberdnd.prisonheroes.signs.PrisonSigns;

public class SignEvent implements Listener {

    @EventHandler
    public void createSign(SignChangeEvent event) {
//	if(event.getPlayer().hasPermission("mca.colored")) {
//	    for(int i = 0; i<event.getLines().length; i++) {
//		event.setLine(i, translate(event.getLine(i)));
//	    }
//	}
	for(PrisonSigns signs: PrisonHeroes.getSigns().signs) {
	    if(ChatColor.stripColor(event.getLine(0)).equalsIgnoreCase
		    ("[" + signs.getSignName() + "]")) {
		signs.createSign(event);
	    }
	}
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
	if(event.getBlock() == null || event.getBlock().getType() == null 
		|| event.getBlock().getType().equals(Material.AIR)) {
	    return;
	}
	if(!(event.getBlock().getState() instanceof Sign)) {
	    return;
	}
	Sign sign = (Sign) event.getBlock().getState();
	for(PrisonSigns signs: PrisonHeroes.getSigns().signs) {
	    if(ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase
		    ("[" + signs.getSignName() + "]")) {
		signs.destroy(event);
	    }
	}
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
	if (!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
	    return;
	}
	if (event.getClickedBlock() == null 
		|| event.getClickedBlock().getType() == null 
		|| event.getClickedBlock().getType().equals(Material.AIR)) {
	    return;
	}
	if (!(event.getClickedBlock().getState() instanceof Sign)) {
	    return;
	}
	Sign sign = (Sign) event.getClickedBlock().getState();
	for(PrisonSigns signs: PrisonHeroes.getSigns().signs) {
	    if(sign.getLine(0).equalsIgnoreCase(ChatColor.DARK_BLUE + "[" + signs.getSignName() + "]")) {
		signs.interact(event.getPlayer(), sign);
	    }
	}
    }
    
    public String translate(String string) {
	return ChatColor.translateAlternateColorCodes('&', string);
    }
}