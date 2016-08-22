package us.timberdnd.prisonheroes.signs;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;

import us.timberdnd.prisonheroes.manager.SimpleUtils;

public class CoinsEnchant extends PrisonSigns {

    @Override
    public void createSign(SignChangeEvent event) {
	if(!(event.getPlayer().hasPermission("ph.signs.create.enchants"))) {
	    event.setLine(0, ChatColor.DARK_RED + "[cEnchant]");
	    // TODO: No permission.
	    breakBlock(event.getBlock());
	    return;
	}
	if(Enchantment.getByName(event.getLine(1).toUpperCase()) != null) {
	    if(SimpleUtils.isInt(event.getLine(2)) && SimpleUtils.isInt(event.getLine(3))) {
		event.setLine(0, ChatColor.DARK_BLUE + "[cEnchant]");
		event.setLine(1, ChatColor.GREEN + event.getLine(1).toUpperCase());
		return;
	    }
	}
	event.setLine(0, ChatColor.DARK_RED + "[cEnchant]");
	breakBlock(event.getBlock());
    }

    @Override
    public void destroy(BlockBreakEvent event) {
	if(!(event.getPlayer().hasPermission("ph.signs.destory.enchants"))) {
	    event.setCancelled(true);
	    // TODO: No Permission
	}
    }

    @Override
    public String getSignName() {
	return "cEnchant";
    }

    @Override
    public void interact(Player player, Sign sign) {
	if(!(player.hasPermission("ph.signs.use.enchants"))) {
	    // TODO: No Permission:
	    return;
	}
	ItemStack item = player.getItemInHand();
	Enchantment enchantment = Enchantment.getByName(ChatColor.stripColor(sign.getLine(1)));
	if(enchantment != null && enchantment.canEnchantItem(item)) {
	    // TODO: Enchantment added.
	    item.addEnchantment(null, Integer.valueOf(sign.getLine(2)));
	}else{
	    // TODO: Can not enchant item.
	}
	return;
    }
}