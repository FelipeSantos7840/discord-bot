package org.lumbot.collector.collectorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class TimerCollectorRSS {
    public static void timerStart(){
        ScheduledExecutorService sheduled = Executors.newScheduledThreadPool(1);
        sheduled.scheduleAtFixedRate(() -> {

        },0,2, TimeUnit.HOURS);
    }
}
