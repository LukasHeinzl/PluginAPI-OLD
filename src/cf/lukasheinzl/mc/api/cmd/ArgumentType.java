package cf.lukasheinzl.mc.api.cmd;

import org.bukkit.Bukkit;

import net.md_5.bungee.api.ChatColor;

/**
 * This enum contains a few common argument types.
 * 
 * @author Lukas Heinzl
 *
 */
public enum ArgumentType implements Argument{

	/**
	 * This represents a whole number.
	 */
	INT("Integer required", ArgumentType::checkInt),

	/**
	 * This represents a floatingpoint number.
	 */
	FLOAT("Float required", ArgumentType::checkFloat),

	/**
	 * This represents the tokens true and false.
	 */
	BOOLEAN("boolean required", ArgumentType::checkBoolean),

	/**
	 * This represents normal text.
	 */
	String("This message should not be visible", ArgumentType::checkTrue),

	/**
	 * This represents online players. It will check if the argument matches the name of a currently online player.
	 */
	ONLINE_PLAYER("Name of currently online player required", ArgumentType::checkOnlinePlayer);

	private final String	msg;
	private final Argument	arg;

	private ArgumentType(String msg, Argument arg){
		this.msg = msg;
		this.arg = arg;
	}

	@Override
	public String toString(){
		return ChatColor.RED + msg + ChatColor.RESET + ": ";
	}

	@Override
	public boolean checkArgument(String arg){
		if(this.arg == null){
			return false;
		}

		return this.arg.checkArgument(arg);
	}

	private static boolean checkTrue(String arg){
		return true;
	}

	private static boolean checkInt(String arg){
		try{
			Long.parseLong(arg);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}

	private static boolean checkFloat(String arg){
		try{
			Double.parseDouble(arg);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}

	private static boolean checkBoolean(String arg){
		return arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("false");
	}

	private static boolean checkOnlinePlayer(String arg){
		return null != Bukkit.getPlayer(arg);
	}

}
