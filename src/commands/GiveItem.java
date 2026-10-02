package commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import hungergames.HDeathListener;
import hungergames.HGameManager;

public class GiveItem implements CommandExecutor {
	
	public static final Map<String, Map<String, Object>> items = new HashMap<>();
	
	static {
		//Consumable
        items.put("head", new HashMap<String, Object>() {{
            put("type", "head");
        }});
        //Fun
        items.put("axe", new HashMap<String, Object>() {{
            put("type", new ItemStack(Material.IRON_AXE, 1));
            put("name", "§bThunder Axe");
            put("lore", Arrays.asList("§7Smites enemies."));
        }});
        items.put("dagger", new HashMap<String, Object>() {{
            put("type", new ItemStack(Material.IRON_SWORD, 1));
            put("name", "§bAssassin Dagger");
            put("lore", Arrays.asList("§7Right click to teleport behind the nearest player.", "Halves your health."));
        }});
        items.put("scythe", new HashMap<String, Object>() {{
            put("type", new ItemStack(Material.DIAMOND_HOE, 1));
            put("name", "§bScythe");
            put("lore", Arrays.asList("§7Heal when you deal damage."));
        }});
    }
	
	public static ItemStack buildItem(String key) {
        Map<String, Object> data = items.get(key);
        if (data == null) return null;
        
        ItemStack item = null;
        if (data.get("type").equals("head")) {
        	item = HDeathListener.createGoldenHead(HGameManager.headTexture);
        } else {
        	item = (ItemStack) data.get("type");
        }
        ItemMeta meta = item.getItemMeta();
        
        if (data.containsKey("name")) meta.setDisplayName((String) data.get("name"));
        meta.setLore((List<String>) data.get("lore"));
        item.setItemMeta(meta);

        return item;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player player = (Player) sender;
		
		ItemStack item = buildItem(args[0]);
		
		player.getInventory().addItem(item);
        return true;
	}
}
