# CreativeNoClip
A simple utility to allow you to no-clip through blocks while in Creative Mode, used for client builds for MelonDev and on client servers.

## Usage

1. Enter **Creative Mode** (or put your target into creative mode)
2. Type `/noclip`
3. Enjoy.

You'll automatically be swapped to spectator mode when you get near a solid object and be moved back to creative mode otherwise. No-Clip mode will automatically disable if your gamemode is changed to adventure or survival.

## Permissions
* `creativenoclip.use` - Allows access to `/noclip`
* `creativenoclip.use.other` - Allows access to `/noclip [player]`

## Uses MelonCore
The `/noclip` command is based on the MelonCore command API, which is used by [MelonDev](https://melondev.co) clients. I published it to github in case the source is useful to anybody else.
