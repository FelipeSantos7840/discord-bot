package org.lumbot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import org.lumbot.collector.TypeRSS;
import org.lumbot.service.DataFileService;

import java.io.IOException;

public class CSetAiringAnimeChat extends ConfigCommands{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("setairingchat")) {
            this.setGuild(event.getGuild());
            this.setTextChannel(event.getChannel().asTextChannel());

            try{
                InteractionHook interactionHook = event.getHook();
                event.reply("Configurando Chat....").setEphemeral(true).queue();
                DataFileService.setDefaultChat(getGuild(),getTextChannel(),"data//chat"+TypeRSS.AIRING +".lum");
                interactionHook.editOriginal("Chat Configurado!!").queue();
            } catch (IOException e){
                event.reply("Erro na Configuração do Chat contate o Suporte!").setEphemeral(true).queue();
            }
        }
    }
}
