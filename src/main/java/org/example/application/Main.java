package org.example.application;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.example.application.servicebot.GenerateBotService;

public class Main {
    public static void main(String[] args){
        JDABuilder lumBot = GenerateBotService.provideLum();
        if(lumBot != null){
            lumBot.setActivity(Activity.playing("Mortal Kombat 1"));
            lumBot.setStatus(OnlineStatus.ONLINE);
            lumBot.build();
        } else {
            throw new IllegalStateException("Não Gerado Bot Lum!");
        }
    }
}
