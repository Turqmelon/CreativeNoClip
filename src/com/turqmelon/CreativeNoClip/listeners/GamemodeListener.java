package com.turqmelon.CreativeNoClip.listeners;

import com.turqmelon.CreativeNoClip.CreativeNoClip;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeListener implements Listener {

    private CreativeNoClip plugin;

    public GamemodeListener(CreativeNoClip plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onGamemode(PlayerGameModeChangeEvent event){
        Player player = event.getPlayer();
        if (!plugin.isNoClipEnabled(player))
            return;

        if (event.getNewGameMode() != GameMode.CREATIVE && event.getNewGameMode() != GameMode.SPECTATOR){
            plugin.setNoClipEnabled(player, false);
        }
    }
}
