package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CSetAnimeChat extends ConfigCommands{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("setanimechat")) {
            this.setGuild(event.getGuild());
            this.setTextChannel(event.getChannel().asTextChannel());

            event.reply("Chat Centralizado!").queue();
        }
    }
}
