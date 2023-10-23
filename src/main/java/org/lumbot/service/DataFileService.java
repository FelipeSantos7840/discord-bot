package org.lumbot.service;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.lumbot.collector.TypeRSS;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFileService extends FileManager {

    public static void setDefaultChat(Guild guild, TextChannel textChannel,String pathChatGuilds) throws IOException{
        boolean haveDeniedPerm = verifyAllPermission(textChannel, guild.getMemberById("1163516372651872307"),false);
        if(haveDeniedPerm){
            boolean haveAllowedPerm = verifyAllPermission(textChannel, guild.getMemberById("1163516372651872307"),true);
            if(!haveAllowedPerm){
                throw new IllegalStateException("O Bot não possui as permissões necessarias(Enviar Link e Enviar Menssagem)");
            }
        }
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
    private static boolean verifyAllPermission(TextChannel textChannel,Member mb,boolean isAllowed){
        Long permission;
        boolean verify;
        permission = returnPermissions(textChannel,mb,isAllowed);
        verify = verifyPermission(permission,isAllowed);
        if(verify){
            if(!isAllowed){
                throw new IllegalStateException("O Bot não possui as permissões necessarias(Enviar Link e Enviar Menssagem)");
            }
            return true;
        }
        for(int x=0; x<mb.getRoles().size();x++){
            permission = returnPermissions(textChannel,mb.getRoles().get(x),isAllowed);
            verify = verifyPermission(permission,isAllowed);
            if(verify){
                return true;
            }
        }
        return false;
    }

    private static Long returnPermissions(TextChannel textChannel, IPermissionHolder ph,boolean isAllowed){
        Long permission;
        try {
            if(isAllowed){
                permission = textChannel.getPermissionOverride(ph).getAllowedRaw();
            } else {
                permission = textChannel.getPermissionOverride(ph).getDeniedRaw();
            }
        } catch (NullPointerException e){
            permission = null;
        }
        return permission;
    }

    private static Long returnPermissions(TextChannel textChannel, IPermissionHolder ph){
        return returnPermissions(textChannel,ph,true);
    }

    private static boolean verifyPermission(Long permission,boolean isAllowed){
        boolean verifyPermission = false;
        if(permission != null){
            if(isAllowed) {
                if ((permission & Permission.MESSAGE_SEND.getRawValue()) != 0
                        && (permission & Permission.MESSAGE_EMBED_LINKS.getRawValue()) != 0) {
                    verifyPermission = true;
                }
            } else {
                if ((permission & Permission.MESSAGE_SEND.getRawValue()) != 0
                        || (permission & Permission.MESSAGE_EMBED_LINKS.getRawValue()) != 0) {
                    verifyPermission = true;
                }
            }

        }
        return verifyPermission;
    }

    public static List<FileData> readGuildChats(String pathChatGuilds){
        List<FileData> listGuildsChats = new ArrayList<>();
        try(BufferedReader bfr = new BufferedReader(new FileReader(pathChatGuilds))){

            String line = bfr.readLine();
            String[] tmp;
            while (line != null) {
                tmp = line.split(",");
                listGuildsChats.add(new FileData(tmp[0],tmp[1]));
                line = bfr.readLine();
            }
        } catch (IOException e){
            System.out.println("Arquivo Não Encontrado!");
        }
        return listGuildsChats;
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

    public static void appendUserPriority(String userMentioned,String guildID,String animeID) throws IOException{
        File file = new File("data//CommandsText//PriorityAnimes//"+animeID+".lum");
        validateDirectory(file);
        verifyFile(file);
        if(verifyDuplicateUser(userMentioned,guildID,file)){
            throw new IllegalStateException("você já está acompanhando esse anime!");
        }
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(file,true))){
            bfw.write(userMentioned+","+guildID+"\n");
        }
    }

    private static void updateUserPriority(List<PriorityData> priorityList,String animeID) throws IOException{
        File file = new File("data//CommandsText//PriorityAnimes//"+animeID+".lum");
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(file))){
            if(!priorityList.isEmpty()){
                for(PriorityData pd : priorityList){
                    bfw.write(pd.getUserMentioned()+","+pd.getGuildID()+"\n");
                }
            } else {
                bfw.close();
                file.delete();
            }
        }
    }

    public static void removeUserPriority(String userMentioned,String guildID,String animeID) throws IOException{
        List<PriorityData> listPriority = readFilePriority(animeID);
        for(PriorityData priority : listPriority){
            if(priority.getGuildID().hashCode() == guildID.hashCode() && priority.getUserMentioned().hashCode() == userMentioned.hashCode()){
                if(priority.getGuildID().equals(guildID) && priority.getUserMentioned().equals(userMentioned)) {
                    listPriority.remove(priority);
                    updateUserPriority(listPriority,animeID);
                    return;
                }
            }
        }
        throw new IllegalStateException(", você não está seguindo esse anime!");
    }

    private static boolean verifyDuplicateUser(String userMentioned,String guildID, File file){
        boolean tmp = false;
        List<String> priorityList = readFilePriority(file);
        for(String priority : priorityList){
            if(priority.hashCode() == (userMentioned +","+guildID).hashCode()){
                if(priority.equals(userMentioned +","+guildID)){
                    tmp = true;
                }
            }
        }
        return tmp;
    }

    private static List<String> readFilePriority(File file){
        List<String> strings = new ArrayList<>();
        try(BufferedReader bfr = new BufferedReader(new FileReader(file))){
            String line = bfr.readLine();
            if(line == null){
                return strings;
            }
            while(line != null){
                strings.add(line);
                line = bfr.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Arquivo de Prioridade Não Encontrado!");
        }
        return strings;
    }

    public static List<PriorityData> readFilePriority(String animeID) {
        String path = "data//CommandsText//PriorityAnimes//"+animeID+".lum";
        List<PriorityData> priorityList= new ArrayList<>();
        try(BufferedReader bfr = new BufferedReader(new FileReader(path))) {
            String line = bfr.readLine();
            String[] split;
            while(line != null){
                split = line.split(",");
                priorityList.add(new PriorityData(split[0],split[1]));
                line = bfr.readLine();
            }
        } catch (IOException e){
            System.out.println("Leitura de Prioridade Invalida!");
        }
        return priorityList;
    }

    public static boolean verifyPriority(String animeID, TypeRSS type){
        if(type != TypeRSS.AIRING){
            return false;
        }
        File file = new File("data//CommandsText//PriorityAnimes//"+animeID+".lum");
        if(!file.exists()){
            return false;
        }
        return true;
    }

    public static boolean verifyPriority(String animeID){
        if(verifyPriority(animeID,TypeRSS.AIRING)){
            return true;
        }
        throw new IllegalStateException(", você não está seguindo esse anime!");
    }
}
