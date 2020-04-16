package com.github.ptom76.mcsemegui;

import com.google.gson.*;
import com.google.gson.internal.$Gson$Types;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.github.ptom76.mcsemegui.Mcsemegui.pluginsFolder;




public class SaveCommand<T> {

    private static Gson gson = new Gson();

    private String command;
    private T value;
    private List<T> values;
    private String saveCommandList;
    private String jsonSaveCommandString;
    public SaveCommand() {
        //使われないメソッド^^
    }
    public SaveCommand(String command) {
        this.command = command;
    }

    public void main() {
       // commanddata = gson.fromJson(saveCommandList, commanddata.getClass());
        File McsemeguiFolder = new File(pluginsFolder, "save_commands.json");
        FileReader fileReader = null;
        try
        {
            //InputStreamReader jsonSaveCommand = new InputStreamReader(new FileInputStream(McsemeguiFolder));
            //BufferedReader br = new BufferedReader(jsonSaveCommand);
            //StringBuilder sb = new StringBuilder();
            //String line;
            //while ((line = br.readLine()) != null) {sb.append(line);}br.close();
            //jsonSaveCommandString = (sb.toString()).substring(1, 1);
//            ServerManagerGUI.addToConsole(jsonSaveCommandString);
            int n = 0;
            fileReader = new FileReader(McsemeguiFolder);
            while(n !=-1){
                n = fileReader.read();
            }
        }
        catch (IOException e)
        {
            ServerManagerGUI.addToConsole("コマンドの読込に失敗しました。", "gui");
        }finally {if (fileReader != null){try{fileReader.close();}catch (Exception ex){ServerManagerGUI.addToConsole("コマンドのファイルを閉じることに失敗しました。", "gui");}}}
        String commanddata = ServerManagerGUI.textField1.getText();
        if (!McsemeguiFolder.exists()) pluginsFolder.mkdir();
        try
        {
            SaveCommand saveCommand = new SaveCommand(commanddata);/*new SaveCommand(saveCommandList),*/
            if (pluginsFolder.canWrite())
            {
                FileWriter filewriter = new FileWriter(McsemeguiFolder);
                filewriter.write("[" + jsonSaveCommandString + "," + System.lineSeparator() + gson.toJson(saveCommand)  + "]");
                filewriter.flush();
                filewriter.close();//JSON config
                ServerManagerGUI.addToConsole("コマンドを保存しました。", "gui");
            }
            else
            {
                ServerManagerGUI.addToConsole("コマンドの保存に失敗しました。(書き込み権限がありません。)", "gui");
            }
        }
        catch (IOException e)
        {
            ServerManagerGUI.addToConsole("コマンドの保存に失敗しました。", "gui");
        }


    }
    public static <T> T objectFromJson(String json, Class<T> clazz) {
        return SaveCommand.gson.fromJson(json, clazz);
    }

    public static <T> List<T> objectsFromJson(String json, Class<T> clazz) {
        Type type = $Gson$Types.newParameterizedTypeWithOwner(null, ArrayList.class, clazz);
        return SaveCommand.gson.fromJson(json, type);
    }
    /*public void loadSaveCommand(){
        try
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(pluginsFolder, "save_commands.json"));
            JsonReader jsr = new JsonReader(isr);
            Gson gson = new Gson();
            SaveCommand commanddata = new SaveCommand();
            commanddata = gson.fromJson(jsr, commanddata.getClass());
            System.out.println(commanddata);
        }catch (IOException e){
            ServerManagerGUI.addToConsole("コマンドの読込に失敗しました。");
        }
    }
     */
}