package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import hungergames.HGameManager;

public class Set implements CommandExecutor {
	
	public static HashMap<String, ArrayList<String>> headMap = new HashMap<>();
	static {
		headMap.put("steve", new ArrayList<>(Arrays.asList(
				"§6Golden Head", 
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2JiNjEyZWI0OTVlZGUyYzVjYTUxNzhkMmQxZWNmMWNhNWEyNTVkMjVkZmMzYzI1NGJjNDdmNjg0ODc5MWQ4In19fQ==")));
		headMap.put("freddy", new ArrayList<>(Arrays.asList(
				"§6Golden Freddy Head",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJlOTc2Yzk5N2U4ODM0OTIwMzA0OTJiZTJlNTRkMzRkZDM1NjdjYTMyNTdjMzAyYzlkZDU5OTcxNjhjMWMxNCJ9fX0=")));
		headMap.put("gapple", new ArrayList<>(Arrays.asList(
				"§6Golden Cubepple",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTkyZWFhY2QyOTBlYWQzN2ViMWEyMDJhYzczNjdmMzJiZTc0Y2Y0YWM3NzIzZTA2N2M0NjU4YmY2MmMzZGJkNiJ9fX0=")));
		headMap.put("skull", new ArrayList<>(Arrays.asList(
				"§6Golden Skull",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDZlYjE3Yzk0NTQ5ZTdiNDg0ODQxM2ZmNzYxYjBhYTk0NTczYjgwNzc3OGQ1ODA3MGE0OTQ1NzEzZWYzNDhkMyJ9fX0=")));
		headMap.put("helmet", new ArrayList<>(Arrays.asList(
				"§6Golden Helmet",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NlYzI3MTI1NGFjM2JlODQxMzM3M2JhZDdjMjUyYTE3YTU0Mzc4ZDNjODgwNWIwY2RjN2MxMmQ0ODg2N2QxNyJ9fX0=")));
		headMap.put("mushroom", new ArrayList<>(Arrays.asList(
				"§6Gold Mushroom",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGFhNDU3ZTllNzBjZTRhMmQ4YzJhNzk2NzNlN2IzODkwZjY1NGI0NzFiNzlkODBhMmI0ZDFiZDUxNTg0NGU0MCJ9fX0=")));
		headMap.put("jellyfish", new ArrayList<>(Arrays.asList(
				"§6Gold Jellyfish",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWE4NmRhYzllMzA3NDNiNzZiMDk1NTFiNzJiNmNjMjAwZDRmNWU1MGI5Mjc0ZjJhN2M4YmYyNTcwMjhhNjc2ZiJ9fX0=")));
		headMap.put("solid", new ArrayList<>(Arrays.asList(
				"§6#FFAA00",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA5MGQwOWUxNzNlZTM0MTM4YzNiMDFiNDhlZTBiZTUzNGJiYjFhY2UwZGRmNWZmOThlNjZmN2IwMjExMzk5NSJ9fX0=")));
		headMap.put("orb", new ArrayList<>(Arrays.asList(
				"§6Gold Orb",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGE5MDBlYWRjMDBhZmUwNjY2ZTZkMDhiYTVhOTNjNmZkYjc0OTc4ZjMzNGY0OGM4YjcxZjMwNjhkYTJhZWQ4MiJ9fX0=")));
		headMap.put("pufferfish", new ArrayList<>(Arrays.asList(
				"§6Golden Pufferfish",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY4YjA1MzZmNjRiMGJmZmE4Mjg4Njk2ODMzN2YwOWEwMGZhN2UwN2YxNjA0NDc1ZDczODMxNmJiYzRhZThhMiJ9fX0=")));
		headMap.put("mango", new ArrayList<>(Arrays.asList(
				"§dEnchanted Mango",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2IzYjMxN2Y5ZmU2MzJmMGQ3MjU0MWI0MDE3NGM2Mzg4MWE4ZGNmNzNlOGZjMjk5YzI3MjhlYjk2NTM0MzQ3YyJ9fX0=")));
		
	}
	
	
	void changeHeadTexture(String texture) {
		if (headMap.containsKey(texture)) HGameManager.headTexture = headMap.get(texture);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args[0].equals("head")) changeHeadTexture(args[1]);
		return true;
	}
}
