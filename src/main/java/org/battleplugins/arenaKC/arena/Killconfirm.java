package org.battleplugins.arenaKC.arena;

import java.time.Duration;
import java.util.Set;

import org.battleplugins.arena.Arena;
import org.battleplugins.arena.ArenaPlayer;
import org.battleplugins.arena.competition.Competition;
import org.battleplugins.arena.competition.LiveCompetition;
import org.battleplugins.arena.config.ArenaOption;
import org.battleplugins.arena.event.ArenaEventHandler;
import org.battleplugins.arena.event.arena.ArenaPhaseStartEvent;
import org.battleplugins.arena.team.ArenaTeam;
import org.battleplugins.arenaKC.command.KillconfirmCmdExt;
import org.bukkit.Bukkit;
import static org.bukkit.Material.WHITE_WOOL;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import io.papermc.paper.event.player.PlayerPickItemEvent;

public class Killconfirm extends Arena {
    private Competition<?> comp;
    private LiveCompetition<?> liveComp;
    
    //ドッグタグ(羊毛)の消失するまでの時間設定
    @ArenaOption(name="drop-time", description="duration drop team-item(dogtag)")
    private Duration dropTime = Duration.ofSeconds(30);
    

    @Override
    public KillconfirmCmdExt createCommandExecutor() {
        return new KillconfirmCmdExt(this);
    }

    //victoryの条件
    @ArenaOption(name = "need-dogtag", description = "勝利に必要なポイント")
    private int needDogtag = 10;
    
    //public ArenaTeam getTeam(Player player) {
    //    return teamManager().getTeam(player);
    //}

    //死亡時処理
    @ArenaEventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;
        Player victim = event.getPlayer();
        Player attacker = (Player) event.getEntity();
        //ArenaTeam victimTeam = getTeam(victim);
        //ItemStack dogtag = victimTeam.getItem();


        //羊毛ドロップ
    }

    //アイテム取得時処理
    @ArenaEventHandler
    public void onPickItem(PlayerPickItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getActiveItem();
        if (item.getType().equals(WHITE_WOOL)) {
            //チーム分岐処理
            //敵チームの場合ポイント付与
            //自チームの場合キル否定
        }
    }

    

    void checkPoint(int currentDogtag) {
        //ポイント付与
        giveDogtag();

        //指定したポイントを上回った場合、試合終了
        if (currentDogtag >= needDogtag) {
            end();
            liveComp.getPhaseManager().end(true);
        }
    }

    private void giveDogtag() {

    }

    private void end() {
        for (ArenaPlayer aPlayer : liveComp.getPlayers()) {
            aPlayer.getPlayer().sendMessage("試合終了！");
        }
    }

    //フェーズ開始時処理
    @ArenaEventHandler
    public void onPhaseStart(ArenaPhaseStartEvent event) {
        if (!event.getPhase().toString().contains("Ingame"))
            return;

        comp = event.getCompetition();
        liveComp = (LiveCompetition<?>) comp;
        Set<ArenaPlayer> players = liveComp.getPlayers();
        Bukkit.broadcastMessage(String.valueOf(needDogtag));

        //ﾏﾝﾏﾐｰｱ…
        //あきらめるな！
        Set<ArenaTeam> teams = liveComp.getTeamManager().getTeams();
        Bukkit.broadcastMessage(teams.toString());
        for (ArenaTeam team : teams) {
            Set<ArenaPlayer> team1players = liveComp.getTeamManager().getPlayersOnTeam(team);
            Bukkit.broadcastMessage(team + ":" + team1players.toString());
        }
    }

//    //フェーズ終了時処理
//    @ArenaEventHandler
//    public void onPhaseEnd(ArenaPhaseCompleteEvent event) {
//        if (!event.getPhase().toString().contains("Ingame"))
//            return;
//
//        Competition<?> comp = event.getCompetition();
//        LiveCompetition<?> liveComp = (LiveCompetition<?>) comp;
//        Set<ArenaPlayer> players = liveComp.getPlayers();
//    }

//    @ArenaEventHandler
//    public void onDamage(EntityDamageByEntityEvent event) {
//        if (!(event.getEntity() instanceof Player) && !(event.getDamager() instanceof Player)) return;
//
//        Player attacker = (Player) event.getDamager();
//        Player victim = (Player) event.getEntity();
//    }

}