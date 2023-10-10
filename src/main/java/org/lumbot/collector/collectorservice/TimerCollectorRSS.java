package org.lumbot.collector.collectorservice;

import com.rometools.rome.feed.synd.SyndFeed;
import net.dv8tion.jda.api.JDA;
import org.lumbot.collector.CollectorRSS;
import org.lumbot.collector.DataRSS;
import org.lumbot.collector.TypeRSS;
import org.lumbot.collector.discordservice.LumRSSService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class TimerCollectorRSS {

    public static CollectorRSS headlineCollector;
    public static CollectorRSS airingCollector;

    public static void  timerStart(JDA jda){
        ScheduledExecutorService sheduled = Executors.newScheduledThreadPool(1);
        sheduled.scheduleAtFixedRate(() -> {
            headlineCollector = new CollectorRSS(TypeRSS.HEADLINE);
            airingCollector = new CollectorRSS(TypeRSS.AIRING);

            airingCollector.collectAndProcessRSS("https://www.livechart.me/feeds/episodes");
            headlineCollector.collectAndProcessRSS("https://www.livechart.me/feeds/headlines");

            LumRSSService.sendDataRSS(headlineCollector.getType(),jda,headlineCollector.dataList);
            LumRSSService.sendDataRSS(airingCollector.getType(),jda,airingCollector.dataList);


        },0,2, TimeUnit.HOURS);
    }
}
