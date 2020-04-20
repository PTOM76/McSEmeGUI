package com.github.ptom76.mcsemegui;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;

import static com.github.ptom76.mcsemegui.Mcsemegui.pluginsFolder;
import static com.github.ptom76.mcsemegui.ServerManagerGUI.*;

public class MainCommandSave {
    private StringBuilder jsonBuilder = new StringBuilder();
    private String json;
    public static SaveCommand model = new SaveCommand();
    private int modelTrue;
    public MainCommandSave() {
    }

    public void main() {
//none
    }

    public void loadMarkCommands() {
        model.commands = new ArrayList<Command>();
        File McsemeguiFolder = new File(pluginsFolder, "save_commands.json");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(McsemeguiFolder));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                jsonBuilder.append(string);
            }
            json = jsonBuilder.toString();
            Gson gson = new Gson();
            model = gson.fromJson(json, SaveCommand.class);
            for (int i = 0; i < model.commands.size(); i++)
            {
                modelMarkCommand.addElement(model.commands.get(i).command);
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
    public void saveMarkCommands()
    {
        ServerManagerGUI.addCommandMark(ServerManagerGUI.textField1.getText());
        ServerManagerGUI.addToConsole(ServerManagerGUI.textField1.getText()+"をマーク(保存)しました。","gui");
        Command saveCommands = new Command();
        saveCommands.command = ServerManagerGUI.textField1.getText();
        model.commands.add(saveCommands);
        File McsemeguiFolder = new File(pluginsFolder, "save_commands.json");
        if (!McsemeguiFolder.exists()) pluginsFolder.mkdir();
        try
        {
            if (pluginsFolder.canWrite())
            {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                FileWriter filewriter = new FileWriter(McsemeguiFolder);
                filewriter.write(gson.toJson(model));
                filewriter.flush();
                filewriter.close();//JSON config
                ServerManagerGUI.addToConsole("コマンドを保存しました。", "gui");
            }
            else {
                ServerManagerGUI.addToConsole("コマンドの保存に失敗しました。(書き込み権限がありません。)", "gui");
            }
        }
        catch (IOException e)
        {
            ServerManagerGUI.addToConsole("コマンドの保存に失敗しました。", "gui");
        }
    }
}

