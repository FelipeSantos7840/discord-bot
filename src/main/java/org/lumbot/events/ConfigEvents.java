package org.lumbot.events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ConfigEvents extends ListenerAdapter {
    private Guild guild;
    private TextChannel textChannel;

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

    public void setTextChannel(TextChannel textChannel) {
        this.textChannel = textChannel;
    }
}
