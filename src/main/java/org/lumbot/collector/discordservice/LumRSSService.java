package org.lumbot.collector.discordservice;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.lumbot.collector.DataRSS;
import org.lumbot.collector.TypeRSS;
import org.lumbot.service.DataFileService;
import org.lumbot.service.FileData;

import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LumRSSService {
    public static void sendDataRSS(TypeRSS type,JDA jda, List<DataRSS> data){
        if(data == null){
            return;
        }
        List<FileData> fileDataList = DataFileService.readGuildChats("data//chat"+type+".lum");
        if(fileDataList.isEmpty()){
            return;
        }
        for(FileData fileData : fileDataList){
            for(DataRSS dataRSS : data){
                fileData.sendMessage(jda,buildEmbed(dataRSS));
            }
        }
    }

    private static MessageEmbed buildEmbed(DataRSS data){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(data.getTitle());
        eb.setDescription("Data de Lançamento: " + data.getPubDate().format(dtf));
        eb.setFooter(data.getUrl());
        eb.setImage(data.getMedia());
        eb.setColor(new Color(50, 210, 69));
        return eb.build();
    }
}
