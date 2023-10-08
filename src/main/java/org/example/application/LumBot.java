package org.example.application;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LumBot {
    private String token;
    private List<CacheFlag> cacheFlags;
    private List<GatewayIntent> gatewayIntents;
    private List<ListenerAdapter> listerners = new ArrayList<>();

    public LumBot() throws IOException {
        String path = ("data/botToken.txt");
        BufferedReader bfr = new BufferedReader(new FileReader(path));
        String line = bfr.readLine();
        if(line == null){
            throw new IOException("Bot sem Token informado!");
        }
        this.token = line;
        cacheFlags = Arrays.asList(CacheFlag.ACTIVITY,CacheFlag.ROLE_TAGS);
        gatewayIntents = Arrays.asList(GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MEMBERS);
    }

    public String getToken() {
        return token;
    }

    public List<ListenerAdapter> getListerners(){
        return listerners;
    }

}