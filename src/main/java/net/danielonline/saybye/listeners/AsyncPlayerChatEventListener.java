package net.danielonline.saybye.listeners;

import net.danielonline.saybye.SayBye;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Array;
import java.util.List;

public class AsyncPlayerChatEventListener implements Listener {

    private BukkitTask task;

    @EventHandler
    public void onEvent(AsyncPlayerChatEvent e) {

        if (e.getMessage().toLowerCase().contains("gtg")
                || e.getMessage().toLowerCase().contains("brb")
                || e.getMessage().toLowerCase().contains("going")
                || e.getMessage().toLowerCase().contains("go")
                || e.getMessage().toLowerCase().contains("off")
                || e.getMessage().toLowerCase().contains("away")
                || e.getMessage().toLowerCase().contains("gn")) {

            if (!SayBye.instance.getBars().containsKey(e.getPlayer().getName())) {

                SayBye.instance.unsafe(e.getPlayer());

            }

        }

        if (e.getMessage().toLowerCase().contains("bye")
                || e.getMessage().toLowerCase().contains("cya")
                || e.getMessage().toLowerCase().contains("adios")
                || e.getMessage().toLowerCase().contains("hei hei")
                || e.getMessage().toLowerCase().contains("moikka")
                || e.getMessage().toLowerCase().contains("bæ")
                || e.getMessage().toLowerCase().contains("hadde")
                || e.getMessage().toLowerCase().contains("adeus")) {

            task = null;

            BossBar bar = Bukkit.getServer().createBossBar(ChatColor.GREEN + "It's safe to log off.", BarColor.GREEN, BarStyle.SOLID, BarFlag.CREATE_FOG);
            bar.setProgress(1);
            bar.addPlayer(e.getPlayer());

            if (task == null) {
                this.task = new BukkitRunnable() {
                    int seconds = 20;

                    @Override
                    public void run() {
                        if ((seconds -= 1) == 0) {
                            task.cancel();
                            bar.removeAll();
                            SayBye.instance.clearBar(e.getPlayer().getName());
                        } else {
                            bar.setProgress(seconds / 20D);
                            SayBye.instance.setBar(e.getPlayer().getName(), bar);
                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);
                        }
                    }
                }.runTaskTimer(SayBye.instance, 0, 20);
            }

        }

    }

}
