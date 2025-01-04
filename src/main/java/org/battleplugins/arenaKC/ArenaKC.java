package org.battleplugins.arenaKC;

import org.battleplugins.arena.BattleArenaApi;
import org.battleplugins.arenaKC.arena.Killconfirm;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArenaKC extends JavaPlugin {

    private static ArenaKC instance;

    @Override
    public void onEnable() {
        this.getLogger().info("Hello!");
        instance = this;

        BattleArenaApi.get().registerArena(this, "Killconfirm", Killconfirm.class, Killconfirm::new);

//        registerEvents();
    }

//    private void registerEvents() {
//        getServer().getPluginManager().registerEvents(new PlayerJoinEventListner(), this);
//        getServer().getPluginManager().registerEvents(new PlayerQuitEventListner(), this);
//        ZombieDisguise zombieDisguise = new ZombieDisguise();
//        getServer().getPluginManager().registerEvents(zombieDisguise, this);
//        ProtocolLibrary.getProtocolManager().addPacketListener(zombieDisguise);
//    }

    @Override
    public void onDisable() {
    }

    public static ArenaKC getInstance() {
        return instance;
    }
}
