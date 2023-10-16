package org.lumbot.service;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.lumbot.commands.*;
import org.lumbot.events.EButtonInteract;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommandsService {
    public static List<ListenerAdapter> generateListListener (){
        List<ListenerAdapter> list = new ArrayList<>();
        list.add(new CSetAiringAnimeChat());
        list.add(new CSetHeadlineAnimeChat());
        list.add(new CAboutLum());
        list.add(new EButtonInteract());
        list.add(new CHelpLum());
        list.add(new CRemove());
        return list;
    }

    public static void updateCommands(Guild guild){
        guild.updateCommands().addCommands(
                Commands.slash("setairingchat", "Define chat de envio dos Lançamentos!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("setheadlinechat", "Define chat de envio das Notícias!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("aboutlum", "Conheça Lum!!").setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("helplum", "Apresenta os comandos disponíveis da Lum!!!").setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("remove","Remove anime com prioridade do usuario!")
                        .addOption(OptionType.STRING,"animeid","Informe o ID do anime para remover",true,false)
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED)
        ).queue();
    }

    public static void updateCommands(JDA jda){
        jda.updateCommands().addCommands(
                Commands.slash("setairingchat", "Define chat padrão para atualização de Animes!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("setheadlinechat", "Define chat de envio das Notícias!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("aboutlum", "Conheça Lum!!").setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("helplum", "Apresenta os comandos disponíveis da Lum!!!").setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("remove","Remove anime com prioridade do usuario!")
                        .addOption(OptionType.STRING,"animeid","ID do anime para remover",true,false)
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED)
        ).queue();
    }

    public static void removeCommands(Guild guild){
        guild.updateCommands().queue();
    }

    public static void removeCommands(JDA jda){
        jda.updateCommands().queue();
    }

}
