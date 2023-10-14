package org.lumbot.service;

import java.io.File;
import java.io.IOException;

public class FileManager {
    protected static void validateDirectory(File file) throws IOException {
        File pasta = new File(file.getParent());
        if(!pasta.exists()){
            pasta.mkdir();
        }
        file.createNewFile();
    }

    protected static void verifyFile(String pathChatGuilds) throws IOException{
        File file = new File(pathChatGuilds);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    protected static void verifyFile(File file) throws IOException{
        verifyFile(file.getPath());
    }
}
