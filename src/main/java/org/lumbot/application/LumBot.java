package org.lumbot.application;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.lumbot.commands.ConfigCommands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;

public class LumBot {
    private String token;
    private List<CacheFlag> cacheFlags;
    private List<GatewayIntent> gatewayIntents;
    private List<ConfigCommands> listerners;
    public LumBot(List<CacheFlag> cacheFlags, List<GatewayIntent> gatewayIntents, List<ConfigCommands> listerners) throws IOException {
        String path = ("data/botToken.lum");
        BufferedReader bfr = new BufferedReader(new FileReader(path));
        String line = bfr.readLine();
        if(line == null){
            throw new IOException("Bot sem Token informado!");
        }
        this.cacheFlags = cacheFlags;
        this.gatewayIntents = gatewayIntents;
        this.listerners = listerners;

        this.token = line;
    }

    public String getToken() {
        return token;
    }

    public List<CacheFlag> getCacheFlags() {
        return cacheFlags;
    }

    public void addCacheFlags(CacheFlag cacheFlags) {
        this.cacheFlags.add(cacheFlags);
    }

    public List<GatewayIntent> getGatewayIntents() {
        return gatewayIntents;
    }

    public void addGatewayIntents(GatewayIntent gatewayIntents) {
        this.gatewayIntents.add(gatewayIntents);
    }

    public List<ConfigCommands> getListerners() {
        return listerners;
    }

    public void addListerners(ConfigCommands listerners) {
        this.listerners.add(listerners);
    }

    public JDABuilder builder(){
        JDABuilder lumBuilder = JDABuilder.createDefault(this.token,gatewayIntents);
        lumBuilder.enableCache(cacheFlags);

        lumBuilder.addEventListeners(listerners.toArray());
        return lumBuilder;
    }
}