package com.github.ptom76.mcsemegui;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import static com.github.ptom76.mcsemegui.Mcsemegui.config;
import static org.bukkit.Bukkit.getLogger;

public class InitialConfig extends JFrame implements ActionListener{
    protected Container pane;
    private static LoadLanguage loadLanguage = new LoadLanguage();
    String[] languages = {"English","日本語","한국어","中文"};
    JComboBox languageSettingBox;
    JButton okButton;
    public static int languageChange = 0;
    private static ConfigSave configSave = new ConfigSave();
    public static String language = null;
    public static void main(String[] args){
        if((config.contains("language")==true)&&(languageChange == 0)) {
            loadLanguage.languageOption = config.getString("language");
            LoadLanguage loadLanguage = new LoadLanguage();
            loadLanguage.main();
            return;
        }
        InitialConfig frame = new InitialConfig("Language Setting");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    InitialConfig(String t){
        super(t);
        ClassLoader cl = this.getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(cl.getResource("icon.png"));
        this.setIconImage(icon.getImage());
        this.setTheme();
        this.setSize(275, 175);
        pane = getContentPane();
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        okButton = new JButton("OK");
        languageSettingBox = new JComboBox(languages);
        languageSettingBox.setPreferredSize(new Dimension(80, 20));
        okButton.addActionListener(this);
        languageSettingBox.addActionListener(this);
        panel.add(okButton,BorderLayout.CENTER);
        panel2.add(languageSettingBox,BorderLayout.CENTER);

        pane.add(panel2,BorderLayout.CENTER);
        pane.add(panel,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                setVisible(false);
            }
        });
    }
    void setTheme()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex1)
        {
            ex1.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectLanguage;
        if (languageSettingBox.getSelectedIndex() == -1){
            selectLanguage = "(not select)";
        }else{
            selectLanguage = (String)languageSettingBox.getSelectedItem();

        }
        if ((e.getSource() == okButton))
        {

            if (selectLanguage == "English"){language = "en_us.json";}
            if (selectLanguage == "日本語"){language = "ja_jp.json";}
            if (selectLanguage == "한국어"){language = "ko_kr.json";}
            if (selectLanguage == "中文"){language = "ch_cn.json";}
            LoadLanguage loadLanguage = new LoadLanguage();
            loadLanguage.languageOption = language;
            configSave.ConfigSave();
            if (languageChange == 1) {
                getLogger().info("Server is reloading...");
                this.setVisible(false);
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "reload");
            }
            this.setVisible(false);
            loadLanguage.main();
        }
    }
}
