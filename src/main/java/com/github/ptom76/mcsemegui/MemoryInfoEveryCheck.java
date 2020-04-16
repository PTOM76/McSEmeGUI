package com.github.ptom76.mcsemegui;

import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryInfoEveryCheck {
    public static long speed;
    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer(false);
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                MemoryInfo.viewMemoryInfo();
            }
        };
        timer.schedule(task, 0, speed);
    }
}