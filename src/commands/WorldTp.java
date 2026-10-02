package commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldTp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
		    return true;
		}
		Player player = (Player) sender;
		String targetWorldName = args[0];
		World targetWorld = Bukkit.getWorld(targetWorldName);

		if (targetWorld == null) {
			player.sendMessage("World " + targetWorldName + " isn't loaded.");
			return true;
		}
		
		Location spawn = targetWorld.getSpawnLocation();
		player.teleport(spawn);
		return true;
	}
}
