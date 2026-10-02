package boring.shit;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (player.getUniqueId().toString().equals("ae53978f-2fe9-466d-891d-fdef9008d409")) {
            event.setQuitMessage(ChatColor.RED + "  " + ChatColor.MAGIC + "||" + ChatColor.YELLOW + "  :3  " + ChatColor.RED + "" + ChatColor.MAGIC + "||");
        } else {
            event.setQuitMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "-" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + player.getName());
        }
    }
}
