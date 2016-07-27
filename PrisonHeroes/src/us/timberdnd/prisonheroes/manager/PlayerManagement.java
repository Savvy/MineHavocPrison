package us.timberdnd.prisonheroes.manager;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManagement {

    public static HashMap<UUID, PlayerEntity> players = new HashMap<UUID, PlayerEntity>();

    /**
     * <b>Description:</b>
     * 		Grab "players" collection database.
     *  @return HashMap<UUID, Player>
     */
    public static HashMap<UUID, PlayerEntity> getPlayers() {
	return players;
    }

    /**
     * <b>Description:</b>
     * 		Retrieve a player from "players" collection database.
     * @param
     * @return Player
     */
    public static PlayerEntity getPlayer(UUID uuid) {
	return players.get(uuid);
    }
    
    /**
     * <b>Description:</b>
     * 		Add a player to "players" collection database.
     * @param
     */
    public static void putPlayer(PlayerEntity player) {
	if(!(players.containsKey(player.getPlayer().getUniqueId()))) {
	    players.put(player.getPlayer().getUniqueId(), player);
	}
    }
    
    /**
     * <b>Description:</b>
     * 		Remove a player from "players" collection database.
     * @param
     */
    public static void removePlayer(PlayerEntity player) {
	if(players.containsKey(player.getPlayer().getUniqueId())) {
	    players.remove(player.getPlayer().getUniqueId());
	}
    }
}
