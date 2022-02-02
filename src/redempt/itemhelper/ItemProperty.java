package redempt.itemhelper;

import org.bukkit.inventory.ItemStack;
import redempt.redlib.commandmanager.ArgType;

public abstract class ItemProperty<T> {

	private String name;
	private ArgType<?> argType;
	
	public ItemProperty(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ArgType<?> getArgType() {
		if (argType == null) {
			argType = createArgType();
		}
		return argType;
	}
	
	public abstract ArgType<?> createArgType();
	public abstract ItemStack apply(ItemStack item, T t, int level);
	
}
