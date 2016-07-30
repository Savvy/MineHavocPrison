package us.timberdnd.prisonheroes.events;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import us.timberdnd.prisonheroes.PrisonHeroes;
import us.timberdnd.prisonheroes.manager.MessageUtils;
import us.timberdnd.prisonheroes.manager.PlayerEntity;
import us.timberdnd.prisonheroes.manager.PlayerManagement;
import us.timberdnd.prisonheroes.manager.SimpleUtils;

public class InteractEvent implements Listener {
    
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
	MessageUtils messageUtils = new MessageUtils(PrisonHeroes.plugin.getConfig());
	if(event.getPlayer().getItemInHand() == null ||
		event.getPlayer().getItemInHand().getType() == Material.AIR) {
	    return;
	}
	if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK 
		|| event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK
			&& SimpleUtils.isPickaxe(event.getPlayer().getItemInHand())) {
	    event.getItem().setDurability(event.getItem().getDurability());
	}
	
	if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK 
		&& SimpleUtils.isPickaxe(event.getPlayer().getItemInHand())) {
	    PlayerEntity pe = PlayerManagement.getPlayer(event.getPlayer());
	    if(pe.activateAbility()) {
		event.setCancelled(true);
		pe.getPlayer().sendMessage(messageUtils.getMessage("abilityActivated", "{0}", "GroundSlam"));
	    }else{
		event.setCancelled(true);
		pe.getPlayer().sendMessage(messageUtils.getMessage("unableToActivateAbility", "", ""));
	    }
	}
    }
}
