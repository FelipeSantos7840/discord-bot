package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import org.example.service.DataFileService;

import java.io.IOException;

public class CSetAnimeChat extends ConfigCommands{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("setanimechat")) {
            this.setGuild(event.getGuild());
            this.setTextChannel(event.getChannel().asTextChannel());

            try{
                InteractionHook ih = event.getHook();
                event.reply("Configurando Chat....").setEphemeral(true).queue();
                DataFileService.setDefaultChat(getGuild(),getTextChannel());
                ih.editOriginal("Chat Configurado!!").queue();
            } catch (IOException e){
                event.reply("Erro na Configuração do Chat contate o Suporte!").setEphemeral(true).queue();
            }
        }
    }
}
