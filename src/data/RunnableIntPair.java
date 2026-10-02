package data;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class RunnableIntPair {
    public BukkitRunnable runnable;
    public long number;
    public BukkitTask task; // add this

    public RunnableIntPair(BukkitRunnable runnable, long number) {
        this.runnable = runnable;
        this.number = number;
    }
}