package net.jack.armorsets.fallencrate;

import com.jeff_media.customblockdata.CustomBlockData;
import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitScheduler;


public class FallenCrate implements Listener {


    private final ArmorSets armorSets;
    private final NamespacedKey key;
    private final NamespacedKey storedItemKey;
    private final Rewards rewards;

    public FallenCrate(ArmorSets armorSets) {
        this.armorSets = armorSets;
        this.key = new NamespacedKey(armorSets, "fallingblock");
        this.storedItemKey = new NamespacedKey(armorSets, "fallenchest");
        this.rewards = new Rewards(armorSets);
    }

    public void crateSummon(Player target) {
        Location location = target.getEyeLocation().add(0, 20, 0);
        Entity entity = location.getWorld().spawnFallingBlock(location, Material.ANVIL.createBlockData());
        entity.getPersistentDataContainer().set(key, PersistentDataType.STRING, "fallingblock");
        target.sendMessage(CC.translate("""
                \s
                &e&lFallen Crate has landed!
                &7(Punch to open!)
                &r"""));
    }

    @EventHandler
    public void onBlockLand(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.FALLING_BLOCK) {
            if (event.getEntity().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                if (event.getEntity().isOnGround()) {
                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(armorSets, new Runnable() {
                        @Override
                        public void run() {
                            event.getBlock().setType(Material.CHEST);
                            PersistentDataContainer customBlockData = new CustomBlockData(event.getBlock(), armorSets);
                            customBlockData.set(storedItemKey, PersistentDataType.STRING, "Thisistheblock");
                        }
                    }, 1);

                }
            }
        }
    }

    @EventHandler
    public void chestBreak(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (!block.getType().equals(Material.CHEST)) return;
        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || (event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
            if (event.getClickedBlock() == null) return;
            final PersistentDataContainer customBlockData = new CustomBlockData(event.getClickedBlock(), armorSets);
            if (customBlockData.has(storedItemKey, PersistentDataType.STRING)) {
                event.setCancelled(true);
                event.getClickedBlock().setType(Material.AIR);
                rewards.giveRewards(player, block.getLocation());

            }

        }
    }

}
