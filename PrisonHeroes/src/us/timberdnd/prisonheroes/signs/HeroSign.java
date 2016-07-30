package us.timberdnd.prisonheroes.signs;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

import us.timberdnd.prisonheroes.heroes.Hero;
import us.timberdnd.prisonheroes.manager.PlayerManagement;

public class HeroSign extends PrisonSigns{

    @Override
    public void createSign(SignChangeEvent event) {
	if(!(event.getPlayer().hasPermission("ph.signs.create.hero"))) {
	    event.setLine(0, ChatColor.DARK_RED + "[Hero]");
	    // TODO: No permission.
	    breakBlock(event.getBlock());
	    return;
	}
	event.setLine(0, ChatColor.DARK_BLUE + "[Hero]");
	return;
    }

    @Override
    public void destroy(BlockBreakEvent event) {
	if(!(event.getPlayer().hasPermission("ph.signs.destory.hero"))) {
	    event.setCancelled(true);
	    // TODO: No Permission
	}
    }

    @Override
    public String getSignName() {
	return "Hero";
    }

    @Override
    public void interact(Player player, Sign sign) {
	if(!(player.hasPermission("ph.signs.use.hero"))) {
	    // TODO: No Permission:
	    return;
	}
	player.closeInventory();
	player.openInventory(Hero.getInv(PlayerManagement.getPlayer(player)));
	return;
    }

}
