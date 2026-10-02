package boring.shit;

//import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class DropListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();
		//Player player = (Player) event.getWhoClicked();
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§fYour Stats")) {
				event.setCancelled(true);
			}
		}
	}
}
