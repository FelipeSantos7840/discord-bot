package org.lumbot.collector.collectorservice;

import org.lumbot.collector.DataRSS;
import org.lumbot.collector.TypeRSS;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FileCollectorRSS {

    private static void validateDirectory(File file) throws IOException{
        File pasta = new File(file.getParent());
        if(!pasta.exists()){
            pasta.mkdir();
        }
        file.createNewFile();
    }
    public static LocalDateTime getLastPubSend(TypeRSS type){
        String path = "data//att//lastPub" +type+".lum";
        File file = new File(path);
        if(!file.exists()){
            try{
                validateDirectory(file);
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
        String path = "data//att//lastPub" +type+".lum";
        File file = new File(path);
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file))){
            validateDirectory(file);
            bfw.write(ldt.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void setLastDataReceived(TypeRSS type, DataRSS data){
        String path = "data//att//lastData" +type+".lum";
        File file = new File(path);
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file))){
            validateDirectory(file);
            bfw.write(data.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getLastDataReceived(TypeRSS type){
        String path = "data//att//lastData" +type+".lum";
        File file = new File(path);
        try{
            validateDirectory(file);
        } catch (IOException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        try (BufferedReader bfw = new BufferedReader(new FileReader(file))){
            return bfw.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        throw new IllegalStateException("Arquivo em Estado Incorreto!");
    }
}
