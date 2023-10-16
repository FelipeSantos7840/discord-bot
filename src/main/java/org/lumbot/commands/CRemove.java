package org.lumbot.commands;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.lumbot.service.DataFileService;

import java.io.IOException;

public class CRemove extends ConfigCommands{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("remove")){
            String animeID = event.getOption("animeid").getAsString();
            String mentionUser = event.getUser().getAsMention();
                try{
                    DataFileService.verifyPriority(animeID);
                    DataFileService.removeUserPriority(mentionUser,event.getGuild().getId(),animeID);
                    event.reply(mentionUser+", parou de seguir o anime!").setEphemeral(true).queue();
                } catch (IOException e){
                    event.reply("Problema com o Arquivo, entre em contato com o Desenvolvedor!").setEphemeral(true).queue();
                } catch (IllegalStateException e){
                    event.reply(mentionUser+e.getMessage()).setEphemeral(true).queue();
                }
        }
    }
}
