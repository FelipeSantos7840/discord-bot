package org.lumbot.collector.collectorservice;

import org.lumbot.collector.TypeRSS;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FileCollectorRSS {
    public static LocalDateTime getLastPubSend(TypeRSS type){
        String path = "data//lasPub" +type+".lum";
        File file = new File(path);
        if(!file.exists()){
            try{
               file.createNewFile();
               setLastPubSend(type,LocalDateTime.parse("1999-01-01T00:00:00"));
               return LocalDateTime.parse("1999-01-01T00:00:00");
            } catch (IOException e){
                System.out.println("Erro ao criar arquivo!");
                e.printStackTrace();
            }
        }
        try(BufferedReader bfr = new BufferedReader(new FileReader(path))){
            String line = bfr.readLine();
            if(line == null){
                return LocalDateTime.parse("1999-01-01T00:00:00");
            } else {
                return LocalDateTime.parse(line);
            }
        } catch (IOException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (DateTimeParseException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        throw new IllegalStateException("Arquivo com Erro!");
    }

    public static void setLastPubSend(TypeRSS type,LocalDateTime ldt){
        String path = "data//lasPub" +type+".lum";
        File file = new File(path);
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file))){
            if(!file.exists()){
                file.createNewFile();
            }
            bfw.write(ldt.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
