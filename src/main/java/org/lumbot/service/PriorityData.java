package org.lumbot.service;

public class PriorityData {
    private String userMentioned;
    private String guildID;

    public PriorityData(String userMentioned, String guildID) {
        this.userMentioned = userMentioned;
        this.guildID = guildID;
    }

    public String getUserMentioned() {
        return userMentioned;
    }

    public void setUserMentioned(String userMentioned) {
        this.userMentioned = userMentioned;
    }

    public String getGuildID() {
        return guildID;
    }

    public void setGuildID(String guildID) {
        this.guildID = guildID;
    }

    @Override
    public String toString() {
        return this.userMentioned;
    }
}
