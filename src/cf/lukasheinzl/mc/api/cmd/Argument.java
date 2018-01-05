package cf.lukasheinzl.mc.api.cmd;

/**
 * This interface if the base of all CommandHandler arguments.
 * 
 * @author Lukas Heinzl
 *
 */
public interface Argument{

	/**
	 * This argument type represents an optional argument.
	 */
	Argument OPTIONAL = (s) -> true;

	/**
	 * This method can be used to check if the given argument is of a certain type.
	 * 
	 * @param arg
	 *            The argument to check
	 * @return True if it fits a certain criteria
	 */
	boolean checkArgument(String arg);

}
