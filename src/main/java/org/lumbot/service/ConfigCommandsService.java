package org.lumbot.service;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.lumbot.commands.CAboutLum;
import org.lumbot.commands.CSetAiringAnimeChat;
import org.lumbot.commands.CSetHeadlineAnimeChat;
import org.lumbot.commands.ConfigCommands;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommandsService {
    public static List<ConfigCommands> generateListListener (){
        List<ConfigCommands> list = new ArrayList<>();
        list.add(new CSetAiringAnimeChat());
        list.add(new CSetHeadlineAnimeChat());
        list.add(new CAboutLum());
        return list;
    }

    public static void updateCommands(Guild guild){
        guild.updateCommands().addCommands(
                Commands.slash("setairingchat", "Define chat de envio dos Lançamentos!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("setheadlinechat", "Define chat de envio das Notícias!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("aboutlum", "Conheça Lum!!").setDefaultPermissions(DefaultMemberPermissions.ENABLED)
        ).queue();
    }

    public static void updateCommands(JDA jda){
        jda.updateCommands().addCommands(
                Commands.slash("setairingchat", "Define chat padrão para atualização de Animes!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("setheadlinechat", "Define chat de envio das Notícias!!").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("aboutlum", "Conheça Lum!!").setDefaultPermissions(DefaultMemberPermissions.ENABLED)
        ).queue();
    }

}
