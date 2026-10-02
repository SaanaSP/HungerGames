package other;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ThunderAxe {
	
	public static Integer func(EntityDamageEvent event) {
		if (event instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event;
			if (event2.getDamager() instanceof Player) {
				Player damager = (Player) event2.getDamager();
				ItemStack item = damager.getItemInHand();
				if (item != null && item.hasItemMeta()) {
				    ItemMeta meta = item.getItemMeta();
				    if (meta != null && meta.hasDisplayName()) {
				        String displayName = meta.getDisplayName();
				        if (displayName.equals("§bThunder Axe")) {
				            Player player = (Player) event.getEntity();
				            player.getWorld().strikeLightningEffect(player.getLocation());
				            return 5;
				        }
				    }
				}
			}
		}
		return 0;
	}
}
