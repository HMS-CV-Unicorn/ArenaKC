package org.battleplugins.arenaKC.command;

import org.battleplugins.arena.command.ArenaCommand;
import org.battleplugins.arena.command.ArenaCommandExecutor;
import org.battleplugins.arenaKC.arena.Killconfirm;
import org.bukkit.entity.Player;

public class KillconfirmCmdExt extends ArenaCommandExecutor {

    public KillconfirmCmdExt(Killconfirm arena) {
        super(arena);
    }

    @ArenaCommand(commands = "test", description = "Adds an infection point to a MyArena competition.", permissionNode = "point.add")
    public void addPoint(Player player) {
        player.sendMessage("成功です！");
    }
}