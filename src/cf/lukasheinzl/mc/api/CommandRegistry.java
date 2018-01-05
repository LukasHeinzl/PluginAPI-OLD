package cf.lukasheinzl.mc.api;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import cf.lukasheinzl.mc.api.cmd.CommandHandler;

/**
 * This class can be used to manage {@link cf.lukasheinzl.mc.api.cmd.CommandHandler CommandHandlers}. The
 * {@link #handleCommand(CommandSender, Command, String, String[]) handleCommand} method can be called in the
 * {@link org.bukkit.plugin.java.JavaPlugin#onCommand(CommandSender, Command, String, String[]) JavaPlugin.onCommand}
 * method to handle all registered commands.
 * 
 * @author Lukas Heinzl
 *
 */
public class CommandRegistry{

	private static final Map<String, CommandHandler> CMD_HANDLERS = new HashMap<>();

	private CommandRegistry(){

	}

	/**
	 * Register the CommandHandler. It can be access by its name (see
	 * {@link cf.lukasheinzl.mc.api.cmd.CommandHandler#getName() getName()})
	 * 
	 * @param ch
	 *            The CommandHandler to register
	 */
	public static void register(CommandHandler ch){
		CMD_HANDLERS.put(ch.getName(), ch);
	}

	/**
	 * Removes the CommandHandler from the registry.
	 * 
	 * @param ch
	 *            The CommandHandler to remove
	 * @see #remove(String)
	 */
	public static void remove(CommandHandler ch){
		remove(ch.getName());
	}

	/**
	 * Removes the CommandHandler from the registry.
	 * 
	 * @param name
	 *            The name of the CommandHandler to remove
	 */
	public static void remove(String name){
		CMD_HANDLERS.remove(name);
	}

	/**
	 * Returns the CommandHandler, if registered, with the given name.
	 * 
	 * @param name
	 *            The name of the CommandHandler to search for
	 * @return The CommandHandler, if registered or null
	 */
	public static CommandHandler get(String name){
		return CMD_HANDLERS.get(name);
	}

	/**
	 * This method can be called from the
	 * {@link org.bukkit.plugin.java.JavaPlugin#onCommand(CommandSender, Command, String, String[])
	 * JavaPlugin.onCommand} method. It searches all registered CommandHandlers for the issued command.
	 * 
	 * @param sender
	 *            The sender of the command
	 * @param cmd
	 *            The issued command
	 * @param commandLabel
	 *            The alias used
	 * @param args
	 *            The arguments to the command
	 * @return True if the command executed successfully
	 */
	public static boolean handleCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		CommandHandler ch = get(cmd.getName());

		if(ch == null){
			return false;
		}

		return ch.handle(sender, args);
	}

}
