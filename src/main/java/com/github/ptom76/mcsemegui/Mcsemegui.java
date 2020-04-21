package com.github.ptom76.mcsemegui;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
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
    //public static FileConfiguration config;
    public static LoadLanguage loadLanguage = new LoadLanguage();
    public static File pluginsFolder;
    public static FileConfiguration config = null;
    ServerManagerGUI gui;
    private static LoadLanguage Langs;
    @Override
    public void onEnable()
    {
        getLogger().info("Minecraft Server Emerald GUIが有効になりました!");
        config = getConfig();
        saveConfig();
        MemoryInfoEveryCheck.speed = 100;
        Bukkit.getPluginManager().registerEvents(this, this);
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        pluginsFolder = getDataFolder();
        String[] t = new String[0];
        InitialConfig.main(t);
        //loadLanguage.main();
    }
    @Override
    public void onDisable()
    {
        saveConfig();
        getLogger().info("Minecraft Server Emerald GUIが無効になりました!");
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        gui.addToConsole(Langs.lang.command + event.getPlayer().getName() + " >" + event.getMessage(), "command");
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        gui.addToConsole(Langs.lang.chat + event.getPlayer().getName() + " >" + event.getMessage(), "chat");
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        gui.addToConsole(event.getPlayer().getName() + Langs.lang.serverJoin, "chat");
        gui.addPlayersList(event.getPlayer().getName());
        MemoryInfo.プレイヤー数++;
        MemoryInfo.viewMemoryInfo();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        gui.addToConsole(event.getPlayer().getName() + Langs.lang.serverLeave, "chat");
        gui.removePlayersList(event.getPlayer().getName());
        MemoryInfo.プレイヤー数--;
        MemoryInfo.viewMemoryInfo();
    }
}
