package org.lumbot.collector.service;

import org.lumbot.collector.TypeRSS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FileCollectorRSS {
    public static LocalDateTime getLastPubSend(TypeRSS type){
        String path = "data//lasPub" +type+".lum";
        File file = new File(path);
        if(!file.exists()){
            try{
               file.createNewFile();
               return LocalDateTime.parse("1999-01-01");
            } catch (IOException e){
                System.out.println("Erro ao criar arquivo!");
                e.printStackTrace();
            }
        }
        try(BufferedReader bfr = new BufferedReader(new FileReader(path))){
            String line = bfr.readLine();
            if(line == null){
                return LocalDateTime.parse("1999-01-01");
            } else {
                return LocalDateTime.parse(line);
            }
        } catch (IOException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (DateTimeParseException e){
            System.out.println("Data em Formato Incorreto!");
            e.printStackTrace();
        }
        throw new IllegalStateException("Arquivo com Erro!");
    }
}
