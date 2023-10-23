package org.lumbot.service;



import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.List;
import java.util.Objects;

public class FileData {
    private String guildId;
    private String textChatId;

    public FileData(String guildId, String textChatId) {
        this.guildId = guildId;
        this.textChatId = textChatId;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getTextChatId() {
        return textChatId;
    }

    public void setTextChatId(String textChatId) {
        this.textChatId = textChatId;
    }

    public void sendMessage(JDA jda, MessageEmbed messageEmbed, List<Button> buttonList){
        TextChannel textChannel = jda.getTextChannelById(textChatId);
        if(textChannel != null){
            textChannel.sendMessageEmbeds(messageEmbed).addActionRow(buttonList).queue();
        }
    }

    public void sendMessage(JDA jda, MessageEmbed messageEmbed, List<Button> buttonList,List<PriorityData> pds){
        TextChannel textChannel = jda.getTextChannelById(textChatId);
        StringBuilder stringBuilder = new StringBuilder();
        if(textChannel != null){
            for(PriorityData pd : pds){
                stringBuilder.append(pd.getUserMentioned()+" ");
            }
            textChannel.sendMessage(stringBuilder.toString()).queue();
            textChannel.sendMessageEmbeds(messageEmbed).addActionRow(buttonList).queue();
        }
    }

    @Override
    public String toString() {
        return guildId + "," + textChatId + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileData fileData = (FileData) o;
        return Objects.equals(guildId, fileData.guildId) && Objects.equals(textChatId, fileData.textChatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guildId, textChatId);
    }
}
