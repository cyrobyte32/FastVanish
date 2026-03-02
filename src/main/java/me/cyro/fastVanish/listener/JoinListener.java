package me.cyro.fastVanish.listener;

import me.cyro.fastVanish.FastVanish;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

import javax.xml.stream.events.Namespace;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player t = e.getPlayer();

        // Hide all players that are in vanish and they cant see
        if(!t.hasPermission("fastvanish.see")) {
            for(Player p : Bukkit.getOnlinePlayers()) {

                if(isVanish(p)) {
                    t.hidePlayer(FastVanish.getPlugin(), p);
                }

            }
        }

        // Hide them if THEY are in vanish
        if(isVanish(t)) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(!p.hasPermission("fastvanish.see")) {
                    p.hidePlayer(FastVanish.getPlugin(), t);
                }
            }
        }

    }

    private boolean isVanish(Player p) {
        return p.getPersistentDataContainer().getOrDefault(
                new NamespacedKey(
                        FastVanish.getPlugin(),
                        "isVanish"),
                PersistentDataType.BOOLEAN,
                false
        );
    }
}
