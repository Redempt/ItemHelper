package redempt.itemhelper.properties;

import org.bukkit.inventory.ItemStack;
import redempt.itemhelper.ItemHelper;
import redempt.itemhelper.MultiItemProperty;
import redempt.redlib.commandmanager.ArgType;
import redempt.redlib.itemutils.ItemUtils;

public class NameProperty extends MultiItemProperty<String> {
	
	public NameProperty() {
		super("name");
	}
	
	@Override
	public ArgType<?> createArgType() {
		return ItemHelper.colorType;
	}
	
	@Override
	public ItemStack apply(ItemStack item, String s, int level) {
		return ItemUtils.setName(item, s);
	}
	
	@Override
	public ItemStack remove(ItemStack item, String s) {
		return ItemUtils.setName(item, null);
	}
	
	@Override
	public boolean requiresArgument() {
		return false;
	}
	
}
