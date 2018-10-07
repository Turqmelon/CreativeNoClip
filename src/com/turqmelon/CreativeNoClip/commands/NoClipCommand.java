package com.turqmelon.CreativeNoClip.commands;

import co.melondev.MelonCore.commands.AllowedUserScope;
import co.melondev.MelonCore.commands.CommandFlag;
import co.melondev.MelonCore.commands.ServerCommand;
import co.melondev.MelonCore.commands.argument.PlayerArgument;
import com.google.common.collect.ImmutableMap;
import com.turqmelon.CreativeNoClip.CreativeNoClip;
import org.apache.commons.lang.Validate;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class NoClipCommand extends ServerCommand {

    private CreativeNoClip plugin;

    public NoClipCommand(CreativeNoClip plugin) {
        super("noclip", AllowedUserScope.PLAYER, "Toggle no-clip mode", "creativenoclip.use", Arrays.asList(
                new PlayerArgument("target", false, false)
        ));
        this.plugin = plugin;
    }

    @Override
    protected void runCommand(CommandSender sender, ImmutableMap<String, Object> args, ImmutableMap<Character, CommandFlag> flags) {
        Player player = (Player) sender;
        if (args.containsKey("target")){
            Validate.isTrue(player.hasPermission(getPermission() + ".other"), "You're not allowed to set others to no-clip mode.");
            Player target = (Player)args.get("target");
            Validate.isTrue(target.getGameMode() == GameMode.CREATIVE || plugin.isNoClipEnabled(target), target.getDisplayName() + "§c must be in creative mode first.");
            plugin.setNoClipEnabled(target, !plugin.isNoClipEnabled(target));
            if (plugin.isNoClipEnabled(target)){
                player.sendMessage("§eEnabled no-clip for " + target.getDisplayName() + "§e.");
            }
            else{
                player.sendMessage("§eDisabled no-clip for " + target.getDisplayName() + "§e.");
            }
        }
        else{
            Validate.isTrue(player.getGameMode() == GameMode.CREATIVE || plugin.isNoClipEnabled(player), "You must be in creative mode before engaging no-clip.");
            plugin.setNoClipEnabled(player, !plugin.isNoClipEnabled(player));
        }
    }
}
