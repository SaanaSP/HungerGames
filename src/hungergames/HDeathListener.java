package hungergames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import boring.shit.Main;
import boring.shit.Utils;
import commands.CmdStart;
import commands.GiveItem;

public class HDeathListener {
	
	public static World world = Bukkit.getWorld("map");
	
	public static ItemStack createGoldenHead(ArrayList<String> data) {
		ItemStack head = Utils.getCustomHead(data.get(1));
		ItemMeta meta = head.getItemMeta();
		meta.setDisplayName(data.get(0));
		head.setItemMeta(meta);
		return head;
	}

	@SuppressWarnings("deprecation")
	public static void func(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;

		if (HGameManager.gracePeriod && CmdStart.gameRunning.equals("hunger")) {
			event.setCancelled(true);
			return;
		}

		Player player = (Player) event.getEntity();
		double finalDamage = event.getFinalDamage();
		finalDamage += other.ThunderAxe.func(event);
		

		if (player.getHealth() - finalDamage <= 0) {
			event.setCancelled(true);
			player.setGameMode(GameMode.SPECTATOR);
			Location location = player.getLocation();
			for (ItemStack item : player.getInventory().getContents()) {
		        if (item != null) {
		            world.dropItemNaturally(location, item);
		        }
		    }

			String deathMessage = "§c" + player.getName() + "§e has died";

			if (event instanceof EntityDamageByEntityEvent) {
				EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event;
				Entity damager = event2.getDamager();

				if (damager instanceof Player) {
					Player killer = (Player) damager;
					killer.playSound(killer.getLocation(), Sound.DIG_STONE, 1.0f, 2.0f);
					//killer.setHealth(Math.min(killer.getHealth() + 6, killer.getMaxHealth()));
					ItemStack goldenHead = createGoldenHead(HGameManager.headTexture);
					if (HGameManager.gameMode.equals("mayhem")) goldenHead.setAmount(16);
					world.dropItemNaturally(location, goldenHead);
					deathMessage = "§c" + killer.getName() + "§e has killed §c" + player.getName();
					UUID killerUUID = killer.getUniqueId();
					HGameManager.kills.put(killerUUID, HGameManager.kills.getOrDefault(killerUUID, 0) + 1);
				} else if (damager instanceof Arrow) {
					Arrow arrow = (Arrow) damager;
					if (arrow.getShooter() instanceof Player) {
						Player killer = (Player) arrow.getShooter();
						killer.playSound(killer.getLocation(), Sound.DIG_STONE, 1.0f, 2.0f);
						ItemStack goldenHead = createGoldenHead(HGameManager.headTexture);
						if (HGameManager.gameMode.equals("mayhem")) goldenHead.setAmount(16);
						world.dropItemNaturally(location, goldenHead);
						//killer.setHealth(Math.min(killer.getHealth() + 6, killer.getMaxHealth()));
						deathMessage = "§c" + killer.getName() + "§e sniped §c" + player.getName();
						UUID killerUUID = killer.getUniqueId();
						HGameManager.kills.put(killerUUID, HGameManager.kills.getOrDefault(killerUUID, 0) + 1);
					}
				}
			} else {
				switch (event.getCause()) {
				case VOID:
					deathMessage = "§c" + player.getName() + "§e yeeted themself into the void";
					break;
				case FALL:
					deathMessage = "§c" + player.getName() + "§e forgot how gravity works";
					break;
				case LAVA:
					deathMessage = "§c" + player.getName() + "§e went for a swim in lava";
					break;
				case FIRE:
				case FIRE_TICK:
					deathMessage = "§c" + player.getName() + "§e was roasted alive";
					break;
				case DROWNING:
					deathMessage = "§c" + player.getName() + "§e forgot to breathe";
					break;
				case SUFFOCATION:
					deathMessage = "§c" + player.getName() + "§e got stuck in a wall";
					break;
				case MAGIC:
					deathMessage = "§c" + player.getName() + "§e was hexed to death";
					break;
				case POISON:
					deathMessage = "§c" + player.getName() + "§e felt a little too green";
					break;
				case ENTITY_EXPLOSION:
				case BLOCK_EXPLOSION:
					deathMessage = "§c" + player.getName() + "§e exploded into confetti";
					break;
				default:
					deathMessage = "§c" + player.getName() + "§e met an untimely end";
					break;
				}
			}

			Utils.broadcast(deathMessage);

			if (CmdStart.gameRunning.equals("hunger")) {
				HGameManager.activeplayers.remove(player);

				if (HGameManager.activeplayers.size() == 1) {
					Player winner = HGameManager.activeplayers.get(0);
					HGameManager.win(winner);
				}
			}
		}
	}
}
