package com.turqmelon.CreativeNoClip.listeners;

import com.turqmelon.CreativeNoClip.CreativeNoClip;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    private final BlockFace[] NEAR_FACES = {
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST,
            BlockFace.NORTH_EAST, BlockFace.NORTH_WEST, BlockFace.SOUTH_EAST, BlockFace.SOUTH_WEST,
            BlockFace.UP, BlockFace.DOWN
    };
    private CreativeNoClip plugin;

    public MoveListener(CreativeNoClip plugin) {
        this.plugin = plugin;
    }

    private boolean isNearSolidBlock(Location... locations){
        for(Location location : locations) {
            Block block = location.getBlock();
            for (BlockFace bf : NEAR_FACES) {
                Block rel = block.getRelative(bf);
                if (rel.getType().isSolid()) {
                    return true;
                }
            }
        }
        return false;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event){

        Player player = event.getPlayer();
        if (!plugin.isNoClipEnabled(player))
            return;

        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR){
            plugin.setNoClipEnabled(player, false);
            return;
        }

        Location from = event.getFrom();
        Location to = event.getTo();

        // There's no reason to update this everytime they move their head...
        if (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()){
            Location[] locs = {player.getLocation(), player.getEyeLocation()};
            if (player.getGameMode() == GameMode.SPECTATOR && !isNearSolidBlock(locs)){
                player.setGameMode(GameMode.CREATIVE);
            }
            else if (player.getGameMode() == GameMode.CREATIVE && isNearSolidBlock(locs)){
                player.setGameMode(GameMode.SPECTATOR);
            }
        }

    }
}
