package org.example.service;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFileService {

    private static final String pathChatGuilds = "data//chatGuilds.lum";
    public static void setDefaultChat(Guild guild, TextChannel textChannel) throws IOException{
        FileData updateDate = new FileData(guild.getId(),textChannel.getId());
        verifyFile();
        List<FileData> listFileData = readGuildChats();
        boolean tmp = false;
        for(FileData data : listFileData){
            if(updateDate.getGuildId().equals(data.getGuildId())){
                tmp = true;
            }
        }
        if(tmp) {
            updateGuildChats(guild, textChannel, listFileData);
        }else{
            appendGuildChats(guild, textChannel);
        }
    }

    public static List<FileData> readGuildChats() throws IOException{
        List<FileData> listGuildsChats = new ArrayList<>();
        try(BufferedReader bfr = new BufferedReader(new FileReader(pathChatGuilds))){

            String line = bfr.readLine();

            while (line != null) {
                String[] tmp = line.split(",");
                listGuildsChats.add(new FileData(tmp[0],tmp[1]));
                line = bfr.readLine();
            }
        }
        return listGuildsChats;
    }

    private static void verifyFile() throws IOException{
        File file = new File(pathChatGuilds);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    private static void appendGuildChats(Guild guild, TextChannel textChannel) throws IOException{
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(pathChatGuilds,true))){
            FileData file = new FileData(guild.getId(),textChannel.getId());
            bfw.append(file.toString());
        }
    }

    private static void updateGuildChats(Guild guild, TextChannel textChannel, List<FileData> datas) throws IOException{
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(pathChatGuilds))){
            FileData updateFileData = new FileData(guild.getId(),textChannel.getId());
            for(FileData files : datas){
                if(!updateFileData.getGuildId().equals(files.getGuildId())){
                    bfw.write(files.toString());
                } else {
                    bfw.write(updateFileData.toString());
                }
            }
        }
    }
}
