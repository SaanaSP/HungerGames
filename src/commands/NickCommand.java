package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import boring.shit.Main;

public class NickCommand implements CommandExecutor {
	
	private final Main plugin;
	
	public NickCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) { return true;}
		
		Player player = (Player) sender;
		
		if (args.length != 1) {
			player.sendMessage("§cUsage: /nick <nickname>");
            return true;
        }
		
        String nickname = args[0];
        plugin.getNicknames().put(player.getUniqueId().toString(), nickname);
        player.setDisplayName(nickname);
        player.sendMessage("§aNickname set to: " + nickname);
        return true;
	}
}
