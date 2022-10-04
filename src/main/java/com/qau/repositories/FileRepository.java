package com.qau.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class FileRepository {
    
    private File file = null;
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private Integer fileCounter;
    
    public void readFromFile(final String table_path, final com.qau.interfaces.FileHandling fileHandling)
    {
        boolean readComplete = false;
        try
        {
            file =new File(table_path+".txt");
            if(!file.exists())
            {
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String document = null;
            while((document = br.readLine()) != null)
            {
                document.trim();
                if(!document.isEmpty()){
                    fileHandling.OnFileRead(document);
                }
            }
            readComplete = true;
        }
        catch(Exception exp)
        {
            System.out.println("Exception Read File: "+exp.getMessage());
            System.out.println("File: "+file.getAbsolutePath());
        }
        finally
        {
            try {
                br.close();
                if(readComplete)
                    fileHandling.OnFileReadingComplete();
            } catch (IOException ex) {
                Logger.getLogger(FileRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void createBufferedWriter(String table_path){
        try
        {
            fileCounter = 0;
            file = new File(table_path+".txt");
            if(!file.exists())
            {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file.getAbsolutePath(), false));
        }
        catch(Exception exp){
            System.out.println("Exception Read File: "+exp.getMessage());
        }
    }
    
    public void writeToFile(String document)
    {
        try
        {
            bw.write(document);
            bw.newLine();
            fileCounter++;
            if(fileCounter == 500)
                flushBufferedWriter();
        }
        catch(Exception exp){
            System.out.println("Exception Write File: "+exp.getMessage());
        }
    }
    
    public void writeToFile(int docId, String doc)
    {
        try
        {
            doc = docId + " " + doc;
            bw.write(doc);
            bw.newLine();
        }
        catch(Exception exp){
            System.out.println("Exception Write File: "+exp.getMessage());
        }
    }
    
    public void flushBufferedWriter()
    {
        try
        {
            fileCounter = 0;
            bw.flush();
        }
        catch(Exception exp){
            System.out.println("Exception Write File: "+exp.getMessage());
        }
    }
    
    public void closeBufferedWriter()
    {
        try
        {
            bw.close();
        }
        catch(Exception exp){
            System.out.println("Exception Write File: "+exp.getMessage());
        }
    }
}
