package redempt.itemhelper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import redempt.itemhelper.properties.AmountProperty;
import redempt.itemhelper.properties.CustomModelDataProperty;
import redempt.itemhelper.properties.DamageProperty;
import redempt.itemhelper.properties.EnchantmentProperty;
import redempt.itemhelper.properties.ItemFlagProperty;
import redempt.itemhelper.properties.LoreProperty;
import redempt.itemhelper.properties.NameProperty;
import redempt.redlib.commandmanager.ArgType;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.commandmanager.Constraint;
import redempt.redlib.commandmanager.ContextProvider;
import redempt.redlib.misc.FormatUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemHelper extends JavaPlugin implements Listener {
	
	public static ItemHelper plugin;
	public static ArgType<String> colorType = ArgType.getDefault("string").map("colorstring", s -> FormatUtils.color((String) s));
	
	private Map<String, ItemProperty<?>> properties = new HashMap<>();
	
	private void registerItemProperties() {
		ItemProperty<?>[] props = {
				new AmountProperty(),
				new CustomModelDataProperty(),
				new DamageProperty(),
				new EnchantmentProperty(),
				new ItemFlagProperty(),
				new LoreProperty(),
				new NameProperty()
		};
		for (ItemProperty<?> prop : props) {
			properties.put(prop.getName(), prop);
		}
	}
	
	@Override
	public void onEnable() {
		plugin = this;
		Bukkit.getPluginManager().registerEvents(this, this);
		registerItemProperties();
		
		ArgType<ItemProperty<?>> propertyType = ArgType.of("property", properties)
				.constraint(s -> Constraint.of(ChatColor.RED + "That trait cannot be removed!", (c, t) -> t instanceof MultiItemProperty));
		ArgType<?> subType = propertyType.subType("value", (sender, prop, str) -> prop.getArgType().convert(sender, null, str))
				.tab((sender, args, prev) -> prev.getArgType().tabComplete(sender, args, null));
		
		new CommandParser(this.getResource("command.rdcml"))
				.setArgTypes(propertyType, subType)
				.setContextProviders(ContextProvider.mainHand)
				.parse().register("itemhelper", this);
	}
	
	@CommandHook("apply")
	public <T> void apply(Player sender, ItemProperty<T> prop, T value, int level, ItemStack item) {
		item = prop.apply(item, value, level);
		sender.setItemInHand(item);
	}
	
	@CommandHook("remove")
	public <T> void remove(Player sender, MultiItemProperty<T> prop, T value, ItemStack item) {
		if (value == null && prop.requiresArgument()) {
			sender.sendMessage(ChatColor.RED + "This property requires an argument to remove!");
			return;
		}
		item = prop.remove(item, value);
		sender.setItemInHand(item);
	}
	
}
