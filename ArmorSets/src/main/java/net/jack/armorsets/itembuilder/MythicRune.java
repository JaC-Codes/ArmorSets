package net.jack.armorsets.itembuilder;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.timer.Timer;
import net.jack.armorsets.utils.CC;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class MythicRune implements Listener {


    private final ArmorSets armorSets;
    private final NamespacedKey key;
    private final Timer timer;

    public MythicRune(ArmorSets armorSets) {
        this.armorSets = armorSets;
        this.key = new NamespacedKey(armorSets, "mythicrune");
        this.timer = new Timer(armorSets);
    }

    public ItemStack getMythicRune() {
        ItemStack mythicrune = new ItemBuild(Material.NETHER_STAR, 1)
                .setDisplayName(CC.translate("&f&l** &r&d&lMythic Rune &f&l**"))
                .setLore(CC.translate(" "), CC.translate("&d&oMythical rune"), CC.translate("&fx2 your xp intake for 2 hours once you redeem!"))
                .build();


        ItemMeta meta = mythicrune.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "mythicrune");
        mythicrune.setItemMeta(meta);

        return mythicrune;
    }

    public void giveMythicRune(Player player, int amount) {
        ItemStack item = getMythicRune();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || item.getType() == Material.AIR) return;
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                if (meta == null) return;
                if (meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    if (item.getAmount() >= 1) {
                        item.setAmount(item.getAmount() - 1);
                    }
                   player.sendTitle(CC.translate("&a&lArmorEvents"), CC.translate("&b&lMythic Rune &a&lActivated"),
                         30, 150, 30);
                    timer.timerStart(player);
                    this.armorSets.getTimers().setDoublexp(player.getUniqueId(), System.currentTimeMillis() + (this.armorSets.getConfig().getInt("Timers.double_exp.time")) * 1000L);

                }
            }
        }
    }

    @EventHandler
    public void doubleExpEvent(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        if (this.armorSets.getTimers().checkDoublexp(player.getUniqueId())) {
            event.setAmount(event.getAmount() * 2);
            player.sendMessage("Test");
        }
    }
}
