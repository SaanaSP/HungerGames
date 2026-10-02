package boring.shit;

//import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageListener implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        //Player player = event.getPlayer();
        event.setFormat("§7%1$s" + ": §f%2$s");
    }
}