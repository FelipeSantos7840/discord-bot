package org.lumbot.service;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFileService {

    public static void setDefaultChat(Guild guild, TextChannel textChannel,String pathChatGuilds) throws IOException{
        FileData updateDate = new FileData(guild.getId(),textChannel.getId());
        verifyFile(pathChatGuilds);
        List<FileData> listFileData = readGuildChats(pathChatGuilds);
        boolean tmp = false;
        for(FileData data : listFileData){
            if(updateDate.getGuildId().equals(data.getGuildId())){
                tmp = true;
            }
        }
        if(tmp) {
            updateGuildChats(guild, textChannel, pathChatGuilds, listFileData);
        }else{
            appendGuildChats(guild, textChannel, pathChatGuilds);
        }
    }

    public static List<FileData> readGuildChats(String pathChatGuilds) throws IOException{
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

    private static void verifyFile(String pathChatGuilds) throws IOException{
        File file = new File(pathChatGuilds);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    private static void appendGuildChats(Guild guild, TextChannel textChannel,String pathChatGuilds) throws IOException{
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(pathChatGuilds,true))){
            FileData file = new FileData(guild.getId(),textChannel.getId());
            bfw.append(file.toString());
        }
    }

    private static void updateGuildChats(Guild guild, TextChannel textChannel, String pathChatGuilds,List<FileData> datas) throws IOException{
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
