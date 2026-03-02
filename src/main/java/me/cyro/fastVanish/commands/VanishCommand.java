package me.cyro.fastVanish.commands;

import me.cyro.fastVanish.FastVanish;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("fastvanish.vanish")) {

                if(args.length == 0) {
                    toggleVanishPlayer(p, false, null);

                } else if(args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);
                    if(t == null) {
                        p.sendMessage("§b§lFV §r§f▶ §r§cFailed to vanish player: Player does not exist or is offline.");
                    } else {
                        toggleVanishPlayer(t, true, p);
                    }
                }

            }
        }

        return true;
    }


    public void toggleVanishPlayer(Player p, boolean remote, Player cause) {

        PersistentDataContainer data = p.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(FastVanish.getPlugin(), "isVanish");
        if(!data.has(key, PersistentDataType.BOOLEAN)) {
            data.set(key, PersistentDataType.BOOLEAN, true);
            FastVanish.getPlugin().vanishPlayer(p);
            if(!remote) {
                p.sendMessage("§b§lFV §r§f▶ §r§9Vanish §benabled §9for Player §b" + p.getDisplayName());
            } else {
                p.sendMessage("§b§lFV §r§f▶ §r§9You have been vanished. You are fully invisible to players without permissions.");
                cause.sendMessage("§b§lFV §r§f▶ §r§9Vanish §benabled §9for Player §b" + p.getDisplayName());
            }
            return;
        }



        if(!data.get(key, PersistentDataType.BOOLEAN)) {
            data.set(key, PersistentDataType.BOOLEAN, true);
            FastVanish.getPlugin().vanishPlayer(p);
            if(!remote) {
                p.sendMessage("§b§lFV §r§f▶ §r§9Vanish §benabled §9for Player §b" + p.getDisplayName());
            } else {
                p.sendMessage("§b§lFV §r§f▶ §r§9You have been vanished. You are fully invisible to players without permissions.");
                cause.sendMessage("§b§lFV §r§f▶ §r§9Vanish §benabled §9for Player §b" + p.getDisplayName());
            }
        } else {
            data.set(key, PersistentDataType.BOOLEAN, false);
            FastVanish.getPlugin().unvanishPlayer(p);
            if (!remote) {
                p.sendMessage("§b§lFV §r§f▶ §r§9Vanish §bdisabled §9for Player §b" + p.getDisplayName());
            } else {
                p.sendMessage("§b§lFV §r§f▶ §r§9You have been unvanished. You are now fully visible to all players.");

                cause.sendMessage("§b§lFV §r§f▶ §r§9Vanish §bdisabled §9for Player §b" + p.getDisplayName());
            }
        }

    }
}
