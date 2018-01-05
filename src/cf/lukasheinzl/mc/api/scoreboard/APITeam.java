package cf.lukasheinzl.mc.api.scoreboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.scoreboard.Team;

/**
 * This class represents a scoreboard team.
 * 
 * @author Lukas Heinzl
 *
 */
public class APITeam implements Serializable{

	private static final long	serialVersionUID	= -7218517085793077483L;
	private transient Team		t;
	private final List<String>	entries				= new ArrayList<>();

	/**
	 * Constructs a new APITeam representing the given team.
	 * 
	 * @param t
	 *            The team to represent
	 */
	public APITeam(Team t){
		this.t = t;
	}

	/**
	 * Returns the team that is being represented.
	 * 
	 * @return The team that is being represented
	 */
	public Team getTeam(){
		return t;
	}

	/**
	 * Sets the display name of this team.
	 * 
	 * @param name
	 *            The display name of this team
	 */
	public void setDisplayName(String name){
		t.setDisplayName(name);
	}

	/**
	 * Returns the display name of this team.
	 * 
	 * @return The display name of this team
	 */
	public String getDisplayName(){
		return t.getDisplayName();
	}

	/**
	 * Adds the given name to the team.
	 * 
	 * @param name
	 *            The name to add
	 */
	public void addEntry(String name){
		t.addEntry(name);
		entries.add(name);
	}

	/**
	 * Removed the given name from the team.
	 * 
	 * @param name
	 *            The name to remove
	 */
	public void removeEntry(String name){
		t.removeEntry(name);
		entries.remove(name);
	}

	void setEntries(Team t){
		this.t = t;

		for(String s: entries){
			t.addEntry(s);
		}
	}

}
