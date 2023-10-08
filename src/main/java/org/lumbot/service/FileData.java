package org.lumbot.service;

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
