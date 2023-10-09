package org.lumbot.collector;

import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.lumbot.collector.service.FileCollectorRSS;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class CollectorRSS {
    private TypeRSS type;
    private LocalDateTime lastModify;
    private List<DataRSS> dataList;

    public CollectorRSS(TypeRSS type) {
        this.type = type;
    }

    public boolean collectAndProcessRSS(String url){
        try{
            URL feedUrl = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            LocalDateTime lastPublishTime = LocalDateTime.ofInstant(feed.getPublishedDate().toInstant(), ZoneId.of("America/Sao_Paulo"));
            boolean validate = verifyModification(lastPublishTime);

            if(validate){
                List<SyndEntry> entryList = feed.getEntries();
                List<DataRSS> datas = new ArrayList<>();
                for(SyndEntry entry : entryList){
                    LocalDateTime pubTime = LocalDateTime.ofInstant(entry.getPublishedDate().toInstant(), ZoneId.of("America/Sao_Paulo"));
                    SyndEnclosure sde = entry.getEnclosures().get(0);

                    datas.add(new DataRSS(entry.getTitle(),pubTime,sde.getUrl(),entry.getLink()));
                    dataList = datas;
                }
                return true;
            }
        } catch (MalformedURLException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (FeedException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean verifyModification(LocalDateTime lastPublishTime){
        LocalDateTime regPubTime = FileCollectorRSS.getLastPubSend(type);
        if(!regPubTime.isBefore(lastPublishTime)){
            FileCollectorRSS.setLastPubSend(type,lastPublishTime);
            return false;
        }
        return true;
    }
}
