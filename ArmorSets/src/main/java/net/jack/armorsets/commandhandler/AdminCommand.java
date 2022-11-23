package net.jack.armorsets.commandhandler;

import net.jack.armorsets.itembuilder.Demon;
import net.jack.armorsets.itembuilder.Warrior;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommand implements CommandExecutor {

    Demon demon = new Demon();
    Warrior warrior = new Warrior();

    void usage(Player player) {
        player.sendMessage(CC.translate("&cUsage: &b/adminas give <player> <armorset>"));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (!player.hasPermission("armorsets.admin")) {
            player.sendMessage(CC.translate("&aYou do not have permission to perform this command."));
        }
        if (args.length < 3) {
            usage(player);
            return true;
        } else {
            Player target = Bukkit.getPlayer(args[1]);
            if (target != null) {
                if (args[2].equalsIgnoreCase("demon")) {
                    demon.giveDemon(target);
                    target.sendMessage(CC.translate("&aYou received the &c&lDemon kit&a."));
                    sender.sendMessage(CC.translate("&aYou have given " + target.getDisplayName() + " &athe &c&lDemon kit&a."));
                    return true;
                } else if (args[2].equalsIgnoreCase("warrior")) {
                    warrior.giveWarrior(target);
                    target.sendMessage(CC.translate("&aYou received the &b&lDemon kit&a."));
                    sender.sendMessage(CC.translate("&aYou have given " + target.getDisplayName() + " &athe &b&lWarrior kit&a."));
                    return true;
                }
            }
        }
        return false;
    }
}



