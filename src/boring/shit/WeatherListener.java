package boring.shit;

import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.EventHandler;

public class WeatherListener implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(WeatherChangeEvent event) {
    	if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }
}
