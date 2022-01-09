package net.danielonline.saybye.listeners;

import net.danielonline.saybye.SayBye;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEventListener implements Listener {

    @EventHandler
    public void onEvent(PlayerQuitEvent e) {

        if (!SayBye.instance.getBars().containsKey(e.getPlayer().getName())) {

            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "jail " + e.getPlayer().getName() + " 1m r:Not saying bye");
            System.out.println("Banned");

        }

    }

}
