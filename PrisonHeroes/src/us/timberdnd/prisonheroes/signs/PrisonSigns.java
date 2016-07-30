package us.timberdnd.prisonheroes.signs;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import us.timberdnd.prisonheroes.PrisonHeroes;

public abstract class PrisonSigns {

    public abstract void createSign(SignChangeEvent event);
    
    public abstract void destroy(BlockBreakEvent event);
    
    public abstract String getSignName();
    
    public abstract void interact(Player player, Sign sign);

    public void breakBlock(Block block) {
	new BukkitRunnable() {
	    public void run() {
		block.breakNaturally();
	    }
	}.runTaskLater(PrisonHeroes.plugin, 40L);
    }
}
