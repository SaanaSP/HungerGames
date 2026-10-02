package bedwars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class BGameManager {
	
	static World world = Bukkit.getWorld("bedwars");
	public static String gracePeriod = "team";
	public static List<List<UUID>> teams = new ArrayList<>();

	public static void startGame() {
		gracePeriod = "team";
		teams.clear();
		teams.add(new ArrayList<UUID>());
		teams.add(new ArrayList<UUID>());
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (teams.get(0).size() == teams.get(1).size()) {
				teams.get(0).add(p.getUniqueId());
			} else {
				teams.get(1).add(p.getUniqueId());
			}
		}
	}
}
