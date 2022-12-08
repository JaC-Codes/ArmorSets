package net.jack.armorsets.subcommands;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.commandmanager.CommandManager;
import net.jack.armorsets.commandmanager.SubCommand;
import net.jack.armorsets.itembuilder.MythicRune;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RunesCommand extends SubCommand {

    private final ArmorSets armorSets;
    private final CommandManager manager;
    private final MythicRune rune;

    public RunesCommand(ArmorSets armorSets) {
        this.armorSets = armorSets;
        this.manager = armorSets.getManager();
        this.rune = new MythicRune(armorSets);
    }

    @Override
    public String getName() {
        return "rune";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (!player.hasPermission("armorsets.admin")) {
            player.sendMessage(CC.translate("&aYou do not have permission to perform this command."));
        }
        if (args.length < 4) {
            manager.usage(player);
            return;
        }
        if (args[1].equalsIgnoreCase("give")) {
            Player target = Bukkit.getPlayer(args[2]);
            if (target != null) {
                if (args[3].equalsIgnoreCase("mythic")) {
                    if (args.length == 4) {
                        manager.usage(player);
                    } else {
                        int amount = Integer.parseInt(args[4]);
                        rune.giveMythicRune(player, amount);
                        target.sendMessage(CC.translate("&aYou received " + amount + " &aMythic Runes"));
                        player.sendMessage(CC.translate("&aYou have given " + target.getDisplayName() + " &a" + amount + " &aMythic Runes"));
                    }
                }
            }
        }
    }
}
