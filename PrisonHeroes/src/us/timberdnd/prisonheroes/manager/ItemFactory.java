package us.timberdnd.prisonheroes.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.timberdnd.prisonheroes.PrisonHeroes;


public class ItemFactory implements Listener {
	private boolean listener = false;
	// TODO: Switch to Map
	private static final HashMap<String, PotionEffect> effects = new HashMap<String, PotionEffect>();

	private ItemStack is;

	public ItemFactory(final Material mat) {
		is = new ItemStack(mat);
	}

	public ItemFactory(final ItemStack is) {
		this.is = is;
	}

	public ItemFactory addItemFlags(ItemFlag... itemf) {
		is.getItemMeta().addItemFlags(itemf);
		return this;
	}

	public ItemFactory amount(final int amount) {
		is.setAmount(amount);
		return this;
	}

	public String getName() {
		return is.getItemMeta().getDisplayName();
	}

	public ItemFactory name(final String name) {
		final ItemMeta meta = is.getItemMeta();
		meta.setDisplayName(translate(name));
		is.setItemMeta(meta);
		return this;
	}

	public ItemFactory lore(final String name) {
		final ItemMeta meta = is.getItemMeta();
		List<String> lore = meta.getLore();
		if (lore == null) {
			lore = new ArrayList<String>();
		}
		lore.add(translate(name));
		meta.setLore(lore);
		is.setItemMeta(meta);
		return this;
	}

	public ItemFactory durability(final int durability) {
		is.setDurability((short) durability);
		return this;
	}

	@SuppressWarnings("deprecation")
	public ItemFactory data(final int data) {
		is.setDurability((short) data);
		is.setData(new MaterialData(is.getType(), (byte) data));
		return this;
	}

	public ItemFactory removeAttribute() {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
		NBTTagCompound tag;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		} else {
			tag = nmsStack.getTag();
		}
		NBTTagList am = new NBTTagList();
		tag.set("AttributeModifiers", am);
		nmsStack.setTag(tag);
		is = CraftItemStack.asCraftMirror(nmsStack);
		return this;
	}

	public ItemFactory addGlow() {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null)
			tag = nmsStack.getTag();
		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		is = CraftItemStack.asCraftMirror(nmsStack);
		return this;
	}

	public ItemFactory addEnchantment(Enchantment enchantment, int arg, boolean arg2) {
		is.getItemMeta().addEnchant(enchantment, arg, arg2);
		return this;
	}

	public ItemFactory enchantment(final Enchantment enchantment, final int level) {
		is.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	public ItemFactory enchantment(final Enchantment enchantment) {
		is.addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	public ItemFactory type(final Material material) {
		is.setType(material);
		return this;
	}

	public ItemFactory clearLore() {
		final ItemMeta meta = is.getItemMeta();
		meta.setLore(new ArrayList<String>());
		is.setItemMeta(meta);
		return this;
	}

	public ItemFactory clearEnchantments() {
		for (final Enchantment e : is.getEnchantments().keySet()) {
			is.removeEnchantment(e);
		}
		return this;
	}

	public ItemFactory color(Color color) {
		if ((is.getType() == Material.LEATHER_BOOTS) || (is.getType() == Material.LEATHER_CHESTPLATE) || (is.getType() == Material.LEATHER_HELMET) || (is.getType() == Material.LEATHER_LEGGINGS)) {
			LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
			meta.setColor(color);
			is.setItemMeta(meta);
			return this;
		} else {
			throw new IllegalArgumentException("color() only applicable for leather armor!");
		}
	}

	public ItemFactory effect(PotionEffectType type, int duration, int amplifier, boolean ambient) {
		effect(new PotionEffect(type, duration, amplifier, ambient));
		return this;
	}

	public ItemFactory effect(PotionEffect effect) {
		if (!listener) {
			Bukkit.getPluginManager().registerEvents(this, PrisonHeroes.plugin);
			listener = true;
		}
		String name = is.getItemMeta().getDisplayName();
		while (effects.containsKey(name)) {
			name = name + "#";
		}
		effects.put(name, effect);
		return this;
	}

	public ItemFactory effect(PotionEffectType type, int duration, int amplifier) {
		effect(new PotionEffect(type, duration == -1 ? 1000000 : duration, amplifier));
		return this;
	}

	public ItemFactory effect(PotionEffectType type, int duration) {
		effect(new PotionEffect(type, duration == -1 ? 1000000 : duration, 1));
		return this;
	}

	public ItemStack build() {
		return is;
	}
	
	private String translate(String string) {
	    return ChatColor.translateAlternateColorCodes('&', string);
	}
}