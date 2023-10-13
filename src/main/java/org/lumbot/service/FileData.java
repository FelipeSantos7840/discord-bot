package org.lumbot.service;



import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

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

    public void sendMessage(JDA jda, MessageEmbed messageEmbed){
        TextChannel textChannel = jda.getGuildById(this.guildId).getTextChannelById(textChatId);
        if(textChannel != null){
            textChannel.sendMessageEmbeds(messageEmbed).queue();
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
