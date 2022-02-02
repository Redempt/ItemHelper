package redempt.itemhelper.properties;

import org.bukkit.inventory.ItemStack;
import redempt.itemhelper.ItemProperty;
import redempt.redlib.commandmanager.ArgType;

public class DamageProperty extends ItemProperty<Integer> {
	
	public DamageProperty() {
		super("damage");
	}
	
	@Override
	public ArgType<?> createArgType() {
		return ArgType.getDefault("int");
	}
	
	@Override
	public ItemStack apply(ItemStack item, Integer damage, int level) {
		item.setDurability((short) (int) damage);
		return item;
	}
	
}
