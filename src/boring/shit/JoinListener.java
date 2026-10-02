package boring.shit;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getUniqueId().toString().equals("ae53978f-2fe9-466d-891d-fdef9008d409")) {
        	event.setJoinMessage(ChatColor.AQUA + "  " + ChatColor.MAGIC + "||" + ChatColor.YELLOW + "  :3  " + ChatColor.AQUA + "" + ChatColor.MAGIC + "||");
        }
        else {
        	event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + player.getName());
        }
    }
}