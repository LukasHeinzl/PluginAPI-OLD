package cf.lukasheinzl.mc.api.inventory;

import java.util.function.Consumer;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * This class contains a few useful inventory action that can be used by the
 * {@link cf.lukasheinzl.mc.api.inventory.InventoryListener InventoryListener}.
 * 
 * @author Lukas Heinzl
 *
 */
public class InventoryAction{

	private InventoryAction(){

	}

	/**
	 * This consumer adds the clicked item to the player's inventory.
	 * 
	 * @return The consumer
	 */
	public static Consumer<InventoryClickEvent> getClickedItemSupplier(){
		return e -> e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
	}

	/**
	 * This consumer adds the given ItemStack to the player's inventory.
	 * 
	 * @param is
	 *            The ItemStack to add
	 * @return The consumer
	 */
	public static Consumer<InventoryClickEvent> getItemSupplier(ItemStack is){
		return e -> e.getWhoClicked().getInventory().addItem(is);
	}

	/**
	 * This consumer sets the player's armor to the given ItemStacks.
	 * 
	 * @param helmet
	 *            The ItemStack for the helmet slot
	 * @param chestplate
	 *            The ItemStack for the chestplate slot
	 * @param leggings
	 *            The ItemStack for the leggings slot
	 * @param boots
	 *            The ItemStack for the boots slot
	 * @return The consumer
	 */
	public static Consumer<InventoryClickEvent> getArmorSupplier(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots){
		return e -> {
			e.getWhoClicked().getInventory().setHelmet(helmet);
			e.getWhoClicked().getInventory().setChestplate(chestplate);
			e.getWhoClicked().getInventory().setLeggings(leggings);
			e.getWhoClicked().getInventory().setBoots(boots);
		};
	}

	/**
	 * This consumer opens the given APIInventroy for the player.
	 * 
	 * @param which
	 *            The APIInventory to open
	 * @return The consumer
	 */
	public static Consumer<InventoryClickEvent> getOpenInventoryAction(APIInventory which){
		return e -> e.getWhoClicked().openInventory(which.getInventory());
	}

}
