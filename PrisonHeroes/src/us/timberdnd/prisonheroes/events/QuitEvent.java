package us.timberdnd.prisonheroes.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import us.timberdnd.prisonheroes.manager.PlayerManagement;

public class QuitEvent implements Listener {
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
	PlayerManagement.removePlayer(event.getPlayer());
    }
}
