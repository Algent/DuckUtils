package eu.algent.DuckUtils.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashSet;
import java.util.UUID;

public class AntiSpawnerFarming implements Listener {

    HashSet<UUID> mobsId;

    public AntiSpawnerFarming() {
        mobsId = new HashSet<>();
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER))
            mobsId.add(event.getEntity().getUniqueId());
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        UUID id = event.getEntity().getUniqueId();
        if (mobsId.contains(id)) {
            event.setDroppedExp(0);
            event.getDrops().clear();
            mobsId.remove(id);
        }
    }
}
