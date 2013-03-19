package com.github.skipperguy12.HealthDisplay;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.server.v1_5_R1.Scoreboard;
import net.minecraft.server.v1_5_R1.ScoreboardTeam;
import net.minecraft.server.v1_5_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_5_R1.CraftWorld;

public class ScoreboardManager {
    static List<Integer> list = new ArrayList<Integer>();
 
    @SuppressWarnings("unchecked")
    public static void load() {
        World mcWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        for (String str : (String[]) mcWorld.getScoreboard().f().toArray(new String[mcWorld.getScoreboard().f().size()])) {
            int entry = -1;
            try {
                entry = Integer.parseInt(str);
            }
            catch (Exception e) {};
            if (entry != -1) {
                list.add(entry);
            }
        }
    }
 
    public static void update(String player, String prefix, String suffix) {
 
        World mcWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
 
        if (prefix == null || prefix.isEmpty())
            prefix = getPrefix(player, mcWorld);
        if (suffix == null || suffix.isEmpty())
            suffix = getSuffix(player, mcWorld);
 
        ScoreboardTeam s = get(prefix, suffix);
 
        mcWorld.getScoreboard().a(player, s);
 
    }
    public static void clear(String player) {
 
        World mcWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
 
        ScoreboardTeam s = getTeam(player, mcWorld);
 
        if (s != null)
            mcWorld.getScoreboard().b(player, s);
 
    }
 
    @SuppressWarnings("unchecked")
    private static String getPrefix(String player, World mcWorld) {
        for (ScoreboardTeam team : (ScoreboardTeam[]) mcWorld.getScoreboard().g().toArray(new ScoreboardTeam[mcWorld.getScoreboard().f().size()])) {
            if (team.d().contains(player))
                return team.e();
        }
        return "";
    }
    @SuppressWarnings("unchecked")
    private static String getSuffix(String player, World mcWorld) {
        for (ScoreboardTeam team : (ScoreboardTeam[]) mcWorld.getScoreboard().g().toArray(new ScoreboardTeam[mcWorld.getScoreboard().f().size()])) {
            if (team.d().contains(player))
                return team.f();
        }
        return "";
    }
    @SuppressWarnings("unchecked")
    private static ScoreboardTeam getTeam(String player, World mcWorld) {
        for (ScoreboardTeam team : (ScoreboardTeam[]) mcWorld.getScoreboard().g().toArray(new ScoreboardTeam[mcWorld.getScoreboard().f().size()])) {
            if (team.d().contains(player))
                return team;
        }
        return null;
    }
 
    private static ScoreboardTeam declareTeam(World mcWorld, String name, String prefix, String suffix) {
        if (mcWorld.getScoreboard().e(name) != null) {
            mcWorld.getScoreboard().d(mcWorld.getScoreboard().e(name));
        }
        mcWorld.getScoreboard().f(name);
        mcWorld.getScoreboard().e(name).b(prefix);
        mcWorld.getScoreboard().e(name).c(suffix);
        return mcWorld.getScoreboard().e(name);
    }
 
    private static ScoreboardTeam get(String prefix, String suffix) {
 
        World mcWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
 
        update(mcWorld);
 
        for (int t : list.toArray(new Integer[list.size()])) {
 
            if (mcWorld.getScoreboard().e("" + t) != null) {
                ScoreboardTeam s = mcWorld.getScoreboard().e("" + t);
                if (s.f().equals(suffix) && s.e().equals(prefix)) {
                    return s;
                }
            }
        }
        return declareTeam(mcWorld, nextName() + "", prefix, suffix);
 
    }
    private static int nextName() {
        int at = 0;
        boolean cont = true;
        while (cont) {
            cont = false;
            for (int t : list.toArray(new Integer[list.size()])) {
                if (t == at) {
                    at++;
                    cont = true;
                }
 
            }
        }
        list.add(at);
        return at;
    }
    @SuppressWarnings("unchecked")
    private static void update(World mcWorld) {
 
        for (ScoreboardTeam team : (ScoreboardTeam[]) mcWorld.getScoreboard().g().toArray(new ScoreboardTeam[mcWorld.getScoreboard().f().size()])) {
            int entry = -1;
            try {
                entry = Integer.parseInt(team.c());
            }
            catch (Exception e) {};
            if (entry != -1) {
                if (team.d().size() == 0) {
                    mcWorld.getScoreboard().d(team);
                    list.remove(new Integer(entry));
                }
            }
        }
    }
}