package org.lumbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CHelpLum extends ConfigCommands{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("helplum")){
            this.setGuild(event.getGuild());
            this.setTextChannel(event.getChannel().asTextChannel());

            try{
                MessageEmbed me = buildEmbedMessage();
                event.replyEmbeds(me).queue();
            }catch (IOException e){
                event.reply("Comando Indisponível! Contate o Desenvolvedor!").queue();
            }
        }
    }

    private MessageEmbed buildEmbedMessage() throws IOException {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        String path = "data//CommandsText//HelpLumText.lum";

        try(BufferedReader bf = new BufferedReader(new FileReader(path))){
            embedBuilder.setTitle(bf.readLine().split("=")[1]);
            embedBuilder.addField("Para Administradores:","",false);
            embedBuilder.addField("/setairingchat",bf.readLine().split("=")[1],false);
            embedBuilder.addField("/setheadlinechat️",bf.readLine().split("=")[1],false);
            embedBuilder.addField("Para Todos:","",false);
            embedBuilder.addField("/remove",bf.readLine().split("=")[1],false);
            embedBuilder.addField("/aboutlum",bf.readLine().split("=")[1],false);
            embedBuilder.addField("/helplum",bf.readLine().split("=")[1],false);
            embedBuilder.addField("Seguir Anime:",bf.readLine().split("=")[1],false);
        }
        return embedBuilder.build();
    }
}
