package org.lumbot.collector.collectorservice;

import com.rometools.rome.feed.synd.SyndFeed;
import org.lumbot.collector.CollectorRSS;
import org.lumbot.collector.DataRSS;
import org.lumbot.collector.TypeRSS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class TimerCollectorRSS {

    public static CollectorRSS headlineCollector;
    public static CollectorRSS airingCollector;

    public static void  timerStart(){
        ScheduledExecutorService sheduled = Executors.newScheduledThreadPool(1);
        sheduled.scheduleAtFixedRate(() -> {
            headlineCollector = new CollectorRSS(TypeRSS.HEADLINE);
            airingCollector = new CollectorRSS(TypeRSS.AIRING);

            boolean verifyAir = airingCollector.collectAndProcessRSS("https://www.livechart.me/feeds/episodes");

            boolean verifyHead = headlineCollector.collectAndProcessRSS("https://www.livechart.me/feeds/headlines");

        },0,2, TimeUnit.HOURS);
    }
}
