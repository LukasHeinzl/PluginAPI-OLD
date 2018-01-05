package cf.lukasheinzl.mc.api.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class represents an inventory.
 * 
 * @author Lukas Heinzl
 *
 */
public class APIInventory{

	private final Inventory i;

	/**
	 * Constructs a new APIInventory with the given amount of rows.
	 * 
	 * @param rows
	 *            The amount of rows this inventory should have
	 * @see #APIInventory(Player, int) APIInventory(Player, int) - Player defaults to null
	 */
	public APIInventory(int rows){
		this(null, rows);
	}

	/**
	 * Constructs a new APIInventory with the given player as the inventory holder and the given amount of rows.
	 * 
	 * @param p
	 *            The holder of this inventory
	 * @param rows
	 *            The amount of rows this inventory should have
	 * @see #APIInventory(Player, int, String) APIInventory(Player, int, String) - String defaults to "APIInventory"
	 */
	public APIInventory(Player p, int rows){
		this(p, rows, "APIInventory");
	}

	/**
	 * Constructs a new APIInventory with the given amount of rows and display name.
	 * 
	 * @param rows
	 *            The amount of rows this inventory should have
	 * @param name
	 *            The display name of this inventory
	 * @see #APIInventory(Player, int, String) APIInventory(Player, int, String) - Player defaults to null
	 */
	public APIInventory(int rows, String name){
		this(null, rows, name);
	}

	/**
	 * Constructs a new APIInventory with the given player as the inventory holder, the given amount of rows and display
	 * name.
	 * 
	 * @param p
	 *            The holder of this inventory
	 * @param rows
	 *            The amount of rows this inventory should have
	 * @param name
	 *            The display name of this inventory
	 */
	public APIInventory(Player p, int rows, String name){
		i = Bukkit.createInventory(p, rows * 9, name);
	}

	/**
	 * Returns Bukkit's inventory.
	 * 
	 * @return Bukkit's inventory
	 */
	public Inventory getInventory(){
		return i;
	}

	/**
	 * Returns the display name of this inventory.
	 * 
	 * @return The display name of this inventory
	 */
	public String getName(){
		return i.getTitle();
	}

	/**
	 * Sets the given APIItem into the given slot.
	 * 
	 * @param row
	 *            The row to place the item in
	 * @param col
	 *            The column to place the item in
	 * @param item
	 *            The item to add to the inventory
	 * @return This APIInventory - allows method chaining
	 * @see #setItem(int, APIItem) setItem(int, APIItem) - int is row * 9 + col
	 */
	public APIInventory setItem(int row, int col, APIItem item){
		return setItem(row * 9 + col, item);
	}

	/**
	 * Sets the given APIItem into the given slot.
	 * 
	 * @param pos
	 *            The slot-id to place the item in
	 * @param item
	 *            The item to add to the inventory
	 * @return This APIInventory - allows method chaining
	 */
	public APIInventory setItem(int pos, APIItem item){
		i.setItem(pos, item.getItemStack());
		return this;
	}

	/**
	 * Returns the ItemStack at the given position.
	 * 
	 * @param pos
	 *            The slot-id of the ItemStack
	 * @return The ItemStack at the given position
	 */
	public ItemStack getItem(int pos){
		return i.getItem(pos);
	}

	/**
	 * Creates a copy of this inventory.
	 * 
	 * @return A new APIInventroy object with the same contents as this one
	 */
	public APIInventory clone(){
		APIInventory ai = new APIInventory(i.getSize() / 9, getName());

		for(int j = 0; j < i.getSize(); j++){
			ItemStack item = i.getItem(j);

			if(item != null){
				ai.setItem(j, new APIItem(item.clone()));
			}
		}

		return ai;
	}

}
