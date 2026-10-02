package boring.shit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import commands.*;

public class Main extends JavaPlugin{
	
	private final Map<String, String> nicknames = new HashMap<>();
	
	static Main plugin;
	private Teams teamsobject;
	
	@Override
	public void onEnable() {
		plugin = this;
		loadWorldIfExists("map");
		loadWorldIfExists("bedwars");
		loadWorldIfExists("walls");
		loadWorldIfExists("deathmatch");
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new LeaveListener(), this);
		Bukkit.getPluginManager().registerEvents(new MessageListener(), this);
		Bukkit.getPluginManager().registerEvents(new NoTnt(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new WeatherListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new DropListener(), this);
		Bukkit.getPluginManager().registerEvents(new GrassListener(), this);
		Bukkit.getPluginManager().registerEvents(new ClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new RightClickListener(), this);
		//Bukkit.getPluginManager().registerEvents(new Trampoline(), this);
		this.getCommand("start").setExecutor(new CmdStart());
		this.getCommand("end").setExecutor(new CmdStop());
		this.getCommand("world").setExecutor(new WorldTp());
		this.getCommand("heal").setExecutor(new Heal());
		this.getCommand("feed").setExecutor(new Feed());
		this.getCommand("debug").setExecutor(new Debug());
		this.getCommand("nick").setExecutor(new NickCommand(this));
		this.getCommand("giveitem").setExecutor(new GiveItem());
		this.getCommand("smite").setExecutor(new Smite());
		this.getCommand("invsee").setExecutor(new InvSee());
		teamsobject = new Teams();
		this.getCommand("teams").setExecutor(teamsobject);
		this.getCommand("set").setExecutor(new Set());
		//this.getCommand("end").setExecutor(new CmdStart());
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}

	public static Plugin getPlugin() {
		return plugin;
	}
	
	public Map<String, String> getNicknames() {
        return nicknames;
    }
	
	private void loadWorldIfExists(String name) {
	    File worldFolder = new File(Bukkit.getServer().getWorldContainer(), name);
	    
	    if (worldFolder.exists()) {
	        getLogger().info("Loading world: " + name);
	        new WorldCreator(name).createWorld();
	    } else {
	        getLogger().warning("World folder '" + name + "' not found! Skipping load.");
	    }
	}
	
	public Teams getTeamsObject() {
        return teamsobject;
    }
}
