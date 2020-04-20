package com.github.ptom76.mcsemegui;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Mcsemegui extends JavaPlugin implements Listener
{
    ServerManagerGUI gui;
    public static File pluginsFolder;
    MainCommandSave scc = new MainCommandSave();
    @Override
    public void onEnable()
    {
        getLogger().info("Minecraft Server Emerald GUIが有効になりました!");
        MemoryInfoEveryCheck.speed = 100;
        gui = new ServerManagerGUI();
        Bukkit.getPluginManager().registerEvents(this, this);
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        pluginsFolder = getDataFolder();
        scc.loadMarkCommands();
    }
    @Override
    public void onDisable()
    {
        getLogger().info("Minecraft Server Emerald GUIが無効になりました!");
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        gui.addToConsole("コマンド" + event.getPlayer().getName() + " >" + event.getMessage(), "command");
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        gui.addToConsole("チャット" + event.getPlayer().getName() + " >" + event.getMessage(), "chat");
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        gui.addToConsole(event.getPlayer().getName() + "さんが参加しました。", "chat");
        gui.addPlayersList(event.getPlayer().getName());
        MemoryInfo.プレイヤー数++;
        MemoryInfo.viewMemoryInfo();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        gui.addToConsole(event.getPlayer().getName() + "さんが退出しました。", "chat");
        gui.removePlayersList(event.getPlayer().getName());
        MemoryInfo.プレイヤー数--;
        MemoryInfo.viewMemoryInfo();
    }
}
