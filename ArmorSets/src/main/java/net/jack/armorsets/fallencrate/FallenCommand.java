package net.jack.armorsets.fallencrate;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.commandmanager.CommandManager;
import net.jack.armorsets.commandmanager.SubCommand;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FallenCommand extends SubCommand {

    private final ArmorSets armorSets;
    private final CommandManager manager;
    private final FallenCrate fallenCrate;

    public FallenCommand(ArmorSets armorSets) {
        this.armorSets = armorSets;
        this.manager = armorSets.getManager();
        this.fallenCrate = new FallenCrate(armorSets);
    }


    @Override
    public String getName() {
        return "fallencrate";
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
                    int amount = Integer.parseInt(args[3]);
                    fallenCrate.crateSummon(target);
                }
            }
        }
    }

