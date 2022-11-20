package net.jack.armorsets.commandhandler;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.guiview.GUICreator;
import net.jack.armorsets.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand implements CommandExecutor {


    private final ArmorSets armorSets;
    private final GUICreator guiCreator;

    public PlayerCommand(ArmorSets armorSets) {
        this.armorSets = armorSets;
        this.guiCreator = new GUICreator(armorSets);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to use this command."));
        }else {
            guiCreator.openInventory(player);
        }


        return false;
    }
}


