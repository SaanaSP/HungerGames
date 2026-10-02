package commands;

import java.util.Objects;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hungergames.HGameManager;
import bedwars.BGameManager;

public class Debug implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage("§eGame Running: §a" + CmdStart.gameRunning);
			player.sendMessage("§eHG Grace Period: §a" + HGameManager.gracePeriod);
			String names = HGameManager.activeplayers.stream()
                    .map(Player::getName)
                    .collect(Collectors.joining(", "));
			player.sendMessage("§eHG Active Players: §a" + names);
			player.sendMessage("§eBW Teams:");
			player.sendMessage("§9Team 1:");
			names = BGameManager.teams.get(0).stream()
			        .map(Bukkit::getOfflinePlayer)
			        .map(OfflinePlayer::getName)
			        .filter(Objects::nonNull)
			        .collect(Collectors.joining(", "));
			player.sendMessage("§e" + names);
			player.sendMessage("§cTeam 2:");
			names = BGameManager.teams.get(1).stream()
			        .map(Bukkit::getOfflinePlayer)
			        .map(OfflinePlayer::getName)
			        .filter(Objects::nonNull)
			        .collect(Collectors.joining(", "));
			player.sendMessage("§e" + names);
		}
		return true;
	}
}
