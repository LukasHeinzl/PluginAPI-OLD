package cf.lukasheinzl.mc.api;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class API extends JavaPlugin{

	public static final String	VERSION			= "1.0";
	public static final String	JAVA_VERSION	= "1.8.0_151";

	@Override
	public void onEnable(){
		System.out.println("PluginAPI by Lukas Heinzl | Version " + VERSION + " (Java " + JAVA_VERSION + ")");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equals("pluginapi")){
			sender.sendMessage(ChatColor.RED + "PluginAPI by Lukas Heinzl" + ChatColor.RESET + " | " + ChatColor.GREEN + "Version " + VERSION
					+ " (Java " + JAVA_VERSION + ")");
			return true;
		}

		return false;
	}

}
