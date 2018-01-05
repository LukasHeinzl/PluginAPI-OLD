package cf.lukasheinzl.mc.api.scoreboard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.scoreboard.Objective;

/**
 * This class represents a scoreboard objective.
 * 
 * @author Lukas Heinzl
 *
 */
public class APIObjective implements Serializable{

	private static final long			serialVersionUID	= -6458304982178297693L;
	private transient Objective			o;
	private final Map<String, Integer>	scores				= new HashMap<>();
	private final Map<String, String>	scoreAliases		= new HashMap<>();

	/**
	 * Constructs a new APIObjective representing the given objective.
	 * 
	 * @param o
	 *            The objective to represent
	 */
	public APIObjective(Objective o){
		this.o = o;
	}

	/**
	 * Returns the objective that is being represented.
	 * 
	 * @return The objective that is being represented
	 */
	public Objective getObjective(){
		return o;
	}

	/**
	 * Sets the score for the given name to the given value.
	 * 
	 * @param name
	 *            The name of the score-holder
	 * @param score
	 *            The value of the score
	 */
	public void setScore(String name, int score){
		o.getScore(name).setScore(score);
		scores.put(name, score);
	}

	/**
	 * Sets the score for the given name to the given value and display name.
	 * 
	 * @param name
	 *            The internal name of the score-holder
	 * @param displayName
	 *            The display name of the score-holder
	 * @param score
	 *            The value of the score
	 */
	public void setScore(String name, String displayName, int score){
		setScore(displayName, score);
		scoreAliases.put(name, displayName);
	}

	/**
	 * Returns the value of the score-holder.
	 * 
	 * @param name
	 *            The name of the score-holder
	 * @return The value of the score-holder
	 */
	public int getScore(String name){
		return o.getScore(name).getScore();
	}

	/**
	 * Returns the value of the score-holder.
	 * 
	 * @param name
	 *            The internal name of the score-holder
	 * @return The value of the score-holder
	 */
	public int getScoreInternal(String name){
		return getScore(scoreAliases.get(name));
	}

	/**
	 * Modifies the score of the given score-holder by the given amount.
	 * 
	 * @param name
	 *            The name of the score-holder
	 * @param delta
	 *            The amount to change the score-value
	 */
	public void modifyScore(String name, int delta){
		setScore(name, getScore(name) + delta);
	}

	/**
	 * Modifies the score of the given score-holder by the given amount.
	 * 
	 * @param name
	 *            The internal name of the score-holder
	 * @param delta
	 *            The amount to change the score-value
	 */
	public void modifyScoreInternal(String name, int delta){
		name = scoreAliases.get(name);
		modifyScore(name, delta);
	}

	/**
	 * Resets the score of the given score-holder.
	 * 
	 * @param name
	 *            The name of the score-holder
	 */
	public void resetScore(String name){
		o.getScoreboard().resetScores(name);
		scores.remove(name);
	}

	/**
	 * Resets the score of the given score-holder.
	 * 
	 * @param name
	 *            The internal name of the score-holder
	 */
	public void resetScoreInternal(String name){
		resetScore(scoreAliases.get(name));
		scoreAliases.remove(name);
	}

	/**
	 * Resets alls scores of this objective.
	 */
	public void resetAllScores(){
		for(String name: scores.keySet()){
			o.getScoreboard().resetScores(name);
		}

		scores.clear();
		scoreAliases.clear();
	}

	void setScores(Objective o){
		this.o = o;

		for(Map.Entry<String, Integer> e: scores.entrySet()){
			o.getScore(e.getKey()).setScore(e.getValue());
		}

	}

}
