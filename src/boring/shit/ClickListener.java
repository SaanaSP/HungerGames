package boring.shit;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bedwars.BGameManager;

public class ClickListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getInventory() == null) return;
		String invname = event.getInventory().getName();
		if (invname.equals("Team Editor")) {
	        event.setCancelled(true);
	        ItemStack item = event.getCurrentItem();
	        if (item == null || item.getType() != Material.SKULL_ITEM) return;
	        if (item.getDurability() != 3) return;
	        ItemMeta meta = item.getItemMeta();
	        if (meta == null || !meta.hasDisplayName()) return;
	        String username = ChatColor.stripColor(meta.getDisplayName());
	        OfflinePlayer player = Bukkit.getOfflinePlayer(username);
	        UUID uuid = player.getUniqueId();
	        List<UUID> team1 = BGameManager.teams.get(0);
	        List<UUID> team2 = BGameManager.teams.get(1);
	        if (team1.remove(uuid)) {
	            team2.add(uuid);
	        } else if (team2.remove(uuid)) {
	            team1.add(uuid);
	        }
	        Main plugin = Main.getPlugin(Main.class);
	        plugin.getTeamsObject().buildgui();
	    }
	}
}
