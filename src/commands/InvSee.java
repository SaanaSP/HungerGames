package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class InvSee implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player admin = (Player) sender;

        if (args.length != 1) {
            admin.sendMessage("Usage: /invsee <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            admin.sendMessage("Player not found or offline.");
            return true;
        }

        // Create a new chest inventory (double chest for full view)
        Inventory view = Bukkit.createInventory(null, 54, target.getName() + "'s Inventory");

        PlayerInventory targetInv = target.getInventory();

        // Copy main inventory (slots 9-35) and hotbar (0-8)
        view.setContents(targetInv.getContents());

        // Copy armor (helmet, chest, legs, boots) into bottom slots for visibility
        view.setItem(45, targetInv.getHelmet());
        view.setItem(46, targetInv.getChestplate());
        view.setItem(47, targetInv.getLeggings());
        view.setItem(48, targetInv.getBoots());

        // In 1.8, no offhand, but you can add extra info if needed

        admin.openInventory(view);
        return true;
    }
}
