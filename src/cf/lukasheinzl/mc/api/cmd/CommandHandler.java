package cf.lukasheinzl.mc.api.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * This class can be used to handle commands. It can be registered in the {@link cf.lukasheinzl.mc.api.CommandRegistry
 * CommandRegistry}.
 * 
 * @author Lukas Heinzl
 *
 */
public abstract class CommandHandler{

	private final Command		cmd;
	private final Argument[]	arguments;

	/**
	 * Constructs a new CommandHandler for the given command and with the given argument types.
	 * 
	 * @param cmd
	 *            The command to handle (see {@link org.bukkit.Bukkit#getPluginCommand(String)})
	 * @param arguments
	 *            The argument types
	 */
	public CommandHandler(Command cmd, Argument... arguments){
		this.cmd = cmd;
		this.arguments = arguments;
	}

	/**
	 * This method checks if the arguments match the argument types both in terms of count and type.
	 * 
	 * @param sender
	 *            The sender of the command
	 * @param args
	 *            The arguments to the command
	 * @return True if the command executed successfully
	 */
	public boolean handle(CommandSender sender, String[] args){
		for(int i = 0; i < arguments.length; i++){
			if(i >= args.length && arguments[i] != Argument.OPTIONAL){
				return false;
			} else if(arguments[i] == Argument.OPTIONAL){
				continue;
			}

			if(!arguments[i].checkArgument(args[i])){
				sender.sendMessage(arguments[i].toString() + args[i]);
				return false;
			}
		}

		return handleCommand(sender, args);
	}

	/**
	 * This method handles the command once it is proven that the arguments are of the correct types.
	 * 
	 * @param sender
	 *            The sender of the command
	 * @param args
	 *            The arguments to the command
	 * @return True if the command executed successfully
	 */
	protected abstract boolean handleCommand(CommandSender sender, String[] args);

	/**
	 * Returns the name of the command.
	 * 
	 * @return The name of the command
	 * @see org.bukkit.command.Command#getName()
	 */
	public String getName(){
		return cmd.getName();
	}

	/**
	 * Returns the argument types.
	 * 
	 * @return The argument types
	 */
	public Argument[] getArgumentTypes(){
		return arguments;
	}

}
