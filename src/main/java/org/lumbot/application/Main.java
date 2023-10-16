package org.lumbot.application;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.lumbot.application.servicebot.GenerateBotService;
import org.lumbot.collector.collectorservice.TimerCollectorRSS;
import org.lumbot.service.ConfigCommandsService;

public class Main {
    public static void main(String[] args){
        JDABuilder lumBot = GenerateBotService.provideLum();
        if(lumBot != null){
            lumBot.setActivity(Activity.listening("/helplum && /aboutlum"));
            lumBot.setStatus(OnlineStatus.ONLINE);
            JDA jda = lumBot.build();
            try{
                jda.awaitReady();
                //ConfigCommandsService.updateCommands(jda.getGuilds().get(0));
                //ConfigCommandsService.removeCommands(jda.getGuilds().get(0));
                //ConfigCommandsService.updateCommands(jda);
                //ConfigCommandsService.removeCommands(jda);

                TimerCollectorRSS.timerStart(jda);

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("Não Gerado Bot Lum!");
        }
    }
}
