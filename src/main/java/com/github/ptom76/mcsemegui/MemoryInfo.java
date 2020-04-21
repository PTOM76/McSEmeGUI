package com.github.ptom76.mcsemegui;
import java.text.DecimalFormat;
//名前がメモリ情報だけど本当はステータス
public class MemoryInfo {
    public static long プレイヤー数;
    private static LoadLanguage Langs;

    public static String getMemoryInfo() {
        String info = null;
        DecimalFormat format_mem = new DecimalFormat("#,###MB");
        DecimalFormat format_ratio = new DecimalFormat("##.#");
        long free = Runtime.getRuntime().freeMemory() / 1000000;
        long total = Runtime.getRuntime().totalMemory() / 1000000;
        long used = total - free;
        double ratio = (used * 100 / (double) total);
        info = Langs.lang.totalMemory+":" + format_mem.format(total);
        info += System.lineSeparator();
        info += Langs.lang.emptyMemory+":" + format_mem.format(free);
        info += System.lineSeparator();
        info += Langs.lang.usedMemory+":" + format_mem.format(used);
        return info;
    }
    public static void viewMemoryInfo()
    {
        String textAreaData = Langs.lang.status + System.lineSeparator() + getMemoryInfo() + System.lineSeparator() + Langs.lang.numberOfPlayers+":" + プレイヤー数 + Langs.lang.people;
        ServerManagerGUI.textArea1.setText(textAreaData);
    }
}