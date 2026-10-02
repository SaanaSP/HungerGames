package commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//import org.bukkit.scheduler.BukkitTask;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import bedwars.BGameManager;

public class Teams implements CommandExecutor {
	
	static Inventory gui = Bukkit.createInventory(
	        null,
	        18,
	        "Team Editor"
	);
	
	public static void buildgui() {
		gui.clear();
		for (int j = 0; j < 2; j++) {
		    int i = 0;
		    for (UUID id : BGameManager.teams.get(j)) {
		        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		        SkullMeta meta = (SkullMeta) head.getItemMeta();
		        String name = Bukkit.getOfflinePlayer(id).getName();
		        if (name == null) name = "Unknown";
		        meta.setDisplayName((j == 0 ? "§9" : "§c") + name);
		        meta.setOwner(name);
		        head.setItemMeta(meta);
		        gui.setItem(i + j * 9, head);
		        i++;
		    }
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player && BGameManager.gracePeriod.equals("teams")){
			Player player = (Player) sender;
			buildgui();
			player.openInventory(gui);
		}
		return true;
	}
}
