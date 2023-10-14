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

    protected static boolean verifyFile(String pathChatGuilds) throws IOException{
        File file = new File(pathChatGuilds);
        return file.createNewFile();
    }

    protected static boolean verifyFile(File file) throws IOException{
        return verifyFile(file.getPath());
    }
}
