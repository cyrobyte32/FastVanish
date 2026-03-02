package me.cyro.fastVanish;

import me.cyro.fastVanish.commands.VanishCommand;
import me.cyro.fastVanish.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class FastVanish extends JavaPlugin {


    private static FastVanish plugin;
    public static FastVanish getPlugin() {
        return plugin;
    }



    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("vanish").setExecutor(new VanishCommand());
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        plugin = this;

    }

    public void vanishPlayer(Player p) {
        // p = player to vanish.
        // permission fastvanish.see allows to bypass invisibility.
        for(Player t : Bukkit.getOnlinePlayers()) {
            if(!t.hasPermission("fastvanish.see")) {
                t.hidePlayer(this, p);
            }
        }
    }


    public void unvanishPlayer(Player p) {
        // p = player to vanish.
        // permission fastvanish.see allows to bypass invisibility.
        for(Player t : Bukkit.getOnlinePlayers()) {
            t.showPlayer(this, p);
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
