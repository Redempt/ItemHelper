package redempt.itemhelper.properties;

import org.bukkit.inventory.ItemStack;
import redempt.itemhelper.ItemProperty;
import redempt.redlib.RedLib;
import redempt.redlib.commandmanager.ArgType;
import redempt.redlib.itemutils.ItemUtils;

public class CustomModelDataProperty extends ItemProperty<Integer> {
	
	public CustomModelDataProperty() {
		super("custommodeldata");
	}
	
	@Override
	public ArgType<?> createArgType() {
		return ArgType.getDefault("int");
	}
	
	@Override
	public ItemStack apply(ItemStack item, Integer data, int level) {
		if (RedLib.MID_VERSION < 14) {
			return item;
		}
		return ItemUtils.setCustomModelData(item, data);
	}
	
}
