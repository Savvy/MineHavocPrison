package us.timberdnd.prisonheroes.manager;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

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
     * @return PlayerEntity
     */
    public static PlayerEntity getPlayer(UUID uuid) {
	return players.get(uuid);
    }
    
    /**
     * <b>Description:</b>
     * 		Retrieve a player from "players" collection database.
     * @param
     * @return PlayerEntity
     */
    public static PlayerEntity getPlayer(Player player) {
	return players.get(getPlayer(player.getUniqueId()));
    }
    
    /**
     * <b>Description:</b>
     * 		Add a player to "players" collection database.
     * @param
     */
    public static void putPlayer(Player player) {
	if(!(players.containsKey(player.getPlayer().getUniqueId()))) {
	    players.put(player.getUniqueId(), new PlayerEntity(player));
	}
    }
    
    /**
     * <b>Description:</b>
     * 		Remove a player from "players" collection database.
     * @param
     */
    public static void removePlayer(Player player) {
	if(players.containsKey(player.getUniqueId())) {
	    players.get(player.getUniqueId()).unload();
	    players.remove(player.getUniqueId());
	}
    }
}
