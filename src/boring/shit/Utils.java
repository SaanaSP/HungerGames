package boring.shit;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Utils {
	
	public static void broadcast(String message) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(message);
		}
	}
	
	public static void sendActionBar(Player player, String message) {
	    CraftPlayer craftPlayer = (CraftPlayer) player;
	    IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}");
	    PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
	    craftPlayer.getHandle().playerConnection.sendPacket(packet);
	}
	
	public static ItemStack getCustomHead(String base64Texture) {
	    ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	    SkullMeta meta = (SkullMeta) head.getItemMeta();

	    // Create a fake GameProfile with a random UUID
	    UUID textureUUID = UUID.nameUUIDFromBytes(base64Texture.getBytes());
	    GameProfile profile = new GameProfile(textureUUID, null);

	    // Add the texture as a "textures" property
	    profile.getProperties().put("textures", new Property("textures", base64Texture));

	    // Inject the profile into the SkullMeta via reflection
	    try {
	        Field profileField = meta.getClass().getDeclaredField("profile");
	        profileField.setAccessible(true);
	        profileField.set(meta, profile);
	    } catch (NoSuchFieldException | IllegalAccessException e) {
	        e.printStackTrace();
	    }

	    head.setItemMeta(meta);
	    return head;
	}
}
