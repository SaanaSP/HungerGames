package hungergames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FillChests {
	
	static World world = Bukkit.getWorld("map");	
	
	static ArrayList<ItemStack> weapons = new ArrayList<>();
	static ArrayList<Material> food = new ArrayList<>();
	static ArrayList<ItemStack> armour = new ArrayList<ItemStack>();
	static {
		weapons.add(new ItemStack(Material.WOOD_SWORD));
		weapons.add(new ItemStack(Material.STONE_SWORD));
		weapons.add(new ItemStack(Material.IRON_SWORD));
		weapons.add(new ItemStack(Material.BOW));
		weapons.add(new ItemStack(Material.ARROW, 16));
		weapons.add(new ItemStack(Material.FISHING_ROD));
		
		food.add(Material.COOKED_FISH);
		food.add(Material.COOKED_FISH);
		food.add(Material.GRILLED_PORK);
		food.add(Material.COOKED_BEEF);
		food.add(Material.COOKED_CHICKEN);
		food.add(Material.BAKED_POTATO);
		food.add(Material.PUMPKIN_PIE);
		food.add(Material.COOKED_MUTTON);
		food.add(Material.MELON);
		
		armour.add(new ItemStack(Material.LEATHER_HELMET)); armour.add(new ItemStack(Material.LEATHER_CHESTPLATE)); armour.add(new ItemStack(Material.LEATHER_LEGGINGS)); armour.add(new ItemStack(Material.LEATHER_BOOTS));
		armour.add(new ItemStack(Material.CHAINMAIL_HELMET)); armour.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE)); armour.add(new ItemStack(Material.CHAINMAIL_LEGGINGS)); armour.add(new ItemStack(Material.CHAINMAIL_BOOTS));
		armour.add(new ItemStack(Material.IRON_HELMET)); armour.add(new ItemStack(Material.IRON_CHESTPLATE)); armour.add(new ItemStack(Material.IRON_LEGGINGS)); armour.add(new ItemStack(Material.IRON_BOOTS));
		armour.add(new ItemStack(Material.GOLD_HELMET)); armour.add(new ItemStack(Material.GOLD_CHESTPLATE)); armour.add(new ItemStack(Material.GOLD_LEGGINGS)); armour.add(new ItemStack(Material.GOLD_BOOTS));
		armour.add(new ItemStack(Material.DIAMOND_HELMET)); armour.add(new ItemStack(Material.DIAMOND_CHESTPLATE)); armour.add(new ItemStack(Material.DIAMOND_LEGGINGS)); armour.add(new ItemStack(Material.DIAMOND_BOOTS));
	}
	static int[][] coords = {
		    {1000, 41, 999},
		    {1001, 41, 1000},
		    {1000, 41, 1001},
		    {999, 41, 1000},
		    {1016, 42, 986},
		    {1039, 40, 996},
		    {1063, 44, 981},
		    {1056, 44, 992},
		    {1045, 53, 1033},
		    {1046, 53, 1038},
		    {1093, 45, 1043},
		    {1093, 45, 1059},
		    {1119, 45, 1049},
		    {1107, 45, 1028},
		    {1106, 51, 998},
		    {1101, 63, 996},
		    {1106, 69, 991},
		    {1123, 61, 996},
		    {1124, 49, 998},
		    {1070, 53, 1000},
		    {1024, 48, 968},
		    {958, 47, 974},
		    {976, 46, 964},
		    {969, 47, 964},
		    {959, 48, 994},
		    {936, 41, 962},
		    {885, 35, 964},
		    {1004, 48, 1037},
		    {995, 67, 1066},
		    {995, 74, 1075},
		    {901, 51, 1090},
		    {918, 51, 1102},
		    {916, 54, 1105},
		    {1019, 48, 922},
		    {1006, 47, 918},
		    {1007, 50, 916},
		    {1078, 48, 876},
		    {1078, 48, 874},
		    {1083, 48, 872},
		    {1051, 50, 904},
		    {922, 52, 1164},
		    {863, 46, 1048},
		    {1132, 45, 1033},
		    {960, 41, 1005},
		    {920, 35, 916},
		    {994, 46, 857},
		    {1018, 55, 914},
		    {887, 77, 915},
		    {889, 41, 905},
		    {960, 66, 1074},
		    {885, 45, 915},
		    {886, 65, 913},
		    {1000, 40, 998},
		    {1002, 40, 1000},
		    {1000, 40, 1002},
		    {998, 40, 1000},
		    {1070, 55, 955},
		    {1100, 56, 939},
		    {1108, 47, 958},
		    {885, 77, 908},
		    {880, 42, 912}
		};
	static ArrayList<Location> chests = new ArrayList<>();
	
	static {
		for (int[] coord : coords) {
		    chests.add(new Location(world, coord[0], coord[1], coord[2]));
		}
	}

	public static void fillChests() {	
		
		
		
		/*food.add(new ItemStack(Material.COOKED_FISH, amount));
		food.add(new ItemStack(Material.COOKED_FISH, amount, (byte) 1));
		food.add(new ItemStack(Material.GRILLED_PORK, amount));
		food.add(new ItemStack(Material.COOKED_BEEF, amount));
		food.add(new ItemStack(Material.COOKED_CHICKEN, amount));
		food.add(new ItemStack(Material.BAKED_POTATO, amount));
		food.add(new ItemStack(Material.PUMPKIN_PIE, amount));
		food.add(new ItemStack(Material.COOKED_MUTTON, amount));
		food.add(new ItemStack(Material.MELON, amount));*/
		

		
		for (Location j : chests) {
			Block block = j.getBlock();
			BlockState state = block.getState();
			if (!(state instanceof Chest)) {
			    System.out.println("Skipping non-chest block at " + j + " | Type: " + block.getType());
			    continue;
			}
			Chest chest = (Chest) state;
			Inventory inv = chest.getBlockInventory();
			chest.getInventory().clear();
			Random random = new Random();
			int number;
			ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
			
			switch (HGameManager.gameMode) {
			case "normal":
				number = random.nextInt(7);
				if (number != 6 && HGameManager.gameMode.equals("normal")) {
					loot.add(weapons.get(number));
				} else {
					number = random.nextInt(6);
					if (number == 5) {
						ItemStack enchiron = new ItemStack(Material.IRON_SWORD);
						enchiron.addEnchantment(Enchantment.DAMAGE_ALL, 1);
						loot.add(enchiron);
					} else if (number > 2 && number < 5) {
						loot.add(new ItemStack(Material.DIAMOND_SWORD));
					} else {
						loot.add(new ItemStack(Material.SNOW_BALL, 16));
					}
				}
				break;
			case "mayhem":
				number = random.nextInt(6);
				switch (number) {
				case 0:
					loot.add(new ItemStack(Material.SNOW_BALL, 16));
					break;
				case 1:
					loot.add(commands.GiveItem.buildItem("axe"));
					break;
				case 2:
					ItemStack enchiron = new ItemStack(Material.IRON_SWORD);
					enchiron.addEnchantment(Enchantment.DAMAGE_ALL, 1);
					loot.add(enchiron);
					break;
				case 3:
					ItemStack kbbow = new ItemStack(Material.BOW);
					kbbow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
					loot.add(kbbow);
					break;
				case 4:
					loot.add(new ItemStack(Material.DIAMOND_SWORD));
					break;
				case 5:
					loot.add(new ItemStack(Material.ARROW, 16));
					break;
				}
			}
			number = random.nextInt(10);
			
			int amount = random.nextInt(3) + 1;
			if (number != 9) {
				if (number == 1) {
					loot.add(new ItemStack(food.get(number), amount, (byte) 1));
				} else {
					loot.add(new ItemStack(food.get(number), amount));
				}
			} else {
				number = random.nextInt(6);
				if (number == 5) {
					loot.add(new ItemStack(Material.GOLDEN_APPLE, 1, (byte) 1));
				} else if (number > 2 && number < 5) {
					loot.add(new ItemStack(Material.GOLDEN_APPLE));
				} else {
					loot.add(new ItemStack(Material.EGG, 16));
				}
			}
			number = random.nextInt(16);
			loot.add(armour.get(number));
			
			if (HGameManager.gameMode.equals("mayhem")) {
				ItemStack heads = HDeathListener.createGoldenHead(HGameManager.headTexture);
				heads.setAmount(random.nextInt(4) + 1);
				loot.set(1, heads);
				number = random.nextInt(4) + 16;
				loot.set(2, armour.get(number));
			}
			
			ArrayList<Integer> slots = new ArrayList<>();
			for (int i = 1; i <= 26; i++) slots.add(i);
			Collections.shuffle(slots);

			inv.setItem(slots.get(0), loot.get(0));
			inv.setItem(slots.get(1), loot.get(1));
			inv.setItem(slots.get(2), loot.get(2));
		}
	}
}
