package com.github.ptom76.mcsemegui;

import org.bukkit.plugin.Plugin;

import static com.github.ptom76.mcsemegui.Mcsemegui.config;

public class ConfigSave {
    private static Plugin plugin;
    private static Mcsemegui main;
    private static InitialConfig initialConfig;
    LoadLanguage loadLanguage = new LoadLanguage();
    public void ConfigSave() {
        config.set("language", initialConfig.language);
    }
}