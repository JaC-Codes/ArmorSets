package net.jack.armorsets.commandmanager;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.subcommands.ArmorSetsCommand;
import net.jack.armorsets.subcommands.RunesCommand;
import net.jack.armorsets.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();
    private final ArmorSets armorSets = ArmorSets.getInstance();


    public CommandManager() {
        subCommands.add(new ArmorSetsCommand(ArmorSets.getInstance()));
        subCommands.add(new RunesCommand(ArmorSets.getInstance()));

    }

    public ArrayList<SubCommand> getSubcommands() {
        return subCommands;
    }

    public static void usage(Player player) {
        player.sendMessage(CC.translate("&7---------------------------------------------------\n" +
                "&cUsage: &b/adminas sets give <player> <armorset(warrior/demon)>\n" +
                "\n" +
                "&cUsage: &b/adminas rune give <player> <mythic(rune)> <amount>\n" +
                "---------------------------------------------------"));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                for (int i = 0; i < this.getSubcommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(this.getSubcommands().get(i).getName())) {
                        this.getSubcommands().get(i).perform(player, args);
                    }
                }
            } else if (args.length == 0) {
                usage(player);
            }
        }
        return false;
    }
}

