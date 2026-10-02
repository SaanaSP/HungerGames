package boring.shit;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import commands.CmdStart;

public class RightClickListener implements Listener {
	
	void useGoldHead(Player player, ItemStack item) {
        if (item.getAmount() - 1 <= 0) {
            player.getInventory().setItemInHand(null);
        } else {
            item.setAmount(item.getAmount() - 1);
        }

        player.setHealth(Math.min(player.getHealth() + 2, player.getMaxHealth()));
        player.setFoodLevel(Math.min(player.getFoodLevel() + 2, 20));
        player.setSaturation(Math.min(player.getSaturation() + 2f, 20f));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1), true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0), true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0), true);
	}
	
	@EventHandler
	void checkHead(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		
		if (item == null || !item.hasItemMeta() || event.getAction() == Action.LEFT_CLICK_AIR) return;
        
		String name = item.getItemMeta().getDisplayName();
		for (ArrayList<String> data : commands.Set.headMap.values()) {
		    if (data.get(0).equals(name)) {
		        useGoldHead(player, item);
		        break;
		    }
		}
	}
	
	@EventHandler
	void leftClickBlock(PlayerAnimationEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		
		if (item == null || !item.hasItemMeta()) return;
        
		String name = item.getItemMeta().getDisplayName();
		for (ArrayList<String> data : commands.Set.headMap.values()) {
		    if (data.get(0).equals(name)) {
		        useGoldHead(player, item);
		        break;
		    }
		}
	}
	
	@EventHandler
	void checkButton(PlayerInteractEvent event) {
		Action action = event.getAction();
		
		if (action != Action.RIGHT_CLICK_BLOCK) return;
		
		Block block = event.getClickedBlock();
    	if (block.getType().name().contains("_BUTTON") && CmdStart.gameRunning.equals("hunger")) event.setCancelled(true);
	}

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        
    }
}
