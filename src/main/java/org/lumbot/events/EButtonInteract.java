package org.lumbot.events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class EButtonInteract extends ConfigEvents{
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("priority")){
            event.reply("Acompanhando! 👀").setEphemeral(true).queue();
        }
    }
}
