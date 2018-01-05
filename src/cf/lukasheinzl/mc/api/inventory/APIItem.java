package cf.lukasheinzl.mc.api.inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

/**
 * This class represents an item.
 * 
 * @author Lukas Heinzl
 *
 */
public class APIItem{

	private final ItemStack is;

	/**
	 * Constructs a new APIItem of the given type and amount.
	 * 
	 * @param type
	 *            The type of this item
	 * @param amount
	 *            The amount of this item
	 * @see #APIItem(ItemStack)
	 */
	public APIItem(Material type, int amount){
		this(new ItemStack(type, amount));
	}

	/**
	 * Constructs a new APIItem representing the given ItemStack.
	 * 
	 * @param is
	 *            The ItemStack this APIItem represents
	 */
	public APIItem(ItemStack is){
		this.is = is;
	}

	/**
	 * Constructs a new APIItem of the given type and amount and with the given display name.
	 * 
	 * @param type
	 *            The type of this item
	 * @param amount
	 *            The amount of this item
	 * @param name
	 *            The display name of this item
	 * @see #APIItem(ItemStack, String)
	 */
	public APIItem(Material type, int amount, String name){
		this(new ItemStack(type, amount), name);
	}

	/**
	 * Constructs a new APIItem representing the given ItemStack and with the given display name.
	 * 
	 * @param is
	 *            The ItemStack this APIItem represents
	 * @param name
	 *            The display name of this item
	 */
	public APIItem(ItemStack is, String name){
		this.is = is;
		setName(name);
	}

	/**
	 * Returns the ItemStack this item is representing.
	 * 
	 * @return The ItemStack this item is representing
	 */
	public ItemStack getItemStack(){
		return is;
	}

	/**
	 * Returns the display name of this item.
	 * 
	 * @return The display name of this item
	 */
	public String getName(){
		String name = is.getItemMeta().getDisplayName();

		if(name == null){
			return is.toString();
		}

		return name;
	}

	/**
	 * Sets the display name of this item.
	 * 
	 * @param name
	 *            The display name of this item
	 * @return This APIItem - allows method chaining
	 */
	public APIItem setName(String name){
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);

		return this;
	}

	/**
	 * Returns the lore of this item.
	 * 
	 * @return The lore of this item
	 */
	public List<String> getLore(){
		ItemMeta im = is.getItemMeta();
		return im.getLore();
	}

	/**
	 * Sets the lore of this item.
	 * 
	 * @param lore
	 *            The lore of this item
	 * @return This APIItem - allows method chaining
	 */
	public APIItem setLore(List<String> lore){
		ItemMeta im = is.getItemMeta();
		im.setLore(lore);
		is.setItemMeta(im);

		return this;
	}

	/**
	 * Sets the lore of this item.
	 * 
	 * @param lore
	 *            The lore of this item
	 * @return This APIItem - allows method chaining
	 * @see #setLore(List)
	 */
	public APIItem setLore(String... lore){
		return setLore(Arrays.asList(lore));
	}

	/**
	 * Constructs a new APIItem of type {@link org.bukkit.Material#POTION POTION} of the given amount and potion type.
	 * 
	 * @param amount
	 *            The amount of this item
	 * @param data
	 *            The potion type
	 * @return The newly created APIItem
	 */
	public static APIItem newPotionItem(int amount, PotionData data){
		APIItem ai = new APIItem(Material.POTION, amount);
		PotionMeta pm = (PotionMeta) ai.getItemStack().getItemMeta();

		pm.setBasePotionData(data);
		ai.getItemStack().setItemMeta(pm);

		return ai;
	}

	/**
	 * Constructs a new APIItem of type {@link org.bukkit.Material#SPLASH_POTION SPLASH_POTION} of the given amount and
	 * potion type.
	 * 
	 * @param amount
	 *            The amount of this item
	 * @param data
	 *            The potion type
	 * @return The newly created APIItem
	 */
	public static APIItem newSplashPotionItem(int amount, PotionData data){
		APIItem ai = new APIItem(Material.SPLASH_POTION, amount);
		PotionMeta pm = (PotionMeta) ai.getItemStack().getItemMeta();

		pm.setBasePotionData(data);
		ai.getItemStack().setItemMeta(pm);

		return ai;
	}

	/**
	 * Constructs a new APIItem of type {@link org.bukkit.Material#TIPPED_ARROW TIPPED_ARROW} of the given amount and
	 * potion type.
	 * 
	 * @param amount
	 *            The amount of this item
	 * @param data
	 *            The potion type
	 * @return The newly created APIItem
	 */
	public static APIItem newTippedArrowItem(int amount, PotionData data){
		APIItem ai = new APIItem(Material.TIPPED_ARROW, amount);
		PotionMeta pm = (PotionMeta) ai.getItemStack().getItemMeta();

		pm.setBasePotionData(data);
		ai.getItemStack().setItemMeta(pm);

		return ai;
	}

	/**
	 * Constructs a new APIITem representing the given ItemStack and containing the given enchantments.
	 * 
	 * @param is
	 *            The ItemStack this item should represent
	 * @param enchants
	 *            The enchantments this item should contain
	 * @return The newly created APIItem
	 */
	public static APIItem newEnchantedItem(ItemStack is, APIEnchantment... enchants){
		APIItem ai = new APIItem(is);
		ItemMeta im = ai.getItemStack().getItemMeta();

		for(APIEnchantment e: enchants){
			im.addEnchant(e.getEnchantment(), e.getLevel(), e.isIgnoreLevelRestriction());
		}

		ai.getItemStack().setItemMeta(im);
		return ai;
	}

}
