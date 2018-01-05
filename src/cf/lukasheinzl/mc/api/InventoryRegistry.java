package cf.lukasheinzl.mc.api;

import java.util.HashMap;
import java.util.Map;

import cf.lukasheinzl.mc.api.inventory.APIInventory;

/**
 * This class can be used to manage {@link cf.lukasheinzl.mc.api.inventory.APIInventory APIInventories}.
 * 
 * @author Lukas Heinzl
 *
 */
public class InventoryRegistry{

	private static final Map<String, APIInventory> INVS = new HashMap<>();

	private InventoryRegistry(){

	}

	/**
	 * Register the APIInventory. It can be access by its name (see
	 * {@link cf.lukasheinzl.mc.api.inventory.APIInventory#getName() getName()})
	 * 
	 * @param i
	 *            The APIInventory to register
	 */
	public static void register(APIInventory i){
		INVS.put(i.getName(), i);
	}

	/**
	 * Registers the APIInventory with the given name.
	 * 
	 * @param name
	 *            The internal name of this inventory
	 * @param i
	 *            The APIInventory to register
	 */
	public static void register(String name, APIInventory i){
		INVS.put(name, i);
	}

	/**
	 * Removes the APIInventory from the registry.
	 * 
	 * @param i
	 *            The APIInventory to remove
	 * @see #remove(String)
	 */
	public static void remove(APIInventory i){
		remove(i.getName());
	}

	/**
	 * Removes the APIInventory from the registry.
	 * 
	 * @param name
	 *            The name of the APIInventory to remove
	 */
	public static void remove(String name){
		INVS.remove(name);
	}

	/**
	 * Returns the APIInventory, if registered, with the given name.
	 * 
	 * @param name
	 *            The name of the APIInventory to search for
	 * @return The APIInventory, if registered or null
	 */
	public static APIInventory get(String name){
		return INVS.get(name);
	}
}
