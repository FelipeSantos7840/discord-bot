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
            System.out.println("Log: Iniciando Consulta!");
            headlineCollector = new CollectorRSS(TypeRSS.HEADLINE);
            airingCollector = new CollectorRSS(TypeRSS.AIRING);
            System.out.println("Log: Iniciando Coleta!");
            airingCollector.collectAndProcessRSS("https://www.livechart.me/feeds/episodes");
            headlineCollector.collectAndProcessRSS("https://www.livechart.me/feeds/headlines");

            System.out.println("Log: Lista formado com tamanho: " + airingCollector.dataList.size());
            System.out.println("Log: Lista formado com tamanho: " + headlineCollector.dataList.size());

            System.out.println("Log: Validando Mensagens");
            if(headlineCollector.dataList.size() != 0){
                System.out.println("Log: Mandando Mensagens");
                LumRSSService.sendDataRSS(headlineCollector.getType(),jda,headlineCollector.dataList);
            }
            if(airingCollector.dataList.size() != 0){
                System.out.println("Log: Mandando Mensagens");
                LumRSSService.sendDataRSS(airingCollector.getType(),jda,airingCollector.dataList);
            }


            System.out.println("Log: Finalizado Consulta!");
            System.out.println("-----------------------------------------------------------------");

        },0,30, TimeUnit.MINUTES);
    }
}
