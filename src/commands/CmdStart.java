package commands;

import hungergames.HGameManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//import org.bukkit.scheduler.BukkitTask;

import bedwars.BGameManager;

public class CmdStart implements CommandExecutor {

	public static String gameRunning = "none";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// ArrayList<BukkitTask> tasks = new ArrayList<BukkitTask>();
		if (args.length == 0) {
			return false;
		} else if (gameRunning == "none" && Bukkit.getOnlinePlayers().size() > 1){
			if (args[0].equalsIgnoreCase("hunger")) {
				if (args.length == 1 || (args.length == 2 && (args[1].equals("normal") || args[1].equals("mayhem")))) {
				    HGameManager.startGame(args.length == 2 ? args[1] : "normal");
				} else return false;
				gameRunning = "hunger";
			} else if (args[0].equalsIgnoreCase("bedwars")) {
				BGameManager.startGame();
				gameRunning = "bedwars";
			}
		} else {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (Bukkit.getOnlinePlayers().size() < 2) {
					player.sendMessage("§cNot enough players to start");
				}
			}
		}
		return true;
	}
}
