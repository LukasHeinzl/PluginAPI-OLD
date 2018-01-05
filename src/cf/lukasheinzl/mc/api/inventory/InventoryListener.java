package cf.lukasheinzl.mc.api.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * This class can listen for inventory-click-events on the given APIInventory.
 * 
 * @author Lukas Heinzl
 *
 */
public class InventoryListener implements Listener{

	private final APIInventory							i;
	private Map<Integer, Consumer<InventoryClickEvent>>	actions	= new HashMap<>();
	private boolean										shouldCancle;

	/**
	 * Constructs a new InventoryListener for the given APIInventory.
	 * 
	 * @param i
	 *            The APIInventory to listen for
	 * @see #InventoryListener(APIInventory, boolean) InventoryListener(APIInventory, boolean) - boolean defaults to
	 *      false
	 */
	public InventoryListener(APIInventory i){
		this(i, false);
	}

	/**
	 * Constructs a new InventoryListener for the given APIInventory.
	 * 
	 * @param i
	 *            The APIInventory to listen for
	 * @param shouldCancle
	 *            If the event-handling should stop after this listener
	 */
	public InventoryListener(APIInventory i, boolean shouldCancle){
		this.i = i;
		this.shouldCancle = shouldCancle;
	}

	/**
	 * Returns the APIInventory to listen for
	 * 
	 * @return The APIInventory to listen for
	 */
	public APIInventory getInventory(){
		return i;
	}

	/**
	 * Returns if the event-handling should be cancelled after this listener.
	 * 
	 * @return If the event-handling should be cancelled after this listener
	 */
	public boolean isShouldCancle(){
		return shouldCancle;
	}

	/**
	 * Sets if the event-handling should be cancelled after this listener.
	 * 
	 * @param shouldCancle
	 *            if the event-handling should be cancelled after this listener
	 */
	public void setShouldCancle(boolean shouldCancle){
		this.shouldCancle = shouldCancle;
	}

	/**
	 * Registers a new action for every inventory-slot.
	 * 
	 * @param action
	 *            The action to perform
	 * @return This InventoryListener - allows method chaining
	 */
	public InventoryListener registerAction(Consumer<InventoryClickEvent> action){
		for(int i = 0; i < this.i.getInventory().getSize(); i++){
			ItemStack is = this.i.getInventory().getItem(i);

			if(is != null){
				registerAction(i, action);
			}
		}

		return this;
	}

	/**
	 * Registers a new action for the given inventory-slot.
	 * 
	 * @param row
	 *            The row of the slot
	 * @param col
	 *            The column of the slot
	 * @param action
	 *            The action to perform
	 * @return This InventoryListener - allows method chaining
	 * @see #registerAction(int, Consumer) registerAction(int, Consumer) - int is row * 9 + col
	 */
	public InventoryListener registerAction(int row, int col, Consumer<InventoryClickEvent> action){
		return registerAction(row * 9 + col, action);
	}

	/**
	 * Registers a new action for the given inventory-slot.
	 * 
	 * @param slot
	 *            The slot-id
	 * @param action
	 *            The action to perform
	 * @return This InventoryListener - allows method chaining
	 */
	public InventoryListener registerAction(int slot, Consumer<InventoryClickEvent> action){
		actions.put(slot, action);
		return this;
	}

	/**
	 * Removes the action for the given inventory-slot.
	 * 
	 * @param row
	 *            The row of the slot
	 * @param col
	 *            The column of the slot
	 * @return This InventoryListener - allows method chaining
	 * @see #removeAction(int) removeAction(int) - int is row * 9 + col
	 */
	public InventoryListener removeAction(int row, int col){
		return removeAction(row * 9 + col);
	}

	/**
	 * Removes the action for the given inventory-slot.
	 * 
	 * @param slot
	 *            The slot-id
	 * @return This InventoryListener - allows method chaining
	 */
	public InventoryListener removeAction(int slot){
		actions.remove(slot);
		return this;
	}

	@EventHandler
	public void handle(InventoryClickEvent e){
		if(!e.getInventory().equals(i.getInventory())){
			return;
		}

		e.setCancelled(shouldCancle);

		Consumer<InventoryClickEvent> action = actions.get(e.getSlot());
		if(action != null){
			action.accept(e);
		}
	}

}
