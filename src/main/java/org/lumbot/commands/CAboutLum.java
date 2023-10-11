package org.lumbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CAboutLum extends ConfigCommands{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("aboutlum")){
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
        String path = "data//CommandsText//AboutLumText.lum";

        try(BufferedReader bf = new BufferedReader(new FileReader(path))){
            embedBuilder.setTitle(bf.readLine().split("=")[1]);
            embedBuilder.setDescription(bf.readLine().split("=")[1]);
            embedBuilder.addField("Como Funciona?😁",bf.readLine().split("=")[1],false);
            embedBuilder.addField("Duvidas!🕵️‍♂️",bf.readLine().split("=")[1],false);
            embedBuilder.addField("Visualizar Código🤖:",bf.readLine().split("=")[1],false);
            embedBuilder.setThumbnail("https://thumbs2.imgbox.com/ae/0f/fKsJ6lsW_t.jpg");
            embedBuilder.setImage("https://thumbs2.imgbox.com/ae/0f/fKsJ6lsW_t.jpg");
        }
        return embedBuilder.build();
    }
}
