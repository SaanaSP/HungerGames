package boring.shit;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import commands.CmdStart;
import hungergames.HDeathListener;
public class DeathListener implements Listener {

	@EventHandler
	public void onDeath(EntityDamageEvent event) {
		if (CmdStart.gameRunning.equals("hunger")) {
			HDeathListener.func(event);
		} else if (CmdStart.gameRunning.equals("none")) {
			event.setCancelled(true);
		}
		
		
	}

}