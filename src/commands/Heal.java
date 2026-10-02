package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player toHeal = null;
		if (args.length < 1 && sender instanceof Player) {
			toHeal = (Player) sender;
        } else {
        	toHeal = Bukkit.getPlayer(args[0]);
        }
		
		if (toHeal == null || !toHeal.isOnline()) {
	        sender.sendMessage("§cPlayer not found");
	        return false;
	    }
		
		toHeal.setHealth(toHeal.getMaxHealth());
        return true;
	}
}
