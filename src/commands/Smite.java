package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import boring.shit.Utils;

public class Smite implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player player = null;
		if (args.length < 1 && sender instanceof Player) {
			player = (Player) sender;
        } else {
        	player = Bukkit.getPlayer(args[0]);
        }
		
		if (player == null || !player.isOnline()) {
	        sender.sendMessage("§cPlayer not found");
	        return false;
	    }
		
		player.getWorld().strikeLightningEffect(player.getLocation());
		player.setHealth(0);
        return true;
	}
}
