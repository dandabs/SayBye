package net.danielonline.saybye;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import net.danielonline.saybye.listeners.AsyncPlayerChatEventListener;
import net.danielonline.saybye.listeners.PlayerQuitEventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class SayBye extends JavaPlugin {

    public static SayBye instance;
    public HashMap<String, BossBar> bars = new HashMap<String, BossBar>();
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        instance = this;

        protocolManager = ProtocolLibrary.getProtocolManager();

        getServer().getPluginManager().registerEvents(new AsyncPlayerChatEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitEventListener(), this);

        protocolManager.addPacketListener(
                new PacketAdapter(this, ListenerPriority.NORMAL,
                        PacketType.Login.Server.DISCONNECT) {
                    @Override
                    public void onPacketSending(PacketEvent event) {

                        if (event.getPacketType() ==
                                PacketType.Login.Server.DISCONNECT) {



                        }
                    }
                });
    }

    public HashMap<String, BossBar> getBars() {

        return bars;

    }

    public void setBar(String user, BossBar bar) {

        if (bars.containsKey(user)) {

            bars.replace(user, bar);

        } else bars.put(user, bar);

    }

    public void clearBar(String user) {

        if (bars.containsKey(user)) bars.remove(user);

    }

    private BukkitTask task;

    public void unsafe(Player p) {

        //p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "It's not safe to log off. Say 'bye' in chat to log off safely.");
        p.getPlayer().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10, 29);

    }
}
