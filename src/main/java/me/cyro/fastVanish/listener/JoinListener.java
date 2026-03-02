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

        if(!t.hasPermission("vanish.see")) {

            // List of vanished players
            List<Player> vanishedPlayers = new ArrayList<>();

            for(Player p : Bukkit.getOnlinePlayers()) {
                if(isVanish(p)) {
                    t.hidePlayer(FastVanish.getPlugin(), p);
                }
            }



        }

    }

    private boolean isVanish(Player p) {
        return p.getPersistentDataContainer().getOrDefault(
                new NamespacedKey(FastVanish.getPlugin(), "isVanish"),
                PersistentDataType.BOOLEAN,
                false
        );
    }
}
