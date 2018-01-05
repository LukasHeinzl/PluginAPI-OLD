package cf.lukasheinzl.mc.api.scoreboard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

/**
 * This class represents a scoreboard.
 * 
 * @author Lukas Heinzl
 *
 */
public class APIScoreboard implements Serializable{

	private static final long			serialVersionUID	= -4722894127374890177L;
	private transient Scoreboard		s;
	private final List<APIObjective>	objectives			= new ArrayList<>();
	private final List<APITeam>			teams				= new ArrayList<>();
	private final List<Player>			players				= new ArrayList<>();

	/**
	 * Constructs a new APIScoreboard.
	 */
	public APIScoreboard(){
		s = Bukkit.getScoreboardManager().getNewScoreboard();
	}

	/**
	 * Returns Bukkit's scoreboard.
	 * 
	 * @return Bukkit's scoreboard
	 */
	public Scoreboard getScoreboard(){
		return s;
	}

	/**
	 * Registers a new team with the given internal name.
	 * 
	 * @param name
	 *            The internal name of the team
	 * @return The APITeam that was created
	 */
	public APITeam registerTeam(String name){
		APITeam t = new APITeam(s.registerNewTeam(name));
		teams.add(t);

		return t;
	}

	/**
	 * Registers a new team with the given internal name and display name.
	 * 
	 * @param name
	 *            The internal name of the team
	 * @param displayName
	 *            The display name of the team
	 * @return The APITeam that was created
	 */
	public APITeam registerTeam(String name, String displayName){
		APITeam t = registerTeam(name);
		t.setDisplayName(displayName);

		return t;
	}

	/**
	 * Unregisters the given team.
	 * 
	 * @param name
	 *            The internal name of the team
	 */
	public void unregisterTeam(String name){
		APITeam t = getTeam(name);
		t.getTeam().unregister();
		teams.remove(t);
	}

	/**
	 * Returns the team with the given internal name.
	 * 
	 * @param name
	 *            The internal name of the team
	 * @return The APITeam with the given name
	 */
	public APITeam getTeam(String name){
		for(APITeam t: teams){
			if(t.getTeam().getName().equals(name)){
				return t;
			}
		}

		return null;
	}

	/**
	 * Adds the given player to the given team.
	 * 
	 * @param player
	 *            The name of the player to add
	 * @param team
	 *            The internal name of the team
	 * @see cf.lukasheinzl.mc.api.scoreboard.APITeam#addEntry(String) APITeam.addEntry(String)
	 */
	public void addPlayerToTeam(String player, String team){
		getTeam(team).addEntry(player);
	}

	/**
	 * Removes the given player from the given team.
	 * 
	 * @param player
	 *            The name of the player to remove
	 * @param team
	 *            The internal name of the team
	 * @see cf.lukasheinzl.mc.api.scoreboard.APITeam#removeEntry(String) APITeam.removeEntry(String)
	 */
	public void removePlayerFromTeam(String player, String team){
		getTeam(team).removeEntry(player);
	}

	public APIObjective registerObjective(String name, String type){
		APIObjective o = new APIObjective(s.registerNewObjective(name, type));
		objectives.add(o);

		return o;
	}

	/**
	 * Registers a new objective with the given internal name, display name and type.
	 * 
	 * @param name
	 *            The internal name of this objective
	 * @param type
	 *            The objective-type
	 * @param displayName
	 *            The display name of this objective
	 * @return
	 */
	public APIObjective registerObjective(String name, String type, String displayName){
		APIObjective o = registerObjective(name, type);
		o.getObjective().setDisplayName(displayName);

		return o;
	}

	/**
	 * Registers a new objective with the given internal name, display name and type in the given DisplaySlot.
	 * 
	 * @param name
	 *            The internal name of this objective
	 * @param type
	 *            The objective-type
	 * @param displayName
	 *            The display name of this objective
	 * @param slot
	 *            The DisplaySlot to show to objective in
	 * @return
	 */
	public APIObjective registerObjective(String name, String type, String displayName, DisplaySlot slot){
		APIObjective o = registerObjective(name, type, displayName);
		o.getObjective().setDisplaySlot(slot);

		return o;
	}

	/**
	 * Unregisters the given objective.
	 * 
	 * @param name
	 *            The internal name of the objective
	 */
	public void unregisterObjective(String name){
		APIObjective o = getObjective(name);
		o.getObjective().unregister();
		objectives.remove(o);
	}

	/**
	 * Returns the objective with the given internal name.
	 * 
	 * @param name
	 *            The internal name of the objective
	 * @return The APIObjective with the given name
	 */
	public APIObjective getObjective(String name){
		for(APIObjective o: objectives){
			if(o.getObjective().getName().equals(name)){
				return o;
			}
		}

		return null;
	}

	/**
	 * Sets the score for the given name to the given value in the given objective.
	 * 
	 * @param objective
	 *            The name of the objective
	 * @param name
	 *            The name of the score-holder
	 * @param score
	 *            The value of the score
	 * @see cf.lukasheinzl.mc.api.scoreboard.APIObjective#setScore(String, int)
	 */
	public void setScore(String objective, String name, int score){
		getObjective(objective).setScore(name, score);
	}

	/**
	 * Sets the score for the given name to the given value and display name in the given objective.
	 * 
	 * @param objective
	 *            The name of the objective
	 * @param name
	 *            The internal name of the score-holder
	 * @param displayName
	 *            The display name of the score-holder
	 * @param score
	 *            The value of the score
	 * @see cf.lukasheinzl.mc.api.scoreboard.APIObjective#setScore(String, String, int)
	 */
	public void setScore(String objective, String name, String displayName, int score){
		getObjective(objective).setScore(name, displayName, score);
	}

	/**
	 * Returns the value of the score-holder in the given objective.
	 * 
	 * @param objective
	 *            The name of the objective
	 * @param name
	 *            The name of the score-holder
	 * @return The value of the score-holder
	 * @see cf.lukasheinzl.mc.api.scoreboard.APIObjective#getScore(String)
	 */
	public int getScore(String objective, String name){
		return getObjective(objective).getScore(name);
	}

	/**
	 * Returns the value of the score-holder in the given objective.
	 * 
	 * @param objective
	 *            The name of the objective
	 * @param name
	 *            The internal name of the score-holder
	 * @return The value of the score-holder
	 * @see cf.lukasheinzl.mc.api.scoreboard.APIObjective#getScoreInternal(String)
	 */
	public int getScoreInternal(String objective, String name){
		return getObjective(objective).getScoreInternal(name);
	}

	/**
	 * Adds the given player to the list of players that are viewing of this scoreboard. It also sets this scoreboard as
	 * the player's current scoreboard.
	 * 
	 * @param p
	 *            The player to add
	 */
	public void addPlayer(Player p){
		players.add(p);
		p.setScoreboard(s);
	}

	/**
	 * Removes the given player from the list of players that are viewing of this scoreboard. It also sets the player's
	 * current scoreboard to a new one.
	 * 
	 * @param p
	 *            The player to remove
	 */
	public void removePlayer(Player p){
		players.remove(p);
		p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}

	/**
	 * Returns the list of players that are viewing this scoreboard.
	 * 
	 * @return The list of players that are viewing this scoreboard
	 */
	public List<Player> getPlayers(){
		return players;
	}

	/**
	 * Should be called after an scoreboard change. Sets this scoreboard as the current scoreboard for all registered
	 * players.
	 */
	public void update(){
		for(Player p: players){
			p.setScoreboard(s);
		}
	}

	private void writeObject(ObjectOutputStream oos) throws IOException{
		oos.defaultWriteObject();

		for(APIObjective o: objectives){
			oos.writeObject(o.getObjective().getName());
			oos.writeObject(o.getObjective().getDisplayName());
			oos.writeObject(o.getObjective().getCriteria());
			oos.writeObject(o.getObjective().getDisplaySlot());
		}

		for(APITeam t: teams){
			oos.writeObject(t.getTeam().getName());
			oos.writeObject(t.getDisplayName());
		}
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
		ois.defaultReadObject();
		s = Bukkit.getScoreboardManager().getNewScoreboard();

		for(APIObjective o: objectives){
			String name = (String) ois.readObject();
			String displayName = (String) ois.readObject();
			String criteria = (String) ois.readObject();
			DisplaySlot slot = (DisplaySlot) ois.readObject();

			o.setScores(s.registerNewObjective(name, criteria));
			o.getObjective().setDisplayName(displayName);
			o.getObjective().setDisplaySlot(slot);
		}

		for(APITeam t: teams){
			String name = (String) ois.readObject();
			String displayName = (String) ois.readObject();

			t.setEntries(s.registerNewTeam(name));
			t.setDisplayName(displayName);
		}
	}

	/**
	 * Creates a copy of this scoreboard.
	 * 
	 * @return A new APIScoreboard object with the same contents as this one
	 */
	public APIScoreboard clone(){
		try(PipedOutputStream pos = new PipedOutputStream();
				PipedInputStream pis = new PipedInputStream(pos);
				ObjectOutputStream oos = new ObjectOutputStream(pos);
				ObjectInputStream ois = new ObjectInputStream(pis)){
			oos.writeObject(this);
			return (APIScoreboard) ois.readObject();
		} catch(IOException | ClassNotFoundException e){
			return null;
		}
	}

}
