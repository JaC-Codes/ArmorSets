package net.jack.armorsets.subcommands;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.commandmanager.CommandManager;
import net.jack.armorsets.timer.Timer;
import net.jack.armorsets.timer.TimerType;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DoubleXpCommand implements CommandExecutor {

    private final ArmorSets armorSets;
    private final CommandManager manager;
    private final Timer timer;

    public DoubleXpCommand(ArmorSets armorSets) {
        this.armorSets = armorSets;
        this.timer = armorSets.getTimers();
        this.manager = armorSets.getManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("armorsets.admin")) {
            player.sendMessage(CC.translate("&aYou do not have permission to perform this command."));
            return true;
        }
        if (args.length < 3) {
            manager.usage(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("doublexp")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) return false;
            if (args[2].equalsIgnoreCase("remove") && (timer.checkDoublexp(player.getUniqueId()))) {
                timer.clearTimer(player.getUniqueId());
                player.sendTitle(CC.translate("&a&lArmorEvents"), CC.translate("&b&lMythic Rune &a&lDe-Activated"),
                        30, 150, 30);
                return true;
            } else if (args[2].equalsIgnoreCase("add") && (!timer.checkDoublexp(player.getUniqueId()))) {
                player.sendTitle(CC.translate("&a&lArmorEvents"), CC.translate("&b&lMythic Rune &a&lActivated"),
                        30, 150, 30);
                timer.setDoublexp(player.getUniqueId(), System.currentTimeMillis() + (this.armorSets.getConfig().getInt("Timer.double_xp.time")) * 1000L);
                return true;
            }
        }
        return false;
    }
}




