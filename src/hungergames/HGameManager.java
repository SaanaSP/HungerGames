package hungergames;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import boring.shit.Main;
import boring.shit.Utils;
import commands.CmdStart;
import net.md_5.bungee.api.ChatColor;

import data.RunnableIntPair;

public class HGameManager {
	
	private static World world = Bukkit.getWorld("map");
	private static World lobby = Bukkit.getWorld("lobby");
	public static ArrayList<RunnableIntPair> delay = new ArrayList<>();
	public static ArrayList<Player> activeplayers = new ArrayList<Player>();
	public static HashMap<UUID, Integer> kills = new HashMap<>();
	public static boolean gracePeriod = false;
	public static Point2D zoneCentre = new Point2D.Double(1000.5, 1000.5);
	private static float borderx = 1000.5F;
	private static float borderz = 1000.5F;
	public static ArrayList<String> headTexture = new ArrayList<>();
	public static String gameMode = "normal";
	static {
		headTexture.add("§6Golden Head"); headTexture.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2JiNjEyZWI0OTVlZGUyYzVjYTUxNzhkMmQxZWNmMWNhNWEyNTVkMjVkZmMzYzI1NGJjNDdmNjg0ODc5MWQ4In19fQ==");
	}
	
	/*public static class RunnableIntPair {
	    public BukkitRunnable runnable;
	    public long number;
	    public BukkitTask task; // add this

	    public RunnableIntPair(BukkitRunnable runnable, long number) {
	        this.runnable = runnable;
	        this.number = number;
	    }
	}*/
	
	@SuppressWarnings("deprecation")
	public static void startGame(String mode) {
		gameMode = mode;
		gracePeriod = true;
		WorldBorder border = Bukkit.getWorld("map").getWorldBorder();
		kills.clear();
		
		Random rand = new Random();
		//int x = rand.nextInt(241) - 120;
		
		borderx = 1000.5F;
		borderz = 1000.5F;
		
		border.setSize(320);
		border.setCenter(1000.5, 1000.5);
		border.setDamageAmount(0);
		border.setDamageBuffer(200);
		
		world.getEntities().stream().filter(Item.class::isInstance).forEach(Entity::remove);
		ArrayList<Location> goldblocks = new ArrayList<>(Arrays.asList(
		    new Location(world, 1000, 44, 990),
		    new Location(world, 993, 44, 993),
		    new Location(world, 990, 44, 1000),
		    new Location(world, 993, 44, 1007),
		    new Location(world, 1000, 44, 1010),
		    new Location(world, 1007, 44, 1007),
		    new Location(world, 1010, 44, 1000),
		    new Location(world, 1007, 44, 993)
		));
		for (Location loc : goldblocks) {
		    loc.getBlock().setType(Material.GOLD_BLOCK);
		}
		
		activeplayers.clear();
		for (Player p : Bukkit.getOnlinePlayers()) {
			activeplayers.add(p);
			p.getInventory().clear();
			if (gameMode.equals("normal")) {
				p.setMaxHealth(20);
				p.setHealth(20);
			} else if (gameMode.equals("mayhem")) {
				p.setMaxHealth(40);
				p.setHealth(40);
			}
			p.setFoodLevel(20);
			p.setSaturation(5);
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);
			p.setGameMode(GameMode.ADVENTURE);
			for (PotionEffect effect : p.getActivePotionEffects()) {
			    p.removePotionEffect(effect.getType());
			}
			kills.put(p.getUniqueId(), 0);
			
		}
		ArrayList<Location> spawns = new ArrayList<>(Arrays.asList(
		    new Location(world, 1000.5, 45, 990.5, 0, 0),
		    new Location(world, 993.5, 45, 993.5, -45, 0),
		    new Location(world, 990.5, 45, 1000.5, -90, 0),
		    new Location(world, 993.5, 45, 1007.5, -135, 0),
		    new Location(world, 1000.5, 45, 1010.5, 180, 0),
		    new Location(world, 1007.5, 45, 1007.5, 135, 0),
		    new Location(world, 1010.5, 45, 1000.5, 90, 0),
		    new Location(world, 1007.5, 45, 993.5, 90, 0)
		));
		for (Player p : activeplayers) {
			p.teleport(spawns.get(activeplayers.indexOf(p)));
		}
		FillChests.fillChests();
		
		for (Player p : activeplayers) {
			p.sendTitle(ChatColor.GREEN + "5", "");
			p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 2.0f);
		}

		delay.clear();
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendTitle(ChatColor.GREEN + "4", "");
					p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 2.0f);
				}
			}
		}, 20L));
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendTitle(ChatColor.YELLOW + "3", "");
					p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 2.0f);
				}
			}
		}, 40L));
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendTitle(ChatColor.YELLOW + "2", "");
					p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 2.0f);
				}
			}
		}, 60L));
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendTitle(ChatColor.RED + "1", "");
					p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 2.0f);
				}
			}
		}, 80L));
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Location i : goldblocks) {
					i.getBlock().setType(Material.AIR);
				}
				for (Player p : activeplayers) {
					p.sendTitle("", ""); 
				}
				for (Player p : activeplayers) {
				}
			}
		}, 100L));
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cGrace period ending in 10 seconds"); 
				}
			}
		}, 900L));
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				gracePeriod = false;
				for (Player p : activeplayers) {
					p.sendMessage("§cGrace period is now over"); 
				}
			}
		}, 1200L));
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cFirst zone activating in 20 seconds."); 
				}
			}
		}, 2400L));
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cFirst zone active, damaging in 10 seconds."); 
				}
				int offset = rand.nextInt(320 - 150 + 1);
				borderx = borderx - 320/2 + 75 + offset;
				offset = rand.nextInt(171);
				borderz = borderz - 320/2 + 75 + offset;
				border.setCenter(borderx + 0.5, borderz + 0.5);
				border.setSize(150.0);
			}
		}, 2800L));
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cZone damaging."); 
				}
				border.setDamageBuffer(0);
				border.setDamageAmount(0);
			}
		}, 3000L));
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cSecond zone activating in 20 seconds."); 
				}
			}
		}, 4200L));
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cSecond zone active, damaging in 30 seconds."); 
				}
				int offset = rand.nextInt(150 - 50 + 1);
				borderx = borderx - 150/2 + 25 + offset;
				offset = rand.nextInt(150 - 50 + 1);
				borderz = borderz - 150/2 + 25 + offset;
				border.setCenter(borderx, borderz);
				border.setDamageBuffer(300.0);
				border.setSize(50.0);
			}
		}, 4600L));
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cZone damaging."); 
				}
				border.setDamageBuffer(0);
				border.setDamageAmount(0);
			}
		}, 5200L));
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cLightning will start in 10 seconds."); 
				}
			}
		}, 6400L));
		
		delay.add(new RunnableIntPair(new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : activeplayers) {
					p.sendMessage("§cLightning has begun."); 
				}
				BukkitTask timerTask = new BukkitRunnable() {
		            @Override
		            public void run() {
		            	Player target = activeplayers.get(rand.nextInt(activeplayers.size()));
		            	target.getWorld().strikeLightningEffect(target.getLocation());
		            	EntityDamageEvent damageEvent = new EntityDamageEvent(target, EntityDamageEvent.DamageCause.CUSTOM, 2);
		            	Bukkit.getPluginManager().callEvent(damageEvent);
		            	target.damage(2, null);
		            }
		        }.runTaskTimer(Main.getPlugin(), 0L, 40L);

		        RunnableIntPair timerPair = new RunnableIntPair(null, 0L);
		        timerPair.task = timerTask;
		        delay.add(timerPair);
			}
		}, 6600L));
		
		for (RunnableIntPair func : delay) {
			func.task = func.runnable.runTaskLater(Main.getPlugin(), func.number);
		}
		
	
	}
	
	public static void softStop() {
		CmdStart.gameRunning = "none";
		gracePeriod = false;
		for (RunnableIntPair func : delay) {
            if (func.task != null) func.task.cancel();
        }
		activeplayers.clear();
		delay.clear();
	}
	
	public static void endGame() {
		softStop();
        for (Player p : activeplayers) {
			p.sendMessage("§cGame Stopped"); 
		}
	}

	public static void win(Player winner) {
		winner.sendTitle("§6§lGGS§r", "");
		winner.playSound(winner.getLocation(), Sound.LEVEL_UP, 1.0f, 2.0f);
		UUID topKiller = null;
		int topKills = -1;
		for (HashMap.Entry<UUID, Integer> entry : kills.entrySet()) {
			if (entry.getValue() > topKills) {
				topKills = entry.getValue();
				topKiller = entry.getKey();
			}1
		}
		final UUID finalTopKiller = topKiller;
		final int finalTopKills = topKills;
		softStop();
		new BukkitRunnable() {
			@Override
			public void run() {
				Utils.broadcast("§b---------------Game Stats---------------");
				Utils.broadcast(" ");
				Utils.broadcast("§eWinner:§a " + winner.getName());
				Utils.broadcast("§eMost Kills:§a " + Bukkit.getPlayer(finalTopKiller).getName() + "§7 (" + finalTopKills + " Kills)");
			}
		}.runTaskLater(Main.getPlugin(), 20L);
		new BukkitRunnable() {
			@Override
			public void run() {
				ArrayList<Player> toTeleport = new ArrayList<>(world.getPlayers());
				Location position = new Location(lobby, 0.5, 40, 0.5);
				for (Player target : toTeleport) {
					target.teleport(position);
					target.setGameMode(GameMode.ADVENTURE);
					target.getInventory().clear();
					target.getInventory().setHelmet(null);
					target.getInventory().setChestplate(null);
					target.getInventory().setLeggings(null);
					target.getInventory().setBoots(null);
					for (PotionEffect effect : target.getActivePotionEffects()) {
					    target.removePotionEffect(effect.getType());
					}
				}
			}
		}.runTaskLater(Main.getPlugin(), 100L);
	}
}
