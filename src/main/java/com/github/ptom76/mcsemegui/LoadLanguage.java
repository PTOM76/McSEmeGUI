package com.github.ptom76.mcsemegui;

import com.google.gson.Gson;
import java.io.*;
import static org.bukkit.Bukkit.getLogger;

public class LoadLanguage {

    public static Language lang = new Language();
    private static StringBuilder jsonBuilder2 = new StringBuilder();
    private static String json2;
    public static String languageOption = new String();

    public void main() {

        InputStream in = getClass().getResourceAsStream("/lang/" + languageOption);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            try {
                String string;
                while ((string = bufferedReader.readLine()) != null) {
                    jsonBuilder2.append(string);
                }
                json2 = jsonBuilder2.toString();
                Gson gson = new Gson();
                lang = gson.fromJson(json2, Language.class);
            } catch (IOException e) {
                getLogger().info("failed to read language file.");
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception ex) {
                        getLogger().info("failed to close language file.");
                    }
                }
            }

            //} catch (URISyntaxException use) {
            //none
            //}
        }catch (UnsupportedEncodingException unsupportedEncodingException){
            //none
        }
        new ServerManagerGUI();
        MainCommandSave scc = new MainCommandSave();
        scc.loadMarkCommands();
    }
}
            // ServerManagerGUI.addToConsole("failed to read language file.", "gui");


    /*
    public void loadMarkCommands() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(McsemeguiFolder));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                jsonBuilder.append(string);
            }
            json = jsonBuilder.toString();
            Gson gson = new Gson();
            Language = gson.fromJson(json, SaveCommand.class);
            for (int i = 0; i < Language.commands.size(); i++)
            {
                modelMarkCommand.addElement(Language.commands.get(i).command);
            }
            listMarkCommand.setModel(modelMarkCommand);
        } catch (IOException e) {
            ServerManagerGUI.addToConsole("コマンドの読込に失敗しました。", "gui");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                    ServerManagerGUI.addToConsole("コマンドのファイルを閉じることに失敗しました。", "gui");
                }
            }
        }
    }
     */




