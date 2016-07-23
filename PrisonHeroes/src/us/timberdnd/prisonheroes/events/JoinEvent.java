package us.timberdnd.prisonheroes.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import us.timberdnd.prisonheroes.manager.PlayerManagement;

public class JoinEvent implements Listener {
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
	PlayerManagement.putPlayer(event.getPlayer());
    }
}
