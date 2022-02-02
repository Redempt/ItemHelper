package redempt.itemhelper.properties;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.itemhelper.ItemHelper;
import redempt.itemhelper.MultiItemProperty;
import redempt.redlib.commandmanager.ArgType;

import java.util.ArrayList;
import java.util.List;

public class LoreProperty extends MultiItemProperty<String> {
	
	public LoreProperty() {
		super("lore");
	}
	
	@Override
	public ArgType<?> createArgType() {
		return ItemHelper.colorType;
	}
	
	@Override
	public ItemStack apply(ItemStack item, String s, int level) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		lore = lore == null ? new ArrayList<>() : lore;
		if (level == -1 || level >= lore.size()) {
			lore.add(s);
		} else {
			lore.add(level, s);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	@Override
	public ItemStack remove(ItemStack item, String s) {
		ItemMeta meta = item.getItemMeta();
		if (s == null) {
			meta.setLore(null);
			item.setItemMeta(meta);
			return item;
		}
		List<String> lore = meta.getLore();
		if (lore == null) {
			return item;
		}
		if (s.chars().allMatch(Character::isDigit)) {
			int index = Integer.parseInt(s);
			if (index >= lore.size()) {
				return item;
			}
			lore.remove(index);
			meta.setLore(lore);
			item.setItemMeta(meta);
			return item;
		}
		lore.remove(s);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	@Override
	public boolean requiresArgument() {
		return false;
	}
	
}
