package com.turqmelon.CreativeNoClip;

import co.melondev.MelonCore.commands.CommandManager;
import com.turqmelon.CreativeNoClip.commands.NoClipCommand;
import com.turqmelon.CreativeNoClip.listeners.GamemodeListener;
import com.turqmelon.CreativeNoClip.listeners.MoveListener;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class CreativeNoClip extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MoveListener(this), this);
        getServer().getPluginManager().registerEvents(new GamemodeListener(this), this);
        CommandManager.registerCommand(new NoClipCommand(this));
    }

    public void setNoClipEnabled(Player player, boolean enabled){
        if (enabled && !isNoClipEnabled(player)){
            player.setMetadata("ncn-enabled", new FixedMetadataValue(this, null));
            player.sendMessage("§aCreative No Clip - §lENABLED");
        }
        else if (isNoClipEnabled(player)){
            player.removeMetadata("ncn-enabled", this);
            player.sendMessage("§cCreative No Clip - §lDISABLED");
        }
    }

    public boolean isNoClipEnabled(Player player){
        return player.hasMetadata("ncn-enabled");
    }
}
