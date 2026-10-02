package boring.shit;

//import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onClick(PlayerDropItemEvent event) {
		ItemStack item = event.getItemDrop().getItemStack();
		//Player player = (Player) event.getWhoClicked();
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§fYour Stats")) {
				event.setCancelled(true);
			}
		}
	}
}
