package us.timberdnd.prisonheroes.events;

import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import us.timberdnd.prisonheroes.manager.SQLManager;
import us.timberdnd.prisonheroes.manager.SimpleUtils;

public class JoinEvent implements Listener {
    
    SQLManager manager = new SQLManager();
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws SQLException {
	SimpleUtils.registerNewPlayer(event.getPlayer());
    }
}
