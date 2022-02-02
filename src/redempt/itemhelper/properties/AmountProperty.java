package redempt.itemhelper.properties;

import org.bukkit.inventory.ItemStack;
import redempt.itemhelper.ItemProperty;
import redempt.redlib.commandmanager.ArgType;

public class AmountProperty extends ItemProperty<Integer> {
	
	public AmountProperty() {
		super("amount");
	}
	
	@Override
	public ArgType<?> createArgType() {
		return ArgType.getDefault("int");
	}
	
	@Override
	public ItemStack apply(ItemStack item, Integer amount, int level) {
		item.setAmount(amount);
		return item;
	}
	
}
