package cf.lukasheinzl.mc.api;

import java.util.HashMap;
import java.util.Map;

import cf.lukasheinzl.mc.api.scoreboard.APIScoreboard;

/**
 * This class can be used to manage {@link cf.lukasheinzl.mc.api.scoreboard.APIScoreboard APIScoreboards}.
 * 
 * @author Lukas Heinzl
 *
 */
public class ScoreboardRegistry{

	private static final Map<String, APIScoreboard> SBS = new HashMap<>();

	private ScoreboardRegistry(){

	}

	/**
	 * Registers the APIScoreboard with given name.
	 * 
	 * @param name
	 *            The internal name of the scoreboard
	 * @param s
	 *            The APIScoreboard to register
	 */
	public static void register(String name, APIScoreboard s){
		SBS.put(name, s);
	}

	/**
	 * Removes the APIScoreboard from the registry.
	 * 
	 * @param name
	 *            The name of the APIScoreboard to remove
	 */
	public static void remove(String name){
		SBS.remove(name);
	}

	/**
	 * Returns the APIScoreboard, if registered, with the given name.
	 * 
	 * @param name
	 *            The name of the APIScoreboard to search for
	 * @return The APIScoreboard, if registered or null
	 */
	public static APIScoreboard get(String name){
		return SBS.get(name);
	}
}
