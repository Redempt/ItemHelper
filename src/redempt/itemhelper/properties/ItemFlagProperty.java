package redempt.itemhelper.properties;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.itemhelper.MultiItemProperty;
import redempt.redlib.commandmanager.ArgType;
import redempt.redlib.itemutils.ItemUtils;

public class ItemFlagProperty extends MultiItemProperty<ItemFlag> {
	
	public ItemFlagProperty() {
		super("flag");
	}
	
	@Override
	public ArgType<?> createArgType() {
		return ArgType.of("itemflag", ItemFlag.class);
	}
	
	@Override
	public ItemStack apply(ItemStack item, ItemFlag itemFlag, int level) {
		return ItemUtils.addItemFlags(item, itemFlag);
	}
	
	@Override
	public ItemStack remove(ItemStack item, ItemFlag itemFlag) {
		ItemMeta meta = item.getItemMeta();
		meta.removeItemFlags(itemFlag);
		item.setItemMeta(meta);
		return item;
	}
	
}
