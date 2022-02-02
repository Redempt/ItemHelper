package redempt.itemhelper;

import org.bukkit.inventory.ItemStack;

public abstract class MultiItemProperty<T> extends ItemProperty<T> {
	
	public MultiItemProperty(String name) {
		super(name);
	}
	
	public abstract ItemStack remove(ItemStack item, T t);
	
	public boolean requiresArgument() {
		return true;
	}
	
}
