package com.qau.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterRepository {

	@Autowired
	private com.qau.entites.Config config;
	@Autowired
    private PorterStemmer stemmer;
	
	@Autowired
	FileRepository fileRepository;
    public List<String> stopWordsList = new ArrayList<String>();
    
    public String specialCharactorsRemoval(String specialCharactors){
        specialCharactors = specialCharactors.replace("-", " ").replace(":", " ")
                        .replace(",", " ").replace("+", " ").replace("%", " ")
                        .replace("?", " ").replace("'", " ").replace(";", " ").replace("&", " ")
                        .replace("#", " ").replace("ω", " ").replace("'s", "")
                            .replace("*", " ").replace(".", " ").replace("_", " ").replace("^", " ")
                            .replace("e.g."," ").replace("`", " ").replace("—", " ").replace("•", " ").replace("“", " ")
                            .replace("#", " ").replace("1", " ").replace("2", " ").replace("3", " ").replace("4", " ")
                            .replace("5", " ").replace("6", " ").replace("7", " ").replace("8", " ").replace("9", " ").replace("0", " ")
                            .replace("\""," ").replace("\\", " ").replace("/"," ").replace("|"," ").replace("~"," ").replace("®"," ")
                         .replace("<"," ").replace(">", " ").replace("!", " ").replace("$"," ").replace("="," ").replace("@"," ").replace("”"," ")
                        .replace("{", " ").replace("}", " ").replace("(", " ").replace(")", " ").replace("[", " ").replace("]", " ");
        specialCharactors = specialCharactors.replaceAll("\\s+", " ").trim();
        return specialCharactors.toLowerCase().trim();
    }
    
    public void stopWordsRemoval(String document, final com.qau.interfaces.Filters filters){
        try
        {
        	if (stopWordsList.isEmpty()) {
    			stopWordsListUpdate(config.getStopWordsPath());
    		}
        	
            String[] vocabulary = document.split(" ");
            StringBuilder sb = new StringBuilder();
            for(String term:vocabulary){
                term = stemmer.stem(term);
                if(term.length()>2 && (!stopWordsList.contains(term))){
                    sb.append(term).append(" ");
                }
            }
            document = sb.toString().trim();
            if(!document.isEmpty())
                filters.OnStopWordsRemoval(document);
        }
        catch(Exception e){
            System.err.println("err in StopWordsRemoval "+ e.getMessage());
        }
    }
    
    public void stopWordsListUpdate(String path){
        // path to read stop words from list or txt file
        stopWordsList.clear();
        fileRepository.readFromFile(path, new com.qau.interfaces.FileHandling() {
            @Override
            public void OnFileRead(String document) {
                stopWordsList.add(document);
            }

            @Override
            public void OnFileReadingComplete() {
            }
        });
    }
}