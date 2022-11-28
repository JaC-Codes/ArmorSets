package net.jack.armorsets.subcommands;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.commandmanager.CommandManager;
import net.jack.armorsets.commandmanager.SubCommand;
import net.jack.armorsets.itembuilder.Demon;
import net.jack.armorsets.itembuilder.Warrior;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ArmorSetsCommand extends SubCommand {

    private final ArmorSets armorSets;

    public ArmorSetsCommand(ArmorSets armorSets) {
        this.armorSets = armorSets;
    }

    Demon demon = new Demon();
    Warrior warrior = new Warrior();

    @Override
    public String getName() {
        return "sets";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (!player.hasPermission("armorsets.admin")) {
            player.sendMessage(CC.translate("&aYou do not have permission to perform this command."));
        }
        if (args.length < 4) {
            CommandManager.usage(player);
            return;
        }
        if (args[1].equalsIgnoreCase("give")) {
                Player target = Bukkit.getPlayer(args[2]);
                if (target != null) {
                    if (args[3].equalsIgnoreCase("demon")) {
                        demon.giveDemon(target);
                        target.sendMessage(CC.translate("&aYou received the &c&lDemon kit&a."));
                        player.sendMessage(CC.translate("&aYou have given " + target.getDisplayName() + " &athe &c&lDemon kit&a."));
                    } else if (args[3].equalsIgnoreCase("warrior")) {
                        warrior.giveWarrior(target);
                        target.sendMessage(CC.translate("&aYou received the &b&lWarrior kit&a."));
                        player.sendMessage(CC.translate("&aYou have given " + target.getDisplayName() + " &athe &b&lWarrior kit&a."));
                    }
                }
            }
        }
    }






