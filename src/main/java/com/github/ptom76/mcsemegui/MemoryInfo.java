package com.github.ptom76.mcsemegui;
import java.text.DecimalFormat;
//名前がメモリ情報だけど本当はステータス
public class MemoryInfo {
    public static long プレイヤー数;

    public static String getMemoryInfo() {
        String info = null;
        DecimalFormat format_mem = new DecimalFormat("#,###MB");
        DecimalFormat format_ratio = new DecimalFormat("##.#");
        long free = Runtime.getRuntime().freeMemory() / 1000000;
        long total = Runtime.getRuntime().totalMemory() / 1000000;
        long used = total - free;
        double ratio = (used * 100 / (double) total);
        info = "  合計メモリ:" + format_mem.format(total);
        info += System.lineSeparator();
        info += "  空きメモリ:" + format_mem.format(free);
        info += System.lineSeparator();
        info += "  使用メモリ:" + format_mem.format(used);
        return info;
    }
    public static void viewMemoryInfo()
    {
        String textAreaData = "ステータス" + System.lineSeparator() + getMemoryInfo() + System.lineSeparator() + "  プレイヤー数:" + プレイヤー数;
        ServerManagerGUI.textArea1.setText(textAreaData);
    }
}