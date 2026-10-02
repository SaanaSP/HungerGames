package boring.shit;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;

public class GrassListener implements Listener{
	
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent event) {
	    if (event.getSource().getType() == Material.GRASS) {
	        event.setCancelled(true);
	    }
	}
}
