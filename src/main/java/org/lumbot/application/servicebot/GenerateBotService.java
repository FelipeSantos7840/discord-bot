package org.lumbot.application.servicebot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.lumbot.application.LumBot;
import org.lumbot.commands.ConfigCommands;
import org.lumbot.service.ConfigCommandsService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GenerateBotService {
    public static JDABuilder provideLum(){
        JDABuilder lum = null;
        try{
            LumBot lumBot = new LumBot(cacheFlags(),gatewayIntents(),listenerAdapters());
            lum = lumBot.builder();
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return lum;

    }

    private static List<GatewayIntent> gatewayIntents(){
        List<GatewayIntent> list = Arrays.asList();
        return list;
    }

    private static List<CacheFlag> cacheFlags(){
        List<CacheFlag> list = Arrays.asList(CacheFlag.ACTIVITY,CacheFlag.VOICE_STATE,CacheFlag.EMOJI,CacheFlag.STICKER);
        return list;
    }

    private static List<ListenerAdapter> listenerAdapters(){
        List<ListenerAdapter> list = ConfigCommandsService.generateListListener();
        return list;
    }
}
