package me.aurynth.fastcreeper;

import org.bukkit.ChatColor;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FastCreeper extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        logEnable();
    }

    @Override
    public void onDisable() {
        logDisable();
    }

    @EventHandler
    public void onCreeperSpawn(CreatureSpawnEvent event) {
        if (!(event.getEntity() instanceof Creeper creeper)) {
            return;
        }

        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
            return;
        }

        // Delay by 1 tick to ensure proper spawning
        getServer().getScheduler().runTaskLater(this, () -> {
            if (!creeper.isValid()) {
                return;
            }

            // Normal creeper, not charged
            creeper.setMaxFuseTicks(80); // 4 seconds
            creeper.ignite();
        }, 1L);
    }

    private void logEnable() {
        String[] lines = {
                "§f╔══════════════════════════════════════════════════════════════╗",
                "§f║                                                              ║",
                "§f║                     §bFAST CREEPER                           §f║",
                "§f║                     §3Aurynth Studios                        §f║",
                "§f║                                                              ║",
                "§f╠══════════════════════════════════════════════════════════════╣",
                "§f║ §aVersion: §f1.0.0                                            §f║",
                "§f║ §aDeveloper: §fLakshay                                        §f║",
                "§f║ §aGitHub: §fgithub.com/Aurynth/FastCreeper                    §f║",
                "§f║ §aDiscord: §fdiscord.gg/yourinvite                            §f║",
                "§f╠══════════════════════════════════════════════════════════════╣",
                "§f║                        §eFEATURES                            §f║",
                "§f║ §a✓ §fFast Creeper Fuse                                      §f║",
                "§f║ §a✓ §fOptimised For Your PvP Servers                         §f║",
                "§f╠══════════════════════════════════════════════════════════════╣",
                "§f║                   §aSuccessfully Loaded!                    §f║",
                "§f╚══════════════════════════════════════════════════════════════╝"
        };

        getLogger().info(" ");
        for (String line : lines) {
            getServer().getConsoleSender()
                    .sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
        getLogger().info(" ");
    }

    private void logDisable() {
        String[] lines = {
                "§f╔══════════════════════════════════════════════════════════════╗",
                "§f║                                                              ║",
                "§f║                     §cFAST CREEPER                           §f║",
                "§f║                     §3Aurynth Studios                        §f║",
                "§f║                                                              ║",
                "§f║              §fThanks For Using Our Plugin!                 §f║",
                "§f║                                                              ║",
                "§f╚══════════════════════════════════════════════════════════════╝"
        };

        getLogger().info(" ");
        for (String line : lines) {
            getServer().getConsoleSender()
                    .sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
        getLogger().info(" ");
    }
}
