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
            eb.setTitle("Testando Embeds!");
            eb.setDescription("Embed criando exclusivamente para testes de funcionalidade!");
            eb.addField("Teste Primario #1","Teste Primario",true);
            eb.addField("Teste Primario #2","Teste Primario",true);
            eb.addField("Teste Secundario #1","Teste Secundario",false);
            eb.addField("Teste Terceiro #1","Teste Terceiro",false);
            eb.addBlankField(true);
            eb.setImage("https://thumbs2.imgbox.com/ae/0f/fKsJ6lsW_t.jpg");
            eb.setThumbnail("https://thumbs2.imgbox.com/ae/0f/fKsJ6lsW_t.jpg");
            eb.setFooter("Teste do Pé");
            eb.setAuthor("Felipe Dos Santos");
            eb.setColor(100);

            event.replyEmbeds(eb.build()).queue();
        }
    }
}
