package org.example.service;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.commands.CSetAiringAnimeChat;
import org.example.commands.CSetHeadlineAnimeChat;
import org.example.commands.ConfigCommands;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommandsService {
    public static List<ConfigCommands> generateListListener (){
        List<ConfigCommands> list = new ArrayList<>();
        list.add(new CSetAiringAnimeChat());
        list.add(new CSetHeadlineAnimeChat());
        return list;
    }

    public static void updateCommands(Guild guild){
        guild.updateCommands().addCommands(
                Commands.slash("setairingchat", "Define chat de envio dos Lançamentos!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("setheadlinechat", "Define chat de envio das Notícias!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED)
        ).queue();
    }

    public static void updateCommands(JDA jda){
        jda.updateCommands().addCommands(
                Commands.slash("setairingchat", "Define chat padrão para atualização de Animes!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("setheadlinechat", "Define chat de envio das Notícias!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED)
        ).queue();
    }

}
