package redempt.itemhelper.properties;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.itemhelper.MultiItemProperty;
import redempt.redlib.commandmanager.ArgType;

import java.util.Arrays;

public class EnchantmentProperty extends MultiItemProperty<Enchantment> {
	
	public EnchantmentProperty() {
		super("enchant");
	}
	
	@Override
	public ArgType<?> createArgType() {
		return new ArgType<>("enchant", s -> Enchantment.getByName(s.toUpperCase()))
				.tabStream(c -> Arrays.stream(Enchantment.values()).map(Enchantment::getName));
	}
	
	@Override
	public ItemStack apply(ItemStack item, Enchantment enchantment, int level) {
		level = level == -1 ? 1 : level;
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchantment, level, true);
		item.setItemMeta(meta);
		return item;
	}
	
	@Override
	public ItemStack remove(ItemStack item, Enchantment enchantment) {
		item.removeEnchantment(enchantment);
		return item;
	}
	
}
