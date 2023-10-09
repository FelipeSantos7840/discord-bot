package org.lumbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CAboutLum extends ConfigCommands{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("aboutlum")){
            this.setGuild(event.getGuild());
            this.setTextChannel(event.getChannel().asTextChannel());

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Olá, Eu soa a Lum!");
            eb.setDescription(embedDescrption());
            eb.addField("Como funciona?","Em um chat pré-definido disponibilizo noticiais e atualizações de lançamentos " +
                    "dos animes da temporada!",false);
            eb.addField("Visualizar Código:","O meu código está disponvel para estudo e análise no link:\nhttps://github.com/FelipeSantos7840/lum-bot",false);
            eb.addField("Principais Comandos:","",false);
            eb.addField("Set Airing Chat:","Define chat que receberá atualizações de episódios",true);
            eb.addField("Set Headline Chat:","Define chat que receberá notícias de animes e lançamentos",true);
            eb.setThumbnail("https://thumbs2.imgbox.com/ae/0f/fKsJ6lsW_t.jpg");
            eb.setImage("https://thumbs2.imgbox.com/ae/0f/fKsJ6lsW_t.jpg");
            eb.setFooter("=D");
            eb.setColor(100);

            event.replyEmbeds(eb.build()).queue();
        }
    }

    private StringBuilder embedDescrption(){
        StringBuilder sb = new StringBuilder();
        sb.append("Olá! Eu sou a Lum, uma aplicação ");
        sb.append("código aberto de notícias e atualizações ");
        sb.append("do mundo dos Animes!");

        return sb;
    }
}
