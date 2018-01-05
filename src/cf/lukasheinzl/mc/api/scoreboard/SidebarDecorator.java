package cf.lukasheinzl.mc.api.scoreboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

/**
 * This class can be used to create and 'design' sidebar-scoreboards. It consists of header, center and footer sections
 * that can be changed independently from each other.
 * 
 * @author Lukas Heinzl
 *
 */
public class SidebarDecorator{

	private final APIScoreboard	s;
	private final String		name;
	private final List<String>	header	= new ArrayList<>();
	private final List<String>	center	= new ArrayList<>();
	private final List<String>	footer	= new ArrayList<>();

	/**
	 * Constructs a new SidebarDecorator with the given display name.
	 * 
	 * @param name
	 *            The display name of this sidebar-scoreboard
	 * @see #SidebarDecorator(APIScoreboard, String)
	 */
	public SidebarDecorator(String name){
		this(new APIScoreboard(), name);
	}

	/**
	 * Constructs a new SidebarDecorator with the given APIScoreboard and display name.
	 * 
	 * @param s
	 * @param name
	 */
	public SidebarDecorator(APIScoreboard s, String name){
		this.s = s;
		this.name = name;

		s.registerObjective("obj", "dummy", name, DisplaySlot.SIDEBAR);
	}

	/**
	 * Returns the display name of this sidebar-scoreboard.
	 * 
	 * @return The display name of this sidebar-scoreboard
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns the header lines.
	 * 
	 * @return The header lines
	 */
	public List<String> getHeader(){
		return header;
	}

	/**
	 * Returns the center lines.
	 * 
	 * @return The center lines
	 */
	public List<String> getCenter(){
		return center;
	}

	/**
	 * Returns the footer lines.
	 * 
	 * @return The footer lines
	 */
	public List<String> getFooter(){
		return footer;
	}

	/**
	 * Sets the header lines. This triggers an update.
	 * 
	 * @param header
	 *            The header lines
	 */
	public void setHeader(List<String> header){
		this.header.clear();
		this.header.addAll(header);
		update();
	}

	/**
	 * Sets the center lines. This triggers an update.
	 * 
	 * @param center
	 *            The center lines
	 */
	public void setCenter(List<String> center){
		this.center.clear();
		this.center.addAll(center);
		update();
	}

	/**
	 * Sets the footer lines. This triggers an update.
	 * 
	 * @param footer
	 *            The footer lines
	 */
	public void setFooter(List<String> footer){
		this.footer.clear();
		this.footer.addAll(footer);
		update();
	}

	/**
	 * Sets the header lines. This triggers an update.
	 * 
	 * @param header
	 *            The header lines
	 * @see #setHeader(List)
	 */
	public void setHeader(String... header){
		setHeader(Arrays.asList(header));
	}

	/**
	 * Sets the center lines. This triggers an update.
	 * 
	 * @param center
	 *            The center lines
	 * @see #setCenter(List)
	 */
	public void setCenter(String... center){
		setCenter(Arrays.asList(center));
	}

	/**
	 * Sets the footer lines. This triggers an update.
	 * 
	 * @param footer
	 *            The footer lines
	 * @see #setFooter(List)
	 */
	public void setFooter(String... footer){
		setFooter(Arrays.asList(footer));
	}

	/**
	 * Adds the given player to the list of players that are viewing of this scoreboard. It also sets this scoreboard as
	 * the player's current scoreboard.
	 * 
	 * @param p
	 *            The player to add
	 * @see cf.lukasheinzl.mc.api.scoreboard.APIScoreboard#addPlayer(Player)
	 */
	public void addPlayer(Player p){
		s.addPlayer(p);
	}

	/**
	 * Removes the given player from the list of players that are viewing of this scoreboard. It also sets the player's
	 * current scoreboard to a new one.
	 * 
	 * @param p
	 *            The player to remove
	 * @see cf.lukasheinzl.mc.api.scoreboard.APIScoreboard#removePlayer(Player)
	 */
	public void removePlayer(Player p){
		s.removePlayer(p);
	}

	/**
	 * Resets the sidebar for all registered players and sets the current lines. <br>
	 * The header lines will be added first, then the center lines and at last the footer lines.
	 * 
	 * @see cf.lukasheinzl.mc.api.scoreboard.APIScoreboard#update()
	 */
	public void update(){
		s.getObjective("obj").resetAllScores();

		int total = header.size() + center.size() + footer.size();
		int count = 0;

		for(int i = 0; i < header.size(); i++){
			s.setScore("obj", header.get(i), total - count);
			count++;
		}

		for(int i = 0; i < center.size(); i++){
			s.setScore("obj", center.get(i), total - count);
			count++;
		}

		for(int i = 0; i < footer.size(); i++){
			s.setScore("obj", footer.get(i), total - count);
			count++;
		}

		s.update();
	}

}
