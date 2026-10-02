package boring.shit;

import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NoTnt implements Listener {
	
	@EventHandler
	public void onBlockExplode(EntityExplodeEvent e) {
	    if (!(e.getEntity() instanceof TNTPrimed)) return;
	    e.setCancelled(true);
	}
}
