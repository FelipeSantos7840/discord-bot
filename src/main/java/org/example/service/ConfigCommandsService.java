package org.example.service;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.commands.CSetAnimeChat;
import org.example.commands.ConfigCommands;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommandsService {
    public static List<ConfigCommands> generateListListener (){
        List<ConfigCommands> list = new ArrayList<>();
        list.add(new CSetAnimeChat());
        return list;
    }

    public static void updateCommands(Guild guild){
        guild.updateCommands().addCommands(
                Commands.slash("setanimechat", "Define chat padrão para atualização de Animes!")).queue();
    }

    public static void updateCommands(JDA jda){
        jda.updateCommands().addCommands(
                Commands.slash("setAnimeChat", "Define chat padrão para atualização de Animes!")).queue();
    }

}
