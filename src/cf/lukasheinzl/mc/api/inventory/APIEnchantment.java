package cf.lukasheinzl.mc.api.inventory;

import org.bukkit.enchantments.Enchantment;

/**
 * This class represents an enchantment of a certain level.
 * 
 * @author Lukas Heinzl
 *
 */
public class APIEnchantment{

	private final Enchantment	e;
	private final int			level;
	private final boolean		ignoreLevelRestriction;

	/**
	 * Constructs a new APIEnchantment of the given enchantment type and level.
	 * 
	 * @param e
	 *            The enchantment type (see {@link org.bukkit.Enchantment Enchantment})
	 * @param level
	 *            The level of the enchantment (level 1 corresponds to 0)
	 * @param ignoreLevelRestriction
	 *            If the Minecraft's level restriction should be ignored
	 */
	public APIEnchantment(Enchantment e, int level, boolean ignoreLevelRestriction){
		this.e = e;
		this.level = level;
		this.ignoreLevelRestriction = ignoreLevelRestriction;
	}

	/**
	 * Returns the enchantment type.
	 * 
	 * @return The enchantment type
	 */
	public Enchantment getEnchantment(){
		return e;
	}

	/**
	 * Returns the level of the enchantment.
	 * 
	 * @return The level of the enchantment
	 */
	public int getLevel(){
		return level;
	}

	/**
	 * Returns if the level restriction should be ignored.
	 * 
	 * @return If the level restriction should be ignored
	 */
	public boolean isIgnoreLevelRestriction(){
		return ignoreLevelRestriction;
	}

}
