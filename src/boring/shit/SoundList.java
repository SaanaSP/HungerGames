package boring.shit;

import org.bukkit.Sound;

public class SoundList {
	public static void main(String[] args) {
        for (Sound sound : Sound.values()) {
            System.out.println(sound.name());
        }
    }
}
