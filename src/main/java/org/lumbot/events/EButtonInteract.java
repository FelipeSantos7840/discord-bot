package org.lumbot.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.lumbot.service.DataFileService;

import java.io.IOException;

public class EButtonInteract extends ConfigEvents{
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("priority")){
            User eventUser = event.getUser();
            Message eventMessage = event.getMessage();

            String animeID = eventMessage.getButtons().get(1).getUrl().substring(31);

            try {
                DataFileService.setUserPriority(eventUser.getAsMention(),event.getGuild().getId(),animeID);
                event.reply(eventUser.getAsMention() + ", você está seguindo "+
                    eventMessage.getEmbeds().get(0).getTitle().split("#")[0] +"agora! 👀").setEphemeral(true).queue();
            } catch (IOException e){
                event.reply("Função Indisponivel! Contate o Desenvolvedor!").setEphemeral(true).queue();
            }
        }
    }
}
