package us.timberdnd.prisonheroes.manager;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerManagement {

    public static HashMap<UUID, Player> players = new HashMap<UUID, Player>();

    /**
     * <b>Description:</b>
     * 		Grab "players" collection database.
     *  @return HashMap<UUID, Player>
     */
    public static HashMap<UUID, Player> getPlayers() {
	return players;
    }

    /**
     * <b>Description:</b>
     * 		Retrieve a player from "players" collection database.
     * @param
     * @return Player
     */
    public static Player getPlayer(UUID uuid) {
	return players.get(uuid);
    }
    
    /**
     * <b>Description:</b>
     * 		Add a player to "players" collection database.
     * @param
     */
    public static void putPlayer(Player player) {
	if(!(players.containsKey(player.getUniqueId()))) {
	    players.put(player.getUniqueId(), player);
	}
    }
    
    /**
     * <b>Description:</b>
     * 		Remove a player from "players" collection database.
     * @param
     */
    public static void removePlayer(Player player) {
	if(players.containsKey(player.getUniqueId())) {
	    players.remove(player.getUniqueId());
	}
    }
}
