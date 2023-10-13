package org.lumbot.collector.discordservice;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.lumbot.collector.DataRSS;
import org.lumbot.collector.TypeRSS;
import org.lumbot.service.DataFileService;
import org.lumbot.service.FileData;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            for(int x = data.size()-1;x>=0;x--){
                List<Button> buttonList = buildButton(data.get(x));
                MessageEmbed messageEmbed = buildEmbed(data.get(x));
                fileData.sendMessage(jda,messageEmbed,buttonList);
            }
        }
    }

    private static MessageEmbed buildEmbed(DataRSS data){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(data.getTitle());
        eb.setDescription("Data de Lançamento: " + data.getPubDate().format(dtf));
        eb.setImage(data.getMedia());
        eb.setColor(new Color(50, 210, 69));
        return eb.build();
    }

    private static List<Button> buildButton(DataRSS data){
        List<Button> buttons = new ArrayList<>();
        buttons.add(Button.link(data.getUrl(),"Anime Site"));
        return buttons;
    }
}
